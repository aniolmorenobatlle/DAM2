package dam2.amoreno;

import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class CategoriesDAOImpl implements DAOCategories {

	private EntityManagerFactory emf;

	public CategoriesDAOImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Categories> LlistarCategories() {

		EntityManager em = emf.createEntityManager();
		List<Categories> Categoria = new ArrayList<>();

		try {

			TypedQuery<Categories> query = em.createQuery("SELECT c FROM Categories c", Categories.class);
			Categoria = query.getResultList();

		} catch (Exception e) {

			System.out.println("Error en llistar les categories!!");

		} finally {
			em.close();
		}

		return Categoria;
	}

	@Override
	public boolean create(Categories categoria) {
		EntityManager em = null;
		EntityTransaction transaction = null;

		try {

			em = emf.createEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			em.persist(categoria);
			transaction.commit();

			return true;

		} catch (Exception e) {

			System.out.println("Error en afegir una categoria. Comprova que totes les dades siguin correctes!!");

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
	public boolean update(int id, Categories categoria) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaction = null;

		try {
			transaction = em.getTransaction();
			transaction.begin();
	
			Categories categoriaId = em.find(Categories.class, id);
			
			if (categoriaId == null) {
				System.out.println("Usuari no trobat amb l'ID: " + id);

				return false;
			}

			categoria.setNom_categoria(categoria.getNom_categoria());
	
			transaction.commit();

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

			Categories categoria = em.find(Categories.class, id);

			if (categoria != null) {
				em.remove(categoria);
				
				transaction.commit();
				
				return true;

			} else {

				System.out.println("Categoria no trobada!!");
				
				return false;
			}

		} catch (Exception e) {
			
			if (transaction != null && transaction.isActive()) transaction.rollback();

			System.out.println("Error en eliminar la categoria.");
			
			return false;

		} finally {
			em.close();
		}
	}
}
