<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.CollegeCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>College view</title>
</head>
<body>
	<form action="CollegeCtl" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.CollegeBean"
			scope="request"></jsp:useBean>

		<center>
			<h1>
				<%
					if (bean != null && bean.getId() > 0) {
				%>
				<tr>
					<th>Update College</th>
				</tr>
				<%
					} else {
				%>
				<tr>
					<th>Add College</th>
				</tr>
				<%
					}
				%>
			</h1>
			<div>
				<h3>
					<font style="color: green"><%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h3>

				<h3>
					<font style="color: red"><%=ServletUtility.getErrorMessage(request)%>
					</font>
				</h3>
			</div>


			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th align="left">Name <span style="color: red">*</span> :
					</th>
					<td><input type="text" name="name" placeholder="Enter Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr><th style="padding: 3px"></th><td></td></tr>
				<tr>
					<th align="left">Address<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="address" placeholder="Enter Address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>
				<tr><th style="padding: 3px"></th><td></td></tr>
				<tr>
					<th align="left">State<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="state" placeholder="Enter State"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr><th style="padding: 3px"></th><td></td></tr>
				<tr>
					<th align="left">City<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="city" placeholder="Enter city"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr><th style="padding: 3px"></th><td></td></tr>
				<tr>
					<th align="left">Phone No.<span style="color: red">*</span> :
					</th>
					<td><input type="text" name="phoneNo"  maxlength="10" placeholder="Enter Phone Number"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
						<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>


                 <tr><th style="padding: 3px"></th><td></td></tr>
                 
				<tr>
					<th></th>
					<%
						if (bean.getId() > 0) {
					%>
					<td>&nbsp;  <input type="submit" name="operation"
						value="<%=CollegeCtl.OP_UPDATE%>"> &nbsp;  <input
						type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>">
					</td>
					<%
						} else {
					%>

					<td>&nbsp;  <input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>"> &nbsp;  <input
						type="submit" name="operation" value="<%=CollegeCtl.OP_RESET%>">
					</td>
					<%
						}
					%>

				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>