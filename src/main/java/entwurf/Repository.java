package entwurf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class Repository<T extends Model> {

    protected String entityName;

    private LinkedList<T> data = new LinkedList<T>();;

    protected Repository(String entityName) {
        this.entityName = entityName;
        initRespository();
    }

    public void add(T instance) {
        System.out.println(data);
        this.data.add(instance);
    }

    public void remove(T instance) {
        this.data.remove(instance);
    }

    public void persist() {
        File storage = new File("./storage");
        if (storage.exists() == false || storage.isDirectory() == false) {
            storage.mkdirs();
        }
        try (PrintWriter out = new PrintWriter(this.getPath())) {
            out.println(dataToJson());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void initRespository() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(this.getPath()));
            String json = new String(encoded, StandardCharsets.UTF_8);
            reCreateData(json);
        } catch (NoSuchFileException e) {
            // start with empty List
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPath() {
        return "./storage/" + entityName + ".json";
    }

    private String dataToJson() {
        if (this.data.isEmpty() == true) {
            return "{}";
        }
        String result = "{";
        result += '"' + "className" + '"' + ':' + this.data.getLast().getClass().getName() + ',';

        result += '"' + "data" + '"' + ':' + "[";
        for (int i = 0, len = this.data.size(); i < len; ++i) {
            result += this.data.get(i).toJson();
            if (i < len - 1) {
                result += ", ";
            }
        }
        result += "]}";

        JsonElement el = new JsonParser().parse(result);
        return new GsonBuilder().setPrettyPrinting().create().toJson(el);
    }

    private void reCreateData(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        System.out.println(jsonObject);

        try {
            String className = jsonObject.get("className").toString();
            Class c = Class.forName(className.substring(1, className.length() - 1));

            for (JsonElement dataElement : jsonObject.get("data").getAsJsonArray()) {
                Object test = gson.fromJson(dataElement.toString(), c);
                if (test instanceof Model) {
                    this.add(((T) test));
                } else {
                    throw new RuntimeException("wdaw");
                }
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("wadawd");
        }
    }

}