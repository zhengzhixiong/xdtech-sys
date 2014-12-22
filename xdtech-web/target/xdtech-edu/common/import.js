function importJS(path){
	document.write("<script type='text/javascript' src='"+path+"'></script>");
}
function importCSS(path){
	document.write("<link rel='stylesheet' type='text/css' href='"+path+"'>");
}
importCSS('jquery-easyui-theme/default/easyui.css');
importCSS('jquery-easyui-theme/icon.css');
importCSS('icons/icon-all.css');

importJS('jquery/jquery-1.8.3.min.js');
importJS('jquery-easyui-1.3.6/jquery.easyui.min.js');
importJS('jquery-easyui-1.3.6/locale/easyui-lang-zh_CN.js');

importJS('release/jquery.jdirk.min.js');
importCSS('jeasyui-extensions/jeasyui.extensions.css');

importJS('release/jeasyui.extensions.all.min.js');

importJS('icons/jeasyui.icons.all.js');

importJS('release/jeasyui.icons.all.min.js');
importJS('jeasyui-extensions/jquery-easyui-toolbar/jquery.toolbar.js');
importJS('jeasyui-extensions/jquery-easyui-comboicons/jquery.comboicons.js');
importJS('jeasyui-extensions/jeasyui.extensions.gridselector.js');
importJS('jeasyui-extensions/jquery-easyui-comboselector/jquery.comboselector.js');
//importJS('jeasyui-extensions/jeasyui.extensions.datagrid.js');


importJS('plugins/My97DatePicker/WdatePicker.js');
importJS('jeasyui-extensions/jquery-easyui-my97/jquery.my97.js');

importJS('jeasyui-extensions/jquery-easyui-portal/jquery.portal.js');

//导入首页启动时需要的相应资源文件(首页相应功能的 js 库、css样式以及渲染首页界面的 js 文件)
importJS('common/index.js');
importCSS('common/index.css');
importJS('common/index-startup.js');
importJS('common/common.js');

