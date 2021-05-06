package AlgebraicAlgos;

import java.math.BigInteger;


import java.time.LocalTime;
import java.util.*;




/*Sieve of Eratosthenes is an algorithm for finding all the 
 * prime numbers in a segment [1,n]
 * 
 * 
 * TESTING : 1. half array linear
 * 				3. upto root with halving the array
 * 				5. prime factorization
 * 				6. factors of a number		
 */

public class SievePrimeNumbers {
	public static void main(String[] args) {
		
//		System.out.println(segmentedSieve(100000));
//		System.out.println((int)123.23);
//		System.out.println(Math.floor(123.23));
 //System.out.println(segmentedSieve(10000));
//		System.out.println(SieveAlgorithm3(100));
//		System.out.println(SieveAlgorithm2(100));
//		System.out.println(SieveAlgorithm(100));\
		
		int n=190;
		System.out.println(SieveAlgorithm(n));
		//System.out.println(LinearSieve(n) + " " + LinearSieve(n).size());
		
		
		
	}
	
	
	static ArrayList<Integer> segmentedSieveByRange(int low, int high){
		/*
		 * Time Complexity :  O((R-L+1)loglog(R) + R^(1/2)loglog(R^(1/2)))
		 */
		ArrayList<Integer> primes = new ArrayList<Integer>();
		
		ArrayList<Integer> basicPrimes = LinearSieve((int)Math.sqrt(high));
		//We pre-generate all prime numbers up to R^(1/2) and use those primes
		//to mark all composite numbers in the segment [L,R]
		
		
		
		boolean[] isPrime = new boolean[high-low+1];
		Arrays.fill(isPrime, true);
		int lowLim;
		for(int x : basicPrimes) {
			lowLim = (low/x ) *x ;
			if(lowLim < low)
				lowLim += x;
			if(lowLim ==x)
				lowLim += x;
			for(int i=lowLim ; i<=high;i+=x)
				if(i != x)
					isPrime[i-low] = false;
		}
		for(int i=low;i<=high;i++)
			if(isPrime[i-low]) primes.add(i);
		return primes;
	}
	
	
	
	static ArrayList<Integer> segmentedSieve(int n){
		/*
		 * For performing of sieving it's enough to keep just prime numbers
		 * until root of n, split the complete range into blocks, and sieve
		 * each block separately. In doing so, we never have to keep multiple
		 * blocks in memory at the same time, and the CPU handles caching 
		 * a lot better.
		 * 
		 * The running time of block sieving is the same for regualr Sieve of
		 * Eratosthenes but the needed memory will shorten to O(n^(1/2) + S)
		 * where S is the size of segment
		 * 
		 * Optimal for sizes between 10^4 and 10^5
		 * 
		 */
		int limit = (int) Math.sqrt(n) + 1;
		ArrayList<Integer> primes = LinearSieve(limit);
		//Compute all primes smaller than or equal to square root of n using simple sieve
		
		int low = limit,high = 2*limit;
		//Divide the range [0..n-1] in different segments
		//We have chosen segment size as sqrt(n). 
		
		boolean isPrime[];
		int lowLim ;
		
		//While all segments of range [0..n-1] are not processed, process one segment at a time
		while(low < n) {
			if(high >= n)
				high = n;
			isPrime = new boolean[limit + 1];
			Arrays.fill(isPrime,true);
			for(int x : primes) {
				lowLim = (low/x)*x;
				if(lowLim < low)
					lowLim += x;
				
				for(int j = lowLim ; j < high ; j+=x)
					isPrime[j-low] = false;
			}
			
			for(int i= low; i< high;i++)
				if(isPrime[i-low]) 
					System.out.print(i + " ");
			System.out.println("next");
			low += limit;
			high += limit;
				
		}
		
		return primes;
	}
	
	
	
	//Main Basic Sieve of Eratosthenes Algorithm function
	static ArrayList<Integer> SieveAlgorithm(int n){
		
		//Time Complexity : O( n log log n)
		
		//PROBLEM :( 
		//An array of size O(n) may not fit in memory
		 
		
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		ArrayList<Integer> primes  = new ArrayList<>();
		isPrime[0] = isPrime[1] = false;
		for(int i=2;i<n;i++)
		{
			
			if(isPrime[i] && i*i <= n) {
			for(int k = i*i ; k<n;k+=i) {
				isPrime[k] = false;}}
		}
		for(int i=2;i<n;i++)
			if(isPrime[i]) primes.add(i);
		
		return primes;
	}
	
	
	//Different optimizations of the Sieve of Eratosthenes
	
	static ArrayList<Integer> SieveAlgorithm2(int n){
		
		//Sieving Till Root
	
		boolean[] isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		ArrayList<Integer> primes = new ArrayList<>();
		isPrime[0] = isPrime[1] = false;
		int ka=2;
		for(int i=2;i*i<n;i++) //**MAGIC HAPPENS HERE :)
		{
			if(!isPrime[i]) continue;
			ka++;
			for(int k = i*i ; k<n;k+=i) {
				isPrime[k] = false;ka++;}
		}
		for(int i=2;i<n;i++)
			if(isPrime[i]) primes.add(i);
		return primes;
		
		
		/* 
		 * Such optimization doesn't affect the complexity
		 * 
		 * TIME COMPLEXITY : n ln ln n^(1/2)  + o(n) which is 
		 * asymptotically the same acc. to properties of logarithms
		 * Though, the number of operations will reduce noticeably.
		 *  
		 */
	}
	
	static ArrayList<Integer> SieveAlgorithm3(int n){
		
		//Sieve by odd numbers only
		
		boolean[] isPrime = new boolean[n/2 +1];//MAGIC STARTS FROM BEGINNING
		Arrays.fill(isPrime, true);
		ArrayList<Integer> primes = new ArrayList<>();
		isPrime[0] = false;
		for(int i=2;i<=n/2;i++)
		{
			if(!isPrime[i]) continue;
		
			for(int k = (i*2-1)*(i*2-1) ; k<n; k+= (2*(i*2-1)))  //Don't get confused : >
				isPrime[(k+1)/2] = false; 
		}
		primes.add(2);
		for(int i=2;i<=n/2;i++)
			if(isPrime[i]) primes.add(2*i-1);
		return primes;
	}
	
	
	
	
	
	
	
	
	
	
	//TESTING
	static int primeNumbers6(int n){
		//Sieve Linear wala half memory wala
		int prime[] = new int[n];
		Stack<Integer> stack = new Stack<>();
		int ka=0;
		for(int i=2;i<n;i++) {
		if(prime[i]==0) {
			ka++;
			prime[i] = i*2-1;
			stack.push(i*2-1);
		}
		
		for(int j=0;  j<stack.size() && stack.get(j) <= prime[i] &&  i*stack.get(j)<n ; ++j) {
			prime[i*((stack.get(j)-1)/2)] = stack.get(j);ka++;
 			}
		}
		for(int x  : stack)
			System.out.println(2*x -1);
		
		return ka;
	}

}
