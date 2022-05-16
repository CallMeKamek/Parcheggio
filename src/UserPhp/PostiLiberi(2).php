<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

   
        $server = "localhost";
        $username = "root";
        $password = "";
        $db = "parcheggio";
         
        echo"sff ";
        $conn = new mysqli($server, $username, $password, $db);
        if($conn->connect_error){
            $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
            echo json_encode($r);
        }else{

            $PostiLiberi=0;
			
				$sql="SELECT count(*) FROM Posto WHERE StatoPosto = 0";

				$ris = $conn->query($sql);
				if($ris->num_rows>0)
                {
                    while($riga=$ris->fetch_assoc()){
                        $PostiLiberi=$riga["count(*)"];
                        echo $riga["count(*)"];
                    }
                }
            
            if($PostiLiberi!=0){
                $r = array("esito"=>"successo", "PostiLiberi"=>$PostiLiberi);
                echo json_encode($r);
				
            }else{
                $r = array("esito"=>"Fallito", "Stato"=>"Non ci sono posti liberi");
                echo json_encode($r);
            }
        }
        
   
    $conn->close();
    ?>