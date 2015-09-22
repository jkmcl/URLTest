package example;

import java.net.InetAddress;

import org.junit.Test;

public class URLTestTest
{
	private static final String googleHostName = "www.google.com";

	@Test
	public void testGoodURL() throws Exception {
		URLTest obj = new URLTest();
		obj.connect("https://" + googleHostName +"/");
	}

	@Test
	public void testBadURL() throws Exception {
		URLTest obj = new URLTest();
		obj.connect("https://" + InetAddress.getByName(googleHostName).getHostAddress() + "/");
	}

	@Test
	public void testAnotherURL() throws Exception {
		URLTest obj = new URLTest();
		obj.connect("http://myhttp.info/");
	}
}
