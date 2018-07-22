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
    
</head>
  <body>
  	
	<c:import charEncoding="utf-8" url="/inc/incHeader">
		<c:param name="path" value="${requestUri}" />
	</c:import>

    <div class="container-fluid">
      <div class="row">
        
        <!-- aside start -->
 		<c:import charEncoding="utf-8" url="/inc/incLeft">
 			<c:param name="path" value="${requestUri}" />
 		</c:import>
		<!-- aside end -->	    
		
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4"><div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;"><div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div></div><div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;"><div style="position:absolute;width:200%;height:200%;left:0; top:0"></div></div></div>
         <h2 class="mt-3">공통 코드 등록</h2>
          <div class="row mt-3">
	        <div class="col-md-8 order-md-1">
	        
          	<form:form modelAttribute="codeVO" id="registFrm" name="registFrm"  data-toggle="validator">
       		<input type="hidden" name="searchCondition" id="searchCondition" value="${ searchVO.searchCondition}" />
       		<input type="hidden" name="searchKeyword" id="searchKeyword" value="${ searchVO.searchKeyword}" />
       		<input type="hidden" name="pageIndex" id="pageIndex" value="${ searchVO.pageIndex }"/>  
      		<c:if test="${flag=='U'}">
	       		<input type="hidden" name="code" id="code" value="${ result.code }"/>
      		</c:if>
				  <div class="form-group row">
				    <label for="codeCategory" class="col-sm-2 col-form-label">코드카테고리</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="code_category" name="code_category" placeholder="" value="${result.code_category }" required="" maxlength="128">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="codeValue" class="col-sm-2 col-form-label">코드값</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="code_value" name="code_value" placeholder="" value="${result.code_value }" required="" maxlength="128">
				    </div>
				  </div>
				  <div class="form-group row">
				    <label for="codeDesc" class="col-sm-2 col-form-label">코드설명</label>
				    <div class="col-sm-10">
				 	   <textarea class="form-control" id="code_desc" name="code_desc" rows="3" data-parsley-length="[0, 2000]">${result.code_desc }</textarea>
				    </div>
				  </div>
				  <div class="float-right">
				    <div>
              		<c:if test="${flag=='U'}">
              		
			        	<button type="submit" class="btn btn-primary" onclick="fnUpdate(); return false;">수정</button>
			        	<button type="submit" class="btn btn-primary" onclick="fnDelete(); return false;">삭제</button>
              		</c:if>
              		<c:if test="${flag=='I'}">
			        	<button type="submit" class="btn btn-primary" onclick="fnInsert();">등록</button>
              		</c:if>
				      
				      
				    <button type="submit" class="btn btn-secondary"  onclick="fnLinkPage('${ param.pageIndex }'); return false;">목록</button>
				    </div>
				  </div>
				</form:form>

	        </div>
	      </div>
        </main>
      </div>
    </div>
	
    <script src="<%=contextPath%>/resources/jquery-3.1.0.js"></script>
    <script src="<%=contextPath%>/resources/jquery.twbsPagination.min.js"></script>
    <script src="<%=contextPath%>/resources/bootstrap-4.1.1/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/resources/Parsley.js-2.8.1/parsley.js"></script>
    <script src="<%=contextPath%>/resources/ESR23Common_debug.js"></script>
    
    <script type="text/javascript">
	var contextPath = "${ pageContext.request.contextPath }";
	
	/**
	 *  게시판 리스트 페이지 이동
	 */
	function fnLinkPage(pageNo){
		document.registFrm.pageIndex.value = pageNo;
		document.registFrm.action = contextPath + "/admin/code/list";
		document.registFrm.submit();
	}
	
	/**
	 *  게시판 등록 
	 */
	function fnInsert(){
		
		if(!confirm("공통 코드를 등록하시겠습니까?")) return;
		$('#registFrm').parsley().on('field:validated', function() {
		})
		.on('form:submit', function() {
			document.registFrm.action = contextPath + "/admin/code/insert";
		});
		
	}
	
	/**
	 *  게시판 수정 
	 */
	function fnUpdate(){
		
		if(!confirm("공통 코드를 수정하시겠습니까?")) return;
// 		$('#registFrm').parsley().on('field:validated', function() {
// 		})
// 		.on('form:submit', function() {
// 			document.registFrm.action = contextPath + "/admin/code/update";
// 		});
		if($('#registFrm').parsley().validate()){
			document.registFrm.action = contextPath + "/admin/code/update";
			document.registFrm.submit();
		}
		
	}
	
	/**
	 *  게시판 삭제
	 */
	function fnDelete(){
		if(!confirm("게시판 정보를 삭제하시겠습니까?")) return;
		document.registFrm.action = contextPath + "/admin/code/delete";
		document.registFrm.submit();
	}
	//]]>
	</script>
	
</body>

</html>
