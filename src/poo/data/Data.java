package poo.data;
import java.util.GregorianCalendar;

public class Data implements Comparable<Data>{ //Comparable è il suo SUPERTIPO
	private final int G, M, A;
	public static final int GIORNO=0, MESE=1, ANNO=2;
	public Data(){
		GregorianCalendar gc=new GregorianCalendar();
		G=gc.get( GregorianCalendar.DAY_OF_MONTH );
		M=gc.get( GregorianCalendar.MONTH )+1;
		A=gc.get( GregorianCalendar.YEAR );
	}//Data
	public Data( int g, int m, int a ){
		if( a<0 || m<1 || m>12 || g<1 || g>durataMese(m,a) )
			throw new IllegalArgumentException();
		this.G=g; this.M=m; this.A=a;
	}//Data
	public Data( Data d ){
		G=d.G; M=d.M; A=d.A;
	}//Data
	public int get( int cosa ){
		switch( cosa ){
			case GIORNO: return G;
			case MESE: return M;
			case ANNO: return A;
			default: return -1;
		}
	}//get
	public static boolean bisestile( int a ){
		if( a<0 )
			throw new IllegalArgumentException();
		if( a%4!=0 ) return false;
		if( a%100==0 && a%400!=0 ) return false;
		return true;
	}//bisestile
	public static int durataMese( int m, int a ){
		if( m<1 || m>12 || a<0 )
			throw new IllegalArgumentException();
		int durata;
		switch( m ){
			case 1: case 3: case 5: case 7: case 8:
			case 10: case 12: durata=31; break;
			case 2: durata=bisestile(a) ? 29:28; break;
			default: durata=30;
		}//switch
		return durata;
	}//durataMese
	public Data giornoDopo(){
		int durata=durataMese( M, A );
		int g1, m1, a1;
		if( G==durata ){
			g1=1;
			if( M==12 ){ m1=1; a1=A+1; }
			else{ m1=M+1; a1=A; }
		}
		else{ g1=G+1; m1=M; a1=A; }
		return new Data( g1,m1,a1 );
	}//giornoDopo
	/*public Data giornoPrima(){
		//lasciato come esercizio
		return null; //TODO
	}//giornoPrima
	*/
	public int distanza( Data d ){// d>this
		if(this.compareTo(d) < 0) throw new IllegalArgumentException();
		if(this.compareTo(d)==0 ) throw new IllegalArgumentException("Stessa data.") ;
		if(A==d.A){
			if(M==d.M) return d.G-G;
			else{	
				int somma=0;
				for(int i=this.M+1;i<=d.M;i++){//questa parentesi � necessaria?
					somma+=durataMese(i,A);
				}//for
				return (durataMese(this.M, this.A) - this.G) + somma - d.G;
			}//else
		}//if ANNO UGUALE	
		if(A!=d.A){
			int ret=durataMese(this.M,this.A)- this.G;
			for(int a=this.A; a<=d.A; a++){
				for(int m=(a==this.A?this.M:1); m<=12; m++){
					if(a==d.A&&m==d.M) return ret+durataMese(m,a)-d.G;
					ret+=durataMese(m,a);
				}//for mese
			}//for anno
		}//if ANNO diverso
	return 0;
	}//distanza


	public String toString(){
		return G+"/"+M+"/"+A;
	}//toString


	public boolean equals(Object x){ //uguaglianza profonda perch� se faccio == restituisce true solo se sono uguali i riferimenti(caso x==this)
 		if(!(x instanceof Data)) return false; //copre anche il caso in cui x � null (perch�?)
		if(x==this) return true;
		Data d=(Data) x; // non da ClassCastException
		return G==d.G&&M==d.M&&A==d.A;
	}//equals

	@Override //"compilatore, io e te ci stiamo capendo"
	/*
	public int compareTo(Object x){ //VECCHIO GREZZO 
		Data d= (Data) x; // se non è di tipo di Data l'eccezione la solleva solo!
		if(A<d.A|| A==d.A &&M<d.M ||A==d.A &&M==d.M &&G<d.G) return -1; //non è detto che sia -1!
		if(this.equals(d)) return 0; //affinità con l'equals
		return 1;
	}//compareTo
*/
	public int compareTo(Data d) {
		if(A<d.A|| A==d.A &&M<d.M ||A==d.A &&M==d.M &&G<d.G) return -1;
		if(this.equals(d)) return 0;
		return 1;
	}//new compareTo(Data d)
	
	
	public int hashCode(){
		final int MUL=43; //numero primo
		int h=0; //qui generiamo l'hashCode
		h=h * MUL + G;
		h=h * MUL + M; //shuffling
		h=h * MUL + A;
		return h; // se hanno lo stesso hashcode non � detto che siano uguali
	}//hashCode 


	public static void main( String []args ){//demo
		Data d=new Data();
		System.out.println("Oggi e' il "+d);
		System.out.println("Domani e' il "+d.giornoDopo());
		d=new Data( 28, 2, 2000 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		Data d2=new Data( 28, 2, 2008 );
		System.out.println("Il giorno dopo il "+d2+" e' il "+d2.giornoDopo());
		System.out.println("DISTANZA " + d.distanza(d2));
		d=new Data( 28, 2, 2009 );
		System.out.println("Il giorno dopo il "+d+" e' il "+d.giornoDopo());
		if( Data.bisestile(2008) )
			System.out.println("Il 2008 e' un anno bisestile");
		System.out.println("Giorno di "+d+" = "+d.get(Data.GIORNO));
		System.out.println("Mese di "+d+" = "+d.get(Data.MESE));
		System.out.println("Anno di "+d+" = "+d.get(Data.ANNO));
	}//main
}//Data
