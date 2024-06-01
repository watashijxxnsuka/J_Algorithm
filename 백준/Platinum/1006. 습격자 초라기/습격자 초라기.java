import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	private static int N;
	private static int W;
	
	private static int[][] e;
	
	private static int[] a;
	private static int[] b;
	private static int[] c;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(reader.readLine());
		
		for (int i = 0; i < T; i++)
		{
			int result = 2147483647;
			
			String[] temp = reader.readLine().split(" ");
			
			N = Integer.parseInt(temp[0]);
			W = Integer.parseInt(temp[1]);
			e = new int[2][N];
			
			for (int j = 0; j < 2; j++)
			{
				temp = reader.readLine().split(" ");
				
				for (int k = 0; k < N; k++)
				{
					e[j][k] = Integer.parseInt(temp[k]);
				}
			}
			
			a = new int[N];
			b = new int[N];
			c = new int[N + 1];
			
			a[0] = 1;
			b[0] = 1;
			c[0] = 0;
			
			solve(0);
			
			result = Math.min(result, c[N]);
			
			if (N > 1)
			{
				if (e[0][0] + e[0][N - 1] <= W && e[1][0] + e[1][N - 1] <= W)
				{
					a[1] = 1;
					b[1] = 1;
					c[1] = 0;
					
					solve(1);
					
					result = Math.min(result, c[N - 1] + 2);
				}
				
				if (e[0][0] + e[0][N - 1] <= W)
				{
					a[1] = 2;
					b[1] = e[1][0] + e[1][1] > W ? 2 : 1;
					c[1] = 1;

					solve(1);
					
					result = Math.min(result, b[N - 1] + 1);
				}
				
				if (e[1][0] + e[1][N - 1] <= W)
				{
					a[1] = e[0][0] + e[0][1] > W ? 2 : 1;
					b[1] = 2;
					c[1] = 1;
					
					solve(1);
					
					result = Math.min(result, a[N - 1] + 1);
				}
			}
			
			System.out.println(result);
		}
		
		reader.close();
	}
    
	private static void solve(int num)
	{
		for (int i = num; i < N; i++)
		{
			c[i + 1] = Math.min(a[i] + 1, b[i] + 1);
			if (e[0][i] + e[1][i] <= W)
			{
				c[i + 1] = Math.min(c[i + 1], c[i] + 1);
			}
			if (i > 0 && e[0][i - 1] + e[0][i] <= W && e[1][i - 1] + e[1][i] <= W)
			{
				c[i + 1] = Math.min(c[i + 1], c[i - 1] + 2);
			}
			if (i < N - 1)
			{
				a[i + 1] = c[i + 1] + 1;
				b[i + 1] = c[i + 1] + 1;
				
				if (e[0][i] + e[0][i + 1] <= W)
				{
					a[i + 1] = Math.min(a[i + 1], b[i] + 1);
				}
				
				if (e[1][i] + e[1][i + 1] <= W)
				{
					b[i + 1] = Math.min(b[i + 1], a[i] + 1);
				}
			}
		}
	}
}