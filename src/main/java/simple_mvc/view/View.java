package simple_mvc.view;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

import project.App;

/**
 * @author Malte Heuser
 */
public abstract class View extends Scene {

    /**
     * The state of this view.
     * 
     * A keyValueMap, that maps Strings to Objects.
     * It can be used during rendering to display dynamic content.
     * To alter the state use setState.
     */
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
     * SetState is not permitted to add new keys.
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
        this.loadStyleSheetAt("src/main/resources/css/globalStyles.css");
        // local stylesheet
        this.loadStyleSheetAt("src/main/resources/css/views/" + this.getClass().getSimpleName() + ".css");
    }

    /**
     * Adds the StyleSheet at the spezified rootPath and adds it if not existent.
     */
    private void loadStyleSheetAt(String rootPath) {
        File f = new File(rootPath);
        if(f.exists() == false || f.isDirectory()) { 
            f.getParentFile().mkdirs(); 
            try {
                f.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
                throw new Error("IOException occured while trying to create StyleSheet.");
            }
        }
        this.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
    }

}