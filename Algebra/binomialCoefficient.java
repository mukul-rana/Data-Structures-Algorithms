package Algebra;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class binomialCoefficient {

	public static void main(String[] args) {
		Scanner inp= new Scanner(System.in);
		int n = inp.nextInt();
		int k = inp.nextInt();		
//		System.out.println(nCr1(n,k));
//		System.out.println(nCr2(n,k));
		System.out.println(nCr3(n,k).mod(BigInteger.valueOf(1000000007)));
	}
	
	static int nCr1(int n, int k) {
		if(n == k || k==0) return 1;
		if(n < k) return 0;
		return nCr1(n-1,k-1) + nCr1(n-1,k);
	}

	static int nCr2(int n, int k) {
		k = Math.min(k,n-k);
		int[][] dp = new int[n+1][];
		for(int i=0;i<=n;i++) {
			if(i<k)
				dp[i] = new int[i+1];
			else 
				dp[i] = new int[k+1];
			
		}
					
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=Math.min(i, k);j++) {
				if(i==j || j==0) dp[i][j] =1;
				else
				dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
			}
		}
		return dp[n][k];
		
	}
	
	static BigInteger nCr3(int n, int k) {
		k = Math.min(k,n-k);
		BigInteger[] pasca = new BigInteger[k+1];
		Arrays.fill(pasca, BigInteger.ZERO);
		pasca[0]=BigInteger.ONE;
		for(int i=1;i<=n;i++)
			for(int j=Math.min(k, i);j>0;j--) {
				pasca[j] = pasca[j].add(pasca[j-1]);
			}
				
		return pasca[k];
	}
	
}
 