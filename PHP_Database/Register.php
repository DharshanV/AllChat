<?php
    $host = "localhost";
    $user = "id13639254_users";
    $databasePassword = "vYukjng7&30227";
    $database = "id13639254_allchat";
    $con = mysqli_connect($host,$user,$databasePassword,$database);

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (username, password) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ss",$username,$password);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = true;

    echo json_encode($response);
?>