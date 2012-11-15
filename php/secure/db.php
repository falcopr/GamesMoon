<?php
$db = "mysql";
$host = "localhost";
$dbname = "gamesmoon";
$user = "root";
$pass = "root";

$gl_dbh = NULL;

function getdbh() {
  global $db, $host, $dbname, $user, $pass, $gl_dbh;
  if($gl_dbh == NULL) {
    $gl_dbh = new PDO($db.':host='.$host.';dbname='.$dbname, $user, $pass);
  }
  return $gl_dbh;
}

?>