package board;
import cpu.CentralProcessingUnit;
import loader.Loader;
import memory.Memory;

public class Board {
	private CentralProcessingUnit centralProcessingUnit;
	private Memory memory;
	

	public Board() {
		this.centralProcessingUnit = new CentralProcessingUnit();
		this.memory = new Memory();
		
		this.centralProcessingUnit.connect(this.memory);
	}


	public void run() {
		this.memory.saveBuffer();
		this.centralProcessingUnit.run();
	}


	public void connect(Loader loader) {
		this.memory.connect(loader);
	}
	
}
