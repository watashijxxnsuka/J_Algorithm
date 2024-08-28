import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int student = sc.nextInt();
        int highScore = sc.nextInt();

        int[] stuArr = new int[student];

        for(int i = 0; i < stuArr.length; i++) {
            stuArr[i] = sc.nextInt();
        }

        for(int j = 1; j < stuArr.length; j++) {
            int data = stuArr[j]; // 1번
            int k = j - 1; // 0번

            while(k >= 0 && stuArr[k] < data) {
                stuArr[k + 1] = stuArr[k];
                k--;
            }
            stuArr[k + 1] = data;
        }
        System.out.println(stuArr[highScore - 1]);
    }
}
