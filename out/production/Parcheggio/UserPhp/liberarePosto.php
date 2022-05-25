<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
//$codiceOccupazione=$data->codiceOccupazione;
//$orainizio=$data->oraInizio;
$targa = $data->targa;
//$numPiano=$data->numPiano;
//$numPosto=$data->numPosto;
//$stato=$data->statoOccupazione;

if (!empty($targa)) {
    
    $server = "localhost";
    $username = "root";
    $password = "";
    $db = "parcheggio";
    
    
    $conn = new mysqli($server, $username, $password, $db);
    

    if ($conn->connect_error) {
        $ra = array("esito" => "Fallito", "Stato" => "Errore conn");
        echo json_encode($ra);
        
    } else {
        $sql = "UPDATE  occupazione SET OraFine=(SELECT CURDATE()) WHERE OraInizio=(select OraInizio From occupazione WHERE Targa='1234' Order by OraInizio DESC limit 1)and targa='1234';";
        $sql .="UPDATE posto SET StatoPosto=0 WHERE NumPosto=(Select NumPosto from occupazione where targa='$targa' Order by OraInizio DESC LIMIT 1)";

        $conn->multi_query($sql);
        if ($conn->affected_rows != 0) {
            $r = array("esito" => "successo", "Stato" => "posto liberato con successo");
            echo json_encode($r);
        } else {
            $r = array("esito" => "Fallito", "Stato" => "l'operazione non si è conclusa con successo");
            echo json_encode($r);
        }
    }


    $conn->close();
}
?>