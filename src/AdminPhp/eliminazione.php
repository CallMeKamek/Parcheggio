<?php
	header ("Content-Type: application/json; charset=UTF-8");
	header ("Access-Control-Allow-Methods: POST");
	$dati = json_decode(file_get_contents("php://input"));
	
	$email = $dati -> email;

	if(!empty($email)){
		$server = "localhost";
		$username = "root";
		$password = "";
		$db = "parcheggio";
		
		$conn = new mysqli($server,$username,$password,$db);
		
		if($conn -> connect_error){
			$arr = array("eliminazione"=>"fallito","stato"=>"errore");
			echo json_encode($arr);
		}else{

			$sql = "SELECT Email FROM utente WHERE Email = '$email' AND Privilegi = 0";
			$result = $conn->query($sql);
			if($result -> num_rows > 0){
				$sql = "DELETE FROM utente WHERE Email = '$email'";
				if(($conn->query($sql))==true){
					$arr = array("eliminazione"=>"ok");
					echo json_encode($arr);
				}else{
					$arr = array("eliminazione"=>"fallito","stato"=>"errore query");
					echo json_encode($arr);
				}

			}else{
				$arr = array("eliminazione"=>"fallito","stato"=>"utente inesistente o amministratore");
				echo json_encode($arr);
			}
		}
	}
	
?>