
function showMsg(info) {
	$.messager.show({    // show error message
        title: '系统提示',
        msg: info,
        timeout:1000,
        showType:'slide'
    });
}
/**
 * 操作成功提示
 */
function showSucMsg() {
	showMsg('操作成功.');
}
/**
 * 操作失败提示
 */
function showFailMsg() {
	showMsg('操作失败.');
}
function showError(info) {
	
}

function alertMsg(info) {
	$.messager.alert('系统提示',info);
}

function printLog(info) {
	console.log(info);
}

function isEmpty(v) {
	switch (typeof v) {
	case 'undefined':
		return true;
	case 'string':
		if (trim(v).length == 0)
			return true;
		break;
	case 'boolean':
		if (!v)
			return true;
		break;
	case 'number':
		if (0 === v)
			return true;
		break;
	case 'object':
		if (null === v)
			return true;
		if (undefined !== v.length && v.length == 0)
			return true;
		for ( var k in v) {
			return false;
		}
		return true;
		break;
	}
	return false;
}

function isNotEmpty(val) {
	if (typeof(val) == "undefined") { 
		return false;
	}else if(val==null) {
		return false;
	}else if(val==''||val=='null') {
		return false;
	}else {
		return true;
	}
	
	 
}
 


function onloading(){  
    $("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");   
    $("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block","z-index": 9999 ,left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});   
}  
function removeload(){  
   $(".datagrid-mask").remove();  
   $(".datagrid-mask-msg").remove();  
}




/**
 * @author zzx
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
	for ( var i = 0; i < arguments.length - 1; i++) {
		str = str.replace("{" + i + "}", arguments[i + 1]);
	}
	return str;
};

function createFromWindow(title,href,submitUrl,callback,enableSaveButton) {
	$.easyui.showDialog({
        title: title,
        maximizable: true,
        autoRestore: true,
        width : 800,
		height : 500,
        topMost: false,
		enableApplyButton:false,
		enableSaveButton:enableSaveButton,
        href: href,
        onSave: function (d) {
			printLog(d);
            var validate = d.form("validate");
            printLog(validate);
            if (validate) {
           	    printLog(d.form("getData"));
	           	$.ajax({
	           	  type: 'POST',
	           	  url: submitUrl,
	           	  data: d.form("getData"),
	           	  success: function(data) {callback(data);}
	           	});
            } else {
                return false;
            }
        },
    });
}
function createFromWindowOptions(options) {
	var winWidth = options.width?options.width:800;
	var winHeight = options.height?options.height:500;
	return $.easyui.showDialog({
        title: options.title,
        maximizable: options.maximizable,
        autoRestore: true,
        width : winWidth,
		height : winHeight,
        topMost: false,
		enableApplyButton:false,
		enableSaveButton:options.enableSaveButton,
        href: options.href,
        onSave: function (d) {
        	 var validate = d.form("validate");
             printLog(validate);
             if (validate) {
            	 return options.callback(d);
             } else {
                 return false;
             }
        	
        },
    });
}



/**
 * @author zzx
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
	if ($.modalDialog.handler == undefined) {// 避免重复弹出
	var opts = $.extend({
		title : '',
		maximizable: true,
	    autoRestore: true,
		width : 840,
		height : 680,
		modal : true,
		onClose : function() {
			$.modalDialog.handler = undefined;
			$(this).dialog('destroy');
		},
		onOpen : function() {
//				parent.$.messager.progress({
//					text : '数据加载中，请稍后....'
//				});
//				onloading();
		}
	}, options);
	opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	return $.modalDialog.handler = $('<div/>').dialog(opts);
	}
};


