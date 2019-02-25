package repository;
import entwurf.Repository;
import model.Rezept;

public class RezeptRepository extends Repository<Rezept> {

    private static RezeptRepository instance = new RezeptRepository();

    public static RezeptRepository getInstance() {
		return RezeptRepository.instance;
    }

    private RezeptRepository() {
        super("Rezept");
    }

}