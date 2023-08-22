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
//파일 시스템을 로딩 해라 명령
	public Scanner getScanner() {
		return this.scanner;
	}
}
