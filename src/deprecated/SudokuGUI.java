package deprecated;
//TODO salva(File f), listener salvaConNome, salva, carica
//TODO rivedi algoritmo

import javax.swing.*;
import javax.swing.border.Border;

import poo.recursion.secondoprogetto.Sudoku;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SudokuGUI extends JFrame implements ActionListener {
    private JButton imposta, proxSol, prevSol, clear, risolvi, sblocca;
    private JPanel griglia, pannelloBottoni;
    private boolean corretto;
    int indiceSoluzione;
    private JTextField matrice[][];//matrice GUI
    private Sudoku matriceTraccia; //usalo per tenere traccia delle soluzioni
    private int[][] matriceSalvataggio;//qui si salvano le configurazioni di partenza
    private ArrayList<int[][]> soluzioni;
    private JMenuBar bar;
    private JMenu file, info;
    private JMenuItem nuovo, salva, carica, salvaNome, esci, about;
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
        nuovo = new JMenuItem("Nuovo");
        nuovo.addActionListener(this);
        carica = new JMenuItem("Carica");
        carica.addActionListener(this);
        esci = new JMenuItem("Esci");
        esci.addActionListener(this);
        salva = new JMenuItem("Salva");
        salva.addActionListener(this);
        salvaNome = new JMenuItem("Salva con nome");
        salvaNome.addActionListener(this);
        file.add(salva);
        file.add(nuovo);
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
        //griglia.setSize(200, 200);
        add(griglia, BorderLayout.CENTER);
        inizializzazione();

        //pannello bottoni
        pannelloBottoni = new JPanel(new GridLayout(7, 1));
        //listenerBottoni= new ListenerBottoni();
        //creazioneBottoni
        imposta = new JButton("Imposta");
        imposta.addActionListener(this);
        clear = new JButton("Cancella");
        clear.addActionListener(this);
        sblocca = new JButton("Sblocca");
        sblocca.addActionListener(this);
        proxSol = new JButton("Prossima soluzione");
        proxSol.addActionListener(this);
        prevSol = new JButton("Soluzione precedente");
        prevSol.addActionListener(this);
        risolvi= new JButton("Risolvi!");
        risolvi.addActionListener(this);
        pannelloBottoni.add(imposta, 0);
        pannelloBottoni.add(new JSeparator(), 1);
        pannelloBottoni.add(clear, 2);
        pannelloBottoni.add(risolvi, 3);
        pannelloBottoni.add(prevSol, 4);
        pannelloBottoni.add(proxSol, 5);
        pannelloBottoni.add(sblocca, 6);
        add(pannelloBottoni, BorderLayout.WEST);
        //soluzioni e traccia
        /*
        soluzioni = new ArrayList<>(100);
        matriceTraccia = new Sudoku3(){
            public void stampaSoluzione(){
                soluzioni.add(getBoard());
                numSol++;
            }//stampaSoluzione
        };
         */
        setSize(500, 400);
        aggiornaTasti();
    }//costruttore

    //TODO vedi Horstmann
    private void chiediSalvataggio(){
        int j= JOptionPane.showConfirmDialog(null,
                "Desideri effettuare un salvataggio?","Salvare?",JOptionPane.YES_NO_OPTION);
       if(j==JOptionPane.YES_OPTION)try {
           JFileChooser jfc= new JFileChooser("C:\\Users\\aless\\OneDrive - Università della Calabria");
           if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
               File f = jfc.getSelectedFile();
               /*if (f.exists())*/ salva(f);
           }
       }catch (IOException ioex){
           JOptionPane.showMessageDialog(null,
                   "Errore!Nessun salavataggio!");
       }//catch
    }//chiediSalvataggio

    private void salva(File f) throws IOException{
        PrintWriter pw= new PrintWriter(new BufferedWriter(new FileWriter(f)));
        for(int i=0; i<9; i++)
            pw.println(Arrays.toString(matriceSalvataggio[i]));
        pw.close();
    }//salva
    //TODO RISCRIVI 
    public void ripristina() throws IOException{
        JFileChooser chooser= new JFileChooser();
        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
            if(chooser.getSelectedFile().exists()){
                File f= chooser.getSelectedFile();
                Scanner sc= new Scanner(f);
                String linea=null; boolean okCaricamento=true;
                int i=0; int j=0;
                while(sc.hasNextLine()){
                    try {
                        linea = sc.nextLine();
                        StringTokenizer st = new StringTokenizer(linea, "[], ");
                        while(st.hasMoreTokens()){
                            matrice[i][j].setText(st.nextToken());
                            j=(j+1)%9;
                        }
                        i=(i+1)%9;
                    }catch(Exception ecx){  okCaricamento=false; break;}
                }//while
            }//ifExists
            else throw new IOException();
        }
        else JOptionPane.showMessageDialog(null,"Nessun ripristino!");
    }//ripristina

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == imposta) {
            try {
                imposta();
                aggiornaTasti();
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null,
                        "Alcuni vincoli imposti non soddisfano le regole.");//TODO evidenziare in rosso cella scrause
            }//catch
        }//imposta
        if (e.getSource() == clear) svuota();
        if (e.getSource() == sblocca) {
            editable(true);
            //TODO non funziona dopo aver fatto una volta imposta
        }
        if (e.getSource() == prevSol) {
            indiceSoluzione--;
            scriviSoluzione();
            aggiornaTasti();
        }
        if(e.getSource()==risolvi){
            matriceTraccia.risolvi();
            scriviSoluzione();
            aggiornaTasti();
        }
        if (e.getSource() == proxSol) {
            indiceSoluzione++;
            aggiornaTasti();
            scriviSoluzione();
        }
        if(e.getSource()==salva){
            if(salvataggio==null){
                JFileChooser chooser= new JFileChooser(
                        "C:\\Users\\aless\\OneDrive - Università della Calabria");
                if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)salvataggio=chooser.getSelectedFile();
            }//ifSalvataggioNull
            try{salva(salvataggio);}catch (IOException exc){JOptionPane.showMessageDialog(null,
                    "Non e' stato possibile salvare.");}
        }//salva
        if(e.getSource()==salvaNome){
            JFileChooser chooser= new JFileChooser(
                    "C:\\Users\\aless\\OneDrive - Università della Calabria");
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)salvataggio=chooser.getSelectedFile();
        try{salva(salvataggio);}catch (IOException exc){JOptionPane.showMessageDialog(null,
                "Non e' stato possibile salvare.");}
        }//salvaNome
        if(e.getSource()==carica){
            try{ ripristina();}catch (IOException iex){}
        }//carica
    }//actionListener
    private void scriviSoluzione(){
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                matrice[i][j].setText(soluzioni.get(indiceSoluzione)[i][j]+""); //TODO PENSARE SOLUZIONE PIU' EFFICIENTE
    }//scriviSoluzione

    private void svuota() {
        for (int i = 0; i < matrice.length; i++)
            for (int j = 0; j < matrice.length; j++){
                matrice[i][j].setText("");
                matrice[i][j].setBackground(Color.WHITE);
            }
        matriceTraccia=null; matriceSalvataggio=null; indiceSoluzione=0; soluzioni=null;
        aggiornaTasti();
    }//svuota

    private void imposta() {
       /* //TODO evidenzia qui le celle sbagliate==> aggiusta celle che non vengono evidenziate
        //TODO azzeramento matriceTraccia per poter fare sblocca quante volte si vuole
        corretto=true;
        for (int i = 0; i < matrice.length; i++)
            for (int j = 0; j < matrice.length; ++j) {
                if (!matrice[i][j].getText().equals("")) {
                    if (matriceTraccia.assegnabile(i, j, Integer.parseInt(matrice[i][j].getText()))) {
                        matriceTraccia.assegna(i,j, Integer.parseInt(matrice[i][j].getText()));
                        matrice[i][j].setFont(new Font("Helvetica", Font.BOLD, 12));
                    }
                    else{
                        corretto= false;
                        matrice[i][j].setOpaque(true);
                        matrice[i][j].setBackground(new Color(255, 0,0));
                    }//else
                }//if
            }//for
        if(!corretto) throw new IllegalArgumentException();
        //se i vincoli andavano bene
        editable(false);
*/
    }//imposta

    private void editable(boolean b) {
        for (int i = 0; i < matrice.length; i++)
            for (int j = 0; j < matrice.length; j++)
                matrice[i][j].setEditable(b);
    }//blocca

    //TODO riscrivi per fixare sblocca e cancella
    private void aggiornaTasti(){
        /*if(matriceTraccia!=null) risolvi.setEnabled(true);
        if(soluzioni.size()!=0){
            sblocca.setEnabled(false);
            proxSol.setEnabled(true);
        }
        else sblocca.setEnabled(true);

        if(indiceSoluzione==0){
             if(matriceSalvataggio==null){
                 clear.setEnabled(false); risolvi.setEnabled(false);//sblocca.setEnabled(false);
                 proxSol.setEnabled(false); prevSol.setEnabled(false);//forse non serve
             }
             prevSol.setEnabled(false);
        }//if
        else{
            clear.setEnabled(true);
            if(indiceSoluzione<99) {
                prevSol.setEnabled(true);
                proxSol.setEnabled(true);
            }
            else proxSol.setEnabled(false);
        }*/

    }//aggiornaTasti

    private void inizializzazione() {
        matrice = new JTextField[9][9];
        for(int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                matrice[i][j]= new JTextField(){
                    @Override
                    public void setEditable(boolean b) {
                        super.setEditable(b);
                        setOpaque(true);
                    }//setEditable
                };
                matrice[i][j].setHorizontalAlignment(JTextField.CENTER);
                //matrice[i][j].setOpaque(true);
                griglia.add(matrice[i][j],i);
                }//forJ
        }//forI
    }//inizializza


    //private void impostaValore()
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
