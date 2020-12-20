import java.util.*;

class Foo{
	static Foo instance = new Foo();
	
	private Foo() {}
	
	public static Foo get_instance() {
		return instance;
	}
	
	public void a() {}
	
	public void b() {}
}

class Bar{
	static Bar instance = new Bar();
	
	private Bar() {}
	
	public static Bar get_instance() {
		return instance;
	}
	
	public void x() {}
	
	public void y() {}
}


class InvokeFunction{
	 
	static Queue<Long> Type = new LinkedList<Long>();
	static HashMap<String , Queue<Long>> queues = new HashMap<String , Queue<Long>>();
	static HashMap<String, Node> hash_map = Config.get_properties();
	static HashMap<String, Boolean> is_backoff_period = new HashMap<String, Boolean>();

	void create_queue() {
		HashMap<String, Node> hash_map = Config.get_properties();

		Set<String> entry_set = hash_map.keySet();
		for(String key : entry_set) {
			queues.put(key, new LinkedList<Long>());
		}
	}
	
	void invoke_method(String Type) {
		
		String parent_Type = InvokeFunction.split_word(Type);
		long milliseconds = System.currentTimeMillis();

		if(is_backoff_period.get(parent_Type)) {
			
			int parent_backoffperiod = get_seconds(hash_map.get(parent_Type).Invocation_backoffperiod, hash_map.get(parent_Type).Invocation_backofftime_unit);
			
			int parent_Invocation_counter = get_seconds(hash_map.get(parent_Type).Invocation_counter, hash_map.get(parent_Type).Invocation_counter_timeunit);
			
			if((queues.get(parent_Type).peek() + parent_backoffperiod + parent_Invocation_counter) < milliseconds) {
				queues.remove(parent_Type);
				queues.get(parent_Type).add(milliseconds);
				is_backoff_period.put(parent_Type, false);

				InvokeFunction.add_Type(Type, milliseconds);
			}
		}
		else {
			InvokeFunction.add_Type(Type, milliseconds);
		}
		
	}
	
	static void add_Type(String Type, long milliseconds) {
		
		String parent_Type = InvokeFunction.split_word(Type);
		
		if(is_backoff_period.get(Type)) {
			
			int backoffperiod = get_seconds(hash_map.get(Type).Invocation_backoffperiod, hash_map.get(Type).Invocation_backofftime_unit);
			
			int Invocation_counter = get_seconds(hash_map.get(Type).Invocation_counter, hash_map.get(Type).Invocation_counter_timeunit);
			
			if((queues.get(Type).peek() + backoffperiod + Invocation_counter) < milliseconds) {
				
				if(queues.get(parent_Type).size() > hash_map.get(parent_Type).Invocation_threshold) {
					is_backoff_period.put(parent_Type, true);
				}
				
				else {
					queues.get(Type).remove();
					queues.get(Type).add(milliseconds);
					queues.get(parent_Type).add(milliseconds);
					is_backoff_period.put(Type, false);	
				}
				
			}
			
		}
		else {
			if(queues.get(Type).size() > hash_map.get(Type).Invocation_threshold ) {
								
				if(queues.get(parent_Type).peek() + hash_map.get(parent_Type).Invocation_threshold < milliseconds) {
					queues.get(parent_Type).remove();
					queues.get(Type).add(milliseconds);
					queues.get(parent_Type).add(milliseconds);
				}
				else {	
					is_backoff_period.put(Type, true);	
					if(queues.get(parent_Type).size() > hash_map.get(parent_Type).Invocation_threshold) {
						is_backoff_period.put(parent_Type, true);
					}
				}
			}
			else {
				queues.get(Type).add(milliseconds);
				queues.get(parent_Type).add(milliseconds);
			}
		}

	}
	
	
	
	static String split_word(String str) {
		String[] words = str.split(".");
		return words[0];
	}
	
	static int get_seconds(int value, String Type) {
		
		if(Type == "minute") {
			return value * 60;
		}
		
		return value;
		
	}
	
}



public class RateLimiter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SetUp_Config config = new SetUp_Config();
		config.add_Config_data();
		
		InvokeFunction invoke = new InvokeFunction();
		invoke.create_queue();
	}

}
