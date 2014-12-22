function importJS(path){
	document.write("<script type='text/javascript' src='"+path+"'></script>");
}
function importCSS(path){
	document.write("<link rel='stylesheet' type='text/css' href='"+path+"'>");
}


importCSS('plugins/easyui/themes/default/easyui.css');
importCSS('plugins/easyui/themes/icon.css');
//importJS('plug-in/easyui/jquery.min.js');
importJS('plugins/jquery/jquery-1.8.3.min.js');
importJS('plugins/easyui/jquery.easyui.min.js');
importJS('plugins/xdtech/js/common.js');
importJS('plugins/easyui/locale/easyui-lang-zh_CN.js');
importCSS('plugins/xdtech/css/common.css');