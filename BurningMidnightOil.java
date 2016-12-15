import java.util.Scanner;

public class BurningMidnightOil {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		
		method(sc);
	}
	
	static void method(Scanner sc){
		
		long n = sc.nextLong();
		int k = sc.nextInt();
		
		boolean found = false;
		int c = 0;
		int requiredC = k;
		
		
		while(!found){
			int exp = (int) Math.pow(k, c);
			
			if((n / exp) == 0){
				found = true;
				requiredC = c;
			}
			c++;
		}

		
		//System.out.println("this is the exponent of k that caues (n / k^x) == 0: " + requiredC);
		
		// wanna compute till (x - 1)
		
		boolean computed = false;
		
		long finalVal = 0;
		long result = 0;
		long highResult = 0;
		long lowResult = 0;
		long v = 0;
		long requiredV = 0;
		long resultantV  = 0;
		
		long low = 0;
		long high = n - 1;
		
//		while(low <= high){
//			long mid = (high + low) / 2;
//			midResult = 0;
//			lowResult = 0;
//			highResult = 0;
//			for(int i = 0; i < requiredC; i++){
//				midResult = midResult + (mid / (long) Math.pow(k, i));
//			}
//			for(int i = 0; i < requiredC; i++){
//				lowResult = lowResult + (low / (long) Math.pow(k, i));
//			}
//			for(int i = 0; i < requiredC; i++){
//				highResult = highResult + (high / (long) Math.pow(k, i));
//			}
//			
//			if(midResult < (lowResult + highResult) / 2){
//				high = mid - 1;
//			} else if(midResult > (lowResult + highResult) / 2){
//				low = mid + 1;
//			} else {
//				System.out.println("mid: " + mid);
//			}
//			
//		}
		
		
		while(!computed){
			
			result = 0;

			
			if(result >= n){
				v = v / 2;
				finalVal = result;
			} else {
				computed = true;
			}
		}
		

		
		final long startTime = System.currentTimeMillis();
		// if gone below, answer that means we went to far
		// have to increment one by one up to get requried answer
		boolean done = false;
		while(!done){
			result = 0;
			for(int i = 0; i < requiredC; i++){
				result = result + (v / (long) Math.pow(k, i));
			}
			if(result < n){
				v++;
			} else {
				done = true;
			}
			
		}
		
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) );

		

		
		
		System.out.println(v);
		
		
	}


}
