package simple_mvc.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.thoughtworks.xstream.XStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import simple_mvc.model.Model;
import simple_mvc.repository.converters.ObservableListConverter;

/**
 * @author Malte Heuser
 */
public abstract class Repository<T extends Model> {

    /**
     * A list of all instances of the corresponding Model,
     * that were added to the Repsoitory.
     */
    private ObservableList<T> data = FXCollections.observableArrayList();

    /**
     * Adds instance to the repository 
     * if instance is not element of the repository.
     */
    public void add(T instance) {
        if (this.data.contains(instance) == false) {
            this.data.add(instance);
        }
    }

    /**
     * Removes instance from the reposiotry
     * if instance is element of the repository.
     */
    public void remove(T instance) {
        if (this.data.contains(instance)) {
            this.data.remove(instance);
        }
    }

    /**
     * Finds all modelInstances added to this repository and returns them.
     * 
     * @return An ObservableList of all Elements of this Repository.
     */
    public ObservableList<T> findAll() {
        return this.data;
    }

    /**
     * Returns the Element at the spezified index in the Repositories ObservableList.
     * Returns null if no Element with the same index was found.
     */
    public T findByIndex(int index) {
        try {
            return this.data.get(index);
        } catch(IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Deletes the Element at the spezified index in the Repository
     * if an element for the index can be found.
     */
    public void deleteByIndex(int index) {
        try {
            this.data.remove(index);
        } catch(IndexOutOfBoundsException e) {
            return;
        }
    }

    /**
     * Saves the Repository to file.
     * 
     * [More information comming soon...]
     */
    public void persist() {
        File storage = new File("./storage");
        if (storage.exists() == false || storage.isDirectory() == false) {
            storage.mkdirs();
        }
        try (PrintWriter out = new PrintWriter(this.getPath())) {
            out.println(this.getXStream().toXML(this.data));
        } catch (FileNotFoundException e) {
            throw new Error("Could not write to file at " + this.getPath());
        }
    }

    /**
     * Class constructor.
     * 
     * Initalises a new Repositoryinstance by starting with an empty ObservableList
     * and subsequently calling load().
     */
    protected Repository() {
        load();
    }

    /**
     * Loades the coressponding xml file if existent.
     */
    protected void load() {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(this.getPath()));
            String json = new String(encoded, StandardCharsets.UTF_8);
            final Object loaded = this.getXStream().fromXML(json);
            if (loaded instanceof ObservableList) {
                this.data = (ObservableList<T>) loaded;
            }
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Returns the Name of the Repositories EnitityType by using the NamingConvention.
     * @throws Error If Respository does not follow NamingConventions.
     */
    private String getModelName() {
        String className = this.getClass().getSimpleName();
        if (className.length() < 10 || className.substring(className.length() - 10).equals("Repository") == false) {
            throw new Error("A Repositorysname must have the following Form [EntityName]Repository.");
        }
        return className.substring(0, className.length() - 10);
    }

    /**
     * Returns the the relative Path to the repositorys xml document
     * starting at the root of the project.
     * 
     * @return The relative Path to the repositorys xml document.
     */
    private String getPath() {
        return "./storage/" + this.getModelName() + ".xml";
    }

    /**
     * Initalises a XStream Instance with default Security, an ObservableListConverter
     * and some additional allowedTypes.
     * 
     * @return An fully initalised instance of XStream.
     */
    private XStream getXStream() {
        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.registerConverter(new ObservableListConverter(xStream));
        xStream.allowTypesByWildcard(new String[] {
            "project.**"
        });
        return xStream;
    }

}