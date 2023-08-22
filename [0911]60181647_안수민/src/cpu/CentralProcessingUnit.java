package cpu;

import memory.Memory;

public class CentralProcessingUnit {
	enum EState {idle, running};
	private EState estate;
	private enum EInstruction {HLT, LDA, LDV, STA, STV, ADD ,AND , JMP, BZ, NOT, SHR};
	
	private ControlUnit controlUnit;
	private ArithmathicLogicUnit arithmathicLogicUnit;
	private Memory memory;
	
	private Register pc, sp, mar, mbr, ir ,ac, status;
	
	
	public CentralProcessingUnit() {
		this.controlUnit = new ControlUnit();
		this.arithmathicLogicUnit = new ArithmathicLogicUnit();
		
		this.pc = new Register();
		this.sp = new Register();
		this.mar = new Register();
		this.mbr = new Register();
		this.ir = new Register();
		this.ac = new Register();
		this.status = new Register();
		
		this.controlUnit.connect(this.pc, this.ir, this.sp, this.mar, this.status);
		this.arithmathicLogicUnit.connect(this.ac, this.mbr, this.status);
	}
	public void connect(Memory memory) {
		this.memory = memory;
		this.memory.connect(this.mar, this.mbr);
	}
	public void run() { //이 run이 클락 //그만 하라고 할 때까지 무한 루프를 돌린다
		this.estate = EState.running;
		while(this.estate == EState.running) {
			this.executeInstruction();
		}
	}
	private void executeInstruction() {
		this.mar.setData(this.pc.getData());
		this.memory.fetch();
		this.ir.setData(this.mbr.getData());
		int instruction = this.ir.getData() >>> 16;
		switch(EInstruction.values()[instruction]) {
		case HLT:
			HLT();
			break;
		case LDA:
			LDA();
			break;
		case LDV:
			LDV();
			break;
		case STA:
			STA();
			break;
		case STV:
			STV();
			break;
		case ADD:
			ADD();
			break;
		case AND:
			AND();
			break;
		case JMP:
			JMP();
			break;
		case BZ:
			BZ();
			break;
		case NOT:
			NOT();
			break;
		case SHR:
			SHR();
			break;
		default:
			break;
		}
	}
	private void HLT() {
		
	}
	private void LDA() {
		int address = this.ir.getData() & 0x0000ffff;  //operand: 피연산자 (연산의 대상이 되는거) +5에서는 5
		address = address + this.sp.getData();
		this.mar.setData(address);	
		this.memory.fetch();
		this.ac.setData(this.mbr.getData());
	}
	private void LDV() {
		this.ac.setData(this.mbr.getData());
	}
	private void STA() {
		int address = this.ir.getData() & 0x0000ffff; 
		this.mar.setData(address);
		this.mbr.setData(this.ac.getData()); 
		this.memory.store();
	}
	private void STV() {
		
	}
	private void ADD() {
		int address = this.ir.getData() & 0x0000ffff;  
		if(address == this.sp.getData()) {  //add value인지 add address인지 구분을 위한 것
		address = address + this.sp.getData();
		this.mar.setData(address);	
		this.memory.fetch();
		}
		this.arithmathicLogicUnit.ADD();
		
	}
	private void AND() {
		int address = this.ir.getData() & 0x0000ffff;  
		if(address == this.sp.getData()) {  //and value인지 and address인지 구분을 위한 것
		address = address + this.sp.getData();
		this.mar.setData(address);	
		this.memory.fetch();
		}
		this.arithmathicLogicUnit.AND();
	}
	private void JMP() {
		
	}
	private void BZ() {
		
	}
	private void NOT() {
		
	}
	private void SHR() {
		
	}
}
