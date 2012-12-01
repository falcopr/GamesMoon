<?php
require_once 'sql.php';
require_once 'paths.php';


function login() {
    global $HOME;
    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['username']) && isset($_POST['password'])) {
        session_start();

        $username = $_POST['username'];
        $password = $_POST['password'];

        $user_id = right_pwd($username, $password);
        if ($user_id != -1) {
            $session_key = createSession($user_id);
            if($session_key != -1) {
                $_SESSION['loggedin'] = true;
                
                setCookie('gamesmoon', $session_key);
                header('Location: '.$HOME);
                exit;
            }
        }
    }
}

function logout() {
    global $LOGIN;
    session_start();
    if (isset($_COOKIE["gamesmoon"])) {
        $session_key = $_COOKIE["gamesmoon"];
        closeSession($session_key);
    }
    session_destroy();
    
    //negativer Wert für Verfallszeitpunkt löscht Cookie
    setCookie('gamesmoon', '', -100);
    
    header('Location: '.$LOGIN);
    exit;
}

function auth() {
    global $LOGIN;
    session_start();
    if (!isset($_SESSION['loggedin']) || !$_SESSION['loggedin']) {
        header('Location: '.$LOGIN);
        exit;
    }
}

?>