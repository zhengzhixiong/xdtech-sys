//监听当前网络发生变化
document.addEventListener("netchange", function() {
	type = plus.networkinfo.getCurrentType();
	if (type == 0 || type == 1) {//如果没有网络
		quit();
	} else {
	}
});
//弹出对话框.回到登陆页
function quit() {
	plus.ui.alert("连接服务器失败，即将回到登陆页面!", function() {
		plus.storage.setItem("nuReadMsg", "login");
		//			var winary = plus.ui.enumWindow();
		//			plus.cache.clear();
		//			for (var i = 0; i < winary.length; i++) {
		//				plus.ui.closeWindow(winary[i]);
		//			}
		plus.ui.enumWindow()[0].evalJS("closewait()");
		var gtologin = null;
		var winary = plus.ui.enumWindow();
		gtologin = plus.ui.createWindow("login.html", {
			name : "login.html",
			scrollIndicator : "none",
			scalable : false,
		});
		gtologin.addEventListener('loaded', function() {
			gtologin.show("fade-in", 300);
			setTimeout(function() {
				plus.cache.clear();
				
				for (var i = 0; i < winary.length; i++) {
					plus.ui.closeWindow(winary[i]);
				}
			}, false);
		}, 300);

	}, "无法连接网络", "确定");
}

