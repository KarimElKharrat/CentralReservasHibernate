package aed.centralreservas.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the hotel database table.
 * 
 */
@Entity
@NamedQuery(name="Hotel.findAll", query="SELECT h FROM Hotel h")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String codHotel;

	private String localidad;

	private String nombre;

	@OneToMany(mappedBy="hotel")
	private List<Habitacion> habitaciones;

	public Hotel() {
	}

	public String getCodHotel() {
		return this.codHotel;
	}

	public void setCodHotel(String codHotel) {
		this.codHotel = codHotel;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Habitacion> getHabitaciones() {
		return this.habitaciones;
	}

	public void setHabitaciones(List<Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Habitacion addHabitacione(Habitacion habitacione) {
		getHabitaciones().add(habitacione);
		habitacione.setHotel(this);

		return habitacione;
	}

	public Habitacion removeHabitacione(Habitacion habitacione) {
		getHabitaciones().remove(habitacione);
		habitacione.setHotel(null);

		return habitacione;
	}
	
	@Override
	public String toString() {
		return String.format(
			"CodHotel: %s.\nNombre: %s.\nLocalidad: %s.\n", 
			getCodHotel(),
			getNombre(),
			getLocalidad()
		);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel otro = (Hotel) obj;
		if (!codHotel.equals(otro.getCodHotel()))
			return false;
		return true;
	}

}