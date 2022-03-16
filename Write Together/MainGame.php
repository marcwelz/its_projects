<?php
    session_start();
?>
<!DOCTYPE html>
<html>
<head>
<title>Foldingstorys</title>
<link href="https://fonts.googleapis.com/css2?family=Luckiest+Guy&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Londrina+Solid:wght@400;900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen and (min-device-width: 320px) and (max-device-width: 500px)" href="mobile.css" />
<link rel="stylesheet" type="text/css" media="screen and (min-device-width: 501px)" href="style.css" />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body onload="timer()">
    <h1 class="titel">Write Together</h1>
    <p id="countdown" class="lobbyPlayer"></p>
    
    <div class="main">
        <input type="text" placeholder="Enter Answer..." id="enterField" maxlength="70" class="inputText" onCopy="return false" onDrag="return false" onDrop="return false" onPaste="return false" autocomplete=off>
        
    </div>
        
    <script>
        var i = 0;
        var antwort = "";
        var checkWords = []
        var sentance = [{}];

        var antwort1
        var antwort2;
        var antwort3;
        var antwort4;
        var antwort5;
        var antwort6;

        function check() {
            antwort = document.getElementById("enterField");
            antwort = antwort.toLowerCase();
            var arr = antwort.split(" ");

            var badWords = ["nigga", "jews", "nazi", "hitler"]
                
            game();
        }

        function game() {
            i++;
            if(i == 1) {
                antwort1 = document.getElementById("enterField");
                if(antwort1 = "") {
                    antwort1 = "The player, who couldn't find the input field";
                }
                document.getElementById("enterField").value = "";
                timer();
            }
            if(i == 2) {
                antwort2 = document.getElementById("enterField");
                if(antwort2 == "") {
                    antwort = "is a stupid Write Together player"
                }
                document.getElementById("enterField").value = "";
                timer();
            }
            if(i == 3) {
                antwort3 = document.getElementById("enterField");
                if(antwort3 = "") {
                    antwort3 = "He hasn't entering anything";
                }
                document.getElementById("enterField").value = "";
                timer();
            }
            if(i == 4) {
                antwort4 = document.getElementById("enterField");
                if(antwort4 = "") {
                    antwort4 = "In a game called Write Together";
                }
                document.getElementById("enterField").value = "";
                timer();
            }
            if(i == 5) {
                antwort5 = document.getElementById("enterField");
                if(antwort5 = "") {
                    antwort5 = "The player is doing this after he hasn't entered s*** in the inputfield";
                }
                document.getElementById("enterField").value = "";
                timer();
            }
            if(i == 6) {
                antwort6 = document.getElementById("enterField");
                if(antwort6 = "") {
                    antwort6 = "because the player wasn't f*** able to type in smth.";
                }
                document.getElementById("enterField").value = "";
                console.log(antwort1 + antwort2 + antwort3 +  antwort4 + antwort5 + antwort6);
                sentance = [antwort1,antwort2,antwort3,antwort4,antwort5,antwort6];
                send();
            }
        }

        function timer() {


        var timeleft = 20; //Zeit angeben in Sekunden
			var downloadTimer = setInterval(function(){
				if(timeleft <= 0){
                    clearInterval(downloadTimer);
                    document.getElementById("countdown").innerHTML = "Time over";
                    antwort = "";
					game();
				} else {
                    //Does during Countdown
					document.getElementById("countdown").innerHTML = timeleft + " seconds remaining";
				}
				timeleft -= 1;
			}, 1000);
        }

        function send() {
            const sendSentance = JSON.stringify(sentance);
			const xhr = new XMLHttpRequest();

            xhr.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
					   console.log(xhr.responseText);
					}
				};
				
				xhr.open("POST", "SendGameAndAnswer.php");
				xhr.setRequestHeader("Content-Type", "application/json");
				xhr.send(sendSentance);
        }

    </script>

</body>

</html>