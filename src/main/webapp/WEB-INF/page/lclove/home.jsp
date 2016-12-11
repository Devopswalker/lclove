<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" type="text/css" href="css/home_main.css" />
<link rel="stylesheet" type="text/css" href="css/head.css" />
<link rel="stylesheet" type="text/css" href="css/navigation.css" />
<link rel="stylesheet" type="text/css" href="css/float.css" />
<title>Home</title>
</head>
<body>
<div class="header">
	<div class="header_left">
		<img class="head_left_1" src="images/img_cat.png"/>
		<div class="head_left_2"><font color="#FF0000">恋</font><br />猫</div>
		<div class="head_left_3">成就女性恋爱的百科全书</div>
	</div>
	<div class="header_right">
		<ul>
			<li><img src="images/s_triangle.png"/><div>by LC 品爱</div></li>
			<li><img src="images/s_triangle.png"/><div>恋猫是什么?</div></li>
		</ul>
	</div>
	
	<div class="float_pic1"><img src="images/head_flower1.png"/></div>
	<div class="float_pic2"><img src="images/head_flower2.png"/></div>
	<div class="float_pic3"><img src="images/head_flower3.png"/></div>
	<div class="float_pic4"><img src="images/head_flower4.png"/></div>
	<div class="float_pic5"><img src="images/head_flower5.png"/></div>
</div>
<div class="navigation">
	<div class="pc_style">
		<ul>
			<li><div class="home_nav_icon"></div><div class="icon_name">Home</div></li>
			<li id="secondary_nav"><div  class="normal_nav">恋の喵言喵语</div><div class="icon_name">Spacial</div></li>
			<li><div  class="normal_nav">恋の恋爱经</div><div class="icon_name">Love</div></li>
			<li><div  class="normal_nav">恋の护身符</div><div class="icon_name">Body</div></li>
			<li><div  class="normal_nav">恋の好奇心</div><div class="icon_name">Research</div></li>
			<li><div  class="normal_nav">恋の梦剧场</div><div class="icon_name">Comic</div></li>
			<li><div  class="normal_nav">恋の上上签</div><div class="icon_name">Pray</div></li>
			<li><img src="images/img_omamori.png"/></li>
		</ul>
	</div>
</div>

<div class="home_main_content">
	<div class="pc_style">
		<div class="content_left">
			<div class="topic_banner">
				<div class="display_banner">
					<div class="banner_item"></div>
					<div class="banner_item"></div>
					<div class="banner_item"></div>
					<div class="banner_item"></div>
					<div class="banner_item"></div>
				</div>
				<div class="pref_banner"><img src="images/left_arrow.png"/></div>
				<div class="next_banner"><img src="images/right_arrow.png"/></div>
				<div class="banner_points">
					<div class="banner_point"></div>
					<div class="banner_point"></div>
					<div class="banner_point_on"></div>
					<div class="banner_point"></div>
					<div class="banner_point"></div>
				</div>
			</div>
			<div class="small_blank"></div>
			<div class="content_item">
				<div class="content_item_pic"><img src="images/content_pic.jpg"/></div>
				<div class="content_item_text">
					<div class="content_item_text_head"><img src="images/text_head_icon.png"/><div>LC品爱员工美丽小秘密</div></div>
					<div class="mini_blank"></div>
					<div class="separate"></div>
					<div class="content_item_text_middle">作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？作为为大家解决身体 &烦恼的LC品爱的员工都有什么变美秘密呢？</div>
					<div class="content_item_text_foot">
						<div class="date">2015.12.23</div>
						<div class="comment"><img src="images/comment.png"/><div class="sum">27</div></div>
						<div class="love"><img src="images/love.png"/><div class="sum">126</div></div>
						<div class="type">#话题特辑#</div>									
					</div>
				</div>
			</div>
			<div class="content_item">
				<div class="content_item_pic"><img src="images/content_pic.jpg"/></div>
				<div class="content_item_text">
					<div class="content_item_text_head"><img src="images/text_head_icon.png"/><div>LC品爱员工美丽小秘密</div></div>
					<div class="mini_blank"></div>
					<div class="separate"></div>
					<div class="content_item_text_middle">作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？作为为大家解决身体 &烦恼的LC品爱的员工都有什么变美秘密呢？</div>
					<div class="content_item_text_foot">
						<div class="date">2015.12.23</div>
						<div class="comment"><img src="images/comment.png"/><div class="sum">27</div></div>
						<div class="love"><img src="images/love.png"/><div class="sum">126</div></div>
						<div class="type">#话题特辑#</div>									
					</div>
				</div>
			</div>
			<div class="content_item">
				<div class="content_item_pic"><img src="images/content_pic.jpg"/></div>
				<div class="content_item_text">
					<div class="content_item_text_head"><img src="images/text_head_icon.png"/><div>LC品爱员工美丽小秘密</div></div>
					<div class="mini_blank"></div>
					<div class="separate"></div>
					<div class="content_item_text_middle">作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？作为为大家解决身体 &烦恼的LC品爱的员工都有什么变美秘密呢？</div>
					<div class="content_item_text_foot">
						<div class="date">2015.12.23</div>
						<div class="comment"><img src="images/comment.png"/><div class="sum">27</div></div>
						<div class="love"><img src="images/love.png"/><div class="sum">126</div></div>
						<div class="type">#话题特辑#</div>									
					</div>
				</div>
			</div>
			<div class="content_item">
				<div class="content_item_pic"><img src="images/content_pic.jpg"/></div>
				<div class="content_item_text">
					<div class="content_item_text_head"><img src="images/text_head_icon.png"/><div>LC品爱员工美丽小秘密</div></div>
					<div class="mini_blank"></div>
					<div class="separate"></div>
					<div class="content_item_text_middle">作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？作为为大家解决身体 &烦恼的LC品爱的员工都有什么变美秘密呢？</div>
					<div class="content_item_text_foot">
						<div class="date">2015.12.23</div>
						<div class="comment"><img src="images/comment.png"/><div class="sum">27</div></div>
						<div class="love"><img src="images/love.png"/><div class="sum">126</div></div>
						<div class="type">#话题特辑#</div>									
					</div>
				</div>
			</div>
			<div class="page_row">
				<div class="pref_page"><img src="images/pre_page.png"/><div>上一页</div></div>
				<div class="page_indexs">
					<div class="page_index pageon">1</div>
					<div class="page_sep">|</div>
					<div class="page_index">2</div>
					<div class="page_sep">|</div>
					<div class="page_index">3</div>
					<div class="page_sep">|</div>
					<div class="page_index">4</div>
					<div class="page_sep">|</div>
					<div class="page_index">5</div>
					<div class="page_sep">|</div>
					<div class="page_index">6</div>
				</div>
				<div class="next_page"><div>下一页</div><img src="images/next_page.png"/></div>			
			</div>	
		</div>
				
		<div class="content_right">
			<div class="seartch_area">
				<input class="search_bar" placeholder="搜索关键字"/>
				<div class="searchButton"></div>
			</div>
			<div class="small_blank"></div>
			<div class="recommend">
				<div class="recommend_item"><img src="images/recoommend1.png"/><div>微电影系列<br />Vol.3</div></div>
				<div class="small_blank"></div>
				<div class="recommend_item"><img src="images/recoommend2.png"/><div>微电影系列<br />Vol.3</div></div>
				<div class="small_blank"></div>
				<div class="recommend_item"><img src="images/recoommend3.png"/><div>微电影系列<br />Vol.3</div></div>
			</div>
			<div class="small_blank"></div>
			<div class="hot_article">
				<div class="hot_article_head"><img src="images/hammer.png"/><div>热文TOP</div></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article1.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">乘务员教你如何在飞机上啪啪啪</div>
						<div class="hot_article_readed">32,574浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
				<div class="separate_small"></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article2.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">日本调查：高个普通人和矮个帅哥，选哪个？</div>
						<div class="hot_article_readed">29,620浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
				<div class="separate_small"></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article3.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">《哈利波特》里最浪漫的11个瞬间</div>
						<div class="hot_article_readed">27,868浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
				<div class="separate_small"></div>
				<div class="hot_article_head"><img src="images/hammer.png"/><div>热文TOP</div></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article1.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">乘务员教你如何在飞机上啪啪啪</div>
						<div class="hot_article_readed">32,574浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
				<div class="separate_small"></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article2.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">日本调查：高个普通人和矮个帅哥，选哪个？</div>
						<div class="hot_article_readed">29,620浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
				<div class="separate_small"></div>
				<div class="hot_article_item">
					<div class="hot_article_pic"><img src="images/article3.jpg"/></div>
					<div class="hot_article_content">
						<div class="hot_article_desc">《哈利波特》里最浪漫的11个瞬间</div>
						<div class="hot_article_readed">27,868浏览</div>
					</div>
				</div>
				<div class="mini_blank"></div>
			</div>
		</div>
		<div class="float_pic8"><img src="images/left_flower.png"/></div>
		<div class="float_pic9"><img src="images/right_flower.png"/></div>
		<div class="float_pic10"><img src="images/left_smoke.png"/></div>
		<div class="float_pic11"><img src="images/right_smoke.png"/></div>
	</div>
	<div class="float_pic6"><img src="images/left_corner.png"/></div>
	<div class="float_pic7"><img src="images/right_corner.png"/></div>
</div>
<div class="clear"></div>
<div class="footer">
	<div class="pc_style">
		<div>Copyright @ LCLOVE COSMETIC. All Rights Reserved</div>
	</div>
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/lc.js"></script>
</body>
</html>
