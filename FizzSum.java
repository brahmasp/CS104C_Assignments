import java.util.Scanner;

public class FizzSum {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		fizzSum(sc);
	}

	private static void fizzSum(Scanner sc){
		while(sc.hasNextLong()){
			long a = sc.nextLong();
			long b = sc.nextLong();
			long total = 0;

			for(long i = a; i <= b; i++){
				if(i % 3 == 0 && i % 5 == 0){
					total += (i * i);
				} else if(i % 3 == 0){
					total += (i + 3);
				} else if(i % 5 == 0){
					total += (i + 5);
				}
			}
			System.out.println(total);
		}
	}
}
