import java.util.Random;
import java.util.Scanner;

public class Robot {
	
	public static void main(String[] args){
		
			
		// abcabcabc - {a,b,c} rep =3,  {abc}.size * 3 == s.length()
		//madamimadam - {m,a,d,i} rep = 
		
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < t; i++){
			
			StringBuilder s = new StringBuilder(sc.nextLine());
			StringBuilder sub = new StringBuilder(s.length());
			boolean maxFound = false;
			int currentMax = 1;
			
			// try tricks like if s.length % 2 == 0, then look at only even substrings
			// skips half the set
			// samething for odd
			// also dont cross past half, because 6 letters cant repeat to make a total of 10 (if s.length() == 10)
			for(int j = 0; j < s.length() && !maxFound; j++){
				sub.append(s.charAt(j));
				if(sub.length() <= s.length() / 2 ){
					int val = kmp(sub, s); // check if sub can be used to recreate the original string
					if(val * sub.length() == s.length()){
						currentMax = Math.max(currentMax, val);
						maxFound = true;
					}
				} else {
					break;
				}
					
			}
			System.out.println(currentMax);
		}
	}
	
   
 
    private static int kmp(StringBuilder pattern, StringBuilder text) {
        // Build failure function
    	int counter = 0;
        int[] failure = new int[pattern.length() + 1];
        failure[0] = 0;
        failure[1] = 0;
        for (int i = 2; i <= pattern.length(); i++) {
            int j = failure[i - 1];
            while (true) {
                if (pattern.charAt(j) == pattern.charAt(i - 1)) {
                    failure[i] = j + 1;
                    break;
                } else if (j == 0) {
                    failure[i] = 0;
                    break;
                } else {
                    j = failure[j];
                }
            }
        }

        // Match against text
        int i = 0;
        int j = 0;
        while (j < text.length()) {
            if (text.charAt(j) == pattern.charAt(i)) {
                i++; j++;
                if (i == pattern.length()) {
                    // We found a match
                	counter++;
                    i = 0;
                }
            } else if (i > 0) {
                i = failure[i];
            } else {
                j++;
            }
        }

        return counter;
    }
    
    
    private static void getRandomString(){
    	
    	
		String s = "";
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		for(int i = 0; i < 80000; i++){
			
			sb.append((char)('a' + r.nextInt(26)));
		}
		
		System.out.println(sb);
    }

}
