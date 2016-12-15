import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CubeWalls {

	
	public static void main(String[] args){
		
//		Scanner sc = new Scanner(System.in);
//		int n = sc.nextInt();
//		int w = sc.nextInt();
//		StringBuilder bearTower = new StringBuilder();
//		StringBuilder elephantTower = new StringBuilder();
//		for(int i = 0; i < n; i++){
//			bearTower.append(sc.nextInt());
//		}
//		
//		
//		for(int i = 0; i < w; i++) {
//			elephantTower.append(sc.nextInt());
//		}
		
		//KMPSearch("34432", "24554322223321");
		System.out.println(kmp("34432", "23443222234432"));

		// general idea
		// linearly search thru main string
		// check differences if there is a difference stop the loop and shift to the first 
		// Occurrence of error, if no error shift to the next place that hasnt been checked yet
		// use: worst O(N^2)
		// average -- almost N^2 but not neceesasrily 
		
		
	}
	
    private static void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
 
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0;  // index for pat[]
 
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat,M,lps);
 
        int i = 0;  // index for txt[]
        while (i < N)
        {
            if (pat.charAt(j)  == txt.charAt(i))
            {
                j++;
                i++;
            }
            if (j == M)
            {
                System.out.println("Found pattern "+
                              "at index " + (i-j));
                j = lps[j-1];
            }
 
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i))
            {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j-1];
                else
                    i = i+1;
            }
        }
    }
 
    private static void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0;  // lps[0] is always 0
 
        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M)
        {
            if (pat.charAt(i) == pat.charAt(len))
            {
                len++;
                lps[i] = len;
                i++;
            }
            else  // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar 
                // to search step.
                if (len != 0)
                {
                    len = lps[len-1];
 
                    // Also, note that we do not increment
                    // i here
                }
                else  // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
    
    private static int kmp(String pattern, String text) {
    	
    	int count = 0;
        // Build failure function
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
        List<Integer> matches = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (j < text.length()) {
            if (text.charAt(j) == pattern.charAt(i)) {
                i++; j++;
                if (i == pattern.length()) {
                    // We found a match
                    count++;
                    i = 0;
                }
            } else if (i > 0) {
                i = failure[i];
            } else {
                j++;
            }
        }

        return count;
    }
    
    
}
