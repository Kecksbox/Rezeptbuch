package entwurf;

import java.lang.reflect.Method;

import com.google.gson.Gson;

public abstract class Model {

    public String toJson() {
        String json = new Gson().toJson(this);
        return json;
    }

    public void toRepository() {
        Repository<Model> repository = this.getAssociatedRepository();
        repository.add(this);
    }

    protected String  getEntityName() {
        return this.getClass().getSimpleName();
    }

    protected  Repository<Model> getAssociatedRepository() {
        System.out.println( this.getClass().getSimpleName());
        try {
            Class<?> c = Class.forName("repository."+ getEntityName() +"Repository");
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
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("awdawd");
        }
    }

}