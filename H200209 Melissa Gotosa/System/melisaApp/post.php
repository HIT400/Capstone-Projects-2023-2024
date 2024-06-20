<?php


if(isset($_POST['vib']) && isset($_POST['lat'])&& isset($_POST['lng'])&& isset($_POST['acc']))
{
$vib = $_POST['vib'];
$lat = $_POST['lat'];
$acc = $_POST['acc'];
$lng = $_POST['lng'];


// Connect to the MySQL database
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "melisa_db";
$id = 1;

$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Update the name for the specified user ID
$sql = "UPDATE readings SET lattitude ='$lat', longitude='$lng', vibration='$vib', accelerometer='$acc' WHERE id='$id'";

if ($conn->query($sql) === TRUE) {
   echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();



}

else{
    echo "Connection failed";

}
