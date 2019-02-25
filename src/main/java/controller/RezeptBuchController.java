package controller;

import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.App;
import model.Rezept;
import repository.RezeptRepository;
import view.RezeptFormular;
import view.RezeptListe;

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
			App.redirectView(RezeptFormular.getInstance());
		}

	};

	public static void editRezeptAction(int selectedIndex) {
		Rezept selected = RezeptRepository.getInstance().findById(selectedIndex);
		App.redirectView(RezeptFormular.getInstance());
		// set RezeptFormularState
		RezeptFormular.getInstance().setState(new HashMap<String, Object>() {
			{
				put("selected", selected);
			}
		});
	}

	public static void deleteRezeptAction(int selectedIndex) {
		RezeptRepository.getInstance().deleteById(selectedIndex);
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
		App.redirectView(RezeptListe.getInstance());
	}

}
