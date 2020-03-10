package agendinaappuntimiei;

public abstract class AgendinaAstratta implements Agendina {
	public String toString() {
		StringBuilder sb= new StringBuilder(500);
		for(Nominativo n: this) {
			sb.append(n+"/n");
		}
		return sb.toString();
	}

	public boolean equals(Object x) {
		return false; //TODO
	}
	
	public int hashCode() {
		return 0; // TODO 
	}
}
