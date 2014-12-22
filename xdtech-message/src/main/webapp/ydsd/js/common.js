//取消浏览器的所有事件，使得active的样式在手机上正常生效
document.addEventListener("touchstart", function(e) {
	return false;
}, false);
// 屏蔽选择函数
function shield() {
	return false;
}

document.oncontextmenu = shield;
// H5 plus事件处理
document.addEventListener("plusready", function() {
	document.body.onselectstart = shield;
	//	mwin.evalJS("loadedliste()");
	// Android处理返回键
	if ("Android" == plus.os.name) {
		setTimeout(function() {//300MS后绑字安卓返回键
			plus.ui.getSelfWindow().addEventListener("back", function() {
				back();
			}, false);
		}, 300);
	}
	// iOS平台使用滚动的div
	if ("iOS" == plus.os.name) {
		var t = document.getElementById("dcontent");
		if (t) {
			t.className = "sdcontent";
		}
		t = document.getElementById("content");
		if (t) {
			t.className = "scontent";
		}
	}
}, false);
// 兼容非H5 plus终端
if (navigator.userAgent.indexOf("Html5Plus") < 0) {
	document.addEventListener("DOMContentLoaded", function() {
		document.body.onselectstart = shield;
	}, false);
}
//安卓退出应用 
function toLogout() {
	if ("Android" == plus.os.name) {
		var winary = plus.ui.enumWindow();
		if (confirm("确认退出？")) {
			plus.storage.setItem("nuReadMsg", "login");
//			plus.cache.clear();
//			for (var i = 0; i < winary.length; i++) {
//				plus.ui.closeWindow(winary[i]);
//			}
			plus.runtime.quit();
		}
	}
}

function toMePage() {
	clicked('me.html');
}

var backflag = false;
// 处理返回事件
function back() {
	setTimeout(function() {
		var selfwin = plus.ui.getSelfWindow();
		if (selfwin.getUrl().indexOf("index.html") > -1 || selfwin.getUrl().indexOf("login.html") > -1) {//如果是首页或者登录页
			toLogout();
		} else {//其它页面.返回返回到上一个页面
			if (backflag)
				return;
				backflag = true;
			var winary = plus.ui.enumWindow();
			var mwin = winary[winary.length - 2];
			var mwinurl = mwin.getUrl();
			if (mwinurl.indexOf("cart.html") > -1) {//如果是购物车页面
				clicked('cart.html');//跳转到购物车页面
				mwin.close();//关闭当前窗口
				return;
			} else if (mwinurl.indexOf("index.html") > -1) {//如果是首页
				var nuReadMsg = plus.storage.getItem("nuReadMsg");//取出系统消息的状态
				if (nuReadMsg == "false") {
					mwin.evalJS("$('.message span').remove();");//关闭系统消息上的小红点
				}
			}
			mwin.setVisible(true);//显示上一页
			if (mwinurl.indexOf("index.html") > -1 || mwinurl.indexOf("service.html") > -1 || mwinurl.indexOf("cart.html") > -1 || mwinurl.indexOf("me.html") > -1) {
				//返回更新购物车数量
				RequestData('APP_Cart', '', 0, 0, function(data) {
					var length = data.Data.ProductList.length;
					if (length > 0)
						mwin.evalJS('$(".cart div").append("<span>' + length + '</span>")');
					else
						mwin.evalJS('$(".cart div span").hide()');
				}, function() {
				});
			}
			backflag = false;
			selfwin.close();//关闭当前页

		}
	}, 300);
}

var url = null;//跳转地址
var fla = null;
var floatWin = null;//跳转地址的页面
function loadedliste(anish) {//页面加载完成后调用的方法
	fla = false;

	setTimeout(function() {

		floatWin.show(anish, 300);//用渐变300MS的方式显示页面
		var winary = plus.ui.enumWindow();//玫举所有页面
		if (url == "index.html" || winary[0].getUrl().indexOf("login.html") > -1) {
			winary[0].evalJS("closewait()");//关闭等待框
		}else if(url == "copyright.html"){
			floatWin.reload();
		}
		setTimeout(function() {
			plus.ui.getSelfWindow().setVisible(false);//隐藏当前页
			if (url == "index.html") {
				plus.cache.clear();//清除缓存
				//循环关闭页面
				for (var i = 0; i < winary.length - 1; i++) {
					plus.ui.closeWindow(winary[i]);
				}
				//				var waitwincom = floatWin.evalJS("getwait()");
				//				if (waitwincom != null) {
				//					floatWin.evalJS("openwait()");
				//				}
				floatWin = null;
				
			} else if (url == "service.html" || url == "me.html" || url == "cart.html") {
				$('.service').removeClass('on');
				$('.cart').removeClass('on');
				$('.mine').removeClass('on');
				if (winary.length > 2) {
					for (var i = 1; i < winary.length - 1; i++) {
						plus.ui.closeWindow(winary[i]);
					}
				}

			}
			//页面数大于5个.清掉一个
			if (winary.length >= 5) {
				if (winary[0].getUrl().indexOf("index.html") > -1) {
					plus.ui.closeWindow(winary[1]);
				} else {
					plus.ui.closeWindow(winary[0]);
				}
			}
		}, 300);
	}, 300);
}

//跳转页面点击
function clicked(id, anis) {
	anis = "fade-in";
	url = encodeURI(id);
	if (url == "service.html") {
		$('.service').addClass('on');
	} else if (url == "me.html") {
		$('.mine').addClass('on');
	} else if (url == "cart.html") {
		$('.cart').addClass('on');
	}
	if (window.plus) {
		plus.ui.enumWindow()[0].evalJS("openwait()");//打开等待框
		fla = true;

		var winary = plus.ui.enumWindow();
		floatWin = plus.ui.createWindow(url, {//设置打开窗口的值
			name : url,//名字
			scrollIndicator : "none",//隐藏滚动条
			scalable : false,//不能放大
			//			transition : {
			//				property : "all",
			//				duration : 400,
			//				timingfunction : "linear"
			//			}
		});

		floatWin.addEventListener('loaded', loadedliste(anis), false);//监听加载完毕执行的方法
		//				wait.onclose = function() {
		//					if (fla) {
		//						floatWin.removeEventListener('loaded', loadedliste);
		//						floatWin.close();
		//						floatWin = null;
		//					}
		//				}
	} else {

		window.location.href = id;
		//window.open(id);
	}
}

// 格式化时长字符串，格式为"HH:MM:SS"
function timeToStr(ts) {
	if (isNaN(ts)) {
		return "--:--:--";
	}
	var h = parseInt(ts / 3600);
	var m = parseInt((ts % 3600) / 60);
	var s = parseInt(ts % 60);
	return (ultZeroize(h) + ":" + ultZeroize(m) + ":" + ultZeroize(s));
}

// 格式化日期时间字符串，格式为"YYYY-MM-DD HH:MM:SS"
function dateToStr(d) {
	return (d.getFullYear() + "-" + ultZeroize(d.getMonth() + 1) + "-" + ultZeroize(d.getDate()) + " " + ultZeroize(d.getHours()) + ":" + ultZeroize(d.getMinutes()) + ":" + ultZeroize(d.getSeconds()));
}

/**
 * zeroize value with length(default is 2).
 * @param {Object} v
 * @param {Number} l
 * @return {String}
 */
function ultZeroize(v, l) {
	var z = "";
	l = l || 2;
	v = String(v);
	for (var i = 0; i < l - v.length; i++) {
		z += "0";
	}
	return z + v;
}
