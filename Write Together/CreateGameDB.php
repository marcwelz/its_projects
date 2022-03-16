<?php
    session_start();
    
	$requestPayload = file_get_contents("php://input");
	
	$mysql = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
	$mysql->set_charset("utf8");
	
	$daten = json_decode($requestPayload);
    
    $sql = "INSERT INTO room (gamecode, admin, player2, player3, player4, player5, player6, player7, player8, player9)
    VALUES ('$daten[1]', '$daten[0]', '', '', '', '', '', '', '', '')";

    $_SESSION["GamecodeSession"] = $daten[1];
    $_SESSION["NicknameAdminSession"] = $daten[0];

    if ($mysql->query($sql) === TRUE) {
       echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $mysql->error;
    }
	$mysql->close();
?>