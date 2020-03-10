package eratostene;

public interface Crivello extends Iterable<Integer>{
	
	
	@SuppressWarnings("unused")
	default int size() {
		int c=0;
		for( int x: this) c++;
		return c;
	}//size

	default boolean isPrime(int x) {
		for(int y: this) {
			if(x==y) return true;
		}//for
		return false;
	}//isPrime
	
	
	void filtra();
	
}//Crivello
