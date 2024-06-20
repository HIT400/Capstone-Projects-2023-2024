<?php

// Get the parameters from the URL
$id = 1;
$status = $_GET['status'];

// Connect to the MySQL database
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "melisa_db";

$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Update the name for the specified user ID
$sql = "UPDATE falsealarm SET falseState = '$status' WHERE id='$id'";

if ($conn->query($sql) === TRUE) {
   // echo "Record updated successfully";
} else {
   // echo "Error updating record: " . $conn->error;
}

$conn->close();

?>
