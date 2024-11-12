package dam2.amoreno;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class MatriculaDAOImpl implements MatriculaDAO {

    private EntityManagerFactory emf;
    public MatriculaDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public List<Matricula> llistarMatricula() {

        EntityManager em = emf.createEntityManager();

        List<Matricula> matricules = new ArrayList<>();

        try {
            
            TypedQuery<Matricula> query = em.createQuery("SELECT m FROM Matricula m", Matricula.class);

            matricules = query.getResultList();


        } catch (Exception e) {

            System.out.println("Error en llistar les matricules!!");

            e.printStackTrace();
        
        } finally {
            em.close();
        }


        return matricules;
    }

    public boolean create(Matricula matricula) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
        
            trans = em.getTransaction();
            trans.begin();

            em.persist(matricula);

            trans.commit();

            System.err.println();
            System.out.println("Matricula afegit correctament!!");

            return true;

        } catch (Exception e) {
            
            System.out.println("Error en afegir l'matricula. Comprova que totes les dades siguin correctes.");
            
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


    public boolean update(Matricula matricula, Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction trans = null;

        try {
            
            trans = em.getTransaction();
            trans.begin();

            Matricula matricules = em.find(Matricula.class, id);

            matricules.setDataMatricula(matricula.getDataMatricula());
            matricules.setEstudiant(matricula.getEstudiant());
            matricules.setAssignatures(matricula.getAssignatures());

            em.merge(matricules);

            trans.commit();

            System.out.println();
            System.out.println("Matricula editat correctament!!");

            return true;

        } catch (Exception e) {

            System.out.println("Error en editar l'matricula. Comprova que totes les dades siguin correctes.");


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

            Matricula matricula = em.find(Matricula.class, id);

            if (matricula != null) {
                em.remove(matricula);
                trans.commit();

                System.out.println();
                System.out.println("Matricula eliminada correctament!!");

                return true;
            } else {
                System.out.println("Matricula no trobat.");
                return false;
            }

        } catch (Exception e) {

            System.out.println("Error en eliminar l'matricula. Comprova que totes les dades siguin correctes.");


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
