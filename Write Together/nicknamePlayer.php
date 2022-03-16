<?php 
    session_start();
    $check = true;
    $i = 2;
    $e = 0;

    $requestPayload = file_get_contents("php://input");
	
	$mysql = new mysqli("localhost", "root", "", "faltgeschichtenroom"); 
	$mysql->set_charset("utf8");
    
    if ($mysql->connect_error) {
        die("Connection failed: " . $mysql->connect_error);
    }
    
	$daten = json_decode($requestPayload);

    $spalte = '';
    if (isset($_SESSION["GamecodeSession"])) {
        $spalte = $_SESSION["GamecodeSession"];
    }

    $stmt = "SELECT gamecode, admin, player2, 
    player3, player4, player5, player6, player7, player8, player9 FROM room WHERE 
    gamecode='$spalte'";
    $result = $mysql->query($stmt);

	/*$stmt = $mysql->prepare( "SELECT gamecode, admin, player2, 
        player3, player4, player5, player6, player7, player8, player9 FROM room WHERE 
        gamecode=?");
        
    $stmt->bind_param('s', $spalte); //s = string
    $stmt->execute();
    
    $stmt->bind_result($gamecode, $admin, $player2, $player3, $player4, $player5,$player6,$player7,$player8,$player9);
    $stmt->close();*/
	
    if ($result->num_rows > 0) {
        while($row = $result->fetch_assoc()) {
            $gamecode = $row["gamecode"];
            $admin = $row["admin"];
            $player2 = $row["player2"];
            $player3 = $row["player3"];
            $player4 = $row["player4"];
            $player5 = $row["player5"];
            $player6 = $row["player6"];
            $player7 = $row["player7"];
            $player8 = $row["player8"];
            $player9 = $row["player9"];
        }
    } else {
        echo "0 results";
    }

    $_SESSION["NicknameAdminSession"] = $admin;

    if(empty($player2) && $check == true) {
        $_SESSION["NicknamePlayer2Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player2) && $check == true) {
        $_SESSION["NicknamePlayer2Session".$spalte] = $player2;
    }
    $i++;
    if(empty($player3)  && $check == true) {
        $_SESSION["NicknamePlayer3Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player3) && $check == true) {
       $_SESSION["NicknamePlayer3Session".$spalte] = $player3;
    }
    $i++;
    if(empty($player4) && $check == true) {
        $_SESSION["NicknamePlayer4Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player4) && $check == true) {
        $_SESSION["NicknamePlayer4Session".$spalte] = $player4;
    }
    $i++;
    if(empty($player5) && $check == true) {
        $_SESSION["NicknamePlayer5Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player5) && $check == true) {
        $_SESSION["NicknamePlayer5Session".$spalte] = $player5;
    }
    $i++;
    if(empty($player6) && $check == true) {
        $_SESSION["NicknamePlayer6Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player6) && $check == true) {
        $_SESSION["NicknamePlayer6Session".$spalte] = $player6;
    }
    $i++;
    if(empty($player7) && $check == true) {
        $_SESSION["NicknamePlayer7Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player7) && $check == true) {
        $_SESSION["NicknamePlayer7Session".$spalte] = $player7;
    }
    $i++;
    if(empty($player8) && $check == true) {
        $_SESSION["NicknamePlayer8Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player8) && $check == true) {
        $_SESSION["NicknamePlayer8Session".$spalte] = $player8;
    }
    $i++;
    if(empty($player9) && $check == true) {
        $_SESSION["NicknamePlayer9Session".$spalte] = $daten[0];
        $check = false;
        $e = $i;
    }
    if(!empty($player9) && $check == true) {
        $_SESSION["NicknamePlayer9Session".$spalte] = $player9;
    } 
    
    if($check == false) {
        if($e == 2) {
            $sql = "UPDATE room SET player2='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 3) {
            $sql = "UPDATE room SET player3='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 4) {
            $sql = "UPDATE room SET player4='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 5) {
            $sql = "UPDATE room SET player5='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 6) {
            $sql = "UPDATE room SET player6='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 7) {
            $sql = "UPDATE room SET player7='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 8) {
            $sql = "UPDATE room SET player8='$daten[0]' WHERE gamecode=$spalte";
        }
        if($e == 9) {
            $sql = "UPDATE room SET player9='$daten[0]' WHERE gamecode=$spalte";
        }
        
        if ($mysql->query($sql) === TRUE) {
            echo "New record created successfully";
        } else {
            echo "Error: " . $sql . "<br>" . $mysql->error;
        }
        $mysql->close();
    } else {
        echo "Lobby is full";
    }
    
    
    
    
?>