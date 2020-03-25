package problems.DIVSUM.v02;

/////////////////////////////////////////////////
// Do not include first lines for submit code! //
/////////////////////////////////////////////////

/* By Joseph Thuemler
A few years ago
I'm sure there are ways to optimize this, maybe you can find them :D
If you have any questions, shoot me an email or ask at practice today
*/

import java.util.*;
import java.io.*;

//Rename class to main, remove package and Public before submission!
public class Main
{
	// don't hardcode constants!
	static int MAX = 500000;
	static int[] sieve;
	public static void main(String[] args) throws Throwable {
		// this is faster for input than scanner -- note that you can only read by line with BufferedReader
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		initPrimes();
		int trials = Integer.parseInt(in.readLine());
		// this is faster for printing than just using System.out.println(); every time
		StringBuffer ans = new StringBuffer();
		while(trials-->0) {
			int test = Integer.parseInt(in.readLine());
			// remember, we are looking for the sum of proper divisors, so find the sum of all divisors and subtract the number itself
			ans.append(factorSum(getPrimes(test))-test+"\r\n");
		}
		System.out.print(ans);
			
	}
	
	public static void initPrimes()  {
		// generate all primes up to MAX. you can make this more efficient!
		sieve = new int [MAX + 1];
		sieve[1] = 1;
		for(int i=2;i<=MAX;++i) {
			if(sieve[i] == 0) {
				// this means i doesn't have any prime factors below it, so it must be prime
				for (int j=i;j<=MAX;j+= i) {
					// sieve[j] stores a prime factor of j. that way, when you want to find the factorization of j
					// you can do it sort of recursively: the prime factorization of j is i + (the prime factorization of j/i)
					// note that this sieve stores the biggest prime factor of j
					sieve[j] = i;
				}
			}
		}
	}
	
	public static Queue<Integer> getPrimes(int n) {
		Queue<Integer> toRet = new LinkedList<Integer>();
		while(n > 1) {
			// sieve[n] stores a prime factor of n
			toRet.add(sieve[n]);
			// divide n by sieve[n] and then continue to find prime factorizations
			n /= sieve[n];
		}
		return toRet;
	
	}
	
	public static int factorSum(Queue<Integer> factors) {
		// the sum of factors formula: (1+p_1+p_1^2+...)(1+p_2+p_2^2+...)...
		int ans = 1; // this is the factor sum
		int curPrime = 1; 
		int toMult = 1; // each paren in the formula is represented by this variable
		int counter = 1; // this keeps track of the current power of our current prime number
		while(factors.size()>0) {
			// notice by the way the primes were put into the queue, you will never have a situation where you pull out a 2, then a 5, then a 2.
			// since sieve[n] always stores the biggest prime factor of n
			int next = factors.poll();
			if (next == curPrime) {
				// this prime is the same as the previous prime, so we add the next power of that prime to toMult
				toMult += Math.pow(curPrime, ++counter);
			}
			else {
				// we have a new prime, so multiply our answer by the sum of all the prime powers we just calculated
				ans *= toMult;
				// ... and reset the variables for the next prime
				toMult = 1+next;
				curPrime = next;
				counter = 1;
			}
		
		}
		ans *= toMult;
		return ans;
	}

}
