function getLength(){
	if($("#company").val().length>0&&$("#_company").val().length>0&&$("#contact").val().length>0&&$("#phone").val().length>0&&$("#qq").val().length>0&&$("#email").val().length>0&&$("#address").val().length>0){
		return true;
	}
	return false;
}

/**
 * 检查输入的邮箱格式是否正确
 */
function checkEmail(obj) {
	if ($("#email").val().toLowerCase().match(/[A-Za-z0-9_-]+[@](\S*)(net|com|cn|org|cc|tv|[0-9]{1,3})(\S*)/g) == null) {
		$("#emailTip").text("邮箱示例：xxx@163.com").addClass("alarmText");
		$("#emailTip").click(function(){
			$("#email").focus();
		});
		$("#email").focus();
		$(obj).parent().addClass("alarmText");
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#emailTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		if(getLength()){
			
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		
		return true;
	}
}

/**
 * 检查QQ的格式是否正确
 */
function checkQQ(obj) {
	if ($("#qq").val().match(/^\d{5,10}$/) == null) {
		$("#qqTip").text("示例：88888(5到10位的数字)").addClass("alarmText");
		$("#qqTip").click(function(){
			$("#qq").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#qq").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#qqTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}

/**
 * 检查手机号或电话号码的格式是否正确
 */
function checkPhone(obj) {
//	var pattern = /(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
	var pattern = /^1[3|4|5|6|8|9][0-9]{9}$/;
	if (!pattern.test($("#phone").val())) {
		$("#phoneTip").text("手机号示例如：15388888888").addClass("alarmText");
		$("#phoneTip").click(function(){
			$("#phone").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#phone").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#phoneTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		
		if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}

/**
 * 检查联系人不能为空
 */
function checkContact(obj) {
	if ($("#contact").val().length < 1) {
		$("#contactTip").text("示例：张三").addClass("alarmText");
		$("#contactTip").click(function(){
			$("#contact").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#contact").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#contactTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}

/**
 * 检查公司名称不能为空
 */
function checkCompany(obj) {
	if ($("#company").val().length < 1) {
		$("#companyTip").text("示例：深圳市XXX公司").addClass("alarmText");
		$("#companyTip").click(function(){
			$("#company").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#company").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#companyTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}
/**
 * 检查公司简称不能为空
 */
function checkCompany2(obj) {
	if ($("#_company").val().length < 1) {
		$("#_companyTip").text("示例：XXX公司").addClass("alarmText");
		$("#_companyTip").click(function(){
			$("#_company").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#_company").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#_companyTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
		if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}

/**
 * 检查地址不能为空
 */
function checkAddress(obj) {
	
	if ($("#address").val().length < 1) {
		$("#addressTip").text("示例：xxxx路xxx号").addClass("alarmText");
		$("#addressTip").click(function(){
			$("#address").focus();
		});
		$(obj).parent().addClass("alarmText");
		$("#address").focus();
		$("#reg").attr("disabled",true);
		return false;
	} else {
		$("#addressTip").text("").removeClass("alarmText");
		$(obj).parent().removeClass("alarmText");
			if(getLength()){
		$("#reg").removeClass("off");
		$("#reg").addClass("on");
		$("#reg").removeAttr("disabled");
		}
		return true;
	}
}


/**
 * 检查地址不能为空
 */
function checkBusinesMan(obj) {
	
	if ($("#businesman").val().length < 1) {
		$("#businesmanTip").text("示例：yw01").addClass("alarmText");
		$("#businesmanTip").click(function(){
			$("#businesman").focus();
		});
		$(obj).parent().addClass("alarmText");
//		$("#businesman").focus();
//		$("#reg").attr("disabled",true);
	} else {
		checkBusinesM();
		
	}
	return true;
	
	
}

function isValid() {
	if (checkCompany('#company') && checkCompany2('#_company') && checkContact('#contact') && checkPhone('#phone') && checkQQ('#qq') && checkEmail('#email') && checkAddress('#address')) {
		return true;
	}
	if(window.plus) plus.ui.alert("您有信息未填写完整，请按提示填写后重新提交",'','温馨提示');
	return false;
}
function checkBusinesM(){
	Request('APP_EmployeeCheck',getUser(),getPassword(),$("#businesman").val(),function(data){
			console.log(data);
			if(data.Code=='1'){
				$("#businesmanTip").text("").removeClass("alarmText");
				$("#businesman").parent().removeClass("alarmText");
				if(getLength()){
					$("#reg").removeClass("off");
					$("#reg").addClass("on");
					$("#reg").removeAttr("disabled");
				}
			}else{
				$("#businesmanTip").text(data.Message).addClass("alarmText");
				$("#businesmanTip").click(function(){
					$("#businesman").focus();
				});
				$("#businesman").parent().addClass("alarmText");
//				$("#businesman").focus();
//				$("#reg").attr("disabled",true);
				
			}
		},function(){
			plus.ui.alert("连接服务器超时",function(){},"请求失败","确定");
		});
}

function reg() {
	if (isValid()) {
		var wait = null;
		if (window.plus) {
			wait = plus.ui.createWaiting("");
		}
		var  provinceId= $("#selProvince").val();
		var  cityId= $("#selCity").val();
		var  province= $('#selProvince option:selected').html();
		var  city= $('#selCity  option:selected').html();
//		area = $("#selArea").val();
//		if(!area){
//			area = city;
//			city = province;
//		}
		var dat = '{"Contract":"'+$("#contact").val()+'","Company":"'+$("#company").val()+'","CompanyShortName":"'+$("#_company").val()+'","Phone":"'+$("#phone").val()+'","QQ":"'
		+$("#qq").val()+'","Email":"'+$("#email").val()+'","Province":"'+province+'","ProvinceId":"'+provinceId+'","CityId":"'+cityId+'","City":"'+city+'","Address":"'+$("#address").val()
		+'","WeChat":"'+$("#wechat").val()+'","Remark":"'+$("#remark").val()+'","EmployeeId":"'+$("#businesman").val()+'"}';
		Request('APP_Register',getUser(),getPassword(),dat,function(data){
			if (window.plus && wait) {wait.close();wait = null;}
			if(data.Code=='0'){
				openDialog('#tips');
				$(".success").html("审核通过后<br/>账号密码将以短信方式发送给您");
			}else{
				openDialog('#errTips');
				$("#errTip").text(data.Message);
			}
			
			
			
		},function(){
			if (window.plus && wait) {wait.close();wait = null;}
			plus.ui.alert("连接服务器超时",function(){
			
		},"请求失败","确定");});
	}
}




