<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$codiceUtente=$data->codiceUtente;

if (!empty($codiceUtente)){
	$server = "localhost";
	$username = "root";
	$password = "";
	$db = "PARCHEGGIO";

	$conn = new mysqli($server,$username,$password,$db);

	if ($conn->connect_error){
		$ra = array("esito"=>"Fallito","Stato" =>"Errore conn");
		echo json_encode($ra);

	}else{
		$sql="UPDATE `utente`SET permesso = 1  WHERE codutente='$codiceUtente'and permessi=0";
		$conn->query($sql);
		if ($conn->affected_rows!=0){
			$r = array("esito"=>"Successo","Stato"=>"Permesso");
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"Permesso");
		}
		

	}
}
$conn->close();



?>