<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$piano=$data->piano;

if (!empty($codiceUtente)){
	$server = "localhost";
	$username = "root";
	$password = "";
	$db = "PARCHEGGIO";

	$conn = new mysqli($server,$username,$password,$db);

	if ($conn->connect_error){
		$ra = array("esito"=>"Fallito","Stato" =>"Errore connessione");
		echo json_encode($ra);

	}else{

		$sql = "DELETE NumPiano,NumPosti FROM `Piano` WHERE $piano = NumPiano";
		$ris=$conn->query($sql);
		if ($conn->affected_rows!=0){
			$r = array("esito"=>"Successo","Stato"=>"Abbiamo cancellato il piano selezzionato!");
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NON TROVATO");
		}
		
	}
}
$conn->close();



?>