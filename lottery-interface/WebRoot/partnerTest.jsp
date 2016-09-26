<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery-2.2.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var timestamp = new Date().getTime();
	</script>
	<form id="partnerTest" action="selectNumber.jsp" method="post">
		partnerId:<input id="partnerId" name="partnerId"
			value="<%=(request.getParameter("partnerId")!=null&&request.getParameter("partnerId").length()==32?request.getParameter("partnerId"):"254b11b646400585c04d851910cb7051")%>" size="40"> <br>
		<input type="hidden" id="transData" name="transData">
		partnerDebug:<select id="partnerDebug" name="partnerDebug">
		<option value="true" selected>true</option>
		<option value="false">false</option>
	</select>
	<br>
	</form>
	appId:
	<input id="appId" name="appId" value="" size="40">
	<br>partnerChannelId:
	<input id="partnerChannelId" name="partnerChannelId" value=""
		size="40">
	<br> partnerReserved:
	<input id="partnerReserved" name="partnerReserved" value="" size="40">
	<br> partnerOrderNumber:
	<input id="partnerOrderNumber" name="partnerOrderNumber" value=""
		size="40">
	<br> userPhoneNumber:
	<input id="userPhoneNumber" name="userPhoneNumber" value="<%=(request.getParameter("userPhoneNumber")!=null&&request.getParameter("userPhoneNumber").length()>0?request.getParameter("userPhoneNumber"):"13507463904")%>"
		size="40"><br>请填入自己的手机号码进行完整流程(包含注册和银行卡绑定)测试<br>测试环境：<br>
点击发送验证码之后，输入111111，进行测试环境注册。<br>
绑定身份证110105199312021302	苏三，省份选天津市、福建省、江西省、湖北省、广西壮族自治区、山西省、吉林省、安徽省、湖南省、河南省、北京市、河北省、内蒙古自治区、辽宁省、黑龙江省、上海市
，其他信息(银行卡等)随便填就可以。
	<br> userName:
	<input id="userName" name="userName" value="" size="40">
	<br> lotteryId:
	<input id="lotteryId" name="lotteryId" value="10001" size="40">
	<br> numberSelectType:
	<input id="numberSelectType" name="numberSelectType" value="1"
		size="4">1机选2单式自选
	<br> betTotalAmount:
	<input id="betTotalAmount" name="betTotalAmount" value="1" size="40"
		readonly>
	<br> partnerCallbackURL:
	<input id="partnerCallbackURL" name="partnerCallbackURL"
		value="http://api.youkala.com/partnerCallbackTest.jsp" size="40">
	<br> betMode:
	<input id="betMode" name="betMode" value="101" size="40">
	<br> betDetail:
	<input id="betDetail" name="betDetail" value=""
		size="40">
	<br>
	
	<button id="testButton">test</button>
</body>
<script>
	$().ready(function() {
		$("#partnerOrderNumber").val(timestamp);
		$("#testButton").click(function() {
			var betInfoList = [];
			var betInfo = {
				betMode : $("#betMode").val(),
				betDetail : $("#betDetail").val(),
			};
			betInfoList[0] = betInfo;

			var transData = {
				partnerChannelId : $("#partnerChannelId").val(),
				appId : $("#appId").val(),
				partnerReserved : $("#partnerReserved").val(),
				partnerOrderNumber : $("#partnerOrderNumber").val(),
				userPhoneNumber : $("#userPhoneNumber").val(),
				userName : $("#userName").val(),
				lotteryId : $("#lotteryId").val(),
				numberSelectType : $("#numberSelectType").val(),
				betTotalAmount : $("#betTotalAmount").val(),
				partnerCallbackURL : $("#partnerCallbackURL").val(),
				betInfoList : betInfoList
			};
			$("#transData").val(JSON.stringify(transData));
			$("#partnerTest").submit();
		});
	})
</script>
</html>