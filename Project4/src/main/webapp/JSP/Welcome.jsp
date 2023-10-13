<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
    <form action="<%=ORSView.WELCOME_CTL%>">
        <%@ include file="Header.jsp"%>
                    <h1 align="Center">
                        <font size="10px" color="red">Welcome to ORS </font>
                    </h1>
        
                    <%
                    UserBean beanUserBean = (UserBean) session.getAttribute("user");
                        if (beanUserBean != null) {
                            if (beanUserBean.getRoleId() == RoleBean.STUDENT) {
                    %>
        
                    <h2 align="Center">
                        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your
                            Marksheet </a>
                    </h2>
                     
                     <%
                            }
                        }
                     %>
                
                </form>
        
        <%@ include file="Footer.jsp"%>