package aed.centralreservas.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import aed.centralreservas.App;
import aed.centralreservas.entities.Estancia;
import aed.centralreservas.entities.Habitacion;
import aed.centralreservas.entities.Hotel;
import aed.centralreservas.model.EstanciaModel;
import aed.centralreservas.model.TipoModificacion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ModificarController implements Initializable {
	
	// model
	
	private TipoModificacion tipo;
	private Stage window;
	
	// view
	
    @FXML
    private Label idLabel;

    @FXML
    private Label mensajeLabel;

    @FXML
    private TextField idText;
    @FXML
    private TextField nombreText;
    @FXML
    private DatePicker fechaiDate;
    @FXML
    private DatePicker fechafDate;
    @FXML
    private ComboBox<String> codHotelCombo;
    @FXML
    private ComboBox<String> numHabitacionCombo;
    
    @FXML
    private Button aceptarButton;
    @FXML
    private Button cancelarButton;

    @FXML
    private GridPane view;

	public ModificarController(TipoModificacion tipo, Stage window) {
		
		this.tipo = tipo;
		this.window = window;
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModificarView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// load data
		
		for(Hotel h : App.dbmanager.getAllHoteles())
			codHotelCombo.getItems().add(h.getCodHotel());
		
		for(Habitacion h : App.dbmanager.getAllHabitaciones())
			numHabitacionCombo.getItems().add(h.getId().getNumHabitacion());
		
		switch(tipo) {
			case Insertar:
				idLabel.setVisible(false);
				idText.setVisible(false);
				mensajeLabel.setText("¿Desea insertar esta estancia?");
				break;
			case Eliminar:
				idText.setDisable(true);
				nombreText.setDisable(true);
				fechaiDate.setDisable(true);
				fechafDate.setDisable(true);
				numHabitacionCombo.setDisable(true);
				codHotelCombo.setDisable(true);
				mensajeLabel.setText("¿Desea eliminar esta estancia?");
				break;
			case Modificar:
				idText.setDisable(true);
				mensajeLabel.setText("¿Desea modificar esta estancia?");
				break;
		}
		
	}
	
	public ModificarController setEstancia(EstanciaModel estancia) {
		idText.setText(estancia.getId() + "");
		nombreText.setText(estancia.getNombre());
		fechaiDate.setValue(convertToLocalDate(estancia.getFechaInicio()));
		fechafDate.setValue(convertToLocalDate(estancia.getFechaFin()));
		numHabitacionCombo.getSelectionModel().select(estancia.getNumHabitacion());
		codHotelCombo.getSelectionModel().select(estancia.getCodHotel());
		return this;
	}
	
	@FXML
	void onCodHotelChanged(ActionEvent event) {
		
		numHabitacionCombo.getItems().clear();
		for(Habitacion h : App.dbmanager.getHabitaciones(codHotelCombo.getSelectionModel().getSelectedItem()))
			numHabitacionCombo.getItems().add(h.getId().getNumHabitacion());
	}

	@FXML
    void onAceptarAction(ActionEvent event) {

		switch(tipo) {
			case Insertar:
				App.dbmanager.insertarEstancia(new Estancia(
					nombreText.getText(), 
					convertToDate(fechaiDate.getValue()), 
					convertToDate(fechafDate.getValue()), 
					numHabitacionCombo.getValue(), 
					codHotelCombo.getValue()
				));
				break;
			case Eliminar:
				App.dbmanager.eliminarEstancia(new Estancia(
					Integer.parseInt(idText.getText()),
					nombreText.getText(),
					convertToDate(fechaiDate.getValue()), 
					convertToDate(fechafDate.getValue()), 
					numHabitacionCombo.getValue(), 
					codHotelCombo.getValue()
				));
				break;
			case Modificar:
				App.dbmanager.modificarEstancia(new Estancia(
					Integer.parseInt(idText.getText()),
					nombreText.getText(),
					convertToDate(fechaiDate.getValue()), 
					convertToDate(fechafDate.getValue()), 
					numHabitacionCombo.getValue(), 
					codHotelCombo.getValue()
				));
				break;
		}
		
		window.close();
		
    }

    @FXML
    void onCancelarAction(ActionEvent event) {

    }
    
    public LocalDate convertToLocalDate(Date dateToConvert) {
    	return Instant.ofEpochMilli(dateToConvert.getTime())
    			.atZone(ZoneId.systemDefault())
    			.toLocalDate();
    }
    
    public Date convertToDate(LocalDate dateToConvert) {
    	return java.sql.Date.valueOf(dateToConvert);
    }
    
    public GridPane getView() {
		return view;
	}
}
