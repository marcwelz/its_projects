<?php
    session_start();

    $requestPayload = file_get_contents("php://input");

	$mysql = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
    $mysql->set_charset("utf8");
    
    $mysql2 = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
    $mysql2->set_charset("utf8");

    $conn = mysqli_connect("localhost", "root", "", "faltgeschichtenroom");
    $tableName = $_SESSION["GamecodeSession"];

    $startGame2 = 1;
    $sql2 = "UPDATE room SET startGame='$startGame2' WHERE gamecode='$tableName'";
    
    if ($mysql2->query($sql2) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql2 . "<br>" . $mysql2->error;
    }    
    $mysql2 -> close();

    $sql = "CREATE TABLE `{$tableName}_tableGame` (
    answer1 TEXT,
    answer2 TEXT,
    answer3 TEXT,
    answer4 TEXT,
    answer5 TEXT,
    answer6 TEXT)";
        
    $_SESSION["TableNameCode"] = $tableName . "_tableGame";

    if (mysqli_query($conn, $sql)) {
        echo "Table answer created successfully";
    } else {
        echo "Error creating table: " . mysqli_error($conn);
    }

?>