package view;

import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXButton;

import controller.RezeptBuchController;
import entwurf.View;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Rezept;

public class RezeptFormular extends View {

	private static RezeptFormular instance = new RezeptFormular();

	public static RezeptFormular getInstance() {
		return RezeptFormular.instance;
	}

	protected  Map<String, Object> getInitalState() {
		Map<String, Object> state = new HashMap<>();
		state.put("selected", new Rezept());
		return state;
	}

	protected void render() {
		VBox layout = new VBox(10);
		this.setRoot(layout);

		layout.setPadding(new Insets(20, 20, 20, 20));

		Rezept selected = (Rezept) this.state.get("selected");
		
		// Form
		TextField nameField = new TextField();
		if (selected != null) {
			nameField.setText(selected.getName());
		}
		Button btn = new Button("Bestätigen");
		btn.setOnAction(e -> {
			String name = nameField.getText();
			RezeptBuchController.submitRezeptFormular(selected, name);
		});

		layout.getChildren().addAll(nameField, btn);
	}


	/*
	private static void createInstance() {
		VBox layout = new VBox();

		RezeptFormular.addLable(layout);
		RezeptFormular.addNameField(layout);
		RezeptFormular.addZutatenListe(layout);

		RezeptFormular.instance = new RezeptFormular(layout);
	}

	private static void addLable(VBox layout) {
		Label label = new Label("Rezept");
		layout.getChildren().addAll(label);
	}

	private static void addNameField(VBox layout) {
		JFXTextField nameField = new JFXTextField();
		nameField.setLabelFloat(true);
		nameField.setPromptText("Floating prompt");

		nameField.getStyleClass().add("nameField");

		JFXTextField validationField = new JFXTextField();
		validationField.setPromptText("With Validation..");
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");
		validationField.getValidators().add(validator);
		validationField.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				validationField.validate();
		});

		layout.getChildren().addAll(nameField);
	}

	private static void addZutatenListe(VBox layout) {
		
	}
	*/

}
