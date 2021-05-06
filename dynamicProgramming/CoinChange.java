package dynamicProgramming;

import java.util.Arrays;

public class CoinChange {

public static void main(String[] args){
			
		int n = 5;
		int arr[] = {1,2,5,10,50};
		int amount =39;
		System.out.println(Coin2(n,arr,amount));
		System.out.println(Coin2(n,arr,13));
	}
	
	static int  Coin1(int n, int arr[],int  sum) {
		//Exponential time
		if(sum ==0) return 0;
		int temp, min = Integer.MAX_VALUE;
		for(int x : arr) {
			if(sum - x < 0) continue;
				temp = Coin1(n,arr,sum-x)+1;
				if(temp < min) min = temp;
		}
		return min;
	}
	
	static int Coin2(int n, int coins[] , int sum) {
		//Bottom Up Approach
		int dp[] = new int[sum+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] =0;
		int min=0;
		
		for(int rupay =1; rupay<=sum;rupay++) {
		
			//Iterate over coins
			for(int i=0;i<n;i++) { 
				if(rupay >= coins[i]) {
					
					min = dp[rupay-coins[i]];
					if(min != Integer.MAX_VALUE)
						dp[rupay] = Math.min(dp[rupay], min+1);
				}
			}
		}
			
		return dp[sum];
	}

}
