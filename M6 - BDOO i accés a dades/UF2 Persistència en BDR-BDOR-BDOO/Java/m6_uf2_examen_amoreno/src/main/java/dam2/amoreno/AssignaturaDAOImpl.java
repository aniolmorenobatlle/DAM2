package dam2.amoreno;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class AssignaturaDAOImpl implements AssignaturaDAO {

    private EntityManagerFactory emf;
    public AssignaturaDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Assignatura> llistarAssignatures() {

        EntityManager em = emf.createEntityManager();

        List<Assignatura> assignaturas = new ArrayList<>();

        try {
            
            TypedQuery<Assignatura> query = em.createQuery("SELECT a FROM Assignatura a", Assignatura.class);

            assignaturas = query.getResultList();


        } catch (Exception e) {

            System.out.println("Error en llistar els Assignaturas!!");
        
        } finally {
            em.close();
        }


        return assignaturas;
    }


    public boolean create(Assignatura assignatura) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
        
            trans = em.getTransaction();
            trans.begin();

            em.persist(assignatura);

            trans.commit();

            System.out.println("Assignatura afegit correctament!!");

            return true;

        } catch (Exception e) {
            
            System.out.println("Error en afegir l'Assignatura. Comprova que totes les dades siguin correctes.");
            
            if (trans.isActive()) {
                trans.rollback();
            }
            
            return false;

        } finally {

            if (em != null) {
                em.close();
            }
        }

    }


    public boolean update(Assignatura assignatura, Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
            
            trans = em.getTransaction();
            trans.begin();

            Assignatura assignatures = em.find(Assignatura.class, id);

            assignatures.setNom(assignatura.getNom());
            assignatures.setDescripcio(assignatura.getDescripcio());
            assignatures.setCodi(assignatura.getCodi());

            em.merge(assignatures);

            trans.commit();

            System.out.println("Assignatura editat correctament!!");

            return true;

        } catch (Exception e) {

            System.out.println("Error en editar l'Assignatura. Comprova que totes les dades siguin correctes.");


            if (trans.isActive()) {
                trans.rollback();
            }
            
            return false;

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }


    public boolean delete (Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
            
            trans = em.getTransaction();
            trans.begin();

            Assignatura assignatura = em.find(Assignatura.class, id);

            if (assignatura != null) {
                em.remove(assignatura);
                trans.commit();

                System.out.println("Estudinat eliminat correctament!!");

                return true;
            } else {
                System.out.println("Assignatura no trobat.");
                return false;
            }

        } catch (Exception e) {

            System.out.println("Error en eliminar l'Assignatura. Comprova que totes les dades siguin correctes.");


            if (trans.isActive()) {
                trans.rollback();
            }
            
            return false;

        } finally {

            if (em != null) {
                em.close();
            }
        }
    }

}
