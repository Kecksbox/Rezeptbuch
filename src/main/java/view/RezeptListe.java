package view;

import java.awt.Label;
import java.util.HashMap;
import java.util.Map;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import controller.RezeptBuchController;
import entwurf.View;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.VBox;
import model.Rezept;

public class RezeptListe extends View {

	private static RezeptListe instance = new RezeptListe();

	public static RezeptListe getInstance() {
		return RezeptListe.instance;
	}

	protected  Map<String, Object> getInitalState() {
		Map<String, Object> state = new HashMap<>();
		state.put("rezepte", RezeptBuchController.getRezepte());
		return state;
	}

	protected void render() {
		VBox layout = new VBox(10);
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
			RezeptBuchController.editRezeptAction(selectedIndex);
		});

		// deleteButton
		Button deleteButton = new Button("delete");
		deleteButton.setOnAction(e -> {
			int selectedIndex = list.getSelectionModel().getSelectedIndex();
			RezeptBuchController.deleteRezeptAction(selectedIndex);
		});

		layout.getChildren().addAll(list, addButton, editButton, deleteButton);
	}

}
