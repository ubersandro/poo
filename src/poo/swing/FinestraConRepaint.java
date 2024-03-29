//*********************************************************
//
//  file FinestraConRepaint.java
//
//  Altro esempio di finestra creabile con Java swing
//  che risponde all'evento di chiusura (si clicca
//  sul pulsante X o si digita ALT-F4 etc.) ed
//  ammette un pannello per la visualizzazione grafica.
//  Ad ogni ri-visualizzazione della
//  finestra (es. massimizzazione dopo una minimizzazione), 
//  il metodo paintComponent
//  sopperisce al fatto che Java AWT/Swing non memorizza
//  i pixel di una finestra e ri-crea la grafica di interesse.
//  Nell'esempio, la ri-visualizzazione e' anche invocata
//  da mouse click sul pannello.
//*********************************************************
package poo.swing;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings({ "serial", "unused" })
class FinestraRepaint extends JFrame{
	
   private Pannello p=null;
   private Font f=new Font("Helvetica", Font.BOLD, 20);
   private Color col=new Color( /*red*/57, /*green*/128, /*blu*/110 );
   
   public FinestraRepaint(){
      setTitle("Finestra con Repaint");
      setSize(400,200);
      setLocation(50,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      add( p=new Pannello() );
   }

    private class Pannello extends JPanel{ //esempio di JComponent
      public void paintComponent(Graphics g){
    	 super.paintComponent(g);
    	 try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
         System.out.println("paintComponent chiamata");
         setBackground( Color.white );
         g.setFont( f );
         g.setColor( col );
         if( Math.random()<0.5 )
        	 g.drawString( "Repainting the world", 30, 40 );
         else
        	 g.drawString( "Repainting the world", 100, 100);
      }
   }//Pannello

}//FinestraRepaint

public class FinestraConRepaint{
   public static void main( String []args ){
/*
	  EventQueue.invokeLater( new Runnable(){
		  public void run(){
		      JFrame f=new FinestraRepaint();
		      f.setVisible(true);			  
		  }
	  });
*/
	  JFrame f=new FinestraRepaint();
	  f.setVisible(true);
   }
}//FinestraConRepaint
