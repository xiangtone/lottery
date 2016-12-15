<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.x.pay.TestZhiHuiFu"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>有卡科技-双色球</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
<link rel="stylesheet" href="js-css/app.min.css">
<script src="js-css/zepto.js"></script>
<link rel="stylesheet" type="text/css" href="js-css/ssq_global.css">
<script src="js-css/app.min.js"></script>
<script src="js-css/ssq_xuanhao.js"></script>
</head>

<body class="app-no-scrollbar app-loaded">
	<form id="selectNumber" action="zhihuifu.jsp" method="post">
		<input id="partnerDebug" name="partnerDebug" type="hidden"
			value="<%=request.getParameter("partnerDebug")%>"> <input
			id="partnerId" name="partnerId" type="hidden"
			value="<%=request.getParameter("partnerId")%>"> <input
			id="transData" name="transData" type="hidden"
			value='<%=request.getParameter("transData")%>'> <input
			id="betDetail" name="betDetail" type="hidden" value=""> <input
			id="userPhoneNumber" name="userPhoneNumber" type="hidden"
			value="<%=request.getParameter("userPhoneNumber")%>">
	</form>
	<div class="app-page" data-page="home">
		<div class="select" id="select">
			<h2 class="red-bar" style="background: #ffcccc">红球(请选择6个红球)：</h2>
			<div class="select-red" id="select-red">
				<ul>
					<li id="redQiu1" class="nqiu" onclick="changeRedQiu(this)">01</li>
					<li id="redQiu2" class="nqiu" onclick="changeRedQiu(this)">02</li>
					<li id="redQiu3" class="nqiu" onclick="changeRedQiu(this)">03</li>
					<li id="redQiu4" class="nqiu" onclick="changeRedQiu(this)">04</li>
					<li id="redQiu5" class="nqiu" onclick="changeRedQiu(this)">05</li>
					<li id="redQiu6" class="nqiu" onclick="changeRedQiu(this)">06</li>
					<li id="redQiu7" class="nqiu" onclick="changeRedQiu(this)">07</li>
					<li id="redQiu8" class="nqiu" onclick="changeRedQiu(this)">08</li>
					<li id="redQiu9" class="nqiu" onclick="changeRedQiu(this)">09</li>
					<li id="redQiu10" class="nqiu" onclick="changeRedQiu(this)">10</li>
					<li id="redQiu11" class="nqiu" onclick="changeRedQiu(this)">11</li>
					<li id="redQiu12" class="nqiu" onclick="changeRedQiu(this)">12</li>
					<li id="redQiu13" class="nqiu" onclick="changeRedQiu(this)">13</li>
					<li id="redQiu14" class="nqiu" onclick="changeRedQiu(this)">14</li>
					<li id="redQiu15" class="nqiu" onclick="changeRedQiu(this)">15</li>
					<li id="redQiu16" class="nqiu" onclick="changeRedQiu(this)">16</li>
					<li id="redQiu17" class="nqiu" onclick="changeRedQiu(this)">17</li>
					<li id="redQiu18" class="nqiu" onclick="changeRedQiu(this)">18</li>
					<li id="redQiu19" class="nqiu" onclick="changeRedQiu(this)">19</li>
					<li id="redQiu20" class="nqiu" onclick="changeRedQiu(this)">20</li>
					<li id="redQiu21" class="nqiu" onclick="changeRedQiu(this)">21</li>
					<li id="redQiu22" class="nqiu" onclick="changeRedQiu(this)">22</li>
					<li id="redQiu23" class="nqiu" onclick="changeRedQiu(this)">23</li>
					<li id="redQiu24" class="nqiu" onclick="changeRedQiu(this)">24</li>
					<li id="redQiu25" class="nqiu" onclick="changeRedQiu(this)">25</li>
					<li id="redQiu26" class="nqiu" onclick="changeRedQiu(this)">26</li>
					<li id="redQiu27" class="nqiu" onclick="changeRedQiu(this)">27</li>
					<li id="redQiu28" class="nqiu" onclick="changeRedQiu(this)">28</li>
					<li id="redQiu29" class="nqiu" onclick="changeRedQiu(this)">29</li>
					<li id="redQiu30" class="nqiu" onclick="changeRedQiu(this)">30</li>
					<li id="redQiu31" class="nqiu" onclick="changeRedQiu(this)">31</li>
					<li id="redQiu32" class="nqiu" onclick="changeRedQiu(this)">32</li>
					<li id="redQiu33" class="nqiu" onclick="changeRedQiu(this)">33</li>
				</ul>
			</div>
			<h2 class="blue-bar" style="background: #66ffff">蓝球(请选择1个蓝球)：</h2>
			<div id="select-blue" class="select-blue">
				<ul>
					<li id="blueQiu1" class="nqiu" onclick="changeBlueQiu(this)">01</li>
					<li id="blueQiu2" class="nqiu" onclick="changeBlueQiu(this)">02</li>
					<li id="blueQiu3" class="nqiu" onclick="changeBlueQiu(this)">03</li>
					<li id="blueQiu4" class="nqiu" onclick="changeBlueQiu(this)">04</li>
					<li id="blueQiu5" class="nqiu" onclick="changeBlueQiu(this)">05</li>
					<li id="blueQiu6" class="nqiu" onclick="changeBlueQiu(this)">06</li>
					<li id="blueQiu7" class="nqiu" onclick="changeBlueQiu(this)">07</li>
					<li id="blueQiu8" class="nqiu" onclick="changeBlueQiu(this)">08</li>
					<li id="blueQiu9" class="nqiu" onclick="changeBlueQiu(this)">09</li>
					<li id="blueQiu10" class="nqiu" onclick="changeBlueQiu(this)">10</li>
					<li id="blueQiu11" class="nqiu" onclick="changeBlueQiu(this)">11</li>
					<li id="blueQiu12" class="nqiu" onclick="changeBlueQiu(this)">12</li>
					<li id="blueQiu13" class="nqiu" onclick="changeBlueQiu(this)">13</li>
					<li id="blueQiu14" class="nqiu" onclick="changeBlueQiu(this)">14</li>
					<li id="blueQiu15" class="nqiu" onclick="changeBlueQiu(this)">15</li>
					<li id="blueQiu16" class="nqiu" onclick="changeBlueQiu(this)">16</li>
				</ul>
			</div>
			<div class="btn">
				<i class="jixuan"> <a class="button" onclick="jixuan()">机选1注</a>
				</i> <i class="ant"> <a class="button" onclick="clearQiu()">清空选球</a>
				</i>
			</div>
		</div>
		<div class="result" id="result">
			<h2 style="background: #ffffbb; color: #cc6600">投注结果：</h2>
			<div class="select">
				<div id="check-left" class="left">
					<ul>
						<li id="redQiuCheck1" style="DISPLAY: none" class="redqiu">01</li>
						<li id="redQiuCheck2" style="DISPLAY: none" class="redqiu">02</li>
						<li id="redQiuCheck3" style="DISPLAY: none" class="redqiu">03</li>
						<li id="redQiuCheck4" style="DISPLAY: none" class="redqiu">04</li>
						<li id="redQiuCheck5" style="DISPLAY: none" class="redqiu">05</li>
						<li id="redQiuCheck6" style="DISPLAY: none" class="redqiu">06</li>
						<li id="redQiuCheck7" style="DISPLAY: none" class="redqiu">07</li>
						<li id="redQiuCheck8" style="DISPLAY: none" class="redqiu">08</li>
						<li id="redQiuCheck9" style="DISPLAY: none" class="redqiu">09</li>
						<li id="redQiuCheck10" style="DISPLAY: none" class="redqiu">10</li>
						<li id="redQiuCheck11" style="DISPLAY: none" class="redqiu">11</li>
						<li id="redQiuCheck12" style="DISPLAY: none" class="redqiu">12</li>
						<li id="redQiuCheck13" style="DISPLAY: none" class="redqiu">13</li>
						<li id="redQiuCheck14" style="DISPLAY: none" class="redqiu">14</li>
						<li id="redQiuCheck15" style="DISPLAY: none" class="redqiu">15</li>
						<li id="redQiuCheck16" style="DISPLAY: none" class="redqiu">16</li>
						<li id="redQiuCheck17" style="DISPLAY: none" class="redqiu">17</li>
						<li id="redQiuCheck18" style="DISPLAY: none" class="redqiu">18</li>
						<li id="redQiuCheck19" style="DISPLAY: none" class="redqiu">19</li>
						<li id="redQiuCheck20" style="DISPLAY: none" class="redqiu">20</li>
						<li id="redQiuCheck21" style="DISPLAY: none" class="redqiu">21</li>
						<li id="redQiuCheck22" style="DISPLAY: none" class="redqiu">22</li>
						<li id="redQiuCheck23" style="DISPLAY: none" class="redqiu">23</li>
						<li id="redQiuCheck24" style="DISPLAY: none" class="redqiu">24</li>
						<li id="redQiuCheck25" style="DISPLAY: none" class="redqiu">25</li>
						<li id="redQiuCheck26" style="DISPLAY: none" class="redqiu">26</li>
						<li id="redQiuCheck27" style="DISPLAY: none" class="redqiu">27</li>
						<li id="redQiuCheck28" style="DISPLAY: none" class="redqiu">28</li>
						<li id="redQiuCheck29" style="DISPLAY: none" class="redqiu">29</li>
						<li id="redQiuCheck30" style="DISPLAY: none" class="redqiu">30</li>
						<li id="redQiuCheck31" style="DISPLAY: none" class="redqiu">31</li>
						<li id="redQiuCheck32" style="DISPLAY: none" class="redqiu">32</li>
						<li id="redQiuCheck33" style="DISPLAY: none" class="redqiu">33</li>
					</ul>
				</div>
				<div id="check-right" class="right">
					<ul>
						<li id="blueQiuCheck1" style="DISPLAY: none" class="blueqiu">01</li>
						<li id="blueQiuCheck2" style="DISPLAY: none" class="blueqiu">02</li>
						<li id="blueQiuCheck3" style="DISPLAY: none" class="blueqiu">03</li>
						<li id="blueQiuCheck4" style="DISPLAY: none" class="blueqiu">04</li>
						<li id="blueQiuCheck5" style="DISPLAY: none" class="blueqiu">05</li>
						<li id="blueQiuCheck6" style="DISPLAY: none" class="blueqiu">06</li>
						<li id="blueQiuCheck7" style="DISPLAY: none" class="blueqiu">07</li>
						<li id="blueQiuCheck8" style="DISPLAY: none" class="blueqiu">08</li>
						<li id="blueQiuCheck9" style="DISPLAY: none" class="blueqiu">09</li>
						<li id="blueQiuCheck10" style="DISPLAY: none" class="blueqiu">10</li>
						<li id="blueQiuCheck11" style="DISPLAY: none" class="blueqiu">11</li>
						<li id="blueQiuCheck12" style="DISPLAY: none" class="blueqiu">12</li>
						<li id="blueQiuCheck13" style="DISPLAY: none" class="blueqiu">13</li>
						<li id="blueQiuCheck14" style="DISPLAY: none" class="blueqiu">14</li>
						<li id="blueQiuCheck15" style="DISPLAY: none" class="blueqiu">15</li>
						<li id="blueQiuCheck16" style="DISPLAY: none" class="blueqiu">16</li>
					</ul>
				</div>
			</div>
			<div class="total">
				您选择了 <span id="fs_red_count" class="red"><strong id="fs_red">0</strong></span>
				个红球<span class="dt-total"></span>， <span id="fs_blue_count"
					class="blue"><strong id="fs_blue">0</strong></span> 个蓝球，共 &nbsp;<span
					id="fs_zhushu">0</span>&nbsp; 注，共 <span class="red"><strong
					id="fs_money">0</strong></span> <span class="unit-text">元</span>
			</div>
			<div class="touzhu">
				<input id="touzhu" type="button" class="button" value="立即投注">
			</div>
		</div>
	</div>
</body>
</html>