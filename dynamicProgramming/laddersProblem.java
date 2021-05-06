package dynamicProgramming;
import java.util.*;

public class laddersProblem {
	
	static void main(String args[]) {
		Scanner inp = new Scanner(System.in);
		int n = inp.nextInt(),k = inp.nextInt();
		System.out.println(linearTime(n,k));
	}
	
	static int linearTime(int n,int k) {
		
//O(n)
		int[] arr = new int[n+1];
		
		arr[0] =1;
		arr[1] = 1;
		for(int j=2;j<=k;j++)
			arr[j] = arr[j-1]<<1;
		
		if(n<=k) return arr[n-1];
		for(int l = k+1;l<=n;l++) {
			arr[l] = 2*arr[l-1] - arr[l-k-1];
		}
		return arr[n];
		
	}
	
	
	static int Linear(int n, int k) {
//		O(n.k)
		int[] arr = new int[n+1];
		arr[0] =1;
		for(int i=1;i<=n;i++) {
			arr[i]=0;
			for(int j=1;j<=k;j++) {
				if(i-j >= 0)
				arr[i] += arr[i-j];}
		}
//		for(int i=0;i<n;i++)
//			System.out.print(arr[i] + " ");
//		System.out.println();
		return arr[n];
	}
	
	static int expo(int n, int k) {
		
		//O ( k^n)
		if(n<0) return 0;
		if(n==0) return 1;
		int ans =0;
		for(int i=1;i<=k;i++)
			ans += expo(n-i,k);
		return ans;
	}
	

}
