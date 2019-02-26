package project.repository;

import project.model.Rezept;
import simple_mvc.repository.Repository;

public class RezeptRepository extends Repository<Rezept> {

    private static RezeptRepository instance = new RezeptRepository();

    public static RezeptRepository getInstance() {
		  return RezeptRepository.instance;
    }

}