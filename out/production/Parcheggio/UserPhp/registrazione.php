 <?php
        header("Content-Type: application/json; charset=UTF-8");
        header("Access-Control-Allow-Methods: POST");

        $data = json_decode(file_get_contents("php://input"));
        $passw = $data->password;
        $email = $data->email;
        $nome = $data->nome;
        $cognome = $data->cognome;


        if (!empty($nome) && !empty($cognome)&&!empty($email) && !empty($passw)) {
            $server = "localhost";
            $username = "root";
            $password = "";
            $db = "PARCHEGGIO";
            $conn = new mysqli($server, $username, $password, $db);
            if ($conn->connect_error) {
                $ra = array("succes" => "false", "Stato" => "Errore conn");
                echo json_encode($ra);
            } else {
                $sql = "INSERT INTO utente (nome,cognome,password,email)
                            VALUES ('$nome','$cognome','$passw','$email')";

                

                if ($conn->query($sql)) {
                    $r = array("success" => "true", "Stato" => "utente inserito con successo");
                    echo json_encode($r);
                } else {
                    $r = array("success" => "false", "Stato" => "l'operazione non si è conclusa con successo");
                    echo json_encode($r);
                }

               
            } $conn->close();
        }

        ?>