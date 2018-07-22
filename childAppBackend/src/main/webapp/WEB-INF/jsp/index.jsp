<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<%
  String contextPath = (String)request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] }" var="requestUri" />
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Kids Diary</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/signin.css" rel="stylesheet">
  	</head>
  	
  <body class="text-center">
    <form class="form-signin">
      <img class="mb-4" src="https://getbootstrap.com/docs/4.1/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Kids Diary</h1>
      <label for="user_login_id" class="sr-only">ID</label>
      <input type="text" id="user_login_id" name="user_login_id" class="form-control" placeholder="ID" required="" autofocus="">
      <label for="user_password" class="sr-only">Password</label>
      <input type="password" id="user_password" name="user_password" class="form-control" placeholder="Password" required="">
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="button" id="login">로그인</button>
      <div class="mt-1">
        <a href="<%=contextPath%>/join" id="popup_create">
        <u><p>New? Create an account</p></u> </a>
      </div>
      <p class="mt-5 mb-3 text-muted">© 2018</p>
    </form>
</body>

<script src=" <%=contextPath%>/resources/js/sha256.js" type="text/javascript"></script> 
<script src="<%=contextPath%>/resources/js/jquery-3.1.0.js"></script>

<script type="text/javascript">
//<![CDATA[
$(function( $ ){ 
	var contextPath = "${ pageContext.request.contextPath }";
		$("#login").on('click',function(){
		   var user_login_id = $("#user_login_id").val();
		   var user_password = SHA256($("#user_password").val());
			$.ajax({
			        type : "post",
			        url : contextPath + "/loginAction.dojson",
		    		data : {"user_login_id" : user_login_id,
	    					"user_password" : user_password},
			        dataType : "json",
			        error:function(request,status,error){
			            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			         },
			        success : function(data) {
			        	if(data.state){
			        		if(data.auth_cd = 61){
				        		//관리자
			        			location.href = contextPath+"/admin/code/list" ; 
			        		}else{
			        			//관장
			        			
			        		}
			        	}
			        }
			      });
		})
	
});
//]]>

</script>




</html>