//点击输入框以后隐藏底部菜单
setTimeout(function(){
	var height = $(window).height() ;
	$(window).resize(function() {
		if($(window).height()<height){
			$('#footer').hide();
			$(".controlBtu").hide();
		}else if($(window).height()==height){
			$('#footer').show();
			$(".controlBtu").show();
		}
	});
},900);