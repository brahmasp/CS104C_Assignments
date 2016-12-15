import java.util.*;



public class midnightOil {
	public static void main(String[] args){

		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		
		if(k > n){
			System.out.println(n);
		}
		int low = 1;
		int high = n;
		int mid = 0;
		
		while (low < high) {
			mid = (high + low) / 2;
			
			long val = sum(mid, k);
			if (val > n) {
				high = mid - 1;
			} else if (val <= n) {
				low = mid + 1;
			}
		}
		long temp = sum(mid, k);
		
		
		while ((temp >= n)) {
			mid--;
			temp = sum(mid, k);
		}
		while ((temp < n)) {
			mid++;
			temp = sum(mid, k);
		}
		mid--;
		System.out.println(mid + 1);
	}

	public static int sum(int x,int y){
		int i = x;
		while(x > .01){
			x = x / y;
			i = i + x;
		}
		return i;
	}

}