document.addEventListener("plusready", function() {
	// Android处理返回键
	setTimeout(function() {
		plus.ui.getSelfWindow().addEventListener("back", function() {
			back();
		}, false);
	}, 300);

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
var backoflag = false;
//回退事件
function back() {
	setTimeout(function() {
		if (backoflag)
			return;
		backoflag = true;
		var wins = plus.ui.enumWindow();//玫举所有页面
		var selfwin = plus.ui.getSelfWindow();//得到当前页面
		var mwin = wins[wins.length - 2];//得到需要返回到的页面
		mwin.setVisible(true);//显示需要返回到的页面
		backoflag = false;
		selfwin.close();//关闭当前页面
	}, 300);
}

var floatWin = null//跳转的页面
//跳转页面方法
function openPage(id) {

	if (floatWin)
		return;
	var url = encodeURI(id);
	if (window.plus) {
		plus.ui.enumWindow()[0].evalJS("openwait()");//打开等待框
		//设置打开窗口的值
		floatWin = plus.ui.createWindow(url, {
			name : url,//名字
			scrollIndicator : "none",//隐藏滚动条
			scalable : false,//不能双击放大
		});
		floatWin.show('fade-in', 200);//以200MS的渐变显示页面
		floatWin.addEventListener('loaded', function() {//监听页面加载完成
			setTimeout(function() {
				floatWin = null;
				var orwinary = plus.ui.enumWindow();//玫举所有页面
				//保存的页面大于5个.关掉一个
				if (orwinary.length >= 5) {
					if (orwinary[0].getUrl().indexOf("index.html") > -1) {
						plus.ui.closeWindow(orwinary[1]);
					} else {
						plus.ui.closeWindow(orwinary[0]);
					}
				}
				plus.ui.getSelfWindow().close();//关掉当前页面
			}, 100);
		}, false);
	} else {
		window.location.href = id;
	}
}
