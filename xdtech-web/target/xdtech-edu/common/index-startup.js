
(function ($) {

    var hash = window.location.hash, start = new Date();

    $(function () {
//        window.mainpage.instMainMenus();
//        window.mainpage.instFavoMenus();
        window.mainpage.instTimerSpan();
//        window.mainpage.bindNavTabsButtonEvent();
        window.mainpage.bindToolbarButtonEvent();
//        window.mainpage.bindMainTabsButtonEvent();

//        window.donate.reload();
//        window.link.reload();

        var portal = $("#portal"), layout = $("#mainLayout"), navTabs = $("#navTabs"), themeCombo = $("#themeSelector");

        $.util.exec(function () {
            var theme = $.easyui.theme(), themeName = $.cookie("themeName");
            if (themeName && themeName != theme) { window.mainpage.setTheme(themeName, false); }

            layout.removeClass("hidden").layout("resize");

            $("#maskContainer").remove();

            var size = $.util.windowSize();
            //  判断当浏览器窗口宽度小于像素 1280 时，右侧 region-panel 自动收缩
            if (size.width < 1280) { layout.layout("collapse", "east"); }

//            window.mainpage.mainTabs.loadHash(hash);

//            var stop = new Date();
//            $.easyui.messager.show({ msg: "当前页面加载耗时(毫秒)：" + $.date.diff(start, "ms", stop), position: "bottomRight" });
        });

        


//        $.util.namespace("mainpage.util");
//        window.mainpage.util.getUrlResponse = function (url, callback) {
//            $.get("./tests/HttpUtility.asmx/GetUrlResponse", { url: url }, function (data) {
//                var text = $(data).text();
//                if ($.isFunction(callback)) { callback.call(this, text); }
//            });
//        };
    });
    

})(jQuery);