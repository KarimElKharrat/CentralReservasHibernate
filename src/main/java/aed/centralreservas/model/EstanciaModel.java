package aed.centralreservas.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EstanciaModel {
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private ObjectProperty<Date> fechaInicio = new SimpleObjectProperty<>();
	private ObjectProperty<Date> fechaFin = new SimpleObjectProperty<>();
	private StringProperty numHabitacion = new SimpleStringProperty();
	private StringProperty codHotel = new SimpleStringProperty();
	
	public EstanciaModel(int id, String nombre, Date fechaInicio, Date fechaFin, String numHabitacion, String codHotel) {
		
		setId(id);
		setNombre(nombre);
		setFechaInicio(fechaInicio);
		setFechaFin(fechaFin);
		setNumHabitacion(numHabitacion);
		setCodHotel(codHotel);
		
	}
	
	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	public final ObjectProperty<Date> fechaInicioProperty() {
		return this.fechaInicio;
	}
	
	public final Date getFechaInicio() {
		return this.fechaInicioProperty().get();
	}
	
	public final void setFechaInicio(final Date fechaInicio) {
		this.fechaInicioProperty().set(fechaInicio);
	}
	
	public final ObjectProperty<Date> fechaFinProperty() {
		return this.fechaFin;
	}
	
	public final Date getFechaFin() {
		return this.fechaFinProperty().get();
	}
	
	public final void setFechaFin(final Date fechaFin) {
		this.fechaFinProperty().set(fechaFin);
	}
	
	public final StringProperty numHabitacionProperty() {
		return this.numHabitacion;
	}
	
	public final String getNumHabitacion() {
		return this.numHabitacionProperty().get();
	}
	
	public final void setNumHabitacion(final String numHabitacion) {
		this.numHabitacionProperty().set(numHabitacion);
	}
	
	public final StringProperty codHotelProperty() {
		return this.codHotel;
	}
	
	public final String getCodHotel() {
		return this.codHotelProperty().get();
	}
	
	public final void setCodHotel(final String codHotel) {
		this.codHotelProperty().set(codHotel);
	}

}
