<?php
    session_start();
?>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Faltgeschichten</title>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Londrina+Solid:wght@400;900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen and (min-device-width: 320px) and (max-device-width: 500px)" href="mobile.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-device-width: 501px)" href="style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

</head>

<body>
    <h1 class="titel">Write Together</h1>
    <p id="ausgabe" class="lobbyPlayer">Your Gamecode: <?php echo $_SESSION["GamecodeSession"]; ?></p>
    <div class="main">
        <input type="text"  class="inputText" placeholder="Enter Nickname..." id="enterNicknamePlayer" maxlength="10" onkeyup="uberprufen()" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete=off>
            <input type="button" onclick="sendNicknamePlayer()" class="button" value="Done">
    </div>
    
    <script>
        
        var validAlt;
        var regex = /^[A-Za-z0-9]+$/;

        function uberprufen() {
            var isValid = regex.test(document.getElementById("enterNickname").value);

            if (!isValid) {
                 console.log("ungültig");
                document.getElementById("enterNickname").style.borderColor = "red";
                if(validAlt == null) {
                    document.getElementById("enterNickname").value = "";
                } else {
                    document.getElementById("enterNickname").value = validAlt;
                }
            } else {
                var gultig = document.getElementById("enterNickname").value;
                validAlt = gultig;
                document.getElementById("enterNickname").style.borderColor = "black";
                console.log("gültig");
            }
        }

        function sendNicknamePlayer() {
            var nicknamePlayer1 = document.getElementById("enterNicknamePlayer").value
            nicknamePlayer = [nicknamePlayer1];
            const jsonString = JSON.stringify(nicknamePlayer);
				const xhr = new XMLHttpRequest();
				
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
                        var codeGet = xhr.responseText;
                        console.log(codeGet);
                        if(codeGet.includes("Lobby is full")) {
                            alert("Lobby is full!");
                            document.getElementById("enterNicknamePlayer").style.borderColor = "red";
                            document.getElementById("enterNicknamePlayer").value = "";
                            document.getElementById("ausgabe").value = "Sorry You can't enter this Lobby, its full";
                        } else {
                            window.location.href = 'Submit.php';
                        }
					}
				};
				
			xhr.open("POST", "nicknamePlayer.php");
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.send(jsonString);
        }

    </script>

    <div class="copyright">
        2020 ©AllRightsReserved
    </div>

    <script>
        
    </script>
</body>

</html>