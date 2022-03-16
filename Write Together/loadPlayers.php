<?php
    include 'dbhLocation.php';
    $spalte = $_POST['RightGamecode'];

    $sql = "SELECT gamecode, admin, player2, 
    player3, player4, player5, player6, player7, player8, player9, startGame FROM room WHERE 
    gamecode='$spalte'";
    $result = mysqli_query($conn, $sql);
    if(mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
            echo $row['admin']."<br>";
            echo $row['player2']."<br>";
            echo $row['player3']."<br>";
            echo $row['player4']."<br>";
            echo $row['player5']."<br>";
            echo $row['player6']."<br>";
            echo $row['player7']."<br>";
            echo $row['player8']."<br>";
            echo $row['player9']."<br>";
            if ($row['startGame'] == 1) {
                echo '<script>',
                'startGame();',
                '</script>';
            }
        }
    } else {
        echo "";
    }
?>