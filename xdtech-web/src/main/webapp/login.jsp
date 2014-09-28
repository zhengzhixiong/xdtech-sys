<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/commons/mytags.jsp"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="plugins/xdtech/images/favicon.png">
<title>学达科技</title>
<script type="text/javascript" src="plugins/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var role = "manager";
	$(document).ready(function() {
		// 点击登录
		$('#but_login').click(function(e) {
			if (!role) {
				$('#alertMessage').addClass('error').html("请选择登录身份").stop(true, true).show().animate({
					opacity : 1,
					right : '0'
				}, 500);
				return;
			}
			if($('#loginName').val()=='') {
				$('#alertMessage').addClass('error').html('用户名不能为空').stop(true, true).show().animate({
					opacity : 1,
					right : '0'
				}, 500);
				return;
			}else if($('#password').val()=='') {
				$('#alertMessage').addClass('error').html('密码不能为空').stop(true, true).show().animate({
					opacity : 1,
					right : '0'
				}, 500);
				return;
			}
			
			loading('验证中..', 1);
			setTimeout("unloading()", 1000);
			setTimeout("login()", 1000);
		});

		//点击消息关闭提示
		$('#alertMessage').click(function() {
			hideTop();
		});
		
		//角色选取控制
		 $.icheck = {
	                init: function() {
	                    var _this = this;
	                    _this._checkbox = "checkbox";
	                    _this._radio = "radio";
	                    _this._disabled = "disabled";
	                    _this._enable = "enable";
	                    _this._checked = "checked";
	                    _this._hover = "hover";
	                    _this._arrtype = [_this._checkbox, _this._radio];
	                    _this._mobile = /ipad|iphone|ipod|android|blackberry|windows phone|opera mini|silk/i.test(navigator.userAgent);
	                    $.each(_this._arrtype, function(k, v) {
	                        _this.click(v);
	                        if(!_this._mobile){
	                            _this.mouseover(v);
	                            _this.mouseout(v);
	                        }
	                    });
	                },
	                click: function(elem) {
	                    var _this = this;
	                   
	                    elem = "." + elem;
	                    $(document).on("click", elem, function() {
	                        var $this = $(this),
	                            _ins = $this.find("ins");
	                        
	                        if (!(_ins.hasClass(_this._disabled) || _ins.hasClass(_this._enable))) {
	                            if ( !! _ins.hasClass(_this._checked)) {
	                                _ins.removeClass(_this._checked).addClass(_this._hover);
	                                role = undefined;
	                            } else {
	                                if (/radio/ig.test(elem)) {
	                                    var _name = $this.attr("name");
	                                    $(elem + "[name=" + _name + "]").find("ins").removeClass(_this._checked);
	                                }
	                                $(elem).find("ins").removeClass(_this._hover);
	                                _ins.addClass(_this._checked);
	                                role = _ins.attr("value");
// 	                                 alert(_ins.attr("value"));
									if(role=='visitor') {
										$('#loginName').val('visitor');
										$('#password').val('123456');
									}else {
										$('#loginName').val('');
										$('#password').val('');
									}
	                            }
	                        }
	                    });
	                },
	                mouseover: function(elem) {
	                    var _this = this;
	                    elem = "." + elem;
	                    $(document).on("mouseover", elem, function() {
	                        var $this = $(this);
	                        var _ins = $this.find("ins");
	                        if (!(_ins.hasClass(_this._disabled) || _ins.hasClass(_this._enable) || _ins.hasClass(_this._checked))) {
	                            _ins.addClass(_this._hover);
	                            $this.css("cursor","pointer");
	                        } else{
	                            $this.css("cursor","default");
	                        }
	                    });
	                },
	                mouseout: function(elem) {
	                    var _this = this;
	                    elem = "." + elem;
	                    $(document).on("mouseout", elem, function() {
	                        $(elem).find("ins").removeClass(_this._hover);
	                    });
	                }
	            };

	            $.icheck.init();
	});

	//登录处理函数
	function login() {
		var actionurl = $('form').attr('action');//提交路径
		var formData = new Object();
		$("#formLogin :input").each(function() {
			formData[this.name] = $("#" + this.name).val();
		});
		//角色身份
		formData["role"] = role;
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			url : actionurl,// 请求的action路径
			data : formData,
			error : function() {// 请求失败处理函数
				
			},
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					setTimeout("window.location.href='main.do?main'", 100);
				} else {
					$('#alertMessage').addClass('error').html(d.msg).stop(true, true).show().animate({
						opacity : 1,
						right : '0'
					}, 500);
				}
				
			}
		});
	}
	
	//加载信息
	function loading(name, overlay) {
		$('body').append('<div id="overlay"></div><div id="preloader">' + name + '..</div>');
		if (overlay == 1) {
			$('#overlay').css('opacity', 0.1).fadeIn(function() {
				$('#preloader').fadeIn();
			});
			return false;
		}
		$('#preloader').fadeIn();
	}

	function unloading() {
		$('#preloader').fadeOut('fast', function() {
			$('#overlay').fadeOut();
		});
	}
	
	function hideTop() {
		$('#alertMessage').animate({
			opacity : 0,
			right : '-20'
		}, 500, function() {
			$(this).hide();
		});
	}
</script>

<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="plugins/bootstrap/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="plugins/xdtech/css/xdtech-login.css" />
<link href="plugins/xdtech/css/font-awesome/css/font-awesome.css" rel="stylesheet" />

</head>
<body>
	<div class="top_bg"></div>
	<div class="login_center">
		<div id="loginbox">
			<form id="formLogin" class="form-vertical" action="authLogin.action?login">
				<div class="control-group normal_text">
					<h3>
						<img alt="Logo" src="plugins/xdtech/images/logo.png" />
					</h3>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_lg"><i class="icon-user"></i></span><input
								type="text" placeholder="Username"  name="loginName"  title="用户名" id="loginName" value="root" />
						</div>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="main_input_box">
							<span class="add-on bg_ly"><i class="icon-lock"></i></span><input
								type="password" placeholder="Password"  title="密码" id="password" name="password" value="123456"/>
						</div>
					</div>
				</div>
				<div class="role">
			        <div class="radio" name="role" ><ins class="checked" value="manager" ></ins><span>管理员</span></div>
			        <div class="radio" name="role" ><ins value="teacher"></ins><span>教师</span></div>
			        <div class="radio" name="role" ><ins value="student"></ins><span>学生</span></div>
			        <div class="radio" name="role" ><ins value="visitor"></ins><span>访客</span></div>
			    </div> 
				<div class="form-actions">
					<span class="pull-left">
						<a href="javascript:void();"  class="flip-link btn btn-info" id="to-recover">忘记密码?</a>
					</span> 
					<span class="pull-right">
						<a href="javascript:void();" id="but_login" class="btn btn-success">登录</a>
					</span>
				</div>
			</form>
		</div>
	</div>


	<div class="bottom_bg">
		<span>Mr.Z Max All Rights Reserved.
			(推荐使用IE8+,谷歌浏览器、火狐浏览器可以获得更快,更安全的页面响应速度)</span>
	</div>
	<div id="alertMessage"></div>
</body>
</html>