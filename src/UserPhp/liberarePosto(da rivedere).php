<?php
             header("Content-Type: application/json; charset=UTF-8");
             header("Access-Control-Allow-Methods: POST");
         
             $data=json_decode(file_get_contents("php://input"));
             $codiceOccupazione=$data->codiceOccupazione;
             $orainizio=$data->oraInizio;
             $targa=$data->targa;
             $numPiano=$data->numPiano;
             $numPosto=$data->numPosto;
             $stato=$data->statoOccupazione;
         
             if(!empty($codiceUtente) && !empty($password)){
                 $server = "localhost";
                 $username = "root";
                 $password = "";
                 $db = "PARCHEGGIO";    
                 $conn = new mysqli($server, $username, $password, $db);
                 if($conn->connect_error){
                     $ra = array("esito"=>"Fallito", "Stato"=>"Errore conn");
                     echo json_encode($r);
                 }else{
                    $sql= "SELECT codiceOccupazione FROM occupazione WHERE targa=$targa AND 
                    oraInizio=(SELECT CAST( GETDATE() AS Date ))";
                    $sql="SELECT Update occupazione SET oraFine =Currentime(),  WHERE codiceOccupazione='$codiceOccupazione'";
                }
                 $conn->query($sql);

                 if($conn->affected_rows!=0){
                     $r = array("esito"=>"successo", "Stato"=>"posto liberato con successo");
                     echo json_encode($r);
                 }else{
                     $r = array("esito"=>"Fallito", "Stato"=>"l'operazione non si è conclusa con successo");
                     echo json_encode($r);
                 }
         
             }
         
         $conn->close();
?>
