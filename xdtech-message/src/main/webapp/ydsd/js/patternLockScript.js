(function(e, t, n, r) {"use strict";
	function l(e) {
		var t = e.holder, n = e.option, r = n.matrix, i = n.margin, s = n.radius, o = ['<ul class="patt-wrap" style="padding:' + i + 'px">'];
		for (var u = 0, a = r[0] * r[1]; u < a; u++)
			o.push('<li class="patt-circ" style="margin:' + i + "px; width : " + s * 2 + "px; height : " + s * 2 + "px; -webkit-border-radius: " + s + "px; -moz-border-radius: " + s + "px; border-radius: " + s + 'px; "><div class="patt-dots"></div></li>');
		o.push("</ul>"), t.html(o.join("")).css({
			width : r[1] * (s * 2 + i * 2) + i * 2 + "px",
			height : r[0] * (s * 2 + i * 2) + i * 2 + "px"
		}), e.pattCircle = e.holder.find(".patt-circ")
	}

	function c(e, t, n, r) {
		var i = t - e, s = r - n;
		return {
			length : Math.ceil(Math.sqrt(i * i + s * s)),
			angle : Math.round(Math.atan2(s, i) * 180 / Math.PI)
		}
	}

	function v() {
	}

	//判断数值在栈是否存在
	function isExist(topArray, num) {
		for (var i = 0; i < topArray.length; i++) {
			if (topArray[i] == num) {
				return true;
			}
		}
		return false;
	}

	function m(t, n) {
		var r = this, i = r.token = Math.random(), o = f[i] = new v, u = o.holder = e(t);
		if (u.length == 0)
			return;
		o.object = r, n = o.option = e.extend({}, m.defaults, n), l(o), u.addClass("patt-holder"), u.css("position") == "static" && u.css("position", "relative"), u.on(s, function(e) {
			h.call(this, e, r)
		}), o.option.onDraw = n.onDraw || a;
		var c = n.mapper;
		typeof c == "object" ? o.mapperFunc = function(e) {
			return c[e]
		} : typeof c == "function" ? o.mapperFunc = c : o.mapperFunc = a, o.option.mapper = null
	}

	var i = "ontouchstart" in t, s = i ? "touchstart" : "mousedown", o = i ? "touchend" : "mouseup", u = i ? "touchmove" : "mousemove", a = function() {
	}, f = {}, h = function(t, r) {
		t.preventDefault();
		var i = f[r.token];
		i.option.patternVisible || i.holder.addClass("patt-hidden"), e(this).on(u + ".pattern-move", function(e) {
			p.call(this, e, r)
		}), e(n).one(o, function() {
			d.call(this, t, r)
		});
		var s = i.holder.find(".patt-wrap"), a = s.offset();
		i.wrapTop = a.top, i.wrapLeft = a.left, r.reset()
	}, p = function(t, n) {
		t.preventDefault();
		var r = t.pageX || t.originalEvent.touches[0].pageX, i = t.pageY || t.originalEvent.touches[0].pageY, s = f[n.token], o = s.pattCircle, u = s.patternAry, a = s.option.lineOnMove, l = s.getIdxFromPoint(r, i), h = l.idx, p = s.mapperFunc(h) || h;
		if (u.length > 0) {
			var d = c(s.lineX1, l.x, s.lineY1, l.y);
			s.line.css({
				width : d.length + 10 + "px",
				transform : "rotate(" + d.angle + "deg)"
			})
		}

		if (h && u.indexOf(p) == -1) {
			var v = e(o[h - 1]);
			u.push(p);
			//========================================
			var zd = f[n.token], pattern = zd.patternAry.join('');
			var nowPat = u.pop();
			//提取栈最后一个值
			var he = 10;
			var flag = false;
			if ((parseInt(pattern[pattern.length - 1]) + parseInt(pattern[pattern.length - 2])) == 10) {
				he = 4;
				flag = true;
			} else if ((Math.abs(pattern[pattern.length - 1] - pattern[pattern.length - 2]) == 2)) {
				he = (parseInt(pattern[pattern.length - 1]) + parseInt(pattern[pattern.length - 2]) - 2) / 2;
				if ((he + 1) == 2 | (he + 1) == 5 | (he + 1) == 8) {
					flag = true;
				}
			} else if (Math.abs(pattern[pattern.length - 1] - pattern[pattern.length - 2]) == 6) {
				he = (parseInt(pattern[pattern.length - 1]) + parseInt(pattern[pattern.length - 2]) - 2) / 2;
				flag = true;
			}
			if (!isExist(pattern, he + 1) && flag) {
				u.push(he + 1);
				$(o[he]).addClass("hovered");
			}
			u.push(nowPat);
			//将刚提取的值放回栈
			//========================================
			v.addClass("hovered");
			var m = s.option.margin, g = s.option.radius, y = (l.i - 1) * (2 * m + 2 * g) + 2 * m + g, b = (l.j - 1) * (2 * m + 2 * g) + 2 * m + g;
			if (u.length != 1) {
				var w = c(s.lineX1, y, s.lineY1, b);
				s.line.css({
					width : w.length + 10 + "px",
					transform : "rotate(" + w.angle + "deg)"
				}), a || s.line.show()
			}
			var E = e('<div class="patt-lines" style="top:' + (b - 5) + "px; left:" + (y - 5) + 'px"></div>');
			s.line = E, s.lineX1 = y, s.lineY1 = b, s.holder.append(E), a || s.line.hide()
		}
	}, d = function(e, t) {
		e.preventDefault();
		var n = f[t.token], r = n.patternAry.join("");
		n.holder.off(".pattern-move").removeClass("patt-hidden");
		if (!r)
			return;
		
		n.option.onDraw(r), n.line.remove(), n.rightPattern && (r == n.rightPattern ? n.onSuccess() : (n.onError(), t.error()))
		t.reset();
	};
	v.prototype = {
		constructor : v,
		getIdxFromPoint : function(e, t) {
			var n = this.option, r = n.matrix, i = e - this.wrapLeft, s = t - this.wrapTop, o = null, u = n.margin, a = n.radius * 2 + u * 2, f = Math.ceil(i / a), l = Math.ceil(s / a), c = i % a, h = s % a;
			return f <= r[1] && l <= r[0] && c > u * 2 && h > u * 2 && ( o = (l - 1) * r[1] + f), {
				idx : o,
				i : f,
				j : l,
				x : i,
				y : s
			}
		}
	}, m.prototype = {
		constructor : m,
		option : function(e, t) {
			var n = f[this.token], r = n.option;
			if (!t)
				return r[e];
			r[e] = t, (e == "margin" || e == "matrix" || e == "radius") && l(n)
		},
		getPattern : function() {
			return f[this.token].patternAry.join("")
		},
		reset : function() {
			var e = f[this.token];
			e.pattCircle.removeClass("hovered"), e.holder.find(".patt-lines").remove(), e.patternAry = [], e.holder.removeClass("patt-error")
		},
		error : function() {
			f[this.token].holder.addClass("patt-error")
		},
		checkForPattern : function(e, t, n) {
			var r = f[this.token];
			r.rightPattern = e, r.onSuccess = t || a, r.onError = n || a
		}
	}, m.defaults = {
		matrix : [3, 3],
		margin : 20,
		radius : 25,
		patternVisible : !0,
		lineOnMove : !0
	}, t.PatternLock = m
})(jQuery, window, document);
//(function() {
//	function r() {
//		$.ajax({
//			url : "http://patterncaptcha.herokuapp.com/api/getPattern",
//			type : "get",
//			dataType : "json",
//			crossDomain : !0,
//			success : function(r) {
//				e = r.id;
//				var i = r.matrix, s = r.imageData;
//				n ? n.option("matrix", i) : n = new PatternLock("#patternUI", {
//					matrix : i,
//					radius : 20,
//					margin : 15
//				}), t.html('<img src="' + s + '" id="patternImage" />')
//			}
//		})
//	}

//	var e, t = $("#patternCaptcha"), n;
//	r(), $("#refreshCaptcha").click(function() {
//		r()
//	}), $("#submitCaptcha").click(function() {
//		$.ajax({
//			url : "http://patterncaptcha.herokuapp.com/api/checkPattern",
//			type : "get",
//			dataType : "json",
//			data : {
//				patternId : e,
//				pattern : n.getPattern()
//			},
//			crossDomain : !0,
//			success : function(e) {
//				plus.ui.alert(e.Message, function() {}, "请求失败", "确定");, r()
//			}
//		})
//	})
//})();
