<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
echo "codsfgrdrd";
$data = json_decode(file_get_contents("php://input"));
$piano = $data->piano;

if (!empty($piano)) {
    $server = "localhost";
    $username = "root";
    $password = "";
    $db = "parcheggio";

    $conn = new mysqli($server, $username, $password, $db);
    if ($conn->connect_error) {
        $ra = array("esito" => "Fallito", "Stato" => "Errore conn");
        echo json_encode($r);
    } else {
        $sql = "SELECT count(*) AS CONTEGGIO FROM Posto WHERE StatoPosto = 0 AND NumPiano = '$piano'";

        $ris = $conn->query($sql);

        if ($conn->affected_rows != 0) {
			while ($row = $ris->fetch_assoc()) {
			$cod = $row["CONTEGGIO"];
			
		}
            $r = array("esito" => "successo", "Stato" => "Posti liberi: " . $cod);
            echo json_encode($r);
        } else {
            $r = array("esito" => "Fallito", "Stato" => "Non ci sono posti liberi");
            echo json_encode($r);
        }
    }
}
$conn->close();
