package poo.geometria;

public interface FiguraSolida extends FiguraPiana{
	class AreaDati{
		String ciao;
		public String toString() {
			return null;
		}
	}
	double areaDiBase();
	double areaLaterale(); //public di default
	double volume(); //ci sono 5 metodi + quelli di FiguraPiana
}//FiguraSolida
