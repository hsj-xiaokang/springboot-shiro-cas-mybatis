<html xmlns:form="http://www.w3.org/1999/html">
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>登录页面</h1>
        <#if message?exists>
            <div style="color:#F00">${message}</div>
        </#if>
        <form action="${context.contextPath}/login" method="post">
            用户名：<input type="text" name="username"/><br/>
            密码：<input type="password" name="password"/><br/>

            <input type="submit" value="提交"/>
        </form>
    </body>
</html>
