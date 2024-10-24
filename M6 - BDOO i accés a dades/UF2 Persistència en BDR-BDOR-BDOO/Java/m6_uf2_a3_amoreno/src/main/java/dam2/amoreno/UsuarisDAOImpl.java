package dam2.amoreno;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UsuarisDAOImpl implements DAOUsuaris {

	private EntityManagerFactory emf;

	public UsuarisDAOImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Usuaris> LlistarUsuaris() {

		EntityManager em = emf.createEntityManager();
		List<Usuaris> usuaris = new ArrayList<>();

		try {

			TypedQuery<Usuaris> query = em.createQuery("SELECT u FROM Usuaris u", Usuaris.class);
			usuaris = query.getResultList();

		} catch (Exception e) {

			System.out.println("Error en llistar els usuaris!!");

		} finally {
			em.close();
		}

		return usuaris;
	}

	@Override
	public boolean create(Usuaris usuari) {
		EntityManager em = null;
		EntityTransaction transaction = null;

		try {

			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			em.persist(usuari);
			transaction.commit();

			return true;

		} catch (Exception e) {

			System.out.println("Error en afegir un usuari. Comprova que totes les dades siguin correctes!!");

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
	public boolean update(int id, Usuaris usuari) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
	
			Usuaris usuariId = em.find(Usuaris.class, id);
			
			if (usuariId == null) {
				System.out.println("Usuari no trobat amb l'ID: " + id);

				return false;
			}
	
			usuariId.setNom(usuari.getNom());
			usuariId.setCognoms(usuari.getCognoms());
			usuariId.setData_naixement(usuari.getData_naixement());
			usuariId.setEmail(usuari.getEmail());
	
			transaction.commit();

			return true;
	
		} catch (Exception e) {
			System.out.println();

			System.out.println("Error en editar l'usuari.");
			
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

			Usuaris usuari = em.find(Usuaris.class, id);

			if (usuari != null) {
				em.remove(usuari);
				
				transaction.commit();
				
				return true;

			} else {

				System.out.println("Usuari no trobat!!");
				
				return false;
			}

		} catch (Exception e) {
			
			if (transaction != null && transaction.isActive()) transaction.rollback();

			System.out.println("Error en eliminar l'usuari.");
			
			return false;

		} finally {
			em.close();
		}
	}
}
