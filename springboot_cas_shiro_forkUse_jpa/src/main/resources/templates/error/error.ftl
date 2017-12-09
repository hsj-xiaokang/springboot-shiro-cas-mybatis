<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error</title>
    <style type="text/css">
        body{
            padding: 20px;
        }
        div{
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>Error Page</h1>
<p>
    <div style="color:red;font-size:large">error code: ${status}-${error}</div>
    <div style="color:red;font-size:medium">error msg: ${exception!},${message!}</div>

    <div>
        <div><a href="javascript:void()" onclick="showOrHideDetail()">error detail:</a></div>
        <div id="trace" style="color:grey;font-size:small;display: none;border: 1px solid black;padding: 10px;">${trace!}</div>
    </div>
</p>
<script type="text/javascript">
    var showOrHideDetail = function(){
        var traceDiv = document.getElementById('trace');
        var display = traceDiv.style.display;
        if(display == 'none'){
            traceDiv.style.display='block';
        }else{
            traceDiv.style.display='none';
        }
    };
</script>
</body>
</html>