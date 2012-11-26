<?php
require_once 'secure/paths.php';
session_start();
if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
    header('Location: '.$LOGIN);
    exit;
}
?>
<html>
<head>
<title>Home</title>
</head>
<body>
<?php
require_once 'secure/game.php';
if(isset($_GET["id"])) {
    $game_id = $_GET["id"];
    getGameTabs($game_id);
    if(isset($_GET["tab"])) {
	if($_GET["tab"] == "all") {
	    formatAllScores($game_id);
	}
    }
}
//TODO: Liste an vorhandenen Spielen
getGameList();
?>
</body>
</html>
