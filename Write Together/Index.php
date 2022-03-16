
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
    <h1 class="titel">Write Together!</h1>
    <div class="main">
        <input type="text"  class="inputText" placeholder="Enter Gamecode..." id="enterGameCode" maxlength="6" onkeyup="numbers()" autocomplete=off>
        <br>
        <table align="center">
            <tr>
                <th>
                 <input type="button"  class="buttonsubmit" value="        Submit       " onclick="joinRoom()" >
                 <br><br>
                </th>
            </tr>
            <tr>
                <th>
                    <a href="EnterNicknameAdmin.php"><input type="button"  class="buttonCreate" value="Or Create Room"></a>
                </th>
            </tr>
        </table>
       
    </div>
    

    <div class="copyright">
        2020 ©AllRightsReserved
    </div>

    <script>
        var checkalt;
        var gamepinInto = [];

        function numbers() {
            var check = document.getElementById("enterGameCode").value;
           if(isNaN(check) || check.includes(" ")) {
                console.log("ungültig");
                document.getElementById("enterGameCode").style.borderColor = "red";
                if(checkalt == null) {
                    document.getElementById("enterGameCode").value = "";
                } else {
                    document.getElementById("enterGameCode").value = checkalt;
                }
            } else {
                document.getElementById("enterGameCode").style.borderColor = "black";
                console.log("gültig");
                checkalt = check;
            } 
            

        }

        function joinRoom() {
            var gamepin = document.getElementById("enterGameCode").value;
            if(gamepin.length == 6) {
                gamepinInto = [gamepin];
                console.log(gamepinInto);
                const jsonString = JSON.stringify(gamepinInto);
                    const xhr = new XMLHttpRequest();
                    
                    xhr.onreadystatechange = function() {
                        if (this.readyState == 4 && this.status == 200) {
                            var codeGet = xhr.responseText;
                            console.log(codeGet);
                            if(codeGet.includes("richtig")) {
                                console.log("Weiterleitung...");
                                window.location.href = 'EnterNickname.php';
                            } 
                            if(codeGet.includes("falsch")) {
                                document.getElementById("enterGameCode").style.borderColor = "red";
                                document.getElementById("enterGameCode").value = "";
                                console.log("Falscher Code")
                            }
                            else {
                                console.log("Fehler!");
                            }

                        }
                    };
                    
                xhr.open("POST", "JoinGame.php");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.send(jsonString);
            }
            else {
                document.getElementById("enterGameCode").style.borderColor = "red";
                document.getElementById("enterGameCode").value = "";
            }
        }
        
        
    </script>

  
</body>

</html>