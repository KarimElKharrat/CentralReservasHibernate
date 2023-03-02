package aed.centralreservas.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the estancias database table.
 * 
 */
@Entity
@Table(name="estancias")
@NamedQuery(name="Estancia.findAll", query="SELECT e FROM Estancia e")
public class Estancia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	private Date fechaInicio;

	private String nombre;

	//bi-directional many-to-one association to Habitacione
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="codHotel", referencedColumnName="codHotel"),
		@JoinColumn(name="numHabitacion", referencedColumnName="numHabitacion")
	})
	private Habitacion habitacion;

	public Estancia() {}
	
	public Estancia(String nombre, Date fechaInicio, Date fechaFin, String numHabitacion, String codHotel) {
		
		setNombre(nombre);
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		setHabitacion(new Habitacion(codHotel, numHabitacion));
		
	}
	
	public Estancia(int id, String nombre, Date fechaInicio, Date fechaFin, String numHabitacion, String codHotel) {
		this(nombre, fechaInicio, fechaFin, numHabitacion, codHotel);
		setId(id);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Habitacion getHabitacion() {
		return this.habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	
	@Override
	public String toString() {
		return String.format(
			"Nombre: %s.\nFecha: %s - %s.\nNumero de habitacion: %s.\nCodigo del hotel: %s.\n", 
			getNombre(), 
			getFechaInicio(),
			getFechaFin(),
			getHabitacion().getId().getNumHabitacion(),
			getHabitacion().getId().getCodHotel()
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
		Estancia otro = (Estancia) obj;
		if (id != otro.getId())
			return false;
		return true;
	}

}