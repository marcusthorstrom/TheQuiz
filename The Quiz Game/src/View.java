import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

	
	
	public View(){
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if( arg0 instanceof Model && arg1 instanceof String){
			//DoSomething.
		}
		
	}
	
	
	public void addListeners(ActionListener e){
		//TODO
	}
	

}
