(function(){
  var blogname = 'oldblog',
      address = window.location.host.split(".")[0], // 获取二级域名
      homeAdress={
        feed : 'http://blog.csdn.net/',
        blog : 'http://oldblog.csdn.net/'
      },
      isFeed = false, // 是否feed首页
      popu_content={
        popuNUm: 'popu_514',
        pid:'blog',
        push:'get'
      },
    isBlogIndex = window.location.href.indexOf('.html') > -1 ? false:true,
    isBlogConent =  window.location.href.indexOf('article')> -1? false:true;
    // 判断二级域名是否显示切换开关
    if((address == blogname && !isBlogIndex)) return ;
    // 判断是否是详情页
    if((address === blogname) && !isBlogConent){
      // 如果是博客首页的子页面或详情页则不显示
      if(!(address == blogname && isBlogIndex && isBlogConent)) return;
      // 博客首页判断是否不在需要直接跳转到feed首页
      var searchStr = window.location.search.substring(1).split("&").forEach(function(s){
        if(s.indexOf('feed')>-1){
          if(s.split("=")[1] === '0') localStorage.setItem('switchHome',0)
        }
      });
      var switchHome = localStorage.getItem('switchHome');
      // 判断localStorage 查看是否需要直接跳转到feed首页
      if(parseInt(switchHome)) window.location.href = homeAdress.feed;
      // feed首页 和迁移后的blog首页 显示欢迎文字
    }else if(address=== 'feed' || (address === 'blog' && isBlogIndex)){

      isFeed = true;
      popu_content={
        popuNUm: 'popu_513',
        pid:'feed',
        push:'get'
      }
    }
  // 使用onload 事件加载
  function addLoadEvent(func){
      var oldonload=window.onload;
      if(typeof window.onload != 'function'){
          window.onload=func;
      }else{
        window.onload=function(){
          oldonload();
          func();
         }
      }
  };

  function isSwitchAddress(){
    var switchButtonNew = '新版';
    var text = 'CSDN新首页已经上线，邀请您来立即体验！',
        activationDom = '.switch-old';
    if(isFeed) text = '您现在体验的是CSDN新首页！有任何建议请反馈给我们 <a href="mailto:feedback@csdn.net" style="color:#4093c6;">feedback@csdn.net</a>';
    if(!isBlogConent) {
      text = '<a href="http://blog.csdn.net/" target="_blank">CSDN新首页上线啦，邀请你来立即体验！</a>';
      switchButtonNew = '立即体验'
    }
    var mainLeft = 0;
    try {
      mainLeft = $('main').length> 0?$('main').offset().left:$('.blog_l').offset().left;
    } catch (e) {
      mainLeft = 150;
    }
    var switchDOMText = '<div class="switchDOM " data-poputype="feed" data-feed-show="false" style ="display:none;height:35px;background: #FAFAFA;box-shadow: 0 1px 2px 0 rgba(0,0,0,0.1);margin-bottom:1px;">\
                      <div class="switch-text" style="padding-left:'+mainLeft+'px;padding-right:16px;"><span style="line-height:35px;font-size: 14px;color: #4F4F4F;">'+text+'</span>\
                        <div class="switch-bottom csdn-tracking-statistics" style="float:right">\
                          <a class="switch-old" href="javascript:void(0);" style="padding-bottom: 5px;padding-top: 5px;line-height:35px;font-size:12px;padding-left:10px;padding-right:10px;" target="_self">旧版</a>\
                          <a class="switch-new" href="javascript:void(0);" style="padding-bottom: 5px;padding-top: 5px;line-height:35px;font-size:12px;padding-left:10px;padding-right:10px;" target="_self">'+switchButtonNew+'</a>\
                        </div>\
                      </div>\
                      <style>\
                      .switch-old,.switch-new,.switch-new:link,.switch-new:visited,.switch-old:link,.switch-old:visited{color:#999;}\
                      body .switch-activation{border-radius: 16px;background-color: #5B5B5B;color:#fff !important}\
                      body .switch-old:hover,body .switch-new:hover{color: #4F4F4F;}\
                      body .switch-activation:hover{color:#999;} \
                      </style>\
                    </div>'
    var bodyDOM = $('body');
    bodyDOM.prepend(switchDOMText);
    csdn.trackingAd('.switch-bottom', {
      pid: popu_content.pid,
      mod: popu_content.popuNUm,
      dsm: popu_content.push,
      mtp: '1'
    });
    // 内容页则去掉跳转老版按钮，只显示新版按钮
    if(!isBlogConent){
      $('.switch-old').css({'display':'none'})
      $('.switch-new').addClass('switch-activation')
    }
    if(isFeed) activationDom = '.switch-new';
    $(activationDom).addClass('switch-activation');
    $('.switchDOM').show(500);
    $('.switch-old').on('click',function(){
      if(address=== 'oldblog') return
      window.location.href = homeAdress.blog+"?feed=0";
    })
    $('.switch-new').on('click',function(){
      if(address=== 'feed') return
      if(!isBlogConent){
        window.open('http://blog.csdn.net/');
      }else{
        localStorage.setItem('switchHome',1);
        window.location.href = homeAdress.feed;
      }
    })
  }
  if(!isBlogConent){
    setTimeout(isSwitchAddress,2000)
  }else{
    addLoadEvent(isSwitchAddress)
  }
})();
