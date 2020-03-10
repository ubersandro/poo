package poo.lambda;

public class TestLambda {
	@FunctionalInterface 
	interface Fact{
		long fact(int n);
	}
	//@FunctionalInterface
	interface Ciao{
		static long factorial(int n) {
			if(n==1)return 1; 
			return n*factorial(n-1);
		}
	}
	public static void main(String[] args) {
		Fact f= new Fact() {
			@Override
			public long fact(int n) {
				if(n==1)return 1; 
				return n*fact(n-1);
			}
		};
		System.out.println(f.fact(5));
		System.out.println(Ciao.factorial(7));
	}

}
