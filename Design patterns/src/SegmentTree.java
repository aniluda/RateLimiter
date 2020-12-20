import java.util.Arrays;

public class SegmentTree {
	
	public SegmentTree(int arr[], int n) {
		int[] st;
		
		int height = (int) Math.ceil(Math.log(n) / Math.log(2));
		
		int max_size = (int)(Math.pow(2, height+1) - 1);
		
		st = new int[max_size];
		
        System.out.println(constructSTUtil(arr, st, 0, n - 1, 0)); 
        System.out.println(Arrays.toString(st));
        
        System.out.println(getSumUtil(arr, st, 0, n-1, 1, 3, 0));
	}
	
	public int getMid(int ss, int se) {
		return (ss+se)/2;
	}

	public int constructSTUtil(int[] arr, int st[], int ss, int se, int si) {
		// TODO Auto-generated method stub
		
		if(ss == se) {
			st[si] =  arr[ss];
			return st[si];
		}
		
		int mid = getMid(ss, se);
		
		st[si] = constructSTUtil(arr, st, ss, mid, 2*si+1) + constructSTUtil(arr, st, mid+1, se, 2*si+2);
		
		return st[si];
	}
	
    void updateValueUtil(int[] st, int ss, int se, int i, int diff, int si) {
    	
    	if(i < ss || i > se) {
    		return;
    	}
    	
    	st[si] = st[si] + diff;
    	
    	if(ss != se) {
    		int mid = getMid(ss, se);
    		updateValueUtil(st, ss, mid, i , diff, 2*si+1);
    		updateValueUtil(st, mid+1, se, i , diff, 2*si+2);
    	}
    }

	
	public int getSumUtil(int[] arr,int[] st, int ss, int se, int qs, int qe, int si) {
		
		if( qe>= se && ss>= qs) {
			return st[si];
		}
		
		if(ss > qe || se < qs) {
			return 0;
		}
		
		int mid = getMid(ss, se);
		
		return getSumUtil(arr, st, ss, mid, qs, qe, 2*si+1) + getSumUtil(arr, st, mid+1, se, qs, qe, 2*si+2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        int arr[] = {1, 3, 5, 7, 9, 11}; 
        int n = arr.length; 
        SegmentTree  tree = new SegmentTree(arr, n); 

	}

}
