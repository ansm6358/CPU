package memory;

import cpu.Register;

public class Memory {
	int buffer[];
	private Register mar, mbr;
	
	public void connect(Register mar,Register mbr) {
		this.mar = mar;
		this.mbr = mbr;
	}
	public Memory() {
		this.buffer = new int[100];
	}
	
	public void fetch() {
		
	}
	public void store() {
		
	}
}
