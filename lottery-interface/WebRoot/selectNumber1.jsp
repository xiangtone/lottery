<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js-css/js/jquery-1.7.2.min.js"></script>
<script src="js-css/js/global.js"></script>
<script src="js-css/js/yqssq.js"></script>
<script src="js-css/js/h.js"></script>
<script src="js-css/js/stat.js"></script>

<script type="text/javascript">
	function changeRedQiu(oo) {
		if (oo.className == "nqiu") {
			oo.className = "redqiu";
			var cNode = document.getElementById("check-left")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].style.display = "";
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
				}
			}
		}
	}

	function cancelBlueQiu(oo) {
		if (oo.style.display == "block") {
			oo.style.display = "none";
			var cNode = document.getElementById("select-right")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].className = "nqiu";
				}
			}
		}
	}

	function cancelRedQiu(oo) {
		if (oo.style.display == "") {
			oo.style.display = "none";
			var cNode = document.getElementById("select-left")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].className = "nqiu";
				}
			}
		}

	}

	function clearQiu() {
		var cNode = document.getElementById("select")
				.getElementsByTagName('li');
		for (var i = 0; i < cNode.length; i++) {
			if (cNode[i].className == "redqiu"
					|| cNode[i].className == "blueqiu")
				cNode[i].className = "nqiu";
		}
		var cNode = document.getElementById("result")
				.getElementsByTagName('li');
		for (var i = 0; i < cNode.length; i++) {
			if (cNode[i].style.display == "block"
					|| cNode[i].style.display == "")
				cNode[i].style.display = "none";
		}
	}

	function jixuan() {
		clearQiu();
		var redBallNum = 33;
		var arr = new Array();
		for (var i = 0; i < redBallNum; i++)
			arr[i] = i + 1;
		var result = new Array(6);
		for (var n = 0; n < 6; n++) {
			var redBall = Math.floor(Math.random() * 33+1 );
			console.log(redBall);
			result[n] = arr[redBall];
			arr[redBall] = arr[redBallNum - 1];
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
				}
			}
		}
		var blueBall = Math.floor(Math.random() * 16 + 1);
		console.log(blueBall);
		var cNode = document.getElementById("select-right")
				.getElementsByTagName('li');
		var cNode = document.getElementById("check-right")
				.getElementsByTagName('li');
		for (var i = 0; i < cNode.length; i++) {
			if (blueBall == cNode[i].innerHTML
					&& blueBall == cNode[i].innerHTML) {
				cNode[i].className = "blueqiu";
				cNode[i].style.display = "block";
			}
		}
	}
</script>
<script src="js-css/js/3w.js"></script>
<link rel="stylesheet" type="text/css" href="js-css/css/ssq_home.css">
<link rel="stylesheet" type="text/css" href="js-css/css/ssq_global.css">

<title>有卡科技-双色球</title>
</head>
<body>
	<div class="leftcerter">
		<div class="nTab2">
			<div class="box-header">
				<div class="bets-info">
					<div class="loty-info">
						<b class="loty-name">双色球</b>&nbsp;&nbsp;2元最高可中1000万&nbsp;&nbsp;<span
							id="pool_info">220倍投注可清空奖池<span class="red">10亿9749万</span></span>
					</div>
					<div class="end-time">
						投注截止时间：<span id="end_time_fs" class="show-end-time">已暂停销售</span><span>每周二、四、日晚21:15开奖</span>
					</div>

				</div>
				<div class="bets-logo">
					<p class="logo"></p>
					<p class="loty-name" id="issue">第16114期</p>
				</div>
			</div>
			<!--内容开始 -->
			<div class="TabContent2">
				<div id="myTab1_Content0">
					<div id="select" class="select">
						<div id="select-left" class="left">
							<h3>红球(选择6个红球)</h3>
							<ul id="a">
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
									<td width="25%" align="center" bgcolor="#FDF8EC"><strong>投注结果<br>(点击号码可取消选择)
									</strong></td>
									<td><div class="select">
											<div id="check-left" class="left">
												<ul>
													<li id="redQiuCheck1" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">01</li>
													<li id="redQiuCheck2" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">02</li>
													<li id="redQiuCheck3" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">03</li>
													<li id="redQiuCheck4" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">04</li>
													<li id="redQiuCheck5" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">05</li>
													<li id="redQiuCheck6" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">06</li>
													<li id="redQiuCheck7" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">07</li>
													<li id="redQiuCheck8" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">08</li>
													<li id="redQiuCheck9" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">09</li>
													<li id="redQiuCheck10" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">10</li>
													<li id="redQiuCheck11" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">11</li>
													<li id="redQiuCheck12" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">12</li>
													<li id="redQiuCheck13" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">13</li>
													<li id="redQiuCheck14" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">14</li>
													<li id="redQiuCheck15" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">15</li>
													<li id="redQiuCheck16" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">16</li>
													<li id="redQiuCheck17" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">17</li>
													<li id="redQiuCheck18" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">18</li>
													<li id="redQiuCheck19" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">19</li>
													<li id="redQiuCheck20" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">20</li>
													<li id="redQiuCheck21" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">21</li>
													<li id="redQiuCheck22" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">22</li>
													<li id="redQiuCheck23" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">23</li>
													<li id="redQiuCheck24" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">24</li>
													<li id="redQiuCheck25" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">25</li>
													<li id="redQiuCheck26" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">26</li>
													<li id="redQiuCheck27" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">27</li>
													<li id="redQiuCheck28" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">28</li>
													<li id="redQiuCheck29" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">29</li>
													<li id="redQiuCheck30" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">30</li>
													<li id="redQiuCheck31" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">31</li>
													<li id="redQiuCheck32" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">32</li>
													<li id="redQiuCheck33" style="DISPLAY: none" class="redqiu"
														onclick="cancelRedQiu(this)">33</li>
												</ul>
											</div>
											<div id="check-right" class="right">
												<ul>
													<li id="blueQiuCheck1" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">01</li>
													<li id="blueQiuCheck2" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">02</li>
													<li id="blueQiuCheck3" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">03</li>
													<li id="blueQiuCheck4" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">04</li>
													<li id="blueQiuCheck5" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">05</li>
													<li id="blueQiuCheck6" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">06</li>
													<li id="blueQiuCheck7" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">07</li>
													<li id="blueQiuCheck8" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">08</li>
													<li id="blueQiuCheck9" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">09</li>
													<li id="blueQiuCheck10" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">10</li>
													<li id="blueQiuCheck11" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">11</li>
													<li id="blueQiuCheck12" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">12</li>
													<li id="blueQiuCheck13" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">13</li>
													<li id="blueQiuCheck14" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">14</li>
													<li id="blueQiuCheck15" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">15</li>
													<li id="blueQiuCheck16" style="DISPLAY: none"
														class="blueqiu" onclick="cancelBlueQiu(this)">16</li>
												</ul>
											</div>
										</div></td>
									<td width="15%" align="center">
										<div class="jixuan">
											<input name="" type="button" value="机选" onclick="jixuan()">
										</div>
										<div class="ant">
											<input name="" type="button" value="清空列表"
												onclick="clearQiu()">
										</div>
									</td>
								</tr>

							</tbody>
						</table>
						<div class="touzhu">
							<input name="touzhu" type="button" value="立即投注" onclick="touzhu()">
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
<style type="text/css">
@charset "utf-8";
/* CSS Document */
.leftcenter {
	float: left;
	width: 730px;
	margin: 10px 0 0;
	overflow: hidden;
	align: center
}

.line_border {
	border: 1px solid #eaeaea;
	border-width: 0px 1px 1px 1px;
}

.dotline {
	clear: both;
	height: 10px;
	border-bottom: 1px dotted #ccc;
	margin: 0 10px;
	overflow: hidden;
}

.dotline02 {
	clear: both;
	height: 1px;
	line-height: 1px;
	font-size: 1px;
	border-bottom: 1px dotted #ccc;
	margin: 0 10px;
	overflow: hidden;
}

.dotline03 {
	clear: both;
	height: 1px;
	line-height: 1px;
	font-size: 1px;
	border-bottom: 1px dotted #ccc;
	margin: 10px 0;
	overflow: hidden;
}

.imgAdbox {
	margin: 0 0px 10px 9px
}

.nTab2 {
	clear: both;
	width: 722px;
	border: 1px #FB9F4C solid;
	height: 470px;
	overflow: hidden;
	position: relative;
}

.nTab2 .TabTitle2 {
	clear: both;
	overflow: hidden;
	height: 30px;
}

.nTab2 .TabTitle2 ul {;
	border: 0;
	margin: 0;
	padding: 4px 0 0 10px;
	background: url(ssqtabbj.jpg) no-repeat;
	height: 26px;
}

.nTab2 .TabTitle2 li {
	color: #CE1F03;
	float: left;
	width: 160px;
	height: 18px;
	line-height: 18px;
	cursor: pointer;
	padding: 4px 0px 2px 0px;
	list-style-type: none;
	text-align: center;
	margin-right: 2px;
}

.nTab2 .TabTitle2 .active {
	border-left: 1px #CE1F03 solid;
	border-top: 1px #CE1F03 solid;
	border-right: 1px #CE1F03 solid;
	border-bottom: 1px #FFF solid;
	color: #FF0000;
	font-weight: bold;
}

.nTab2 .TabTitle2 .normal {
	background: url(ssqtab.png) repeat-x;
	color: #666;
	border: 1px #CE1F03 solid;
	border-bottom-width: 0px;
}

.nTab2 .TabTitle2 .normal a {
	font-weight: bold;
}

.nTab2 .TabContent2 {
	background: #fff;
	padding: 13px 0;
	width: 700px;
	height: 200px;
	margin: 0 auto;
	color: #000;
}

.nTab2 .TabContent2 h4 {
	height: 20px;
	line-height: 20px;
	font: normal 12px "宋体";
	text-align: center;
}

.box-header {
	position: relative;
	height: 115px;
	background: url("//888.gtimg.com/images/trade/public/p_head_topbg.png")
		no-repeat;
	font: 12px/1.5 tahoma, arial, '宋体';
	z-index: 10;
}

.bets-logo {
	position: absolute;
	height: 100px;
	width: 80px;
	left: 20px;
	top: 10px;
}

.bets-logo .logo {
	background:
		url("//888.gtimg.com/images/v1.0/qqcaipiao/digital/digital_logo.jpg")
		no-repeat;
	display: block;
	height: 80px;
	width: 80px;
}

.bets-logo .loty-name {
	color: #333333;
	font-family: "Microsoft Yahei";
	font-weight: bold;
	text-align: center;
	margin-top: 5px;
}

.bets-info {
	position: relative;
	margin: 0 270px 0 125px;
}

.bets-info .loty-name {
	font-family: "微软雅黑";
	font-size: 20px;
	font-weight: bold;
	text-align: center;
}

.bets-info .loty-info {
	padding-top: 8px;
	color: #333333;
	font-family: "宋体";
	font-size: 14px;
	font-weight: bold;
}

.bets-info .end-time {
	padding-top: 7px;
}

.bets-info .red {
	color: #e60012
}

.bets-info .show-end-time {
	padding-right: 13px;
}

.nTab2 .TabContent2 .jixuan {
	text-align: right;
	height: 20px;
	padding: 3px 15px 3px 10px;
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

.nTab2 .TabContent2 .touzhu {
	text-align: right;
	height: 20px;
	padding: 20px 270px 3px 0;
}

.nTab2 .TabContent2 .touzhu input {
	background: url(js-css/webindex_122.jpg);
	width: 90px;
	height: 20px;
	line-height: 20px;
	border: 0;
	cursor: pointer;
}

.nTab2 .TabContent2 .select {
	overflow: hidden;
	height: 125px;
}

.nTab2 .TabContent2 .select .left {
	float: left;
	width: 60%;
	text-align: center;
}

.nTab2 .TabContent2 .select .left h3 {
	height: 19px;
	background: url(js-css/tabred.png);
	font: normal 12px/19px "";
	color: red;
}

.nTab2 .TabContent2 .select .left ul {
	width: 300px;
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.nTab2 .TabContent2 .select .left ul li {
	float: left;
	margin: 0 5px 5px 0;
	cursor: pointer;
}

.nTab2 .TabContent2 .select .right {
	float: right;
	width: 40%;
	text-align: center;
}

.nTab2 .TabContent2 .select .right h3 {
	background: url(js-css/tabblue.png);
	height: 19px;
	font: normal 12px/19px "";
	color: blue;
}

.nTab2 .TabContent2 .select .right ul {
	width: 180px;
	margin: 0 auto;
	padding: 10px 0;
	overflow: hidden;
}

.nTab2 .TabContent2 .select .right ul li {
	float: left;
	margin: 0 5px 5px 0;
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
	overflow: hidden;
}

.nTab2 .TabContent2 .result p {
	float: left;
	height: 30px;
	line-height: 30px;
	padding-left: 20px;
}

.nTab2 .TabContent2 .result p font {
	color: #F00;
}

.nTab2 .TabContent2 .result .select {
	overflow: hidden;
	border: solid 1px #CCC;
	height: 80px;
	margin-top: 2px;
	padding-top: 10px;
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
</style>
</html>