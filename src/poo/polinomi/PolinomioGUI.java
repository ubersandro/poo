package poo.polinomi;

import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static poo.polinomi.Applicazione.Implementazione.*;


/*
 * GUI per l'esecuzione di operazioni con i Polinomi.
 *
 */
@SuppressWarnings("serial")
public class PolinomioGUI extends JFrame {
	private JPanel pannello;
	private JMenuBar barra;
	private JMenu file, comandi;
	private JMenuItem nuovo, salva, carica, salvaNome, derivata, valuta, somma, differenza, prodotto, rimozione;
	private JTextField ins;
	Applicazione.Implementazione impl;
	private ItemListener listBoxes;
	private int selectionCounter;
	private File salvataggio;
	private Map<JCheckBox, Polinomio> mappa;
	private ActionListener listener; 
	
	public PolinomioGUI() {
		// dimensionamento finestra
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		PolinomioGUI.this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				chiediSalvataggio();
				System.exit(0);
			}// windowClosing
		});// addWindowListener
		setSize(800, 400);
		setLocation(300, 200);
		setTitle("Operazioni con i polinomi");
		// listener
		listener = new AscoltatoreMenuBar();
		// area menu
		barra = new JMenuBar();
		setJMenuBar(barra);
		// aggiunta del menu file
		file = new JMenu("File");
		barra.add(file);
		nuovo = new JMenuItem("Nuovo");
		nuovo.addActionListener(listener);
		file.add(nuovo);
		salva = new JMenuItem("Salva");
		salva.addActionListener(listener);
		file.add(salva);
		salvaNome = new JMenuItem("Salva con nome");
		salvaNome.addActionListener(listener);
		file.add(salvaNome);
		carica = new JMenuItem("Carica");
		carica.addActionListener(listener);
		file.add(carica);
		// aggiunta del menu comandi
		comandi = new JMenu("Comandi");
		barra.add(comandi);
		derivata = new JMenuItem("Derivata prima");
		derivata.addActionListener(listener);
		comandi.add(derivata);
		somma = new JMenuItem("Somma");
		somma.addActionListener(listener);
		comandi.add(somma);
		differenza = new JMenuItem("Differenza");
		differenza.addActionListener(listener);
		comandi.add(differenza);
		prodotto = new JMenuItem("Prodotto");
		prodotto.addActionListener(listener);
		comandi.add(prodotto);
		valuta = new JMenuItem("Valuta in x");
		valuta.addActionListener(listener);
		comandi.add(valuta);
		rimozione = new JMenuItem("Rimuovi");
		rimozione.addActionListener(listener);
		comandi.add(rimozione);
		// pannello
		mappa = new HashMap<>();
		inizializzaPannello(mappa);
		JFrame i = new FrameImpl();
		this.setVisible(false);
		i.setVisible(true);
		//implementazione
		impl=LINKEDLIST;//default value
		menuIniziale();

		listBoxes = new CheckBoxesListener();
		
	}// costruttore

	private void chiediSalvataggio() {
		int j = JOptionPane.showConfirmDialog(null, "Vuoi salvare il contenuto dello spazio di lavoro?", "Salvare?",
				JOptionPane.YES_NO_OPTION);
		if (j == JOptionPane.YES_OPTION)
			try {
				if (salvataggio != null)
					salva(salvataggio);
				else {
					salvaConNome();
				}
			} catch (IOException e) {
			}
	}// chiediSalvataggio

	private void nuovoSpazioDiLavoro() {
		remove(pannello);
		mappa = new HashMap<>();
		inizializzaPannello(mappa);
	}// nuovoSL

	
	private void inizializzaPannello(Map<JCheckBox, Polinomio> mappa) {
		pannello = new JPanel(new FlowLayout(0)) {
			public void revalidate() {
				super.revalidate();
				for (JCheckBox box : mappa.keySet())
					if (box.isSelected())
						box.setSelected(false);
			}// revalidate
		};// layout border di default
		ActionListener listPannello = new ListenerPannello();
		ins = new JTextField(14);
		ins.addActionListener(listPannello);
		JLabel label = new JLabel("Inserisci polinomio");
		pannello.add(ins);
		pannello.add(label);
		for (JCheckBox b : mappa.keySet()) {
			pannello.add(b, FlowLayout.RIGHT);
			b.addItemListener(listBoxes);
		} // for
		add(pannello);
		this.revalidate();
		this.repaint();
		selectionCounter = 0;
	}// inizializzaPannello

	private void menuIniziale() {
		salva.setEnabled(false);
		salvaNome.setEnabled(false);
		somma.setEnabled(false);
		differenza.setEnabled(false);
		derivata.setEnabled(false);
		valuta.setEnabled(false);
		prodotto.setEnabled(false);
		rimozione.setEnabled(false);
	}

	private class FrameImpl extends JFrame implements ActionListener {
		 JRadioButton ll, al, set, map, conc;
		 ButtonGroup gruppo;
		private FrameImpl() {
			setTitle("Selezione implementazione");
			Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			setLocation(d.width / 2, d.height / 4);
			setSize(400, 400);
			setLayout(new GridLayout(5, 1));
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					PolinomioGUI.this.setVisible(true);
					dispose();
				}
			});

			gruppo = new ButtonGroup();
			ll = new JRadioButton("LinkedList", true);
			ll.addActionListener(this);
			al = new JRadioButton("ArrayList", false);
			al.addActionListener(this);
			set = new JRadioButton("TreeSet", false);
			set.addActionListener(this);
			conc = new JRadioButton("Lista concatenata", false);
			conc.addActionListener(this);
			map = new JRadioButton("TreeMap", false);
			map.addActionListener(this);
			gruppo.add(al);
			gruppo.add(ll);
			gruppo.add(set);
			gruppo.add(conc);
			gruppo.add(map);
			add(ll, 0);
			add(al, 1);
			add(set, 2);
			add(map, 3);
			add(conc, 4);
		}// costruttore

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == al)
				impl = ARRAYLIST;
			if (e.getSource() == ll)
				impl = LINKEDLIST;
			if (e.getSource() == set)
				impl = SET;
			if (e.getSource() == map)
				impl = MAP;
			if (e.getSource() == conc)
				impl = LISTA_CONCATENATA;
			
		}
	}// FrameImpl

	private class ListenerPannello implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == ins) {
				try {
					Polinomio p = Applicazione.costruisciPolinomio(ins.getText(), impl);
					JCheckBox box = new JCheckBox(p.toString());
					box.addItemListener(listBoxes);
					pannello.add(box);
					ins.setText("");
					mappa.put(box, p);
					pannello.revalidate();
					salva.setEnabled(true);
					salvaNome.setEnabled(true);
				} catch (RuntimeException exception) {
					JOptionPane.showMessageDialog(null, "Errore, hai inserito un'espressione scorretta");
				}
			} // ins
		}// actionPerformedPannello
	}// ListenerPannello

	private class AscoltatoreMenuBar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nuovo) {
				nuovoSpazioDiLavoro();
			} // pulisce lo spazio di lavoro

			if (e.getSource() == carica) {
				JFileChooser jfc = new JFileChooser();
				try {
					if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						if (jfc.getSelectedFile().exists()) {
							ripristina(jfc.getSelectedFile());
							salvataggio = jfc.getSelectedFile();
						}
						else {
							throw new IOException();
						} // else
					} // ifApproveOption
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Nessun ripristino!");
				}
			} // carica
			
			if (e.getSource() == salva) {
				JFileChooser chooser = new JFileChooser();
				try {
					if (salvataggio != null) {
						int ris = JOptionPane.showConfirmDialog(null, "Sovrascrivere" + salvataggio + "?");
						if (ris == 0)
							salva(salvataggio);
						else
							JOptionPane.showMessageDialog(null, "Nessun salvataggio effettuato.");
					} else {
						if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
							salvataggio = chooser.getSelectedFile();
						if (salvataggio != null)
							salva(salvataggio);
					} // else
				} catch (IOException exc) {
					exc.printStackTrace();
				}
			} // salva

			if (e.getSource() == salvaNome)
				salvaConNome();

			if (e.getSource() == derivata) {
				if (selectionCounter > 1)
					JOptionPane.showMessageDialog(null, "Errore, non si puo' derivare piu' di un polinomio la volta.");
				else {
					for (JCheckBox b : mappa.keySet())
						if (b.isSelected()) {

							Polinomio pb = mappa.get(b);
							try {
							pb = pb.derivata();
							if(pb.size()!=0) {
								mappa.replace(b, pb);
								b.setText(pb.toString());
							}else{
								mappa.remove(b);
								pannello.remove(b); selectionCounter--; primeFunzioni(false);
							}
							pannello.revalidate();
							pannello.repaint();
							}catch(RuntimeException exc) {
								JOptionPane.showMessageDialog(null,"Errore!");
							}
						} // ifBselected
				} // else
			} // derivata
			if (e.getSource() == valuta) {
				if (selectionCounter > 1)
					JOptionPane.showMessageDialog(null, "Prego, selezionare un solo polinomio per volta");
				else{
					Polinomio p = null;
				
				String punto = null;
				for (JCheckBox b : mappa.keySet())
					if (b.isSelected()) {
						p = mappa.get(b);
						punto = JOptionPane.showInputDialog(null, "In che punto vuoi valutare l'epressione?");
						JOptionPane.showMessageDialog(null, p.valore(Double.parseDouble(punto)));
						//pannello.revalidate();
					}
				}
			} // valuta

			if (e.getSource() == somma) {
				if (selectionCounter < 2 || selectionCounter > 2)
					JOptionPane.showMessageDialog(null, "Errore");
				else {
					Polinomio risultato = null;
					
					for (JCheckBox b : mappa.keySet())
						if (b.isSelected()) {
							if (risultato == null)
								risultato = mappa.get(b);
							else
								risultato = risultato.add(mappa.get(b));
						} // if b.isSelected
					if(risultato.size()!=0) {
						
					
					JCheckBox box = new JCheckBox(risultato.toString());
					box.addItemListener(listBoxes);
					mappa.put(box, risultato);
					pannello.add(box);
					pannello.revalidate();
					}
				} // else
			} // somma

			if (e.getSource() == differenza) {
				if (selectionCounter > 2) {
					JOptionPane.showMessageDialog(null, "Selezionare solo due polinomi!");
					return;
				} // if
				Polinomio p1 = null, p2 = null;
				for (JCheckBox b : mappa.keySet())
					if (b.isSelected()) {
						if (p1 == null)
							p1 = mappa.get(b);
						else
							p2 = mappa.get(b);
					} // if b.isSelected
				FrameDifferenza f = new FrameDifferenza(p1, p2);
				f.setVisible(true);
			}

			if (e.getSource() == prodotto) {
				if (selectionCounter < 2 || selectionCounter > 2)
					JOptionPane.showMessageDialog(null, "Errore");
				Polinomio risultato = null;
				for (JCheckBox b : mappa.keySet())
					if (b.isSelected()) {
						if (risultato == null)
							risultato = mappa.get(b);
						else
							risultato = risultato.mul(mappa.get(b));
					} // if b.isSelected
				JCheckBox box = new JCheckBox(risultato.toString());
				box.addItemListener(listBoxes);
				mappa.put(box, risultato);
				pannello.add(box);
				pannello.revalidate();
			}
			if (e.getSource() == rimozione) {
				Iterator<JCheckBox> it = mappa.keySet().iterator();
				while (it.hasNext()) {
					JCheckBox current = it.next();
					if (current.isSelected()) {
						current.setSelected(false);
						pannello.remove(current);
						mappa.remove(current);
					} // if is selected
				} // while				
				pannello.revalidate();
				pannello.repaint();
				if(mappa.size()==0) menuIniziale();
			} // rimozione

		}// actionPerformed
	}// AscoltatoreMenuBar

	private class FrameDifferenza extends JFrame {
		private JRadioButton opzione1, opzione2;
		private ButtonGroup opzioni;
		private JPanel panel;
		JButton ok;

		public FrameDifferenza(Polinomio p1, Polinomio p2) {
			setSize(300, 300);
			setLocation(738, 300);
			String segno=(p2.getSegno())?"":"+";
			String differenza1 = p1.toString() +segno+ p2.mul(new Monomio(-1, 0)).toString();
			segno=(p1.getSegno())?"":"+";
			String differenza2 = p2.toString() +segno+ p1.mul(new Monomio(-1, 0)).toString();
			opzione1 = new JRadioButton(differenza1,true);
			opzione2 = new JRadioButton(differenza2,false);
			opzioni= new ButtonGroup();
			opzioni.add(opzione1);opzioni.add(opzione2);
			panel = new JPanel(new GridLayout(2, 3));
			this.add(panel);
			panel.add(opzione1);
			panel.add(new JSeparator());
			panel.add(opzione2);
			ok = new JButton("OK");
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == ok) {
						String scelta = null;
						if (opzione1.isSelected())
							scelta = opzione1.getText();
						else
							scelta = opzione2.getText();
						try {
						JOptionPane.showMessageDialog(null, Applicazione.costruisciPolinomio(scelta, impl));
						PolinomioGUI.FrameDifferenza.this.dispose();
						Polinomio risultato=Applicazione.costruisciPolinomio(scelta, impl);
							JCheckBox nuova = new JCheckBox(risultato.toString());
							nuova.addItemListener(listBoxes);
							mappa.put(nuova, Applicazione.costruisciPolinomio(scelta, impl));
							pannello.add(nuova);
							pannello.revalidate();							
						}catch(RuntimeException exc) {
							JOptionPane.showMessageDialog(null, "0");
							PolinomioGUI.FrameDifferenza.this.dispose();
						}
					} // ok
				}// actionPerformed
			});
			panel.add(ok);
		}// costruttore
	}// FrameDifferenza

	private class CheckBoxesListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.DESELECTED)
				selectionCounter--;
			else
				selectionCounter++;
			if (selectionCounter == 0)
				primeFunzioni(false);
			if (selectionCounter == 1)
				primeFunzioni(true);
			if (selectionCounter > 1)
				abilitaComandi(true);
			else
				abilitaComandi(false);
		}// itemStateChanged
	}// CheckBoxesListener

	private void abilitaComandi(boolean flag) {
		somma.setEnabled(flag);
		prodotto.setEnabled(flag);
		differenza.setEnabled(flag);
	}// abilitaComandi

	private void primeFunzioni(boolean flag) {
		rimozione.setEnabled(flag);
		valuta.setEnabled(flag);
		derivata.setEnabled(flag);
	}// primeFunzioni

	private void salva(File f) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(mappa);
		oos.close();
	}// salva

	private void salvaConNome() {
		JFileChooser jfc = new JFileChooser();
		try {
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File f = jfc.getSelectedFile();
				if (f != null)
					salva(f);
				else throw new IOException();
			}
		} catch (IOException ioex) {
			JOptionPane.showMessageDialog(null,"Nessun salvataggio");
		}
	}// salvaConNome

	@SuppressWarnings("unchecked")
	private void ripristina(File f) throws IOException {
		Map<JCheckBox, Polinomio> rip = null;
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
		try {
			rip = (Map<JCheckBox, Polinomio>) ois.readObject();
		} catch (EOFException e1) {
			
		} catch (ClassNotFoundException cnfe) {
			ois.close();
			throw new IOException();
			
		} catch (ClassCastException cce) {
			ois.close();
			throw new IOException();
		}
		ois.close();

		PolinomioGUI.this.remove(pannello);
		if(rip!=null) {
			mappa = rip;
			inizializzaPannello(rip);
			revalidate();
			repaint();
		}
		else throw new IOException();
		}// ripristina

	public static void main(String... strings) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*JFrame finestra =*/ new PolinomioGUI();
			}
		});
	}// main
}// PolinomioGUI
