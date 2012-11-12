<?php
require_once 'secure/sql.php';


function login() {
    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['username']) && isset($_POST['password'])) {
        session_start();

        $username = $_POST['username'];
        $passwort = $_POST['password'];

        $user_id = right_pwd($username, $password);
        if ($user_id != -1) {
            $session_key = createSession($user_id);
            if($session_key != -1) {
                $_SESSION['loggedin'] = true;
                
                //TODO cookie erstellen + weiterleitung
            }
        }
    }
}

function logout() {
    session_start();
    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['session_key'])) {
        $session_key = $_POST['session_key'];
        closeSession($session_key);
    }
    session_destroy();
    
    //TODO cookie löschen + weiterleitung
}

function auth() {
    session_start();
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        //TODO weiterleitung
    }
}

?>