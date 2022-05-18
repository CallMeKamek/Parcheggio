/*
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientLoginUtente {
    public static void main(String[] args) {
        final String PERCORSO = "http://localhost/Parcheggio/src/UserPhp/";
        String ind = "";
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        JSONObject daInviare = new JSONObject();
        JSONObject ricevuto = new JSONObject();
        boolean loggato = false;
        int menuLogin = -1;
        int menuAzioni = -1;
        int codiceUtente;
        String s;

        try {

            while (!loggato) {
                System.out.println("Inserisci: \n" +
                        "1)Per accedere\n" +
                        "2)Per registrazione");
                menuLogin = Integer.parseInt(tastiera.readLine());
                switch (menuLogin) {
                    case 1:

                        System.out.println("inserisci email");
                        s = tastiera.readLine();
                        daInviare.put("email", s);
                        System.out.println("inserisci password");
                        s = tastiera.readLine();
                        daInviare.put("password", s);


                        ind = PERCORSO + "loginUtente.php";
                        ricevuto = richiesta(ind, daInviare);
                        if (ricevuto.get("esito").equals("successo")) {
                            loggato = true;
                            codiceUtente = Integer.parseInt((String) ricevuto.get("cod"));
                            ricevuto=null;
                            System.out.println("Inserire:\n" +
                                    "1)Inserire un nuovo vehicolo\n" +
                                    "2)Occupare un posto\n" +
                                    "3)Liberare un posto\n" +
                                    "4)Eliminare il proprio account\n" +
                                    "5)Cambiare la email");
                            menuAzioni = Integer.parseInt(tastiera.readLine());
                            switch (menuAzioni) {
                                case 1:
                                    break;
                                case 2:
                                    System.out.println("inserisci targa");
                                    s = tastiera.readLine();
                                    daInviare.put("targa", s);
                                    System.out.println("inserisci il numero posto");
                                    s = tastiera.readLine();
                                    daInviare.put("numPosto", s);

                                    ind = PERCORSO + "occupazione.php";
                                    ricevuto = richiesta(ind, daInviare);
                                    if (ricevuto.get("esito").equals("successo")) {

                                        System.out.println("Posto occupato con successo");

                                    } else {
                                        System.out.println("Errore neloccupare il posto");

                                    }

                                    ind = "";
                                    daInviare = null;
                                    ricevuto=null;
                                    break;
                                case 3:

                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                            }


                        } else {
                            System.out.println("non loggato, per favore riprovare");

                        }
                        ind = "";
                        daInviare = null;
                        ricevuto=null;


                        break;
                    case 2:

                        System.out.println("inserisci nome");
                        s = tastiera.readLine();
                        daInviare.put("nome", s);
                        System.out.println("inserisci cognome");
                        s = tastiera.readLine();
                        daInviare.put("cognome", s)
                        ;
                        System.out.println("inserisci email");
                        s = tastiera.readLine();
                        daInviare.put("email", s);
                        System.out.println("inserisci password");
                        s = tastiera.readLine();
                        daInviare.put("password", s);


                        ind = PERCORSO + "registrazione.php";
                        ricevuto = richiesta(ind, daInviare);
                        if (ricevuto.get("esito").equals("successo")) {

                            System.out.println("Registrato con successo");
                            System.out.println("Per favore loggarsi");
                        } else {
                            System.out.printf("C'e stato un problema durante la sua registrazione");

                        }
                        ind = "";
                        //daInviare = null;

                        break;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static JSONObject richiesta(String indirizzo, JSONObject daMandare) {
        JSONParser parser = new JSONParser();
        JSONObject daRitornare = null;
        try {
            String output = daMandare.toJSONString();
            URL url = new URL(indirizzo);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(output.getBytes());
            os.flush();
            os.close();
            int rCode = connection.getResponseCode();
            if (rCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String input;
                if ((input = in.readLine()) != null) {
                    //System.out.println(input);
                    daRitornare = (JSONObject) parser.parse(input);
                    return daRitornare;
                } else {
                    daRitornare = new JSONObject();
                    daRitornare.put("esito", "Fallito");
                    return daRitornare;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        daRitornare = new JSONObject();
        daRitornare.put("success", "false");
        return daRitornare;
    }
}*/