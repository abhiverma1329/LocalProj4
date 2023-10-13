<%@page import="java.util.ArrayList"%>
<%@page import="in.co.rays.proj4.model.MarksheetModel"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@page import="in.co.rays.proj4.controller.MarksheetListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Marksheet List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>

</head>

<body>
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">
		<%@include file="Header.jsp"%>

		<%
			List l = (List) request.getAttribute("rollNo");

			int next = DataUtility.getInt(request.getAttribute("nextlist").toString());
			
		%>

		<center>
			<div align="center">
				<h1>Marksheet List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>
			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List<MarksheetBean>list = ServletUtility.getList(request);
			/* 	String totalString = request.getParameter("total");
				int totalReq = Integer.parseInt((totalString == null || totalString == "") ? "0" : totalString);
				List<MarksheetBean> list = new ArrayList();
				if (totalReq > 0) {
					List<MarksheetBean> lista = ServletUtility.getList(request);
					Iterator it1 = lista.iterator();
					while (it1.hasNext()) {
						bean = (MarksheetBean) it1.next();
						if (totalReq == bean.getPhysics() + bean.getChemistry() + bean.getMaths()) {
							list.add(bean);
						}

					}
					
					  list= lista.stream().filter(s-> (s.getChemistry()+s.getPhysics()+s.getMaths())==totalReq).collect(Collectors.toList()); 
				
				} else {
					list = ServletUtility.getList(request);
					
				} */
				Iterator<MarksheetBean> it = list.iterator();
				if (list.size() != 0) {
			%>


			<table width="100%" align="center">
				<tr>
					<td align="center"><label> Student Name :</font></label> <input
						type="text" name="name" placeholder="Enter Student Name"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&emsp; <label>RollNo :</label> <%--  <input type="text" name="rollNo" placeholder="Enter Roll Number" value="<%=ServletUtility.getParameter("rollNo", request)%>"> --%>

						<%=HTMLUtility.getList("rollNo123", ServletUtility.getParameter("rollNo123", request), l)%>
						&nbsp; <label>Total</label> <input type="number" name="total"
						placeholder="Enter Total Number" value=<%=request.getParameter("total") %>> &nbsp; <input
						type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>




			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing="2">

				<tr style="background: #E5E4E2">

					<th width="8%"><input type="checkbox" id="select_all"
						name="Select">Select All.</th>
					<th>S.No.</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage</th>
					<th>Result</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
							bean = it.next();

							int phy = DataUtility.getInt(DataUtility.getStringData(bean.getPhysics()));
							int chem = DataUtility.getInt(DataUtility.getStringData(bean.getChemistry()));
							int math = DataUtility.getInt(DataUtility.getStringData(bean.getMaths()));
							int total = (phy + chem + math);
							float perc = total / 3;
				%>

				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=bean.getId()%>">
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>
					<td><%=total%></td>
					<td><%=((perc) + "%")%></td>

					<td>
						<%
							if (phy >= 33 && chem >= 33 && math >= 33) {
						%> <span
						style="color: green"> Pass</span> <%
 	} else {
 %> <span
						style="color: red"> Fail</span> <%
 	}
 %>
					</td>
					<td><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>

				</tr>

				<%
					}
				%>

			</table>

			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>



					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize || next == 0) ? "disabled" : ""%>></td>

				</tr>
			</table>

			<%
				}
				if (list.isEmpty() || list.size() == 0) {
					ServletUtility.setErrorMessage("No record found ", request);
					
					
			%>
			<div align="center">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>
			<td align="center"><input type="submit" name="operation"
				value="<%=MarksheetListCtl.OP_BACK%>"></td>
				
			<%
				}
			%>


			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</center>


	<%@include file="Footer.jsp"%>
</body>
</html>