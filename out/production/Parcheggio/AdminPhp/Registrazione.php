<?php
header ("Content-Type: application/json; charset=UTF-8");
header ("Access-Control-Allow-Methods: POST");
$dati = json_decode(file_get_contents("php://input"));
$nome = $dati -> nome;
$cognome = $dati -> cognome;
$email = $dati -> email;
$password = $dati -> password;
$privilegi = $dati-> privilegi;

if (!empty($cognome) && !empty($nome) && !empty($email) && !empty($password) /* && !empty($privilegi) */) {
	
	$server = "localhost";
	$username = "root";
	$dbpass = "";
	$dbname = "parcheggio";

	$conn = new mysqli($server, $username, $dbpass, $dbname);
	$query1 = "SELECT count(*) as conteggio FROM utente WHERE Nome = '$nome' AND Cognome = '$cognome' AND Email = '$email' AND Password = '$password' AND Privilegi = '$privilegi' ";
	$result1 = $conn->query($query1);
if ($result1 == true) {
	
	$risultato = mysqli_fetch_assoc($result1);
	
if ($risultato['conteggio'] == 0) {


	$query2 = "INSERT INTO utente (Nome, Cognome, Email, Password, Privilegi) VALUES ('$nome', '$cognome', '$email', '$password', '$privilegi')";
	$result2 = $conn->query($query2);
    if ($result2 == true) {
	$r = array("Registrazione" => "Successo!");
	echo json_encode($r);
	
    }
	else {
	$r = array("Registrazione" => "Fallita");
	echo json_encode($r);
	
    }
}
else {
	$r = array("Registrazione" => "Fallita");
	echo json_encode($r);
	
}
    
}

}

?>