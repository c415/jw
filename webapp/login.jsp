<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>欢迎登录教务管理系统</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 引入bootstrap -->
  <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
  <!-- 引入JQuery  bootstrap.js-->
  <script src="/js/jquery-3.2.1.min.js"></script>
  <script src="/js/bootstrap.min.js"></script>
  <style type="text/css">
    body{
      background: url(images/bg.jpg) repeat;
    }
    #login-box {
      /*border:1px solid #F00;*/
      padding: 35px;
      border-radius:15px;
      background: #2aabd2;
      color: #fff;
    }

    #label{
      text-align: center;
    }

  </style>
</head>
<body>
<div class="container" id="top">
  <div class="row" style="margin-top: 280px; ">
    <div class="col-md-4"></div>
    <div class="col-md-4" id="login-box">
      <form class="form-horizontal" role="form" action="/login" id="from1" method="post">
        <div class="form-group">
          <div >
            <h2 id="label">教务管理系统</h2>
          </div>
        </div>
        <div class="form-group">
          <label for="userID" class="col-sm-3 control-label">账号：</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" id="userID" required  placeholder="请输入账号" name="username">
          </div>
        </div>
        <div class="form-group">
          <label for="password" class="col-sm-3 control-label">密码：</label>
          <div class="col-sm-9">
            <input type="password" class="form-control" id="password" required placeholder="请输入密码" name="password">
          </div>
        </div>
        <div class="form-group">

          <div class="col-sm-9 " style="margin-left: 15px;">
            <input type="checkbox" />记住密码
            <div style="text-align: center; color: #ff4652;">
              ${message}
            </div>
          </div>
          <div class=" col-sm-3 pull-right" style="margin-right: 15px;">
            <button type="submit" class="btn  btn-warning">登录</button>
          </div>
        </div>

      </form>
    </div>
    <div class="col-md-4"></div>
  </div>
</div>
</body>
</html>