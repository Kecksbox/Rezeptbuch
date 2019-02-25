package main;

import entwurf.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.RezeptListe;

public class App extends Application {

	public static int width = 400;

	public static int height = 400;
	
	private static App instance = new App();
	
	public static App getInstance () {
	    return App.instance;
	}
	
	private Stage window;
	
	private View view = RezeptListe.getInstance();

	@Override
	public void start(Stage window) throws Exception {
		instance.window = window;
		instance.window.setTitle("Rezepto");
		instance.window.setScene(view);
		instance.window.show();
	}
	
	public static void redirectView(Scene view) {
		instance.window.setScene(view);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
