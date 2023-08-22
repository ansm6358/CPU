package cpu;

import memory.Memory;

public class ControlUnit {
	//	attributes
	enum EState {halt, running};
	private EState estate;	
	private enum EInstruction {HLT, LDA, LDV, STA, STV, ADD ,AND , JMP, BZ, NOT, SHR, PRT};	
	
	//association
	@SuppressWarnings("unused")
	private Register pc, sp, mar, mbr, ir ,ac, status;
	private ArithmathicLogicUnit arithmathicLogicUnit;
	private Memory memory;

	public ControlUnit() {
		this.estate = EState.halt;
	}
	public void connect(Register pc, Register ir, Register sp, Register mar, Register status, Register mbr, Register ac) {
		this.pc = pc;
		this.ir = ir;
		this.sp = sp;
		this.mar = mar;
		this.status = status;
		this.mbr = mbr;
		this.ac = ac;
	}
	public void connect(ArithmathicLogicUnit arithmathicLogicUnit) {
		this.arithmathicLogicUnit = arithmathicLogicUnit;
	}
	public void connect(Memory memory) {
		this.memory = memory;
	}
	public void run() {
		this.estate = EState.running;
		while(this.estate == EState.running) {
			this.fetch();
			this.decode();
		}
	}
	private void fetch() {
		this.mar.setData(this.pc.getData());
		this.memory.fetch();
		this.ir.setData(this.mbr.getData());		
	}
	private void decode() {
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
		case PRT:
			PRT();
		default:
			break;
		}

	}

	private void PRT() {
		System.out.println("PRT");
		int address = this.ir.getData() & 0x0000ffff;
		address = address + this.sp.getData();		
		this.mar.setData(address);
		this.memory.fetch();
		System.out.println(this.mbr.getData());
		this.pc.setData(this.pc.getData()+1);
	}
	private void HLT() {
		System.out.println("HLT");
		this.estate = EState.halt;
		this.pc.setData(this.pc.getData()+1);
	}
	private void LDV() {
		System.out.println("LDV");
		this.ac.setData(this.ir.getData() & 0x0000ffff);
		this.pc.setData(this.pc.getData()+1);
	}
	private void STA() {
		System.out.println("STA");
		this.mar.setData(this.ir.getData() & 0x0000ffff);
		this.mbr.setData(this.ac.getData());
		this.memory.store(this.sp);
		this.pc.setData(this.pc.getData()+1);
	}
	private void STV() {
		System.out.println("STV");
		this.pc.setData(this.pc.getData()+1);
	}
	private void ADD() {
		System.out.println("ADD");
		int address = this.ir.getData() & 0x0000ffff;
		address = address + this.sp.getData();		
		this.mar.setData(address);
		this.memory.fetch();
		this.arithmathicLogicUnit.add();
		this.pc.setData(this.pc.getData()+1);
	}
	private void AND() {
		System.out.println("AND");
		this.pc.setData(this.pc.getData()+1);
	}
	private void JMP() {
		System.out.println("JMP");
		this.pc.setData(this.pc.getData()+1);
	}
	private void BZ() {
		System.out.println("BZ");
		this.pc.setData(this.pc.getData()+1);
	}
	private void NOT() {
		System.out.println("NOT");
		this.pc.setData(this.pc.getData()+1);
	}
	private void SHR() {
		System.out.println("SHR");
		this.pc.setData(this.pc.getData()+1);
	}
	private void LDA() {
		System.out.println("LDA");
		int address = this.ir.getData() & 0x0000ffff;
		address = address + this.sp.getData();		
		this.mar.setData(address);
		this.memory.fetch();
		this.ac.setData(this.mbr.getData());
		this.pc.setData(this.pc.getData()+1);
	}

}
