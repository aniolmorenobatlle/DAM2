package dam2.amoreno;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class AutorsDAOImpl implements DAOAutors {

	private EntityManagerFactory emf;

	public AutorsDAOImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Autors> LlistarAutors() {

		EntityManager em = emf.createEntityManager();
		List<Autors> autors = new ArrayList<>();

		try {

			TypedQuery<Autors> query = em.createQuery("SELECT a FROM Autors a", Autors.class);
			autors = query.getResultList();

		} catch (Exception e) {

			System.out.println("Error en llistar els llibres!!");

		} finally {
			em.close();
		}

		return autors;
	}

	@Override
	public boolean create(Autors autor) {
		EntityManager em = null;
		EntityTransaction transaction = null;

		try {

			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			em.persist(autor);
			transaction.commit();

			return true;

		} catch (Exception e) {

			System.out.println("Error en afegir un autor. Comprova que totes les dades siguin correctes!!");

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
	public boolean update(int id, Autors autor) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
	
			Autors autorId = em.find(Autors.class, id);
			
			if (autorId == null) {
				System.out.println("Usuari no trobat amb l'ID: " + id);

				return false;
			}

			autor.setNom(autor.getNom());
			autor.setCognoms(autor.getCognoms());
			autor.setData_naixement(autor.getData_naixement());
	
			transaction.commit();

			return true;
	
		} catch (Exception e) {
			System.out.println();

			System.out.println("Error en editar un autor.");
			
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

			Autors autor = em.find(Autors.class, id);

			if (autor != null) {
				em.remove(autor);
				
				transaction.commit();
				
				return true;

			} else {

				System.out.println("Autor no trobat!!");
				
				return false;
			}

		} catch (Exception e) {
			
			if (transaction != null && transaction.isActive()) transaction.rollback();

			System.out.println("Error en eliminar l'autor.");
			
			return false;

		} finally {
			em.close();
		}
	}
}
