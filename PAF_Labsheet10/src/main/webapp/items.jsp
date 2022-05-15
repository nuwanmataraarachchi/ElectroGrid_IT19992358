<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>

<body style="background-color:#cff3fa";>
<div class="container"><div class="row"><div class="col-6"> 
<center><h1 style="color:grey;">Payment Management</h1></center>
<form id="formItem" name="formItem" method="post" action="items.jsp">

 Name On Card: 
 <input id="holder_name" name="holder_name" type="text" 
 class="form-control form-control-sm">
 <br> Payment Method: 
 <input id="ctype" name="ctype" type="text" 
 class="form-control form-control-sm">
 <br> Card Number: 
 <input id="card_no" name="card_no" type="text" 
 class="form-control form-control-sm">
 <br> CVC: 
 <input id="cvv" name="cvv" type="text" 
 class="form-control form-control-sm">
 <br> Payment Date: 
 <input id="pay_date" name="pay_date" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidpay_idSave" 
 name="hidpay_idSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Item itemObj = new Item();  
 out.print(itemObj.readItems()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>