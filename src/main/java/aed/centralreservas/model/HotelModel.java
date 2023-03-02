package aed.centralreservas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HotelModel {
	
	private StringProperty codHotel = new SimpleStringProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty localidad = new SimpleStringProperty();
	
	public HotelModel(String codHotel, String nombre, String localidad) {
		
		setCodHotel(codHotel);
		setNombre(nombre);
		setLocalidad(localidad);
		
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
	
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	public final StringProperty localidadProperty() {
		return this.localidad;
	}
	
	public final String getLocalidad() {
		return this.localidadProperty().get();
	}
	
	public final void setLocalidad(final String localidad) {
		this.localidadProperty().set(localidad);
	}
	
}
