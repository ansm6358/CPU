package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Loader {

	private Scanner scanner;
	public void Load(String exe) throws FileNotFoundException {
		File file = new File(exe);
		 this.scanner = new Scanner(file);
		
	}
//���� �ý����� �ε� �ض� ���
	public Scanner getScanner() {
		return this.scanner;
	}
}
