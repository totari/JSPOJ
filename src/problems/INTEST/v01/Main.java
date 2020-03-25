package problems.INTEST.v01;

/////////////////////////////////////////////////
// Do not include first lines for submit code! //
/////////////////////////////////////////////////

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] strings) throws java.lang.Exception 
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		int counter = 0;
		while(n-- > 0) {
			int t = Integer.parseInt(br.readLine());
			if (t < k) {
				continue;
			} else if (t % k == 0) {
				counter++;
			}
		}
		System.out.println(counter);
	}
}
