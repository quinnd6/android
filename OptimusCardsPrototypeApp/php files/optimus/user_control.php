<?php
include_once 'connection.php';
	
	class User {
		
		private $db;
		private $connection;
		
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
		public function does_user_exist($userid,$password)
		{
			$query = "Select * from optimus_users where userid='$userid' and password = '$password' ";
			$result = mysqli_query($this->connection, $query);
			if(mysqli_num_rows($result)>0){
				$json['success'] = ' Welcome '.$userid;
				echo json_encode($json);
				mysqli_close($this -> connection);
			}else{
				
				$json['error'] = ' wrong username or password';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
                
                
                
        }
	
	
	$user = new User();
        
	if(isset($_POST['userid'],$_POST['password'])) {
		$userid = $_POST['userid'];
		$password = $_POST['password'];
		
		if(!empty($userid) && !empty($password)){
			
			$encrypted_password = md5($password);
			$user-> does_user_exist($userid,$encrypted_password);
			
		}else{
                        $json['error'] = ' You must type both inputs';
			echo json_encode($json);
		}
		
	}
?>