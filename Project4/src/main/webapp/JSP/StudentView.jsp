<%@page import="in.co.rays.proj4.bean.CollegeBean"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.controller.StudentListCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $(function() {
    $( "#udat4" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1980:2002',
	  dateFormat:'dd-mm-yy'
    });
  } );
  </script>
</head>
<body>
    <jsp:useBean id="bean" class="in.co.rays.proj4.bean.StudentBean" scope="request"></jsp:useBean>
    	
	<form action="<%=ORSView.STUDENT_CTL%>" method="post">
    <%@include file="Header.jsp"%>
    
    <% 
    	List <CollegeBean> clist = (List <CollegeBean>)request.getAttribute("collegeList");
    
    %>
    
    <center>
        <h1>
        	<%
        		if( bean != null && bean.getId()>0){
        	%> 
        	<tr><th><font>Update Student</font></th></tr>
        	<% }else{%>
        	<tr><th><font>Add Student</font></th></tr>
        	<% }%>
        </h1>
		
		<div>
		<h3><font style="color: green"><%=ServletUtility.getSuccessMessage(request) %></font></h1>
		<h3><font style="color: red"><%=ServletUtility.getErrorMessage(request) %></font>
		</h1>
		</div>
		
		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<input type="hidden" name="createdby" value="<%=bean.getCreatedBy()%>">
		<input type="hidden" name="modifiedby" value="<%=bean.getModifiedBy()%>">
		<input type="hidden" name="createddatetime" value="<%=bean.getCreatedDatetime()%>">
		<input type="hidden" name="modifieddatetime" value="<%=bean.getModifiedDatetime()%>">

	<table>
	
		<tr>
		<th align="left">CollegeName <span style="color: red">*</span> :</th>
		<td><%=HTMLUtility.getList("collegename", String.valueOf(bean.getCollegeId()), clist) %>
		<td style="position: fixed"><font color="red" ><%=ServletUtility.getErrorMessage("collegename", request)%></font>
		</td>
		</tr>
		
	  <tr><th style="padding: 3px"></th></tr>    	
		
		<tr>
		<th align="left">FirstName <span style="color: red">*</span> :</th>
		<td><input type="text" name="firstname" placeholder="Enter First Name" style="width: 170px" value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
		<td style="position: fixed"><font  color="red"><%=ServletUtility.getErrorMessage("firstname", request)%></font>
		</td>
		</tr>
		
		  <tr><th style="padding: 3px"></th></tr>    
		
		<tr>
		<th align="left" >LastName <span style="color: red">*</span> :</th>
		<td><input type="text" name="lastname" placeholder="Enter Last Name" style="width: 170px" value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
		<td style="position: fixed"><font  color="red"><%=ServletUtility.getErrorMessage("lastname", request)%></font>
		</td>
		</tr>
		
		  <tr><th style="padding: 3px"></th></tr>    
		
		 <tr>
		  <th align="left">Date Of Birth <span style="color: red">*</span> :</th>
          <td><input type="text" style="width: 170px" name="dob" id="udat4" readonly="readonly" placeholder=" Date of Birth" size="25"  value="<%=DataUtility.getDateString(bean.getDob())%>"></td> 
         <td style="position: fixed"><font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
   
   <tr><th style="padding: 3px"></th></tr>    
	
		<tr>
		<th align="left">MobileNo <span style="color: red">*</span> :</th>
		<td><input type="text" name="mobile" maxlength="10" placeholder="Enter Mobile No" style="width: 170px" value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
		<td style="position: fixed" ><font color="red"><%=ServletUtility.getErrorMessage("mobile", request)%></font>
		</td>
		</tr>
	
		  <tr><th style="padding: 3px"></th></tr>    
	
		<tr>
		<th align="left">Email-Id <span style="color: red">*</span> :</th>
		<td><input type="text" name="email" placeholder="Enter Email_Id" style="width: 170px" value="<%=DataUtility.getStringData(bean.getEmail())%>"></td>
		<td style="position: fixed" ><font color="red"><%=ServletUtility.getErrorMessage("email", request)%></font>
		</td>
		</tr>
	
		  <tr><th style="padding: 3px"></th></tr>    
		

	<tr>
	<th></th>
		<%
		if(bean.getId() > 0){ %>
		<td>
		 &nbsp;  &emsp;
		<input type="submit" name="operation" value="<%=StudentCtl.OP_UPDATE %>">
		 &nbsp;  &nbsp;
		<input type="submit" name="operation" value="<%=StudentCtl.OP_CANCEL%>"></td>
		<%}else{ %>
		<td>
		 &nbsp;  &emsp;
		<input type="submit" name="operation" value="<%=StudentCtl.OP_SAVE %>">
		 &nbsp;  &nbsp;
		<input type="submit" name="operation" value="<%=StudentCtl.OP_RESET%>"></td>
	
		<%} %>
	</tr>
	
	</table>
</form>
</center>

<%@ include file = "Footer.jsp" %>
</body>
</html>