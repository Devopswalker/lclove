var lclove = {};
lclove.util = {};
lclove.util.basePath = '/';
lclove.util.imgPath = '/';

lclove.params = {};
lclove.params.keyword = "";
lclove.params.navtype = "";
lclove.params.isSurvey = false;
lclove.params.pageNo = "";
lclove.params.position = "";
lclove.params.aid = "";
lclove.params.sortType = "";
lclove.params.surveyId = "";


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
		url = "special.xhtml";
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

            sbHtml.append("            <li><img src='" + lclove.util.imgPath + "images/s_triangle.png'/><div><a href='" + lclove.util.basePath + "brand.xhtml' target='_blank'>by LC 品爱</a></div></li>");
            sbHtml.append("            <li><img src='" + lclove.util.imgPath + "images/s_triangle.png'/><div><a href='" + lclove.util.basePath + "lianmao.xhtml' target='_blank'>恋猫是什么?</a></div></li>");
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
            sbHtml.append("            <li id='secondary_nav'><div  class='normal_nav' onClick='navClick(2)'>恋の喵言喵语</div><div class='icon_name' onClick='navClick(2)'>Special</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(4)'>恋の护身符</div><div class='icon_name' onClick='navClick(4)'>Body</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(3)'>恋の恋爱经</div><div class='icon_name' onClick='navClick(3)'>Love</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(6)'>恋の梦剧场</div><div class='icon_name' onClick='navClick(6)'>Comic</div></li>");
            sbHtml.append("            <li><div class='normal_nav' onClick='navClick(5)'>恋の好奇心</div><div class='icon_name' onClick='navClick(5)'>Research</div></li>");
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
            //$(".header_right").on("click", function(){
            //	 window.open(lclove.util.basePath + "brand.xhtml");
            //});
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
            	var tourl = "index.xhtml";
            	var vnav = lclove.params.navtype;
                var isSurvey = lclove.params.isSurvey;
                if (isSurvey == true){
                    tourl = "research.xhtml";
                }else if(vnav == 1){
            		tourl = "special.xhtml";
            	}else if(vnav == 2){
                    tourl = "loves.xhtml";
            	}else if(vnav == 3){
            		tourl = "amulet.xhtml";
            	}else if(vnav == 4){
            		tourl = "comic.xhtml";
            	}
            	
            	var filterUrl =  lclove.util.basePath + tourl +"?keyword=" + keyword+"&navtype="+vnav;
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
            sbHtml.append("  <div class='hot_article_pic'><a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=2&aid="+data.id+"'><img width='100' height='100' class='radius-small' src='" + data.thumbnail + "'/></a></div>");
            sbHtml.append("  <div class='hot_article_content'>");
            sbHtml.append("    <div class='hot_article_desc'><a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=2&aid="+data.id+"'>"+ data.title +"</a></div>");
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
        var url = lclove.util.basePath + "ajax/getArticleList.xhtml?navtype="+lclove.params.navtype +"&keyword=" + lclove.params.keyword;
        var itemTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='content_item'>");
            sbHtml.append("<div class='content_item_pic'>");
            sbHtml.append("  <a class='thumbnail' href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&aid="+data.id+"'><img width='148' height='148' class='img-border radius-small' src='" + data.thumbnail + "'/></a>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='content_item_text'>");
            sbHtml.append("  <div class='content_item_text_head'>");
            sbHtml.append("    <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&aid="+data.id+"'><img src='" + lclove.util.imgPath + "images/text_head_icon.png'/><div>"+ data.title +"</div></a>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='separate'></div>");
            sbHtml.append("  <div class='content_item_text_middle'> "+ data.description +"</div>");
            sbHtml.append("  <div class='content_item_text_foot'>");
            sbHtml.append("    <div class='date'>"+ data.pubDate +"</div>");
            sbHtml.append("    <div class='comment'><img src='" + lclove.util.imgPath + "images/comment.png'/><div class='sum'>"+ data.commentNum +"</div></div>");
            sbHtml.append("    <div class='love'><img width='20' height='20' class='radius-small' src='" + lclove.util.imgPath + "images/love.png'/><div class='sum'>"+ data.likeNum +"</div></div>");
            sbHtml.append("    <div class='type'> "+ data.topic +" </div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            //var sbHtml = new StringBuilder();
            //sbHtml.append("<div class='content_item'>");
            //sbHtml.append("<div class='content_item_pic'>");
            //sbHtml.append("  <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&aid=1'><img width='148' height='148' class='img-border radius-small' src='" + lclove.util.imgPath + "images/content_pic.jpg'/></a>");
            //sbHtml.append("</div>");
            //sbHtml.append("<div class='content_item_text'>");
            //sbHtml.append("  <div class='content_item_text_head'>");
            //sbHtml.append("    <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&aid=1'><img src='" + lclove.util.imgPath + "images/text_head_icon.png'/><div>LC品爱员工美丽小秘密</div></a>");
            //sbHtml.append("  </div>");
            //sbHtml.append("  <div class='mini_blank'></div>");
            //sbHtml.append("  <div class='separate'></div>");
            //sbHtml.append("  <div class='content_item_text_middle'>作为为大家解决身体&烦恼的LC品爱的员工，来看 看他们都有什么变美秘密呢？</div>");
            //sbHtml.append("  <div class='content_item_text_foot'>");
            //sbHtml.append("    <div class='date'>2017-01-04</div>");
            //sbHtml.append("    <div class='comment'><img src='" + lclove.util.imgPath + "images/comment.png'/><div class='sum'>666</div></div>");
            //sbHtml.append("    <div class='love'><img src='" + lclove.util.imgPath + "images/love.png'/><div class='sum'>888</div></div>");
            //sbHtml.append("    <div class='type'> #新年快乐# </div>");
            //sbHtml.append("  </div>");
            //sbHtml.append("</div>");
            //sbHtml.append("</div>");
            var sbHtml = new StringBuilder();
            //sbHtml.append('<div class="no_data_container"><p>沒有查找結果，請重新輸入搜索條件</p></div>');
            sbHtml.append('<img class="no_data_img" src="/images/NO_DATA.jpg">');

            return $(sbHtml.toString());
        };
        
        var scrollEvent = function(){
            var winH = $(window).height(); //页面可视区域高度
            var scrollAdd = function(){
            	var scrollT = $(window).scrollTop(); //滚动条top 
            	var pageH = $(document.body).height();
            	var scrollUrl = lclove.util.basePath + "ajax/getArticleList.xhtml?type="+lclove.params.navtype + "&keyword=" + lclove.params.keyword;
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
        		//for (var i=0; i<4 ; i++)
        		//{
        			$(".content_list").append(defaultTemplate());
        		//}
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
        var url = lclove.util.basePath + "ajax/getArticle.xhtml?sortType="+lclove.params.sortType+"&aid=" + lclove.params.aid +"&navtype="+lclove.params.navtype;
        var showContent = function (data) {
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='article_content_head'>");

            if (data.lastArticle != null){
                var lastUrl = lclove.util.basePath + "detail.xhtml?sortType="+lclove.params.sortType+"&aid=" + data.lastArticle.id +"&navtype="+lclove.params.navtype;
                sbHtml.append("  <div class='head_left'><a href='"+lastUrl+"'>上一篇："+data.lastArticle.title+"</a></div>");
            }else{
                //sbHtml.append("  <div class='head_left'>上一篇："+data.lastArticle.title+"</div>");
            }
            if (data.nextArticle != null){
                var nextUrl = lclove.util.basePath + "detail.xhtml?sortType="+lclove.params.sortType+"&aid=" + data.nextArticle.id +"&navtype="+lclove.params.navtype;
                sbHtml.append("  <div class='head_right'><a href='"+nextUrl+"'>下一篇："+data.nextArticle.title+"</a></div>");
            }else{
                //sbHtml.append("  <div class='head_right'>下一篇："+data.nextArticle.title+"</div>");
            }
            sbHtml.append("</div>");
            sbHtml.append("<div class='article_separate'></div>");
            sbHtml.append("<div class='article_content_body'>");
            sbHtml.append("  <div class='row_1'>");
            sbHtml.append("  	<div class='row_1_left'>");
            sbHtml.append("  		<img src='"+lclove.util.imgPath+"images/text_head_icon.png'/>");
            sbHtml.append("  	<div>"+data.detail.title+"</div></div>");
            sbHtml.append("  	<div class='row_1_right'>"+data.detail.topic+"</div>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='row_2'>"+data.detail.pubDate+" | 小编："+data.detail.editor+"</div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='row_3'>");
            sbHtml.append(data.detail.content);
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='taplike'>");
            sbHtml.append("    <a calss='taptolike' id='taptolike' ref='javascript:void(0)' onclick='tapToLike("+data.detail.id+");' ><img width='20' height='20' class='radius-small' src='" + lclove.util.imgPath + "images/love.png'/><div class='likenums' id='likenums'>&nbsp;"+ data.detail.likeNum +"</div></div>");
            sbHtml.append("    </a>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
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

function tapToLike(sid){
	var url = lclove.util.basePath + "ajax/ontTapToLike.xhtml";
	var data = {};
	data.sid = sid;
	$.getData(url, data, true, "POST", "json", true, callBackAddLike);
}

function callBackAddLike(result){
	if(result.article != null){
		var curNum = result.article.likeNum;
		$("#likenums").html("&nbsp;"+curNum);
	}
	
	
}

/*  Comment  */
$(function(){
    var eComment = function(options,object) {
        var opts = $.extend({}, $.fn.renderComment.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getComments.xhtml?aid=" + lclove.params.aid;
        var showComment = function (data) {
            var sbHtml = new StringBuilder();
            if(data.comments.length > 0 ){
                 sbHtml.append("<div class='comments_area'>");
                 sbHtml.append("<div class='comments_title'><img src='"+lclove.util.imgPath+"images/comment.png'/><div> "+data.comments.length+" 条评论</div></div>");
                 $.each(data.comments, function(index, item){
                 	sbHtml.append("    <div class='one_comment'>");
                 	sbHtml.append("        <div class='name' id='comment_name_"+item.id+"'> "+item.nickName+" </div>");
                 	sbHtml.append("        <div class='colon'>：</div>");
                 	sbHtml.append("        <div class='comment_content' id='comment_content_"+item.id+"'> "+item.content+" </div>");
                 	sbHtml.append("        <a ref='javascript:void(0)' onclick='replyComment("+item.id+");' >");
                 	sbHtml.append("            <div class='reply_tag'>回复</div>");
                 	sbHtml.append("        </a>");
                 	sbHtml.append("        <a ref='javascript:void(0)' onclick='doPraiseOpration("+item.id+");' >");
                 	sbHtml.append("            <img class='good_img' src='"+lclove.util.imgPath+"images/love.png'/> <div class='good_count' id='good_count"+item.id+"'> "+item.upNum+" </div>");
                 	sbHtml.append("        </a>");
                 	sbHtml.append("    </div>");
                 	if(item.replyId != null){
                 		sbHtml.append("     <div class='one_comment_r'>");
                     	sbHtml.append("        <div class='name_r' id='comment_r_name_"+item.replyId+"'> "+item.replyName+" </div>");
                     	sbHtml.append("        <div class='colon_r'>：</div>");
                     	sbHtml.append("        <div class='comment_content_r' id='comment_r_content_"+item.replyId+"'> "+item.replyContent+" </div>");
                 		sbHtml.append("    </div>");
                 	}
                 	sbHtml.append("    <div class='solid_separate'></div>");
                 });
                 sbHtml.append("</div>");
            }else{
            	sbHtml.append("<div class='_blanklist'>");
                sbHtml.append("  <img style='margin-left:220px;' src='"+lclove.util.imgPath+"images/head_flower1.png'/><div style='margin-left:10px;'>暂无评论</div><img style='margin-left:10px;' src='"+lclove.util.imgPath+"images/head_flower1.png'/>");
                sbHtml.append("</div>");
            }
            sbHtml.append("<div class='comment_submit'>");
            sbHtml.append("  <div class='publish'>发表评论</div>");
            sbHtml.append("  <form name='commentsForm' class='form-inline' method='post'");
            sbHtml.append("     <div class='form-group'>");
            sbHtml.append("       <div class='field'>");
            sbHtml.append("         &nbsp;昵称<font color='red'>*</font>&nbsp;<input type='text' class='input' id='nickName' name='nickName' maxlength='12' style='width:240px;' size='30' />");
            sbHtml.append("         邮箱<font color='red'>*</font>&nbsp;<input type='text' class='input' id='email' name='email' maxlength='36' style='width:240px;' size='48' />");
            sbHtml.append("       </div>");
            sbHtml.append("     </div>");
            sbHtml.append("     <div class='form-group' style='margin-top: 10px;margin-bottom: 10px;'>");
            sbHtml.append("       <textarea class='input' rows='4' cols='50' placeholder='写下您的评论内容' maxlength='50' id='content' name='content' ></textarea>");
            sbHtml.append("     </div>");
            sbHtml.append("     <div class='clear'></div>");
            sbHtml.append("     <div id='submitBtn' class='confirm_comment'><img src='"+lclove.util.imgPath+"images/send.png'/></div>");
            sbHtml.append("  </form>");
            sbHtml.append("</div>");
            $(instance).append($(sbHtml.toString()));
            $("#submitBtn").on("click", saveData);
        };
        //用户评论
        var saveData = function(){
        	//validate
        	var nickName = $('#nickName').val();
        	var email = $('#email').val();
        	var content = $('#content').val();
    	  	if(nickName=='' || nickName==null){
    	  		alert("请填写昵称！");
     	  		return false;
    	  	}
    	   	if(email=='' || email==null){
    	   		alert("请填写邮箱！");
    	   		return false;
    	   	}	
    	   	if(content=='' || content==null){
    	   		alert("请填写评论！");
    	   		return false;
    	   	}
            var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
            if(!search_str.test(email)){
                alert("邮件格式不正确 ! 请重新输入！");
                $('#email').focus();
                return false;
            }
        	//validate end
        	
        	var url = lclove.util.basePath + "ajax/saveComments.xhtml";
        	var data = {};
        	data.nickName = nickName;
        	data.email = email;
        	data.content = content;
        	data.articleId = lclove.params.aid;
        	$.getData(url, data, true, "POST", "json", true, callBackComments);
        };
        
      //保存回调
        var callBackComments = function(result){
			refreshPage(lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&aid="+lclove.params.aid);
        };
        
        var initComments = function(data){
            $(".detail_comment_content").append(showComment(data));
        };
        //initComments();
        $.getData(url, null, true, "POST", "json", true, initComments);
    };
   
    $.fn.renderComment = function(options) {
        return this.each(function () {
            return eComment(options, $(this));
        });
    };

    $.fn.renderComment.defaults = {};
});
/*  Comment  */


/*  doPraise  */
function doPraiseOpration(cid){
	var url = lclove.util.basePath + "ajax/onTapToPraise.xhtml";
	var data = {};
	data.cid = cid;
	$.getData(url, data, true, "POST", "json", true, callBackDoPraise);
}

function callBackDoPraise(result){
	if(result.comment != null){
		var curNum = result.comment.upNum;
		$("#good_count"+result.comment.id).html(curNum);
	}
	
}
/*  doPraise  */

/*  Recomment  */
$(function(){
    var eRecomment = function(options,object) {
        var opts = $.extend({}, $.fn.renderComment.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getRecomments.xhtml";
        var showRecomment = function (data) {
            var sbHtml = new StringBuilder();
            if(data.readings.length > 0 ){
                 sbHtml.append("<div class='detail_comment_recommend_head'>推荐阅读</div>");
                 sbHtml.append("<section class='readingRecommend'>");
                 sbHtml.append("  <div class='readingRecommendListBox'>");
                 $.each(data.readings, function(index, item){
                     sbHtml.append("<div class='readingRecommendList'>");
                     sbHtml.append("  <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=2&aid="+item.id+"'>");
                     sbHtml.append("  <div>→"+item.title+"</div>");
                     sbHtml.append("  </a>");
                     sbHtml.append("</div>");
                 });
                 sbHtml.append("  </div>");
                 sbHtml.append("</section>");
                 sbHtml.append("<div class='separate'></div>");
            }
            
            if(data.recommends.length > 0 ){
                sbHtml.append("<div class='detail_comment_recommend_head'>热文推荐</div>");
                sbHtml.append("<section class='hotRecommend'>");
                sbHtml.append("  <div class='hotRecommendListBox'>");
                $.each(data.recommends, function(index, item){
                	 sbHtml.append("<div class='hotRecommendList'>");
                     sbHtml.append("  <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=2&aid="+item.id+"'><img width='183' height='160' class='radius-small' src='" + item.thumbnail + "'/>");
                     sbHtml.append("  </a>");
                     sbHtml.append("</div>");
                });
                sbHtml.append("  </div>");
                sbHtml.append("</section>");
           }
            $(instance).append($(sbHtml.toString()));
        };
        
        var initRecomments = function(data){
            $(".detail_comment_recommend").append(showRecomment(data));
        };
        
        $.getData(url, null, true, "POST", "json", true, initRecomments);
    };
   
    $.fn.renderRecomment = function(options) {
        return this.each(function () {
            return eRecomment(options, $(this));
        });
    };

    $.fn.renderRecomment.defaults = {};
});
/*  Recomment  */

/* research List */
$(function(){
    var eResearchList = function(options,object) {
        var opts = $.extend({}, $.fn.researchList.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getSurveyList.xhtml?pageNo="+lclove.params.pageNo +"&keyword=" + lclove.params.keyword;
        var itemTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='content_item'>");
            sbHtml.append("<div class='content_item_pic'>");
            sbHtml.append("  <a class='thumbnail' href='"+ lclove.util.basePath + "researchDetail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&surveyId="+data.id+"'><img width='148' height='148' class='img-border radius-small' src='" + data.thumbnail + "'/></a>");
            sbHtml.append("</div>");
            sbHtml.append("<div class='content_item_text'>");
            sbHtml.append("  <div class='content_item_text_head'>");
            sbHtml.append("    <a href='"+ lclove.util.basePath + "researchDetail.xhtml?navtype="+lclove.params.navtype+"&sortType=1&surveyId="+data.id+"'><img src='" + lclove.util.imgPath + "images/text_head_icon.png'/><div>"+ data.title +"</div></a>");
            sbHtml.append("  </div>");
            sbHtml.append("  <div class='mini_blank'></div>");
            sbHtml.append("  <div class='separate'></div>");
            sbHtml.append("  <div class='content_item_text_middle'> "+ data.description +"</div>");
            sbHtml.append("  <div class='content_item_text_foot'>");
            sbHtml.append("    <div class='date'>"+ data.pubDate +"</div>");
            sbHtml.append("  </div>");
            sbHtml.append("</div>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };

        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append('<img class="no_data_img" src="/images/NO_DATA.jpg">');
            return $(sbHtml.toString());
        };
        
        var scrollEvent = function(){
            var winH = $(window).height(); //页面可视区域高度
            var scrollAdd = function(){
            	var scrollT = $(window).scrollTop(); //滚动条top 
            	var pageH = $(document.body).height();
            	var scrollUrl = lclove.util.basePath + "ajax/getSurveyList.xhtml";
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
        	if(data != null && data.surveys != null && data.surveys != "" ){
        		$.each(data.surveys, function(index, item){
            		$(".content_list").append(itemTemplate(item));
            	});
        	}else{
                $(".content_list").append(defaultTemplate());
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

    $.fn.researchList = function(options) {
        return this.each(function () {
            return eResearchList(options, $(this));
        });
    };
    $.fn.researchList.defaults = {};
});
/* research List */

/*  research detail  */
$(function(){
    var eResearch = function(options,object) {
        var opts = $.extend({}, $.fn.renderResearch.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getResearchDetail.xhtml?surveyId=" + lclove.params.surveyId;
        var showResearch = function (data) {
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='search_head' suveryid='" + data.id + "'><img src='"+lclove.util.imgPath+"images/text_head_icon.png'/><div>" + data.title + "</div></div>");
            sbHtml.append("<img width='550' height='60' class='radius-small' src='"+ data.headerImg + "'/>");
            sbHtml.append("<div class='smallest_blank'></div>");
            $.each(data.questions, function(index, item){
                sbHtml.append("<div id='" + item.id + "' class='single_choose'>");
                sbHtml.append("<div class='choose_topic'>" + item.seq+" "+item.title + "</div>");
                if(item.inputType == "1"){
                	$.each(item.questionOptions, function(i, subItem){
                		sbHtml.append("<div class='suggenstion_div'><textarea class='suggestion_input' surveyid=" + item.id + " optionid='" + subItem.id + "'></textarea></div>");
                	});
                }else if(item.inputType == "2"){
	                	$.each(item.questionOptions, function(i, subItem){
	                		sbHtml.append("<div class='single_choose_item'><input name='survey_" + item.id + "' type='radio' value='" + subItem.id + "' /><div>" + subItem.content + "</div>");
	                		if(subItem.imgSrc != null && subItem.imgSrc != ""){
	                			sbHtml.append("<img width='90' height='80' class='radius-small'  src='"+subItem.imgSrc+"'/>");
	                		}
	                		sbHtml.append("</div>");
	                	});
                }else if(item.inputType == "3"){
	                	$.each(item.questionOptions, function(i, subItem){
	                		sbHtml.append("<div class='mutiple_choose_item'><div class='up_part'><input name='survey_" + item.id + "' type='checkbox' value='"+subItem.id+"' /><div>" + subItem.content + "</div></div>");
	                		if(subItem.imgSrc != null && subItem.imgSrc != ""){
	                			sbHtml.append("<img width='90' height='80' class='radius-small'  src='"+subItem.imgSrc+"'/>");
	                		}
	                		sbHtml.append("</div>");
	                });
                }
                sbHtml.append("<div class='mini_blank'></div>");
                sbHtml.append("</div>");
            });
            sbHtml.append("<div class='mini_blank'></div>");
            sbHtml.append("<div id='submitBtn'><img src='"+lclove.util.imgPath+"images/dy_03.png'/><div>完成提交后，可看到调研结果</div></div>");
            sbHtml.append("<div class='mini_blank'></div>");
            $(instance).append($(sbHtml.toString()));
            
            $("#submitBtn").on("click", submitData);
        };
        
        //提交问卷
        var submitData = function(){
        		//validate start
	        	var data = {};
	        	data.surveyId = $(".search_head").attr("suveryid");
	        	data.options = [];
	        	$(".single_choose").each(function(){
	        		var subData = {};
	        		var subSurveyId = $(this).attr("id");
	        		if($(this).find("input[type=radio]").length > 0){
	        			subData.optionId = $('input[name="survey_' + subSurveyId + '"]:checked ').val();
	        			data.options.push(subData);
	        		}else if($(this).find("input[type=checkbox]").length > 0){
	        			$('input[name="survey_' + subSurveyId + '"]').each(function(){
	        				if($(this).is(':checked')){
	        					subData = {};
	        					subData.optionId = $(this).val();
	        					data.options.push(subData);
	        				}
	        			})	
	        		}else if($(this).find("textarea").length > 0){
	        			var $tempObj = $('textarea[surveyid="' + subSurveyId + '"]');
    					subData.optionId = $tempObj.attr("optionid");
    					subData.answerContent = $tempObj.val();
    					data.options.push(subData);
	        			
	        		}
	        	});
	        	//console.log(data);
	        	var url = lclove.util.basePath + "ajax/saveResearch.xhtml";
	        	var optionDatas = JSON.stringify(data);
	        	var values = {"optionDatas":optionDatas}
	        	$.getData(url, values, true, "POST", "json", true, callBackResearch(data.surveyId));
        	}
        
      //保存回调
        var callBackResearch = function(surveyId){
        	refreshPage(lclove.util.basePath + "researchResult.xhtml?surveyId=" + surveyId);
        };
        $.getData(url, null, true, "POST", "json", true, showResearch);
    };
   
    $.fn.renderResearch = function(options) {
        return this.each(function () {
            return eResearch(options, $(this));
        });
    };

    $.fn.renderResearch.defaults = {};
});
/*  research detail  */


/*  research result  */
$(function(){
    var eResearchResult = function(options,object) {
        var opts = $.extend({}, $.fn.renderResearch.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getResearchDetail.xhtml?surveyId=" + lclove.params.surveyId;
        var showResearchResult = function (data) {
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='search_head'><img src='"+lclove.util.imgPath+"images/text_head_icon.png'/><div>" + data.title + "</div></div>");
            sbHtml.append("<img width='550' height='60' class='radius-small' src='"+ data.headerImg + "'/>");
            sbHtml.append("<div class='smallest_blank'></div>");
            $.each(data.questions, function(index, item){
            	if(item.inputType != "1"){
                    sbHtml.append("<div id='" + item.id + "' class='single_choose'>");
                    sbHtml.append("<div class='choose_topic'>" + item.seq+" "+item.title + "</div>");
                    $.each(item.questionOptions, function(i, subItem){
                        sbHtml.append("<div class='result_choose_item'>");
                        sbHtml.append("  <div class='result_content'>" + subItem.content + "</div>");
    	                sbHtml.append("  <div class='progress'>");
    	                if(subItem.score != null && subItem.score != ""){
    	                	sbHtml.append("    <div class='progress-bar' style='width: " + subItem.score + "%;'>" + subItem.score + "%</div>");
    	                }else{
    	                	sbHtml.append("    <div class='progress-bar' style='width: 0%;'></div>");
    	                }
    	                sbHtml.append("  </div>");
    	                sbHtml.append("</div>");
    	            });
                    sbHtml.append("<div class='mini_blank'></div>");
                    sbHtml.append("</div>");
                }
            });
            sbHtml.append("<div class='mini_blank'></div>");
            $(instance).append($(sbHtml.toString()));
        };
        $.getData(url, null, true, "POST", "json", true, showResearchResult);
    };
   
    $.fn.renderResearchResult = function(options) {
        return this.each(function () {
            return eResearchResult(options, $(this));
        });
    };

    $.fn.renderResearchResult.defaults = {};
});
/*  research result  */


/* backnumber list */
$(function(){
    var eBackNumberList = function(options,object) {
        var opts = $.extend({}, $.fn.backNumberList.defaults, options);
        var instance = object;
        var url = lclove.util.basePath + "ajax/getBackNumberList.xhtml?navtype="+lclove.params.navtype;
        var itemTemplate = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='specialBackNumberList'>");
            sbHtml.append("  <a href='"+ lclove.util.basePath + "detail.xhtml?navtype="+lclove.params.navtype+"&sortType=2&aid="+data.id+"'><img width='140' height='140' class='radius-small' src='" + data.thumbnail + "'/>");
            sbHtml.append("  </a>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var defaultTemplate = function(){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='specialBackNumberList'>");
            sbHtml.append("  <span>暂无记录</span>");
            sbHtml.append("</div>");
            return $(sbHtml.toString());
        };
        
        var fillData = function(data){
        	if(data != null && data != ""){
        		$.each(data.backNumbers, function(index, item){
            		$(".specialBackNumberListBox").append(itemTemplate(item));
            	});
        	}else{
        		$(".specialBackNumberListBox").append(defaultTemplate());
        		
        	}
        };
        $.getData(url, null, true, "POST", "json", true, fillData);
    };

    $.fn.backNumberList = function(options) {
        return this.each(function () {
            return eBackNumberList(options, $(this));
        });
    };
    $.fn.backNumberList.defaults = {};
});
/* backnumber list */ 

/*  about lm  */
$(function(){
    var eAbout = function(options,object) {
        var opts = $.extend({}, $.fn.aboutModule.defaults, options);
        var showAbout = function (data) {
        var sbHtml = new StringBuilder();
        sbHtml.append("<div class='article_content_body'>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-size: 20px; font-family: 宋体;'>「</span><span style='font-size: 20px; font-family: 宋体;'>新恋爱</span><span style='font-size: 20px; font-family: 宋体;'>」</span><span style='font-size: 20px; font-family: 宋体;'>时</span><span style='font-size: 20px; font-family: 宋体;'>代</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-size: 20px; font-family: 宋体;'>我的</span><span style='font-size: 20px; font-family: 宋体;'>恋爱将</span><span style='font-size: 20px; font-family: 宋体;'>何去何从？</span>    ");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>&nbsp;</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>想要靠近某个人</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>想要好好去</span><span style='font-family: 宋体;'>爱</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>&nbsp;</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>想要珍惜身边的他</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>想要</span><span style='font-family: 宋体;'>细</span><span style='font-family: 宋体;'>水长流走到最后</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>&nbsp;</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>最好的</span><span style='font-family: 宋体;'>爱</span><span style='font-family: 宋体;'>情</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>就是「有未</span><span style='font-family: 宋体;'>来</span><span style='font-family: 宋体;'>」</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>&nbsp;</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>为</span><span style='font-family: 宋体;'>所有想</span><span style='font-family: 宋体;'>爱</span><span style='font-family: 宋体;'>的女性</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>提供去</span><span style='font-family: 宋体;'>爱</span><span style='font-family: 宋体;'>的精神力量</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>支援大家走在</span><span style='font-family: 宋体;'>恋爱</span><span style='font-family: 宋体;'>成就的路上</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>&nbsp;</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p style='white-space: normal; background-color: rgb(255, 255, 255); text-align: center;'>");
        sbHtml.append("    <span style='font-family: 宋体;'>这</span><span style='font-family: 宋体;'>就是「</span><span style='font-family: 宋体;'>恋</span><span style='font-family: 宋体;'>猫神社」存在意义</span>");
        sbHtml.append("</p>");
        sbHtml.append("<p>");
        sbHtml.append("    <span style='font-family: 宋体;'><br/></span>");
        sbHtml.append("</p>");
        sbHtml.append("<p>");
        sbHtml.append("    <br/>");
        sbHtml.append("</p>");
        sbHtml.append("</div>");
        return $(sbHtml.toString());
        };
        
        var initAbout = function(data){
            $(".about_content").append(showAbout(data));
        };
        initAbout();
    };

    $.fn.aboutModule = function(options) {
        return this.each(function () {
            return eAbout(options, $(this));
        });
    };
        
    $.fn.aboutModule.defaults = {};

});
/*  Footer  */

/*  Footer  */
;(function($){
    var eFooter = function(options,object) {
        var opts = $.extend({}, $.fn.footerModule.defaults, options);
        var instance = object;

        var renderFooter = function(data){
            var sbHtml = new StringBuilder();
            sbHtml.append("<div class='layout bg-black bg-inverse'>");
    		sbHtml.append("	<div class='container'>");
    		sbHtml.append("		<div class='navbar'>");
    		sbHtml.append("			<div class='navbar-head'>");
    		sbHtml.append("				<button class='button bg-gray icon-navicon' data-target='#navbar-footer'></button>");
    		sbHtml.append("				<a href='http://www.lclianmao.com/' target='_blank'><img width='24' height='24' class='radius-small' src='" + lclove.util.imgPath + "images/img_omamori.png' alt='恋猫' /></a>");
    		sbHtml.append("			</div>");
    		sbHtml.append("			<div class='navbar-body nav-navicon' id='navbar-footer'>");
    		sbHtml.append("				<div class='navbar-text navbar-left hidden-s hidden-l'>版权所有 &copy; <a href='http://www.lclianmao.com/' target='_blank'>www.lclianmao.com</a> All Rights Reserved，沪ICP备10200855号-5</div>");
    		sbHtml.append("				<ul class='nav nav-inline navbar-right'>");
    		sbHtml.append("					<li><a href='" + lclove.util.basePath + "lianmao.xhtml' target='_blank'>关于恋猫</a></li>");
    		sbHtml.append("					<li><a href='" + lclove.util.basePath + "brand.xhtml' target='_blank'>关于LC品爱</a></li>");
    		sbHtml.append("					<li><a href=''>转载声明</a></li>");
    		sbHtml.append("					<li><a href='http://www.beianbeian.com/beianxinxi/a89c1924-5c86-4846-a79b-261aaea5527e.html' target='_blank'>ICP备案信息</a></li>");
    		sbHtml.append("				</ul>");
    		sbHtml.append("			</div>");
    		sbHtml.append("		</div>");
    		sbHtml.append("	</div>");
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

