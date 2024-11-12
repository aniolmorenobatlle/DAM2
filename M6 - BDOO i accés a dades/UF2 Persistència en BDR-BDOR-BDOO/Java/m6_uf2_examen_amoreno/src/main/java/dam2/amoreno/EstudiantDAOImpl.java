package dam2.amoreno;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class EstudiantDAOImpl implements EstudiantDAO {

    private EntityManagerFactory emf;
    public EstudiantDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Estudiant> LlistarEstudiant() {

        EntityManager em = emf.createEntityManager();

        List<Estudiant> estudiants = new ArrayList<>();

        try {
            
            TypedQuery<Estudiant> query = em.createQuery("SELECT e FROM Estudiant e", Estudiant.class);

            estudiants = query.getResultList();


        } catch (Exception e) {

            System.out.println("Error en llistar els estudiants!!");
        
        } finally {
            em.close();
        }


        return estudiants;
    }


    public boolean create(Estudiant estudiant) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
        
            trans = em.getTransaction();
            trans.begin();

            em.persist(estudiant);

            trans.commit();

            System.out.println("Estudiant afegit correctament!!");

            return true;

        } catch (Exception e) {
            
            System.out.println("Error en afegir l'estudiant. Comprova que totes les dades siguin correctes.");
            
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


    public boolean update(Estudiant Estudiant, Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
            
            trans = em.getTransaction();
            trans.begin();

            Estudiant estudiant = em.find(Estudiant.class, id);

            estudiant.setNom(Estudiant.getNom());
            estudiant.setCognoms(Estudiant.getCognoms());
            estudiant.setDataNaixement(Estudiant.getDataNaixement());
            estudiant.setEmail(Estudiant.getEmail());

            em.merge(estudiant);

            trans.commit();

            System.out.println("Estudiant editat correctament!!");

            return true;

        } catch (Exception e) {

            System.out.println("Error en editar l'estudiant. Comprova que totes les dades siguin correctes.");


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

            Estudiant estudiant = em.find(Estudiant.class, id);

            if (estudiant != null) {
                em.remove(estudiant);
                trans.commit();

                System.out.println("Estudinat eliminat correctament!!");

                return true;
            } else {
                System.out.println("Estudiant no trobat.");
                return false;
            }

        } catch (Exception e) {

            System.out.println("Error en eliminar l'estudiant. Comprova que totes les dades siguin correctes.");


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
