package view;

import com.jfoenix.controls.JFXButton;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class TestView2 extends Scene {

	private static TestView2 instance;

	private TestView2 (Parent parent) {
		super(parent, 200, 200);
	}

	public static TestView2 getInstance() {
		if (TestView2.instance == null) {
			createInstance();
		}
		return TestView2.instance;
	}

	private static void createInstance() {
		JFXButton btn = new JFXButton();
		btn.setText("You did it!'");
		StackPane layout = new StackPane();
		layout.getChildren().add(btn);
		TestView2.instance = new TestView2(layout);
	}

}
