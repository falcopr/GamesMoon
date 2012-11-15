<?php
require_once 'secure/auth_sys.php';

auth();

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['game_session']) && isset($_POST['score'])) {
    $game_session = $_POST['game_session'];
    $score = $_POST['score'];

    $game = checkGameSession($game_session);
    if($game != -1) {
	if(insertScore($game['game'], $game['user'], $score)) {
	    $back = array('session' => $game_session, 'score' => $score, 'insert' => TRUE);
	    echo $back;
	}
    }
}
?>