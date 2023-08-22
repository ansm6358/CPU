package memory;

import java.util.Scanner;

import cpu.Register;
import loader.Loader;

public class Memory {
	int buffer[];
	private Register mar, mbr;
	private Loader loader;
	private Scanner scanner;
	private int dataSegment;
	
	public void connect(Register mar,Register mbr) {
		this.mar = mar;
		this.mbr = mbr;
	}
	public int getDatasegment() {
		return this.dataSegment;
	}
	public Memory() {
		this.buffer = new int[100];
	}
	
	public void fetch() {
		this.mbr.setData(buffer[this.mar.getData()]);
	}
	public void store(Register sp) {
		buffer[this.mar.getData()+sp.getData()]= this.mbr.getData();
	}
	public void connect(Loader loader) {
		this.loader = loader;
		this.scanner = this.loader.getScanner();
	}
	public void saveBuffer() {		
		int locate = 0;
		int instructionInt =0;
		int address =0;
		
		this.dataSegment = this.scanner.nextInt();
		
		while(this.scanner.hasNext()) {
		String instruction =this.scanner.next();
		 instructionInt= compile(instruction);
		if(!instruction.equals("HLT")) {
		 address = this.scanner.nextInt();
		}
		int code = (instructionInt << 16)+ address;
		this.buffer[locate]=code;
		locate=locate+1;
		}
		this.dataSegment=locate;
	}
	private int compile(String instruction) {
		int codeHexa = 0;
		if(instruction.equals("HLT")) {
			codeHexa = 0;
		} else if(instruction.equals("LDA")) {
			codeHexa = 1;
		} else if(instruction.equals("LDV")) {
			codeHexa = 2;
		} else if(instruction.equals("STA")) {
			codeHexa = 3;
		} else if(instruction.equals("STV")) {
			codeHexa = 4;
		} else if(instruction.equals("ADD")) {
			codeHexa = 5;
		} else if(instruction.equals("AND")) {
			codeHexa = 6;
		} else if(instruction.equals("JMP")) {
			codeHexa = 7;
		} else if(instruction.equals("BZ")) {
			codeHexa = 8;
		} else if(instruction.equals("NOT")) {
			codeHexa = 9;
		} else if(instruction.equals("SHR")) {
			codeHexa = 10;
		} else if(instruction.equals("PRT")) {
			codeHexa = 11;
		}		
		return codeHexa;
	}

	
}
