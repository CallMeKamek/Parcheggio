<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$piano=$data->piano;

if (!empty($piano)){
	$server = "localhost";
	$username = "root";
	$password = "";
	$db = "PARCHEGGIO";

	$conn = new mysqli($server,$username,$password,$db);

	if ($conn->connect_error){
		$ra = array("esito"=>"Fallito","Stato" =>"Errore connessione");
		echo json_encode($ra);

	}else{

		$sql = "DELETE FROM `Piano` WHERE $piano = NumPiano";
		$ris=$conn->query($sql);
		if ($conn->affected_rows!=0){
			$r = array("esito"=>"Successo","Stato"=>"Abbiamo cancellato il piano selezzionato!");
			echo json_encode($r);
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NON TROVATO");
			echo json_encode($r);
		}
		
	}
	$conn->close();
}




?>