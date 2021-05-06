package dynamicProgramming;

public class KnapSack {

	static int knapSack(int wts[], int prices[], int N, int W){
		if(N==0||W==0) return 0;

		int incl = 0,exc=0;

		//Including a current item
		if(wts[N-1] <=W)
		incl = prices[N-1] + knapSack(wts,prices,N-1,W-wts[N-1]);

		//Excluding the current item
		exc = 0 + knapSack(wts,prices,N-1,W);
		return Math.max(incl,exc);
	}
	public static void main(String[] args) {
//		int [] wts = {2,7,3,2};
		int [] wts = {2,2,3,1};
		int prices[] = {5,20,20,10};
		int n = 4;
//		int W = 11;
		int W= 5;
		
//		System.out.println(knapSack(wts,prices,n,W));
		System.out.println(bottomUP(wts,prices,n,W));
	}
	
	static int bottomUP(int wts[], int prices[], int N, int W) {
		int dp[][] = new int[100][100];
		
		for(int i=0;i<=N;i++) {
			for(int w=0;w<=W;w++) {
				if(i==0||w==0) dp[i][w]=0;
				else {
					int inc =0,exc=0;
					if(wts[i-1]<=w) 
						inc = prices[i-1] + dp[i-1][w-wts[i-1]];
//					}
//					else
						exc = dp[i-1][w];
					dp[i][w] = Math.max(inc, exc);
				}
				
					
				
			}
		}
		for(int i=0;i<=N;i++) {
			for(int j=0;j<=W;j++)
				System.out.print(dp[i][j] + " ");
		System.out.println();}
		return dp[N][W];
	}

}
