import java.util.Scanner;




public class BinaryNumbers {
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		char[] num = str.toCharArray(); 
		
		
		int counter = 0;
		int i = str.length() - 1;
		while(i >= 1) { 
			while(i >= 0 && num[i] == '0') {
				counter++; 
				i--;
			}
			
			if(i >= 1 && num[i] == '1') {
				counter++;
				while(i >= 0 && num[i] == '1' ) {
					counter++;
					i--;
				}

				if(i >= 0 && num[i] == '0') {
					num[i] = '1';
				}
			}
		}		
		System.out.println(counter);
	}


}