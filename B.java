import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class B {
    
    static class TrieNode {
        int prefixOf = 0;
        TrieNode[] children = new TrieNode[26];
    }
    
    static void insertWord(TrieNode node, String w){
        node.prefixOf++; //root
        
        for (int i = 0; i < w.length(); i++){
            char c = w.charAt(i);
            
            int idx = c - 'a';
            
            if (node.children[idx] == null){
                node.children[idx] = new TrieNode();
            }
            
            node = node.children[idx];
            node.prefixOf++;
        }
    }
    
    static int requiredKeystrokes(TrieNode node, String w){
        TrieNode root = node;
        
        int idx = 0;
        
        while((node == root || node.prefixOf > 1) && idx < w.length()){ //at least 1 keystroke is needed!
            char c = w.charAt(idx);
            
            node = node.children[c - 'a'];
            idx++;
        }
        
        return idx;
    }
    
    static int solve(String[] words){
        TrieNode root = new TrieNode();
        
        int ans = 0;
        
        for (int i = 0; i < words.length; i++){
            String w = words[i];
            insertWord(root, w);
            
            ans += requiredKeystrokes(root, w);
        }
        
        return ans;
    }
    
    public static void main(String[] args) throws IOException {
        //File input = new File("B-test.in");
        //File output = new File("B-test.out");
        
        File input = new File("autocomplete.txt");
        File output = new File("B.out");
        
        //Scanner in = new Scanner(input);
        //PrintWriter out = new PrintWriter(output);
        
        Scanner in = new Scanner(System.in);
        PrintStream out = System.out;
        
        int T = in.nextInt();
        
        for (int t = 1; t <= T; t++){
            int N = in.nextInt();
            
            String[] words = new String[N];
            
            for (int i = 0; i < N; i++){
                words[i] = in.next();
            }
            
            out.println(String.format("Case #%d: %d", t, solve(words)));
        }
        
        in.close();
        out.close();
    }
    
}
