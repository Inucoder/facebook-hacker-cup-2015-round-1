import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    
    static final int MAX = 10000000;
    
    static int[] primacity = new int[MAX + 1];
    
    static void precomputePrimacity(){
        for (int n = 2; n <= MAX; n++){
            if (primacity[n] == 0) { //n is prime
                for (int k = 1; n * k <= MAX; k++){
                    primacity[n * k]++;
                }
            }
        }
    }
    
    static int solve(int A, int B, int K){
        int ans = 0;
        
        for (int n = A; n <= B; n++){
            if (primacity[n] == K){
                ans++;
            }
        }
        
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        precomputePrimacity();
        
        //File input = new File("A-test.in");
        //File output = new File("A-test.out");
        
        File input = new File("A.in");
        File output = new File("A.out");
        
        //Scanner in = new Scanner(input);
        //PrintWriter out = new PrintWriter(output);
        
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        
        int T = in.nextInt();
        
        for (int t = 1; t <= T; t++){
            int A = in.nextInt();
            int B = in.nextInt();
            int K = in.nextInt();
            
            out.println(String.format("Case #%d: %d", t, solve(A, B, K)));
        }
        
        in.close();
        out.close();
    }
    
}
