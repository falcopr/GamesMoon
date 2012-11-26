<?php
require_once 'sql.php';
require_once 'paths.php';

function getGameList() {
    $html="<ul>";
    $games = getAllGames();
    foreach ($games as &$game) {
	$html=$html.'<li><a href="'.$LOCATION.$HOME.'?id='.$game["id"].'">'.$game["display_name"].'</a></li>';
    }
    $html=$html."</ul>";
    echo $html;
}

function getGameTabs($id) {
    $html="<table><tr>";
    $html=$html.'<th><a href="'.$LOCATION.$HOME.'?id='.$id.'&tab=game">Game</a></th>';
    $html=$html.'<th><a href="'.$LOCATION.$HOME.'?id='.$id.'&tab=all">All Scores</a></th>';
    $html=$html.'<th><a href="'.$LOCATION.$HOME.'?id='.$id.'&tab=own">Own Scores</a></th>';
    $html=$html.'<th><a href="'.$LOCATION.$HOME.'?id='.$id.'&tab=hight">Hightscores</a></th>';
    $html=$html.'</tr></table>';
    echo $html;
}

function formatAllScores($id) {
    $scores = getAllScores($id);
    $html="<table><tr><th>Player</th><th>Date</th><th>Score</th></tr>";
    foreach ($scores as &$score) {
	$html=$html."<tr><th>".$score["alias"]."</th><th>".$score["created"]."</th><th>".$score["points"]."</th></tr>";
    }
    $html=$html."</table>";
    echo $html;
}

?>
