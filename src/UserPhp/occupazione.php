<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
//$orainizio=$data->oraInizio;
$targa = $data->targa;
$numPosto = $data->numPosto;


if (!empty($targa) && !empty($numPosto)) {
    $server = "localhost";
    $username = "root";
    $password = "";
    $db = "PARCHEGGIO";
    $conn = new mysqli($server, $username, $password, $db);
    if ($conn->connect_error) {
        $ra = array("esito" => "Fallito", "Stato" => "Errore conn");
        echo json_encode($ra);
    } else {
        $sql = ("INSERT INTO Occupazione (OraInizio,Targa,NumPosto) 
                    VALUES((SELECT CURRENT_TIME()),'$targa','$numPosto')");
        if ($conn->query($sql)) {
            $r = array("esito" => "successo", "Stato" => "posto occupato con successo");
            echo json_encode($r);
        } else {
            $r = array("esito" => "Fallito", "Stato" => "l'operazione non si è conclusa con successo");
            echo json_encode($r);
        }
    }
    $conn->close();
}
