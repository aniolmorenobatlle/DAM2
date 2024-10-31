package dam2.amoreno;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class LlibresDAOImpl implements DAOLlibres {

	private EntityManagerFactory emf;

	public LlibresDAOImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Llibres> LlistarLlibres() {

		EntityManager em = emf.createEntityManager();
		List<Llibres> Llibres = new ArrayList<>();

		try {

			TypedQuery<Llibres> query = em.createQuery("SELECT l FROM Llibres l", Llibres.class);
			Llibres = query.getResultList();

		} catch (Exception e) {

			System.out.println("Error en llistar els llibres!!");

		} finally {
			em.close();
		}

		return Llibres;
	}

	@Override
	public boolean create(Llibres llibre) {
		EntityManager em = null;
		EntityTransaction transaction = null;

		try {

			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			em.persist(llibre);
			transaction.commit();

			System.out.println("Llibre afegit amb èxit!");

			return true;

		} catch (Exception e) {

			System.out.println("Error en afegir un llibre. Comprova que totes les dades siguin correctes!!");

			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			return false;

		} finally {

			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public boolean update(int id, Llibres llibre) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
	
			Llibres llibreId = em.find(Llibres.class, id);
			
			if (llibreId == null) {
				System.out.println("Usuari no trobat amb l'ID: " + id);

				return false;
			}

			llibreId.setTitol(llibre.getTitol());
			llibreId.setIsbn(llibre.getIsbn());
			llibreId.setAny_publicacio(llibre.getAny_publicacio());
			llibreId.setAutor(llibre.getAutor());
			llibreId.setCategoria(llibre.getCategoria());
	
			em.merge(llibreId);
			transaction.commit();

			System.out.println("Llibre editat amb èxit!!");

			return true;
	
		} catch (Exception e) {
			System.out.println();

			System.out.println("Error en editar el llibre.");
			
			if (transaction != null) transaction.rollback();
			
			return false;
	
		} finally {
			em.close();
		}
	}

	@Override
	public boolean delete(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {

			transaction = em.getTransaction();
			transaction.begin();

			Llibres llibre = em.find(Llibres.class, id);

			if (llibre != null) {
				em.remove(llibre);
				
				transaction.commit();

				System.out.println("Llibre eliminat amb èxit!");
				
				return true;

			} else {

				System.out.println("Llibre no trobat!!");
				
				return false;
			}

		} catch (Exception e) {
			
			if (transaction != null && transaction.isActive()) transaction.rollback();

			System.out.println("Error en eliminar el llibre.");
			
			return false;

		} finally {
			em.close();
		}
	}
}
