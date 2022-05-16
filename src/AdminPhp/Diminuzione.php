<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
$nomeServer="localhost";
$username="root";
$password="";
$dbname="parcheggio";
$conn=new mysqli($nomeServer,$username,$password,$dbname);
$dato=json_decode(file_get_contents("php://input")); //estraggo il json

$codiceUtente=$dato->codiceUtente;
$richiesta=$dato->richiesta;
$utente="SELECT * FROM utente WHERE CodUtente='$codiceUtente' AND  Privilegi=1"; //vogliamo saper il tipo di utente
$risultato=$conn->query($utente);
if($risultato->num_rows>0){ //se ho almeno un elemento
	if($richiesta=="EliminaAuto"){
		$numPiano=$dato->numPiano;
		$numPosti=$dato->numPosti;
		$visualizzo="SELECT * FROM Piano WHERE NumPiano=$numPiano";
		$ris=$conn->query($visualizzo);
		if($ris->num_rows>0){
			$modifica="UPDATE piano set NumPosti=NumPosti-$numPosti WHERE NumPosti>=$numPosti AND NumPiano=$numPiano"; //dimunuisco i posti a disposizione
			$elimina="";
			$elimina="DELETE FROM posto WHERE NumPiano=$numPiano  AND StatoPosto=0 LIMIT $numPosti;"; //l'eliminazione è limitata al numero di posti
			if($conn->query($modifica)==true && $conn->query($elimina)==true){
				$r=array("Eliminazione"=>"Eliminazione fatta con successo");
				echo json_encode($r);
			}else{
				$r=array("Eliminazione"=>"Eliminazione non riuscita");
				echo json_encode($r);
			}
		}else{
			$r=array("Piano"=>"Piano inesistente");
			echo json_encode($r);
		}
	}
}else{
	$r=array("Ingresso"=>"Utente non autorizzato a fare questa richiesta");
	echo json_encode($r);
}
?>