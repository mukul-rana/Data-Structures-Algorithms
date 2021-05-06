
package Graphs;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * The Floyd-Warshal (FW) algorithm is an All-Pairs Shortest Path(APSP)
 * algorithm. The main idea behind the Floyd Warshal algo is to gradually
 * build up all intermediate routes between nodes i and j to find the
 * optimal path. The goal of Floyd Warshal is to eventually consider going 
 * through all possible intermediate nodes on paths of different lengths.
 * 
 * Time complexity : O(V^3) - which is ideal for graph no larger than a 
 * couple hundred nodes.
 * 
 * Space complexity : O(V^2)
 * 
 * 
 * Can detect negative cycles. 
 * 
 * NOTE: 
 * -> It is assumed that the distance from a node to itself is zero
 *    This is why the diagonal is all zeros.
 * -> If there is no edge from node i to j then set the edge value from
 * 	  m[i][j] to be positive infinity like here 1234 etc.
 * -> Taking Integer.MAX_VALUE or min as infinite will cause integer overflow,
 * 	  therefore, prefer to use a large constant such as 10^7 instead.
 */

public class FloydWarshal {

	public static int[][] floydWarshal(int[][] graph,int next[][]) {
		int n = graph.length;
		
		int dp[][] = new int[n][n];
		//The memo table that will contain ASPS solution
		
		setup(graph,dp,next);
		
		printa(next);
		
		int i,j,k;
		
		for(k=0;k<n;k++)
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					if(dp[i][k] + dp[k][j] < dp[i][j]) {
						dp[i][j] = dp[i][k] + dp[k][j];
						next[i][j] = next[i][k];
					}
		
		
		//Detect and propagate negative cycles. (optional)
		//Execute FW APSP algorithm a second time but this time 
		// if the distance can be improved 
		//Setting the optimal distance to be -infinity
		//Every edge(i,j) marked with -infinity is either
		//part of or reaches into a negative cycle
		for(k=0;k<n;k++)
			for(i=0;i<n;i++)
				for(j=0;j<n;j++)
					if(dp[i][k] + dp[k][j] < dp[i][j]) {
						dp[i][j] = 1234;
						
						//setting next value with -1 to indicate that the edge
						//is affected
						next[i][j] =-1;
					}
		
		printa(next);
		return dp;
		
		
	}
	
	public static void setup(int[][] graph, int[][] dp, int[][] next) {
		int n = graph.length;
		for(int i=0; i<n;i++)
			for(int j=0;j<n;j++) {
				dp[i][j] = graph[i][j];
				//Deep copy of the input matrix and setup
				
				if(graph[i][j] != 1234)
					next[i][j] = j;
			}
	}
	
	public static ArrayList<Integer> reconstructPath(int[][] graph, int start, int end) {
		int next[][] = new int[graph.length][graph.length];
		//Matrix used to reconstruct shortest path
		
		int[][] dp = floydWarshal(graph,next);
		
		ArrayList<Integer> path = new ArrayList<>();
		
		//Check if there exists a path between start and end nodes
		if(dp[start][end] == 1234) return path;
		
		int at = start;
		for(;at!=end; at = next[at][end])
			{if(at==-1) return null;
			//This means that a negative cycle is encountered
			 
			 path.add(at);
			}
		if(next[at][end] == -1) return null;
		path.add(end);
		return path;
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("Enter the number of nodes");
		Scanner inp = new Scanner(System.in);
		int n= inp.nextInt();
		int[][] graph = new int[n][n];
			int INF = 1234;
		String enter;
		for(int i=0;i<n;i++)
			for(int j =0 ; j<n;j++) {
				System.out.println("Edge weight from "+ i + " to " + j);
				enter = inp.next();
				if(enter.equals("n")) { 
					graph[i][j] = INF;
					continue;}
				graph[i][j] = Integer.valueOf(enter);
			}
		
		for(int x : reconstructPath(graph,1,3))
			System.out.println(x);

	}
	
	public static void printa(int[][] m) {
		System.out.println("\n\n");
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m.length;j++)
				System.out.print(m[i][j] + ",");
			System.out.println();
		}
		System.out.println("\n\n");
	}

}


