<?php
    header("Content-Type: application/json; charset=UTF-8");
    header("Access-Control-Allow-Methods: POST");

    $data=json_decode(file_get_contents("php://input"));
    $num=$data->num;
    if(!empty($num)){
        $r=array("success"=>"true");
        echo json_encode($r);
    }else{
        $r=array("success"=>"false");
        echo json_encode($r);
    }

?>