<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $codiceUtente=$data->codiceUtente;
    $passwordVecchia=$data->passwordVecchia;
    $passwordNuova=$data->passwordNuova;

    if(!empty($codiceUtente) && !empty($passwordVecchia) && !empty($passwordNuova)){
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "PARCHEGGIO";

        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else{
            $sql="UPDATE `utente` SET password='$passwordNuova' WHERE codutente=$codiceUtente AND password ='$passwordVecchia'";

            $conn->query($sql);

            if($conn->affected_rows!=0){
                $r = array("esito"=>"successo", "Stato"=>"aggiornato con successo");
                echo json_encode($r);
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"Password ");
                echo json_encode($r);
            }

        }
        
        $conn->close();
    }

    

?>