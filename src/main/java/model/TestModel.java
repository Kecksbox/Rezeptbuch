package model;

import entwurf.Model;
import entwurf.Repository;

public class TestModel extends Model {

    private String name;

    private String vorname;

    public TestModel(String name, String vorname) {
        Repository<Model> repository = getAssociatedRepository();
        repository.add(this);

        this.name = name;
        this.vorname = vorname;
    }

    public String getName() {
        return this.name + ' ' + this.vorname;
    }

}