package bitManipulation;
import java.time.LocalTime;
import java.util.Scanner;


public class FastExponentiation {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		long a = inp.nextLong(),b=inp.nextLong();
		System.out.println((long)Math.pow(a, b)%1000000007);
		
		LocalTime myObj = LocalTime.now();
		System.out.print(power(a,b) + " ");
	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
	    
	    myObj = LocalTime.now();
	    System.out.print(power(a,b) + " ");  
	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
	    
	    myObj = LocalTime.now();
	    System.out.print(power3modulo(a,b) + " ");
	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
//		System.out.println(highestSet(3423));
//		System.out.println(highestSet2(3423));
		
	    myObj = LocalTime.now();
	    System.out.print(recursivePower(a,b) + " ");
	    System.out.println(Double.parseDouble(String.valueOf(LocalTime.now()).substring(6)) - Double.parseDouble(String.valueOf(myObj).substring(6)));
	    
	    
	}
	static long power(long n, long pow) {
		String pows = Long.toBinaryString(pow).substring(1);
		long exp=n;
		for(int i=0;i<pows.length();i++) {
			exp = (exp *exp)%1000000007;
			if(pows.charAt(i)=='1')
				exp = (exp*n)%1000000007;
		}
			
		return exp;
//		more likely
	}
	
	static long power2(long n, long pow) {
		
		// O(log pow) time
		long exp = 1;
		while(pow>0) {
			
			if((pow&1)==1)
				exp = (exp*n)%1000000007;
			n = (n*n)%1000000007;
			pow = pow>>1;
		}
		
		return exp;
	}
	
	static long power2Modulo(long n, long pow, long M) {
		long exp = 1;
		while(pow>0) {
			
			if((pow&1)==1)
				exp = (exp * n)%M;
			n = (n*n)%M;
			pow = pow>>1;
		}
		
		return exp;
	}
	
	static long power3(int x, int n) {
		if(n==0) return 1;
		if(n ==1 )
		return x;
		return n%2==0 ? power3(x*x,n/2) : x*power3( x*x , (n-1)/2);
	}
	static long power3modulo(long x, long n) {
		if(n==0) return 1;
		if(n==1) return x%1000000007;
		long t = power3modulo(x,n/2);
		t = (t*t)%1000000007;
		
		if(n%2==0)
			return t;
		else 
			return ((x%1000000007)*t)%1000000007;
	}
	
	
	static long recursivePower(long a,long n) {
		
		//Not SO Accurate
//		return (a*x*x)%1000000007;
		
		if(n==0) return 1;
		long x = recursivePower(a,n/2)%1000000007;
		if(n%2==0) return (x*x)%1000000007;
		return (a*x%1000000007*x%1000000007)%1000000007;
	}
	
	static int highestSet2(int n) {
		n |= n>>1;
		n |= n>>2;
		n |= n>>4;
		n |= n>>8;
		n |= n>>16;
		n++;
		return (n>>1);
		
	}
	static int highestSet(int n) {
		return 1 << ((int)(Math.log(n)/Math.log(2)));
	}

}
