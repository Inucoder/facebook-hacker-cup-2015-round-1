import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class C {
    
    static final int MAX = 2000;
    
    static int MOD =  1000000007;
    
    static long[][] stressFreeDp = new long[MAX + 1][MAX + 1];
    
    static long[][] stressfulDp = new long[MAX + 1][MAX + 1];
    
    static void computeDp(){
        for (int i = 0; i <= MAX; i++){
            stressFreeDp[i][0] = 1;
            stressfulDp[0][i] = 1;
        }
        
        for (int a = 1; a <= MAX; a++){
            for (int b = 1; b <= MAX; b++){
                if (a > b){
                    stressFreeDp[a][b] += stressFreeDp[a - 1][b];
                    stressFreeDp[a][b] += stressFreeDp[a][b - 1];
                    stressFreeDp[a][b] %= MOD;
                }
                else {
                    stressfulDp[a][b] += stressfulDp[a - 1][b];
                    stressfulDp[a][b] += stressfulDp[a][b - 1];
                    stressfulDp[a][b] %= MOD;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        computeDp();
        
        File input = new File("C-test.in");
        File output = new File("C-test.out");
        
        //File input = new File("winning_at_sports.txt");
        //File output = new File("C.out");
        
        //Scanner in = new Scanner(input);
        //PrintWriter out = new PrintWriter(output);
        
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        
        int T = in.nextInt();
        
        for (int t = 1; t <= T; t++){
            String score = in.next();
            String[] parts = score.split("-");
            
            int A = Integer.parseInt(parts[0]);
            int B = Integer.parseInt(parts[1]);
            
            long stressFree = stressFreeDp[A][B];
            
            long stressful = 0;
            
            int a = A;            
            while(a >= 0){
                if (stressfulDp[a][B] > 0){
                    stressful += stressfulDp[a][B];
                    break;
                }
                
                a--;
            }
            
            int b = B;
            while (b >= 0){
                if (stressfulDp[A][b] > 0){
                    stressful += stressfulDp[A][b];
                    break;
                }
                
                b--;
            }
            
            stressful %= MOD;
            
            out.println(String.format("Case #%d: %d %d", t, stressFree, stressful));
        }
        
        in.close();
        out.close();
    }
    
}
