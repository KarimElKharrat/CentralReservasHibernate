package aed.centralreservas.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HabitacionModel {
	
	private IntegerProperty numHabitacion = new SimpleIntegerProperty();
	private StringProperty codHotel = new SimpleStringProperty();
	private IntegerProperty preciodia = new SimpleIntegerProperty();
	
	public HabitacionModel(int numHabitacion, String codHotel, int preciodia) {
		
		setNumHabitacion(numHabitacion);
		setCodHotel(codHotel);
		setPreciodia(preciodia);
		
	}
	
	public final IntegerProperty numHabitacionProperty() {
		return this.numHabitacion;
	}
	
	public final int getNumHabitacion() {
		return this.numHabitacionProperty().get();
	}
	
	public final void setNumHabitacion(final int numHabitacion) {
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
	
	public final IntegerProperty preciodiaProperty() {
		return this.preciodia;
	}
	
	public final int getPreciodia() {
		return this.preciodiaProperty().get();
	}
	
	public final void setPreciodia(final int preciodia) {
		this.preciodiaProperty().set(preciodia);
	}

}
