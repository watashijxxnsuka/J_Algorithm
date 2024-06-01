import java.io.*;
import java.util.*;

class Main {
    static final String[] NUMS = {
            "####.##.##.####",
            "..#..#..#..#..#",
            "###..#####..###",
            "###..####..####",
            "#.##.####..#..#",
            "####..###..####",
            "####..####.####",
            "###..#..#..#..#",
            "####.#####.####",
            "####.####..####"
    };
    static String[] map = {"","","","",""};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<5; i++){
            map[i] = br.readLine();
        }
        HashSet<Double>[] set = new HashSet[N];
        for(int i=0; i<N; i++) set[i] = new HashSet<>();
        int strLen = 4*N-1;
        int totalSize = 1;
        int[] digitCount = new int[N];
        Arrays.fill(digitCount,1);
        for(int i=0; i<5; i++){
            for(int j=0; j<strLen; j+=4){
                int index = j/4;
                for(int k=0; k<10; k++){
                    double value = Math.pow(10, N-1-index)*k;
                    if(canNum(i,j,k)){
                        if(i==0){
                            set[index].add(value);
                        }
                    }else{
                        set[index].remove(value);
                    }
                }
                if(set[index].size()==0){
                    System.out.println("-1");
                    return;
                }
                if(i==4){
                    totalSize = totalSize * set[index].size();
                    for(int k=0; k<N; k++){
                        if(k!=index) digitCount[k] = digitCount[k]*set[index].size();
                    }
                }
            }
        }
        double sum = 0;
        for(int i=0; i<N; i++){
            double multi = (double) digitCount[i] / totalSize;
            sum += set[i].stream().mapToDouble(x -> x).map(x -> x * multi).sum();
        }
        System.out.println(sum);
        br.close();
    }
    static boolean canNum(int row, int index ,int compareTo){
        for(int i=0; i<3; i++){
            if(map[row].charAt(index+i)!='.' && map[row].charAt(index+i)!=NUMS[compareTo].charAt(row*3+i)) return false;
        }
        return true;
    }
}