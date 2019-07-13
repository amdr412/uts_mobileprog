<?php

//-------------database

$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'aflowz_db';

$conn = mysql_connect($dbhost, $dbuser, $dbpass) or die ('Could not connect: ' . mysql_error());
mysql_select_db($dbname);

//----------------------

if($_SERVER['REQUEST_METHOD']=='POST'){
	
    $id_rec = $_POST['id_rec'];
    $user_id = $_POST['user_id'];
    $user_name = $_POST['user_name'];
	$password = $_POST['password'];
	$department = $_POST['department'];
	$user_sex = $_POST['user_sex'];
	$role_admin = $_POST['role_admin'];
	$role_user = $_POST['role_user'];

    
   
	$zsql = "";
	$zsql = $zsql."insert into tbl_user values(";
	$zsql = $zsql."'".$id_rec."',";
	$zsql = $zsql."'".$user_id."',";
	$zsql = $zsql."'".$user_name."',";
	$zsql = $zsql."'".md5($password)."',";
	$zsql = $zsql."'".$department."',";
	$zsql = $zsql."'".$user_sex."',";
	$zsql = $zsql."'".$role_admin."',";
	$zsql = $zsql."'".$role_user."')";
	
	$result = mysql_query($zsql);


    if($result){
        echo "Penyimpanan User Berhasil ...";
    }else{
        echo "Penyimpanan User Gagal ...";

    }
}
else
{
echo 'error';
}


//-------close database
mysql_close($conn);

?>