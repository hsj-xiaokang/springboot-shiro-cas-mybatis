<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>login</title>

    <link href="/css/home.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>
</head>
<body>
<h1>login page</h1>
<h2>${welcomeMsg}</h2>
<form action="/common/login" method="post">
    <div>
     <label>用户名:<input type="text" id="name" name="name"/></label>
    </div>
    <div>
     <label>密码:<input type="password"/></label>
    </div>
    <div>
        <input type="submit" value="提交"/>
        <input type="reset" value="重置" />
    </div>
</form>
</body>
</html>