package utils;

import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupOwners {

    public static void main(String[] args) {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        Owner ow1 = new Owner("julemand", "paltholmpark", 28213321);
        Owner ow2 = new Owner("majsen", "lyngeeeeby", 263453321);
        Owner ow3 = new Owner("aleeeksen", "nææærumdu", 282124321);

        em.getTransaction().begin();
        em.persist(ow1);
        em.persist(ow2);
        em.persist(ow3);
        em.getTransaction().commit();
    }
}
