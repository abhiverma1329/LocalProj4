<%@page import="in.co.rays.proj4.bean.TimeTableBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.TimeTableCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TimeTable</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"
	type="text/javascript"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script>
<link
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css"
	rel="Stylesheet" type="text/css" />
	
	



  <script>
	$(function() {
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			minDate: +1,
			maxDate: "+1Y",
			dateFormat: "dd-mm-yy",
		});
	});
</script>

</head>
<body>
<jsp:useBean id="bean" class="in.co.rays.proj4.bean.TimeTableBean" scope="request"></jsp:useBean>

<form action="<%=ORSView.TIMETABLE_CTL %>" method="post">

	<%@include file="Header.jsp" %>

<center>
	<%
	List <TimeTableBean> courseList =(List<TimeTableBean>)request.getAttribute("CourseList"); 
	List <TimeTableBean> subjectList = (List<TimeTableBean>)request.getAttribute("SubjectList");
	%>
	
	<input type="hidden" name="id" value="<%=bean.getId() %>">
	<input type="hidden" name="createdby" value="<%=bean.getCreatedBy() %>">
	<input type="hidden" name="modifiedby" value="<%=bean.getModifiedBy() %>">
	<input type="hidden" name="createddatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime()) %>">
	<input type="hidden" name="modifiedby" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
	
	<div align = "center">
		<h1 >
			<% if(bean != null && bean.getId() >0 ){ %>
			
		<tr><th>Update TimeTable</th></tr>
				
			<% }else{ %>
		
		<tr ><th >Add TimeTable</th></tr>	
			<% } %>
		</h1>
		
	<h3 align="center"><font style="color: red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
	<h3 align="center"><font style="color: green"><%=ServletUtility.getSuccessMessage(request) %></font></h3>
	</div>

<table>
	<tr>
	<th align="left">Course <span style="color: red">*</span> :</th>
	<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), courseList) %></td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("courseId",request) %></font> 
	</td></tr>
	
 <tr><th style="padding: 3px"></th></tr>   
	
	<tr><th align="left">Subject <span style="color: red">*</span> :</th>
	<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectList) %></td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("subjectId",request) %></font> 
	</td></tr>
	
 <tr><th style="padding: 3px"></th></tr>   
	
	<tr>
	<th align="left">Semester<span style="color: red">*</span> :</th>
	<td><%
		LinkedHashMap<String , String> map = new LinkedHashMap< String , String>();
		map.put("1st","1st");
		map.put("2nd","2nd");
		map.put("3rd","3rd");
		map.put("4th","4th");
		map.put("5th","5th");
		map.put("6th","6th");
		map.put("7th","7th");
		map.put("8th","8th");
	
		String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
	%>
	<%=htmlList %>
	</td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("semester",request) %></font> 
	</td></tr>
	
 <tr><th style="padding: 3px"></th></tr>  
  
	<tr><th align="left">Exam Date <span style="color: red">*</span> :</th>
	<td> <input type="text" readonly="readonly" id="datepicker" style="width: 170px" placeholder="Select Date" name="ExDate" value="<%=DataUtility.getDateString(bean.getExamDate()) %>">
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("ExDate",request) %></font> 
	</td></tr>
	
 <tr><th style="padding: 3px"></th></tr>   
 
	<tr><th align="left">Exam Time <span style="color: red">*</span> :</th>
	<td>
	<%
		LinkedHashMap<String , String > map1 = new LinkedHashMap<String  ,String >();
		map1.put("08:00 AM to 11:00 AM","08:00 AM to 11:00 AM");
		map1.put("12:00 PM to 03:00 PM","12:00 PM to 03:00 PM");
		map1.put("04:00 PM to 07:00 PM","04:00 PM to 07:00 PM");
	
		String htmlList1 = HTMLUtility.getList("ExTime", bean.getExamTime(), map1);
	%>
	<%=htmlList1 %>
	</td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("ExTime",request) %></font> 
	</td></tr>
	
<%-- 	<tr><th align = "right">Description <span style="color: red">*</span></th>
	<td><input type="text" name="description" placeholder="Enter Description" size="25" value="<%=DataUtility.getStringData(bean.getDescription())%>">
	
	<%
	if(bean != null && bean.getId()>0) {
	%>
	<%=DataUtility.getStringData(bean.getDescription()) %>
	<% } %>
	</td>
	<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("description", request) %>	</font>	</td></tr>
	
 --%>
 
 	<tr><th style="padding: 3px"></th>
	<td></td></tr>
	
	<tr><th></th><td></td>
	</tr>
	
	<tr align="center">	
	<%
	if(bean != null && bean.getId() > 0){
	%>
		<td colspan="3">
		 &emsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_UPDATE%>">
		 &nbsp;  
		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_CANCEL%>">
		</td>
		<%} else { %>
		
		<td colspan="3">
		 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_SAVE%>">
		 &nbsp;  
		<input type="submit" name="operation" value="<%=TimeTableCtl.OP_RESET%>">
		</td>
		<% } %>
	</tr>
</table>
</form>	
</center>

	<%@include file="Footer.jsp" %>
</body>
</html>