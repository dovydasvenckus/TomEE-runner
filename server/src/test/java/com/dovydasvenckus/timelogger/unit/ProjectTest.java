package com.dovydasvenckus.timelogger.unit;

import org.junit.*;
import org.junit.AfterClass;
import com.dovydasvenckus.timelogger.domain.Project;

import javax.persistence.*;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class ProjectTest {
    static EntityManagerFactory emf;
    static EntityManager em;
    static EntityTransaction tx;

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

        List<Project> books = em.createQuery("SELECT p FROM Project AS p").getResultList();
        assertEquals(1, books.size());
    }

    @Test(expected = RollbackException.class)
    public void shouldRejectProjectWithoutTitle(){
        Project project = new Project();
        project.setDescription("Title");

        em.persist(project);
        tx.commit();

        List<Project> projects = em.createQuery("SELECT p FROM Project AS p").getResultList();
        assertEquals(0, projects.size());
    }
    
    @Test
    public void namedQueryShouldReturnResult(){
        Project project = new Project("Project", "Description");

        em.persist(project);

        List<Project> projects = em.createNamedQuery("Project.findAll").getResultList();
        assertEquals(1, projects.size());
    }
}
