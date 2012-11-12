<?php
require_once 'db.php';

function getHash($string) {
    return sha1($string);
}

function right_pwd($alias, $pwd) {
    $pwd_hash = getHash($pwd);

    $stmt = $dbh->prepare("Select * FROM users where alias=:alias and pwd_hash=:pwd_hash;");
    $stmt->bindParam(':alias', $alias);
    $stmt->bindParam(':pwd_hash', $pwd_hash);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        return $row['id'];
    }
    return -1;
}

function createUser($alias, $pwd) {
    if (right_pwd($alias, $pwd) == -1) {
        $pwd_hash = getHash($pwd);
    
        $stmt = $dbh->prepare("INSERT INTO users (alias, pwd_hash) VALUES (:alias, :pwd_hash);");
        $stmt->bindParam(':alias', $alias);
        $stmt->bindParam(':pwd_hash', $pwd_hash);
        if($stmt->execute()) {
            return right_pwd($alias, $pwd);
        }
    }
    return -1;
}

function createSession($user_id) {
    $timestamp = date_timestamp_get(date_create());
    $session_key = getHash($timestamp);

    $stmt = $dbh->prepare("INSERT INTO sessions (user_id, session_key, login) VALUES (:user_id, :session_key, FROM_UNIXTIME(:login));");
    $stmt->bindParam(':user_id', $user_id);
    $stmt->bindParam(':session_key', $session_key);
    $stmt->bindParam(':login', $timestamp);
    if($stmt->execute()) {
        return $session_key;
    }
    return -1;
}

function checkSession($session_key) {

    $stmt = $dbh->prepare("Select user_id FROM sessions where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute() && $stmt->rowCount() == 1) {
        $row = $stmt->fetch();
        return $row['user_id'];
    }
    return -1;
}

function closeSession($session_key) {
    $timestamp = date_timestamp_get(date_create());

    $stmt = $dbh->prepare("UPDATE sessions SET logout=FROM_UNIXTIME(:logout) where session_key=:session_key and logout IS NULL;");
    $stmt->bindParam(':logout', $timestamp);
    $stmt->bindParam(':session_key', $session_key);
    $stmt->execute();
}

function createGameSession($session_id, $game_id) {
    $session_key = getHash(date_timestamp_get(date_create()));

    $stmt = $dbh->prepare("INSERT INTO game_sessions (session_id, game_id, session_key) VALUES (:session_id, :game_id, :session_key);");
    $stmt->bindParam(':session_id', $session_id);
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':session_key', $session_key);
    if($stmt->execute()) {
        return $session_key;
    }
    return -1;
}

function checkGameSession($session_key) {
    //TODO
}

function closeGameSession($session_key) {
    //TODO
}
function getAllGames() {
    $stmt = $dbh->prepare("Select id, name, path from games;");
    if($stmt->execute()) {
        return $stmt->fetchAll();
    }
    return -1;
}

function insertScore($game_id, $user_id, $score) {
    $stmt = $dbh->prepare("INSERT INTO scores (game_id, user_id, created, points) VALUES (:game_id, :user_id, NOW(), :points);");
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':user_id', $user_id);
    $stmt->bindParam(':points', $score);
    if($stmt->execute()) {
        return TRUE;
    }
    return FALSE;
}


function getAllScores($game_id) {
    $stmt = $dbh->prepare("Select scores.id, users.alias, created, points from scores inner join users on scores.user_id = users.id where scores.game_id=:game_id;");
    $stmt->bindParam(':game_id', $game_id);
    if($stmt->execute()) {
        return $stmt->fetchAll();
    }
    return -1;
}

function getOwnScores($game_id, $user_id) {
    $stmt = $dbh->prepare("Select id, created, points from scores where scores.game_id=:game_id and user_id=:user_id;");
    $stmt->bindParam(':game_id', $game_id);
    $stmt->bindParam(':user_id', $user_id);
    if($stmt->execute()) {
        return $stmt->fetchAll();
    }
    return -1;
}

function getAllHighScores($game_id) {
    //TODO
}


?>