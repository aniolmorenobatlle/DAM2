package dam2.amoreno;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class LlibresDAOImpl implements LlibresDAO {

    private EntityManagerFactory emf;
    public LlibresDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Llibres> LlistarLlibre() {

        EntityManager em = emf.createEntityManager();
        List<Llibres> llibres = new ArrayList<>();

        try {
            
            TypedQuery<Llibres> query = em.createQuery("SELECT l from Llibres l", Llibres.class);

            llibres = query.getResultList();

        } catch (Exception e) {

            System.out.println("Erro en llistar els llibres.");

        } finally {
            em.close();
        }


        return llibres;

    }


    public boolean create(Llibres llibres) {
        
        EntityManager em = null;
        EntityTransaction trans = null;

        try {
            
            em = emf.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            em.persist(llibres);
            trans.commit();

            System.out.println();

            System.out.println("Llibre afegit correctament!!");

            return true;


        } catch (Exception e) {
            
            System.out.println("Error en afegir el llibres. Comprova que totes les dades siguin correctes.");

            if (trans != null && trans.isActive()) trans.rollback();

            return false;

        } finally {
            
            if (em != null) em.close();

        }

    }


    public boolean update(int id, Llibres llibres) {
        EntityManager em = null;
        EntityTransaction trans = null;

        try {
            
            em = emf.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            System.out.println();

            Llibres llibreId = em.find(Llibres.class, id);

            llibreId.setTitol(llibres.getTitol());
            llibreId.setIsbn(llibres.getIsbn());
            llibres.setAny_publicacio(llibres.getAny_publicacio());

            em.merge(llibreId);
            trans.commit();

            System.out.println("Llibre editat correctament.");

            return true;

        } catch (Exception e) {
            
            System.out.println("Error en editar el llibre. Comprova que totes les dades siguin correctes.");

            if (trans != null && trans.isActive()) trans.rollback();

            return false;

        } finally {
            
            if (em != null) em.close();

        }
    }


    public boolean delete(int id) {
        EntityManager em = null;
        EntityTransaction trans = null;

        try {
            
            em = emf.createEntityManager();
            trans = em.getTransaction();
            trans.begin();

            System.out.println();

            Llibres llibreId = em.find(Llibres.class, id);

            if (llibreId != null) {
                System.out.println();

                em.remove(llibreId);
                trans.commit();

                System.out.println("Llibre eliminat correctament.");

                return true;
            } else {
                System.out.println();
                System.out.println("Llibre no trobat!!");
                return false;
            }

        } catch (Exception e) {
            
            System.out.println("Error en editar el llibre. Comprova que totes les dades siguin correctes.");

            if (trans != null && trans.isActive()) trans.rollback();

            return false;

        } finally {
            
            if (em != null) em.close();

        }
    }

}
