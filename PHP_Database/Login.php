<?php
    $host = "localhost";
    $user = "id13639254_users";
    $password = "vYukjng7&30227";
    $database = "id13639254_allchat";
    $con = mysqli_connect($host,$user,$password,$database);

    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");

    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement,$userID,$username,$password);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["username"] = $username;
        $response["password"] = $password;
    }

    echo json_encode($response);

?>