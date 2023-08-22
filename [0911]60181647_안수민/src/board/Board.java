package board;
import cpu.CentralProcessingUnit;
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
		this.centralProcessingUnit.run();
	}
	
}
