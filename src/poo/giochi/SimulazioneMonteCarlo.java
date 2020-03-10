package poo.giochi;


public class SimulazioneMonteCarlo {
	
	public static double MonteCarlo(){
		double pi=0;
		long colpiInterni=0;
		long colpiTotali=0;
		for(long i=0;i<Long.MAX_VALUE/16;i++){
			double x=Math.random()*2 +1;
			double y=Math.random()*2+1;
			colpiTotali++;
			if(Math.sqrt(x*x+y*y) <1)colpiInterni++;
		}//for
		pi=4*((double) colpiInterni/colpiTotali);
		return pi;
	}//MonteCarlo
	
	public static void main(String ... args ) {
		System.out.println(MonteCarlo());
	}//main
	
	
	
}//SimulazioneMonteCarlo
