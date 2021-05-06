package Algebra;

import java.util.*;
public class InclusionExclusion {

	
	//This is a simple program to calculate that how many numbers upto a certain number(upto)
	//are divisible by any of the array elements(arr).
	
	
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		
		int numbers = inp.nextInt();
		//Number of Array Element
		
		int[] arr = new int[numbers];

		
		for(int i=0;i<numbers;i++)
			arr[i] = inp.nextInt();
		//Entering the values of array elements
		
		int upto = inp.nextInt();
		//The upper limit upto which numbers are counted.
		
		ArrayList<ArrayList<int[]>> allComb = new ArrayList<>();
		//List of all combinations of all sizes of the array
		
		for(int i=0;i<numbers;i++)
			allComb.add(combinations(arr,i+1));
		// Calculating and adding combinations for every size upto numbers
	
		
		int ans =0;
		//Count of numbers
		
		
		boolean f=false;//Inclusion Exclusion purpose
		
		int sum;
		int gcd;
		for(ArrayList<int[]> ar : allComb) {

			sum=0;
			
			for(int[] a : ar) {
				gcd=a[0];
				//Calculating the gcd of particular combination
				for(int i=1;i<a.length;i++) {
					gcd =    (gcd*a[i])/gcd(gcd,a[i]);
					
				}

				sum += upto/gcd;
				//Numbers divisible by gcd of particular combination
			}

		//Evaluating the Inclusion Exclusion Principle Equation	
			if(f)
				ans -= sum; //Even sized combination
			else
				ans += sum;//Odd sized combination
			f = !f;

		}
		
		
		System.out.println(ans);
		
		
		
	}
	
	static int gcd(int a, int b) {
		if(b==0 ) return a;
		return gcd(b,a%b);
	}
	
	static ArrayList<int[]> combinations(int[] arr, int size) {
		ArrayList<int[] > combi = new ArrayList<>();
		int[] subset;
		for(int i=0;i<arr.length-size+1;i++) {
			subset = new int[size];
			subset[0] = arr[i];
			
			fillIt(arr,combi,subset,size,1,i+1);
		}
		return combi;
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
