<?php
//-------------database

$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'aflowz_db';

$conn = mysql_connect($dbhost, $dbuser, $dbpass) or die ('Could not connect: ' . mysql_error());
mysql_select_db($dbname);

//----------------------

 
 $image_file = $_POST['d_image_file'];
 $nama_file = $_POST['d_nama_file'];
 $id_rec = GetIdRec();
 $id_rec_master = $_POST['d_id_rec_master'];
 $nama_proses = $_POST['d_nama_proses'];
 
 

 $s_nama_file=str_replace(' ','_',$nama_file);
 $s_nama_file=str_replace("'","`",$nama_file);
 

 $path = "../app_upload/".$id_rec_master."_".$nama_file .".png";
 $path_db = "/app_upload/".$id_rec_master."_".$nama_file .".png";
 file_put_contents($path,base64_decode($image_file));
 
 $zsql = "";
 $zsql = $zsql." select id_rec from tbl_upload_images";
 $zsql = $zsql." where id_rec_master='". $id_rec_master."'";
 
 $ds=mysql_query($zsql);
 
 if (mysql_num_rows($ds)>0)
 {
 
    $zsql = "";
 	$zsql = $zsql." update tbl_upload_images set";
 	$zsql = $zsql." path_file='".$path_db."',";
 	$zsql = $zsql." nama_photo='".$nama_file."'";
 	$zsql = $zsql." where id_rec_master='". $id_rec_master."'";
 	$zsql = $zsql." and nama_proses='".$nama_proses."'";
 }
 else
 {
 
 	$zsql = "";
 	$zsql = $zsql."insert into tbl_upload_images values(";
 	$zsql = $zsql."'".$id_rec."',";
 	$zsql = $zsql."'".$id_rec_master."',";
 	$zsql = $zsql."'".$nama_proses."',";
 	$zsql = $zsql."'".$path2."',";
	$zsql = $zsql."'".$nama_file."')";
	
 }

 
 if(mysql_query($zsql))
 {
   echo "Successfully Uploaded";
 }
 function GetIdRec()
	{
		$microtime = microtime();
		$comps = explode(' ', $microtime);
		$ms=sprintf('%d%03d', $comps[1], $comps[0] * 1000);
		$ms=substr($ms,-3);
		return  date("YmdHis", time()).$ms;
	}

//-------close database
mysql_close($conn);
?>