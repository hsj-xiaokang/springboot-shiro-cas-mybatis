<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>test</title>
    <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
</head>
<body>
<h1>test page</h1>
<div>
    <button onclick="doAjax();">请求异常测试</button>

    <div id="msg"></div>
    <textarea id="errorMsg" style="color:red;width: 80%;height:300px"></textarea>
</div>


<script type="text/javascript">
    var doAjax = function () {
        $.ajax({
            url:'/test2/throwex',
            async:true,
            type:'GET',
            data:{},
            dataType:'json',
            success:function (data) {
                $('#msg').text(data);
            },
            error: function(xhr, textStatus, errorThrown) {
//                alert(XMLHttpRequest.status);
//                alert(XMLHttpRequest.readyState);
//                alert(textStatus);
                $('#errorMsg').text(xhr.responseText);
            }
        }); 
    };
</script>
</body>
</html>