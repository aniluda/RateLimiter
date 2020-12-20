import java.util.HashMap;

class Node{
	String Type;
	int Invocation_threshold;
	int Invocation_counter;
	String Invocation_counter_timeunit;
	int Invocation_backoffperiod;
	String Invocation_backofftime_unit;
}

public class Config {
	
	static HashMap<String, Node> hash_map = new HashMap<String, Node>();

	static HashMap<String, Node> get_properties() {
		return hash_map;
	}
	
	void add(String invocable, Node node) {
		hash_map.put(invocable, node);
	}
}


