package bitManipulation;

public class bitwise {
//Set bit at nth position
//Unset bit at nth position
//Toggle bit at nth position
//check set or not
//strip last set bit
//lowest set bit
//highest set bit
//sum of two intger
//reverse bit
//rangebitwiese and

	public static void main(String[] args) {
		int n=34356;
		System.out.println(highestSetBit(n));
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(-n));
		System.out.println(Integer.toBinaryString(lowestSetBit(n)));
		System.out.println(lowestSetBit(n));
		System.out.println(Integer.toBinaryString(onesComplement(n)));
		
		//Toggling
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(toggle(n,Integer.toBinaryString(n).length()))+"\n");
		
		//Strip last set bit
		System.out.println(Integer.toBinaryString(n));
		System.out.println(Integer.toBinaryString(n-1));
		System.out.println(Integer.toBinaryString(stripLastSetbit(n)));
		
	}
	
	static int set(int n, int pos) {
		//If we want to set a bit at nth position in 
		//number ‘num’ ,it can be done using ‘OR’ operator( | ).
		return n| (1 <<pos);
	}
	
	static int unset(int n, int pos) {
		//Suppose we want to unset a bit at nth position in number ‘num’ 
		//then we have to do this with the help of ‘AND’ (&) operator
		return n&(~(1<<pos));
	}
	
	static int toggle(int n, int pos) {
		//Toggling means to turn bit ‘on'(1) if it was ‘off'(0) and to turn ‘off'(0) if it 
		//was ‘on'(1) previously.We will be using ‘XOR’ operator here which is this ‘^’
		
		return n^((1<<pos)-1);
	}
	
	static boolean checkSet(int n, int pos) {
		// Checking if bit at nth position is set or unset
		return n == set(n,pos);
	}
	
	static int onesComplement(int n){
		//Inverting every bit of a number/1’s complement
		return ~n;
	}
	
	static int twosComplement(int n) {
		// Two’s complement of the number
		//So formally we can have 2’s complement by finding 1s complement and adding 1 to the result i.e (~num+1) 
		//or what else we can do is using ‘-‘ operator
		return (~n+1);
	}
	
	static int stripLastSetbit(int n) {
		//Stripping off the lowest set bit :
		//In many situations we want to strip off the lowest set bit for example in Binary 
		//Indexed tree data structure, counting number of set bit in a number.
		return n & ( n-1);
	}
	
	static int lowestSetBit(int n) {
		//Getting lowest set bit of a number
		//The return 2 raised to the power of lowest bit position
		return n & ~(n-1);
		//return n&-n;
		//return x^(x&(x-1))
		// or return n & (~ n + 1);
		// or return n & twoscomplement(n);
	}
	
	static int highestSetBit(int n) {
		//Getting Highest set bit of a number
		//The return 2 raised to the power of highest bit position
			
		return 1<<(Integer.toBinaryString(n).length()-1);
		
		
		/*
		 * n = n | (n>>1);
		 * n = n | (n>>2);
		 * n = n | (n>>4);
		 * n = n | (n>>8);
		 * n = n | (n>>16);
		 * return (n+1)>>1;
		 */
	}
	
	static int sumOfTwoIntegers(int a, int b) {
		return b==0 ? a : sumOfTwoIntegers(a^b, (a&b)<<1);
		
	}
	
	static int reverseBits(int n) {
		int res=0, mask = 1 <<31;
		for(int i=0;i<32;i++) {
			if((n&1) ==1 ) res |= mask;
			if((mask & n)>0 )  res |= 1;
			mask <<=1 ;
		}
		return res;
		//TOPCODER BLOG
		
//		int mask =1,res=0;
//		for(int i=0;i<32;i++) {
//			res <<=1;
//			if((mask&n)>0) res |= 1;
//			mask <<=1 ;
//		}
//		return res;
		
		
//		x = ((x & 0xaaaaaaaa) >> 1) | ((x & 0x55555555) << 1);
//		x = ((x & 0xcccccccc) >> 2) | ((x & 0x33333333) << 2);
//		x = ((x & 0xf0f0f0f0) >> 4) | ((x & 0x0f0f0f0f) << 4);
//		x = ((x & 0xff00ff00) >> 8) | ((x & 0x00ff00ff) << 8);
//		x = ((x & 0xffff0000) >> 16) | ((x & 0x0000ffff) << 16);
		
	}
	
	static int rangeBitwiseAnd(int m, int n) {
		//Bitwise AND of numbers range
		int a=0;
		while(m != n) {
			m >>= 1;
			n >>= 1;
			a++;
		}
		return m<<a;
	}
	
}
