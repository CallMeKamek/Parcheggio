<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");
    $data = json_decode(file_get_contents("php://input"));

    $email = $data->email;
    $password = $data->password;
    if (!empty($email) && !empty($password)) {

        $server = "localhost";
        $db = "parcheggio";
        $username = "root"; //root
        $DBpassword = "";   //lasciate vuoto
        $conn;
        $conn = new mysqli($server, $username, $DBpassword, $db);

        // Check connection
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }

        $sql = "SELECT CodUtente FROM utente WHERE email='$email' AND password='$password' AND Privilegi=0";
        $risultato = $conn->query($sql);
        if ($risultato == true) {
            
        /*    $cod=$risultato->fetch_assoc()["CodUtente"];   */

		while ($row = $risultato->fetch_assoc()) {
			$cod = $row["CodUtente"];
			
		}


            if ($risultato->num_rows > 0) {

                $r = array("esito" => "successo", "cod" => "$cod");
                echo json_encode($r);
            } else {

                $r = array("esito" => "Fallito", "login" => "non andato a buon fine");
                echo json_encode($r);
            }
        } 
    } else {
        $r = array("esito" => "Fallito", "login" => "non andato a buon fine");
        echo json_encode($r);
    }
