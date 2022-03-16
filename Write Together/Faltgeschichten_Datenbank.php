<?php
	$requestPayload = file_get_contents("php://input");
	
	$mysql = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
	$mysql->set_charset("utf8");
	
	$daten = json_decode($requestPayload);
		
	
	$stmt = $mysql->prepare( "INSERT INTO room (admin) VALUES (?)");
	$stmt->bind_param("s", $daten[0]);
	$stmt->execute();
	
?>