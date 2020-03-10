package poo.test;

import java.io.Serializable;

public class TestSerialization implements Serializable {
	private static final long serialVersionUID = 1L;
	private int ciao;
	public TestSerialization() {
		ciao=0;
	}
	public int getCiao() {
		return ciao;
	}
	public void setCiao(int ciao) {
		this.ciao = ciao;
	}
}
