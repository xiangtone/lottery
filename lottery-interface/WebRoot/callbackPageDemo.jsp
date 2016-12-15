<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.x.TouZhuXiangQing"%>
<%@ page import="com.alibaba.fastjson.JSON"%>
<%@ page import="java.util.List"%>
<%@ page import="org.x.info.BackReqBetInfo"%>
<%
	TouZhuXiangQing detail = JSON.parseObject(request.getParameter("transData"), TouZhuXiangQing.class);
	List<BackReqBetInfo> betInfoList = detail.getTicketInfoList();
	String redBall = betInfoList.get(0).getBetDetail().substring(5, 17);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes">
<title>有卡科技-个人中心</title>
<script src="js-css/app.min.js"></script>
<link rel="stylesheet" href="js-css/app.min.css">
</head>
<body>
	<div class="main">
		<h2 class="mylottery">我的彩票:</h2>
		<div class="box">
			<div class="box2">
				<div class="head">
					<span> <i class="key">订单号：</i> <i class="value"><%=detail.getOrderNumber()%></i>
						<br> <i class="key">特征码：</i> <i class="value"><%=detail.getTicketInfoList().get(0).getTicketId()%></i>
					</span>
				</div>
				<h2 class="bet_detail">投注详情:</h2>
				<div class="con_main">
					<span> <i class="key">游戏名称：</i> <i class="value">双色球</i> <br> <i
						class="key">期号：</i> <i class="value"><%=detail.getIssueNumber()%></i> <br>
						<i class="key">投注时间：</i> <i class="value"><%=betInfoList.get(0).getBetDateTime()%></i>
						<br> <i class="key">投注号码：</i> <i class="redball"><%=redBall.substring(0, 2)%>
							<%=redBall.substring(2, 4)%> <%=redBall.substring(4, 6)%> <%=redBall.substring(6, 8)%>
							<%=redBall.substring(8, 10)%> <%=redBall.substring(10, 12)%></i> <i>
							| </i> <i class="blueball"><%=betInfoList.get(0).getBetDetail().substring(19, 21)%></i>
						<br> <i class="key">总注数：</i> <i class="value">1注</i> <br> <i
						class="key">总金额：</i><i class="value">2元</i> <br> <i class="key">投注状态：</i><i class="value">投注成功</i></span>
				</div>
			</div>
		</div>
	</div>
</body>
<style>
* {
	margin: 0;
	padding: 0;
}
body{
background:#f5f5f5;
}

pre {
	max-height: 200px;
	overflow: auto;
}

div.demo {
	overflow: auto;
}

span {
	line-height: 30px;
}

.main {
	clear: both;
	width: 100%;
	margin: 0 auto;
}

.mylottery {
	color: #FF0000;
	margin: 10px 0 20px 0;
	letter-spacing: 0;
	text-shadow: 0px 1px 0px #999, 0px 2px 0px #888, 0px 3px 0px #777, 0px
		4px 0px #666, 0px 5px 0px #555, 0px 6px 0px #444, 0px 7px 0px #333,
		0px 8px 7px #001135,0 0 1em #F87, 0 0 1em #F87;//使用阴影叠加出文本外发光特   
}

.head {
	margin: 10px 0;
}

.bet_detail {
	color: transparent;
	border-top: 1px solid #ccc;
	background-color: black;
	text-shadow: rgba(255, 255, 255, 0.5) 0 5px 6px,
		rgba(255, 255, 255, 0.2) 1px 3px 3px;
	-webkit-background-clip: text;
}

.con_main {
	margin: 10px 0 15px 0;
}

.key {
	font-weight: bold;
	font-size: 18px;
}

.redball{
	color: #FF0000;
	font-weight: bold;
}

.blueball {
	color: #0000FF;
	font-weight: bold;
}

.box {
	width: 85%;
	margin:0 auto;
	background: #f3f3f3;
	position: relative;
}

.box2 {
	padding: 0 0 1px 0;
	background: #ff5511;
	background: -webkit-gradient(linear, 0% 20%, 0% 92%, from(#EE0F0F),
		to(#D1FF18), color-stop(.1, rgba(247, 6, 34, 1)));
	background: -webkit-linear-gradient(0 0 270deg, #f3f3f3, #f3f3f3 10%, #fff);
	background: -moz-linear-gradient(0 0 270deg, #f3f3f3, #f3f3f3 10%, #fff);
	background: -o-linear-gradient(0 0 270deg, #f3f3f3, #f3f3f3 10%, #fff);
	border-top: 1px solid #ccc;
	border-right: 1px solid #ccc;
	-webkit-border-radius: 0 0 60px 0/0 0 60px 0;
	-moz-border-radius: 0 0 60px 0/0 0 60px 0;
	border-radius: 0 0 60px 0/0 0 60px 0;
	-webkit-box-shadow: -1px 2px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: -1px 2px 2px rgba(0, 0, 0, 0.2);
	box-shadow: -1px 2px 2px rgba(0, 0, 0, 0.2);
}

.box2:before {
	content: '';
	width: 25px;
	height: 20px;
	position: absolute;
	bottom: 0;
	right: 0;
	-webkit-border-radius: 0 0 30px 0;
	-moz-border-radius: 0 0 30px 0;
	border-radius: 0 0 30px 0;
	-webkit-box-shadow: -2px -2px 5px rgba(0, 0, 0, 0.3);
	-moz-box-shadow: -2px -2px 5px rgba(0, 0, 0, 0.3);
	box-shadow: -2px -2px 5px rgba(0, 0, 0, 0.3);
	-webkit-transform: rotate(-20deg) skew(-40deg, -3deg)
		translate(-13px, -13px);
	-moz-transform: rotate(-20deg) skew(-40deg, -3deg)
		translate(-13px, -13px);
	-o-transform: rotate(-20deg) skew(-40deg, -3deg) translate(-13px, -13px);
	transform: rotate(-20deg) skew(-40deg, -3deg) translate(-13px, -13px);
}

</style>
</html>