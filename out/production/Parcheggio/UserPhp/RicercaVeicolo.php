<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $targa =$data->targa;
	$modello =$data->modello;
	$CodUtente =$data->CodUtente;
	
    if(!empty($targa) && !empty($modello) && !empty($CodUtente)){
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "parcheggio";

        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else
		{
            $sql="SELECT * FROM auto WHERE Targa = '$targa' AND Modello = '$modello' AND CodUtente = '$CodUtente'" ;

            $result = $conn->query($sql);
		
 
            if($conn->affected_rows!=0){
                $r = array("esito"=>"successo", "Stato"=>"Veicolo trovato");
                echo json_encode($r);
				
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"Nessun veicolo trovato");
                echo json_encode($r);
            }

        }
    }
    $conn->close();