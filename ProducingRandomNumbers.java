import java.util.Random;

public class ProducingRandomNumbers {
	
	public static void main(String[] args){
		
		Random rand = new Random();
		

		
		for(int i = 0; i < 100; i++){
			int randomStartingPoint = rand.nextInt((1000000) + 1);
			int b = rand.nextInt((1000000 - randomStartingPoint) + 1) + randomStartingPoint;
			int a = rand.nextInt((b) + 1);
			System.out.println(a + " " + b);
		}
		
		
	}

}
