function changeRedQiu(oo) {
		if (oo.className == "rqiu") {
			oo.className = "redqiu";
			var cNode = document.getElementById("check-left")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].style.display = "";
					if (countRedQiu() > 6) {
						oo.className = "rqiu";
						cNode[i].style.display = "none";
					}
					document.getElementById("fs_red").innerHTML = countRedQiu();
					if (countRedQiu() == 6 && countBlueQiu() == 1) {
						document.getElementById("fs_zhushu").innerHTML = 1;
						document.getElementById("fs_money").innerHTML = 200;
					}
				}
			}
		} else {
			oo.className = "rqiu";
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
		if (oo.className == "bqiu") {
			oo.className = "blueqiu";
			var cNode = document.getElementById("check-right")
					.getElementsByTagName('li');
			for (var i = 0; i < cNode.length; i++) {
				if (oo.innerHTML == cNode[i].innerHTML) {
					cNode[i].style.display = "block";
					if (countBlueQiu() > 1) {
						oo.className = "bqiu";
						cNode[i].style.display = "none";
					}
					document.getElementById("fs_blue").innerHTML = countBlueQiu();
					if (countRedQiu() == 6 && countBlueQiu() == 1) {
						document.getElementById("fs_zhushu").innerHTML = 1;
						document.getElementById("fs_money").innerHTML = 200;
					}
					if (countRedQiu() > 6) {
						oo.className = "rqiu";
						cNode[i].style.display = "none";
					}
				}
			}
		} else {
			oo.className = "bqiu";
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
		var cNode = document.getElementById("select-red")
				.getElementsByTagName('li');
		for (var i = 0; i < cNode.length; i++) {
			if (cNode[i].className == "redqiu")
				cNode[i].className = "rqiu";

		}
		var sNode = document.getElementById("select-blue")
		.getElementsByTagName('li');
for (var i = 0; i < sNode.length; i++) {
	if (sNode[i].className == "blueqiu")
		sNode[i].className = "bqiu";
}
		var rNode = document.getElementById("result")
				.getElementsByTagName('li');
		for (var i = 0; i < rNode.length; i++) {
			if (rNode[i].style.display == "block"
					|| rNode[i].style.display == "")
				rNode[i].style.display = "none";
			document.getElementById("fs_red").innerHTML = 0;
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
			var sNode = document.getElementById("select-red")
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
	}

	function jixuan() {
		clearQiu();
		jixuanRedBall();
		document.getElementById("fs_red").innerHTML = 6;
		var blueBall = Math.floor(Math.random() * 16) + 1;
		var sNode = document.getElementById("select-blue")
				.getElementsByTagName('li');
		var cNode = document.getElementById("check-right")
				.getElementsByTagName('li');
		for (var i = 0; i < sNode.length; i++) {
			if (blueBall == sNode[i].innerHTML
					&& blueBall == sNode[i].innerHTML) {
				sNode[i].className = "blueqiu";
				cNode[i].style.display = "block";
			}
		}
		document.getElementById("fs_blue").innerHTML = 1;
		document.getElementById("fs_zhushu").innerHTML = 1;
		document.getElementById("fs_money").innerHTML = 200;
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
		if (countRedQiu() < 6 || countBlueQiu() < 1) {
			betDetail = null;
		}
		return betDetail;
	}

	$(document).ready(function() {
		$("#touzhu").click(function() {
			if (countBlueQiu() == 1 && countRedQiu() == 6) {
				$("#betDetail").val(touzhu());
				$("#selectNumber").submit(); 
			} else {
				alert("请选择6个红球和1个蓝球！");
			}
		});
	})