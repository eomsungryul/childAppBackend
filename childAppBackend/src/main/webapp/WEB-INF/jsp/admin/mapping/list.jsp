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

    <title>공통 코드 관리</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/dashboard.css" rel="stylesheet">
	
</head>
  
	<style>
	table tr.active {background: #ccc;}
	</style>
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
         <h2 class="mt-3">운동 기구-범위 매핑 관리 </h2>
          <div class="col-md-8 table-responsive">
<!--           	<div class="float-right mt-3 mb-3"> -->
<!-- 				<div class="form-inline"> -->
<!-- 				검색 start  -->
<%--                    	<form:form modelAttribute="searchVO" action="${ contextPath }/admin/code/list" name="searchFrm"> --%>
<!-- 		            <div> -->
<!-- 		            	<select class="form-control" id="searchCondition" name="searchCondition" > -->
<!-- 					        <option value="codeCategory" selected="selected">카테고리</option> -->
<!-- 					        <option value="codeValue">코드명</option> -->
<!-- 					    </select> -->
<%-- 						<input type="hidden" id="pageIndex" name="pageIndex" value="${pni.pageIndex}" /> --%>
<%-- 		                <input type="text" class="form-control search" id="searchKeyword" name="searchKeyword" placeholder="검색어"  value="${pni.searchKeyword}" onkeydown="$event.keyCode===13&&fnSearch()"/> --%>
<!-- 		                <button type="button" onclick="fnSearch()" class="btn btn-secondary" >검색</button> -->
<!-- 		            </div> -->
<%-- 		            </form:form> --%>
<!-- 		        </div> -->
<!--           	</div> -->

<!--           	<div style="overflow-y: scroll;height: 200px;"> -->

          	<div class="col-md-3 float-left" style="overflow-y: scroll;height: 350px;">
          	
	          	<table class="table table-hover">
	              <thead>
	                <tr>
	                  <th>기구명</th>
	                  <th>기구설명</th>
	                </tr>
	              </thead>
	              <tbody id="equip">
	              	<c:if test="${fn:length(resultList)!=0}">
	                 	<c:forEach var="result" items="${ resultList }" varStatus="status">
			                <tr style="cursor: pointer;" onclick="fnClick(this,${ result.code } );return false;">
			                  <td>${result.code_value }</td>
			                  <td>${result.code_desc }</td>
			                </tr>
	                 	</c:forEach>
	              	</c:if>
	              	<c:if test="${fn:length(resultList)==0}">
		                <tr>
		                  <td colspan="5" class="text-center"> 운동 범위가 존재하지 않습니다.</td>
		                </tr>
	              	</c:if>
	              </tbody>
	            </table>
          	
          	</div>
          	
          	<div class="col-md-3 float-left" style="overflow-y: scroll;height: 350px;" id="part">
          	
          	</div>
          	
          	<div class="col-md-6 float-left" style="overflow-y: scroll;height: 350px;" id="partDetail">
          	
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

	var clickVal;
	
// 	$(document).ready(function(){
// 	    $("#equip > tr").click(function(){
// 	    	$("#equip > tr").removeClass("active");
// 	        $(this).toggleClass("active");
// 	    });
// 	});
	
	/**
	 *  게시판 리스트 검색
	 */
	function fnClick(target,code){
    	$("#equip > tr").removeClass("active");
        $(target).toggleClass("active");
        clickVal = code;
		
    	$.ajax({
    		type : "POST",
    		url : contextPath + "/admin/mapping/partList",
    		dataType : "html",
    		data : {"code" : clickVal },
    		success : function(data){
    			$("#part").html(data);
    		}
    	});
        
	}
	
	/**
	 *  체크박스 클릭시 업데이트
	 */
	function chkBoxOnClick(target,code){
		
		var use_yn = $(target).is(":checked") ? 'Y' : 'N';
		var exercise_equip_cd = clickVal;
		var exercise_range_cd = code;
		
    	$.ajax({
    		type : "POST",
    		url : contextPath + "/admin/mapping/insert",
    		dataType : "html",
    		data : {"use_yn" : use_yn, 
    				"exercise_equip_cd" : exercise_equip_cd,
    				"exercise_range_cd" : exercise_range_cd,
    				},
    		success : function(data){
//     			$("#part").html(data);
    		}
    	});
        
	}
	
	/**
	 *  부위 클릭 시 상세정보 입력창 
	 */
	function partClick(target,code){

		var exercise_equip_cd = clickVal;
		var exercise_range_cd = code;
		var use_yn = $(target).find("input:checkbox").is(":checked") ? true : false;
		if(!use_yn){
			alert('기구-부위 매핑 체크박스를 체크해주세요.');
			return;
		}
    	$.ajax({
    		type : "POST",
    		url : contextPath + "/admin/mapping/partDetail",
    		dataType : "html",
    		data : {
    				"exercise_equip_cd" : exercise_equip_cd,
    				"exercise_range_cd" : exercise_range_cd
    				},
    		success : function(data){
    			$("#partDetail").html(data);
    		}
    	});
        
	}
	
	
	
	/**
	 *  기구-부위 등록  
	 */
	function fnRegist(){
		console.log(clickVal);
		var partArray=[];
		$("input:checkbox[name=partChk]:checked").each(function(){
			partArray.push($(this).val());
		});
		console.log(partArray);
		
		

    	$.ajax({
    		type : "POST",
    		url : contextPath + "/admin/mapping/insert",
    		dataType : "html",
    		data : {"code" : clickVal,"partArray" : partArray },
    		success : function(data){
    	    	
    		}
    	});
	}
	
	
	/**
	 *  기구-부위 상세등록  
	 */
	function fnDetailInsert(){

		if(!confirm("기구-부위 상세등록을 하시겠습니까?")) return;
		if($('#registFrm').parsley().validate()){
			var data = $("#registFrm").serialize();
	    	$.ajax({
	    		type : "POST",
	    		url : contextPath + "/admin/mapping/detailUpdate",
	    		dataType : "json",
	    		data : data,
	    		success : function(data){
	    	    	
	    		}
	    	});
		}
	}
	
	
	</script>
</body>

</html>
