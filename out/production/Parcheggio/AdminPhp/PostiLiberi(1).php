<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $piano =$data->piano;

    if(!empty($codiceUtente) && !empty($passwordVecchia) && !empty($passwordNuova)){
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "parcheggio";

        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else{
            $sql="SELECT count(*) FROM Posto WHERE StatoPosto = 0 AND NumPiano = '$piano'";

            $ris= $conn->query($sql);

        if($ris != 0)
		{
                $r = array("esito"=>"successo", "Stato"=>"Posti liberi: "+$ris);
                echo json_encode($r);
				//echo ris;
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"Non ci sono posti liberi");
                echo json_encode($r);
            }

        }
    }
    $conn->close();