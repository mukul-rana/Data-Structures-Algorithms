package AlgebraicAlgos;
import java.math.BigInteger;
import java.time.LocalTime;

public class Fibonacci {

	/*
	 * 
	 * Tags : https://www.geeksforgeeks.org/interesting-facts-fibonacci-numbers/
	 * 
	 * Cassini Identity
	 * 
	 * 
	 * n = 1000000
	 * simple = 11 sec
	 * fastDoublingMethod = 0.07 sec
	 * fastDoublingMethod2 = 0.13 sec
	 * 
	 */
	
	
	public static void main(String[] args) {
		int n= 100000;
//		LocalTime myObj = LocalTime.now();
	    simple(n);
//	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
		
	    
	    
//	    myObj = LocalTime.now();
//	    fastDoublingMethod(n);
//	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
//
//	    
//	    myObj = LocalTime.now();
//	    fastDoublingMethod2(n);
//	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));

	}		
		
	static void simple(int n) {
		//Simple Fibonacci Series Finder 
		BigInteger a=BigInteger.valueOf(-1),b=BigInteger.ONE,c=BigInteger.ZERO;
		for(int i=0;i<=n;i++) {
			c = a.add(b);
			a = b;
			b = c;
			
//			System.out.println(c);
		}
		System.out.println(c);
	}
	
	static long nthFibonacci(int n) {
		// Binet's Formula
		//Correct Value upto 71th value only
		//uses Golden Ratio (1 + sqrt(5))/2
		// O(1)
		
		return Math.round( Math.pow((1 + Math.sqrt(5))/2, n) / Math.sqrt(5) );
		//Rounding to the nearest neighbour
	}
	
	static BigInteger[] fastDoublingMethod(int n) {
		
		//Best Method Never Exists  : )
		
		//https://cp-algorithms.com/algebra/fibonacci-numbers.html#FastDoublingMethods
		//It return Fn and F n+1;
		//Based on the Matrix Method
		// O (logn)
		//Tags : Binary Exponentiation
		
 		
		if(n==0) {
			BigInteger []ans = {BigInteger.ZERO,BigInteger.ONE};
			return ans;
		}
		BigInteger[] tuple = fastDoublingMethod(n>>1);
		BigInteger c = tuple[0].multiply(((tuple[1]).multiply(BigInteger.TWO).subtract(tuple[0]))) ;
		BigInteger d = tuple[0].multiply(tuple[0]).add(tuple[1].multiply(tuple[1]));
		
		if(n%2==1) {
			BigInteger[] ans = {d,d.add(c)};
			return ans;
		}
		else {
			BigInteger[] ans  = {c,d};
			return ans;
		}
	}
	
	
	static BigInteger fastDoublingMethod2(int n) {
		/*
		 * F(2k) = F(k)( 2F(k+1) - F(k))
		 * F(2k+1) = F(k+1)*F(k+1) + F(k)*F(k) 
		 */
		

		if(n<=2) return BigInteger.ONE;
		

		
		BigInteger so = fastDoublingMethod2(n>>1);
		BigInteger sa = fastDoublingMethod2(n/2 + 1);
		return n%2==0 ? so.multiply(  sa.multiply(BigInteger.TWO).subtract(so) ) : so.multiply(so).add(sa.multiply(sa));
	}
	
	
}
