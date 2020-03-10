package poo.giochi;

public class ApprossimazionePI {
	private double pi;
	private long colpiTutti=0;
	private long colpiInterni=0;
	public void monteCarlo( long n ) {
		double x=0, y=0;
		for( long i=0; i<n; ++i ) {
			//genera un double tra -1 e 1 per x e per y
			x=Math.random()*2-1;
			y=Math.random()*2-1;
			//verifica dove ricade il "colpo"
			colpiTutti++;
			if( Math.sqrt(x*x+y*y)<=1.0 ) colpiInterni++;
		}
		pi=4*((double)colpiInterni/colpiTutti);
	}//monteCarlo
	public String toString() {
		return "PI="+pi;
	}
	public static void main( String[] args ) {
		ApprossimazionePI pi=new ApprossimazionePI();
		pi.monteCarlo( 5000000000L );
		System.out.println(pi);
	}

}
