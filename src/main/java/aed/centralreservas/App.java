package aed.centralreservas;

import aed.centralreservas.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	public static Stage primaryStage;
	private MainController controller = new MainController();
	public static DBManager dbmanager = new DBManager();

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		App.primaryStage = primaryStage;
		
		primaryStage.setTitle("Central Reservas");
		primaryStage.setScene(new Scene(controller.getView()));
		primaryStage.show();
		
		primaryStage.widthProperty().addListener((o,ov,nv) -> {
			System.out.println(nv);
		});
		
	}
	
	@Override
	public void stop() throws Exception {
		super.stop();
		dbmanager.close();
	}
	
}
