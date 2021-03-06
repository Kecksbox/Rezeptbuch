package project;

import javafx.application.Application;
import javafx.stage.Stage;
import simple_mvc.view.View;
import project.view.RezeptListe;

public class App extends Application {

	public static int width = 400;

	public static int height = 400;

	private static App instance = new App();

	public static App getInstance() {
		return App.instance;
	}

	public static void redirectView(View view) {
		instance.window.setScene(view);
	}

	private Stage window;

	private View view = RezeptListe.getInstance();

	@Override
	public void start(Stage window) throws Exception {
		instance.window = window;
		instance.window.setScene(view);
		instance.window.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
