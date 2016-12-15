import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class ViewAngles {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();

		Point[] mans = new Point[n];
		
		double[] angles = new double[n];
		for(int i = 0; i < mans.length; i++){
			
			mans[i] = new Point();
			mans[i].x = sc.nextInt();
			mans[i].y = sc.nextInt();
			if(mans[i].x == 0){
				if(mans[i].y > 0){
					angles[i] = 90.0;
				} else {
					angles[i] = 270;
				}
			} else if(mans[i].y == 0){
				if(mans[i].x > 0){
					angles[i] = 0;
				} else {
					angles[i] = 180;
				}
			} else {
				double angle = Math.toDegrees(Math.atan((double)(mans[i].y) / (double) mans[i].x));
				angles[i] = angle;
				if(mans[i].x < 0 && mans[i].y > 0){ // 2nd quad
					angles[i] += 180;
				}
				if(mans[i].x < 0 && mans[i].y < 0){ // 2nd quad
					angles[i] = Math.abs(angles[i]) + 180;
				}
				if(mans[i].x > 0 && mans[i].y < 0){ // 2nd quad
					angles[i] += 360;
				} 
			}
		}
		Arrays.sort(angles);
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		double tempMin = angles[0];
        for(int i = 0; i < n; i++) {
            if(angles[n - i - 1] != tempMin){
                if(Math.abs(angles[n - i - 1] - tempMin) < min)
                {
                    min=Math.abs(angles[n - i - 1] - tempMin);
                }
                tempMin = angles[n - i - 1] - 360;
            }


            //System.out.println(min+" "+Math.abs(m[n-i-1]-tmp)+" "+m[n-i-1]+" "+tmp);
            
            
        }
		if(n == 1 || min == Double.MAX_VALUE){
			System.out.println("0.000000");
		} else {
			System.out.printf("%.10f\n", min);
		}
	}
	
	public static class Point {
		double x;
		double y;
		double slope; // since other point is just the origin
		double incline;
		
	}

}
