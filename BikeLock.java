import java.util.Scanner;

public class BikeLock {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for(int i = 0; i < t; i++){
			char[] comb = sc.next().toCharArray();
			long[] count = new long[1];
			
			// take input
			helper(comb, count, getNextEmpty(comb, 0));
			System.out.println((count[0] + 1) % 1000000007); // print answer
		}
		
	}
	
	// recursive backtracking to try and fill ? spots
	// slow but correct :P
	private static void helper(char[] comb, long[] count, int currentIndex){
		
		if(comb.length != currentIndex){
			
			if(comb[currentIndex] == '?'){
				for(int i = 1; i <= 9; i++){
					String temp = i + "";
					comb[currentIndex] = temp.charAt(0);
					
					if(nonDecreasing(comb)){
						count[0]++;
						helper(comb, count, getNextEmpty(comb, currentIndex));
					}
					comb[currentIndex] = '?';
				}
				count[0]--;
			}
			
		}
		
		
	}
	
	// get the next spot to fill
	private static int getNextEmpty(char[] comb, int currentIndex){
		for(int i = currentIndex; i < comb.length; i++){
			if(comb[i] == '?'){
				return i;
			}
		}
		return comb.length;
		
		
	}
	
	// verify if this condition is maintained
	private static boolean nonDecreasing(char[] comb){
		
		for(int i = 0; i < comb.length - 1; i++){
			if(comb[i] != '?' && comb[i + 1] != '?'){
				if(Character.getNumericValue(comb[i]) > Character.getNumericValue(comb[i + 1]) ){
					return false;
				}
			}
		}
		return true;
	}

}
