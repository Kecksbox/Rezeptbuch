package view;

import com.jfoenix.controls.JFXButton;

import controller.TestController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class TestView extends Scene {

	private static TestView instance;

	private TestView (Parent parent) {
		super(parent, 200, 200);
	}

	public static TestView getInstance() {
		if (TestView.instance == null) {
			createInstance();
		}
		return TestView.instance;
	}

	private static void createInstance() {
		JFXButton btn = new JFXButton();
		btn.setText("Say 'Hello World'");
		btn.setOnAction(TestController.handleButton1Click);
		StackPane layout = new StackPane();
		layout.getChildren().add(btn);
		TestView.instance = new TestView(layout);
	}

}
