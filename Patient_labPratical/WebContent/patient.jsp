<%@page import="model.Patient" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
</head>
<body>
<%
	session.setAttribute("statusMsg", "");
	System.out.println("Tryinng to process");
	
	//save
	if (request.getParameter("pname")!= null)
	{
		Patient patient = new Patient();
		String stsMsg = "";
			if(request.getParameter("hidItemIDSave") == "")
			{
				 stsMsg = patient.insertItem(request.getParameter("pname"), request.getParameter("ppsw"), request.getParameter("pdob"), request.getParameter("pnic")); 
			}
			else
			{
				 stsMsg = patient.updateItem(request.getParameter("hidItemIDSave") , request.getParameter("pname"), request.getParameter("ppsw"), request.getParameter("pdob"), request.getParameter("pnic"));
				 
			}
			
			session.setAttribute("statusMsg", stsMsg);
	}
	//DELETE
	if(request.getParameter("hidItemIDDelete") != null)
	{
		Patient patient = new Patient();
		String stsMsg = patient.deleteItem(request.getParameter("hidItemIDDelete"));
		session.setAttribute("ststusMsg", stsMsg);
	}
%>
	<div class="container">
		<div class="row">
			<div class="col-6">
			<h1>Patient managemet</h1>
				<form  id="formItem" name="formItem" method="post" action="patient.jsp">
					Paitent Name: 
					<input id="pname" name="pname" type="text" class="form-control form-control-sm"> <br> 
					Paitent password:
					<input id="ppsw" name="ppsw" type="password" class="form-control form-control-sm"> <br> 
					Paitent DOB:
					<input id="pdob" name="pdob" type="text" class="form-control form-control-sm"> <br> 
					Paitent NIC: 
					<input id="pnic" name="pnic" type="text"class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					
					<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusmsg"));
					%>
				</div>
				
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
			
				Patient patient = new Patient();
				out.print(patient.readItems());
				%>
			</div>
		</div>
	</div>
	<script src="Component/jquery-3.5.1.min.js"></script>
	<script src="Component/patient.js"></script>
</body>
</html>