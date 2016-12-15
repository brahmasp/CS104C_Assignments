import java.util.Scanner;

public class HSLG {
	
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
		
		int count = 0;
		
		for(int i = 1 ; i <= n ; i++){
			int cordX = points[i].x;
			int cordY = points[i].y;
			if(!spotsVisited[i]){ // if not considered
				count++;
			}
			spotsVisited[i] = true; // considered
			
			// find all the ones not considered
			// and look at their slopes
			
			// m = (y2 - y1) / (x2 - x1)
			// but if equal then m = (y3 - y1)/(x3 - x1)
			// so for equaliy
			// (x2 - x1) * (y3 - y1) == (y2 - y1) * (x3 - x1)
			// x1,y1 == gun
			for(int j = i + 1 ; j <= n ;j++){
				int tempCordX= points[j].x;
				int tempCordY = points[j].y;
				
				if(!spotsVisited[j] && (tempCordX - gun.x) * (cordY - gun.y) == (cordX - gun.x) * (tempCordY - gun.y)){
					spotsVisited[j] = true;
				}
			}
		}
		System.out.println(count);
	}
	
	private static class Point {
		int x;
		int y;
	}

}
