var curr = 0;
var banner_points;
$(document).ready(function(e) {
	var prev = $(".pref_banner").eq(0);
	var next = $(".next_banner").eq(0);
	var content_bg = $(".display_banner").eq(0);
	banner_points = $(".banner_points").find("div");
	
	prev.bind("click", function(){
		curr = curr - 1;
		if(curr<0){
			curr = 4;
		}
		var currentBanner = $(banner_points).eq(curr);
		currentBanner.removeClass("banner_point");
		currentBanner.addClass("banner_point_on");
		$($(currentBanner).siblings()).each(function(){ 
			if($(this).hasClass("banner_point_on")){
				$(this).removeClass("banner_point_on");
				$(this).addClass("banner_point");
			}
		});
		
		content_bg.animate({left:-100*curr+"%"}, 500);
	});
	
	next.bind("click", function(){
		curr = curr + 1;
		if(curr>4){
			curr = 0;
		}
		var currentBanner = $(banner_points).eq(curr);
		currentBanner.removeClass("banner_point");
		currentBanner.addClass("banner_point_on");
		$($(currentBanner).siblings()).each(function(){ 
			if($(this).hasClass("banner_point_on")){
				$(this).removeClass("banner_point_on");
				$(this).addClass("banner_point");
			}
		});
		
		content_bg.animate({left:-100*curr+"%"},500);
	});
	
	$(banner_points).each(function(index){		
		$(this).bind("click", function(){
			curr = index;
			if($(this).hasClass("banner_point")){				
				$(this).removeClass("banner_point");
				$(this).addClass("banner_point_on");
				var others = $(this).siblings();
				$(others).each(function(){
					$(this).removeClass("banner_point_on");
					$(this).addClass("banner_point");					
				});
			}else if($(this).hasClass("banner_point_on")){
				$(this).removeClass("banner_point_on");
				$(this).addClass("banner_point");
				var others = $(this).siblings();
				$(others).each(function(){
					$(this).removeClass("banner_point");
					$(this).addClass("banner_point_on");					
				});
			}
			content_bg.animate({left:-100*curr+"%"},500);
		});							   
	});
	
	setInterval("autoFly()", 10000);
	
	/* function for result.html*/
	var percents = $(".percents");
	var colorbars = $(".color_bar");
	$(percents).each(function(index){
		var percent = parseInt($(this).html());
		$(colorbars).eq(index).width(percent*3+"px");
	});
});

function autoFly(){
	var content_bg = $(".display_banner").eq(0);
	curr = curr + 1;
	if(curr>4){
		curr = 0;
	}
	var currentBanner = $(banner_points).eq(curr);
		currentBanner.removeClass("banner_point");
		currentBanner.addClass("banner_point_on");
		$($(currentBanner).siblings()).each(function(){ 
			if($(this).hasClass("banner_point_on")){
				$(this).removeClass("banner_point_on");
				$(this).addClass("banner_point");
			}
	});
	
	content_bg.animate({left:-100*curr+"%"},500);
}

function navClick(index){
	var url;
	switch(index){
	case 1:
		url = "../home.html?"+new Date().getTime();
		break;
	case 2:
		url = "special.html";
		break;
	case 3:
		url = "body.html";
		break;
	case 4:
		url = "amulet.html";
		break;
	case 5:
		url = "research.html";
		break;
	case 6:
		url = "amulet.html";
		break;
	case 7:
		url = "amulet.html";
		break;
	default:
		break;
	}
	if(url!=null){
	    window.location = url;
	}
}

function navClickInHome(index){
	var url;
	switch(index){
	case 1:
		url = "home.html?"+new Date().getTime();
		break;
	case 2:
		url = "html/special.html";
		break;
	case 3:
		url = "html/body.html";
		break;
	case 4:
		url = "html/amulet.html";
		break;
	case 5:
		url = "html/research.html";
		break;
	case 6:
		url = "html/amulet.html";
		break;
	case 7:
		url = "html/amulet.html";
		break;
	default:
		break;
	}
	if(url!=null){
	    window.location = url;
	}
}

