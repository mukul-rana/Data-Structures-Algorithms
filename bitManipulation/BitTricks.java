package bitManipulation;


//Clear all bits from LSB to ith bit (pos th bit)
// Clearing all bits from MSB to i-th bit
//Upper case English alphabet to lower case
//Lower case English alphabet to upper case
//Find log base 2 of 32 bit integer
//ispower2
//Direct XOR of all numbers from 1 to n
//swap

public class BitTricks {
	public static void main(String[]args) {
		System.out.println(Integer.toBinaryString(20)+"\n\n\n");
		System.out.println(clearBits(29,2));
		System.out.println(Integer.toBinaryString(29) + " "  + Integer.toBinaryString(clearBits(29,2)));
		System.out.println(clearBits2(215,3));
		System.out.println(Integer.toBinaryString(215) + " "  + Integer.toBinaryString(clearBits2(215,3)) + "\n");
		
		System.out.println(toUpperCase('a'));
		System.out.println(Integer.toBinaryString('_'));
		System.out.println(Integer.toBinaryString('a') + " " + Integer.toBinaryString(toUpperCase('a')) + "\n");
		
		System.out.println(toLowerCase('B'));
		System.out.println(Integer.toBinaryString(' '));
		System.out.println(Integer.toBinaryString('B') + " " + Integer.toBinaryString(toLowerCase('B')) + "\n");
		
		
	}
	
	static int clearBits(int n, int pos) {
		//Clear all bits from LSB to ith bit (pos th bit)
		
		return n & ~((1<<pos+1) -1);
	}
	
	static int clearBits2(int n, int pos) {
		// Clearing all bits from MSB to i-th bit
		return n & ((1<<pos)-1);
	}
	
	static int doubleIt(int n) {
		return n<<1;
	}
	
	static int hajfIt(int n) {
		return n>>1;
	}
	
	static char toLowerCase(char c) {
		//Upper case English alphabet to lower case
		return (char) (c|' ');
	}
	
	static char toUpperCase(char c) {
		 //Lower case English alphabet to upper case
		return (char) (c&'_');
	}
	
	
		
	
	
	static int log2(int n) {
		//Find log base 2 of 32 bit integer
		int res =0;
		while((n >>= 1) > 0) 
			res++;
		//We right shift x repeatedly until it becomes 0, meanwhile 
		//we keep count on the shift operation. This count value is the log2(x).
		
		return res;
	}
	
	static boolean isPowerOf2(int n) {
		return (n >0 )&& ((n & n-1) == 0);
		// All the power of 2 have only single bit set e.g. 16 (00010000). If we minus 1 from this, 
		//all the bits from LSB to set bit get toggled, i.e., 16-1 = 15 (00001111). 
		//Now if we AND x with (x-1) and the result is 0 then we can say 
		//that x is power of 2 otherwise not. We have to take extra care when x = 0.
	}
	
	static int computeXOR(int n) {
		
		//Direct XOR of all numbers from 1 to n
		
		if(n%4==0) return n;
		if(n%4==1) return 1;
		if(n%4==2) return n+1;
		return 0;
		
	}
	
	static void swap(int a, int b) {
		a ^=b;
		b ^=a;
		a ^=b;
	}
}
