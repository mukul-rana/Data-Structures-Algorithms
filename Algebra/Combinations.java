package Algebra;

import java.util.*;
import java.util.Arrays;

public class Combinations {

	public static void main(String[] args) {
		
		Scanner inp = new Scanner(System.in);
		
		int n = inp.nextInt();
		int arr[] = new int[n];
		
		for(int i=0;i<n;i++)
			arr[i] = inp.nextInt();
		combinations(arr,2);
		
	}
	
	
	static void combinations(int[] arr, int size) {
		ArrayList<int[] > combi = new ArrayList<>();
		int[] subset;
		for(int i=0;i<arr.length-size+1;i++) {
			subset = new int[size];
			subset[0] = arr[i];
			
			fillIt(arr,combi,subset,size,1,i+1);
		}
		for(int[] s : combi) {
			for(int x : s)
				System.out.print(x  + " ");
			System.out.println();
		}
	}
	
	static void fillIt(int[] arr, ArrayList<int[]> combi, int[] subset, int size, int pos, int index) {
		if(size == pos) { combi.add(subset); return;}
		
		int subs[];
		for(int i = index;i<arr.length-size+pos+1;i++) {
			subs = Arrays.copyOf(subset, size);
			subs[pos] = arr[i];
			fillIt(arr,combi,subs,size,pos+1,i+1);
		}
	}
}
