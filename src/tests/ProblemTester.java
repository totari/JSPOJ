package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import helper.FileTools;
import helper.TestDataGenerator;
import helper.Problems;
import static helper.Problems.*;

public class ProblemTester {

	private static final String DEFAULT_VERSTION = "01"; 
	private static final String PROBLEMS_PACKAGE = "problems";
	private static final String LOG_PREFIX= "_LOG_";
	private static final String MAIN_CLASS= "Main";
	private static final String MAIN_METHOD= "main";
	private static final String VERSION_PREFIX= "v";
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	private String problemId;
	private String version = "";

	public String getProblemId() {
		return problemId;
	}

	private void setProblemId(Problems problemId) {
		this.problemId = problemId.toString();
	}

	public String getVersionNumber() {
		return version;
	}

	private void setVersionNumber(String versionNumber) {
		this.version = versionNumber;
	}
	
	public void setupProblem(Problems probId){
		setupProblem(probId, DEFAULT_VERSTION);
	}
	
	public void setupProblem(Problems probId, String versionNum){
		setProblemId(probId);
		if (!"".contentEquals(versionNum)) {
			setVersionNumber(VERSION_PREFIX + versionNum);
		}
	}
	
	public void prepareData() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TestDataGenerator.generateTestData(getProblemId());
		systemInMock.provideText(FileTools.extractTextFomInputFile(getProblemId()));
	}

	private long runProgram () throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> problemClass = Class.forName(PROBLEMS_PACKAGE + "." + getProblemId() + "." + getVersionNumber() + "." + MAIN_CLASS);
		Method main = problemClass.getMethod(MAIN_METHOD, String[].class);
		long startTime = System.currentTimeMillis();
		main.invoke(null, (Object) new String[]{});
		long stopTime = System.currentTimeMillis();
		return stopTime - startTime;
	}
	
	private boolean compareData() {
		FileTools.saveData(LOG_PREFIX + getProblemId(), "", outContent.toString());
		return FileTools.extractTextFomOutputFile(getProblemId()).equals((outContent.toString()));
	}
	
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@Test
	public void DIVSUM() throws Throwable {
		setupProblem(DIVSUM, "01");
		prepareData();
	    @SuppressWarnings("unused")
		long elapsedTime = runProgram();
	    assertTrue(compareData());
	}
	
	@Test
	public void INTEST() throws Throwable {
		setupProblem(INTEST);
		prepareData();
	    @SuppressWarnings("unused")
		long elapsedTime = runProgram();
	    assertTrue(compareData());
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	}
}
