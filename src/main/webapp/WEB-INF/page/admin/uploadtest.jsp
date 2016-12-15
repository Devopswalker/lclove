<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
  <script src="/js/jquery-1.8.3.min.js"></script>
  <script src="/bootstrap/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-hover">
  <caption>已经上传的图片</caption>
  <thead>
  <tr>
    <th>名称</th>
    <th>Position</th>
    <th>URL</th>
    <th>Description</th>
  </tr>
  </thead>
  <tbody>
  <tr>
    <td>Tanmay</td>
    <td>Bangalore</td>
    <td>560001</td>
    <td>560001</td>
  </tr>
  </tbody>
</table>
<form class="form-horizontal" role="form" action="/Lclove/uploadImage.do"
      method="post" enctype="multipart/form-data">
  <div class="form-group">
    <label for="position" class="col-sm-2 control-label">Position</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="position" name="position"
             placeholder="请输入位置">
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Desction</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="description" name="description"
             placeholder="请输描述">
    </div>
  </div>
  <div class="form-group">
    <label for="url" class="col-sm-2 control-label">URL</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" id="url" name="url"
             placeholder="请输入连接地址">
    </div>
  </div>
  <div class="form-group">
    <label class="col-sm-2 control-label" for="file">上传图片</label>
    <div class="col-sm-10">
      <input type="file" id="file" name="file">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default">Commit</button>
    </div>
  </div>
</form>

<%--<form action="/Lclove/uploadImage.do" method="post" enctype="multipart/form-data">--%>
  <%--<input type="file" name="file" />--%>
  <%--<input type="text" name="position" />--%>
  <%--<input type="text" name="description" />--%>
  <%--<input type="submit" value="upload" /></form>--%>


</body>
</html> 