<?php
             header("Content-Type: application/json; charset=UTF-8");
             header("Access-Control-Allow-Methods: POST");
         
             $data=json_decode(file_get_contents("php://input"));
             $orainizio=$data->oraInizio;
             $targa=$data->targa;
             $numPiano=$data->numPiano;
             $numPosto=$data->numPosto;
             $stato=$data->statoOccupazione;
         
             if(!empty($codiceUtente) && !empty($password)){
                 $server = "localhost";
                 $username = "root";
                 $password = "";
                 $db = "PARCHEGGIO";    
                 $conn = new mysqli($server, $username, $password, $db);
                 if($conn->connect_error){
                     $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
                     echo json_encode($r);
                 }else{
                    $sql=("INSERT INTO Occupazione (OraInizio,Targa,Numpiano,Numposto) 
                    VALUES((select getDate()),'$targa','$numpiano','$numposto')");

         }
        $conn->query($sql);

        if($conn->affected_rows!=0){
            $r = array("esito"=>"successo", "Stato"=>"posto occupato con successo");
            echo json_encode($r);
        }else{
            $r = array("esito"=>"Fallito", "Stato"=>"l'operazione non si è conclusa con successo");
            echo json_encode($r);
        }

    }

$conn->close();
?>