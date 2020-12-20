import java.util.Arrays;

class Occurance{
	
	 void repeated_occurance(String str){
		 
		str = str.toLowerCase();
		
		int[] count = new int[26];
		
		// Looping and find out the count for each character
		for(int index= 0; index < str.length() ; index++){
			
			int key = str.charAt(index) - 'a';
						
			if(key > 26 || key < 0) {
				continue;
			}
			
			else {
				count[key]++;
			}
		}
		
		
		// Loop through the whole string and find out the repeated character
		for(int index = 0; index < str.length(); index++){
			
			int key = str.charAt(index) - 'a';
			
			if(key > 26 || key < 0) {
				continue;
			}
			
			else {
				
				if(count[key] > 1){
					System.out.println("key at: "+ index);
					break;
				}
				
			}
		}
	}

}


public class NonRepeated {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Occurance obj = new Occurance();
			obj.repeated_occurance("Just book here Just");
	}

}
