
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class isReachable {

	private Scanner scanner;

	public boolean isReachableMetod2(String addr, int openPort, int timeOutMillis) {
		// Any Open port on other machine
		// openPort = 22 - ssh, 80 or 443 - webserver, 25 - mailserver etc.
		try {
			try (Socket soc = new Socket()) {
				soc.connect(new InetSocketAddress(addr, openPort), timeOutMillis);
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	public boolean isReachableMetod1(int nping, int wping, String ipping) throws Exception {
		int nReceived = 0;
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec("ping -n " + nping + " -w " + wping + " " + ipping);

		scanner = new Scanner(process.getInputStream());
		process.waitFor();
		ArrayList<String> strings = new ArrayList<>();
		String data = "";
		//
		while (scanner.hasNextLine()) {
			String string = scanner.nextLine();
			data = data + string + "\n";
			strings.add(string);
		}

		if (data.contains("IP address must be specified.")
				|| (data.contains("Ping request could not find host " + ipping + ".")
						|| data.contains("Please check the name and try again."))) {
			throw new Exception(data);
		} else if (nping > strings.size()) {
			throw new Exception(data);
		}

		int index = 2;

		for (int i = index; i < nping + index; i++) {
			String string = strings.get(i);
			System.out.println(string);
			if (string.contains("Destination host unreachable.")) {
			} else if (string.contains("Request timed out.")) {
			} else if (string.contains("байт") && string.contains("время") && string.contains("TTL")) {
				nReceived++;
			} else {
			}
		}

		return nReceived > 0;
	}
}
