import java.util.Observable;


public class Model extends Observable{
	
	
	
	public void Model()
	{
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
	}

}
