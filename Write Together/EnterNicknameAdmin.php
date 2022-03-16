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

<body onload="newCode()">
    <form action="CreateGameDB.php" method="post">
    <h1 class="titel">Write Together</h1>
    <p id="ausgabeAdminCode" class="lobbyPlayer">Your Gamecode:</p>
    <div class="main">
        <input type="text" class="inputText"  name="EnterNicknameAdmin" placeholder="Enter Nickname..." id="enterNickname" maxlength="15" onkeyup="checkName()" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete=off>
            <input type="button" onclick="sendNicknameAdmin()"  class="button" value="Done">
    </form>
    </div>
    

    <div class="copyright">
        2020 ©AllRightsReserved
    </div>

    <script>
        var nicknameAdmin = [];
        var gamepin;
        var validAlt;
        var regex = /^[A-Za-z0-9]+$/;

        function checkName() {
            var isValid = regex.test(document.getElementById("enterNickname").value);

            if (!isValid) {
                console.log("invalid");
                document.getElementById("enterNickname").style.borderColor = "red";
                if(validAlt == null) {
                    document.getElementById("enterNickname").value = "";
                } else {
                    document.getElementById("enterNickname").value = validAlt;
                }
            } else {
                var validconfirmed = document.getElementById("enterNickname").value;
                validAlt = validconfirmed;
                document.getElementById("enterNickname").style.borderColor = "black";
                console.log("valid");
            }
        }

        function newCode() {
            gamepin = Math.round(Math.random() * 899999) + 100000;
            console.log(gamepin);
            document.getElementById("ausgabeAdminCode").innerHTML = "Your Gamecode: " + gamepin;
        }

        function sendNicknameAdmin() {
            var nicknameAdmin1 = document.getElementById("enterNickname").value
            nicknameAdmin = [nicknameAdmin1, gamepin];
            const jsonString = JSON.stringify(nicknameAdmin);
				const xhr = new XMLHttpRequest();
				
				xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
                        window.location.href = 'SubmitAdminView.php';
					}
				};
				
			xhr.open("POST", "CreateGameDB.php");
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.send(jsonString);
        }
    </script>
    
</body>

</html>