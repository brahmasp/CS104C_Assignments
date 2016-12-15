import java.util.Scanner;

public class Subtractions {

	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		for(int i = 0; i < n; i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			int max = Math.max(a, b);
			int min = Math.min(a, b);
			
			int ans = 0;
			int rem = 0;
			
			while(min > 1 && max > 1){
				if(max > min){
					ans += (max / min);
					if(max > min){
						max = max % min;
					}
				} else {
					ans += (min / max);
					min = min % max;
				}
				
			}
			
			if(min != 0 && max != 0){
				ans += (Math.max(max, min) / Math.min(max, min));
			}
			System.out.println(ans);
			
			
		}
		
		
	}
}
