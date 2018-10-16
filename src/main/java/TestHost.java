
import java.io.IOException;
import java.util.logging.LogManager;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import java.util.logging.Logger;

/**
 * ������������ ����������������� ����� ���
 * 
 * @author Ivank
 * 
 */

public class TestHost {
	// XMLConfiguration config = new XMLConfiguration();
	private static Logger log = Logger.getLogger(TestHost.class.getName());

	public static final String USAGE = "Missing required option: \n" + "-file\n" + "-mailto\n" + "-mailfrom\n"
			+ "-mailpasswd\n" + "ALL or WARNING\n" + "\n" + "Example:\n" + "java test -file host.txt ALL\n"
			+ "Example:\n"
			+ "java test -file host.txt WARNING -mailto anna_semenovich@mail.ru -mailfrom egor@mail.ru -mailpasswd 31415926\n";

	public static void main(String args[]) {

		try {
			// System.out.println(TestHost.class.getResourceAsStream("logging.properties"));
			LogManager.getLogManager().readConfiguration(TestHost.class.getResourceAsStream("logging.properties"));

		} catch (IOException e) {
			System.err.println("Could not setup logger configuration: " + e.toString());
		}

		boolean depth = false;
		String filename = null;
		String mailto = null;
		String mailfrom = null;
		String mailpasswd = null;

		if (args.length == 0) {
			System.err.println(USAGE);
			System.exit(1);
		} else {

			int base = 0;
			for (base = 0; base < args.length; base++) {

				if (args[base].equals("WARNING")) {
					depth = true;
				} else if (args[base].equals("-file")) {
					filename = args[++base];
				} else if (args[base].equals("-mailto")) {
					mailto = args[++base];
				} else if (args[base].equals("-mailfrom")) {
					mailfrom = args[++base];
				} else if (args[base].equals("-mailpasswd")) {
					mailpasswd = args[++base];
				}

			}

		}

		// System.out.println("Only Warning:" + depth + "\n");
		log.info("Only Warning:" + depth + "\n");

		String message = "";
		isReachable IpTest = new isReachable();
		int i = 0;
		int indexD = 0;
		int indexComment = 0;

		String hostname = "";
		String port = "";
		String comment = "";
		int portNumber;
		try {
			// FileText f = new FileText(args[0]);
			FileText f = new FileText(filename);

			while (i < f.list.size()) {

				indexD = f.list.get(i).indexOf(":");
				indexComment = f.list.get(i).indexOf(";");
				if (indexD > 0) {
					hostname = f.list.get(i).substring(0, indexD);
					if (indexComment > 0) {
						port = f.list.get(i).substring(indexD + 1, indexComment);
						comment = f.list.get(i).substring(indexComment + 1, f.list.get(i).length());
					} else {
						port = f.list.get(i).substring(indexD + 1, f.list.get(i).length());
					}

					try {
						portNumber = Integer.parseInt(port);

						boolean IpTestResult = IpTest.isReachableMetod2(hostname, portNumber, 1000);
						if (IpTestResult == true) {
							if (depth == false) {
								message = message + hostname + " port:" + port + "  work;" + comment + "\n";
							}
						} else {
							message = message + comment + " " + hostname + " port:" + port + "  WARNING NOT WORK \n";
						}

					} catch (NumberFormatException e) {
						// System.out.println("Krivoi port dla hostname=" + hostname);
						log.info("Krivoi port dla hostname=" + hostname);
					}
					// ��������� �������� �� ���� �� �����

				}
				i++;
			}

			// System.out.println(message);

			if (message.length() > 0) {
				new EMailService("mail.nic.ru", mailfrom, mailpasswd, "Monitoring <" + mailfrom + ">", mailto, message,
						"Monitoring");

			}

		} catch (IOException ex) {
			System.out.println("File not found");
			log.info("File not found");
			ex.printStackTrace();

		}
	}
}