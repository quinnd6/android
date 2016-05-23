<?php
include_once 'connection.php';
	
	class TransactionList {
		
		private $db;
		private $connection;
		
                //connect to the database
		function __construct() {
			$this -> db = new DB_Connection();
			$this -> connection = $this->db->getConnection();
		}
		
                //get the last five transactions for a paticular user from the database
		public function getLastTenTransactions($userid)
		{
			$query = "SELECT description, price, DATE_FORMAT( dateandtime, '%a %d %b %Y %l:%i:%s %p ' ) AS time FROM optimus_transactions WHERE userid = '$userid' ORDER BY dateandtime DESC LIMIT 10 ";
			$result = mysqli_query($this->connection, $query);
                        
                        
			if(mysqli_num_rows($result)>0)
                        {
                            while($row = mysqli_fetch_assoc($result))
                            {
                                    $temp_array[] = $row;
                            }
				header('Content-Type: application/json');
                                echo json_encode(array("transactions"=>$temp_array));
				mysqli_close($this -> connection);
			}
                        else
                        {
				
				$json['error'] = ' wrong username or password';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
                public function getLastXTransactions($userid,$numOfTransactions)
		{
			$query = "SELECT description, price, DATE_FORMAT( dateandtime, '%a %d %b %Y %l:%i:%s %p ' ) AS time FROM optimus_transactions WHERE userid = '$userid' ORDER BY dateandtime DESC LIMIT $numOfTransactions";
			$result = mysqli_query($this->connection, $query);
                        
                        
			if(mysqli_num_rows($result)>0)
                        {
                            while($row = mysqli_fetch_assoc($result))
                            {
                                    $temp_array[] = $row;
                            }
				header('Content-Type: application/json');
                                echo json_encode(array("transactions"=>$temp_array));
				mysqli_close($this -> connection);
			}
                        else
                        {
				
				$json['error'] = ' wrong username or password';
				
				echo json_encode($json);
				mysqli_close($this->connection);
			}
			
		}
                
                
        }
	
	
	$transactionList = new TransactionList();
        
            if(isset($_POST['userid'])&&(isset($_POST['numOfTransactions'])))
            {
                $userid = $_POST['userid'];
                $numOfTransactions = $_POST['numOfTransactions'];
                if(!empty($userid))
                {
                    $transactionList->getLastXTransactions($userid,$numOfTransactions);
                }
            }
            
        
            else if(isset($_POST['userid'])) 
            {
		$userid = $_POST['userid'];
		//$password = $_POST['password'];
		
                if(!empty($userid))
                {
			
			//$encrypted_password = md5($password);
			$transactionList-> getLastTenTransactions($userid);
                       
                 }
            }
            
			
            else
            {
                        $json['error'] = 'You may not be logged in. Please try again later.  ';
			echo json_encode($json);
            }
		
	
?>