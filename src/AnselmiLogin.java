/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Andrea
 */
public class AnselmiLogin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JSONObject oggetto = new JSONObject();
        BufferedReader lettura = new BufferedReader(new InputStreamReader(System.in));

        int scelta = 0;
        int cod = 0;
        String valoreletto = "";
        String percorso = "http://localhost/Parcheggio/src/UserPhp/";
        while (scelta != 3 && cod == 0) {
            System.out.println("1) LOGIN \n2) REGISTRAZIONE \n3) USCITA \n");
            System.out.println("Inserisci scelta: ");

            try {
                scelta = Integer.parseInt(lettura.readLine());
            } catch (IOException ex) {
                Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (scelta) {
                case 1:
                    try {
                        System.out.println("Inserisci email: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("email", valoreletto);
                        System.out.println("Inserisci password: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("password", valoreletto);
                        String richiesta = oggetto.toString();
                        String indirizzo = percorso + "loginUtente.php";
                        URL uri = new URL(indirizzo);
                        HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                        connessione.setRequestMethod("POST");
                        connessione.setDoOutput(true);
                        OutputStream os = connessione.getOutputStream();
                        os.write(richiesta.getBytes());
                        os.flush();
                        os.close();
                        int codice = connessione.getResponseCode();
                        if (codice == HttpURLConnection.HTTP_OK) {
                            BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                            String ricevuto = "";
                            while ((ricevuto = inArrivo.readLine()) != null) {

                                JSONParser converti = new JSONParser();
                                JSONObject oggettoconvertito = (JSONObject) converti.parse(ricevuto);
                                String stringafinale = (String) oggettoconvertito.get("esito");
                                System.out.println(stringafinale);
                                if (stringafinale.equals("successo")) {
                                    cod = Integer.parseInt(oggettoconvertito.get("cod") + "");
                                }
                            }

                            inArrivo.close();
                        } else {
                            System.out.println("Richiesta non andata a buon fine");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;


                case 2:
                    oggetto.clear();
                    System.out.println("Inserisci nome utente: ");

                    try {
                        valoreletto = lettura.readLine();
                        oggetto.put("nome", valoreletto);
                        System.out.println("Inserisci cognome utente: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("cognome", valoreletto);
                        System.out.println("Inserisci email: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("email", valoreletto);
                        System.out.println("Inserisci la password: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("password", valoreletto);
                        oggetto.put("privilegi", 0);
                        String richiesta = oggetto.toString();
                        String indirizzo = percorso + "registrazione.php";
                        URL uri = new URL(indirizzo);
                        HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                        connessione.setRequestMethod("POST");
                        connessione.setDoOutput(true);
                        OutputStream os = connessione.getOutputStream();
                        os.write(richiesta.getBytes());
                        os.flush();
                        os.close();
                        int codice = connessione.getResponseCode();
                        if (codice == HttpURLConnection.HTTP_OK) {
                            BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                            String ricevuto = "";
                            while ((ricevuto = inArrivo.readLine()) != null) {

                                JSONParser converti = new JSONParser();
                                JSONObject oggettoconvertito = (JSONObject) converti.parse(ricevuto);
                                String stringafinale = (String) oggettoconvertito.get("Registrazione");
                                System.out.println(stringafinale);
                            }
                            inArrivo.close();
                        } else {
                            System.out.println("Richiesta non andata a buon fine");
                        }


                    } catch (IOException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
            }
        }
        if (cod != 0) {
            System.out.println("");
            System.out.println("Utente loggato con successo! ");
            System.out.println("");
            scelta = 0;
            while (scelta != 10) {
                System.out.println("1)CambioEmail\n" +
                        "2)eliminare un account\n" +
                        "3)Occupare un posto\n" +
                        "4)Cambio Password\n" +
                        "5)Ricerca un Vehicolo\n" +
                        "6)Numero posti liberi di un piano\n" +
                        "7)Numero psoti liberi i tutto il parcheggio\n" +
                        "8)Liberare un posto");
                System.out.println("Inserisci scelta: ");
                try {
                    scelta = Integer.parseInt(lettura.readLine());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                switch (scelta) {
                    case 1:
                        oggetto.clear();
                        System.out.println("Inserisci un codice utente: ");

                        try {
                            valoreletto = lettura.readLine();
                            oggetto.put("codiceUtente", valoreletto);
                            System.out.println("Inserisci vecchia email: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("emailVecchia", valoreletto);
                            System.out.print("Inserisci la nuova email: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("emailNuova", valoreletto);
                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "cambioemail.php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream flussoinuscita = connessione.getOutputStream();
                            flussoinuscita.write(richiesta.getBytes());
                            flussoinuscita.flush();
                            flussoinuscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    System.out.println(ricevuto);
                                    JSONParser convertitore = new JSONParser();
                                    JSONObject restituito = (JSONObject) convertitore.parse(ricevuto);
                                    String valore = (String) restituito.get("esito");
                                    System.out.println(valore);
                                }
                            }

                        } catch (IOException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    case 2:
                        oggetto.clear();
                        System.out.println("Inserisci nome utente da cancellare: ");

                        try {
                            valoreletto = lettura.readLine();
                            oggetto.put("codiceUtente", valoreletto);
                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "delete.php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    JSONParser convertitore = new JSONParser();
                                    JSONObject restituito = (JSONObject) convertitore.parse(ricevuto);
                                    String valore = (String) restituito.get("Stato");
                                    System.out.println(valore);

                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException e) {

                        }
                        break;

                    case 3:
                        oggetto.clear();


                        try {
                            System.out.println("Inserisci targa veicolo: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("targa", valoreletto);
                            System.out.println("Inserisci numero piano: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("numPiano", valoreletto);
                            System.out.println("Inserisci numero posto; ");
                            valoreletto = lettura.readLine();
                            oggetto.put("numPosto", valoreletto);
                            oggetto.put("statoOccupazione", 1);
                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "occupazione.php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    JSONParser convertitore = new JSONParser();
                                    JSONObject restituito = (JSONObject) convertitore.parse(ricevuto);
                                    String valore = (String) restituito.get("Stato");
                                    System.out.println(valore);
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException e) {

                        }
                        break;

                    case 4:
                        oggetto.clear();

                        System.out.println("Inserisci il codice utente: ");
                        try {
                            valoreletto = lettura.readLine();
                            oggetto.put("codiceUtente", valoreletto);
                            System.out.println("Inserisci password vecchia: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("passwordVecchia", valoreletto);
                            System.out.println("Inserisci la nuova password: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("passwordNuova", valoreletto);
                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "CambioPassword.php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    JSONParser convertitore = new JSONParser();
                                    JSONObject restituito = (JSONObject) convertitore.parse(ricevuto);
                                    String valore = (String) restituito.get("Stato");
                                    System.out.println(valore);
                                }
                            }


                        } catch (IOException e) {


                        } catch (ParseException e) {

                        }
                        break;


                    case 5:
                        oggetto.clear();
                        System.out.println("Inserisci targa del veicolo: ");
                        try {
                            valoreletto = lettura.readLine();
                            oggetto.put("targa", valoreletto);
                            System.out.println("Inserisci il modello del veicolo: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("modello", valoreletto);
                            System.out.println("Inserisci il codice dell'utente proprietario: ");
                            valoreletto = lettura.readLine();
                            oggetto.put("CodUtente", valoreletto);
                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "RicercaVeicolo.php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    JSONParser convertitore = new JSONParser();
                                    JSONObject restituito = (JSONObject) convertitore.parse(ricevuto);
                                    String valore = (String) restituito.get("Stato");
                                    System.out.println(valore);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        } catch (ParseException e) {

                        }
                        break;

                    case 6:
                        oggetto.clear();
                        System.out.println("Inserisci numero del piano: ");
                        try {
                            valoreletto = lettura.readLine();
                            oggetto.put("piano", Integer.parseInt(valoreletto));
                            String richiesta = oggetto.toString();

                            String indirizzo = percorso + "PostiLiberi(1).php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();

                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    System.out.println(ricevuto);
                                }
                            }


                        } catch (IOException e) {
                            throw new RuntimeException(e);
                            //} catch (ParseException e) {

                        }
                        break;

                    case 7:
                        oggetto.clear();
                        System.out.println("Inserisci il numero del nuovo piano: ");

                        try {

                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "PostiLiberi(2).php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    System.out.println(ricevuto);
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;

                    case 8:


                        try {

                            /*$codiceOccupazione=$data->codiceOccupazione;
                             $orainizio=$data->oraInizio;
                             $targa=$data->targa;
                             $numPiano=$data->numPiano;
                             $numPosto=$data->numPosto;
                             $stato=$data->statoOccupazione;*/


                            oggetto.clear();
                            System.out.println("Inserisci la targa  ");

                            String richiesta = oggetto.toString();
                            String indirizzo = percorso + "PostiLiberi(2).php";
                            URL uri = new URL(indirizzo);
                            HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                            connessione.setRequestMethod("POST");
                            connessione.setDoOutput(true);
                            OutputStream inUscita = connessione.getOutputStream();
                            inUscita.write(richiesta.getBytes());
                            inUscita.flush();
                            inUscita.close();
                            int codice = connessione.getResponseCode();
                            if (codice == HttpURLConnection.HTTP_OK) {
                                BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                                String ricevuto = "";
                                while ((ricevuto = inArrivo.readLine()) != null) {
                                    System.out.println(ricevuto);
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;


                }
            }
        }

/*



                case 6:
                    oggetto.clear();
                    System.out.println("Inserisci numero del piano: ");

                    try {
                        valoreletto = lettura.readLine();
                        oggetto.put("piano", valoreletto);
                        String richiesta = oggetto.toString();
                        String indirizzo = "http://localhost/Pellizzari/Deletepiano.php";
                        URL uri = new URL(indirizzo);
                        HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                        connessione.setRequestMethod("POST");
                        connessione.setDoOutput(true);
                        OutputStream inUscita = connessione.getOutputStream();
                        inUscita.write(richiesta.getBytes());
                        inUscita.flush();
                        inUscita.close();
                        int codice = connessione.getResponseCode();
                        if (codice == HttpURLConnection.HTTP_OK) {
                            BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                            String ricevuto = "";
                            while ((ricevuto = inArrivo.readLine()) != null) {
                                System.out.println(ricevuto);


                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;

                case 7:
                    oggetto.clear();
                    System.out.println("Inserisci il numero del nuovo piano: ");

                    try {
                        valoreletto = lettura.readLine();
                        oggetto.put("num_piano_nuovo", valoreletto);
                        System.out.println("Inserisci il numero di posti del nuovo piano: ");
                        valoreletto = lettura.readLine();
                        oggetto.put("num_posti", valoreletto);
                        String richiesta = oggetto.toString();
                        String indirizzo = "http://localhost/Pellizzari/aggiungere_piano.php";
                        URL uri = new URL(indirizzo);
                        HttpURLConnection connessione = (HttpURLConnection) uri.openConnection();
                        connessione.setRequestMethod("POST");
                        connessione.setDoOutput(true);
                        OutputStream inUscita = connessione.getOutputStream();
                        inUscita.write(richiesta.getBytes());
                        inUscita.flush();
                        inUscita.close();
                        int codice = connessione.getResponseCode();
                        if (codice == HttpURLConnection.HTTP_OK) {
                            BufferedReader inArrivo = new BufferedReader(new InputStreamReader(connessione.getInputStream()));
                            String ricevuto = "";
                            while ((ricevuto = inArrivo.readLine()) != null) {
                                System.out.println(ricevuto);
                            }
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(AnselmiLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;


            }
*/


    }

}
