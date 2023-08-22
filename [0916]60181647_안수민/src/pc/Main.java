package pc;

import java.io.FileNotFoundException;

import board.Board;
import loader.Loader;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Board board = new Board();
		Loader loader = new Loader();
		loader.Load("data/exe");
		board.connect(loader);
		board.run();
	}
}
