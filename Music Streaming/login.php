<?php
session_start();
include "db_connection.php"; // Database connection file

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    function validate($data) {
        return htmlspecialchars(stripslashes(trim($data)));
    }

    $uname = validate($_POST['username']);
    $pass = validate($_POST['password']);

    if (empty($uname) || empty($pass)) {
        header("Location: login.html?error=Username and Password are required");
        exit();
    } else {
        $stmt = $conn->prepare("SELECT * FROM users WHERE username=?");
        $stmt->bind_param("s", $uname);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows === 1) {
            $row = $result->fetch_assoc();
            if (password_verify($pass, $row['password'])) {
                $_SESSION['username'] = $row['username'];
                $_SESSION['name'] = $row['name'];
                $_SESSION['id'] = $row['id'];
                header("Location: index.html");
                exit();
            } else {
                header("Location: login.html?error=Incorrect Username or Password");
                exit();
            }
        } else {
            header("Location: login.html?error=Incorrect Username or Password");
            exit();
        }
    }
} else {
    header("Location: login.html");
    exit();
}
?>
