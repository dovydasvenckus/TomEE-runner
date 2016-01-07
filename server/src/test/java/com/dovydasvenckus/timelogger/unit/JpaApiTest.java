package com.dovydasvenckus.timelogger.unit;

import com.dovydasvenckus.timelogger.domain.Project;
import com.dovydasvenckus.timelogger.domain.Tag;
import com.dovydasvenckus.timelogger.domain.TimeLogEntry;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dovydas
 */
public class JpaApiTest {
    static EntityManagerFactory emf;
    static EntityManager em;
    static EntityTransaction tx;
    
    public JpaApiTest() {
    }

    @BeforeClass
    public static void initEntityManager(){
        emf = Persistence.createEntityManagerFactory("timeLoggerPU");
        em = emf.createEntityManager();
    }

    @AfterClass
    public static void tearDown(){
        em.close();
        emf.close();
    }

    @Before
    public void initTransaction(){
        tx = em.getTransaction();
        tx.begin();
    }
    
    @After
    public void rollBack(){
        if (tx.isActive())
            tx.rollback();
    }

    @Test
    public void shouldCreateProject(){
        Project project = new Project("Project", "Description");

        em.persist(project);

        List<Project> books = em.createQuery("SELECT p FROM Project AS p", Project.class).getResultList();
        assertEquals(1, books.size());
    }
    
    @Test
    public void shouldFindProject(){
        Project project = new Project("Project", "Description");

        em.persist(project);
        
        assertNotNull(em.find(Project.class, project.getId()));
    }
    
    @Test
    public void shouldRemoveProject(){
        Project project = new Project("Title", "Description");
        em.persist(project);
        
        em.remove(project);
        
        assertNotNull(project);
        assertNotNull(project.getId());
        assertNull(em.find(Project.class, project.getId()));
    }
    
    @Test
    public void refreshShouldDiscardLocalChanges(){
        String title = "Title";
        Project project = new Project(title, "Description");
        em.persist(project);
        em.flush();
        
        project.setName("My project");
        em.refresh(project);
        
        assertEquals(title, project.getName());
        
    }
    
    @Test
    public void shouldBeAbleToCheckIfContainsInDb(){
        Project project = new Project("Title", "Description");
        
        em.persist(project);
        em.flush();
        
        assertTrue(em.contains(project));
        
        em.remove(project);
        em.flush();
        
        assertFalse(em.contains(project));
    }
    
    @Test
    public void shouldBeAbleToDetachEntity(){
        Project project = new Project("Title", "Description");
        
        em.persist(project);
        em.flush();
        
        em.detach(project);
        
        assertFalse(em.contains(project));
    }
    
    @Test
    public void shouldBeAbleToReatach(){
        String changedName = "Other name";
        Project project = new Project("Title", "Description");
        
        em.persist(project);
        em.flush();
        em.clear();
        
        project.setName(changedName);
        em.merge(project);
        
        assertEquals(changedName, em.find(Project.class, project.getId()).getName());
    }
    
    @Test
    public void shouldGetEntityUsingJPQL(){
        String title = "Title";
        Project project = new Project(title, "Description");
        em.persist(project);
        em.flush();
        
        TypedQuery<Project> query = em.createQuery("SELECT p FROM Project p WHERE p.name = :name", Project.class);
        query.setParameter("name", title);
        
        assertEquals(project.getName(), query.getSingleResult().getName());
    }
    
    @Test
    public void queringViaCriteriaAPIShouldWork(){
        String title = "My project";
        Project project = new Project(title, "Description");
        em.persist(project);
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Project> criteriaQuery = builder.createQuery(Project.class);
        
        Root<Project> p = criteriaQuery.from(Project.class);
        criteriaQuery.select(p).where(builder.equal(p.get("name"), title));
        
        TypedQuery<Project> query = em.createQuery(criteriaQuery);
        Project projectFromDb = query.getSingleResult();
        
        assertEquals(project.getName(), projectFromDb.getName());
    }
    
    @Test(expected = RollbackException.class)
    public void shouldRejectProjectWithoutTitle(){
        Project project = new Project();
        project.setDescription("Title");

        em.persist(project);
        tx.commit();

        List<Project> projects = em.createQuery("SELECT p FROM Project AS p", Project.class).getResultList();
        assertEquals(0, projects.size());
    }
    
    @Test
    public void namedQueryShouldReturnResult(){
        Project project = new Project("Project", "Description");

        em.persist(project);

        List<Project> projects = em.createNamedQuery("Project.findAll", Project.class).getResultList();
        assertEquals(1, projects.size());
    }
    
    @Test
    public void cascadingShouldWork(){
        Project project = new Project("Title", "Descr");
        TimeLogEntry entry = new TimeLogEntry();
        entry.setTitle("Done something");
        entry.setProject(project);
        
        em.persist(project);
        em.persist(entry);
        //Do not need to persist entry because I have set up cascading on project class
        em.flush();
        List<TimeLogEntry> entries = em.createQuery("SELECT t FROM TimeLogEntry t", TimeLogEntry.class).getResultList();
        assertEquals(1, entries.size());
    }
    
    @Test
    public void orderingOnRelationshipShouldWork(){
        Project project = new Project("Title", "Descr");
        Tag tag1 = new Tag("B Tag name");
        
        Tag tag2 = new Tag("A Tag name");
        
        em.persist(project);
        em.persist(tag1);
        em.persist(tag2);
        
        project.getTags().add(tag1);
        project.getTags().add(tag2);
        em.flush();
        em.refresh(project);
        
        List<String> names = project.getTags().stream().map(Tag::getName).collect(Collectors.toList());
        List<String> expected = Lists.newArrayList("A Tag name", "B Tag name");
        
        assertEquals(2, project.getTags().size());
        assertEquals(expected, names);
    }
}
