var lclove = {};
lclove.util = {};
lclove.util.basePath = '/';
lclove.util.imgPath = '/';

lclove.params = {};
lclove.params.keyword = "";
lclove.params.navtype = "";
lclove.params.pageNo = "";
lclove.params.position = "";
lclove.params.aid = "";

/* StringBuilder  */
function StringBuilder () {
     this.__asBuilder = [];
}

StringBuilder.prototype.clear = function(){
     this.__asBuilder = [];
}

StringBuilder.prototype.append = function(){
     Array.prototype.push.apply(this.__asBuilder,arguments);
     return this;
}

StringBuilder.prototype.toString = function(){
     return this.__asBuilder.join("");    
}
/* StringBuilder  */

/* json对象根据某个字段排序 */
$.extend({
	getSortFun: function(order, sortBy){
		var ordAlpah = (order == 'asc') ? '>' : '<';
		var sortFun = new Function('a', 'b', 'return a.' + sortBy + ordAlpah + 'b.' + sortBy + '?1:-1');
		return sortFun;
	}
});
/* json对象根据某个字段排序 */


function navClick(index){
	var url;
	switch(index){
	case 1:
		url = "index.xhtml?"+new Date().getTime();
		break;
	case 2:
		url = "spacial.xhtml";
		break;
	case 3:
		url = "loves.xhtml";
		break;
	case 4:
		url = "amulet.xhtml";
		break;
	case 5:
		url = "research.xhtml";
		break;
	case 6:
		url = "comic.xhtml";
		break;
	case 7:
		url = "pray.xhtml";
		break;
	default:
		break;
	}
	if(url!=null){
		document.location.href = lclove.util.basePath + url;
	}
}

/* Ajax  */
$.extend({
	getRequest : function(url, data, async, type, dataType, successfn, errorfn) {
        async = (async==null || async=="" || typeof(async)=="undefined")? "true" : async;
        type = (type==null || type=="" || typeof(type)=="undefined")? "post" : type;
        dataType = (dataType==null || dataType=="" || typeof(dataType)=="undefined")? "json" : dataType;
        data = (data==null || data=="" || typeof(data)=="undefined")? {} : data;
        $.ajax({
            type: type,
            async: async,
            data: data,
            url: url,
            dataType: dataType,
            success: function(r){
                successfn(r);
            },
            error: function(e){
            	errorfn(e);
            }
        });
    },
    getData: function(url, data, async, type, dataType, isJsonType, fn_init){
	   	 var successfn = function(result){
	   		if(result.success){
	   			var resultData = null;
	   			if(isJsonType){
	   				//resultData = result.data;	
	   				resultData = eval('(' + result.data + ')');
	   			}else{
	   				resultData = result;
	   			}
	   			fn_init(resultData);
	   		 }else{
				//$.showPopupMessage({type:"warning",message: result.msg});
	   			 alert(result.msg);
	   		}
	   	 };
	   	 var errorfn = function(jqXHR, textStatus, errorThrown){
	   	 };
	   	 $.getRequest(url, data, async, type, dataType, successfn, errorfn);
    }
});
/* Ajax  */

/*refresh page*/
function refreshPage(pageUrl){
	if(typeof pageUrl != "undefined"){
		document.location.href = pageUrl;
	}else{
		var loc="" + document.location.href, idx = loc.indexOf("refresh"), anidx = loc.indexOf("#"), anchor="";
		if(anidx > 0){
			anchor = loc.substring(anidx);
			loc = loc.substring(0, anidx);
		}
		if(idx > 0){
			loc = loc.substring(0, idx - 1);
		}else{
			idx = loc.indexOf("?");
			if(idx > 0){
				loc=loc+"&refresh=1";
			}else{
				loc=loc+"?refresh=1";
			}
		}
		loc += anchor;
		document.location.href = loc;
	}
}
/*refresh page*/


/* to top  */
$.extend({  
	backToTop: function(options){
		var defaults = {
			text : '',
			autoShow: true,
			timeEffect: 500,
			effectScroll: 'linear',
			appearMethod : 'slide'
		};

		var opts = $.extend({}, defaults, options);
		
		var init = function(){
			var divBack = $('<a/>',{id : 'BackToTop',href : '#body', html: '<span>'+opts.text+'</span>'}).appendTo('body');
			divBack.css({"bottom": "50px", "right":"0px", "position":"fixed"});
			
			$(window).scroll(function(){
				if($(this).scrollTop() != 0) {
					switch (opts.appearMethod) {
						case 'fade' : divBack.fadeIn('fast'); break;
						case 'slide' : divBack.slideDown('fast'); break;
						default : divBack.show();	
					}
				}
				else {
					switch (opts.appearMethod) {
						case 'fade' : divBack.fadeOut('fast'); break;
						case 'slide' : divBack.slideUp('fast'); break;
						default : divBack.hide();	
					}
				}
			});
			
			$('#BackToTop').click(function(e) {
				e.preventDefault();
				$('body,html').animate({scrollTop:0},opts.timeEffect,opts.effectScroll);
			});
		}
		
		return init();
	}
});
/* to top  */

/* Header Menu */
(function($){
    var eMenu = function(options,object) {
        var opts = $.extend({}, $.fn.topMenu.defaults, options);
        var instance = object;
        var renderHeader = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='header'>");
            sbHtml.append("    <div class='header_left'>");
            sbHtml.append("        <img class='head_left_1' src='" + lclove.util.imgPath + "images/img_cat.png' />");
            sbHtml.append("        <div class='head_left_2'><font color='#FF0000'>恋</font><br />猫</div>");
            sbHtml.append("        <div class='head_left_3'>成就女性恋爱的百科全书</div>");
            sbHtml.append("    </div>");
            sbHtml.append("    <div class='header_right'>");
            sbHtml.append("        <ul>");
            sbHtml.append("            <li><img src='" + lclove.util.imgPath + "images/s_triangle.png'/><div>by LC 品爱</div></li>");
            sbHtml.append("            <li><img src='" + lclove.util.imgPath + "images/s_triangle.png'/><div>恋猫是什么?</div></li>");
            sbHtml.append("        </ul>");
            sbHtml.append("    </div>");
            sbHtml.append("    <div class='float_pic1'><img src='" + lclove.util.imgPath + "images/head_flower1.png'/></div>");
            sbHtml.append("    <div class='float_pic2'><img src='" + lclove.util.imgPath + "images/head_flower2.png'/></div>");
            sbHtml.append("    <div class='float_pic3'><img src='" + lclove.util.imgPath + "images/head_flower3.png'/></div>");
            sbHtml.append("    <div class='float_pic4'><img src='" + lclove.util.imgPath + "images/head_flower4.png'/></div>");
            sbHtml.append("    <div class='float_pic5'><img src='" + lclove.util.imgPath + "images/head_flower5.png'/></div>");
            sbHtml.append("</div>");
            
            sbHtml.append("<div class='navigation'>");
            sbHtml.append("    <div class='pc_style'>");
            sbHtml.append("        <ul>");
            sbHtml.append("            <li><div class='home_nav_icon' onClick='navClick(1)'></div><div class='icon_name' onClick='navClick(1)'>Home</div></li>");
            sbHtml.append("            <li id='secondary_nav'><div  class='normal_nav' onClick='navClick(2)'>恋の喵言喵语</div><div class='icon_name' onClick='navClick(2)'>Spacial</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(3)'>恋の恋爱经</div><div class='icon_name' onClick='navClick(3)'>Love</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(4)'>恋の护身符</div><div class='icon_name' onClick='navClick(4)'>Body</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(5)'>恋の好奇心</div><div class='icon_name' onClick='navClick(5)'>Research</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(6)'>恋の梦剧场</div><div class='icon_name' onClick='navClick(6)'>Comic</div></li>");
            sbHtml.append("            <li><img src='" + lclove.util.imgPath + "images/img_omamori.png'/></li>");
            sbHtml.append("        </ul>");
            sbHtml.append("    </div>");
            sbHtml.append("</div>");
            return sbHtml.toString();
        };
        
        var init = function(){
            $(instance).append($(renderHeader()));
            $(".head_left_1").on("click", function(){
            	 window.location.href = lclove.util.basePath + "index.xhtml";
            });
            $(".header_right").on("click", function(){
            	 window.open("http://www.lclovecosmetic.com/"); 
            });
        };

        return init();
    }

    $.fn.topMenu = function(options) {
        return this.each(function () {
            return eMenu(options, $(this));
        });
    };
    
    $.fn.topMenu.defaults = {};

})(jQuery);
/* Header Menu */

/* comm search */
$(function(){
    var eSearchByType = function(options,object) {
        var opts = $.extend({}, $.fn.searchByType.defaults, options);
        var instance = object;

        //筛选
        var render = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<input class='search_bar' placeholder='搜索关键字' type='text' value='" + lclove.params.keyword + "'/>");
            sbHtml.append("<div class='searchButton'></div>");
            $(instance).append($(sbHtml.toString()));
            $(".searchButton").on("click", function(){
            	//筛选条件
            	var keyword = $(".search_bar").val();
            	var filterUrl =  lclove.util.basePath + "ajax/getArticleList.xhtml?keyword=" + keyword+"&navtype="+lclove.params.navtype;
            	refreshPage(filterUrl);
            });
        };        
        return render();
    };

    $.fn.searchByType = function(options) {
        return this.each(function () {
            return eSearchByType(options, $(this));
        });
    };
    $.fn.searchByType.defaults = {};
});
/* comm search */

/* Recommand Show */
$(function(){
    var eRecommandShow = function(options,object) {
        var opts = $.extend({}, $.fn.recommandShow.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getRecommand.xhtml?position=" + lclove.params.position;
        var imgTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='recommend_item'>");
            sbHtml.append("  <a href='" + data.recommendUrl + "'><img width='301' height='116' class='radius-small' src='" + data.imgUrl + "'/></a>");
            sbHtml.append("  <div>"+ data.description +"<br />"+ data.seq +"</div>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='small_blank'></div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='recommend_item'><img width='301' height='116' class='radius-small' src='"+ lclove.util.imgPath + "images/recoommend1.png'/><div>微电影系列<br />Vol.1</div></div>");
            sbHtml.append("<div class='small_blank'></div>");
            sbHtml.append("<div class='recommend_item'><img width='301' height='116' class='radius-small' src='"+ lclove.util.imgPath + "images/recoommend2.png'/><div>微电影系列<br />Vol.2</div></div>");
            sbHtml.append("<div class='small_blank'></div>");
            sbHtml.append("<div class='recommend_item'><img width='301' height='116' class='radius-small' src='"+ lclove.util.imgPath + "images/recoommend3.png'/><div>微电影系列<br />Vol.3</div></div>");
            sbHtml.append("<div class='small_blank'></div>");
            return $(sbHtml.toString());
        };

        var loadRecommand = function () {
            var sbHtml = new StringBuilder();
            sbHtml.append(" <div class='recommendList'>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
        };
        
        var fillData = function(data){
        	if(data != null && data != ""){
        		$.each(data, function(index, item){
            		$(".recommendList").append(imgTemplate(item));
            	});
        	}else{
        		$(".recommendList").append(defaultTemplate());
        	}
        };
        loadRecommand();
        $.getData(url, null, true, "POST", "json", true, fillData);
    };

    $.fn.recommandShow = function(options) {
        return this.each(function () {
            return eRecommandShow(options, $(this));
        });
    };
    $.fn.recommandShow.defaults = {};
});
/* Recommand Show */

/* TOP 6 */
$(function(){
    var eTopShow = function(options,object) {
        var opts = $.extend({}, $.fn.topShow.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getHotArticle.xhtml?navtype="+lclove.params.navtype;
        var itemTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='hot_article_item'>");
            sbHtml.append("  <div class='hot_article_pic'><a href='"+ lclove.util.basePath + "detail.xhtml?aid="+data.id+"'><img width='100' height='100' class='radius-small' src='" + data.thumbnail + "'/></a></div>");
            sbHtml.append("  <div class='hot_article_content'>");
            sbHtml.append("    <div class='hot_article_desc'><a href='"+ lclove.util.basePath + "detail.xhtml?aid="+data.id+"'>"+ data.title +"</a></div>");
            sbHtml.append("    <div class='hot_article_readed'> "+ data.scanNum +" 浏览</div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='mini_blank'></div>");
            sbHtml.append("<div class='separate_small'></div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='hot_article_item'>");
            sbHtml.append("  <div class='hot_article_pic'><a href='#'><img width='100' height='100' class='radius-small' src='" + lclove.util.imgPath + "images/article1.jpg'/></a></div>");
            sbHtml.append("  <div class='hot_article_content'>");
            sbHtml.append("    <div class='hot_article_desc'><a href='#'>乘务员教你如何在飞机上啪啪啪</a></div>");
            sbHtml.append("    <div class='hot_article_readed'> 99999 浏览</div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='mini_blank'></div>");
            sbHtml.append("<div class='separate_small'></div>");
            return $(sbHtml.toString());
        };

        var loadItemList = function () {
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='hot_article_head'><img src='"+ lclove.util.imgPath + "images/hammer.png'/><div>热文TOP</div>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
        };
        
        var fillData = function(data){
        	if(data != null && data != ""){
        		$.each(data, function(index, item){
            		$(".hot_article").append(itemTemplate(item));
            	});
        	}else{
        		for (var i=0; i<6 ; i++)
        		{
        			$(".hot_article").append(defaultTemplate());
        		}
        		
        	}
        };
        loadItemList();
        $.getData(url, null, true, "POST", "json", true, fillData);
    };

    $.fn.topShow = function(options) {
        return this.each(function () {
            return eTopShow(options, $(this));
        });
    };
    $.fn.topShow.defaults = {};
});
/* TOP 6 */

/* load Banner */
$(function(){
    var eLoadBanner = function(options,object) {
        var opts = $.extend({}, $.fn.loadBanner.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getRecommand.xhtml?position=1";
        var imgTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='item'>");
            sbHtml.append("  <a target='_blank' href='" + data.recommendUrl + "'><img src='" + data.imgUrl + "'/></a>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='item'>");
            sbHtml.append("	<a href='http://www.baidu.com'><img src='" + lclove.util.imgPath + "images/banner.png'/></a>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='item'>");
            sbHtml.append("	<a href='http://www.baidu.com'><img src='" + lclove.util.imgPath + "images/banner.png'/></a>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };

        var instanceObject = function () {
            var sbHtml = new StringBuilder();
            sbHtml.append(" <div class='carousel'>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
        };
        
        var fillData = function(data){
        	var objdiv = $("<div/>").addClass("carousel");
        	$(instance).append(objdiv);
        	if(data != null && data != ""){
        		$.each(data, function(index, item){
            		$(".carousel").append(imgTemplate(item));
            	});
        	}else{
        		$(".carousel").append(defaultTemplate());
        	}
        	(function() {
        		var hm = document.createElement("script");
        		hm.src = lclove.util.imgPath + "js/pintuer.js";
        		var s = document.getElementsByTagName("script")[0]; 
        		s.parentNode.insertBefore(hm, s);
        	})();
        };
        //instanceObject();
        //fillData();
        $.getData(url, null, true, "POST", "json", true, fillData);
    };

    $.fn.loadBanner = function(options) {
        return this.each(function () {
            return eLoadBanner(options, $(this));
        });
    };
    $.fn.loadBanner.defaults = {};
});
/* load Banner */


/* Content List */
$(function(){
    var eContentList = function(options,object) {
        var opts = $.extend({}, $.fn.contentList.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getArticleList.xhtml?navtype="+lclove.params.navtype;
        var itemTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='content_item'>");
            sbHtml.append("<div class='content_item_pic'>");
            sbHtml.append("  <a class='thumbnail' href='"+ lclove.util.basePath + "detail.xhtml?aid="+data.id+"'><img width='148' height='148' class='img-border radius-small' src='" + data.thumbnail + "'/></a>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='content_item_text'>");
            sbHtml.append("  <div class='content_item_text_head'>");
            sbHtml.append("    <a href='"+ lclove.util.basePath + "detail.xhtml?aid="+data.id+"'><img src='" + lclove.util.imgPath + "images/text_head_icon.png'/><div>"+ data.title +"</div></a>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='separate'></div>");
            sbHtml.append("  <div class='content_item_text_middle'> "+ data.description +"</div>");
            sbHtml.append("  <div class='content_item_text_foot'>");
            sbHtml.append("    <div class='date'>"+ data.pubDate +"</div>");
            sbHtml.append("    <div class='comment'><img src='" + lclove.util.imgPath + "images/comment.png'/><div class='sum'>"+ data.scanNum +"</div></div>");
            sbHtml.append("    <div class='love'><img src='" + lclove.util.imgPath + "images/love.png'/><div class='sum'>"+ data.likeNum +"</div></div>");
            sbHtml.append("    <div class='type'> "+ data.topic +" </div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='content_item'>");
            sbHtml.append("<div class='content_item_pic'>");
            sbHtml.append("  <a href='"+ lclove.util.basePath + "detail.xhtml?aid=1'><img width='148' height='148' class='img-border radius-small' src='" + lclove.util.imgPath + "images/content_pic.jpg'/></a>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='content_item_text'>");
            sbHtml.append("  <div class='content_item_text_head'>");
            sbHtml.append("    <a href='"+ lclove.util.basePath + "detail.xhtml?aid=1'><img src='" + lclove.util.imgPath + "images/text_head_icon.png'/><div>LC品爱员工美丽小秘密</div></a>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='separate'></div>");
            sbHtml.append("  <div class='content_item_text_middle'>作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？</div>");
            sbHtml.append("  <div class='content_item_text_foot'>");
            sbHtml.append("    <div class='date'>2017-01-04</div>");
            sbHtml.append("    <div class='comment'><img src='" + lclove.util.imgPath + "images/comment.png'/><div class='sum'>666</div></div>");
            sbHtml.append("    <div class='love'><img src='" + lclove.util.imgPath + "images/love.png'/><div class='sum'>888</div></div>");
            sbHtml.append("    <div class='type'> #新年快乐# </div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var scrollEvent = function(){
            var winH = $(window).height(); //页面可视区域高度
            var scrollAdd = function(){
            	var scrollT = $(window).scrollTop(); //滚动条top 
            	var pageH = $(document.body).height();
            	var scrollUrl = lclove.util.basePath + "ajax/getArticleList.xhtml?type="+lclove.params.navtype;
                if (scrollT + winH > ($(".loadMore").offset().top + 100) && $(".loadMore").css("display") == "block" && ($(".loadMore").attr("cnum") < ($(".loadMore").attr("tnum") -1))) {
					$(".loadMore").css("display","none");
					scrollUrl += "&pageNo=" + (parseInt($(".loadMore").attr("cnum")) + 1);
                	$.getData(scrollUrl, null, true, "POST", "json", true, appendList);
                }
            };
            //定义鼠标滚动事件
            $(window).scroll(scrollAdd);
        };
        
        var fillData = function(data){
        	if(data != null && data.articles != null && data.articles != "" ){
        		$.each(data.articles, function(index, item){
            		$(".content_list").append(itemTemplate(item));
            	});
        	}else{
        		for (var i=0; i<4 ; i++)
        		{
        			$(".content_list").append(defaultTemplate());
        		}
        	}
        };
        
        var appendList = function(data){
        	fillData(data);
            var cPageNum = data.pageInfo.currentPage;
            if(cPageNum == data.pageInfo.pageCount - 1){
            	$(".loadMore").css("display", "none");
            }else{
            	$(".loadMore").appendTo($(instance));
                $(".loadMore").attr("cnum", cPageNum).css("display", "block");
            }
        };
        
        var renderList = function(data){
        	var pageNum = data.pageInfo.currentPage;
            var totalPage = data.pageInfo.pageCount;
            
        	fillData(data);
        	
            $("<div/>").addClass("loadMore").attr({"cnum": pageNum, "tnum": totalPage}).appendTo($(instance));
            
            if(pageNum >= totalPage - 1 ){
            	$(".loadMore").css("display", "none");
            }         
            scrollEvent();
        };

        $.getData(url, null, true, "POST", "json", true, renderList);
    };

    $.fn.contentList = function(options) {
        return this.each(function () {
            return eContentList(options, $(this));
        });
    };
    $.fn.contentList.defaults = {};
});
/* Content List */

$(function(){
    var eMyContent = function(options,object) {
        var opts = $.extend({}, $.fn.ArticleContent.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getArticle.xhtml?aid=" + lclove.params.aid;
        var showContent = function (data) {
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='article_content_head'>");
            sbHtml.append("  <div class='head_left'>上一篇：</div>");
            sbHtml.append("  <div class='head_right'>下一篇：</div>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='separate'></div>");
            sbHtml.append("<div class='article_content_body'>");
            sbHtml.append("  <div class='row_1'>");
            sbHtml.append("  	<div class='row_1_left'>");
            sbHtml.append("  		<img src='"+lclove.util.imgPath+"images/text_head_icon.png'/>");
            sbHtml.append("  	</div>");
            sbHtml.append("  	<div>"+data.detail.title+"</div></div><div class='row_1_right'>"+data.detail.topic+"</div>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='row_2'>"+data.detail.pubDate+" | 小编："+data.detail.editor+"</div>");
            sbHtml.append("  <div class='row_3'>");
            sbHtml.append(data.detail.content);
            
            
            
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var initContent = function(data){
            $(".article_content").append(showContent(data));
        };
        $.getData(url, null, true, "POST", "json", true, initContent);
    };
    $.fn.ArticleContent = function(options) {
        return this.each(function () {
            return eMyContent(options, $(this));
        });
    };

    $.fn.ArticleContent.defaults = {};
});

/*  Footer  */
;(function($){
    var eFooter = function(options,object) {
        var opts = $.extend({}, $.fn.footerModule.defaults, options);
        var instance = object;

        var renderFooter = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='footer'>");
            sbHtml.append("    <div class='pc_style'>");
            sbHtml.append("       <div>Copyright © LCLOVE COSMETIC. All Rights Reserved.</div>");
            sbHtml.append("    </div>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
        };

        return renderFooter();
    }

    $.fn.footerModule = function(options) {
        return this.each(function () {
            return eFooter(options, $(this));
        });
    };
        
    $.fn.footerModule.defaults = {};

})(jQuery);
/*  Footer  */

