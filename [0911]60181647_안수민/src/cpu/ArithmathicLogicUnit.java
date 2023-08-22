package cpu;

public class ArithmathicLogicUnit {
	Register ac, mbr, status;
	
	public void connect(Register ac, Register mbr, Register status) {
		this.ac = ac;
		this.mbr = mbr;
		this.status = status;
	}

	public void ADD() {
		this.ac.setData(this.ac.getData() + this.mbr.getData());
	}

	public void AND() {
		this.ac.setData(this.ac.getData() & this.mbr.getData());
	}

}
