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
	
    $d_id_rec = $_POST['id_rec'];
    $d_user_id = $_POST['user_id'];
    $d_user_name = $_POST['user_name'];
	$d_password = $_POST['password'];
	$d_department = $_POST['department'];
	$d_user_sex = $_POST['user_sex'];
	$d_role_admin = $_POST['role_admin'];
	$d_role_user = $_POST['role_user'];

    
	$zsql = "";
	$zsql = $zsql." update `tbl_user` set";
	$zsql = $zsql." user_id='".$d_user_id."',";
	$zsql = $zsql." user_name='".$d_user_name."',";
	$zsql = $zsql." password='".$d_password."',";
	$zsql = $zsql." department='".$d_department."',";
	$zsql = $zsql." user_sex='".$d_user_sex."',";
	$zsql = $zsql." role_admin='".$d_role_admin."',";
	$zsql = $zsql." role_user='".$d_role_user."'";
	$zsql = $zsql." where id_rec='".$d_id_rec."'";
	
   
	
	$result = mysql_query($zsql);


    if($result){
        echo "Update Data User Berhasil ...";
    }else{
        echo "Update Data User Gagal ...";

    }
}
else
{
echo 'error';
}


//-------close database
mysql_close($conn);

?>