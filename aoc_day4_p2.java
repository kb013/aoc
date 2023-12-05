import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aoc_day4_p2 {
    public static void main(String args[]) throws IOException {
        File f = new File("input_day4.txt");
        Scanner sc = new Scanner(f);
        long sum = 0;
        int i = 1;
        int[] count = new int[205];
        while (sc.hasNextLine()) {
            HashMap<Integer, Integer> winners = new HashMap<>();
            String s = sc.nextLine();
            s = s.replaceAll("\\w+\s+\\d+: ", "");
            String[] ss = s.split("\\|");
//            System.out.println(s);
//            System.out.println(ss[0]);
//            System.out.println(ss[1]);
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(ss[0]);
            while(m.find()){
                winners.put(Integer.parseInt(m.group()), 1);
            }
            m = p.matcher(ss[1]);
            while(m.find()){
                if(winners.containsKey(Integer.parseInt(m.group()))) {
                    if (winners.get(Integer.parseInt(m.group())) == 1) {
                        count[i]++;
                    }
                }
            }
            i++;
        }
        int[] rep = new int[206];
        for(int p = 0; p < 205; p++) rep[p] = 1;
        int[] finalcnt = new int[206];
        for(int p = 0; p < 205; p++) finalcnt[p] = 1;
        for(int x = 1; x <= 205; x++){
            //System.out.println(count[x]);
            for(int k = 0; k < rep[x]; k++) {
                for (int y = x + 1; y <= count[x] + x; y++) {
                    rep[y]++;
                    finalcnt[y]++;
                }
            }
        }
        for(int p = 1; p < 205; p++){
            sum += finalcnt[p];
        }
        System.out.println(sum);
    }
}
