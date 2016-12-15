import java.util.Scanner;

public class MultiplicationTable
{

    public static void main(String [] args) {
    	Scanner sc = new Scanner(System.in);
    	long n = sc.nextLong();
    	long m = sc.nextLong();
    	long k = sc.nextLong();
    	
    	long lo = 1;
    	long hi = n * m + 1 ;
    	while(lo < hi) {
    		long mid = lo + (hi - lo) / 2;
    		if(numSoFar(mid, n, m) < k)
    		{
    			lo = mid + 1 ;
    		}
    		else {
    			hi = mid;
    		}
    	}
    	System.out.println(hi);
    }


    private static long numSoFar(long a, long n , long m) {
    	long r = 0 ;
    	for(int i = 1; i <= n; i++) {
    		r += Math.min(m, a / i) ;
    	}
    	return r ;
    }
}