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
	<form id="partnerTest" action="api.jsp" method="post">
		partnerId:<input id="partnerId" name="partnerId"
			value="40a2154b89485d89c7ebeb0fe251c75f" size="40"> <br>
			<input type="hidden" id="transData" name="transData">
	</form>
	appId:
	<input id="appId" name="appId" value=""
		size="40">
	<br>
	partnerIdchannelId:
	<input id="partnerIdChannelId" name="partnerIdChannelId" value=""
		size="40">
	<br> partnerReserved:
	<input id="partnerReserved" name="partnerReserved" value="" size="40">
	<br> partnerOrderNumber:
	<input id="partnerOrderNumber" name="partnerOrderNumber" value=""
		size="40">
	<br> userPhoneNumber:
	<input id="userPhoneNumber" name="userPhoneNumber" value="18025314707"
		size="40">
	<br> userName:
	<input id="userName" name="userName" value="" size="40">
	<br> lotteryId:
	<input id="lotteryId" name="lotteryId" value="10001" size="40">
	<br> betTotalAmount:
	<input id="betTotalAmount" name="betTotalAmount" value="1" size="40">
	<br> partnerCallbackURL:
	<input id="partnerCallbackURL" name="partnerCallbackURL" value="http://"
		size="40">
	<button id="testButton">test</button>
</body>
<script>
	$().ready(function() {
		$("#partnerOrderNumber").val(timestamp);
		$("#testButton").click(function() {
			transData = {
					partnerChannelId : $("#partnerChannelId").val(),
					appId : $("#appId").val(),
					partnerReserved : $("#partnerReserved").val(),
					partnerOrderNumber : $("#partnerOrderNumber").val(),
					userPhoneNumber : $("#userPhoneNumber").val(),
					userName : $("#userName").val(),
					lotteryId : $("#lotteryId").val(),
					betTotalAmount : $("#betTotalAmount").val(),
					partnerCallbackURL : $("#partnerCallbackURL").val()
				};
			$("#transData").val(JSON.stringify(transData));
			$("#partnerTest").submit();
		});
	})
</script>
</html>