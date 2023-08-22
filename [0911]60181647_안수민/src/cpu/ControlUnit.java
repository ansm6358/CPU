package cpu;

public class ControlUnit {
	private Register pc, sp, mar, mbr, ir ,ac, status;

	public void connect(Register pc, Register ir, Register sp, Register mar, Register status) {
		this.pc = pc;
		this.ir = ir;
		this.sp = sp;
		this.mar = mar;
		this.status = status;
	}
	
}
