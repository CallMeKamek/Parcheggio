<?php
	header("Content-Type: application/json;charset=UTF-8");
	header("Access-Control-Allow-Methods: POST");

	$data = json_decode(file_get_contents("php://input"));
	$num_piano_nuovo=$data->num_piano_nuovo;
	$num_posti = $data->num_posti;

	if (!empty($num_piano_nuovo) && !empty($num_posti)){
		$server = "localhost";
		$username = "root";
		$password = "";
		$db = "parcheggio";

		$conn = new mysqli($server,$username,$password,$db);

		if ($conn->connect_error){
			$ra = array("esito"=>"Fallito","Stato" =>"Errore connessione");
			echo json_encode($ra);

		}else{

			$sql = "INSERT INTO Piano(NumPiano,NumPosti) VALUES ($num_piano_nuovo,$num_posti);";
			for ($i = 0; $i <$num_posti; $i++) {
					$sql.="INSERT INTO posto(NumPiano,StatoPosto) VALUES($num_piano_nuovo,0);";
			}
		
			if ($conn->multi_query($sql)){
				$r = array("esito"=>"Successo","Stato"=>"Abbiamo inserito il nuovo piano!");
				echo json_encode($r);
			}else{
				$r = array("esito"=>"Fallito","Stato"=>"Impossibile aggiungere un piano");
				echo json_encode($r);
			}
			
		}
		$conn->close();
	}




?>