<?php
$db = "mysql";
$host = "localhost";
$dbname = "gamesmoon";
$user = "root";
$pass = "root";

$dbh = new PDO($db.':host='.$host.';dbname='.$dbname, $user, $pass);
?>