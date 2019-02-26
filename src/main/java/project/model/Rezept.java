package project.model;

import simple_mvc.model.Model;

public class Rezept extends Model {

    private String name;

    private String zutaten;

    private String arbeitsschritte;

    public Rezept() {
        name = "";
        zutaten = "";
        arbeitsschritte = "";
    }


    public String getName() {
        return this.name;
    }

	public void setName(String name) {
		this.name = name;
    }
    
    public String getZutaten() {
        return this.zutaten;
    }

	public void setZutaten(String zutaten) {
		this.zutaten = zutaten;
    }
    
    public String getArbeitsschritte() {
        return this.arbeitsschritte;
    }

	public void setArbeitsschritte(String arbeitsschritte) {
		this.arbeitsschritte = arbeitsschritte;
    }
    
}