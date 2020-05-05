$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//SAVE 
$(document).on("click", "#btnSave", function(event)
{
	
	//Clear alerts
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();
	
	
	var status = validatePatientForm();
	if(status != true)
	{
		$("#alertError").text(status);
		$("#alertError"). show();
		return;
	}
	
	//if valid
	$("#formItem").submit();
});

// UPDATE
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#pname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#ppsw").val($(this).closest("tr").find('td:eq(1)').text());
	$("#pdob").val($(this).closest("tr").find('td:eq(2)').text());
	$("#pnic").val($(this).closest("tr").find('td:eq(3)').text());
	 //alert("button was clicked");
	
});

function validatePatientForm()
{
	//name
	if ($("#pname").val().trim() == "")
	{
		return "Insert your name";
	}
	//password
	if ($("#ppsw").val().trim() == "")
	{
		return "Insert your password";
	}
	//DOB
	if ($("#pdob").val().trim() == "")
	{
		return "Insert your DOB";
	}
	//NIC
	if ($("#pnic").val().trim() == "")
	{
		return "Insert your name";
	}

		return true;
}





