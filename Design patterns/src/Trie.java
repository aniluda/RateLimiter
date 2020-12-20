class TrieNode{
	TrieNode[] child;
	boolean isEndOfWord ;
	
	public TrieNode() {
		this.child = new TrieNode[26];
		this.isEndOfWord = false;
		for(int index = 0 ; index < 26 ; index++) {
			child[index] = null;
		}
	}
}

public class Trie {
	
	static TrieNode root;
	
	static void insert(String str) {
		
		TrieNode pcrawl = root;
		
		for(int key = 0 ; key < str.length() ; key++) {
			
			int index = str.charAt(key)-'a';
			
			if(pcrawl.child[index] == null) {
				pcrawl.child[index] = new TrieNode();
			}
			
			pcrawl = pcrawl.child[index];
		}
		pcrawl.isEndOfWord = true;
	}
	
	static boolean search(String str) {
		
		TrieNode pcrawl = root;
		
		for(int key = 0 ; key < str.length() ; key++) {
			
			int index = str.charAt(key)-'a';
			
			if(pcrawl.child[index] == null) {
				return false;
			}
			
			pcrawl = pcrawl.child[index];
		}
		
		return (pcrawl != null && pcrawl.isEndOfWord);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			root = new TrieNode();
			
	        String keys[] = {"the", "a", "there", "answer", "any", 
	                "by", "bye", "their"}; 
	
	        String output[] = {"Not present in trie", "Present in trie"}; 
	
	
	        // Construct trie 
			int i; 
			for (i = 0; i < keys.length ; i++) 
			   insert(keys[i]); 	
			
	        // Search for different keys 
	        if(search("the") == true) 
	            System.out.println("the --- " + output[1]); 
	        else System.out.println("the --- " + output[0]); 
	          
	        if(search("these") == true) 
	            System.out.println("these --- " + output[1]); 
	        else System.out.println("these --- " + output[0]); 
	          
	        if(search("their") == true) 
	            System.out.println("their --- " + output[1]); 
	        else System.out.println("their --- " + output[0]); 
	          
	        if(search("thaw") == true) 
	            System.out.println("thaw --- " + output[1]); 
	        else System.out.println("thaw --- " + output[0]); 

		}
}
