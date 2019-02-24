package repository;
import entwurf.Repository;
import model.Rezept;

public class RezeptRepository extends Repository<Rezept> {

    private static RezeptRepository instance = new RezeptRepository();

    private RezeptRepository() {
        super("Rezept");
    }

    public static RezeptRepository getInstance() {
        if (RezeptRepository.instance == null) {
			throw new RuntimeException("instance was not yet inizialised.");
		}
		return RezeptRepository.instance;
    }

}