<?php
    session_start();
	$requestPayload = file_get_contents("php://input");
	
	
	$mysql2 = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
	$mysql2->set_charset("utf8");

	$codeEnter = json_decode($requestPayload);
	
    $stmt2 = $mysql2->prepare( "SELECT gamecode FROM room");
	$stmt2->bind_result($result);
	$stmt2->execute();
    while($stmt2->fetch()) {  //geht alle Spalten durch
        if($result == $codeEnter[0]) {
            $_SESSION["GamecodeSession"] = $codeEnter[0];
            echo "richtig";
            $check = true;
        }
    }
    if($check == false) {
        echo "falsch";
    }

?>