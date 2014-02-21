package MVC_test;

public class GMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GView view = new GView();
		GModel model = new GModel(view);
		GController controller = new GController(view, model);
		model.playGame();
	}
}
