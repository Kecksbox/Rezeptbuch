package simple_mvc.view;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import project.App;

public abstract class View extends Scene {

    protected Map<String, Object> state;

    /**
     * Class constructor.
     * 
     * Initialises the view with a StackPane Layout, 
     * assigns the inital State by calling getInitalState, the stylesheets (global and local) 
     * and renders the view for the first time.
     */
    protected View() {
        super(new StackPane(), App.width, App.height);
        this.state = getInitalState();
        this.initStyles();
        render();
    }

    /**
     * Updates the views state by overwriting the prevStates values where keys match.
     * 
     * @param update A keyValueMap.
     */
    public void setState(Map<String, Object> update) {
        for (String key : update.keySet()) {
            if (state.containsKey(key)) {
                state.put(key, update.get(key));
            } else {
                throw new RuntimeException(key + " does not exist on state.");
            }
        }
        render();
    }

    /**
     * Renders the View.
     * 
     * Start your implementation with:
     * [your prefered layout]layout = new [your prefered layout]();
	 * this.setRoot(layout);
     */
    protected abstract void render();

    /**
     * Returns the inital State.
     * 
     * A state is a KeyValueMap that maps Strings to Objects.
     * 
     * Your implemtation should have the following form:
     * Map<String, Object> state = new HashMap<>();
	 * state.put([key], [value]);
     * ...
	 * return state;
     */
    protected abstract Map<String, Object> getInitalState();

    /**
     * Assigns the views Stylesheet.
     * 
     * All view share the globalStyleSheet.
     * In addition to that every view has its own stylesheet, that is found by the views name.
     * For example:
     * A view called RezeptFormular would have the following styleSheets:
     * globalStyleSheet.css, rezeptFormularStyleSheet.css
     */
    private void initStyles() {
        // public stylesheet

        // does not work :( (still here for later fix)
        this.getStylesheets().add("./src/main/resource/css/globalStyles.css");
    }

}