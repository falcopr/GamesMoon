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
    <h1><span id="g">G</span>
        <span id="r">a</span>
        <span id="y">m</span>
        <span id="pu">e</span>
        <span id="b">s</span>
        <span id="t">m</span>
        <span id="o">o</span>
        <span id="pi">o</span>
        <span id="y">n</span>
    </h1>
    
  </div>

  <div align="right">
    <form action="login.php" method="POST">
      <table>
        <tr>
        <th align="right">
        </th>
        <th align="left">
          <p><span>Bitte hier einloggen:</span></p>
        </th>
        </tr>
        <tr>
        <td align="right">
          <p><span>Alias:</span></p>
        </td>
        <td align="left">
          <input type="Text" name="username">
        </td>
        </tr>
        <tr>
        <td align="right">
          <p><span>Passwort:</span>
        </td>
        <td align="left">
          <input type="Password" name="password">
        </td>
        <tr>
        <td>
        </td>
        <td align="center">
             <input type="Submit" value="Login">
        </td>
        </tr>
          </p>
        </tr>

      </table>
    </form>
    </div>
  
</body>
</html>