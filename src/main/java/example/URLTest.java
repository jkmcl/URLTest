package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/**
 * Simple command-line tool for retrieving the contents of an URL. If the protocol is HTTPS, this tool will bypass hostname verification.
 *
 * Excellent reference on the use of URLConnection:
 * http://stackoverflow.com/questions/2793150/using-java-net-urlconnection-to-fire-and-handle-http-requests
 */
public class URLTest implements HostnameVerifier
{
	/** Bypass host name verification */
	public boolean verify(String hostname, SSLSession session) {
		System.out.println("Bypassing verification of hostname: " + hostname);
		return true;
	}

	/** Make connection to URL and print contents of the URL to standard output */
	public void connect(String urlString) throws IOException {
		URLConnection conn = new URL(urlString).openConnection();
		if (conn instanceof HttpsURLConnection) {
			((HttpsURLConnection) conn).setHostnameVerifier(this);
		}

		System.out.println("Connecting to URL: " + urlString);
		conn.connect();

		System.out.println("Contents of the URL (if any) starting from the next line: ");
		try (InputStreamReader isr = new InputStreamReader(conn.getInputStream());
				BufferedReader br = new BufferedReader(isr)) {
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}

	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage: java " + URLTest.class.getName() + " <URL>");
			return;
		}
		String url = args[0];
		URLTest obj = new URLTest();
		obj.connect(url);
	}
}
