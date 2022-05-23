<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $codiceUtente=$data->codiceUtente;


    if(!empty($codiceUtente)){
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "PARCHEGGIO";

        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else{
            $sql="DELETE FROM utente WHERE CodUtente='$codiceUtente'";

            $result = $conn->query($sql);

            if($result == true){
                $r = array("esito"=>"successo", "Stato"=>"utente eliminato con successo");
                echo json_encode($r);
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"l'operazione non si è conclusa con successo");
                echo json_encode($r);
            }

        }
    }

?>