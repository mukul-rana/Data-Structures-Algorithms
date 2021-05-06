package bitManipulation;

import java.util.*;

public class subsets {

	public static void main(String[] args) {
		fu();
		Scanner inp = new Scanner(System.in);
		int n = inp.nextInt(),k;
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = inp.nextInt();
		ArrayList<ArrayList<Integer>> sets = new ArrayList<>();
		
		for(int i=0; i< 2<<(n-1) ; i++) {
			k=1;
			ArrayList<Integer> newSubset = new ArrayList<>();
			for(int j=0;j<n;j++) {
				if((i&k)>0)
					newSubset.add(arr[j]);
				k = k<<1;
			}
			sets.add(newSubset);
		}
		for(ArrayList<Integer  > l : sets)
		System.out.println(l);
	}
	
	static void fu() {
		int n=4;
		int[] arr = {2,3,4,5};
		for(int i=1;i<(1<<(n));i++) {
			String bin = String.valueOf(new StringBuffer(Integer.toBinaryString(i)).reverse() );
			System.out.println(bin);
			for(int j=0;j<bin.length();j++) {
				if(bin.charAt(j)=='1') 
					System.out.print(arr[j] + " ") ;
			}
			System.out.println("\n");
		}
	}

}
