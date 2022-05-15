$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidpay_idSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "ItemsAPI", 
 type : type, 
 data : $("#formItem").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onItemSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onItemSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidpay_idSave").val(""); 
$("#formItem")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
			$("#hidpay_idSave").val($(this).data("pay_id")); 
			$("#pay_id").val($(this).closest("tr").find('td:eq(0)').text());
		 	$("#holder_name").val($(this).closest("tr").find('td:eq(1)').text()); 
		 	$("#ctype").val($(this).closest("tr").find('td:eq(2)').text()); 
		 	$("#card_no").val($(this).closest("tr").find('td:eq(3)').text()); 
		 	$("#cvv").val($(this).closest("tr").find('td:eq(4)').text()); 
			$("#pay_date").val($(this).closest("tr").find('td:eq(5)').text());
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "ItemsAPI", 
		 type : "DELETE", 
		 data : "pay_id=" + $(this).data("pay_id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onItemDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onItemDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateItemForm()
{
	// HOLDER NAME
	if ($("#holder_name").val().trim() == "")
	{
	return "Insert Holder Name.";
	}
	// CARD TYPE
	if ($("#ctype").val().trim() == "")
	{
	return "Insert Card Type.";
	}
	// CARD NUMBER
	if ($("#card_no").val().trim() == "")
	{
	return "Insert Card Number.";
	}
	// CVV
	if ($("#cvv").val().trim() == "")
	{
	return "Insert CVV.";
	}
	
// PRICE-------------------------------
//if ($("#itemPrice").val().trim() == ""){
	//return "Insert Item Price.";
//}
		// is numerical value
		//var tmpPrice = $("#itemPrice").val().trim();
		//if (!$.isNumeric(tmpPrice))
	//{
	//return "Insert a numerical value for Item Price.";
	//}
		
// convert to decimal price
//$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));

// PAYED DATE
if ($("#pay_date").val().trim() == ""){
	
	return "Insert Item Pay Date.";
}
	return true;
}