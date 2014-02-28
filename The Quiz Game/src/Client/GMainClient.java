package Client;
/**
 * Starts the program by creating View, Model and the Controller. Also sets the View-class as an observer to model. 
 *
 */
public class GMainClient {
	public static void main(String[] args) {
		GView view = new GView();
		GModel model = new GModel();
		model.addObserver(view);
		new GController(view, model);
	}
}
