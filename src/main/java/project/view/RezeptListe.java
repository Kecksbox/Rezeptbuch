package project.view;

import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXListView;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import project.model.Rezept;
import project.controller.RezeptBuchController;
import simple_mvc.view.View;

public class RezeptListe extends View {

	private static RezeptListe instance;

	public static RezeptListe getInstance() {
		if (RezeptListe.instance == null) {
			RezeptListe.instance = new RezeptListe();
		}
		return RezeptListe.instance;
	}

	protected  Map<String, Object> getInitalState() {
		Map<String, Object> state = new HashMap<>();
		state.put("rezepte", RezeptBuchController.getRezepte());
		return state;
	}

	protected void render() {
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20, 20, 20, 20));
		this.setRoot(layout);

		// list
		JFXListView<String> list = new JFXListView<>();
		for (Rezept rezept : (ObservableList<Rezept>) this.state.get("rezepte")) {
			list.getItems().add(rezept.getName());
		}
		list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// addButton
		Button addButton = new Button("new");
		addButton.setOnAction(RezeptBuchController.newRezeptAction);

		// editButton
		Button editButton = new Button("edit");
		editButton.setOnAction(e -> {
			int selectedIndex = list.getSelectionModel().getSelectedIndex();
			if (selectedIndex != -1) {
				RezeptBuchController.editRezeptAction(selectedIndex);
			}
		});

		// deleteButton
		Button deleteButton = new Button("delete");
		deleteButton.setOnAction(e -> {
			int selectedIndex = list.getSelectionModel().getSelectedIndex();
			if (selectedIndex != -1) {
				RezeptBuchController.deleteRezeptAction(selectedIndex);
			}
		});

		layout.getChildren().addAll(list, addButton, editButton, deleteButton);
	}

}
