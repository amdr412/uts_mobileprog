<?php

//-------------database

$dbhost = 'localhost';
$dbuser = 'root';
$dbpass = '';
$dbname = 'aflowz_db';

$conn = mysql_connect($dbhost, $dbuser, $dbpass) or die ('Could not connect: ' . mysql_error());
mysql_select_db($dbname);

//------------------------
// array for JSON response
$response = array();

	
    $d_kriteria = $_GET['kriteria'];
  	
	$zsql = "";
	$zsql = $zsql."select * from tbl_user";
	$zsql = $zsql." WHERE id_rec like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or user_id like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or user_name like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or department like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or user_sex like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or role_admin like '%%".$d_kriteria."%%'";
	$zsql = $zsql." or role_user like '%%".$d_kriteria."%%'";
	
	
	$result = mysql_query($zsql);
	
    if (mysql_num_rows($result) > 0) {
    
		$response["datauser"] = array();
			
		while ($row = mysql_fetch_array($result)) {
			// temp user array
			$datafield= array();
			$datafield["id_rec"] = $row["id_rec"];
			$datafield["user_id"] = $row["user_id"];
			$datafield["user_name"] = $row["user_name"];
			$datafield["department"] = $row["department"];
			$datafield["user_sex"] = $row["user_sex"];
			$datafield["role_admin"] = $row["role_admin"];
			$datafield["role_user"] = $row["role_user"];
			
			//------mencari img user hasil upload
			 $zsql2 = "";
			 $zsql2 = $zsql2." select * from tbl_upload_images";
			 $zsql2 = $zsql2." where id_rec_master='". $row["id_rec"]."'";
			 
			 $result2 = mysql_query($zsql2);
			 
			 if (mysql_num_rows($result2) > 0) 
			 {
				while ($row2 = mysql_fetch_array($result2)) 
				{
					$datafield["img_user"] = $row2["path_file"];
				}
			 }
			 else
			 {
				 $datafield["img_user"] = "tidak ada";
			 }

			// push single product into final response array
			array_push($response["datauser"], $datafield);
		}
		// success
		$response["success"] = 1;

		// echoing JSON response
		echo json_encode($response);
		
	}
	else
	{
		$response["success"] = 0;
		$response["message"] = "No user found";
		// echoing JSON response
		echo json_encode($response);
	}
	



//-------close database
mysql_close($conn);

?>