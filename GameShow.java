import java.util.Scanner;

public class GameShow {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int test = sc.nextInt();
		
		for(int t = 0; t < test; t++){
			int num = sc.nextInt();
			int[] dp = new int[num + 1];
			dp[1] = 0; // 1 has 0 primes

			for(int i = 2; i < dp.length; i++){
				dp[i] = -1; // initlize all to -1
			}
			// dp[1] == number of prime factors of 1
			// dp[3] == number of prime factors of 3
			// dp[n] == number of prime factors of n
			for(int i = 0; i < dp.length; i++){
				if(dp[i] == -1){
					int init = 2;
					dp[i] = 1;
					int temp = i * init;
					while(temp < dp.length){
						if(dp[temp] == -1){ // if seen first time, then it has its first prime factor
							dp[temp] = 1;
						} else {
							dp[temp] += 1; // more than one prime factor
						}
						init++;
						temp = i * init;
					}
				}
			}
			// getting answer, summing over array
			long sum = 0;
			for(int i = 1; i < dp.length; i++){
				sum += dp[i];
			}
			System.out.println(sum);
		}
	}
}
