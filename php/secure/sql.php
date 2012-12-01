<?php
require_once 'db.php';

function getHash($string) {
    return sha1($string);
}

function right_pwd($alias, $pwd) {
    $return = -1;
    $pwd_hash = getHash($pwd);

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select * FROM users where alias=:alias and pwd_hash=:pwd_hash;");
    $stmt->bindParam(':alias', $alias);
    $stmt->bindParam(':pwd_hash', $pwd_hash);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        $return =  $row['id'];
    }
    $stmt->closeCursor();
    return $return;
}

function createUser($alias, $pwd) {
    $return = -1;
    if (right_pwd($alias, $pwd) == -1) {
        $pwd_hash = getHash($pwd);
    
        $dbh = getdbh();
        $stmt = $dbh->prepare("INSERT INTO users (alias, pwd_hash) VALUES (:alias, :pwd_hash);");
        $stmt->bindParam(':alias', $alias);
        $stmt->bindParam(':pwd_hash', $pwd_hash);
        if($stmt->execute()) {
            $return =  right_pwd($alias, $pwd);
        }
    }
    $stmt->closeCursor();
    return $return;
}

function createSession($user_id) {
    $return = -1;
    $timestamp = date_timestamp_get(date_create());
    $session_key = getHash($timestamp);

    $dbh = getdbh();
    $stmt = $dbh->prepare("UPDATE sessions SET logout=now() where user_id=:user_id and logout IS NULL;");
    $stmt->bindParam(':user_id', $user_id);
    $stmt->execute();
    $stmt->closeCursor();
    
    $stmt = $dbh->prepare("INSERT INTO sessions (user_id, session_key, login) VALUES (:user_id, :session_key, FROM_UNIXTIME(:login));");
    $stmt->bindParam(':user_id', $user_id);
    $stmt->bindParam(':session_key', $session_key);
    $stmt->bindParam(':login', $timestamp);
    if($stmt->execute()) {
        $return =  $session_key;
    }
    $stmt->closeCursor();
    return $return;
}

function checkSession($session_key) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select id FROM sessions where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        $return =  $row['id'];
    }
    $stmt->closeCursor();
    return $return;
}

function getUser($session_key) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select user_id FROM sessions where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        $return =  $row['user_id'];
    }
    $stmt->closeCursor();
    return $return;
}

function getUserName($session_key) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select users.alias FROM sessions inner join users on users.id = sessions.user_id where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        $return =  $row['alias'];
    }
    $stmt->closeCursor();
    return $return;
}

function closeSession($session_key) {
    $timestamp = date_timestamp_get(date_create());

    $dbh = getdbh();
    $stmt = $dbh->prepare("UPDATE sessions SET logout=FROM_UNIXTIME(:logout) where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':logout', $timestamp);
    $stmt->bindParam(':session_key', $session_key);
    $stmt->execute();
    $stmt->closeCursor();
}

function getGameId($id_name) {
    $return = -1;
    
    $dbh = getdbh();
    $stmt = $dbh->prepare("Select id from games where id_name=:id_name;");
    $stmt->bindParam(':id_name', $id_name);
    if($stmt->execute()) {
        $row = $stmt->fetch();
        $return =  $row['id'];
    }
    $stmt->closeCursor();
    return $return;
}

function createGameSession($session_id, $game_id) {
    $return = -1;
    $session_key = getHash(date_timestamp_get(date_create()));

    $dbh = getdbh();
    $stmt = $dbh->prepare("INSERT INTO game_sessions (session_id, game_id, session_key) VALUES (:session_id, :game_id, :session_key);");
    $stmt->bindParam(':session_id', $session_id);
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute()) {
        $return =  $session_key;
    }
    $stmt->closeCursor();
    return $return;
}

function checkGameSession($session_key) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select game_sessions.id, game_id, user_id FROM game_sessions inner join sessions on sessions.id=game_sessions.session_id where game_sessions.session_key=:session_key;");
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        $return = array('id' => $row['id'], 'game' => $row['game_id'], 'user' => $row['user_id']);
    }
    $stmt->closeCursor();
    return $return;
}

function closeGameSession($session_key) {
    //TODO
}

function getAllGames() {
    $return = -1;
    
    $dbh = getdbh();
    $stmt = $dbh->prepare("Select id, display_name, path from games;");
    if($stmt->execute()) {
        $return = $stmt->fetchAll();
    }
    $stmt->closeCursor();
    return $return;
}

function getGame($id) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select id_name, display_name, path, width, height, startfile from games where id=:id;");
    $stmt->bindParam(':id', $id);
    if($stmt->execute()) {
        $return = $stmt->fetch();
    }
    $stmt->closeCursor();
    return $return;
}

function insertScore($game_id, $user_id, $score) {
    $return = FALSE;
    
    $dbh = getdbh();
    $stmt = $dbh->prepare("INSERT INTO scores (game_id, user_id, created, points) VALUES (:game_id, :user_id, NOW(), :points);");
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':user_id', $user_id);
    $stmt->bindParam(':points', $score);
    if($stmt->execute()) {
        $return = TRUE;
    }
    $stmt->closeCursor();
    return $return;
}


function getAllScores($game_id) {
    $return = -1;
    
    $dbh = getdbh();
    $stmt = $dbh->prepare("Select scores.id, users.alias, DATE_FORMAT(created, '%H:%i %d.%m.%y') as created, points from scores inner join users on scores.user_id = users.id where scores.game_id=:game_id order by points DESC;");
    $stmt->bindParam(':game_id', $game_id);
    if($stmt->execute()) {
        $return = $stmt->fetchAll();
    }
    $stmt->closeCursor();
    return $return;
}

function getOwnScores($game_id, $user_id) {
    $return = -1;
    
    $dbh = getdbh();
    $stmt = $dbh->prepare("Select id, DATE_FORMAT(created, '%H:%i %d.%m.%y') as created, points from scores where scores.game_id=:game_id and user_id=:user_id order by points DESC;");
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':user_id', $user_id);
    if($stmt->execute()) {
        $return =  $stmt->fetchAll();
    }
    return $return;
}

function getAllHighScores($game_id) {
    $return = -1;

    $dbh = getdbh();
    $stmt = $dbh->prepare("Select scores.id, user_id, users.alias, DATE_FORMAT(created, '%H:%i %d.%m.%y') as created, max(points) as points from scores inner join users on scores.user_id = users.id where scores.game_id=:game_id group by user_id order by points DESC;");
    $stmt->bindParam(':game_id', $game_id);
    if($stmt->execute()) {
        $return =  $stmt->fetchAll();
    }
    return $return;
}


?>