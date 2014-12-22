var orderWin = null;
function clicked(id, mflag) {

	var url = encodeURI(id);
	if (window.plus) {
		//		var orwinary = plus.ui.enumWindow().slice(0);
		//		for (var i = orwinary.length - 2; i < 0; i--) {
		//			alert(orwinary[i].getUrl());
		//			if (orwinary[i].getUrl().indexOf(url) > -1) {
		//				orwinary[i].setVisible(true);
		//				plus.ui.getSelfWindow().setVisible(false);
		//				flagwi = false;
		//				alert(flagwi);
		//				return;
		//			}
		//		}
		plus.ui.enumWindow()[0].evalJS("openwait()");//打开等待框
		var orwinary = plus.ui.enumWindow();//玫举所有窗口
		orderWin = plus.ui.createWindow(url, {//设置窗口的属性
			name : url,//名字
			scrollIndicator : "none",//隐藏滚动条
			scalable : false//不能放大
		});
		orderWin.show('fade-in', 400);//以400秒的渐变显示页面
		orderWin.addEventListener('loaded', function() {

//			if (url == "index.html") {
//				plus.ui.enumWindow()[0].evalJS("closewait()");
//				for (var i = 0; i < orwinary.length; i++) {
//					plus.ui.closeWindow(orwinary[i]);
//				}
//			}
			//如果保存的窗口大于5个.关掉一个
			if (orwinary.length >= 5) {
				if (orwinary[0].getUrl().indexOf("index.html") > -1) {
					plus.ui.closeWindow(orwinary[1]);
				} else {
					plus.ui.closeWindow(orwinary[0]);
				}
			}
			if (mflag) {//如果是返回键触发的
				var u = orwinary[orwinary.length - 2].getUrl();//得到该返回的页面的链接地址
				if (u&& u.indexOf("index.html") == -1) {
					orwinary[orwinary.length - 2].close();//关闭该页面
				}
				plus.ui.getSelfWindow().close();//关闭自己
			} else {
				plus.ui.getSelfWindow().setVisible(false);//隐藏自己
			}
		}, false);

	} else {
		window.location.href = id;
	}
}
