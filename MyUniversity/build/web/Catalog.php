<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
</body>
</html>


<?php
    if (isset($_GET['pages'])){
        $page = $_GET['pages'];
        $url = $page.".php";
    if (file_exists($url)){
           echo "<Br />";
           include $url;
    }else{
        echo"<br /><div class='alert alert-warning'>Bad page !!</div>";
    }
    }else{
     include "home.php";
    }
?>