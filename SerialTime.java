import java.util.*;


public class SerialTime {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String dim = sc.nextLine();
        Scanner scanner = new Scanner(dim);
        int layers = scanner.nextInt();
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        boolean[][][] plate = new boolean[row][col][layers];
        for(int h = 0; h < layers; h++){
            for(int r = 0; r < row; r++){
                char[] ch = sc.next().toCharArray();
                for(int c = 0; c < col; c++){
                	if(ch[c] == '.'){
                		plate[r][c][h] = true;
                	}
                }
            }
        }
        int[] xDir = {1, -1, 0, 0, 0, 0};
        int[] yDir = {0, 0, 1, -1, 0, 0};
        int[] zDir = {0, 0, 0, 0, 1, -1};
        int dir = 6;
        int[] start = new int[2];
        start[0] = sc.nextInt();
        start[1] = sc.nextInt();
        Queue<Spot> spotsToVisit = new LinkedList<>();
        int timeTaken = 1;
        spotsToVisit.add(new Spot(start[0] - 1, start[1] - 1, 0));
        plate[start[0] - 1][start[1] - 1][0] = false;
        
        while(!spotsToVisit.isEmpty()){
            Spot s = spotsToVisit.remove();
            for(int i = 0; i < dir; i++){
                int nextSpotX = s.x + xDir[i];
                int nextSpotY = s.y + yDir[i];
                int nextSpotZ = s.z + zDir[i];
                
                if(nextSpotX < 0){
                	nextSpotX = 0;
                }
                	
                if(nextSpotX >= plate.length){
                	nextSpotX = plate.length - 1;
                }
                	
                if(nextSpotY < 0){
                	nextSpotY = 0;
                }
                	
                if(nextSpotY >= plate[0].length){
                	nextSpotY = plate[0].length - 1;
                }
                	
                if(nextSpotZ < 0){
                	nextSpotZ = 0;
                }
                
                if(nextSpotZ >= plate[0][0].length){
                	nextSpotZ = plate[0][0].length - 1;
                }
                	
                if(plate[nextSpotX][nextSpotY][nextSpotZ]){
                    plate[nextSpotX][nextSpotY][nextSpotZ] = false;
                    spotsToVisit.add(new Spot(nextSpotX, nextSpotY, nextSpotZ));
                    timeTaken++;
                }
            }
        }
        System.out.println(timeTaken);
    }
    
    public static class Spot {
        int x, y, z;
        public Spot(int x, int y,int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}