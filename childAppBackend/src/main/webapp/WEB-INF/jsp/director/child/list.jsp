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

    <title>어린이 관리</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/dashboard.css" rel="stylesheet">

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
         <h2 class="mt-3">어린이 관리</h2>
          <div class="col-md-8 table-responsive">
          	<div class="float-right mt-3 mb-3">
				<div class="form-inline">
				<!-- 검색 start  -->
                   	<form:form modelAttribute="searchVO" action="${ contextPath }/director/child/list" name="searchFrm">
		            <div>
		            	<select class="form-control" id="searchCondition" name="searchCondition" >
					        <option value="classNm" selected="selected">반명</option>
					        <option value="childNm">어린이명</option>
					    </select>
						<input type="hidden" id="pageIndex" name="pageIndex" value="${pni.pageIndex}" />
		                <input type="text" class="form-control search" id="searchKeyword" name="searchKeyword" placeholder="검색어"  value="${pni.searchKeyword}" onkeydown="$event.keyCode===13&&fnSearch()"/>
		                <button type="button" onclick="fnSearch()" class="btn btn-secondary" >검색</button>
		            </div>
		            </form:form>
		        </div>
          	</div>
            <table class="table text-center">
              <thead>
                <tr>
                  <th>번호</th>
                  <th>어린이명</th>
                  <th>반명</th>
                  <th>부모성함</th>
                  <th>명찰</th>
                </tr>
              </thead>
              <tbody>
              	<c:if test="${fn:length(resultList)!=0}">
                 	<c:forEach var="result" items="${ resultList }" varStatus="status">
		                <tr>
		                  <td onclick="fnRegist( '${result.childId }' ,'U')">${ pni.totalRecordCount - (((pni.pageIndex - 1) * pni.recordCountPerPage) + (status.index)) }</td>
		                  <td onclick="fnRegist( '${result.childId }' ,'U')">${result.childNm }</td>
		                  <td onclick="fnRegist( '${result.childId }' ,'U')">${result.classNm }</td>
		                  <td onclick="fnRegist( '${result.childId }' ,'U')">${result.parentUserNm }</td>
		                  <td><button type="button" class="btn btn-info btn-sm" onclick="fnPopup( '${result.childId }')">명찰보기</button></td>
		                </tr>
                 	</c:forEach>
              	</c:if>
              	<c:if test="${fn:length(resultList)==0}">
	                <tr>
	                  <td colspan="5" class="text-center"> 검색결과가 존재하지 않습니다.</td>
	                </tr>
              	</c:if>
              </tbody>
            </table>
            
			  <ul id="pagination" class="pagination-sm justify-content-center"></ul>
			  
	          <div class="float-right mb-3">
	          	  <button type="button" class="btn btn-info" onclick="fnResultPopup()">검색된 명찰보기</button>
		          <button type="button" class="btn btn-primary" onclick="fnRegist('','I'); return false;">등록</button>
	          </div>
	          <div class="float-right mb-3 mr-3">
	          	<input type="text" class="form-control" id="pageExport" name="pageExport" placeholder="명찰 수량 " >	 
			  </div>
		      
          </div>

          
        </main>
      </div>
    </div>
    
    <script src="<%=contextPath%>/resources/bootstrap-4.1.1/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/resources/js/jquery-3.1.0.js"></script>
    <script src="<%=contextPath%>/resources/js/jquery.twbsPagination.min.js"></script>
    
	<script type="text/javascript">
	var contextPath = "${ pageContext.request.contextPath }";
	$(function(){
		
		var totalPage = ${pni.totalPage};
		var searchCondition = "${pni.searchCondition}";
		if(searchCondition!=""){
			$("#searchCondition").val("${pni.searchCondition}");
		}
		
		if(totalPage>0){
		    $('#pagination').twbsPagination({
		        totalPages: totalPage,
		        startPage: ${pni.pageIndex} ,
		        visiblePages: ${pni.pageSize},
		        initiateStartPageClick:false,
		        onPageClick: function (event, page) {
		            fnLinkPage(page);
		        }
		    });
		}
		
	})
	/**
	 *  어린이 리스트 검색
	 */
	function fnSearch(){
		document.searchFrm.pageIndex.value = 1;
		document.searchFrm.action = contextPath + "/director/child/list";
		document.searchFrm.submit();
	}
	
	
	/**
	 *  어린이 리스트 페이지 이동
	 */
	function fnLinkPage(pageNo){
		document.searchFrm.pageIndex.value = pageNo;
		document.searchFrm.action = contextPath + "/director/child/list";
		document.searchFrm.submit();
	}
	
	/**
	 *  어린이 등록 페이지 
	 */
	function fnRegist(childId ,flag){
		if(flag=="U"){
			document.searchFrm.action = contextPath + "/director/child/regist?flag="+flag+"&childId="+childId;
		}else{
			document.searchFrm.action = contextPath + "/director/child/regist?flag="+flag;
		}
		document.searchFrm.submit();
	}
	/**
	 *  어린이 상세 페이지 
	 */
	function fnDetail(childId){
		document.searchFrm.action = contextPath + "/director/child/detail?childId="+childId;
		document.searchFrm.submit();
	}
	
	/**
	 *  어린이 명찰보기 1개
	 */
	function fnPopup(childId){
		var popUrl = contextPath + "/director/childQr/detail?childId="+childId;	//팝업창에 출력될 페이지 URL
// 		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		var popOption = "resizable=no, scrollbars=no, status=no; fullscreen= yes;";    //팝업창 옵션(optoin)
			window.open(popUrl,"",popOption);
	}
	
	/**
	 *  어린이 검색된 명찰보기
	 */
	function fnResultPopup(){
		var popUrl = contextPath + "/director/childQr/detail";	//팝업창에 출력될 페이지 URL
		
		var param = "?pageExport="+$("#pageExport").val()
		+"&searchCondition="+$("#searchCondition").val()
		+"&searchKeyword="+$("#searchKeyword").val();
		
// 		var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		var popOption = "resizable=no, scrollbars=no, status=no; fullscreen= yes;";    //팝업창 옵션(optoin)
			window.open(popUrl+param,"",popOption);
	}
	
	//]]>
	</script>
</body>

</html>
