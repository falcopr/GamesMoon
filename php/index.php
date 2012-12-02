<?php
require_once 'secure/paths.php';
session_start();
if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
    header('Location: '.$LOGIN);
    exit;
}
?>
<!DOCTYPE html>
<html>
<head>
<title>GamesMoon: Home</title>
<link rel="stylesheet" type="text/css" media="screen" href="./css/format.css">
</style>
</head>
<!--<body onLoad="siteLoaded();">-->
<body>
<div id="all">
<div id="header">
<?php
require_once 'secure/user.php';
formatUser($_COOKIE["gamesmoon"]);
echo '<a href="'.$LOCATION.$LOGOUT.'">Logout</a>';
?>

</div>
<div id="games">
<?php
require_once 'secure/game.php';
getGameList();
?>
</div>
<div id="frame">
<?php
if(isset($_GET["id"])) {
    $game_id = $_GET["id"];
    ?>
<div id="tabs">
    <?php
    getGameTabs($game_id);
    ?>
</div>
    <?php
    if(isset($_GET["tab"])) {
	$tab = $_GET["tab"];
	if($tab != "game") {
	    ?>
<div id="scores">
	    <?php
	    if($tab == "all") {
		formatAllScores($game_id);
	    } else if($tab == "own") {
	      formatOwnScores($game_id, $_COOKIE["gamesmoon"]);
	    } else if($tab == "hight") {
		formatHightScores($game_id);
	    }
	    ?>
</div>
	    <?php
	} else {
	    ?>
<div id="game">
	    <?php
	    formatGameTab($game_id, $_COOKIE["gamesmoon"]);
	    ?>
</div>
	    <?php
	    
	}
    }
}
?>
</div>
</div>
</body>
<!--<script type="text/javascript">
  function siteLoaded()
  {
    setTimeout("document.applet_game.requestFocus()", 500);
    setTimeout("document.applet_game.requestFocus()", 1000);
    setTimeout("document.applet_game.requestFocus()", 1500);
  }
  siteLoaded();
</script>-->
</html>
