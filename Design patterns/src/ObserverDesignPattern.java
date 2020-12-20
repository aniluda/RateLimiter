import java.util.ArrayList;
import java.util.List;

abstract class Observer{
	Subject subject;
	abstract void update();
}

interface Observable{
	void add(Observer observer);
	void remove(Observer observer);
	void notify_all_observer();
}


class Subject implements Observable{
	
	List<Observer> observers;
	
	public Subject() {
		this.observers = new ArrayList<>();
	}
	
	private int state;
	
	public int getState() {
		return state;
	}

	
	public void setState(int state) {
		this.state = state;
		notify_all_observer();
	}
	
	@Override
	public void add(Observer observer) {
		// TODO Auto-generated method stub
		observers.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	@Override
	public void notify_all_observer() {
		// TODO Auto-generated method stub
		for(Observer observer : observers) {
			observer.update();
		}
	}
	
}

class OctalObserver extends Observer{
	
	public OctalObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	     System.out.println( "Octal String: " + Integer.toOctalString( subject.getState() ) ); 
	}

}

class HexaObserver extends Observer{
	
	public HexaObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
	      System.out.println( "Hex String: " + Integer.toHexString( subject.getState() ) ); 
	}
	
}

class BinaryObserver extends Observer{
	
	public BinaryObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
	      System.out.println( "Binary String: " + Integer.toBinaryString( subject.getState() ) ); 
	}
	
}

public class ObserverDesignPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      Subject subject = new Subject();

	      new HexaObserver(subject);
	      new OctalObserver(subject);
	      new BinaryObserver(subject);

	      System.out.println("First state change: 15");	
	      subject.setState(15);
	      System.out.println("Second state change: 10");	
	      subject.setState(10);

	}

}
