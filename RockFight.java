/*
 * Collaborated ideas with Madhav Narayan
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RockFight {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		int numTests = input.nextInt();

		for(int i = 0; i < numTests; i++){

			int numRocks = input.nextInt();

			ArrayList<Integer> ruth = new ArrayList<Integer>();
			ArrayList<Integer> bleminda = new ArrayList<Integer>();

			for (int j = 0; j < numRocks; j++) {
				ruth.add(input.nextInt());
			}
			for (int k = 0; k < numRocks; k++) {
				bleminda.add(input.nextInt());
			}

			System.out.println(getMinimumLoss(ruth, bleminda));	
		}
		input.close();
	}

	private static int getMinimumLoss(ArrayList<Integer> ruth, ArrayList<Integer> bleminda) {
		int totalLoss = 0;

		boolean[] ruthRockUsed = new boolean[ruth.size()];
		Collections.sort(ruth); 

		for (int i = 0; i < ruth.size(); i++) {
			int blemindaRock = bleminda.get(i);
			int ruthRock = getRuthRock(ruth, ruthRockUsed, blemindaRock);			
			if (ruthRock < blemindaRock) {
				totalLoss += ruthRock;
			}
		}
		return totalLoss;
	}

	private static int getRuthRock(ArrayList<Integer> ruth, boolean[] ruthRockUsed, int blemindaRock) {

		boolean found = false;

		for (int i = 0; (i < ruth.size()) && !found; i++) {
			if ((ruth.get(i) >= blemindaRock) && !ruthRockUsed[i]) {
				ruthRockUsed[i] = true;
				return ruth.get(i);
			}
		}
		for (int i = 0; i < ruth.size(); i++) {
			if (!ruthRockUsed[i]) {
				ruthRockUsed[i] = true;
				return ruth.get(i);
			}
		}
		return -1;
	}
}
