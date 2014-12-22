


//清除缓存的图片
function deleteImage(){
	plus.io.resolveLocalFileSystemURL("_downloads/_img" , function(fs) {
			//递归删除目录
			fs.removeRecursively(function(entry){
				alert("删除成功")
			},function(e){
				alert(e.message )
			});
			
			return true;
		}, function(e) {
			alert("文件不存在");
			return false;
	});
}

//加载图片，如果不存在下载之，如果存在加载之
function loadImage(imgUrl,successFunction,errFunction) {
	
	var url = imgUrl;
	if(imgUrl.indexOf("http://")!=-1){
		url = imgUrl.substring(7,imgUrl.length);
	}
	
	
	plus.io.resolveLocalFileSystemURL("_downloads/_img/" + url, function(fs) {
		successFunction(fs);
	}, function(e) {
		//不存在图片，调用下载方法
		downloadImg(url,imgUrl,successFunction,errFunction);
	});
}

//封装下载的方法
function downloadImg(url,imgUrl,successFunction,errFunction){
	
	var dtask = plus.downloader.createDownload(imgUrl, {
		filename : "_img/" + url  //把图片的地址作为图片名字
	}, function(d, status) {
		if (status == 200) {
			plus.io.resolveLocalFileSystemURL("_downloads/_img/" + url, function(fs) {
				successFunction(fs);
			},function(){alert("load err")});
		} else {
			errFunction(d);
		}
	});
	dtask.start(); 
}

var keyDelete = "delete",
checkInterval=60480000,//检查间隔，单位为ms,7天为7*24*60*60*1000=60480000,
dir = null;
function check(){
	// 判断删除检测是否过期
	var lastcheck = plus.storage.getItem( keyDelete );
	if ( lastcheck ) {
		var dc = parseInt( lastcheck );
		var dn = (new Date()).getTime();
		if ( dn-dc < checkInterval ) {	// 未超过上次删除检测间隔，不需要进行删除检查
			return;
		}
		plus.storage.setItem(keyDelete, dn);
		plus.io.resolveLocalFileSystemURL("_downloads/_img" , function(fs) {
			//递归删除目录
				fs.removeRecursively(function(entry){
//					alert("删除成功")
				},function(e){
//					alert(e.message )
				});
				
			}, function(e) {
//				alert("文件不存在");
		});
		
	}else{
		plus.storage.setItem(keyDelete, (new Date()).getTime());
	}
}




