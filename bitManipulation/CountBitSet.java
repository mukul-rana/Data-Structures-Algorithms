package bitManipulation;

public class CountBitSet {
	// Counting bits in a number using Look up table with Time complexity O(1)
    static int[] BitsSetTable256 = new int[256];
 
    // Function to initialise the lookup table
    public static void initialize()
    {
 
        // To initially generate the
        // table algorithmically
        BitsSetTable256[0] = 0;
        for (int i = 0; i < 256; i++) {
            BitsSetTable256[i] = (i & 1) + BitsSetTable256[i / 2];
        }
    }
 
    // Function to return the count
    // of set bits in n
    public static int countSetBits(int n)
    {
        return (BitsSetTable256[n & 0xff]
                + BitsSetTable256[(n >> 8) & 0xff]
                + BitsSetTable256[(n >> 16) & 0xff]
                + BitsSetTable256[n >> 24]);
    }
 
    // Driver code
    public static void main(String[] args)
    {
 
        // Initialise the lookup table
        initialize();
        int n = 255;
        System.out.println(countSetBits(n));
        System.out.println(Integer.toBinaryString(n));
        System.out.println(count(n));
    }
    
        
    static int count(int n) {
    	return n==0   ? 0 :  (n&1) + count(n/2);
    }
    
    static int countSetBits2(int n) {
		//Brian Kernighan's algorithm ( O (logn))
		//Count Set (1) bits in integer
		
		/*
		 * Subtracting 1 from a decimal number flips all the bits after the rightmost 
		 * set bit(which is 1) including the rightmost set bit. 
			for example : 
			10 in binary is 00001010 
			9 in binary is 00001001 
			8 in binary is 00001000 
			7 in binary is 00000111 
			So if we subtract a number by 1 and do bitwise & with itself 
			(n & (n-1)), we unset the rightmost set bit. If we do n & (n-1) in
			 a loop and count the no of times loop executes we get the set bit count. 
			The beauty of this solution is the number of times it loops is equal
			 to the number of set bits in a given integer
		 */
		int count=0;
		while(n>0) {
			n &= (n-1);
			count++;
		}
		return count;
	}

}







