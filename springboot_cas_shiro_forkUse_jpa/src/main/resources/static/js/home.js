/**
 * Created by hdwang on 2017/6/5.
 */

$(function(){
   $('#username').focus(function(){
      $(this).css('outline','1px solid red');
   });

    $('#username').blur(function () {
        $(this)[0].style.outline='';
    });
});