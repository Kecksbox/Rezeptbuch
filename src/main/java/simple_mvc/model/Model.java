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

public abstract class Model {

    public void toRepository() {
        Repository<Model> repository = this.getAssociatedRepository();
        repository.add(this);
    }

    protected String getEntityName() {
        return this.getClass().getSimpleName();
    }

    protected Repository<Model> getAssociatedRepository() {
        System.out.println(this.getClass().getSimpleName());
        Class<?> c =this.findRepositoryClass();
        try {
            Method tmp = c.getMethod("getInstance", new Class[0]);
            try {
                Object tmp2 = tmp.invoke(null);
                System.out.println(tmp2);
                if (tmp2 instanceof Repository) {
                    return (Repository<Model>) tmp2;
                }
                throw new RuntimeException("awdawd");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("awdawd");
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("awdawd");
        }
    }

    private Class findRepositoryClass() {
        try {       
            String expectedRepositoryName = getEntityName() + "Repository";
            for ( Class<?> c : ClassFinder.getClasses("project")) {
                if (c.getSimpleName().equals(expectedRepositoryName)) {
                    return c;
                }
            }
            throw new Error(
                "The corresponding Model could not be found. Please make sure the Repository is named " 
                + expectedRepositoryName + " and is located under the project package.** ."
                );
        } catch(Exception e) {
            throw new Error("Search in project.repository failed.");
        }
    }

}