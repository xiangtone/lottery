<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name=”viewport” content=”width=device-width, initial-scale=2.0,
	minimum-scale=0.5, maximum-scale=3.0, user-scalable=yes” />
<script src="js-css/js/jquery-1.7.2.min.js"></script>
<script src="js-css/js/global.js"></script>
<script src="js-css/js/stat.js"></script>
<link rel="stylesheet" type="text/css" href="js-css/css/ssq_global.css">

<script type="text/javascript">
	function changeRedQiu(oo) {
		if (oo.className == "nqiu") {
			oo.className = "redqiu";
			var cNode = document.getElementById("check-left")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].style.display = "";
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
					document.getElementById("fs_blue").innerHTML = countBlueQiu();
					if (countRedQiu() == 6 && countBlueQiu() == 1) {
						document.getElementById("fs_zhushu").innerHTML = 1;
						document.getElementById("fs_money").innerHTML = 2;
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
		var cNode = document.getElementById("select")
				.getElementsByTagName('li');
		for (var i = 0; i < cNode.length; i++) {
			if (cNode[i].className == "redqiu"
					|| cNode[i].className == "blueqiu")
				cNode[i].className = "nqiu";
			document.getElementById("fs_red").innerHTML = 0;
		}
		var cNode = document.getElementById("result")
				.getElementsByTagName('li');
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
		var cNode = document.getElementById("check-left").getElementsByTagName(
				'li');
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
		if(countRedQiu()<6||countBlueQiu()<1){
			betDetail=null;
		}
		return betDetail;
	}

	$("#nTab2").css( "height" , $(window).height +"px")
	$().ready(function() {
		$("#touzhu").click(function() {
			document.getElementById("betDetail").value = touzhu();
			$("#selectNumber").submit();
		});
	})
</script>

<title>有卡科技-双色球</title>
</head>
<body>
	<form id="selectNumber" action="api.jsp" method="post">
		<input id="partnerDebug" name="partnerDebug" type="hidden"
			value="<%=request.getParameter("partnerDebug")%>"> <input
			id="partnerId" name="partnerId" type="hidden"
			value="<%=request.getParameter("partnerId")%>"> <input
			id="transData" name="transData" type="hidden"
			value='<%=request.getParameter("transData")%>'> <input
			id="betDetail" name="betDetail" type="hidden" value="">
	</form>
	<div class="nTab2">
		<!--内容开始 -->
		<div class="TabContent2">
			<div class="box-header">
				<div class="bets-info">
					<div class="loty-info">
						<b class="loty-name">有卡积分兑换双色球</b><br>2元最高可中1000万&nbsp;&nbsp;<span
							id="pool_info">220倍投注可清空奖池<span class="red">10亿9749万</span></span>
					</div>
					<div class="end-time">
						<span>每周二、四、日晚21:15开奖</span>
					</div>

				</div>
				<div class="bets-logo">
					<p class="logo"></p>
					<p class="loty-name">有卡科技</p>
				</div>
			</div>
			<div id="select" class="select">
				<div id="select-left" class="left">
					<h3>红球(选择6个红球)</h3>
					<ul >
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
							<td width="25%" align="center" bgcolor="#FDF8EC"><strong>投注结果
							</strong></td>
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
							<td width="15%">
								<div class="jixuan">
									<input name="" type="button" value="机选" onclick="jixuan()">
								</div>
								<div class="ant">
									<input name="" type="button" value="清空列表" onclick="clearQiu()">
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
			<div class="box-wxts on" id="box_wxts">
				<h3 class="txt-title">
					温馨提示 :<b></b>
				</h3>
				<p class="txt-content" id="js_warn_tips">
					1、"双色球"是福彩中心发行的彩种，每周销售3期，每周二、四、日开奖；<br>
					2、投注因网络阻塞、系统故障、彩票机故障、国家动态限号以及其它原因，造成出票未成功则视为投注失败，将在国家开奖前对投注进行撤单处理，并在撤单后再进行返款，除此之外，网站不承担任何责任；<br>
					3、投注成功与否以出票状态为准，"已出票"表示投注成功，"等待出票"表示正在出票中，"已撤单"表示投注失败；<br>
					4、为了保证您能投注成功，请您尽早投注；<br>
					5、感谢您支持社会公益事业，彩市有风险，投注需谨慎。未满18岁不得购买彩票。
				</p>
			</div>
		</div>
	</div>
</body>
<style type="text/css">
@charset "utf-8";
/* CSS Document */
.nTab2 {
	clear: both;
	width: 100%;
	height: 100%;
	overflow: hidden;
	position: absolute;
}

.nTab2 .TabContent2 {
	background: #fff;
	padding: 13px 0;
	width: 700px;
	margin: 0 auto;
	color: #000;
}

.nTab2 .TabContent2 h4 {
	line-height: 20px;
	font: normal 12px "宋体";
	text-align: center;
}

.nTab2 .TabContent2 .box-header {
	position: relative;
	height: 130px;
	background: url("//888.gtimg.com/images/trade/public/p_head_topbg.png")
		no-repeat;
	font: 12px/1.5 tahoma, arial, '宋体';
	z-index: 10;
	border: 1px #CE1F03 solid;
}

.nTab2 .TabContent2 .box-header .bets-logo {
	position: absolute;
	height: 100px;
	width: 80px;
	left: 20px;
	top: 10px;
}

.nTab2 .TabContent2 .box-header .bets-logo .logo {
	background:
		url("//888.gtimg.com/images/v1.0/qqcaipiao/digital/digital_logo.jpg")
		no-repeat;
	display: block;
	height: 80px;
	width: 80px;
}

.nTab2 .TabContent2 .box-header .bets-logo .loty-name {
	color: #333333;
	font-family: "Microsoft Yahei";
	font-weight: bold;
	text-align: center;
	margin-top: 5px;
}

.nTab2 .TabContent2 .box-header .bets-info {
	position: relative;
	margin: 0 270px 0 125px;
}

.nTab2 .TabContent2 .box-header .bets-info .loty-name {
	font-family: "微软雅黑";
	font-size: 20px;
	font-weight: bold;
	text-align: center;
}

.nTab2 .TabContent2 .box-header .bets-info .loty-info {
	padding-top: 10px;
	color: #333333;
	font-family: "宋体";
	font-size: 14px;
	font-weight: bold;
}

.nTab2 .TabContent2 .box-header .bets-info .end-time {
	padding-top: 7px;
}

.nTab2 .TabContent2 .box-header .bets-info .red {
	color: #e60012
}

.nTab2 .TabContent2 .box-header .bets-info .show-end-time {
	padding-right: 13px;
}

.nTab2 .TabContent2 .jixuan {
	text-align: right;
	height: 20px;
	padding: 0px 15px 3px 8px;
	margin-bottom: 5px;
}

.nTab2 .TabContent2 .jixuan input {
	background: url(js-css/webindex_122.jpg);
	width: 90px;
	height: 20px;
	line-height: 20px;
	border: 0;
	cursor: pointer;
}

.nTab2 .TabContent2 .ant {
	text-align: right;
	height: 20px;
	padding: 15px 15px 3px 10px;
}

.nTab2 .TabContent2 .ant input {
	background: url(js-css/webindex_122.jpg);
	width: 90px;
	height: 20px;
	line-height: 20px;
	border: 0;
	cursor: pointer;
}

.nTab2 .TabContent2 .select {
	overflow: hidden;
	height: 210px;
	border: 1px #E2D5C6 solid;
}

.nTab2 .TabContent2 .select .left {
	float: left;
	width: 50%;
	text-align: center;
}

.nTab2 .TabContent2 .select .left h3 {
	height: 19px;
	background: url(js-css/tabred.png);
	font: normal 12px/19px "";
	color: red;
	margin-bottom: 8px;
}

.nTab2 .TabContent2 .select .left ul {
	width: 300px;
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.nTab2 .TabContent2 .select .left ul li {
	float: left;
	margin: 3px 9px 9px 3px;
	cursor: pointer;
}

.nTab2 .TabContent2 .select .right {
	float: right;
	width: 50%;
	text-align: center;
}

.nTab2 .TabContent2 .select .right h3 {
	background: url(js-css/tabblue.png);
	height: 19px;
	font: normal 12px/19px "";
	color: blue;
	margin-bottom: 8px;
}

.nTab2 .TabContent2 .select .right ul {
	width: 180px;
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.nTab2 .TabContent2 .select .right ul li {
	float: left;
	margin: 3px 9px 9px 3px;
	cursor: pointer;
}

.nTab2 .TabContent2 .select .nqiu {
	background: url(js-css/ssqnonq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.nTab2 .TabContent2 .select .redqiu {
	background: url(js-css/ssqredq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.nTab2 .TabContent2 .select .blueqiu {
	background: url(js-css/ssqblueq.png) repeat-x;
	width: 21px;
	height: 17px;
	text-align: center;
	padding-top: 4px;
}

.nTab2 .TabContent2 .result {
	border: 1px #E2D5C6 solid;
	overflow: hidden;
}

.nTab2 .TabContent2 .result p {
	float: left;
	height: 80px;
	line-height: 30px;
	padding-left: 20px;
}

.nTab2 .TabContent2 .result p font {
	color: #F00;
}

.nTab2 .TabContent2 .result .select {
	overflow: hidden;
	height: 100px;
	padding-top: 12px;
}

.nTab2 .TabContent2 .result .select .left {
	float: left;
	width: 60%;
	text-align: left;
}

.nTab2 .TabContent2 .result .select .left h3 {
	background: none;
	line-height: 19px;
	height: 19px;
	padding-left: 40px;
	line-height: 19px;
}

.nTab2 .TabContent2 .result .select .left ul {
	width: 250px;
	margin: 0 auto;
	padding: 5px 0 5px 5px;
}

.nTab2 .TabContent2 .result .select .left ul li {
	float: left;
	margin: 0 5px 5px 0;
}

.nTab2 .TabContent2 .result .select .right {
	float: right;
	width: 40%;
	text-align: left;
}

.nTab2 .TabContent2 .result .select .right h3 {
	background: none;
	line-height: 19px;
	height: 19px;
	padding-left: 40px;
}

.nTab2 .TabContent2 .result .select .right ul {
	width: 120px;
	margin: 0 auto;
	padding: 5px 0;
}

.nTab2 .TabContent2 .result .select .right ul li {
	float: left;
	margin: 0 5px 5px 0;
}

.nTab2 .TabContent2 .total {
	padding: 20px 0 20px 0;
	font: 14px/20px "宋体";
	text-align: center;
	clear: left;
	border: 1px #E2D5C6 solid;
}

.nTab2 .TabContent2 .touzhu {
	text-align: right;
	height: 50px;
	padding: 30px 300px 3px 0;
	border: 1px #E2D5C6 solid;
}

.nTab2 .TabContent2  .touzhu input {
	background: #127FD3;
	width: 90px;
	height: 30px;
	line-height: 20px;
	border: 0;
	cursor: pointer;
	font-size: 14px;
	font-weight: bold;
}

.nTab2 .box-wxts { *+
	position: relative;
	_position: static;
	zoom: 1;
	background: #FEF9F3;
	border: 1px #E2D5C6 solid;
	border-top: none 0;
	padding: 0 18px;
	padding-bottom: 10px;
}

.nTab2  .box-wxts h3 {
	font-size: 16px;
	line-height: 40px;
	font-family: "microsoft yahei", "瀹嬩綋";
	padding: 0px 14px;
	border-top: 1px solid #E3DACE;
	cursor: pointer;
	color: #333;
	height: 40px;
	font-weight: bold;
	position: relative;
}

</style>

</html>