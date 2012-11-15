 <?php
 session_start();
 if (isset($_SESSION['loggedin']) && $_SESSION['loggedin']) {
     echo('LOGIN');
 }
 
 require_once 'secure/auth_sys.php';
 login();
 ?>
 <html>
 <body>
  <form action="login.php" method="POST">
   Bitte Loggen sie sich ein:
   <input type="Text" name="username">
   <input type="Text" name="password">
   <input type="Submit" value="Weiter">
  </form>
 </body>
</html>