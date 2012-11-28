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
</head>
<body>
 <form action="login.php" method="POST">
  <p>Bitte Loggen sie sich ein:<br/></p>
  <p>Alias:<input type="Text" name="username"><br/></p>
  <p>Passwort:<input type="Password" name="password"><br/></p>
  <input type="Submit" value="Login">
 </form>
</body>
</html>