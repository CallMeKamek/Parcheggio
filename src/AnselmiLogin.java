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
        while (scelta != 8 && cod == 0) {
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
                                System.out.println(ricevuto);
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
                        String indirizzo = "http://localhost/Pellizzari/Registrazione.php";
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
                                System.out.println(ricevuto);
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

                case 3:
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
                        String indirizzo = "http://localhost/Pellizzari/cambioemail.php";
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

                case 4:
                    oggetto.clear();
                    System.out.println("Inserisci nome utente da cancellare: ");

                    try {
                        valoreletto = lettura.readLine();
                        oggetto.put("codiceUtente", valoreletto);
                        String richiesta = oggetto.toString();
                        String indirizzo = "http://localhost/Pellizzari/delete.php";
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

                case 5:
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
                        String indirizzo = "http://localhost/Pellizzari/occupazione.php";
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

    }

}
