<?php
$host = "localhost";  // Change if needed
$user = "root";       // Your MySQL username
$pass = "";           // Your MySQL password
$dbname = "streamvibe_db"; 

$conn = new mysqli($host, $user, $pass, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>
