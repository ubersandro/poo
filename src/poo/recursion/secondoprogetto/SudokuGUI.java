package poo.recursion.secondoprogetto;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

@SuppressWarnings("serial")
public class SudokuGUI extends JFrame implements ActionListener {
    private JButton imposta, proxSol, prevSol, clear, risolvi, sblocca;
    private JPanel griglia, pannelloBottoni;
    private int indiceSoluzione;
    private JTextField matrice[][];//matrice GUI
    private Sudoku matriceTraccia; //usalo per tenere traccia delle soluzioni
    private int[][] matriceSalvataggio;//qui si salvano le configurazioni di partenza
    private ArrayList<int[][]> soluzioni;
    private JMenuBar bar;
    private JMenu file, info;
    private JMenuItem salva, carica, salvaNome, esci, about;
    private File salvataggio;

    public SudokuGUI() {
        setLayout(new BorderLayout());
        setTitle("Sudoku");
        Toolkit kit = Toolkit.getDefaultToolkit();
        int screenWidth = kit.getScreenSize().width;
        int screenHeight = kit.getScreenSize().height;
        setLocation(screenWidth / 4, screenHeight / 7);
        setSize(screenWidth / 2, screenHeight * 3 / 4);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                chiediSalvataggio();
                dispose();
            }//widnowClosing
        });//addWindowListener
        //MenuBar
        bar = new JMenuBar();
        this.setJMenuBar(bar);
        //fileMenu'
        file = new JMenu("File");

        carica = new JMenuItem("Carica");
        carica.addActionListener(this);
        esci = new JMenuItem("Esci");
        esci.addActionListener(this);
        salva = new JMenuItem("Salva");
        salva.addActionListener(this);
        salvaNome = new JMenuItem("Salva con nome");
        salvaNome.addActionListener(this);
        file.add(salva); salva.setEnabled(false); salvaNome.setEnabled(false);

        file.add(salvaNome);
        file.add(esci);
        file.add(carica);
        bar.add(file);
        //menu info
        info = new JMenu("?");
        bar.add(info);
        about = new JMenuItem("About");
        about.addActionListener(this);
        info.add(about);
        //pannello
        griglia = new JPanel(new GridLayout(9, 9));
        griglia.setSize(new Dimension(200, 200));
        add(griglia, BorderLayout.CENTER);
        inizializzazione();

        //pannello bottoni
        pannelloBottoni = new JPanel(new GridLayout(7, 1));
        //listenerBottoni= new ListenerBottoni();
        //creazioneBottoni
        imposta = new JButton("Imposta");
        imposta.addActionListener(this);
        imposta.setEnabled(true); //primo comando utile
        clear = new JButton("Cancella");
        clear.addActionListener(this);
        clear.setEnabled(false);
        sblocca = new JButton("Sblocca");
        sblocca.addActionListener(this);
        sblocca.setEnabled(false);
        proxSol = new JButton("Prossima soluzione");
        proxSol.addActionListener(this);
        proxSol.setEnabled(false);
        prevSol = new JButton("Soluzione precedente");
        prevSol.addActionListener(this);
        prevSol.setEnabled(false);
        risolvi = new JButton("Risolvi!");
        risolvi.addActionListener(this);
        risolvi.setEnabled(false);
        pannelloBottoni.add(imposta, 0);
        pannelloBottoni.add(new JSeparator(), 1);
        pannelloBottoni.add(clear, 2);
        pannelloBottoni.add(risolvi, 3);
        pannelloBottoni.add(prevSol, 4);
        pannelloBottoni.add(proxSol, 5);
        pannelloBottoni.add(sblocca, 6);
        add(pannelloBottoni, BorderLayout.WEST);

        //soluzioni
        soluzioni = new ArrayList<>(100);
       
    }//costruttore


    private void chiediSalvataggio() {
        int j = JOptionPane.showConfirmDialog(null,
                "Desideri effettuare un salvataggio?", "Salvare?", JOptionPane.YES_NO_OPTION);
        if (j == JOptionPane.YES_OPTION) try {
            JFileChooser jfc = new JFileChooser(
                    "C:\\Users");
            if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File f = jfc.getSelectedFile();
                salva(f);
            }
        } catch (IOException ioex) {
            //se non si riesce a salvare
            JOptionPane.showMessageDialog(null,
                    "Errore! Nessun salavataggio!");
        }//catch
    }//chiediSalvataggio

    private void salva(File f) throws IOException {
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        for (int i = 0; i < 9; i++)
            pw.println(Arrays.toString(matriceSalvataggio[i]));
        pw.close();
        File f1 = new File(f.getAbsolutePath() + ".txt");
        f1.delete();
        f.renameTo(f1);
    }//salva

    public void ripristina() throws IOException {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
                Sudoku s = new Sudoku() {
                    @Override
                    public void stampaSoluzione() {
                        soluzioni.add(getBoard());
                        setNumSol(getNumSol() + 1);
                    }//stampaSoluzione
                };
                File f = chooser.getSelectedFile();
                Scanner sc = new Scanner(f);
                String linea = null;
                int i = 0;
                int j = 0;
                while (sc.hasNextLine()) {
                    //try {
                        linea = sc.nextLine();
                        StringTokenizer st = new StringTokenizer(linea, "[], ");
                        while (st.hasMoreTokens()) {
                            String token = st.nextToken();
                            if (!(token.equals("0"))) s.imposta(i, j, Integer.parseInt(token));
                            j++;
                        }
                        i = (i + 1) % 9;
                   
                }//while
                svuota();
                sc.close();
                int[][] rip = s.getBoard();
                for (int r = 0; r < matrice.length; r++)
                    for (int c = 0; c < matrice.length; ++c)
                        if (rip[r][c] != 0) matrice[r][c].setText(String.valueOf(rip[r][c]));
                matriceTraccia = s;
                matriceSalvataggio = matriceTraccia.getBoard(); // i vincoli sono pronti ad essere salvati
                
            }//ifExists
           
         else JOptionPane.showMessageDialog(null, "Nessun ripristino!");
    }//ripristina

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imposta) {
            try {
                imposta();
                sblocca.setEnabled(true); salvaNome.setEnabled(true); salva.setEnabled(true);
                risolvi.setEnabled(true);
                clear.setEnabled(true);
                imposta.setEnabled(false);
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null,
                        "Alcuni vincoli imposti non soddisfano le regole.");
            }//catch
        }//imposta
        if (e.getSource() == clear) {
            svuota(); salva.setEnabled(false); salvaNome.setEnabled(false);
            imposta.setEnabled(true);
            clear.setEnabled(false);
            sblocca.setEnabled(false);
            risolvi.setEnabled(false);
            proxSol.setEnabled(false);
            prevSol.setEnabled(false);
        }
        if (e.getSource() == sblocca) {
            editable(true);
            sblocca.setEnabled(false);
            imposta.setEnabled(true);
        }
        if (e.getSource() == prevSol) {
            indiceSoluzione--;
            if (indiceSoluzione == 0) prevSol.setEnabled(false);
            else if (!proxSol.isEnabled()) proxSol.setEnabled(true);
            scriviSoluzione();

        }
        if (e.getSource() == risolvi) {
            matriceTraccia.risolvi();
            scriviSoluzione();
            sblocca.setEnabled(false);
            risolvi.setEnabled(false);
            proxSol.setEnabled(true);
        }
        if (e.getSource() == proxSol) {
            indiceSoluzione++;
            if (indiceSoluzione > soluzioni.size()) proxSol.setEnabled(false);
            else if (!prevSol.isEnabled()) prevSol.setEnabled(true);
            scriviSoluzione();
        }
        if (e.getSource() == salva) {
            try {
                File f=null;
                if (salvataggio == null) {
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        f= chooser.getSelectedFile();
                    }
                    else JOptionPane.showMessageDialog(null,
                            "Nessun salvataggio!");
                }//ifSalvataggioNull
                salva(f);
                salvataggio=f;
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(null,
                        "Non e' stato possibile salvare.");
            }
        }//salva
        if (e.getSource() == salvaNome) {

            JFileChooser chooser = new JFileChooser();
            try {
                File f=null;
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    f=chooser.getSelectedFile(); salva(f);
                }//if
            } catch (IOException exc) {
                JOptionPane.showMessageDialog(null,
                        "Non e' stato possibile salvare.");
            }

        }//salvaNome
        if (e.getSource() == carica) {
            try {
                ripristina();
            } catch (IOException iex) {
            }
        }//carica
        if (e.getSource() == esci) {
            chiediSalvataggio();
            System.exit(0);
        }
    }//actionListener

    private void scriviSoluzione() {
    	int[][] soluzioneAttuale=soluzioni.get(indiceSoluzione);
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                matrice[i][j].setText(String.valueOf(soluzioneAttuale[i][j] )); //TODO PENSARE SOLUZIONE PIU' EFFICIENTE
    }//scriviSoluzione

    private void svuota() {
        griglia.removeAll();
        inizializzazione();
        griglia.revalidate();
        griglia.repaint();
        matriceTraccia = null;
        matriceSalvataggio = null;
        indiceSoluzione = 0;
        soluzioni = new ArrayList<>();
        indiceSoluzione = 0;
        //aggiornaTasti();
    }//svuota

    private void imposta() {
        Sudoku s = new Sudoku() {
            @Override
            public void stampaSoluzione() {
                soluzioni.add(getBoard());
                setNumSol(getNumSol() + 1);
            }//stampaSoluzione
        };
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice.length; ++j) {
                if (!matrice[i][j].getText().equals("")) {
                    try {
                        s.imposta(i, j, Integer.parseInt(matrice[i][j].getText()));
                    } catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(null, "Errore nella cella:<"+i+","+j+">");
                    }
                }//if
            }//for
        }
        //se i vincoli andavano bene
        for (int i = 0; i < matrice.length; i++)
            for (int j = 0; j < matrice.length; ++j)
                if (!matrice[i][j].getText().equals("")) {
                    matrice[i][j].setFont(new Font(
                            "Helvetica", Font.BOLD, 12));
                }
        editable(false);
        matriceTraccia = s;
        matriceSalvataggio = matriceTraccia.getBoard(); // i vincoli sono pronti ad essere salvati
    }//imposta

    private void editable(boolean b) {
        for (int i = 0; i < matrice.length; i++)
            for (int j = 0; j < matrice.length; j++)
                matrice[i][j].setEditable(b);
    }//blocca


    //per inizializzare la matrice di JTextFields
    private void inizializzazione() {
        matrice = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                matrice[i][j] = new JTextField("", 2);
                matrice[i][j].setHorizontalAlignment(JTextField.CENTER);
                griglia.add(matrice[i][j]);
            }//forJ
        }//forI
    }//inizializza


    public static void main(String... args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                SudokuGUI s = new SudokuGUI();
                s.setVisible(true);
            }//run
        });//invokeLater
    }//main
}//SudokuGUI
