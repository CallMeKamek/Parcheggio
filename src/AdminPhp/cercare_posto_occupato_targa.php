<?php
header("Content-Type: application/json;charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$data = json_decode(file_get_contents("php://input"));
$targa=$data->targa;

if (!empty($targa)){
	$server = "localhost";
	$username = "root";
	$password = "";
	$db = "PARCHEGGIO";

	$conn = new mysqli($server,$username,$password,$db);

	if ($conn->connect_error){
		$ra = array("esito"=>"Fallito","Stato" =>"Errore connessione");
		echo json_encode($ra);

	}else{

		$sql = "SELECT * FROM `Occupazione` WHERE Targa='$targa'";

		

		$ris=$conn->query($sql);
		while($row=$ris->fetch_assoc()){
			$es="targa  ".$row["Targa"]."  num posto  ".$row["NumPosto"]."  ora inizio ".$row["OraInizio"];
		}

		if ($conn->affected_rows!=0){
			$r = array("esito"=>"successo","Stato"=>"Dati targa cercata (posto occupato): ".$es);
			echo json_encode($r);
		}else{
			$r = array("esito"=>"Fallito","Stato"=>"NON TROVATO");
			echo json_encode($r);
		}
		
	}
	$conn->close();

}



?>