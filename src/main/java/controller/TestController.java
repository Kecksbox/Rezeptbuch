package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import main.App;
import view.TestView2;

public abstract class TestController {
	
	public static EventHandler<ActionEvent> handleButton1Click = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			System.out.print("button clicked");
			App.redirectView(TestView2.getInstance());
		}
	};
	
}
