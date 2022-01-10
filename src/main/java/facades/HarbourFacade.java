package facades;

import dtos.OwnerDTO;
import dtos.OwnersDTO;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class HarbourFacade {

    private static EntityManagerFactory emf;
    private static HarbourFacade instance;

    public HarbourFacade() {
    }

    public static HarbourFacade getHarbourFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new HarbourFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public OwnersDTO getAllOwners () {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            TypedQuery<Owner> query = em.createNamedQuery("Owner.getAllRows", Owner.class);
            List<Owner> result = query.getResultList();
            OwnersDTO dto = new OwnersDTO(result);
            em.getTransaction().commit();
            return dto;
        }  finally {
            em.close();
        }
    }







}
