package Client;

public class GMainClient {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GView view = new GView();
		GModel model = new GModel();
		model.addObserver(view);
		GController controller = new GController(view, model);
	}
}
