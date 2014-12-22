function getCity(id,val){
	//连接城市数据
	RequestData('APP_City', id, 0, 0, function(e) {
		var d = e.Data;
		if(d){
			$("#selCity").empty();
			//添加数据到select节点下边
			$.each(d, function(i,n) {
				$("#selCity").append("<option value='"+n.CityId+"'>"+n.CityName+"</option>");                                                      
			});
			$("#selCity").val(val);
		}
	}, function(d) {
		alert(d.Message);	//弹出服务器返回失败的原因
	},function(){
		plus.ui.alert($.i18n.prop("string_requsertOver"), function() {}, "请求失败", "确定");
	});
}
function getProvince(){
	//连接省份数据
	RequestData('APP_Province', '', 0, 0, function(e) {
		var d = e.Data;
		if(d){
			//添加数据到select节点下边
			$.each(d, function(i,n) {
				if(i==0){
					getCity(n.ProvinceId);
				}
				$("#selProvince").append("<option value='"+n.ProvinceId+"'>"+n.ProvinceName+"</option>");                                                      
			});
		}
	}, function(d) {
		alert(d.Message);	//弹出服务器返回失败的原因
	},function(){
		//连接超时
		plus.ui.alert($.i18n.prop("string_requsertOver"), function() {}, "请求失败", "确定");
	});
}