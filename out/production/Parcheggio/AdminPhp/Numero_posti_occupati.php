<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));


	$server = "localhost";
	$username = "root";
	$password = "";
	$db = "PARCHEGGIO";

	$conn = new mysqli($server,$username,$password,$db);

	if ($conn->connect_error){
		$ra = array("esito"=>"Fallito","Stato" =>"Errore connessione");
		echo json_encode($ra);

	}else{
		$sql = "SELECT count(*) FROM `posto` WHERE StatoPosto=1";
		$result=$conn->query($sql);
		
		
		
		if ($result->num_rows>0){
			
			while($row = $result->fetch_assoc()) {
			$num=$row["count(*)"];
			}
			$r = array("esito"=>"Successo","Stato"=>"Numero posti occupati:".$num);
			echo json_encode($r);
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NEGATO");
			echo json_encode($r);
		}
		

	}

	$conn->close();



?>