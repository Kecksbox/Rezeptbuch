package view;

import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;

import controller.RezeptBuchController;
import entwurf.View;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Rezept;

public class RezeptFormular extends View {

	private static RezeptFormular instance;

	public static RezeptFormular getInstance() {
		if (RezeptFormular.instance == null) {
			RezeptFormular.instance = new RezeptFormular();
		}
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
		Label nameLabel = new Label("Name");
		TextField nameField = new TextField(selected.getName());
		Label zutatenLabel = new Label("Zutaten");
		TextArea zutatenTextArea = new TextArea(selected.getZutaten());
		Label arbeitsschritteLabel = new Label("Arbeitsschritte");
		TextArea arbeitsschritteTextArea = new TextArea(selected.getArbeitsschritte());

		Button btn = new Button("Bestätigen");
		btn.setOnAction(e -> {
			String name = nameField.getText();
			String zuataten = zutatenTextArea.getText();
			String arbeitsschritte = arbeitsschritteTextArea.getText();

			// basic validation
			if (name.length() == 0) {
				return;
			}

			RezeptBuchController.submitRezeptFormular(selected, name, zuataten, arbeitsschritte);
		});

		layout.getChildren().addAll(nameLabel, nameField, zutatenLabel, zutatenTextArea, arbeitsschritteLabel, arbeitsschritteTextArea, btn);
	}

}
