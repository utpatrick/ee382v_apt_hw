package LowerInputStream;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class LowerInputStreamTest {

	@Test
	public void test1() throws IOException {
		String testString = "I know the Decorator Pattern therefore I RULE!";
		String ansString = "i know the decorator pattern therefore i rule!";
		String resultString = "";
		try(InputStream in = 
				new LowerInputStream(
					new BufferedInputStream(
						IOUtils.toInputStream(testString, "UTF-8")
				))){
			int ch;
			while ((ch = in.read()) >= 0) {
				resultString += Character.toString((char) ch);
	        }
		}
		assertTrue(resultString.equals(ansString));
	}
	
	@Test
	public void test2() throws IOException {
		String ansString = "i know the decorator pattern therefore i rule!";
		String resultString = "";
		try(InputStream in = 
				new LowerInputStream(
					new BufferedInputStream(
						new FileInputStream(
						"src\\LowerInputStream\\testcase.txt"
				)))){
			int ch;
			while ((ch = in.read()) >= 0) {
				resultString += Character.toString((char) ch);
	        }
		}
		assertTrue(resultString.equals(ansString));
	}

}
