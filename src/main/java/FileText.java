
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileText {
	/**
	 * ��������� ��������� ���� ��� ������
	 * 
	 * @param ���
	 *            �����
	 * @list ������ �����
	 */

	// public StringBuilder sb; // ���������� � ������� ������� ���������
	// ������������� �������
	public ArrayList<String> list; /* ���������� � ������� ������� ��������� ������������� ������� */

	FileText(String filename) throws IOException // �����������
	{

		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		list = new ArrayList<String>();
		while ((s = in.readLine()) != null) {
			list.add(s);
		}
		in.close();

	}
}