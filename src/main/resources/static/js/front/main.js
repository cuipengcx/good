;
jQuery(document).ready(function($) {
	// 导航
	$(function () {
		var tag = $('.page_tag').attr('data-tag');
		$('.nav a').each(function(index, el) {

			if (tag == $(this).attr('data-tag')&& tag!=null) {
				console.log(tag);
				$(this).addClass('active');
			}
		});
	})
	// 首页
	$(function(){
		var search_box = $(".header .search");
		$(".header .search_btn").click(function(event) {
			if(search_box.hasClass('search_active')){
				search_box.find('input').removeAttr('autofocus');
				search_box.find('input').blur();
				search_box.removeClass('search_active');
			}else{
				search_box.addClass('search_active');
				search_box.find('input').focus();
				search_box.find('input').attr('autofocus', 'autofocus');
			}
		});
		$('.search_input').keyup(function(event) {
			var val = $(this).val();
			if (val&&event.keyCode == 13) {
				window.location.href="search.html";
			}
		});
		var user = $('.is_login .hide_box');
		$('.is_login p').click(function(e) {
			if (user.is(':hidden')) {
				user.show();
				$("body").not(this).click(function () {
		               user.hide();
		        });
			}else{
				user.hide();
			}
			e.stopPropagation();
		});
		// 优秀项目初始化及tab切换效果
		var tab_btn = $(".main_body .f1 .tab_r span");
		var tab_content = $(".main_body .f1 .tab_l");
		tab_btn.removeClass().eq(0).addClass('active');
		tab_content.hide().eq(0).show();
		tab_btn.click(function () {
			var index = $(this).index();
			tab_btn.removeClass().eq(index).addClass('active');
			tab_content.hide().eq(index).show();
		})
		// 项目字数限制
		$('.project .font h4').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,15);
			$(this).text(s);
		});
		$('.project .font .des').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,90);
			$(this).text(s);
		});
		$('.project .font .from').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,18);
			$(this).text(s);
		});
		$('.f2 .p_box h4').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,13);
			$(this).text(s);
		});
		$('.f2 .p_box .des').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,280);
			$(this).text(s);
		});
		$('.f2 .p_box .from').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,20);
			$(this).text(s);
		});
		$('.f2 .p_box .from').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,20);
			$(this).text(s);
		});
		$('.f3 .des').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,52);
			$(this).text(s);
		});
		// 专家队伍
		var z_top = Math.ceil($('.f3 li').length/3);
		var z_index = 0;
		$(".f3 ul").width(z_top*1200);
		$('.f3 .next').click(function(event) {
			$(".f3 ul").stop(true,true);
			z_index ++ ;
			if (z_index>=z_top-1) {
				z_index = z_top-1;
			}
			console.log(z_index);
			$(".f3 ul").animate({left:-z_index*1200}, 250);
		});
		$('.f3 .prev').click(function(event) {
			$(".f3 ul").stop(true,true);
			z_index -- ;
			if (z_index<=0) {
				z_index = 0;
			}
			console.log(z_index);
			$(".f3 ul").animate({left:-z_index*1200}, 250);
		});
	})

	// 发现页
	$(function () {
		var title = $('.find_title span');
		var content = $('.find_list>div');
		// init
		title.removeClass().eq(0).addClass('active');
		content.hide().eq(0).show();
		title.click(function(event) {
			var index = $(this).index();
		    title.eq(index).addClass('active').siblings().removeClass();
		    content.hide().eq(index).show();

		});

		$('.first_find p').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,30);
			$(this).text(s);
		});
		$('.find_list h3').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,25);
			$(this).text(s);
		});
		$('.find_list .font p').each(function(index, el) {
			var s = $(this).text();
			s = wl(s,114);
			$(this).text(s);
		});
	})
	// 新建项目
	$(function () {
		// init
		show_checked();
		function show_checked() {
			$('.create_body input[type=radio]').each(function(index, el) {
				if ($(this).attr('checked')) {
					$(this).siblings('.radio').find('em').addClass('yes');
				}
			});
			$('.create_body .law input[checked=checked]').each(function(index, el) {
				$(this).siblings('em').addClass('yes');
			});
		}
		$(".create_body form div label.company").click(function() {
			
			$('.create_body form div label em').removeClass('yes');
			show_checked();
		});
		$(".create_body form div label.personal").click(function() {
			$('.create_body form div label em').removeClass('yes');
			show_checked();
		});
		$('.create_body .law').click(function(event) {
			$(this).find('em').removeClass('yes');
			if ($(this).find('input').attr('checked')) {
				$(this).find('em').addClass('yes');
			}
		});
		// step1添加项目伙伴
		var len = 1;
		$('.create_step .add').click(function(event) {
			len ++ ;
			if (len>3){
				lockMessage("只能添加三个伙伴!",1000);
				$(this).css('cursor','not-allowed');
				return;
			}
			var input = $('<input/>');
			var div = $('<div></div>');
			div.addClass('form_m_width').css('margin-top',12);
			input.attr({
				type: 'text',
				placeholder:'需要时选填',
				maxlength:'30',
				class:'aaa'
			});
			div.append(input);
			$('.add_to_three div p').before(div);
		});
	})


	/*项目详情页*/
	$(function () {
		var tab = getStr('tab');
		console.log(tab);
		var title = $('.project_detail_title .title span');
		var content = $('.project_detail_content .content_l .content');
		// init
		if (tab) {
			title.removeClass('active').eq(tab-1).addClass('active');
			content.hide().eq(tab-1).show();
		}else{
			title.removeClass('active').eq(0).addClass('active');
			content.hide().eq(0).show();
		}
		title.click(function(event) {
			var index = $(this).index();
			$(this).addClass('active').siblings().removeClass('active');
			content.hide().eq(index).show();
		});
	})

	// 个人中心评论
	$(function(){
		var pcHL=$(".pcLeft-nav").height();
		var pcHR=$(".pcRight-body").height();
		if(pcHL>pcHR){
			$(".pc_body").height(pcHL);
		}else{
			$(".pc_body").height(pcHR);
		}
		var comIndex;
		$(".comment-button").click(function(){
			comIndex=$(".comment-button").index(this);
			$(".mask-body").fadeIn(500);
			$(".comment-from").eq(comIndex).slideDown(500);
		})
		$(".mask-body,.comment-submit").click(function(){
			$(".mask-body").fadeOut(500);
			$(".comment-from").eq(comIndex).slideUp(500);
		});
		// 评论字数统计
		$(".comment-textarea").keyup(function(){
			var tn=$(this).val().length;
			var ti=$(".comment-textarea").index(this);
			if(tn>120){
				$(this).attr("disabled")="disabled";
			}else{
				$(".comment-num").eq(ti).html(tn+"/120");
			}
		})
	})
	// 输入框动态更新字数
	$(function () {
		$('input,textarea').each(function(index, el) {
			var limit = $(this).attr('data-limit');
			if (limit) {
				$(this).attr('maxlength',limit);
				$(this).siblings('p').text('0/'+limit);
				$(this).keyup(function(event) {
					var val = $(this).val();
					var len = val.length;
					if (len>limit) {
						val=val.substring(0,limit);
						$(this).val(val)
						$(this).blur();
						$(this).siblings('p').text(limit+'/'+limit);
					}else{
						$(this).siblings('p').text(len+'/'+limit);
					}
				});
			}
		});
		// 提醒框
		$('input[data-must=true],textarea[data-must=true]').blur(function(event) {
			var val = $(this).val();
			if (!val) {
				$(this).parents('div,tr').css('border-color','red');
			}else{
				$(this).parents('div,tr').css('border-color','#ddd');
			}
		}).keyup(function(event) {
			var val = $(this).val();
			if (!val) {
				$(this).parents('div,tr').css('border-color','red');
			}else{
				$(this).parents('div,tr').css('border-color','#ddd');
			}
		});


		// 图片预览
		$('img[data-alert=true]').click(function(event) {
			console.log(111);
			var src = $(this).attr('src');
			var mask = $('<div></div>');
			mask.css({
				position: 'fixed',
				width: '100%',
				height: '100%',
				top: 0,
				left: 0,
				background: 'rgba(0,0,0,0.5)',
				'z-index':1000000
			}).click(function(event) {
				$(this).remove();
			});
			var img = $('<img>')
			img.attr('src', src);
			img.css({
				position: 'absolute',
				'max-width': '50%',
				top: '50%',
				left: '50%',
				'transform': 'translate(-50%, -50%)',
				'-webkit-transform': 'translate(-50%, -50%)',
				'-moz-transform': 'translate(-50%, -50%)',
				'-ms-transform': 'translate(-50%, -50%)',
				'-o-transform': 'translate(-50%, -50%)',
			});
			mask.append(img);
			$('body').append(mask);
		});
	})
});

// 字数限制
function wl(s,n){
	if (s.length>n) {
		s=s.substring(0,n);
		s+="...";
	}
	return s;
};
//获取网址中的参数
function getStr(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}