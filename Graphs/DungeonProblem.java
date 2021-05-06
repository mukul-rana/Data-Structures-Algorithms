package Graphs;

import java.util.ArrayDeque;
import java.util.Scanner;



public class DungeonProblem {

	public static void main(String[] args) {
		int R = 5,C=11;//Number of rows and columns
		char[][] m = {{'.','.','.','.','#','.','.','.','.','.','.'}, //Input character matrix of size R x C
				{'S','.','#','.','.','.','#','.','.','.','#'},
				{'#','.','#','.','.','.','.','.','#','.','#'},
				{'.','.','.','.','#','#','.','#','#','.','.'},
				{'.','.','.','.','.','#','.','.','.','.','E'}}; 
//		Scanner inp = new Scanner(System.in);
//		int R,C;
//		String t;
//		R=inp.nextInt(); C=inp.nextInt();
//		System.out.println("Now enter the map");
//		
//		char[][] m = new char[R][C];
//		for(int i=0;i<R;i++) {
//			for(int j=0;j<C;j++) {
//				System.out.print("ENter");
//				t=inp.nextLine();
//				m[i][j] = t.charAt(0);}
//		System.out.println("Next line")	;
//		}
		
		
		
		int sr=1,sc=0; //Starting point index
		ArrayDeque<Integer> rq = new ArrayDeque<>();
		ArrayDeque<Integer> cq = new ArrayDeque<>();
		
		//Variables used to track the number of steps taken
		int move_count=0;
		int nodes_left_in_layer = 1;
		int nodes_in_next_layer = 0;
			
		//Variale used to track whether the 'E' character 
		//ever gets reached during the BFS
		boolean reached_end = false;
		
		//R x C matrix of false values used to track whether the node at position
		// (i,j) has been visited
		boolean[][] visited = new boolean[R][C];
		
		//North, south, east and west direction vectors
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,1,-1};
		int r,c,rr,cc;
		rq.addFirst(sr);
		cq.addFirst(sc);
		visited[sr][sc] = true;
		
		tuple[][] at = new tuple[R][C];
		for(int i=0;i<R;i++)
			for(int j=0;j<C;j++)
			at[i][j] = new tuple(-1,-1);
		
		tuple fin =new tuple(-1,-1);
		
		while(!cq.isEmpty()) {
			r = rq.pollLast();
			c = cq.pollLast();
			if( m[r][c] == 'E') {reached_end = true;  fin = new tuple(r,c); break;}
			
			
			for(int i=0;i<4;i++) {
				rr = r + dr[i];
				cc = c + dc[i];
				
				if(rr<0 || cc<0) continue;
				if(rr>=R || cc>=C) continue;
				if(visited[rr][cc]) continue;
				if(m[rr][cc] == '#') continue;
				
				//for(int j=0;j<R;j++)
					//for(int k=0;k<C;k++)
						
				at[rr][cc] = new tuple(r,c);
				rq.addFirst(rr);
				cq.addFirst(cc);
				visited[rr][cc] = true;
				nodes_in_next_layer++;
			}
			
			nodes_left_in_layer--;
			if(nodes_left_in_layer == 0) {
				nodes_left_in_layer =nodes_in_next_layer;
				nodes_in_next_layer=0;
				move_count++;
			}
		}	
			if(reached_end)  System.out.println(move_count);
			for(int i=0;i<R;i++) {
				for(int j=0;j<C;j++)
					System.out.print("( " + at[i][j].x +  " , " + at[i][j].y + " ) ->" );
				System.out.println();
			}
		System.out.println("\n\n");
			while( fin.x!=-1) {
				System.out.println(fin);
				fin = at[fin.x][fin.y];
			}
			
	}
}


class tuple{
	int x, y;
	tuple(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "tuple [x=" + x + ", y=" + y + "]";
	}
	
}