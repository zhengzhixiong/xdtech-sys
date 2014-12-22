function getCity(id){
	//从服务器获取城门的数据
	RequestData('APP_City', id, 0, 0, function(e) {
		var d = e.Data;
		if(d){
			$("#selCity").empty();
			//读取数据，并添加到select节点下边
			$.each(d, function(i,n) {
				if(id==n.CityId){
					$("#selCity").append("<option value='"+n.CityName+"#"+n.CityId+"' selected>"+n.CityName+"</option>"); 
				}else{
					$("#selCity").append("<option value='"+n.CityName+"#"+n.CityId+"'>"+n.CityName+"</option>");                                                      
				}
			});
		}
	}, function(d) {
		plus.ui.alert(d.Message, function() {}, "请求失败", "确定");
			//弹出服务器返回失败的原因
	},function(){
		//连接超时
		plus.ui.alert($.i18n.prop("string_requsertOver"), function() {}, "请求失败", "确定");
	});
}
function getProvince(province){
	//连接省份数据
	RequestData('APP_Province', '', 0, 0, function(e) {
		var d = e.Data;
		if(d){
			$.each(d, function(i,n) {
				//添加省份数据到select节点下边
				if(province == n.ProvinceName){
					$("#selProvince").append("<option value='"+n.ProvinceName+"#"+n.ProvinceId+"' selected>"+n.ProvinceName+"</option>");
					getCity(n.ProvinceId);
				}else{
					$("#selProvince").append("<option value='"+n.ProvinceName+"#"+n.ProvinceId+"'>"+n.ProvinceName+"</option>");                                                      
				}
			});
		}
	}, function(d) {
		plus.ui.alert(d.Message, function() {}, "请求失败", "确定");
		//弹出服务器返回失败的原因
	},function(){
		//连接超时
		plus.ui.alert($.i18n.prop("string_requsertOver"), function() {}, "请求失败", "确定");
	});
}