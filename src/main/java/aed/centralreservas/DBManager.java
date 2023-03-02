package aed.centralreservas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import aed.centralreservas.entities.Estancia;
import aed.centralreservas.entities.Habitacion;
import aed.centralreservas.entities.Hotel;

public class DBManager {
	
	EntityManagerFactory emf;
	EntityManager em;
	
	public DBManager() {
		emf = Persistence.createEntityManagerFactory("central_reservas");
		em = emf.createEntityManager();
	}
	
	public List<Hotel> getAllHoteles() {
		return em.createQuery("select h from Hotel h", Hotel.class).getResultList();
	}
	
	public List<Habitacion> getAllHabitaciones() {
		return em.createQuery("select h from Habitacion h", Habitacion.class).getResultList();
	}
	
	public List<Estancia> getAllEstancias() {
		return em.createQuery("select e from Estancia e", Estancia.class).getResultList();
	}
	
	public List<Estancia> getEstancias(String codHotel) {
		TypedQuery<Estancia> query = em.createQuery("select e from Estancia e where e.habitacion.id.codHotel = :codHotel", Estancia.class);
		return query.setParameter("codHotel", codHotel).getResultList();
	}
	
	public List<Habitacion> getHabitaciones(String codHotel) {
		TypedQuery<Habitacion> query = em.createQuery("select h from Habitacion h where h.id.codHotel = :codHotel", Habitacion.class);
		return query.setParameter("codHotel", codHotel).getResultList();
	}
	
	public void insertarEstancia(Estancia estancia) {
		em.getTransaction().begin();
		em.persist(estancia);
		em.getTransaction().commit();
	}
	
	public void eliminarEstancia(Estancia estancia) {
		em.getTransaction().begin();
		em.remove(em.contains(estancia) ? estancia : em.merge(estancia));
		em.getTransaction().commit();
	}
	
	public void close() {
		em.close();
		emf.close();
	}

	public void modificarEstancia(Estancia estancia) {
		em.getTransaction().begin();
		TypedQuery<Estancia> query = em.createQuery("select e from Estancia e where e.id = :id", Estancia.class);
		Estancia estanciaActual = query.setParameter("id", estancia.getId()).getSingleResult();
		estanciaActual.setNombre(estancia.getNombre());
		estanciaActual.setFechaInicio(estancia.getFechaInicio());
		estanciaActual.setFechaFin(estancia.getFechaFin());
		estanciaActual.setHabitacion(estancia.getHabitacion());
		em.getTransaction().commit();
	}

}
