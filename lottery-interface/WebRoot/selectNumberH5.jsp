<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.x.pay.TestZhiHuiFu"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>有卡科技-双色球</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes, minimal-ui">
<link rel="stylesheet" href="js-css/app.min.css">
<link rel="stylesheet" type="text/css" href="js-css/css/ssq_global.css">
<style>
/* TODO */
</style>
</head>

<body class="app-no-scrollbar app-loaded">
	<script src="js-css/zepto.js"></script>
	<script src="js-css/app.min.js"></script>
	<script>
		function changeRedQiu(oo) {
			if (oo.className == "nqiu") {
				oo.className = "redqiu";
				var cNode = document.getElementById("check-left")
						.getElementsByTagName('li');
				for (var i = 0; i < cNode.length; i++) {
					if (oo.innerHTML == cNode[i].innerHTML) {
						cNode[i].style.display = "";
						if (countRedQiu() > 6) {
							oo.className = "nqiu";
							cNode[i].style.display = "none";
						}
						document.getElementById("fs_red").innerHTML = countRedQiu();
						if (countRedQiu() == 6 && countBlueQiu() == 1) {
							document.getElementById("fs_zhushu").innerHTML = 1;
							document.getElementById("fs_money").innerHTML = 2;
						}
					}
				}
			} else {
				oo.className = "nqiu";
				var cNode = document.getElementById("check-left")
						.getElementsByTagName('li');
				for (var i = 0; i < cNode.length; i++) {
					if (oo.innerHTML == cNode[i].innerHTML) {
						cNode[i].style.display = "none";
						document.getElementById("fs_red").innerHTML = countRedQiu();
						if (countRedQiu() < 6 || countBlueQiu() < 1) {
							document.getElementById("fs_zhushu").innerHTML = 0;
							document.getElementById("fs_money").innerHTML = 0;
						}
					}
				}
			}
		}

		function changeBlueQiu(oo) {
			if (oo.className == "nqiu") {
				oo.className = "blueqiu";
				var cNode = document.getElementById("check-right")
						.getElementsByTagName('li');
				for (var i = 0; i < cNode.length; i++) {
					if (oo.innerHTML == cNode[i].innerHTML) {
						cNode[i].style.display = "block";
						if (countBlueQiu() > 1) {
							oo.className = "nqiu";
							cNode[i].style.display = "none";
						}
						document.getElementById("fs_blue").innerHTML = countBlueQiu();
						if (countRedQiu() == 6 && countBlueQiu() == 1) {
							document.getElementById("fs_zhushu").innerHTML = 1;
							document.getElementById("fs_money").innerHTML = 2;
						}
						if (countRedQiu() > 6) {
							oo.className = "nqiu";
							cNode[i].style.display = "none";
						}
					}
				}
			} else {
				oo.className = "nqiu";
				var cNode = document.getElementById("check-right")
						.getElementsByTagName('li');
				for (var i = 0; i < cNode.length; i++) {
					if (oo.innerHTML == cNode[i].innerHTML) {
						cNode[i].style.display = "none";
						document.getElementById("fs_blue").innerHTML = countBlueQiu();
						if (countRedQiu() < 6 || countBlueQiu() < 1) {
							document.getElementById("fs_zhushu").innerHTML = 0;
							document.getElementById("fs_money").innerHTML = 0;
						}
					}
				}
			}
		}

		function countRedQiu() {
			var redCount = 0;
			for (var i = 1; i <= 33; i++) {
				if (document.getElementById("redQiu" + i).className == "redqiu") {
					redCount++;
				}
			}
			return redCount;
		}

		function countBlueQiu() {
			var blueCount = 0;
			for (var i = 1; i <= 16; i++) {
				if (document.getElementById("blueQiu" + i).className == "blueqiu") {
					blueCount++;
				}
			}
			return blueCount;
		}

		function clearQiu() {
			var cNode = document.getElementById("select").getElementsByTagName(
					'li');
			for (var i = 0; i < cNode.length; i++) {
				if (cNode[i].className == "redqiu"
						|| cNode[i].className == "blueqiu")
					cNode[i].className = "nqiu";
				document.getElementById("fs_red").innerHTML = 0;
			}
			var cNode = document.getElementById("result").getElementsByTagName(
					'li');
			for (var i = 0; i < cNode.length; i++) {
				if (cNode[i].style.display == "block"
						|| cNode[i].style.display == "")
					cNode[i].style.display = "none";
				document.getElementById("fs_blue").innerHTML = 0;
			}
			document.getElementById("fs_zhushu").innerHTML = 0;
			document.getElementById("fs_money").innerHTML = 0;
		}

		function jixuanRedBall() {
			var redBallNum = 33;
			var arr = new Array();
			for (var i = 0; i < redBallNum; i++)
				arr[i] = i + 1;
			var result = new Array();
			for (var n = 0; n < 6; n++) {
				var redBall = Math.floor(Math.random() * redBallNum) + 1;
				console.log(redBall);
				result[n] = arr[redBall - 1];
				arr[redBall - 1] = arr[redBallNum - 1];
				redBallNum--;
			}
			for (var n = 0; n < 6; n++) {
				var redBall = result[n];
				var sNode = document.getElementById("select-left")
						.getElementsByTagName('li');
				var cNode = document.getElementById("check-left")
						.getElementsByTagName('li');
				for (var i = 0; i < sNode.length; i++) {
					if (redBall == sNode[i].innerHTML
							&& redBall == cNode[i].innerHTML) {
						sNode[i].className = "redqiu";
						cNode[i].style.display = "";
						document.getElementById("fs_red").innerHTML = 6;
					}
				}
			}
		}

		function jixuan() {
			clearQiu();
			jixuanRedBall();
			var blueBall = Math.floor(Math.random() * 16) + 1;
			console.log(blueBall);
			var sNode = document.getElementById("select-right")
					.getElementsByTagName('li');
			var cNode = document.getElementById("check-right")
					.getElementsByTagName('li');
			for (var i = 0; i < sNode.length; i++) {
				if (blueBall == sNode[i].innerHTML
						&& blueBall == sNode[i].innerHTML) {
					sNode[i].className = "blueqiu";
					cNode[i].style.display = "block";
					document.getElementById("fs_blue").innerHTML = 1;
				}
			}
			document.getElementById("fs_zhushu").innerHTML = 1;
			document.getElementById("fs_money").innerHTML = 2;
		}

		function touzhu() {
			var cNode = document.getElementById("check-left")
					.getElementsByTagName('li');
			var result = new Array();
			var res = "";
			for (var i = 0; i < cNode.length; i++) {
				if (cNode[i].style.display == "") {
					result[i] = cNode[i].innerHTML;
					res = res + result[i] + "";
				}
			}
			var rNode = document.getElementById("check-right")
					.getElementsByTagName('li');
			var blueBall;
			for (var i = 0; i < rNode.length; i++) {
				if (rNode[i].style.display == "block") {
					blueBall = rNode[i].innerHTML;
				}
			}
			var betDetail = "00106" + res + "01" + blueBall;
			if (countRedQiu() < 6 || countBlueQiu() < 1) {
				betDetail = null;
			}
			return betDetail;
		}

		$().ready(function() {
			$("#touzhu").click(function() {
				//todo
				if (countBlueQiu() == 1 && countRedQiu() == 6) {
					document.getElementById("betDetail").value = touzhu();
					$("#selectNumber").submit();
				} else {
					alert("请选择6个红球和1个蓝球！");
				}
			});
		})
	</script>

	<form id="selectNumber" action="zhihuifu.jsp" method="post">
		<input id="partnerDebug" name="partnerDebug" type="hidden"
			value="<%=request.getParameter("partnerDebug")%>"> <input
			id="partnerId" name="partnerId" type="hidden"
			value="<%=request.getParameter("partnerId")%>"> <input
			id="transData" name="transData" type="hidden"
			value='<%=request.getParameter("transData")%>'> <input
			id="betDetail" name="betDetail" type="hidden" value="">
	</form>
	<div class="app-page" data-page="home" style="">
		<div id="select" class="select">
			<div id="select-left" class="left">
				<h3>红球(选择6个红球)</h3>
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
			<div id="select-right" class="right">
				<h3>蓝球(选择1个蓝球)</h3>
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
		</div>
		<div class="result" id="result">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tbody>
					<tr>
						<td width="20%" align="center" bgcolor="#FDF8EC"><strong
							style="font-size: 14px">投注结果 </strong></td>
						<td><div class="select">
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
							</div></td>
						<td width="20%">
							<div class="btu">
								<div class="jixuan">
									<input name="" type="button" value="机选" onclick="jixuan()">
								</div>
								<div class="ant">
									<input name="" type="button" value="清空列表" onclick="clearQiu()">
								</div>
							</div>
						</td>
					</tr>

				</tbody>
			</table>
		</div>
		<div class="total">
			您选择了 <span id="fs_red_count" class="red"><strong id="fs_red">0</strong></span>
			个红球<span class="dt-total"></span>， <span id="fs_blue_count"
				class="blue"><strong id="fs_blue">0</strong></span> 个蓝球，共 &nbsp;<span
				id="fs_zhushu">0</span>&nbsp; 注，共 <span class="red"><strong
				id="fs_money">0</strong></span> <span class="unit-text">元</span>
		</div>
		<div class="touzhu">
			<input id="touzhu" name="touzhu" type="button" value="立即投注">
		</div>
	</div>


</body>
<style>
.result .btu {
	overflow: hidden;
	height: 100px;
}

.btu .jixuan {
	text-align: right;
	height: 20px;
	margin-bottom: 15px;
}

.btu .jixuan input {
	background: url(js-css/webindex_122.jpg);
	height: 20px;
	width: 58px;
	border: 1px #467c9e solid;
	cursor: pointer;
	margin: 15px 5px 30px 2px;
}

.ant {
	text-align: right;
	height: 20px;
}

.ant input {
	background: url(js-css/webindex_122.jpg);
	height: 20px;
	width: 58px;
	border: 1px #467c9e solid;
	cursor: pointer;
	margin: 30px 5px 0 2px;
}

.select {
	overflow: hidden;
	height: 215px;
	border: 1px #E2D5C6 solid;
}

.select .left {
	float: left;
	width: 65%;
	text-align: center;
}

.select .left h3 {
	height: 19px;
	background: url(js-css/tabred.png);
	font: normal 14px/19px "";
	color: red;
	margin-bottom: 8px;
}

.select .left ul {
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.select .left ul li {
	float: left;
	margin: 2px 6px 6px 3px;
	cursor: pointer;
}

.select .right {
	float: right;
	width: 35%;
	text-align: center;
}

.select .right h3 {
	background: url(js-css/tabblue.png);
	height: 19px;
	font: normal 14px/19px "";
	color: blue;
	margin-bottom: 8px;
}

.select .right ul {
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.select .right ul li {
	float: left;
	margin: 2px 6px 6px 3px;
	cursor: pointer;
}

.select .nqiu {
	background: url(js-css/ssqnonq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.select .redqiu {
	background: url(js-css/ssqredq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.select .blueqiu {
	background: url(js-css/ssqblueq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.result {
	border: 1px #E2D5C6 solid;
	overflow: hidden;
}

.result p {
	float: left;
	height: 80px;
	line-height: 30px;
	padding-left: 20px;
}

.result p font {
	color: #F00;
}

.result .select {
	overflow: hidden;
	height: 100px;
}

.result .select .left {
	float: left;
	width: 80%;
	text-align: left;
}

.result .select .left h3 {
	background: none;
	line-height: 19px;
	height: 19px;
	padding-left: 40px;
}

.result .select .left ul {
	margin: 0 auto;
	padding: 5px 5px;
}

.result .select .left ul li {
	float: left;
	margin: 0 5px 5px 0;
}

.result .select .right {
	float: left;
	width: 20%;
	text-align: left;
}

.result .select .right h3 {
	background: none;
	line-height: 19px;
	height: 19px;
	padding-left: 40px;
}

.result .select .right ul {
	margin: 0 auto;
	padding: 5px 0;
}

.result .select .right ul li {
	float: left;
	margin: 0 5px 5px 0;
}

.total {
	padding: 20px 0 20px 0;
	font: 14px/20px "宋体";
	text-align: center;
	clear: left;
	border: 1px #E2D5C6 solid;
}

.touzhu {
	text-align: center;
}

.touzhu input {
	background: url(js-css/webindex_122.jpg);
	height: 20px;
	border: 1px #467c9e solid;
	cursor: pointer;
	font-size: 14px;
	font-weight: bold;
	margin: 60px 5px 5px 0;
}
</style>
</html>