<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
  String contextPath = (String)request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="../../../../favicon.ico">

    <title>회원가입</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/join.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/bootstrap-4.1.1/parsley.css" rel="stylesheet">
    
  	</head>
	<style>
	</style>
  <body class="text-center">
    <div class="form-signin">
      <div class="row mt-10">
	        <div class="col-md-12 order-md-1">
	          <h4 class="mt-3">회원 가입</h4>
	          
          		<form:form class="needs-validation" id="registFrm" name="registFrm"  data-toggle="validator">
	            <div class="row">
	              <div class="col-md-6 mb-3">
	                <label for="firstName">사용자 ID</label>
	                <input type="text" class="form-control" id="userLoginId" name="userLoginId" value="" required="" maxlength="40">
	                <div class="invalid-feedback">
	                  Valid first name is required.
	                </div>
	              </div>
	              <div class="col-md-6 mb-3">
	                <label for="firstName">사용자 이름</label>
	                <input type="text" class="form-control" id="userNm" name="userNm" value="" required="" maxlength="50">
	                <div class="invalid-feedback">
	                  Valid first name is required.
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="col-md-6 mb-3">
	                <label for="firstName">비밀번호</label>
<!-- 	                <input type="password" class="form-control" id="user_password" name="user_password" placeholder="" value="" required=""  -->
<!-- 	                data-parsley-pattern="/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/g" -->
<!-- 	                > -->
	                <input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="" value="" required="" 
	                data-parsley-pattern="/^(?=.*?[a-z])(?=.*?[0-9]).{5,}$/g"
	                >
	                <div class="invalid-feedback">
	                  Valid first name is required.
	                </div>
	              </div>
	              <div class="col-md-6 mb-3">
	                <label for="lastName">비밀번호 확인</label>
	                <input type="password" class="form-control" id="userPasswordChk" name="userPasswordChk" data-parsley-equalto="#userPassword"	 placeholder="" value="" required="">
	                <div class="invalid-feedback">
	                  Valid last name is required.
	                </div>
	              </div>
	            </div>
	            
	            <div class="row">
	              <div class="col-md-6 mb-3">
	                <label for="lastName">핸드폰</label>
	                <input type="text" class="form-control" id="userPhone" name="userPhone" placeholder="예시 : 0100000000" value="" maxlength="30" required="" data-parsley-type="number">
	                <div class="invalid-feedback">
	                  Valid last name is required.
	                </div>
	              </div>
	              <div class="col-md-6 mb-3">
	                <label for="email">Email 
	<!-- 	              <span class="text-muted">(Optional)</span> -->
		              </label>
		              <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="you@example.com" data-parsley-type="email">
		              <div class="invalid-feedback">
		                Please enter a valid email address for shipping updates.
		              </div>
	              </div>
	            </div>
<!-- 	            <div class="mb-3"> -->
<!-- 	              <label for="email">Email  -->
<!-- 	              <span class="text-muted">(Optional)</span> -->
<!-- 	              </label> -->
<!-- 	              <input type="text" class="form-control" id="email" placeholder="you@example.com" data-parsley-type="email"> -->
<!-- 	              <div class="invalid-feedback"> -->
<!-- 	                Please enter a valid email address for shipping updates. -->
<!-- 	              </div> -->
<!-- 	            </div> -->
	
<!-- 	            <div class="row"> -->
<!-- 	              <div class="col-md-5 mb-3"> -->
<!-- 	                <label for="country">사용자 타입</label> -->
<!-- 	                <select class="custom-select d-block w-100" id="country" required=""> -->
<!-- 	                  <option value="">Choose...</option> -->
<!-- 	                  <option>United States</option> -->
<!-- 	                </select> -->
<!-- 	                <div class="invalid-feedback"> -->
<!-- 	                  Please select a valid country. -->
<!-- 	                </div> -->
<!-- 	              </div> -->
<!-- 	              <div class="col-md-4 mb-3"> -->
<!-- 	                <label for="state">State</label> -->
<!-- 	                <select class="custom-select d-block w-100" id="state" required=""> -->
<!-- 	                  <option value="">Choose...</option> -->
<!-- 	                  <option>California</option> -->
<!-- 	                </select> -->
<!-- 	                <div class="invalid-feedback"> -->
<!-- 	                  Please provide a valid state. -->
<!-- 	                </div> -->
<!-- 	              </div> -->
<!-- 	              <div class="col-md-3 mb-3"> -->
<!-- 	                <label for="zip">Zip</label> -->
<!-- 	                <input type="text" class="form-control" id="zip" placeholder="" required=""> -->
<!-- 	                <div class="invalid-feedback"> -->
<!-- 	                  Zip code required. -->
<!-- 	                </div> -->
<!-- 	              </div> -->
<!-- 	            </div> -->
	            <hr class="mb-4">
	            <h4 class="mb-3">어린이집 정보</h4>
<!-- 	            <div class="d-block my-3"> -->
<!-- 	              <div class="custom-control custom-radio"> -->
<!-- 	                <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" required=""> -->
<!-- 	                <label class="custom-control-label" for="credit">Credit card</label> -->
<!-- 	              </div> -->
<!-- 	              <div class="custom-control custom-radio"> -->
<!-- 	                <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required=""> -->
<!-- 	                <label class="custom-control-label" for="debit">Debit card</label> -->
<!-- 	              </div> -->
<!-- 	              <div class="custom-control custom-radio"> -->
<!-- 	                <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required=""> -->
<!-- 	                <label class="custom-control-label" for="paypal">PayPal</label> -->
<!-- 	              </div> -->
<!-- 	            </div> -->
	            <div class="row">
	              <div class="col-md-6 mb-3">
	                <label for="cc-name">어린이집 이름</label>
	                <input type="text" class="form-control" id="centerNm" name="centerNm" placeholder="" required="">
	                <div class="invalid-feedback">
	                  Name on card is required
	                </div>
	              </div>
	              <div class="col-md-6 mb-3">
	                <label for="cc-number">어린이집 번호</label>
	                <input type="text" class="form-control" id="centerPhone" name="centerPhone"  placeholder="예시 : 0100000000" required="">
	                <div class="invalid-feedback">
	                  Credit card number is required
	                </div>
	              </div>
	            </div>
	            <div class="row">
	              <div class="col-md-12 mb-3">
	                <label for="cc-expiration">어린이집 주소</label>
	                <input type="text" class="form-control" id="centerAddr" name="centerAddr" placeholder="" required="">
	                <small class="text-muted float-left">상세주소로 입력</small>
	                <div class="invalid-feedback">
	                  Expiration date required
	                </div>
	              </div>
	            </div>
	            <hr class="mb-4">
	            <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="fnRegist()">가입</button>
	            <input type="hidden" id="userRoleCd" name="userRoleCd" value="100002">
	               
	          </form:form>
	          
	        </div>
	      </div>
    </div>

</body>


<script src="<%=contextPath%>/resources/bootstrap-4.1.1/js/bootstrap.min.js"></script>
<script src=" <%=contextPath%>/resources/js/sha256.js" type="text/javascript"></script> 
<script src="<%=contextPath%>/resources/js/jquery-3.1.0.js"></script>
<script src="<%=contextPath%>/resources/js/Parsley.js-2.8.1/parsley.js"></script>
<script src="<%=contextPath%>/resources/js/ESR23Common_debug.js"></script>


<script type="text/javascript">

var contextPath = "${ pageContext.request.contextPath }";

/**
 *  회원가입 버튼
 */
function fnRegist(){

	if($('#registFrm').parsley().validate()){
		//유저 중복 확인 
		$.ajax({
			type : "POST",
			url : contextPath + "/findUser",
			data : {"userLoginId":$("#userLoginId").val()},
			dataType : "json",
	        error:function(request,status,error){
	            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	         },
			success : function(data){
				if(data.data.state){
	
					//가입
					if(!confirm("가입 하시겠습니까?")) return;
					var data = $("#registFrm").serialize();
			    	$.ajax({
			    		type : "POST",
			    		url : contextPath + "/joinAction",
			    		data : data,
			    		dataType : "json",
				        error:function(request,status,error){
				            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				         },
			    		success : function(data){
			    			if(data.code=200){
			            		alert("회원가입이 되었습니다. 로그인 해주세요.")
			        			location.href = contextPath+"/login" ; 
				        	}else{
				        		//200아닌것들
				        	}
			    		}
			    	});
					
				}else{
	        		alert("비밀번호를 확인해주세요.");
				}
			}
		});

	}
	
}

</script>
</html>