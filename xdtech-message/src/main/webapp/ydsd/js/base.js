var IP = "http://www.e-dawbuy.com:8000";
var picUrl = "http://www.e-dawbuy.com/edweb/upfile/";
//var IP = "http://192.168.1.169:8087";
var SERVER_URL=IP+"/WebAPIHandler.ashx";

var UPDATEURL = IP+"/version/update.json";
var SIZE = '10';

function getUpdateAddr(){
	return UPDATEURL;
}
function getIP() {
//	
//	var ip = null;
//	try {
//		if(window.plus) ip = plus.storage.getItem("ip");
//	}catch(e) {}
//	
//	if(ip==null){
////		ip = "192.168.1.64:8087";
//		ip = "http://www.e-dawbuy.com:8000";
//	}
//	if(ip.indexOf("http://")==-1){
//		ip = "http://" + ip;
//	}
//	SERVER_URL = ip+"/WebAPIHandler.ashx";
	return SERVER_URL;
}
//取出用户名的值
function getUser(){
	var user = '';
	try{
		user = plus.storage.getItem("user");
	}catch(e){}
	return user;
}
//密码的值
function getPassword(){
	var password = '';
	try{
		password = plus.storage.getItem("password");
	}catch(e){}
	return password;
}



//异步连接的方法
function RequestData(type, data,size,productId, successCallback, errCallback,func) {
	if(!size){
		size = '10';
	}
	if(!productId){
		productId = '1';
	}
	$.ajax({
		type : "post",
		timeout:15000,
		url : getIP(),
		dataType : "jsonp",
		jsonp : "callback",
		data : {
			'requestType' : type,
			'requestUser':"{'ID':'"+getUser()+"','Password':'"+getPassword()+"'}",
			'data' : data,
			'pageIndex':productId,
			'size':size
		},
		error:function(XMLHttpRequest,textStatus,errorThrown) {
			if(func) func();
		},
		success : function(data) {
			if (data.Code == '0') {
				successCallback(data);
			} else {
				errCallback(data);
			}
		}
	});
}

//异步连接的方法
function Request(type, user, password, data, successCallback, errCallback) {
	$.ajax({
		type : "post",
		url : getIP(),
		timeout:15000,
		data : {
			'requestType' : type,
			'requestUser':"{'ID':'"+user+"','Password':'"+password+"'}",
			'data' : data
		},
		dataType : "jsonp",
		jsonp : "callback",
		success : function(data) {
			
				successCallback(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			errCallback(textStatus);
		}
	});
}




//国际化
//JSON格式 [{"id":"","name":""}];
var nation = function(json){
	jQuery.i18n.properties({
	 		name:'strings',
	 		path:'i18n/',
	 		mode:'both',
	 		callback: function() {
				$.each(json, function(i,e) {    
					$(e.id).html($.i18n.prop(e.name));                                                     
				});
			}
	});
}
var nation = function(json,inputJson){
	jQuery.i18n.properties({
	 		name:'strings',
	 		path:'i18n/',
	 		mode:'both',
	 		callback: function() {
	 			if(json!=null){
					$.each(json, function(i,e) {    
						$(e.id).html($.i18n.prop(e.name));                                                     
					});
	 			}
				if(inputJson!=null){
					$.each(inputJson, function(i,e) {    
						$(e.id).attr("placeholder",$.i18n.prop(e.name));
					});
				}
			}
	});
}
