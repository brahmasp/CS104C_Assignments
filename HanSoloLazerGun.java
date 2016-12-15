import java.util.Arrays;
import java.util.Scanner;

public class HanSoloLazerGun {
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		Point gun = new Point();
		gun.x = sc.nextInt();
		gun.y = sc.nextInt();
		
		boolean[] spotsVisited = new boolean[n + 1];
		Point[] points = new Point[n + 1];
		
		for(int i = 1; i <= n; i++){
			Point p = new Point();
			p.x = sc.nextInt();
			p.y = sc.nextInt();
			
			points[i] = p;
		}
		
		getCount(gun, points, spotsVisited);
		
		
		
		
	}
	
	private static void getCount(Point gun, Point[] points, boolean[] spotsVisited){
		
		int count = 0;
		
		int startingSlope = 0;
		
		if((gun.x - points[1].x)  == 0){
			startingSlope = Integer.MAX_VALUE;
		} else {
			startingSlope = (gun.y - points[1].y) / ((gun.x - points[1].x));
		}
		count++;
		spotsVisited[1] = true;
		
		boolean[] temp = new boolean[spotsVisited.length];
		for(int i = 1; i < temp.length; i++){
			temp[i] = true;
		}
		while(!Arrays.equals(spotsVisited, temp)){
			for(int i = 2; i < spotsVisited.length; i++){
				int slope = 0;
				if(!spotsVisited[i]){
					if((gun.x - points[i].x)  == 0){
						slope = Integer.MAX_VALUE;
					} else {
						slope = (gun.y - points[i].y) / ((gun.x - points[i].x));
					}
					
					if(startingSlope == slope){
						spotsVisited[i] = true;
					}
				}
			}
			int index = 1;
			boolean found = false;
			for(int i = 1; i < spotsVisited.length; i++){
				if(!spotsVisited[i]){
					index = i;
					found = true;
					spotsVisited[i] = true;
					break;
				}
			}
			
			if(found){
				if((gun.x - points[index].x)  == 0){
					startingSlope = Integer.MAX_VALUE;
				} else {
					startingSlope = (gun.y - points[index].y) / ((gun.x - points[index].x));
				}
				count++;
			}
		}
		
		System.out.println(count);
		
		
		
		
		
		
		
	}
	
	private static class Point {
		int x;
		int y;
	}

}
