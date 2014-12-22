(function() {
    var lastTime = 0;
    var vendors = ['ms', 'moz', 'webkit', 'o'];
    for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x) {
        window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
        window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame']
            || window[vendors[x]+'CancelRequestAnimationFrame'];
    }
    if (!window.requestAnimationFrame)
        window.requestAnimationFrame = function(callback, element) {
            var currTime = new Date().getTime();
            var timeToCall = Math.max(0, 16 - (currTime - lastTime));
            var id = window.setTimeout(function() { callback(currTime + timeToCall); },
                timeToCall);
            lastTime = currTime + timeToCall;
            return id;
        };

    if (!window.cancelAnimationFrame)
        window.cancelAnimationFrame = function(id) {
            clearTimeout(id);
        };
}());

$(function() {
	var click = ('ontouchstart' in window) ? "touchstart" : "click";
	
	if($("#particles").size()>0){
		(function() {
		    var width, height, largeHeader, canvas, ctx, circles, target, animateHeader = true;
			width = window.innerWidth;
			height = window.innerHeight;
			height=200;
			target = {x: 0, y: height};
			largeHeader = document.getElementById('banner');
			largeHeader.style.height = height+'px';
			canvas = document.getElementById('particles');
			canvas.width = width;
			canvas.height = height;
			ctx = canvas.getContext('2d');

			// create particles
			circles = [];
			for(var x = 0; x < width*0.5; x++) {
				var c = new Circle();
				circles.push(c);
			}
			animate();
		 
		    window.addEventListener('scroll',function(){
		    	if(document.body.scrollTop > height) animateHeader = false;
		   		else animateHeader = true;
		    });
		    window.addEventListener('resize',function(){
 				width = window.innerWidth;
        		canvas.width = width;
		    });
		    function animate() {
		        if(animateHeader) {
		            ctx.clearRect(0,0,width,height);
		            for(var i in circles) {
		                circles[i].draw();
		            }
		        }
		        requestAnimationFrame(animate);
		    }
		    // Canvas manipulation
		    function Circle() {
		        var _this = this;

		        // constructor
		        (function() {
		            _this.pos = {};
		            init();
		        })();

		        function init() {
		            _this.pos.x = Math.random()*width;
		            _this.pos.y = height+Math.random()*100;
		            _this.alpha = 0.1+Math.random()*0.3;
		            _this.scale = 0.1+Math.random()*0.3;
		            _this.velocity = Math.random();
		        }

		        this.draw = function() {
		            if(_this.alpha <= 0) {
		                init();
		            }
		 
		            _this.pos.x -=0.1;
		            _this.pos.y -= _this.velocity;
		            _this.alpha -= 0.0005;
		            ctx.beginPath();
		            ctx.arc(_this.pos.x, _this.pos.y, _this.scale*10, 0, 2 * Math.PI, false);
		            ctx.fillStyle = 'rgba(255,255,255,'+ _this.alpha+')';
		            ctx.fill();
		        };
		    }
		})();

	}
	// bootstrap tip
	$(".tip").tooltip();
	//侧栏随动
	$(window).scroll(function() {
		var st = $(this).scrollTop();
		if($('#left_px').size()>0){
			if (st > $('#left_px').offset().top) {

				$('#left_pin').addClass("px").css({
					width: 'inherit',
					top: '60px'
				});
			} else {
				$('#left_pin').removeClass("px").removeAttr("style");
			}
		}
		if($('#right_px').size()>0){
			if (st > $('#right_px').offset().top) {

				$('#right_pin').addClass("px").css({
					width: 'inherit !important',
					top: '60px'
				});
			} else {
				$('#right_pin').removeClass("px").removeAttr("style");
			}
		}
		// 显示和隐藏返回顶部按钮
		if(st>200){
			$("#totop").fadeIn();
		}else{
			$("#totop").fadeOut();
		}
	});
	// 返回顶部
	$("#totop").on("click",function(){
		$("html,body").animate({
			scrollTop:0
		});
	});
	//选中当前栏目
	$("#nav-" + $("body").data("nav")).addClass("active");

	
	//搜索
	var search = $("#search");
	var searchSubmit = function() {
		var keyword = $.trim(search.find("input").val());
		if (keyword == '') {
			// show_tip('关键字不能为空', 3, 300);
			search.tooltip('show')
			setTimeout(function(){
				search.tooltip('destroy');
			},3000);
			search.find("input").focus();
			return false;
		}
		keyword = keyword.replace(/\'/gi, "");
		keyword = keyword.replace(/\"/gi, "");
		keyword = keyword.replace(/\?/gi, "");
		keyword = keyword.replace(/\%/gi, "");
		keyword = keyword.replace(/\./gi, "");
		keyword = keyword.replace(/\*/gi, "");
		location.href = PATH + "search/" + encodeURI(keyword) + "/";
	}
	search.on("click", "i", function(e) {
		e.preventDefault();
		searchSubmit();
	});
	search.find("input").on("keydown", function(e) {
		if (e.keyCode == 13) {
			searchSubmit();
		}

	});
	//点赞
	$("#like").on("click", function(e) {
		e.preventDefault();
		var that = $(this);
		var count = parseInt(that.find("span").html());
		var id = that.data("id");
		if ($.cookie("post-like-" + id) == null) {
			$.post(PATH + "api.php?action=post&do=like", {
				id: id
			}, function(status) {
				if (status == "ERROR:0") {
					show_tip('您还没有登录', 3, 300);
					return false;
				}
				if (status == "ERROR:1") {
					show_tip('您已经点过赞了', 3, 300);
					return false;
				}
				that.find("span").html(++count).show();
				$.cookie("post-like-" + id, true);
				show_tip('点赞成功', 3, 300);
			});
		} else {
			show_tip('您已经点过赞了', 3, 300);
		}
	});
	//会员
//	$.get(PATH + "user/info/", function(data) {
//		$("#user").html(data);
//	});
	//评论统计字数
	$.fn.inputCount = function(wrap, max) {
		$(this).on('keyup change', function() {
			var count = $(this).val().length;
			if (count <= max) {
				$('#' + wrap).html('\u8FD8\u80FD\u8F93\u5165 ' + (max - count) + ' \u4E2A\u5B57');
			} else {
				$('#' + wrap).html('\u5DF2\u7ECF\u8D85\u51FA ' + (count - max) + ' \u4E2A\u5B57');
			};
			return this;
		});
	};
	$("textarea").inputCount('comment-tip', $(".post-comment").data("comment-count"));
	//发表评论
	$("#comment-button").on("click", function(e) {
		e.preventDefault();
		var that = $(this);
		var val=that.html();
		var post_id = that.data("post-id");
		var category_id = that.data("category-id");
		var uniqid = that.data("uniqid");
		var parent_id = that.data("parent-id");
		var interval_time = that.data("interval-timeout");
		var type = that.data("type");
		var content = $.trim($("#comment-content textarea").val());
		if (content == '') {
			show_tip('评论内容不能为空', 3, 300);
			$("#comment-content textarea").focus();
			return false;
		}
		content = encodeURI(content);
		if (type == 'say') {
			var url = PATH + "api.php?action=user&do=say";
			var data = {
				uniqid: uniqid,
				say_content: content
			};
		} else {
			var url = PATH + "api.php?action=post&do=comment";
			var data = {
				uniqid: uniqid,
				comment_content: content,
				post_id: post_id,
				category_id: category_id,
				parent_id: parent_id
			};
		}
		that.html("Waiting...");
		$.post(url, data, function(json) {
			that.html(val);
			if (json.result.error == 0) {
				show_tip('会话已超时，请重新登陆', 3, 300);
				return false;
			}
			if (json.result.error == 1) {
				show_tip('您的帐号未激活！', 3, 300);
				return false;
			}
			if (json.result.error == 2) {
				show_tip('发布间隔必须大于' + interval_time + '分钟', 3, 300);
				return false;
			}
			if (json.success) {
				var tip_text="请等待管理员审核您的评论";
					if(that.data("check")=="0"){
						tip_text="您的评论已发布成功";
					}
				show_tip(tip_text, 3, 300);
				$("#comment-content textarea").val("");
			}
		});
	});
	//评论回复
	$(document).on("click", ".reply", function() {
		var that = $(this);
		var id = that.data("id");
		$("#comment-button").attr("data-parent-id", id);
		$("#comment-content textarea").focus();
	});
	//读取评论
	if ($(".post-comment").data("post-id") || $(".post-comment").data("user-id")) {
		var url;
		if ($(".post-comment").data("user-id") != undefined) {
			url = PATH + "api.php?action=user&do=comment-data";
		} else {
			var post_id = $(".post-comment").data("post-id");
			url = PATH + "api.php?action=post&do=comment&post_id=" + post_id;
		}
		$.getJSON(url, function(json) {
			if (json.success) {
				for (var i in json.result.data) {
					var html = $("#comment-template").html();
					html = html.replace("~postid~", json.result.data[i].post_id);
					html = html.replace("~id~", json.result.data[i].id);
					html = html.replace("~name~", json.result.data[i].user_nickname);
					html = html.replace("~ip~", json.result.data[i].ip);
					html = html.replace("~time~", json.result.data[i].time);
					html = html.replace("~content~", json.result.data[i].content);	
					if (json.result.data[i].reply) {
						html = html.replace("~admin~", "<div class='reply'>管理员：" + json.result.data[i].reply + "</div>");
					} else {
						html = html.replace("~admin~", json.result.data[i].reply);
					}
					if (json.result.data[i].replys) {
						var reply = '<div class="comment-reply">';
						for (var r in json.result.data[i].replys) {
							var html2 = $("#comment-reply-template").html();
							html2 = html2.replace("~postid~", json.result.data[i].replys[r].post_id);
							html2 = html2.replace("~id~", json.result.data[i].replys[r].id);
							html2 = html2.replace("~name~", json.result.data[i].replys[r].user_nickname);
							html2 = html2.replace("~ip~", json.result.data[i].replys[r].ip);
							html2 = html2.replace("~time~", json.result.data[i].replys[r].time);
							html2 = html2.replace("~content~", json.result.data[i].replys[r].content);
							
					if (json.result.data[i].reply) {
						html2 = html2.replace("~admin~", "<div class='reply'>管理员：" + json.result.data[i].replys[r].reply + "</div>");
					} else {
						html2 = html2.replace("~admin~", json.result.data[i].reply);
					}
							reply = reply + html2;
						}
						reply = reply + '</div>';
						html = html.replace("~reply~", reply);
					} else {
						html = html.replace("~reply~", '');
					}

					$("#comment-loop").append(html);
				}
			}
		});
		//自动加载
		var page = 1,
			totalheight = 0,
			isDone = false,
			isRequest = false,
			loadData = function() {
				++page;
				if ($(".post-comment").data("user-id") != undefined) {
					url = PATH + "api.php?action=user&do=comment-data&page=" + page;
				} else {
					var post_id = $(".post-comment").data("post-id");
					var url = PATH + "api.php?action=post&do=comment&post_id=" + post_id + "&page=" + page;
				}
				$.getJSON(url, function(data) {
					if (data.success) {
						page_count = data.result.page_count;
						var push = '';
						for (var i in data.result.data) {
							push += template(data.result.data[i]);
						}
						$("#comment-loop").append(push);
						if (data.result.end == true) {
							$("#more").hide();
							isDone = true;
						}
					} else {
						isDone = true;
					}
					isRequest = false;
				});
			},
			template = function(json) {
				console.log(json);
				var html = $("#comment-template").html();
				html = html.replace("~postid~", json.post_id);
				html = html.replace("~id~", json.id);
				html = html.replace("~name~", json.user_nickname);
				html = html.replace("~ip~", json.ip);
				html = html.replace("~time~", json.time);
				html = html.replace("~content~", json.content);
				if (json.reply) {
					var reply = '<div class="comment-reply">';
					for (var r in json.reply) {
						var html2 = $("#comment-reply-template").html();
						html2 = html2.replace("~postid~", json.reply[r].post_id);
						html2 = html2.replace("~id~", json.reply[r].id);
						html2 = html2.replace("~name~", json.reply[r].user_nickname);
						html2 = html2.replace("~ip~", json.reply[r].ip);
						html2 = html2.replace("~time~", json.reply[r].time);
						html2 = html2.replace("~content~", json.reply[r].content);
						reply = reply + html2;
					}
					reply = reply + '</div>';
					html = html.replace("~reply~", reply);
				} else {
					html = html.replace("~reply~", '');
				}
				return html;
			},
			page_count = 1;
		$(window).scroll(function() {
			if (isRequest) return false;
			if (isDone) return false;
			if (page > 3) return false;
			isRequest = true;
			totalheight = parseFloat($(this).height()) + parseFloat($(this).scrollTop());
			if ($(document).height()-totalheight<=100) {
				loadData();
				if (page == 3) $("#more").show();
			}
		});
		$("#more").on("click", function(e) {
			e.preventDefault();
			if (isDone) return false;
			loadData();
		});
	}

	//读取留言
	if ($(".post-comment").data("type") == "say") {
		var url = PATH + "api.php?action=user&do=say-data";
		$.getJSON(url, function(json) {
			if (json.success) {
				for (var i in json.result.data) {
					var html = $("#comment-template").html();
					html = html.replace("~postid~", json.result.data[i].post_id);
					html = html.replace("~id~", json.result.data[i].id);
					html = html.replace("~name~", json.result.data[i].user_nickname);
					html = html.replace("~ip~", json.result.data[i].ip);
					html = html.replace("~time~", json.result.data[i].time);
					html = html.replace("~content~", json.result.data[i].content);
					if (json.result.data[i].reply) {
						html = html.replace("~reply~", "<div class='reply'>管理员：" + json.result.data[i].reply + "</div>");
					} else {
						html = html.replace("~reply~", json.result.data[i].reply);
					}
					$("#comment-loop").append(html);
				}
			}
		});
		//自动加载
		var page = 1,
			totalheight = 0,
			isDone = false,
			isRequest = false,
			loadData = function() {
				++page;

				url = PATH + "api.php?action=user&do=say-data&page=" + page;

				$.getJSON(url, function(data) {
					if (data.success) {
						page_count = data.result.page_count;
						var push = '';
						for (var i in data.result.data) {
							push += template(data.result.data[i]);
						}
						$("#comment-loop").append(push);
						if (data.result.end == true) {
							$("#more").hide();
							isDone = true;
						}
					} else {
						isDone = true;
					}
					isRequest = false;
				});
			},
			template = function(json) {
				var html = $("#comment-template").html();
				html = html.replace("~postid~", json.post_id);
				html = html.replace("~id~", json.id);
				html = html.replace("~name~", json.user_nickname);
				html = html.replace("~ip~", json.ip);
				html = html.replace("~time~", json.time);
				html = html.replace("~content~", json.content);
				html = html.replace("~reply~", json.reply);
				if (json.reply) {
					html = html.replace("~reply~", "<div class='reply'>管理员：" + json.reply + "</div>");
				} else {
					html = html.replace("~reply~", json.reply);
				}
				return html;
			},
			page_count = 1;
		$(window).scroll(function() {
			if (isRequest) return false;
			if (isDone) return false;
			if (page > 3) return false;
			isRequest = true;
			totalheight = parseFloat($(this).height()) + parseFloat($(this).scrollTop());
			if ($(document).height() <= totalheight) {
				loadData();
				if (page == 3) $("#more").show();
			}
		});
		$("#more").on("click", function(e) {
			e.preventDefault();
			if (isDone) return false;
			loadData();
		});
	}
});

function show_tip(html, time, width) {
	if ($("#tip").size() == 1) return false;
	var x = ($(document).width() - width) / 2;
	var y = $(window).height() / 2 + $(document).scrollTop();
	var d = $('<div />');
	d.attr('id', 'tip');
	d.css({
		left: x + 'px',
		top: y + 'px',
		width: width + 'px'
	});
	d.show().animate({
		top: (y + 20) + 'px'
	});
	$('body').append(d);

	d.html(html);

	if (time > 0) {
		setTimeout(function() {
			d.animate({
				top: (y - 20) + 'px'
			}).fadeOut('slow');

			setTimeout(function() {
				d.empty().remove();
			}, time * 1000);
		}, time * 1000);
	}
}