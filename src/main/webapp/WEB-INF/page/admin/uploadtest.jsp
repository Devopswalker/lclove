<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
  <script src="/js/jquery-1.8.3.min.js"></script>
  <script src="/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<form action="/Lclove/uploadImage.do" method="post" enctype="multipart/form-data">
  <input type="file" name="file" />
  <input type="text" name="image.position" />
  <input type="text" name="image.description" /><input type="submit" value="upload" /></form>

<form action="/Lclove/test" method="post">
  <input type="text" name="image.position" />
  <input type="text" name="image.description" /><input type="submit" value="ok" /></form>
</body>
</html> 