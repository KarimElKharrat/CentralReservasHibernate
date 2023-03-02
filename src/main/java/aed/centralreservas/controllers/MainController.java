package aed.centralreservas.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aed.centralreservas.App;
import aed.centralreservas.entities.Estancia;
import aed.centralreservas.entities.Habitacion;
import aed.centralreservas.entities.Hotel;
import aed.centralreservas.model.EstanciaModel;
import aed.centralreservas.model.TipoModificacion;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	// model
	
	private BooleanProperty isSelected = new SimpleBooleanProperty(true);
	private ObjectProperty<EstanciaModel> estancia = new SimpleObjectProperty<>();
	private ObservableList<EstanciaModel> estancias = FXCollections.observableArrayList();
	
	// view
	
	@FXML
    private TableView<EstanciaModel> estanciasTable;

    @FXML
    private TableColumn<EstanciaModel, Number> idColumn;
    @FXML
    private TableColumn<EstanciaModel, String> nombreColumn;
    @FXML
    private TableColumn<EstanciaModel, String> fechaInicioColumn;
    @FXML
    private TableColumn<EstanciaModel, String> fechaFinColumn;
    @FXML
    private TableColumn<EstanciaModel, String> numHabitacionColumn;
    @FXML
    private TableColumn<EstanciaModel, String> codHotelColumn;
    
    @FXML
    private ListView<String> habitacionesList;
    
    @FXML
    private ComboBox<String> hotelesCombo;
    
    @FXML
    private Button insertarButton;
    @FXML
    private Button eliminarButton;
    @FXML
    private Button modificarButton;
    
    @FXML
    private GridPane view;

	public MainController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// bindings
		
		eliminarButton.disableProperty().bind(isSelected);
		modificarButton.disableProperty().bind(isSelected);
		estancia.bind(estanciasTable.getSelectionModel().selectedItemProperty());
		isSelected.bind(estancia.isNull());
		
		idColumn.setCellValueFactory(date -> date.getValue().idProperty());
		nombreColumn.setCellValueFactory(date -> date.getValue().nombreProperty());
		fechaInicioColumn.setCellValueFactory(date -> date.getValue().fechaInicioProperty().asString());
		fechaFinColumn.setCellValueFactory(date -> date.getValue().fechaFinProperty().asString());
		numHabitacionColumn.setCellValueFactory(date -> date.getValue().numHabitacionProperty());
		codHotelColumn.setCellValueFactory(date -> date.getValue().codHotelProperty());
		
		estanciasTable.setItems(estancias);
		
		// load data
		
		for(Hotel h : App.dbmanager.getAllHoteles())
			hotelesCombo.getItems().add(h.getCodHotel());
		
		recargarDatos();
		
	}
	
	@FXML
    void onChangeAction(ActionEvent event) {
		
		recargarDatos();

    }
	
	private void recargarDatos() {
		
		estancias.clear();
		habitacionesList.getItems().clear();
		if(hotelesCombo.getSelectionModel().getSelectedItem() != null) {
			
			for(Estancia e : App.dbmanager.getEstancias(hotelesCombo.getSelectionModel().getSelectedItem())) {
				EstanciaModel estancia = new EstanciaModel(e.getId(), e.getNombre(), e.getFechaFin(), e.getFechaFin(), e.getHabitacion().getId().getNumHabitacion(), e.getHabitacion().getId().getCodHotel());
				estancias.add(estancia);
			}
			
			for(Habitacion h : App.dbmanager.getHabitaciones(hotelesCombo.getSelectionModel().getSelectedItem()))
				habitacionesList.getItems().add(h.getId().getNumHabitacion());
			
		} else {
			
			for(Estancia e : App.dbmanager.getAllEstancias()) {
				EstanciaModel estancia = new EstanciaModel(e.getId(), e.getNombre(), e.getFechaFin(), e.getFechaFin(), e.getHabitacion().getId().getNumHabitacion(), e.getHabitacion().getId().getCodHotel());
				estancias.add(estancia);
			}
			
			for(Habitacion h : App.dbmanager.getAllHabitaciones())
				habitacionesList.getItems().add(h.getId().getNumHabitacion());
			
		}
		
	}

	@FXML
	void onInsertarAction(ActionEvent event) {
		createModificarWindow(TipoModificacion.Insertar);
	}

    @FXML
    void onEliminarAction(ActionEvent event) {
    	createModificarWindow(TipoModificacion.Eliminar);
    }

    @FXML
    void onModificarAction(ActionEvent event) {
    	createModificarWindow(TipoModificacion.Modificar);
    }
    
    private void createModificarWindow(TipoModificacion tipo) {
    	Stage window = new Stage();
    	window.setTitle(tipo.toString());
    	window.initOwner(App.primaryStage);
    	if(tipo.equals(TipoModificacion.Insertar))
    		window.setScene(new Scene(new ModificarController(tipo, window).getView()));
    	else
    		window.setScene(new Scene(new ModificarController(tipo, window).setEstancia(estanciasTable.getSelectionModel().getSelectedItem()).getView()));
    	window.showAndWait();
    	
    	recargarDatos();
    }
    
    public GridPane getView() {
		return view;
	}

}
