package repository;
import entwurf.Repository;
import model.TestModel;

public class TestRepository extends Repository<TestModel> {

    private static TestRepository instance;

    private TestRepository() {
        super("Test");
    }

    public static TestRepository getInstance() {
        if (TestRepository.instance == null) {
			instance = new TestRepository();
		}
		return TestRepository.instance;
    }

}