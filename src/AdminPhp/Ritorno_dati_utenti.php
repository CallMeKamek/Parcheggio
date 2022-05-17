<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$email=$data->email;

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
		$sql = "SELECT nome,cognome FROM `utente` WHERE email=$email";
		$ris=$conn->query($sql);
		if ($conn->affected_rows!=0){
			$r = array("esito"=>"Successo","Stato"=>"Dati persona cercata: Email "$email." Nome/Cognome: ".$ris);
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NON TROVATO");
		}
		

	}
}
$conn->close();



?>