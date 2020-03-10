package poo.polinomi;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Applicazione {
     public enum Implementazione {ARRAYLIST, LINKEDLIST, LISTA_CONCATENATA, MAP, SET}


    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        Polinomio p1 = null, p2 = null;
        String poli = null;
        Implementazione impl = null;
        ciclo:
        while (true) {
            do{
                System.out.println("Si scelga l'implementazione del Polinomio:");
                System.out.println("1) ArrayList");
                System.out.println("2) LinkedList");
                System.out.println("3) ListaConcatenata");
                System.out.println("4) TreeMap");
                System.out.println("5) TreeSet");
                System.out.println("0) Stop");
                int x = sc.nextInt();
                if (x == 0) System.exit(0);
                impl = impostaImpl(x);
            } while (impl == null);
            sc= new Scanner(System.in);
            while (p1 == null || p2 == null) {
                System.out.println("Inserire polinomio:");
                System.out.print('>');
                poli = sc.nextLine();
                try {
                    if (p1 == null) {
                        p1 = costruisciPolinomio(poli, impl);
                        System.out.println("Corretto.");
                    }//p1null
                    else {
                        p2 = costruisciPolinomio(poli, impl);
                        System.out.println("Corretto.");
                    }
                }//if(poli.matches(REGEX))
                catch (Exception ex) {
                    System.out.println("Non corretto, riprova.");
                }

            }//while polinomi


            cicloInterno:
            for (; ; ) {
                System.out.println("Inserire codice operazione:");
                System.out.println("1)SOMMA ALGEBRICA");
                System.out.println("2)PRODOTTO ");
                System.out.println("STOP per terminare il programma");
                System.out.print("> ");
                String comando = sc.nextLine();
                Polinomio risultato = new PolinomioSet();
                switch (comando) {
                    case "1":
                        risultato = p1.add(p2);
                        System.out.println(risultato);
                        p1 = null;
                        p2 = null;
                        break cicloInterno;
                    case "2":
                        risultato = p1.mul(p2);
                        System.out.println(risultato);
                        p1 = null;
                        p2 = null;
                        break cicloInterno;

                    case "STOP": case "stop": case "S": case "s":
                        break ciclo;
                    default:
                        System.out.println("Errore, riprova.");
                }//switch

            }//for

        }//whiletrue
        sc.close();
        System.out.println("Bye");

    }//main


    static Polinomio costruisciPolinomio(String polinomio, Implementazione imp) {
        String MONOMIO = "[\\-\\+]?([0-9]|[1-9]?x{1}[\\^\\d{1,3}]?)";
        String POLINOMIO_REGEX = MONOMIO +"[" + MONOMIO + "]*";
        if (polinomio.matches(POLINOMIO_REGEX)) {
            StringTokenizer st = new StringTokenizer(polinomio, "+-", true);
            String MONOMIO_DEGENERE = "[1-9]+";
            String MONOMIO_VAR_EXP = "[1-9]*x(\\^[\\-]?[1-9]{1,3})?";
            Polinomio risultato = null;
            switch (imp) {
                case ARRAYLIST:
                    risultato = new PolinomioAL();
                    break;
                case LINKEDLIST:
                    risultato = new PolinomioLL();
                    break;
                case LISTA_CONCATENATA:
                    risultato = new PolinomioConcatenato();
                    break;
                case MAP:
                    risultato = new PolinomioMap();
                    break;
                case SET:
                	risultato = new PolinomioSet();
                	break;
                default: risultato= null;
            }//switchImplementazione
            if(risultato==null) throw new RuntimeException();
            boolean segno = true;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token.equals("-")) segno = false;
                if (token.equals("+")) segno = true;
                if (token.matches(MONOMIO_DEGENERE)) {
                    if (!segno) risultato.add(new Monomio(-Integer.parseInt(token), 0));
                    else risultato.add(new Monomio(Integer.parseInt(token), 0));
                }
                if (token.matches(MONOMIO_VAR_EXP)) {
                    if (!segno) {
                        if (token.charAt(0) != 'x') {
                            if (token.contains("^"))
                                risultato.add(new Monomio(-Integer.parseInt(token.substring(0, token.indexOf("x"))),
                                        Integer.parseInt(token.substring(token.indexOf('^') + 1))));
                            else
                                risultato.add(new Monomio(-Integer.parseInt(token.substring(0, token.indexOf("x"))), 1));
                        } else {
                            if (token.contains("^")) risultato.add(new Monomio(-1,
                                    Integer.parseInt(token.substring(token.indexOf('^') + 1))));
                            else risultato.add(new Monomio(-1, 1));
                        }
                    }//if segno
                    else {
                        if (token.charAt(0) != 'x') {
                            if (token.contains("^"))
                                risultato.add(new Monomio(Integer.parseInt(token.substring(0, token.indexOf("x"))),
                                        Integer.parseInt(token.substring(token.indexOf('^') + 1))));
                            else
                                risultato.add(new Monomio(Integer.parseInt(token.substring(0, token.indexOf("x"))), 1));
                        } else {
                            if (token.contains("^")) risultato.add(new Monomio(1,
                                    Integer.parseInt(token.substring(token.indexOf('^') + 1))));
                            else risultato.add(new Monomio(1, 1));
                        }
                    }//else
                }//if segno
            }//while
			if(risultato.size()==0) throw new RuntimeException();
            return risultato;
        } else throw new RuntimeException();
    }//costruisciPolinomio

    static Implementazione impostaImpl(int x) {
        switch (x) {
            case 1:
                return Implementazione.ARRAYLIST;
            case 2:
                return Implementazione.LINKEDLIST;
            case 3:
                return Implementazione.LISTA_CONCATENATA;
            case 4:
                return Implementazione.MAP;
            case 5:
                return Implementazione.SET;
            default: return null;
        }
    } //impostaImpl
}//Applicazione


