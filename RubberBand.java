import java.util.Arrays;
import java.util.Scanner;


public class RubberBand {

	public static void main(String[] args){

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		for(int i = 0; i < t; i++){
			int n = sc.nextInt();
			Point[] points = new Point[n];

			for(int j = 0; j < n; j++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				Point p = new Point();
				p.x = x;
				p.y = y;
				points[j] = p;
			}
			if(points.length < 3){
				for(int k = 0; k < points.length; k++){
					System.out.print(k + " ");
				}
			} else {
				boolean[] vertices = new boolean[n];
				jarvis(points, vertices);
			}

			System.out.println();
		}
	}

	private static void jarvis(Point[] points, boolean[] vertices){

		int numPoints = points.length;
		int[] remainingPoints = new int[numPoints];
		
		int extremeEnd = 0;
		// get extreme end, left most
		for (int i = 1; i < numPoints; i++){
			if (points[i].x < points[extremeEnd].x){
				extremeEnd = i;
			}
		}
		
		// perform jarvis march
		int pointOne = extremeEnd;
		int pointTwo = -1;
		do {
			pointTwo = (pointOne + 1) % numPoints;
			for (int pointThree = 0; pointThree < numPoints; pointThree++){
				boolean counterClock = ((points[pointThree].y - points[pointOne].y) * (points[pointTwo].x - points[pointThree].x) - (points[pointThree].x - points[pointOne].x) * (points[pointTwo].y - points[pointThree].y)) < 0 ? true : false;
				pointTwo = counterClock ? pointThree : pointTwo;
			}

			remainingPoints[pointOne] = pointTwo;  
			vertices[pointOne] = true;
			pointOne = pointTwo; 
		} while (pointOne != extremeEnd);

		// print vertiices of the polygon formed
		for (int k = 0; k < remainingPoints.length; k++){
			if (vertices[k]){
				System.out.print(k + " ");
			}  
		}
	}
	private static class Point {
		int x;
		int y;
	}
}
