<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");

$dati = json_decode(file_get_contents("php://input"));

$email = $dati->email;
$password = $dati->password;

if (!empty($email) && !empty($password)) {
	$server = "localhost";
	$nomeutente = "root";
	$passdb = "";
	$nomedatabase = "parcheggio";

	$conn = new mysqli($server, $nomeutente, $passdb, $nomedatabase);

	$query = "SELECT CodUtente FROM UTENTE WHERE Email='$email' AND Password='$password' AND Privilegi=1 ";

	$result = $conn->query($query);
	
		//$codUt = $result->fetch_assoc()["CodUtente"];
		while($riga=$result->fetch_assoc()){
			$cod=$riga["CodUtente"];
			
		}

	if ($result->num_rows>0) {
		$r = array("Esito" => "successo", "Login" => "Riuscito", "cod" => "$cod");
			echo json_encode($r);
	} else {
		$r = array("Esito" => "Fallito", "Login" => "Non riuscito");
		echo json_encode($r);
	}
} else {
	$r = array("Esito" => "Fallito", "Login" => "Non riuscito");
	echo json_encode($r);
}
