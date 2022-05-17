
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientAdmin {
    public static void main(String[] args) {

        JSONObject oggetto = new JSONObject();
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        String scelta;
        String valore;
        String percorso="http://localhost/Parcheggio/src/AdminPhp/";
        int privilegi = 0;

        boolean flag = true;

        try {
            while (flag) {

                System.out.println("---------------------------------------------ADMIN CLIENT---------------------------------------------");
                System.out.println("1 register, 2 login, 3 impostazioni piano, 4 impostazioni posti, 5 impostazioni utenti, 6 per chiudere");
                scelta = tastiera.readLine();
                switch (scelta) {
                    case "1":
                        //registrazione
                        if (privilegi == 0) {
                            System.out.println("inserisci nome");
                            valore = tastiera.readLine();
                            oggetto.put("nome", valore);

                            System.out.println("inserisci cognome");
                            valore = tastiera.readLine();
                            oggetto.put("cognome", valore);

                            System.out.println("inserisci email");
                            valore = tastiera.readLine();
                            oggetto.put("email", valore);

                            System.out.println("inserisci password");
                            valore = tastiera.readLine();
                            oggetto.put("password", valore);

                            oggetto.put("privilegi", 1);

                            String richiesta = oggetto.toString();
                            String ind = percorso+"Registrazione.php";
                            connetti(ind, "POST", richiesta);
                        } else {
                            if (privilegi == 1) {
                                System.out.println("sei già loggato.");
                            }
                        }
                        break;

                    case "2":
                        //login
                        if (privilegi == 0) {
                            System.out.println("inserisci email");
                            valore = tastiera.readLine();
                            oggetto.put("email", valore);

                            System.out.println("inserisci password");
                            valore = tastiera.readLine();
                            oggetto.put("password", valore);

                            String richiesta = oggetto.toString();

                            String ind = percorso+"login.php";
                            if (validate(ind, richiesta) == true) {
                                System.out.println("Successo");
                                privilegi = 1;
                            } else {
                                System.out.println("credenziali errate.");
                            }
                        } else {
                            if (privilegi == 1) {
                                System.out.println("sei già loggato.");
                            }
                        }
                        break;

                    case "3":
                        //impostazioni piano
                        System.out.println("\n-----------------------IMPOSTAZIONI PIANO-----------------------");
                        String scelta2;
                        boolean flag2 = true;
                        while (flag2) {
                            System.out.println("1 creazione nuovo piano, 2 eliminazione piano, 3 torna indietro");
                            scelta2 = tastiera.readLine();
                            switch (scelta2) {
                                case "1":
                                    //creazione nuovo piano
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano");
                                        valore = tastiera.readLine();
                                        oggetto.put("num_piano_nuovo", valore);
                                        System.out.println("Inserisci numero dei posti");
                                        valore = tastiera.readLine();
                                        oggetto.put("num_posti", valore);
                                        String ind = percorso+"aggiungere_piano.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "2":
                                    //eliminazione piano
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano");
                                        valore = tastiera.readLine();
                                        oggetto.put("piano", valore);
                                        String ind = percorso+"Delete_piano.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "3":
                                    //torna indietro
                                    System.out.println("\n");
                                    flag2 = false;
                                    break;

                                default:
                                    System.out.println("error");
                                    break;
                            }
                        }
                        break;

                    case "4":
                        //impostazione posti
                        System.out.println("\n--------------------------------------------------------IMPOSTAZIONI POSTI--------------------------------------------------------");
                        String scelta3;
                        boolean flag3 = true;
                        while (flag3) {
                            System.out.println("1 diminuire posti, 2 aumentare posti, 3 visualizzazione posti occupati, 4 visualizzazione dati dei posti occupati, 5 torna indietro");
                            scelta3 = tastiera.readLine();
                            switch (scelta3) {
                                case "1":
                                    //diminuire posti
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano");
                                        valore = tastiera.readLine();
                                        oggetto.put("numPiano", valore);
                                        System.out.println("Inserisci numero dei posti");
                                        valore = tastiera.readLine();
                                        oggetto.put("numPosti", valore);
                                        String ind = percorso+"dimuireposti.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "2":
                                    //aumentare posti
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano");
                                        valore = tastiera.readLine();
                                        oggetto.put("numPiano", valore);
                                        System.out.println("Inserisci numero dei posti");
                                        valore = tastiera.readLine();
                                        oggetto.put("numPosti", valore);
                                        String ind = "http://localhost/magliano/aumentoposti.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "3":
                                    //visualizzazione posti occupati
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano"); //nel foglio di bursuc non c'è
                                        valore = tastiera.readLine();
                                        oggetto.put("numPiano", valore);
                                        String ind = "http://localhost/magliano/stampaposti.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "4":
                                    //visualizzazione dati posti occupati
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci numero del piano"); //non abbiamo il file php
                                        valore = tastiera.readLine();
                                        oggetto.put("numPiano", valore);
                                        System.out.println("Inserisci numero del posti");
                                        valore = tastiera.readLine();
                                        oggetto.put("numPosto", valore);
                                        String ind = "http://localhost/magliano/infoposto.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "5":
                                    //torna indietro
                                    System.out.println("\n");
                                    flag3 = false;
                                    break;

                                default:
                                    System.out.println("error");
                                    break;
                            }
                        }
                        break;

                    case "5":
                        //impostazioni utenti
                        System.out.println("\n--------------------------------------------------------IMPOSTAZIONI UTENTI--------------------------------------------------------");
                        String scelta4;
                        boolean flag4 = true;
                        while (flag4) {
                            System.out.println("1 visualizzazione dati utente, 2 privilegi admin utente, 3 revocare privilegi admin utente, 4 eliminazione utente, 5 torna indietro");
                            scelta4 = tastiera.readLine();
                            switch (scelta4) {
                                case "1":
                                    //visualizzazione dati utente
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci email utente");
                                        valore = tastiera.readLine();
                                        oggetto.put("email", valore);
                                        String ind = "http://localhost/magliano/visualizzadatiutente.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "2":
                                    //previlegi admin utente
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci codice utente");
                                        valore = tastiera.readLine();
                                        oggetto.put("codiceUtente", valore);
                                        String ind = "http://localhost/magliano/aggiungipermessi.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "3":
                                    //revocare privilegi admin utente
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci codice utente");
                                        valore = tastiera.readLine();
                                        oggetto.put("codiceUtente", valore);
                                        String ind = "http://localhost/magliano/rimuovipermessi.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "4":
                                    //eliminazione utente
                                    if (privilegi == 1) {
                                        System.out.println("Inserisci email utente");
                                        valore = tastiera.readLine();
                                        oggetto.put("email", valore);
                                        String ind = "http://localhost/magliano/eliminazione.php";
                                        String richiesta = oggetto.toString();
                                        connetti(ind, "POST", richiesta);
                                    } else {
                                        if (privilegi == 0) {
                                            System.out.println("non hai i permessi.");
                                        }
                                    }
                                    break;

                                case "5":
                                    //torna indietro
                                    System.out.println("\n");
                                    flag4 = false;
                                    break;

                                default:
                                    System.out.println("error");
                                    break;
                            }
                        }
                        break;


                    case "6":
                        System.out.println("chiusura client...");
                        flag = false;
                        break;

                    default:
                        System.out.println("error\n");
                        break;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connetti(String ind, String method, String richiesta) {

        if (method.equals("GET")) {

            try {
                URL url = new URL(ind);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int rCode = connection.getResponseCode();
                if (rCode == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String input;
                    if ((input = in.readLine()) != null) {
                        System.out.println(input);
                    }
                } else {
                    System.out.println("errore");
                    System.out.println("codice errore: " + rCode + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if (method.equals("POST")) {

                try {
                    URL url = new URL(ind);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    OutputStream os = conn.getOutputStream();
                    os.write(richiesta.getBytes());
                    os.flush();
                    os.close();
                    int rCode = conn.getResponseCode();
                    if (rCode == 200) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String input;
                        if ((input = in.readLine()) != null) {
                            System.out.println(input);
                            JSONParser p=new JSONParser();
                            JSONObject o=(JSONObject) p.parse(input);
                        }
                    } else {
                        System.out.println("errore");
                        System.out.println("codice errore: " + rCode + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public static boolean validate(String ind, String richiesta) {

        try {
            URL url = new URL(ind);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(richiesta.getBytes());
            os.flush();
            os.close();
            int rCode = conn.getResponseCode();
            if (rCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String input;
                if ((input = in.readLine()) != null) {
                    System.out.println(input);
                    if (input.contains("\"Esito\":\"successo\"")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                System.out.println("errore");
                System.out.println("codice errore: " + rCode + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
