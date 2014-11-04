function importJS(path) {
	document.write("<script type='text/javascript' src='" + path
			+ "'></script>");
}
function importCSS(path) {
	document.write("<link rel='stylesheet' type='text/css' href='" + path
			+ "'>");
}

function skipPage(page,id) {
//		$('#'+id).addClass('active');
//		$("#mainContent").load(page);
//	$.get(page, function(result)
//	{
//		$("#mainContent").html(result);
//	}, "html");
	window.location.href=page;
}

importCSS('assets/css/bootstrap.min.css');
importCSS('assets/css/font-awesome.min.css');
//importJS('plug-in/easyui/jquery.min.js');
importCSS('assets/css/ace.min.css');
importCSS('assets/css/ace-rtl.min.css');
importCSS('assets/css/ace-skins.min.css');

importJS('assets/js/ace-extra.min.js');
window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<script>");
if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<script>");
importJS('assets/js/bootstrap.min.js');
importJS('assets/js/typeahead-bs2.min.js');

importJS('assets/js/date-time/bootstrap-datepicker.min.js');
importJS('assets/js/jqGrid/jquery.jqGrid.min.js');
importJS('assets/js/jqGrid/i18n/grid.locale-en.js');

// 特殊插件js结束 -->
importJS('assets/js/jquery-ui-1.10.3.custom.min.js');
importJS('assets/js/jquery.ui.touch-punch.min.js');
importJS('assets/js/jquery.slimscroll.min.js');
importJS('assets/js/jquery.easy-pie-chart.min.js');
importJS('assets/js/jquery.sparkline.min.js');
importJS('assets/js/flot/jquery.flot.min.js');
importJS('assets/js/flot/jquery.flot.pie.min.js');
importJS('assets/js/flot/jquery.flot.resize.min.js');

//ace scripts
importJS('assets/js/ace-elements.min.js')
importJS('assets/js/ace.min.js')