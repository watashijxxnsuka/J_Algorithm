import java.io.*;
import java.util.*;

public class Main {
    static long N, result, mid;
    static int M, maxT;
    static int[] time;
    static List<Integer> startList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[M];
        maxT = 0;
        startList = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            maxT = Math.max(time[i], maxT);
        }
        binarySearch();
        System.out.println(result);
    }

    static void binarySearch() {
        if (N <= M) { 
            result = N;
            return;
        }
        long left = 0;
        long right = maxT * N;
        while (left <= right) {
            mid = (left + right) / 2;
            long num = countStart(mid);  
            if (num >= N) {  
                getLastRide(mid);
                right = mid - 1;  
            } else { 
                left = mid + 1;  
            }
        }
    }

    static long countStart(long t) {
        long cnt = M;  
        for (int i = 0; i < M; i++) {
            cnt += t / time[i];
        }
        return cnt; 
    }

    static void getLastRide(long t) {
        long cnt = M; 
        for (int i = 0; i < M; i++) {
            cnt += (t - 1) / time[i]; 
        }

        for (int i = 0; i < M; i++) {
            if (t % time[i] == 0) { 
                cnt++;
                if (cnt == N) {
                    result = i + 1;
                }
            }
        }
    }
}