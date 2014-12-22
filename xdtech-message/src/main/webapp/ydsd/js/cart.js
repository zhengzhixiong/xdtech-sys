document.addEventListener("plusready", function() {
		//连接购物车数量 
		RequestData('APP_Cart','',0,0,function(data){
			var length = data.Data.ProductList.length;
			if(length>0) 
				$(".cart div").append("<span>"+length+"</span>");
		},function(){},function(){});
//	}
});

