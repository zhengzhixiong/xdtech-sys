//初始化登录次数
function initLoginCount() {
	var c = plus.storage.getItem("logcount");
	var t = plus.storage.getItem("logtime");
	if (!c) {
		plus.storage.setItem("logcount", "5");
	}
	if (!t) {
		plus.storage.setItem("logtime", "0");
	}
}

function addLoginCount() {
	//取出数据加1，再存回去
	var count = parseInt(plus.storage.getItem("logcount"));
	count--;
	plus.storage.setItem("logcount", count + "");
	count = parseInt(plus.storage.getItem("logcount"));
	var start_time = new Date().getTime();
	var end_time = start_time + (5 * 60 * 1000);
	if (count == 0) {
		plus.storage.setItem("logtime", end_time + "");
		$('#loginErr').skygqbox();
		$("#loginErrTip").html('输入错误次数过多，请5分钟后再试');
		addliste();
	} else {
		$('#loginErr').skygqbox();
		$("#loginErrTip").text('账号或密码错误，您还可以尝试' + count + "次");
		addliste();
	}
}

//登录失败次数限制
function checkLoginCount() {
	var c = plus.storage.getItem("logcount");
	if (c && c <= 0) {
		var start_time = new Date().getTime();
		var t = parseInt(plus.storage.getItem("logtime"));
		if (start_time >= t) {
			plus.storage.setItem("logcount", "5");
			plus.storage.setItem("logtime", "0");
			return true;
		}
		$('#loginErr').skygqbox();
		$("#loginErrTip").html('输入错误次数过多，账号已锁定稍后再试');
		addliste();
		return false;
	} else {
		return true;
	}

}

//检查用户名及密码是否为空
function checkAccount() {
	if ($("#name").val().length < 1 || $("#password").val().length < 1) {
		$('#NOnull').skygqbox();
		addliste();
		return false;
	}
	return true;
}

//如果用户名及密码合法异步登录
function login() {
	if (checkAccount() && checkLoginCount()) {
		var name = $("#name").val().trim();
		var password = $("#password").val().trim();
		if (window.plus) {
			plus.ui.enumWindow()[0].evalJS("openwait()");
		}
		Request('APP_Login', name, password, '', function(data) {
			plus.storage.setItem("logtime", "0");

			if (window.plus) {
				plus.ui.enumWindow()[0].evalJS("closewait()")
			}
			if (data.Code == '0') {
				plus.storage.setItem("logcount", "5");
				d = data.Data;
				if (window.plus) {
					plus.storage.setItem("clientAddr",d.Company);//
					plus.storage.setItem("user", name);
					plus.storage.setItem("password", password);
					if($("#savePwd").attr('checked')){
						plus.storage.setItem("savePwd", "yes");
					}else{
						plus.storage.setItem("savePwd", "no");
					}
					
					//					plus.storage.setItem("address",d.Address);
					//					plus.storage.setItem("contact",d.Contact);
					//					plus.storage.setItem("phone",d.Phone);
				}
				plus.ui.enumWindow()[0].evalJS("closewait()");
				clicked("index.html", "zoom-out");
			} else {
				//				alert("login");
				addLoginCount();
				//登录失败，记录次数
			}
		}, function() {
			if (window.plus) {
				plus.ui.enumWindow()[0].evalJS("closewait()");
			}
			if (window.plus)
				plus.ui.alert("连接服务器超时", function() {}, "请求失败", "确定");
		});
	}
}