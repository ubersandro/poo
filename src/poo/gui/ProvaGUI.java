package poo.gui;

import javax.swing.*;

@SuppressWarnings({ "serial" })
public class ProvaGUI extends JFrame{
	private JPanel pannello;
	public ProvaGUI() {
		pannello=new JPanel();
		add(pannello);
		String[] a= {"porcodio"};
		JComboBox<String> box=new JComboBox<>(a);
		pannello.add(box);
		
		
	}
	
	public static void main(String...strings ) {
		JFrame j= new ProvaGUI();
		j.setVisible(true);
	}
}
