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

    <title>Dashcode Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/dashboard.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/parsley.css" rel="stylesheet">
    <style> 
    @media print {
    .a4 {page-break-before: always;}
	}
	</style>
</head>
  <body>
      	
   	<c:if test="${fn:length(resultList)!=0}">
      	<c:forEach var="result" items="${ resultList }" varStatus="status">
      		<c:forEach var="i" begin="1" varStatus="repeatStts" end="${pageExport}">
      			
      			
   				<c:if test="${( ((status.count-1)*pageExport)+repeatStts.count)%16==1}">
			    <div class="container-fluid">
			      <div class="row">
   				</c:if>
      		
      		
			      <!-- 아이의 QR코드 및 아이 정보 STRART -->
				  <div class="col-6">
					<div class="card justify-content-center">
		              <div class="card-body">
				      <div class="row">
				        <div class="col-md-8">
				         <p class="display-4">${result.centerNm}</p>
				         <p class="display-3"><small>${result.classNm}</small> ${result.childNm}</p>
<%-- 				         <p class="card-title text-muted">부모님 전화번호 : ${result.parentPhone}</p> --%>
				         </div>
				        <div class="col-md-4">
				       	 <img src="<c:url value="/common/FileView.do?filePath=${result.childQrCodeImg}"/>" class="figure-img img-fluid rounded">
				        </div>
				       </div>
				       </div>
			     	</div>
				  </div>
			      <!-- 아이의 QR코드 및 아이 정보 END -->
		      
		      
		      
   				<c:if test="${( ((status.count-1)*pageExport)+repeatStts.count)%16==0}">
			   	</div>
			   	</div>
   				<div class ="a4">
   				</div>
   				</c:if>
		      
	      	</c:forEach>
      	</c:forEach>
   	</c:if>
	      
	      
	      
	<c:if test="${fn:length(resultList)==0}">
		<div class="card justify-content-center">
			<div class="card-body">
				<p class="text-center"> 검색결과가 존재하지 않습니다.</td>
			</div>
		</div>
	</c:if>
	      
	  	
	
    <script src="<%=contextPath%>/resources/js/jquery-3.1.0.js"></script>
    <script src="<%=contextPath%>/resources/js/jquery.twbsPagination.min.js"></script>
    <script src="<%=contextPath%>/resources/bootstrap-4.1.1/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/resources/js/Parsley.js-2.8.1/parsley.js"></script>
    <script src="<%=contextPath%>/resources/js/ESR23Common_debug.js"></script>
    
    <script type="text/javascript">
	var contextPath = "${ pageContext.request.contextPath }";
	
	$(function(){
// 		window.print();
	});
	</script>
	
</body>

</html>
