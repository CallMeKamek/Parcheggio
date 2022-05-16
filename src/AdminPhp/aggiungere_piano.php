<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$num_piano_nuovo=$data->num_piano_nuovo;
$num_posti = $data->num_posti;

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

		$sql = "INSERT INTO Piano(NumPiano,NumPosti) VALUES ($piano_nuovo,$num_posti)";
		$ris=$conn->query($sql);
		if ($conn->affected_rows!=0){
			$r = array("esito"=>"Successo","Stato"=>"Abbiamo inserito il nuovo piano!");
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NON TROVATO");
		}
		
	}
}
$conn->close();



?>