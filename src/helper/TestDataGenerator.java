package helper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class TestDataGenerator {
	
	public static void generateTestData(String problemId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
				
		if (!FileTools.problemDataExists(problemId)) {
			Method problemDataGenerator = TestDataGenerator.class.getMethod(problemId, String.class);
			problemDataGenerator.invoke(TestDataGenerator.class, problemId);			
		}
	}
	
	//
	// Problem test data generator methods
	//
	
	public static void DIVSUM(String problemId) {
		int lnCount =  10000;
		String contentIn = Integer.toString(lnCount);
		String contentOut = "";
		int d, dSum;
		for (int n = 1; n <= lnCount; n++) {
			contentIn += "\n" + n;
			d = 1;
			dSum = 0;
			while (d <= (n / 2)) {
				if (n % d == 0) {
					
					dSum += d;
				}
				d++;
			}
			contentOut += (contentOut.equals("") ? "" : "\n") + dSum;
		}
		FileTools.saveData(problemId, contentIn.toString(), contentOut);
	}
	
	public static void INTEST(String problemId){
		Random randomGenerator = new Random();
		
		int lnCount =  (int) Math.pow(10, 6);
		int rndRange = (int) Math.pow(10, 9);
		int divisor = randomGenerator.nextInt(lnCount + 1);
		int divisorsCount = 0;
		
		StringBuilder contentIn =  new StringBuilder(Integer.toString(lnCount) + " " + divisor);
		String contentOut = "";
		
		for (int n = 1; n <= lnCount; n++) {
			int randomInt =  randomGenerator.nextInt(rndRange + 1);
			contentIn.append("\n" + randomInt);
			if (randomInt % divisor == 0) {
				divisorsCount++;
			}
		}
		contentOut = Integer.toString(divisorsCount);
		
		FileTools.saveData(problemId, contentIn.toString(), contentOut);
	}	
	
}
