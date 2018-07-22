<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] }" var="requestUri" />
<head>
<%
  String contextPath = (String)request.getContextPath();
%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>방금</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/signin.css" rel="stylesheet">
  	</head>
  	
  <body class="text-center">
    <form class="form-signin">
      <img class="mb-4" src="<%=contextPath%>/resources/images/icon.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">방금</h1>
      <label for="user_login_id" class="sr-only">ID</label>
      <input type="text" id="user_login_id" name="user_login_id" class="form-control" placeholder="ID" required="" autofocus=""onkeydown="fnLogin2()">
      <label for="user_password" class="sr-only">Password</label>
      <input type="password" id="user_password" name="user_password" class="form-control" placeholder="Password" onkeydown="event.keyCode===13&&fnLogin()" required="">
      <div class="checkbox mb-3">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="button" id="login" onclick="fnLogin();">로그인</button>
      <div class="mt-1">
        <a href="<%=contextPath%>/join" id="popup_create">
        <u><p>New? Create an account</p></u> </a>
      </div>
      <p class="mt-5 mb-3 text-muted">© 2018</p>
    </form>
</body>

<script src=" <%=contextPath%>/resources/js/sha256.js" type="text/javascript"></script> 
<script src="<%=contextPath%>/resources/js/jquery-3.1.0.js"></script>
    <script src="<%=contextPath%>/resources/bootstrap-4.1.1/js/bootstrap.min.js"></script>

<script type="text/javascript">

var contextPath = "${ pageContext.request.contextPath }";

function fnLogin(){
   var user_login_id = $("#user_login_id").val();
   var user_password = SHA256($("#user_password").val());
	$.ajax({
	        type : "post",
	        url : contextPath + "/loginAction.dojson",
    		data : {"userLoginId" : user_login_id,
					"userPassword" : user_password},
	        dataType : "json",
	        error:function(request,status,error){
	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         },
	        success : function(data) {
	        	if(data.code=200){
		        	if(data.data.state){
		        		if(data.data.userRoleCode == 100001){
			        		//관리자
		        			location.href = contextPath+"/admin/center/list" ; 
		        		}else{
		        			//원장 님 및 선생님
		        			if(data.data.confirmYn=='Y'){	        				
			        			location.href = contextPath+"/director/class/list" ; 
		        			}else{
				        		alert("승인이 허가되지 않았습니다. 관리자에게 문의해주세요.");
		        			}
		        		}
		        	}else{
		        		alert("비밀번호를 확인해주세요.");
		        	}
	        	}else{
	        		//200아닌것들
	        	}
	        }
	      });
}

</script>


</html>