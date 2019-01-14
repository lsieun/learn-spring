# JavaScript in Thymeleaf

```html
<script language='javascript' th:inline="javascript">

    var basePath = /*[[${#httpServletRequest.getScheme() + "://" + #httpServletRequest.getServerName() + ":" + #httpServletRequest.getServerPort() + #httpServletRequest.getContextPath()}]]*/;

    function login(){
        var username = $("#username").val();
        var password = $("#password").val();
        var postData = {};
        postData.username = username;
        postData.password = password;
        $.ajax({
            'type': 'POST',
            'url' : basePath + '/user/page/login',
            'data' : postData,
            'success' : function(data){
                if(data.code == 200){
                    var token = data.token;
                    // web storage的查看 - 在浏览器的开发者面板中的application中查看。
                    // local storage - 本地存储的数据。 长期有效的。
                    // session storage - 会话存储的数据。 一次会话有效。
                    var localStorage = window.localStorage; // 浏览器提供的存储空间。 根据key-value存储数据。
                    localStorage.token = token;
                }else{
                    alert(data.msg);
                }
            }
        });
    }

</script>
```

