package main;
import com.google.gson.Gson;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TestModel;
import repository.TestRepository;
import view.TestView;

public class App extends Application {
	
	private static App instance = new App();
	
	public static App getInstance () {
	    return App.instance;
	}
	
	private Stage window;
	
	private TestView view = TestView.getInstance();

	@Override
	public void start(Stage window) throws Exception {
		instance.window = window;
		instance.window.setTitle("Hello World!");
		instance.window.setScene(view);
		instance.window.show();
	}
	
	public static void redirectView(Scene view) {
		instance.window.setScene(view);
	}

	public static void main(String[] args) {
		new TestModel("Pan", "Peter");
		TestRepository.getInstance().persist();
		launch(args);
	}
}

