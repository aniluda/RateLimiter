import java.io.File;
import java.util.*;

class HitCounter{
	
	public int REQUEST_LIMIT = 100; 
	public Long TIME_LIMIT = (long) 1; 
	
	Queue<Long> queue;
	Queue<File> file_queue;
	
	File a = new File("a.txt");
	
	public HitCounter() {
		queue = new LinkedList<>();   
	}
	
	public boolean hit(long timestamp) {
		if(!queue.isEmpty() && queue.peek() - timestamp >= TIME_LIMIT) queue.poll();
		
		if(queue.size() < 100) {
			queue.add(timestamp);
			return true;
		}
		
		return false;
	}
	
}

public class RateLimiter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
