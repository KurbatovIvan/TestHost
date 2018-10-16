import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;

public class Params {
	protected static Logger log = Logger.getLogger(TestHost.class.getName());

	private static String progname = "TestHost";
	private static String FtpUrl = "";
	private static String FtpUserName = "";
	private static String FtpUserPasswd = "";

	private static String DatabaseFDB = "";

	private static String DatabaseUser = "";
	private static String DatabasePasswd = "";
	private static String Databaseencoding = "";

	private static String Mailhost = "";
	private static String MailUsername = "";
	private static String Mailpasswd = "";
	private static String Mailfrom = "";
	private static String MailProtocol = "";
	private static String MailPort = "";
	private static String Mailto = "";
	private static String PatternToExtractRegexp = "";
	private static String SectionErrorName = "";
	public static String filename;
	private static String[] DirToDownload;

	public Params() { // Конструктор
		try {
			if ((filename == null) || filename.equals("")) {
				filename = "ini.ini";
			}

			Preferences prefs = new IniPreferences(new Ini(new File(filename)));

			setFtpUrl(prefs.node(progname).get("UrlFtp", null));
			setFtpUserName(prefs.node(progname).get("FtpUserName", null));
			setFtpUserPasswd(prefs.node(progname).get("FtpUserPasswd", null));
			setDatabaseFDB(prefs.node(progname).get("DatabaseFDB", null));
			setPatternToExtractRegexp(prefs.node(progname).get("PatternToExtractRegexp", null));
			setMailhost(prefs.node(progname).get("Mailhost", null));
			setMailfrom(prefs.node(progname).get("Mailfrom", null));

			setMailProtocol(prefs.node(progname).get("MailProtocol", null));
			setMailPort(prefs.node(progname).get("MailPort", null));

			setMailUsername(prefs.node(progname).get("MailUsername", null));
			setMailpasswd(prefs.node(progname).get("Mailpasswd", null));
			setMailto(prefs.node(progname).get("Mailto", null));
			setDatabaseUser(prefs.node(progname).get("DatabaseUser", null));
			setDatabasePasswd(prefs.node(progname).get("DatabasePasswd", null));
			setDatabaseencoding(prefs.node(progname).get("Databaseencoding", null));

		} catch (IOException E) {
			log.warning(("IOException, BREAK: " + E.toString()));
			E.printStackTrace();
			System.exit(2);
		} catch (NullPointerException E) {
			log.warning(E.toString());
			E.printStackTrace();
			System.exit(2);

		}

	}

	public static String getFtpUrl() {
		return FtpUrl;
	}

	public static void setFtpUrl(String ftpUrl) {
		if (ftpUrl == null) {
			SectionErrorName = SectionErrorName + "FtpUrl;";
		}

		FtpUrl = ftpUrl;
	}

	/** @return the ftpUserPasswd */
	public static String getFtpUserPasswd() {
		return FtpUserPasswd;
	}

	/** @param ftpUserPasswd
	 *            the ftpUserPasswd to set */
	public static void setFtpUserPasswd(String ftpUserPasswd) {
		if (ftpUserPasswd == null) {
			SectionErrorName = SectionErrorName + "ftpUserPasswd;";
		}
		FtpUserPasswd = ftpUserPasswd;
	}

	/** @return the ftpUserName */
	public static String getFtpUserName() {
		return FtpUserName;
	}

	/** @param ftpUserName
	 *            the ftpUserName to set */
	public static void setFtpUserName(String ftpUserName) {
		if (ftpUserName == null) {
			SectionErrorName = SectionErrorName + "ftpUserName;";
		}
		FtpUserName = ftpUserName;
	}

	/** @return the dirToDownload */
	public static String[] getDirToDownload() {
		return DirToDownload;
	}

	/** @param dirToDownload
	 *            the dirToDownload to set */
	public static void setDirToDownload(String[] dirToDownload) {
		DirToDownload = dirToDownload;
	}

	/** @return the databaseFDB */
	public static String getDatabaseFDB() {
		return DatabaseFDB;
	}

	/** @param databaseFDB
	 *            the databaseFDB to set */
	public static void setDatabaseFDB(String databaseFDB) {
		if (databaseFDB == null) {
			SectionErrorName = SectionErrorName + "databaseFDB;";
		}

		DatabaseFDB = databaseFDB;
	}

	/** @return the mailhost */
	public static String getMailhost() {
		return Mailhost;
	}

	/** @param mailhost
	 *            the mailhost to set */
	public static void setMailhost(String mailhost) {
		if (mailhost == null) {
			SectionErrorName = SectionErrorName + "mailhost;";
		}
		Mailhost = mailhost;
	}

	/** @return the mailUsername */
	public static String getMailUsername() {
		return MailUsername;
	}

	/** @param mailUsername
	 *            the mailUsername to set */
	public static void setMailUsername(String mailUsername) {
		if (mailUsername == null) {
			SectionErrorName = SectionErrorName + "mailUsername;";
		}
		MailUsername = mailUsername;
	}

	/** @return the mailpasswd */
	public static String getMailpasswd() {
		return Mailpasswd;
	}

	/** @param mailpasswd
	 *            the mailpasswd to set */
	public static void setMailpasswd(String mailpasswd) {
		if (mailpasswd == null) {
			SectionErrorName = SectionErrorName + "mailpasswd;";
		}
		Mailpasswd = mailpasswd;
	}

	/** @return the mailfrom */
	public static String getMailfrom() {
		return Mailfrom;
	}

	/** @param mailfrom
	 *            the mailfrom to set */
	public static void setMailfrom(String mailfrom) {
		if (mailfrom == null) {
			SectionErrorName = SectionErrorName + "mailfrom;";
		}
		Mailfrom = mailfrom;
	}

	/** @return the mailto */
	public static String getMailto() {
		return Mailto;
	}

	/** @param mailto
	 *            the mailto to set */
	public static void setMailto(String mailto) {
		if (mailto == null) {
			SectionErrorName = SectionErrorName + "mailto;";
		}
		Mailto = mailto;
	}

	/** @return the patternToExtractRegexp */
	public static String getPatternToExtractRegexp() {
		return PatternToExtractRegexp;
	}

	/** @param patternToExtractRegexp
	 *            the patternToExtractRegexp to set */
	public static void setPatternToExtractRegexp(String patternToExtractRegexp) {
		if (patternToExtractRegexp == null) {
			SectionErrorName = SectionErrorName + "patternToExtractRegexp;";
		}
		PatternToExtractRegexp = patternToExtractRegexp;

	}

	/** @return the databaseUser */
	public static String getDatabaseUser() {
		return DatabaseUser;
	}

	/** @param databaseUser
	 *            the databaseUser to set */
	public static void setDatabaseUser(String databaseUser) {
		if (databaseUser == null) {
			SectionErrorName = SectionErrorName + "databaseUser;";
		}
		DatabaseUser = databaseUser;
	}

	/** @return the databasePasswd */
	public static String getDatabasePasswd() {
		return DatabasePasswd;
	}

	/** @param databasePasswd
	 *            the databasePasswd to set */
	public static void setDatabasePasswd(String databasePasswd) {
		if (databasePasswd == null) {
			SectionErrorName = SectionErrorName + "databasePasswd;";
		}
		DatabasePasswd = databasePasswd;
	}

	/** @return the databaseencoding */
	public static String getDatabaseencoding() {
		return Databaseencoding;
	}

	/** @param databaseencoding
	 *            the databaseencoding to set */
	public static void setDatabaseencoding(String databaseencoding) {
		if (databaseencoding == null) {
			SectionErrorName = SectionErrorName + "databaseencoding;";
		}
		Databaseencoding = databaseencoding;
	}

	/** @return the mailProtocol */
	public static String getMailProtocol() {
		return MailProtocol;
	}

	/** @param mailProtocol
	 *            the mailProtocol to set */
	public static void setMailProtocol(String mailProtocol) {
		MailProtocol = mailProtocol;
	}

	/** @return the mailPort */
	public static String getMailPort() {
		return MailPort;
	}

	/** @param mailPort
	 *            the mailPort to set */
	public static void setMailPort(String mailPort) {
		MailPort = mailPort;
	}

}
