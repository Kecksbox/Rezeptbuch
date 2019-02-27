package project.controller;

import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import project.model.Rezept;
import project.repository.RezeptRepository;
import project.view.RezeptListe;
import project.view.RezeptFormular;

import project.App;

public abstract class RezeptBuchController {

	public static ObservableList<Rezept> getRezepte() {
		return RezeptRepository.getInstance().findAll();
	}

	public static EventHandler<ActionEvent> newRezeptAction = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Rezept newRezept = new Rezept();
			newRezept.toRepository();
			RezeptFormular.getInstance().setState(new HashMap<String, Object>() {
				{
					put("selected", newRezept);
				}
			});
			App.getInstance().redirectView(RezeptFormular.getInstance());
		}

	};

	public static void editRezeptAction(int selectedIndex) {
		Rezept selected = RezeptRepository.getInstance().findByIndex(selectedIndex);
		App.getInstance().redirectView(RezeptFormular.getInstance());
		// set RezeptFormularState
		RezeptFormular.getInstance().setState(new HashMap<String, Object>() {
			{
				put("selected", selected);
			}
		});
	}

	public static void deleteRezeptAction(int selectedIndex) {
		RezeptRepository.getInstance().deleteByIndex(selectedIndex);
		RezeptRepository.getInstance().persist();
		RezeptListe.getInstance().setState(new HashMap<String, Object>() {
			{
				put("rezepte", RezeptBuchController.getRezepte());
			}
		});
	}

	public static void submitRezeptFormular(Rezept selected, String name, String zutaten, String arbeitsschritte) {
		selected.setName(name);
		selected.setZutaten(zutaten);
		selected.setArbeitsschritte(arbeitsschritte);
		RezeptRepository.getInstance().persist();

		RezeptListe.getInstance().setState(new HashMap<String, Object>() {
			{
				put("rezepte", RezeptBuchController.getRezepte());
			}
		});
		App.getInstance().redirectView(RezeptListe.getInstance());
	}

}
