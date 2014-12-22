function tips(obj){
	objName = obj;//获取传进来的对象名字，然后显示出来，再自动隐藏
    $('.tipsBg').show(50).delay(1000).hide(20);
    $(objName).show(50).delay(1000).hide(20);
}
//使传进来的对象，显示出来，并且带有蒙层效果
function tipsOrder(obj){
	objName = obj;//
	var _scrollHeight = $(document).scrollTop();//获取当前窗口距离页面顶部高度 
	_windowHeight = $(window).height();//获取当前窗口高度 
	_windowWidth = $(window).width();//获取当前窗口宽度 
	_popupHeight = $(objName).height();//获取弹出层高度 
	_popupWeight = $(objName).width();//获取弹出层宽度 
	_posiTop = (_windowHeight - _popupHeight)/4 + _scrollHeight; 
	_posiLeft = (_windowWidth - _popupWeight)/2; 
	$(objName).css({"left": _posiLeft + "px","top":_posiTop + "px","display":"block"});//设置position 
    $('.tipsBg').show(50);
    $(objName).show(100);
}
//主要用于点击 商品列表的那个价格的。
function showFilter(me,obj){
	if ($('.searchList').is(":hidden") == false){
		$('.searchList').hide();
		}
	$(me).toggleClass('up');
	objName = obj;
    $(objName).slideToggle(300);
}
function changeList(obj,showObj,hideObj){
	$(obj).toggleClass(showObj);
}
//关闭一个层
function closeDiv(obj,btu){
	objName = obj;
    $('.tipsBg').hide(20);
    $(objName).hide(20);
	$(btu).removeClass('on');
	}
//显示一个层，弹出窗口的效果
function openDiv(obj,clickObj){
	if ($('.filterList').is(":hidden") == false){
		$('.filterList').hide();
		$('.brandF').removeClass('on');
		}
	objName = obj;
	if ($(obj).is(":hidden")){
		$(clickObj).addClass('on');
		$(objName).show("slow");
		}
	else {
		$(clickObj).removeClass('on');
		$(objName).hide("slow");
		}
	}
	
$(function(){
	//.login获取焦点的时候，添加一个状态
	$('.login input').focus(function(){
		$(this).parents('li').addClass('on');
	});
	//给对象删除一个状态
	$('.login input').focusout(function(){
		$(this).parents('li').removeClass('on');
	});
	
	$('.filterList li').click(function(){
		$('.brandF').removeClass('on');
		$('.filterList').hide();
	});
	
	$('.selectParm span').click(function(){
		$(this).parents('.selectParm').find('span').removeClass('on');	
		$(this).addClass('on');			
	});
	
	$('.showRec').click(function(){
		if ($(this).hasClass('up')){
			$(this).parents('.recive').find('li').hide();
			$(this).parents('.recive').find('li').eq(0).show();
			$(this).removeClass('up');
			}
		else {
			$(this).parents('.recive').find('li').show();
			$(this).addClass('up');
		}
		
	});
	
});