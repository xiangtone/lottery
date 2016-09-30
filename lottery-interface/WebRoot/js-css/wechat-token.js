function wechat() {
	var oriData = {
		userName : $("#signInUserName").val(),
		password : $("#signInPassword").val()
	};
	$
			.ajax({
				type : "get",
				url : "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb011e7747898ad8c&secret=032b056567a9f174b131c910abd6c753",
				async : true,
				// data : JSON.stringify(oriData),
				dataType : "json",
				success : function(msg) {
					console.log(msg);
				},
				error : ajaxNetworkError
			});
}

function ajaxNetworkError(XMLHttpRequest, textStatus, errorThrown) {
	// alert("ajaxNetworkError:" + XMLHttpRequest.status + "-" +
	// XMLHttpRequest.readyState + "-" + textStatus);
	console.log("ajaxNetworkError:" + XMLHttpRequest.status + "-"
			+ XMLHttpRequest.readyState + "-" + textStatus);
}

wechat();