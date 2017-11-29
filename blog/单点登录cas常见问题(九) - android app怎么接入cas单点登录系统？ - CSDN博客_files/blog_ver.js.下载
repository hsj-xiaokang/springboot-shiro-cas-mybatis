/**
 * Created by liwz on 2017-8-7.
 * attention.
 * login and not login
 * search
 */

$(document).ready(function () {
    var un = getUN().toLowerCase();
    if (un == _blogger.toLowerCase()) {
        $('.interact').hide();
    } else if (un) {
        set_guanzhu_status(un);
    } else { //未登录状态
        $('#span_add_follow').on("click",function () { loginto(0); });
    }

    //login
    var blogVer = {
        notLogin:function(){
            var userName = currentUserName,
                    _this = this;
            if(userName == '' || userName == undefined){
                _this.popHide(".alrLogin");
                _this.popShow(".notLogin");
            }else{
                _this.popShow(".alrLogin");
                _this.popHide(".notLogin");
            }
        },
        _getCookieValue:function (a, b) {
            b = document.cookie.match('(^|;)\\s*' + a + '\\s*=\\s*([^;]+)');
            return b ? b.pop() : '';
            //return '11';  //测试  ''未登录跳转，其他登陆成功
        },
        _verificaUser:function(){
            if(!(this._getCookieValue('UserName')))
            {
                var _url = "https://passport.csdn.net/account/login?from=" + encodeURI(location.href);
                window.location = _url;
                return false;
            }else{
                return true;
            }
        },
        collectArticle:function(){
            var collectBtn = $("#com-quick-collect"),
                    popCloseBtn = $(".cancel_icon"),
                    _this = this,
                    collectWrap = $("#collectIframe"),
                    title = $(".csdn_top").text(),
                    src = 'http://my.csdn.net/my/favorite/miniadd?t='+title +'&u=';
            collectBtn.on("click",function(){
                if(_this._verificaUser()){
                    var curUrl = window.location.href;
                    _this.popShow(".pop_CA_cover");
                    _this.popShow(".pop_CA");
                    collectWrap.attr("src",src + curUrl);
                }
                return false;
            });
            popCloseBtn.on("click",function(){
                _this.popHide(".pop_CA_cover");
                _this.popHide(".pop_CA");
                collectWrap.attr("src","");
                return false;
            });
        },
        popShow:function(obj){
            $(obj).show();
        },
        popHide:function(obj){
            $(obj).hide();
        },
        report:function(e,t,n){
            $.createMask();
            var r = blog_address + "/common/report?id=" + e + "&t=" + t;
            if (3 == t) {
                var s = n.parentNode.parentNode.parentNode.getAttribute("floor");
                r += "&floor=" + s
            }
            var i = (document.documentElement.clientHeight - 400) / 2 + (document.documentElement.scrollTop || document.body.scrollTop)
                    , a = (document.documentElement.clientWidth - 400) / 2;

            $("#report_dialog").load(r).css({
                //top: i + "px",
                top: '20%',
                left: a
            }).show()
            
        },
        //search,
        goFn:function(obj,txt){
            var searchTxt = encodeURIComponent(txt),
                    url = "http://so.csdn.net/so/search/s.do?q="+searchTxt + "&t=blog";
            if(searchTxt == ''){
                return false;
            }else{
                window.location.href = url;
            }

        },
        search:function() {
            var searchBtn = $(".btn-lg34"),
                    searchInpt = $(".inp_search"),
                    _this = this;
            searchBtn.on("click", function (event) {
                _this.goFn($(this), $(this).prev("input").val());
            });

            searchInpt.keyup(function (event) {
                var evCode = event.keyCode;
                if(evCode == 13) {
                    var searchTxt = encodeURIComponent($(this).val()),
                        url = "http://so.csdn.net/so/search/s.do?q=" + searchTxt + "&t=blog";
                    window.location.href = url;
                }
            });
        },
        //edit and delete
        editDelete:function(){
            var _this = this;
            if(username == currentUserName){
                _this.popShow(".edit");
                _this.popShow(".del");
            }else{
                _this.popHide(".edit");
                _this.popHide(".del");
            }
        },
        isExpert:function(){
            var medal = $(".medals");
            if(isExpert == true){
                medal.attr("title","博客专家");
            }else{
                medal.attr("title","");
            }
        }
    };

    blogVer.notLogin();
    blogVer.collectArticle();
    $(".r_ico").on("click",function(){
        blogVer.report(fileName,2);
        return false;
    });
    blogVer.search();
    blogVer.editDelete();
    blogVer.isExpert();

    jQuery.createMask = function() {
        var e = document.documentElement.clientHeight
                , t = document.documentElement.clientWidth
                , n = $("body").height();
        n > e && (e = n);
        var r = {};
        0 == $("#mask_div").length && $("body").append('<div id="mask_div" style="position:absolute;z-index:9999;top:0;left:0;filter:alpha(opacity=20);-moz-opacity:0.2;opacity:.2;"></div>'),
                r = $("#mask_div"),
                r.css({
                    width: t,
                    height: e,
                    background: "#000"
                })
    };


});
function set_guanzhu_status(un) {
    var url = "http://my.csdn.net/index.php/follow/check_is_followed/" + encodeURIComponent(un) + "/" + encodeURIComponent(_blogger) + "?jsonpcallback=?";
    $.getJSON(url, {}, function (data) {
        if (data.succ == 1 && data.info == 1) {
            //$('#span_add_follow').attr('class', 'attented')[0].onclick = (function () { return false; });
            $('#span_add_follow').addClass( "attented").text("已关注");
            $('#fan').text(parseInt($('#fan').text())+1)
        } else {
            $('#span_add_follow')[0].onclick = guanzhu;
        }
    }, 'json');
}
function guanzhu() {
    var url = "http://my.csdn.net/index.php/follow/do_follow?jsonpcallback=?";
    $.getJSON(url, { "username": _blogger }, function (data) {
        if (data.succ == 1) {
            //$('#span_add_follow').addClass('attented')[0].onclick = (function () { return false; });
            $('#span_add_follow').addClass('attented').text("已关注").on("click",function () { return false; });
        } else {
            alert(data.msg);
        }
    });
    return false;
}
function getUN() {
    var m = document.cookie.match(new RegExp("(^| )UserName=([^;]*)(;|$)"));
    if (m) return m[2];
    else return '';
}

function loginto(n) {
    if (!n) {
        csdn.showLogin(function (data) {
            js_logined(data.data.userName);
        });
    } else {
        if (!getUN()) {
            csdn.showLogin(function (data) {
                js_logined(data.data.userName);
            });
        } else {
            location = 'http://my.csdn.net/my/letter/send/' + _blogger;
        }
    }
}
function js_logined(un) {
    /*currentUserName = un;
     set_guanzhu_status(un);
     if (typeof load_comment_form == 'function') {
     load_comment_form();
     }*/
    location = '?reload';
}


