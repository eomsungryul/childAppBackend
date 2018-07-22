<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${ requestScope['javax.servlet.forward.servlet_path'] }" var="requestUri" />
<%
  String contextPath = (String)request.getContextPath();
%>
<c:if test="${result.exercise_equip_cd!=null }">
<form:form modelAttribute="mappingVO" id="registFrm" name="registFrm" enctype="multipart/form-data" data-toggle="validator"  onsubmit="return false;">
<%-- ${result.exercise_equip_cd!=null } --%>
<%-- ${result.exercise_equip_cd==null } --%>
<%-- ${result.exercise_equip_cd } --%>
<input type="hidden" name="exercise_equip_cd" id="exercise_equip_cd" value="${ result.exercise_equip_cd}" />
<input type="hidden" name="exercise_range_cd" id="exercise_range_cd" value="${ result.exercise_range_cd}" />
<input type="hidden" name="use_yn" id="use_yn" value="${ result.use_yn}" />
 <div class="form-group row">
   <label for="exercise_desc" class="col-sm-4 col-form-label">운동설명</label>
   <div class="col-sm-8">
	   <textarea class="form-control" id="exercise_desc" name="exercise_desc" rows="3" data-parsley-length="[0, 2000]">${result.exercise_desc }</textarea>
   </div>
 </div>
 <div class="form-group row">
   <label for="cardio_yn" class="col-sm-4 col-form-label">유산소 여부</label>
   <div class="col-sm-8">
<!-- 	  <input class="form-check-input" type="radio" name="cardio_yn" id="cardio_yn_Y" value="Y" checked> -->
<!-- 	  <label class="form-check-label" for="cardio_yn_Y">Y</label> -->
<!-- 	  <input class="form-check-input" type="radio" name="cardio_yn" id="cardio_yn_N" value="N"> -->
<!-- 	  <label class="form-check-label" for="cardio_yn_N">N</label> -->
	  <input class="" type="radio" name="cardio_yn" id="cardio_yn_Y" value="Y" checked>
	  <label class="" for="cardio_yn_Y">Y</label>
	  <input class="" type="radio" name="cardio_yn" id="cardio_yn_N" value="N">
	  <label class="" for="cardio_yn_N">N</label>
   </div>
 </div>
 <div class="form-group row">
   <label for="exercise_video" class="col-sm-4 col-form-label">운동 비디오</label>
   <div class="col-sm-8">
     <input type="text" class="form-control" id="exercise_video" name="exercise_video" placeholder="" value="${result.exercise_video }" data-parsley-length="[0, 2000]" data-parsley-type="url">
   </div>
 </div>
 <div class="float-right">
   <div>
      	<button type="submit" class="btn btn-primary" onclick="fnDetailInsert();">등록</button>
   </div>
 </div>
</form:form>
</c:if>
<c:if test="${result.exercise_equip_cd==null }">
없음
</c:if>
	