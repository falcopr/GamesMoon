<?php
require_once 'secure/auth_sys.php';

auth();

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['session_key']) && isset($_POST['game_name'])) {
    $session_key = $_POST['session_key'];
    $game_name = $_POST['game_name'];

    $session_id = checkSession($session_key);
    if($session_id != -1) {
	$game_id = getGameId($game_name);
	if($game_id != -1) {
	    $gamesession = createGameSession($session_id, $game_id);
	    if($gamesession != -1) {
		$back = array('session' => $session_key, "game_name" => $game_name, "game_session" => $gamesession);
		echo json_encode($back);
	    }
	}
    }
}
?>