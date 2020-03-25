package problems.DIVSUM.v01;

/////////////////////////////////////////////////
// Do not include first lines for submit code! //
/////////////////////////////////////////////////

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws java.lang.Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t, n, s, d, k;
		StringBuffer ans = new StringBuffer();
		t = Integer.parseInt(br.readLine());
		boolean[] arrPrimes = getPrimeList(t);
		while(t-- > 0)
		{
			n = Integer.parseInt(br.readLine());
			if (!arrPrimes[n])
			{
				ans.append("1\r\n");
				continue;
			}
			s = 0;
			k = (int) Math.round(Math.sqrt(n) + 0.5);
			
			for(int i = 2; i < k; i++){
				if(n % i == 0) {
					d = n / i;
					s += (d == i) ? i : (d + i);
				}
			}
			ans.append((n == 1 ? 0 : s + 1) + "\r\n");
		}
		System.out.print(ans);
	}
	
	private static boolean[] getPrimeList(int n)
	{
		n++;
		boolean[] arr = new boolean[n];
		arr[1] = true;
		for(int i = 2; i*i < n; i++) 
		{
			if (!arr[i])
			{
				for(int j = i*i; j < n; j+=i)
				{
					arr[j] = true;
				}
			}
		}
		return arr;
	}
}