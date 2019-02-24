package entwurf;

import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import main.App;

public abstract class View extends Scene {

    protected Map<String, Object> state;

    protected View() {
        super(new StackPane(), App.width, App.height);
        this.state = getInitalState();
        render();
    }

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

}