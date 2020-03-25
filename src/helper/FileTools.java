package helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileTools {

	private static final String TEST_DATA_LOCATION = "src/testdata/";
	private static final String INPUT_FILE_POSTFIX = "in";
	private static final String OUTPUT_FILE_POSTFIX = "out";
	private static final String DEFAULT_FILE_EXTENTION = "txt";
	
	public static String getInputFilePath (String problemId){
		return TEST_DATA_LOCATION + problemId + INPUT_FILE_POSTFIX + "." + DEFAULT_FILE_EXTENTION;
	}
	public static String getOutputFilePath (String problemId){
		return TEST_DATA_LOCATION + problemId + OUTPUT_FILE_POSTFIX + "." + DEFAULT_FILE_EXTENTION;
	}
	
	public static String extractTextFomInputFile(String problemId) {
		return extractTextFomFileByPath(getInputFilePath(problemId));
	}
	
	public static String extractTextFomOutputFile(String problemId) {
		return extractTextFomFileByPath(getOutputFilePath(problemId));
	}
	
	public static boolean problemDataExists(String problemId) {
		
		Path pathIn = Paths.get(FileTools.getInputFilePath(problemId));
		Path pathOut = Paths.get(FileTools.getOutputFilePath(problemId));
		return Files.exists(pathIn) && Files.exists(pathOut);
	}
	
	public static void saveData(String problemId, String contentIn, String contentOut){
		if (!"".equals(contentIn)){
			try {
				Files.write(Paths.get(getInputFilePath(problemId)), contentIn.getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (!"".equals(contentOut)){
			try {
				Files.write(Paths.get(getOutputFilePath(problemId)), contentOut.getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String extractTextFomFileByPath(String filePath)
	{
		String extractedText = "";
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))) 
		{
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            line = br.readLine();
	        }
	        extractedText = sb.toString();
	    } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return extractedText;
	}
}
