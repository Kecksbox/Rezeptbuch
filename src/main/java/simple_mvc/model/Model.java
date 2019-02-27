package simple_mvc.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import simple_mvc.repository.Repository;
import simple_mvc.utilities.ClassFinder;

/**
 * @author Malte Heuser
 */
public abstract class Model {

    /**
     * Adds this instance to the corresponding Repository.
     */
    public void toRepository() {
        Repository<Model> repository = this.getAssociatedRepository();
        repository.add(this);
    }

    /**
     * Returns the name of this Class.
     */
    protected String getModelName() {
        return this.getClass().getSimpleName();
    }

    /**
     * Returns the associated Repository.
     */
    protected Repository<Model> getAssociatedRepository() {
        System.out.println(this.getClass().getSimpleName());
        Class<?> c = this.findRepositoryClass();
        try {
            Method tmp = c.getMethod("getInstance", new Class[0]);
            try {
                Object tmp2 = tmp.invoke(null);
                if (tmp2 instanceof Repository) {
                    return (Repository<Model>) tmp2;
                }
                throw new Error("The instance returned by " + c.getName() +" is no valid Repository.");
            } catch (Exception e) {
                e.printStackTrace();
                throw new Error("Call to static Method getInstance on " + c.getName() + " failed.");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new Error("The Class " + c.getName() + " is no valid Singelton.");
        }
    }

    /**
     * Finds a Repository with the expectedName in the project package.
     * 
     * @return The Class of a Repository with the expectedName.
     */
    private Class<?> findRepositoryClass() {
        try {
            String expectedRepositoryName = getModelName() + "Repository";
            for (Class<?> c : ClassFinder.getClasses("project")) {
                if (c.getSimpleName().equals(expectedRepositoryName)) {
                    return c;
                }
            }
            throw new Error("The corresponding Model could not be found. Please make sure the Repository is named "
                    + expectedRepositoryName + " and is located under the project package.** .");
        } catch (Exception e) {
            throw new Error("Search in project.repository failed.");
        }
    }

}