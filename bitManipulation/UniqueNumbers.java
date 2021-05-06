package bitManipulation;
import java.util.*;

public class UniqueNumbers {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		int n =20;
		//int[] arr = new int[n];
		int[] arr = {1, 9, 2, 8, 3, 7,57, 4, 6, 5,23, 1, 9, 2, 8 ,3 ,7 ,4 ,6, 5};
//		for(int i=0;i<n;i++)
//			arr[i] = inp.nextInt();
		int sum=0;
		for(int x  : arr) {
			System.out.println(Integer.toBinaryString(x));
			sum ^= x;}
		
//		System.out.println(Integer.toBinaryString(sum) + " " + Integer.toBinaryString(~(sum-1)));
//		System.out.println((sum) + " " + (~(sum-1)));
		sum = sum &~(sum-1);
		System.out.println(Integer.toBinaryString(sum)  + " " + sum);
		int a, b = a =0;
		
		for(int x : arr) {
			if( (x&sum) > 0 ) {System.out.println("a " + Integer.toBinaryString(x) + " " + x);
				a ^=x;}
			else {
//				System.out.println("b " + Integer.toBinaryString(x));
				b ^=x;}
		}
		System.out.println(a + " " + b);

	}
	
}