
(function () {
    var hrefs=window.location.href;
    var ref = '';
    if (document.referrer.length > 0) {
        ref = document.referrer
    }
    try {
        if (ref.length == 0 && opener.location.href.length > 0) {
            ref = opener.location.href
        }
    } catch(e) {}
    var apiurl="";
    if(ref=="" || ref==null){
        apiurl="http://139.198.11.216:8801/business/api/url/collect?u="+encodeURIComponent(hrefs);
    }else{
        apiurl="http://139.198.11.216:8801/business/api/url/collect?u="+encodeURIComponent(hrefs)+"&r="+encodeURIComponent(ref);
    }
    (new Image).src=apiurl;
})();
