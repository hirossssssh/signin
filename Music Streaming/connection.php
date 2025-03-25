<?php
// connection.php
$servername = "localhost";
$username = "root";             // Default MySQL username
$password = "";                 // Default MySQL password (blank)
$dbname = "streamvibe_db"; // Your actual database name, replace accordingly

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
?>