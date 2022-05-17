<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
$nomeServer = "localhost";
$username = "root";
$password = "";
$dbname = "parcheggio";
$conn = new mysqli($nomeServer, $username, $password, $dbname);
$dato = json_decode(file_get_contents("php://input")); //estraggo il json

$codiceUtente = $dato->codiceUtente;
$richiesta = $dato->richiesta;
$utente = "SELECT * FROM utente WHERE CodUtente='$codiceUtente' AND  Privilegi=1"; //vogliamo saper il tipo di utente
$risultato = $conn->query($utente);
if ($risultato->num_rows > 0) { //se ho almeno un elemento
	if ($richiesta == "InserimentoAuto") {
		$numPiano = $dato->numPiano; //estraggo il piano
		$numPosti = $dato->numPosti; //estraggo i posti da inserire
		$visualizzo = "SELECT * FROM piano WHERE NumPiano=$numPiano";
		$ris = $conn->query($visualizzo);
		if ($ris->num_rows > 0) {
			$modifica = "UPDATE piano set NumPosti=NumPosti+$numPosti WHERE NumPiano=$numPiano"; //cambio il numero di posti
			$inserisci = "";
			for ($i = 1; $i <= $numPosti; $i++) { //apro questo ciclo per fare un tot di inserimenti
				$inserisci .= "INSERT INTO posto(NumPiano,StatoPosto) 
				VALUES($numPiano,0); "; //aggiungo un posto
			}
			if ($conn->query($modifica) == true && $conn->multi_query($inserisci) == true) {
				$r = array("Inserimento" => "Inserimento fatto con successo");
				echo json_encode($r); //invio con un json
			} else {
				$r = array("Inserimento" => "Inserimento non riuscito");
				echo json_encode($r);
			}
		} else {
			$r = array("Piano" => "Piano inesistente");
			echo json_encode($r);
		}
	}
} else {
	$r = array("Ingresso" => "Utente non autorizzato a fare questa richiesta");
	echo json_encode($r);
}
