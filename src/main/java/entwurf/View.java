package entwurf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import main.App;

public abstract class View extends Scene {

    protected Map<String, Object> state;

    /**
     * Class constructor.
     * <p>
     * Initialises the view with a StackPane Layout, 
     * assigns the inital State by calling getInitalState and renders the view for the first time.
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

    protected abstract void render();

    protected abstract Map<String, Object> getInitalState();

    private void initStyles() {
        // public stylesheet

        // does not work :( (still here for later fix)
        this.getStylesheets().add("./src/main/resource/css/globalStyles.css");
    }

}