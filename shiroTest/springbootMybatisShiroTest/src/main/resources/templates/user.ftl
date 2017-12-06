<html>
    <head>
        <title>用户列表</title>
        <script src="jquery.min.js">
        </script>
    </head>
    <body>
        <#--<h1>${message}</h1>-->
        <h1>用户列表-<a href="logout">登出</a></h1>
        <h2>权限列表</h2>

        <@shiro.authenticated>用户已经登录显示此内容</@shiro.authenticated><br/>
        <@shiro.hasRole name="manager">manager角色登录显示此内容</@shiro.hasRole><br/>
        <@shiro.hasRole name="admin">admin角色登录显示此内容</@shiro.hasRole><br/>
        <@shiro.hasRole name="normal">normal角色登录显示此内容</@shiro.hasRole><br/>

        <@shiro.hasAnyRoles name="manager,admin">manager或admin角色用户登录显示此内容</@shiro.hasAnyRoles><br/>
        <@shiro.principal/>-显示当前登录用户名<br/><br/>

        <@shiro.hasPermission name="add">add权限用户显示此内容</@shiro.hasPermission><br/>
        <@shiro.hasPermission name="user:query">user:query权限用户显示此内容</@shiro.hasPermission><br/>
        <@shiro.lacksPermission name="user:query">不具有user:query权限用户显示此内容<br/></@shiro.lacksPermission><br/>
        <@shiro.lacksPermission name="user:edit">不具有user:edit权限用户显示此内容<br/></@shiro.lacksPermission><br/>
        <@shiro.hasPermission name="user:edit">user:edit权限用户显示此内容</@shiro.hasPermission><br/>

        <br/>所有用户列表：<br/>
        <ul>
            <#list userList as user>
                <li>用户名：${user.username}---密码：${user.password}---<a href="${context.contextPath}/user/edit/${user.id}">修改用户</a></li>
            </#list>
        </ul>
        
        <br/><br/><br/>
        <button>向页面发送 HTTP POST 请求，并获得返回的结果</button>
    </body>
    <script>
$(document).ready(function(){
  $("button").click(function(){
  
  
   $.ajax({
    url:'http://www.ssoclient.com:8989/sso-client/userGet',
    type:'get', //GET
    //async:true,    //或false,是否异步
	contentType: 'application/json',
    timeout:5000,    //超时时间
    //dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    beforeSend:function(xhr){
        console.log(xhr)
        console.log('发送前')
    },
    success:function(data,textStatus,jqXHR){
        console.log(data)
        console.log(textStatus)
        console.log(jqXHR)
    },
    error:function(xhr,textStatus){
        console.log('错误')
        console.log(xhr.getAllResponseHeaders())
        console.log(textStatus)
    },
    complete:function(){
        console.log('结束')
    }
})
	
	
  });
});
</script>
</html>
