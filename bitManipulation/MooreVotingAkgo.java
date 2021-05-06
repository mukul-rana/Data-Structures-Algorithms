package bitManipulation;

import java.util.Scanner;

public class MooreVotingAkgo {

	
	//Finding Majority element
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int n = inp.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = inp.nextInt();
		
		int count=1, max_index=0;
		for(int i=1;i<n;i++) {
			if(arr[i] == arr[max_index])  
				count++;
			else
				count--;
			if(count ==0 ) { max_index  = i;
			count=1;}
			
			
		}
		count =0;
		for(int x : arr)
			count += x == arr[max_index] ? 1 : 0;
		System.out.println(count + " " + arr[max_index]);

	}

}
