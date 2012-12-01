<?php
require_once 'secure/paths.php';
session_start();
if (isset($_SESSION['loggedin']) && $_SESSION['loggedin']) {
    header('Location: '.$HOME);
    exit;
}

require_once 'secure/auth_sys.php';
login();
?>
<!DOCTYPE html>
<html>
<head>
<title>GamesMoon: Login</title>
<link rel="stylesheet" type="text/css" media="screen" href="./css/format.css">

</head>
<body>

  <div align="center">
    <h1><span id="g">W</span>
        <span id="r">i</span>
        <span id="y">l</span>
        <span id="p">l</span>
        <span id="b">k</span>
        <span id="t">o</span>
        <span id="o">m</span>
        <span id="p">m</span>
        <span id="g">e</span>
        <span id="r">n</span>
    </h1>
    
  </div>

  <div align="center">
    <form action="login.php" method="POST">
    <p>Bitte Loggen sie sich ein:<br/></p>
    <p>Alias: <input type="Text" name="username"><br/></p>
    <p>Passwort: <input type="Password" name="password"><br/></p>
    <input type="Submit" value="Login">
    </form>
  </div>
  
</body>
</html>