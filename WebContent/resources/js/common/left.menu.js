/**
 * 左边菜单栏js
 */
$(function() {
    /**
     * 获取菜单
     */
    $.postJsonAsync(YOUNG_DOMAIN + "/permission/v1/menu/query/all.do", {}, function(data) {
        if (null != data && data.rspCd == "00000") {
            console.log(data);
            var menus = data.menus;
            for(var i = 0; i < menus.length; i++) {
                $("#side-menu").append(buildFirstMenu(menus[i]));
            }
        }
    });
});

/**
 * 构建一级菜单
 * @param menu 菜单集合
 * @returns 菜单
 */
function buildFirstMenu(menu) {
    var $menu = $("#base_menu_li").clone(true);
    $menu.find("ul").remove();
    $menu.find("a").remove();
    $menu.show();
    var $clone_a = $("#menu_a").clone(true);
    $clone_a.find(".nav-label").html(menu.perName);
    if (menu.hasChild) {
        $menu.append(buildSecond(menu.childPermissions));
    }
    if (null != menu.perIco && "" != menu.perIco) {
        $clone_a.find("i").removeClass().addClass(menu.perIco);
    }
    $clone_a.prop("href", "#" != menu.perUrl ? menu.perUrl : "#");
    $clone_a.removeAttr("id");
    $menu.prepend($clone_a);
    return $menu;
}

/**
 * 构建二级菜单
 * 
 * @param menus 菜单集合
 * @returns 菜单
 */
function buildSecond(menus) {
    var $menu_second_ul = $("#menu_second_ul").clone(true);
    $menu_second_ul.find("li").remove();
    for(var i = 0; i < menus.length; i++) {
        if (menus[i].hasChild) {
            var $menu_third_li = $("#menu_third_li").clone(true);
            $menu_third_li.removeAttr("id").find("ul").remove();
            $menu_third_li.find("a").removeAttr("id").prop("href", YOUNG_DOMAIN + menus[i].perUrl).prepend(menus[i].perName);
            $menu_second_ul.append($menu_third_li);
            $menu_third_li.append(buildThird(menus[i].childPermissions));
        } else {
            var $menu_second_li = $("#menu_second_li").clone(true);
            $menu_second_li.removeAttr("id").find("a").removeAttr("id").prop("href", YOUNG_DOMAIN +  menus[i].perUrl).prepend(menus[i].perName);
            $menu_second_ul.append($menu_second_li);
        }
    }
    return $menu_second_ul;
}

/**
 * 构建三级菜单
 * 
 * @param menus 菜单集合
 * @returns 菜单
 */
function buildThird(menus) {
    var $menu_third_ul = $("#menu_third_ul").clone(true);
    for(var i = 0; i < menus.length; i++) {
        var $li = $("#menu_third_ul").find("li").clone(true);
        $li.find("a").removeAttr("id").prop("href", YOUNG_DOMAIN + menus[i].perUrl).prepend(menus[i].perName);
        $menu_third_ul.append($li);
    }
    return $menu_third_ul;
}

/**
 * 创建新的Tab页面
 * @param id id
 * @param url 跳转路径
 * @param text tab显示文本
 * @param orderNo 排序编号
 * @param newTable 是否生成新tab
 * @param refresh 是否刷新
 */
function buildTab(id, url, text, orderNo, newTable, refresh){
    var k = true;
    if (!newTable) {
        $(".J_menuTab").each(
                function() {
                    if ($(this).data("id") == id) {
                        if (!$(this).hasClass("active")) {
                            $(this).addClass("active").siblings(".J_menuTab")
                                    .removeClass("active");
                            $(".J_mainContent .J_iframe").each(function() {
                                if ($(this).data("id") == id) {
                                    if (refresh) {
                                        $(this).prop("src", url);
                                    }
                                    $(this).show().siblings(".J_iframe").hide();
                                    return false
                                }
                            })
                        }
                        k = false;
                        return false
                    }
                });
    }
    if (k) {
        var tabHTML = '<a href="javascript:;" class="J_menuTab active" data-id="'+id+'">'+text+'<i class="fa fa-times-circle"></i></a>';
        $("a.J_menuTab").removeClass('active');
        $('#pageTabs').append(tabHTML);
        var iframeHtml = '<iframe class="J_iframe" id="'+orderNo+'" width="100%" height="100%" src="'+url+'" frameborder="0" data-id="'+id+'" seamless="" style="display: inline;"></iframe>';
        $("iframe.J_iframe").css('display','none');
        var $iframe = $(iframeHtml);
        $('#content-main').append($iframe);
    }
}

/**
 * 关闭当前页面,同时还原前面一个页面
 */
function closeTab(){
    var tabs = $('a.active.J_menuTab');
    var leftTabs = $(tabs).prevAll();
    
    var tabId = tabs.data("id");
    var jfreme = $('.J_iframe').each(function() {
        if ($(this).data("id") == tabId) {
            $(this).remove();
        }
    });
    tabs.remove();
    
    leftTabs.each(function() {
        $(this).addClass("active");
        var showId = $(this).data("id");
        $('.J_iframe').each(function() {
            if ($(this).data("id") == showId) {
                $(this).show();
                return false;
            }
        });
        return false;
    });
    
}
/**
 * 刷新首页消息
 */
function refreshIndex(){
    iframe0.window.onChangeShow();
}