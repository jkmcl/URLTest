package example;

import static org.junit.Assert.fail;

import java.net.InetAddress;

import javax.net.ssl.SSLHandshakeException;

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
		String hostname = InetAddress.getByName(googleHostName).getHostAddress().toString();
		try {
			obj.connect("https://" + hostname + "/");
			fail();
		} catch (SSLHandshakeException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testAnotherURL() throws Exception {
		URLTest obj = new URLTest();
		obj.connect("http://myhttp.info/");
	}
}
