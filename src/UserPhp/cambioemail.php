<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $codiceUtente=$data->codiceUtente;
    $emailVecchia=$data->emailVecchia;
    $emailNuova=$data->emailNuova;

    if(!empty($codiceUtente) && !empty($emailVecchia) && !empty($emailNuova)){
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "PARCHEGGIO";

        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else{
            $sql="UPDATE `utente` SET email='$emailNuova' WHERE codutente=$codiceUtente AND email ='$emailVecchia'";

            $conn->query($sql);

            if($conn->affected_rows!=0){
                $r = array("esito"=>"successo", "Stato"=>"email aggiornata con successo");
                echo json_encode($r);
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"operazione fallita ");
                echo json_encode($r);
            }

        }
    }
    $conn->close();

?>