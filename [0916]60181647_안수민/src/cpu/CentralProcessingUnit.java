package cpu;

import memory.Memory;

public class CentralProcessingUnit {
	//	components
	private ControlUnit controlUnit;
	private ArithmathicLogicUnit arithmathicLogicUnit;
	private Register pc, sp, mar, mbr, ir ,ac, status;
	//	association
	private Memory memory;
		
	public CentralProcessingUnit() {
				
		//components
		this.controlUnit = new ControlUnit();
		this.arithmathicLogicUnit = new ArithmathicLogicUnit();
		
		this.pc = new Register();
		this.sp = new Register();
		this.mar = new Register();
		this.mbr = new Register();
		this.ir = new Register();
		this.ac = new Register();
		this.status = new Register();
		
		this.controlUnit.connect(this.pc, this.ir, this.sp, this.mar, this.status, this.mbr,this.ac);
		this.arithmathicLogicUnit.connect(this.ac, this.mbr, this.status);
		
		this.controlUnit.connect(this.arithmathicLogicUnit);
		
	}
	public void connect(Memory memory) {
		this.memory = memory;
		this.memory.connect(this.mar, this.mbr);
		this.controlUnit.connect(this.memory);
	}
	public void run() { //이 run이 클락 //그만 하라고 할 때까지 무한 루프를 돌린다
		this.sp.setData(this.memory.getDatasegment());
		this.controlUnit.run();
	}


}
