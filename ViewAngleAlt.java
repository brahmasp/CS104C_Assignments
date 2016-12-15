import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;



public class ViewAngleAlt  {
	
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double m[] = new double[n];
        
        for(int i = 0; i < n; i++) {
        	
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            if(a == 0 && b > 0) { // +ve y axis
                m[i]=90;
                continue;
            }
            
            
            if(a==0 && b<0)
            {
            m[i]=270;
            continue;
            }
            
            if(b==0 && a>0)
            {
            m[i]=0;
            continue;
            }
            
            
            if(b==0 && a<0)
                m[i]=180;
            else
            m[i]=Math.toDegrees(Math.atan(((double)b)/(double)a));
            
                
                {if(a<0 && b>0)
                m[i]=180+(m[i]);
                else
                    if(a<0 && b<0)
                        m[i]=180+Math.abs(m[i]);
                    else
                        if(a>0 && b<0)
                            m[i]=360+m[i];
            
                }
                
                
            
        }
        
        if(n==1)
        {
            System.out.printf("0.000000");
            System.exit(0);
        }
        
        Arrays.sort(m);
        
        
        //System.out.println(m1);
        double min=Double.MAX_VALUE;
        double tmp=m[0];
        for(int i=0;i<n;i++)
        {
            if(m[n-i-1]==tmp)
            continue;
            if(Math.abs(m[n-i-1]-tmp)<min)
            {
                min=Math.abs(m[n-i-1]-tmp);
            }
            //System.out.println(min+" "+Math.abs(m[n-i-1]-tmp)+" "+m[n-i-1]+" "+tmp);
            tmp=(m[n-i-1]-360);
            
        }
        if(min==Double.MAX_VALUE)
            System.out.println("0.000000");
        else
        System.out.printf("%.6f",min);
        
    }
 }