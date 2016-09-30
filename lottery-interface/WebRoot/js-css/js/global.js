function copyLink() {
	try {
		var cText =  document.location.toString();
		if (window.clipboardData) {
			window.clipboardData.setData("Text", cText);
			alert("复制完成!");
		} else if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("您的浏览器设置为不允许复制！\n如果需要此操作，请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true',再重试复制操作!");
				return false;
			}
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
			if (!clip) 
				return;
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
			if (!trans) {
				return;
			}
			trans.addDataFlavor('text/unicode');
			var str = new Object();
			var len = new Object();
			var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
			
			str.data = cText;
			trans.setTransferData("text/unicode", str, cText.length * 2);
			var clipid = Components.interfaces.nsIClipboard;
			if (!clip) 
				return false;
			clip.setData(trans, null, clipid.kGlobalClipboard);
			alert("复制完成!");
		}
	} catch (e) {
	}
};

function setHome(obj, sURL) {
	try {
		obj.style.behavior = "url(#default#homepage)";
		obj.setHomePage(sURL);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
			} catch (e) {
				alert("抱歉！您的浏览器不支持直接设为首页，此操作被浏览器拒绝！\n请在浏览器地址栏输入：“about:config”，并回车键。\n然后将“[signed.applets.codebase_principal_support]”设置为：“true”。"); 
			}
			var prefs = Components.classes["@mozilla.org/preferences-service;1"].getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref("browser.startup.homepage", sURL);
		} else {
			alert("抱歉！您的浏览器不支持直接设为首页。");
		}
	}
}

function addFavorite(sURL, sTitle) {
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			alert("加入收藏失败，请使用“Ctrl+D”进行添加。");
		}
	}
}

function gotoPage(url) {
	if (url != "" && url != null) {
		window.open(url);
	}
}

function SetFont(size) { 
              var divBody = document.getElementById("news_content"); 
              if(!divBody) 
              { 
                  return; 
              } 
              divBody.style.fontSize = size + "px"; 
              var divChildBody = divBody.childNodes; 
              for(var i = 0; i < divChildBody.length; i++) 
              { 
                  if (divChildBody[i].nodeType==1) 
                  { 
                      divChildBody[i].style.fontSize = size + "px"; 
                  } 
              } 
};

function popMenu1(obj) {
	if (document.getElementById("_"  + obj.id) == null || document.getElementById("_" + obj.id) == "undefined") {
		return; 
	}	 

	if (document.getElementById("_" + obj.id).style.display != "") {
		document.getElementById("_bch").style.display = "none";
		document.getElementById("_xqh").style.display = "none";
		document.getElementById("_" + obj.id).style.display = "";
	} else {
		document.getElementById("_" + obj.id).style.display = "none";
	}
}

function popMenu2(obj) {
	if (document.getElementById("_" + obj.id + "h") == null || document.getElementById("_" + obj.id + "h") == "undefined") {
		return; 
	}
	document.getElementById("_" + obj.id + "h").style.display = "none";
}

function changeCode(type, para) {
	var path;
	if (type == "1") {
		path = "http://app.zhcw.com/wwwroot/zhcw/jsp/tagSearch.jsp?site=214&sscsName=ss&query=" + encodeURIComponent(para);
	} else if (type == "2") {
		path = "http://app.zhcw.com/wwwroot/zhcw/jsp/tagSearch.jsp?site=214&sscsName=cs&query=" + encodeURIComponent(para);
	} else {
		path = "http://app.zhcw.com/wwwroot/zhcw/jsp/indexSearch.jsp?site=214&query=" + encodeURIComponent(para);
	}
	window.open(path); 
};
