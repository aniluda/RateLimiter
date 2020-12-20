
public class SetUp_Config {
	void add_Config_data() {
		Config config = new Config();
		
		Node Foo = new Node();
		Foo.Type = "class";
		Foo.Invocation_threshold = 100;
		Foo.Invocation_counter = 1;
		Foo.Invocation_counter_timeunit = "minute";
		Foo.Invocation_backoffperiod = 1;
		Foo.Invocation_backofftime_unit = "minute";
		
		config.add("FOO", Foo);
		
		Node a = new Node();
		Foo.Type = "method";
		Foo.Invocation_threshold = 50;
		Foo.Invocation_counter = 10;
		Foo.Invocation_counter_timeunit = "second";
		Foo.Invocation_backoffperiod = 10;
		Foo.Invocation_backofftime_unit = "second";
		
		config.add("FOO.a", a);
		
		Node b = new Node();
		Foo.Type = "method";
		Foo.Invocation_threshold = 25;
		Foo.Invocation_counter = 20;
		Foo.Invocation_counter_timeunit = "second";
		Foo.Invocation_backoffperiod = 10;
		Foo.Invocation_backofftime_unit = "second";
		
		config.add("FOO.b", b);

	}
}
