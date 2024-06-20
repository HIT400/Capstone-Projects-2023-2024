<?php


    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);
    $mysqli = new mysqli("localhost", "root", "", "melisa_db");
    $result = $mysqli->query("SELECT * FROM readings WHERE id=1");
    //get row
    while ($row = $result->fetch_row()) {
      echo $row['4'].'|'.$row['3'].'|';
    }
    
?>