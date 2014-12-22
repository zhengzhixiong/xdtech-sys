/*
 *  Document   : app.js
 *  Author     : pixelcave
 *  Description: Custom scripts and plugin initializations (available to all pages)
 *
 *  Feel free to remove the plugin initilizations from uiInit() if you would like to
 *  use them only in specific pages. Also, if you remove a js plugin you won't use, make
 *  sure to remove its initialization from uiInit().
 */

var App = function() {

    /* Cache variables of some often used jquery objects */
    var page        = $('#page-container');
    var header      = $('header');

    /* Sidebar */
    var sToggleSm   = $('#sidebar-toggle-sm');
    var sToggleLg   = $('#sidebar-toggle-lg');
    var sScroll     = $('.sidebar-scroll');

    /* Initialization UI Code */
    var uiInit = function() {

        // Initialize Sidebar Functionality
        handleSidebar('init');

        // Sidebar Navigation functionality
        handleNav();

        // Scroll to top functionality
        scrollToTop();

        // Template Options, change features
        templateOptions();

        // Add the correct copyright year at the footer
        var yearCopy = $('#year-copy'), d = new Date();
        if (d.getFullYear() === 2014) { yearCopy.html('2014'); } else { yearCopy.html('2014-' + d.getFullYear().toString().substr(2,2)); }

        // Set min-height to #page-content, so that footer is visible at the bottom if there is not enough content
        $('#page-content').css('min-height', $(window).height() -
            (header.outerHeight() + $('footer').outerHeight()) + 'px');

        $(window).resize(function() {
            $('#page-content').css('min-height', $(window).height() -
                (header.outerHeight() + $('footer').outerHeight()) + 'px');
        });

        // Initialize tabs
        $('[data-toggle="tabs"] a, .enable-tabs a').click(function(e){ e.preventDefault(); $(this).tab('show'); });

        // Initialize Tooltips
        $('[data-toggle="tooltip"], .enable-tooltip').tooltip({container: 'body', animation: false});

        // Initialize Popovers
        $('[data-toggle="popover"], .enable-popover').popover({container: 'body', animation: true});

        // Initialize single image lightbox
        $('[data-toggle="lightbox-image"]').magnificPopup({type: 'image', image: {titleSrc: 'title'}});

        // Initialize image gallery lightbox
        $('[data-toggle="lightbox-gallery"]').magnificPopup({
            delegate: 'a.gallery-link',
            type: 'image',
            gallery: {
                enabled: true,
                navigateByImgClick: true,
                arrowMarkup: '<button type="button" class="mfp-arrow mfp-arrow-%dir%" title="%title%"></button>',
                tPrev: 'Previous',
                tNext: 'Next',
                tCounter: '<span class="mfp-counter">%curr% of %total%</span>'
            },
            image: {titleSrc: 'title'}
        });

        // Initialize Editor
        $('.textarea-editor').wysihtml5();

        // Initialize Chosen
        $('.select-chosen').chosen({width: "100%"});

        // Initiaze Slider for Bootstrap
        $('.input-slider').slider();

        // Initialize Tags Input
        $('.input-tags').tagsInput({ width: 'auto', height: 'auto'});

        // Initialize Datepicker
        $('.input-datepicker, .input-daterange').datepicker({weekStart: 1});
        $('.input-datepicker-close').datepicker({weekStart: 1}).on('changeDate', function(e){ $(this).datepicker('hide'); });

        // Initialize Timepicker
        $('.input-timepicker').timepicker({minuteStep: 1,showSeconds: true,showMeridian: true});
        $('.input-timepicker24').timepicker({minuteStep: 1,showSeconds: true,showMeridian: false});

        // Easy Pie Chart
        $('.pie-chart').easyPieChart({
            barColor: $(this).data('bar-color') ? $(this).data('bar-color') : '#777777',
            trackColor: $(this).data('track-color') ? $(this).data('track-color') : '#eeeeee',
            lineWidth: $(this).data('line-width') ? $(this).data('line-width') : 3,
            size: $(this).data('size') ? $(this).data('size') : '80',
            animate: 800,
            scaleColor: false
        });

        // Initialize Placeholder
        $('input, textarea').placeholder();
    };

    /* Sidebar Navigation functionality */
    var handleNav = function() {

        // Animation Speed, change the values for different results
        var upSpeed     = 250;
        var downSpeed   = 250;

        // Get all vital links
        var allTopLinks     = $('.sidebar-nav a');
        var menuLinks       = $('.sidebar-nav-menu');
        var submenuLinks    = $('.sidebar-nav-submenu');

        // Primary Accordion functionality
        menuLinks.click(function(){
            var link = $(this);

            if (link.parent().hasClass('active') !== true) {
                if (link.hasClass('open')) {
                    link.removeClass('open').next().slideUp(upSpeed);
                }
                else {
                    $('.sidebar-nav-menu.open').removeClass('open').next().slideUp(upSpeed);
                    link.addClass('open').next().slideDown(downSpeed);
                }
            }

            return false;
        });

        // Submenu Accordion functionality
        submenuLinks.click(function(){
            var link = $(this);

            if (link.parent().hasClass('active') !== true) {
                if (link.hasClass('open')) {
                    link.removeClass('open').next().slideUp(upSpeed);
                }
                else {
                    link.closest('ul').find('.sidebar-nav-submenu.open').removeClass('open').next().slideUp(upSpeed);
                    link.addClass('open').next().slideDown(downSpeed);
                }
            }

            return false;
        });
    };

    /* Sidebar Functionality */
    var handleSidebar = function(mode) {
        if (mode === 'init') {
            // Init Scrolling (if we have a fixed header)
            if (header.hasClass('navbar-fixed-top') || header.hasClass('navbar-fixed-bottom')) {
                handleSidebar('init-scroll');
            }

            // Init Sidebar
            sToggleSm.click(function(){ handleSidebar('toggle-sm'); });
            sToggleLg.click(function(){ handleSidebar('toggle-lg'); });
        }
        else if (mode === 'toggle-lg') { // Toggle Sidebar in large screens (> 991px)
            page.toggleClass('sidebar-full');
        }
        else if (mode === 'toggle-sm') { // Toggle Sidebar in small screens (< 992px)
            page.toggleClass('sidebar-open');
        }
        else if (mode === 'open-sm') { // Open Sidebar in small screens (< 992px)
            page.addClass('sidebar-open');
        }
        else if (mode === 'close-sm') { // Close Sidebar in small screens (< 992px)
            page.removeClass('sidebar-open');
        }
        else if (mode === 'open-lg') { // Open Sidebar in large screens (> 991px)
            page.addClass('sidebar-full');
        }
        else if (mode === 'close-lg') { // Close Sidebar in large screens (> 991px)
            page.removeClass('sidebar-full');
        }
        else if (mode == 'init-scroll') { // Init Sidebar Scrolling
            if (sScroll.length && (!sScroll.parent('.slimScrollDiv').length)) {
                // Initialize Slimscroll plugin
                sScroll.slimScroll({ height: $(window).height(), color: '#fff', size: '3px', touchScrollStep: 100 });

                // Resize sidebar scrolling height on window resize or orientation change
                $(window).resize(sidebarScrollResize);
                $(window).bind('orientationchange', sidebarScrollResizeOrient);
            }
        } else if (mode == 'destroy-scroll') { // Destroy Sidebar Scrolling
            if (sScroll.parent('.slimScrollDiv').length) {
                // Remove Slimscroll by replacing .slimScrollDiv (added by Slimscroll plugin) with sScroll
                sScroll.parent().replaceWith(function() {return $( this ).contents();});

                // Save the new .sidebar-scroll div to our sScroll variable
                sScroll = $('.sidebar-scroll');

                // Remove inline styles (added by Slimscroll plugin) from our new sScroll
                sScroll.removeAttr('style');

                // Disable functions running on window scroll and orientation change
                $(window).off('resize', sidebarScrollResize);
                $(window).unbind('orientationchange', sidebarScrollResizeOrient);
            }
        }
    };

    // Sidebar Scrolling Resize Height on window resize and orientation change
    var sidebarScrollResize         = function() { sScroll.css('height', $(window).height()); };
    var sidebarScrollResizeOrient   = function() { setTimeout(sScroll.css('height', $(window).height()), 500); };

    /* Scroll to top functionality */
    var scrollToTop = function() {
        // Get link
        var link = $('#to-top');

        $(window).scroll(function() {
            // If the user scrolled a bit (150 pixels) show the link
            if ($(this).scrollTop() > 150) {
                link.fadeIn(100);
            } else {
                link.fadeOut(100);
            }
        });

        // On click get to top
        link.click(function() {
            $('html, body').animate({scrollTop: 0}, 400);
            return false;
        });
    };

    /* Template Options, change features functionality */
    var templateOptions = function() {
        /*
         * Color Themes
         */
        var colorList = $('.sidebar-themes');
        var themeLink = $('#theme-link');
        var theme;

        if (themeLink.length) {
            theme = themeLink.attr('href');

            $('li', colorList).removeClass('active');
            $('a[data-theme="' + theme + '"]', colorList).parent('li').addClass('active');
        }

        $('a', colorList).click(function(e){
            // Get theme name
            theme = $(this).data('theme');

            $('li', colorList).removeClass('active');
            $(this).parent('li').addClass('active');

            if (theme === 'default') {
                if (themeLink.length) {
                    themeLink.remove();
                    themeLink = $('#theme-link');
                }
            } else {
                if (themeLink.length) {
                    themeLink.attr('href', theme);
                } else {
                    $('link[href="css/themes.css"]').before('<link id="theme-link" rel="stylesheet" href="' + theme + '">');
                    themeLink = $('#theme-link');
                }
            }
        });

        // Prevent template options dropdown from closing on clicking options
        $('.dropdown-options a').click(function(e){ e.stopPropagation(); });

        /* Page Style */
        var optMainStyle        = $('#options-main-style');
        var optMainStyleAlt     = $('#options-main-style-alt');

        if (page.hasClass('style-alt')) {
            optMainStyleAlt.addClass('active');
        } else {
            optMainStyle.addClass('active');
        }

        optMainStyle.click(function() {
            page.removeClass('style-alt');
            $(this).addClass('active');
            optMainStyleAlt.removeClass('active');
        });

        optMainStyleAlt.click(function() {
            page.addClass('style-alt');
            $(this).addClass('active');
            optMainStyle.removeClass('active');
        });

        /* Header options */
        var optHeaderDefault    = $('#options-header-default');
        var optHeaderInverse    = $('#options-header-inverse');
        var optHeaderTop        = $('#options-header-top');
        var optHeaderBottom     = $('#options-header-bottom');

        if (header.hasClass('navbar-default')) {
            optHeaderDefault.addClass('active');
        } else {
            optHeaderInverse.addClass('active');
        }

        if (header.hasClass('navbar-fixed-top')) {
            optHeaderTop.addClass('active');
        } else if (header.hasClass('navbar-fixed-bottom')) {
            optHeaderBottom.addClass('active');
        }

        optHeaderDefault.click(function() {
            header.removeClass('navbar-inverse').addClass('navbar-default');
            $(this).addClass('active');
            optHeaderInverse.removeClass('active');
        });

        optHeaderInverse.click(function() {
            header.removeClass('navbar-default').addClass('navbar-inverse');
            $(this).addClass('active');
            optHeaderDefault.removeClass('active');
        });

        optHeaderTop.click(function() {
            page.removeClass('header-fixed-bottom').addClass('header-fixed-top');
            header.removeClass('navbar-fixed-bottom').addClass('navbar-fixed-top');
            $(this).addClass('active');
            optHeaderBottom.removeClass('active');
            handleSidebar('init-scroll');
        });

        optHeaderBottom.click(function() {
            page.removeClass('header-fixed-top').addClass('header-fixed-bottom');
            header.removeClass('navbar-fixed-top').addClass('navbar-fixed-bottom');
            $(this).addClass('active');
            optHeaderTop.removeClass('active');
            handleSidebar('init-scroll');
        });
    };

    /* Datatables Basic Bootstrap integration (pagination integration included under the Datatables plugin in plugins.js) */
    var dtIntegration = function() {
        $.extend(true, $.fn.dataTable.defaults, {
            "sDom": "<'row'<'col-sm-6 col-xs-5'l><'col-sm-6 col-xs-7'f>r>t<'row'<'col-sm-5 hidden-xs'i><'col-sm-7 col-xs-12 clearfix'p>>",
            "sPaginationType": "bootstrap",
            "oLanguage": {
                "sLengthMenu": "_MENU_",
                "sSearch": "<div class=\"input-group\">_INPUT_<span class=\"input-group-addon\"><i class=\"fa fa-search\"></i></span></div>",
                "sInfo": "<strong>_START_</strong>-<strong>_END_</strong> of <strong>_TOTAL_</strong>",
                "oPaginate": {
                    "sPrevious": "",
                    "sNext": ""
                }
            }
        });
    };

    return {
        init: function() {
            uiInit(); // Initialize UI Code
        },
        sidebar: function(mode) {
            handleSidebar(mode); // Handle Sidebar Functionality
        },
        datatables: function() {
            dtIntegration(); // Datatables Bootstrap integration
        }
    };
}();

/* Initialize app when page loads */
$(function(){ App.init(); });