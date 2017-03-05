/**
* 轮播图组件
*	by maii
*	2016-11-15 10:07:58
*/
function Banner(selector,obj) {
	this.selector = selector;
	this.box = $(selector);
	if (this.box.length) {	
		if (typeof obj.width == 'number') {
			obj.width += 'px';
		}
		this.width = obj.width;
		if (typeof obj.height == 'number') {
			obj.height += 'px';
		}
		this.height = obj.height;
		this.img_src = this.get_img_src();
		this.href = this.get_href();
		this.len = this.img_src.length;
		this.time = obj.time;
		this.speed = obj.speed;
		this.active = true;
		this.hover = obj.hover_show;
		this.create();
	}
}
Banner.prototype.get_img_src = function() {
	var arr = [];
	this.box.find('img').each(function () {
		var src = $(this).attr('src');
		arr.push(src);
	})
	return arr;
};
Banner.prototype.get_href = function() {
	var arr = [];
	this.box.find('a').each(function () {
		var href = $(this).attr('href');
		arr.push(href);
	})
	return arr;
};
Banner.prototype.clone_left_and_right = function () {
	var left = this.box.find('.banner_prev');
	var right = this.box.find('.banner_next');
	return {left:left,right:right};
}
Banner.prototype.create = function() {
	var box = this;
	var left_and_right = this.clone_left_and_right();
	this.box.empty();
	this.box.append(left_and_right.left);
	this.box.append(left_and_right.right);
	var banner_box = $('<div></div>').addClass('banner_box');
	banner_box.css({
		height: box.height,
		position: 'relative',
		overflow: 'hidden'
	});
	this.box.append(banner_box);
	var banner_img = $('<ul></ul>').addClass('banner_img');
	banner_img.css({
		'list-style': 'none'
	});
	banner_box.append(banner_img);
	var banner_control = $('<div></div>').addClass('banner_control');
	banner_control.css({
		height: 10,
		width: '100%',
		position: 'absolute',
		left: 0,
		bottom: 30,
		'text-align':'center'
	});
	banner_box.append(banner_control);
	for (var i = 0; i < this.len; i++) {
		var li = $('<li></li>');
		if(this.len>1){
			var span = $('<span></span>');
			span.css({
				display: 'inline-block',
				width: 14,
				height: 14,
				'border-radius':'50%',
				background:'rgba(30,30,30,.65)',
				margin:10
			});
			banner_control.append(span);
		}
		li.css({
			width: '100%',
			height: box.height,
			position: 'absolute',
			left: '100%'
		}).hide();
		if (i == 0) {
			li.show().css({
				left: 0
			});
		}
		banner_img.append(li);
		var img = $('<img/>')
		img.attr({
			src: box.img_src[i]
		});
		img.css({
			width: box.width,
			height: box.height,
			position: 'absolute',
			left: '50%',
			'margin-left': -parseInt(box.width)/2 + 'px'
		});
		if (this.href.length) {
			var a = $('<a></a>');
			a.attr({
				href: box.href[i]
			});
			a.append(img);
			li.append(a);
		}else{
			li.append(img);
		}

	}
	if (this.len>1) {
		this.move();
	}	
};
Banner.prototype.move = function() {
	var box = this;
	var len = this.img_src.length;
	var index = 0;
	var index_pre = len - 1;
	var index_next = 1;
	$(box.selector+' .banner_box>.banner_control>span').eq(0).addClass('active');
	function banner_move_left() {
		$(box.selector+' .banner_img>li').eq(index_pre).animate({left: '-100%'},box.speed,function () {
			$(this).hide();
		});
		$(box.selector+' .banner_img>li').eq(index).show().css({
			'left': '100%'
		}).animate({left: 0},box.speed);
		$(box.selector+' .banner_box>.banner_control>span').removeClass().eq(index).addClass('active');
	}
	function banner_move_right() {
		$(box.selector+' .banner_img>li').eq(index_pre).animate({
			left: '100%'},
			box.speed, function() {
				$(this).hide();
		});
		$(box.selector+' .banner_img>li').eq(index).show().css('left','-100%').animate({
			left: 0},
			box.speed);
		$(box.selector+' .banner_box>.banner_control>span').removeClass().eq(index).addClass('active');
	}
	function banner_index() {
		index_pre = index;
		index++;
		if (index == len) {
			index = 0;
		}
	}
	function banner_index2() {
		index_pre = index;
		index--;
		if (index < 0) {
			index = len - 1;
		}
	}
	function banner_auto_play() {
		banner_index();
		banner_move_left();
	}
	var banner_timer = setInterval(banner_auto_play,box.time);
	this.box.mouseenter(function(event) {
		$(box.selector+' .banner_img>li').stop(true,true);
		// $('.banner_box>span').show();
		clearInterval(banner_timer);
		if (box.hover) {
			box.box.find('.banner_prev').show();
			box.box.find('.banner_next').show();
		}
	}).mouseleave(function(event) {
		$(box.selector+' .banner_box>span').hide();
		banner_timer = setInterval(banner_auto_play,box.time);
		if (box.hover) {
			box.box.find('.banner_prev').hide();
			box.box.find('.banner_next').hide();
		}
	});
	$(box.selector+' .banner_box>.banner_control>span').mouseenter(function(event) {
		$(box.selector+' .banner_img>li').stop(true,true);
		if ($(this).index()<index) {
			index_pre = index;
			index = $(this).index();
			banner_move_right();
		}else if($(this).index()>index){
			index_pre = index;
			index = $(this).index();
			banner_move_left();
		}
		event.stopPropagation();
	});
	$(box.selector+' .banner_box>span.pre').click(function(event) {
		index_pre = index;
		index--;
		if (index>0) {
			index = len - 1;
		}
		$(box.selector+' .banner_img li').stop(true,true);
		banner_move_right();
	});
	$(box.selector+' .banner_box>span.next').click(function(event) {
		$(box.selector+' .banner_img li').stop(true,true);
		banner_auto_play();
	});
	box.box.find('.banner_prev').click(function () {
		$(box.selector+' .banner_img li').stop(true,true);
		banner_index2();
		banner_move_right();
	})
	box.box.find('.banner_next').click(function () {
		$(box.selector+' .banner_img li').stop(true,true);
		banner_auto_play();
	})
};

/*jQuery字数限制*/
jQuery.prototype.wl = function(n) {
	var s = this.text();
	if (s.length > n) {
		s = s.substring(0,n);
		s += '...';
	}
	this.text(s);
};
jQuery.prototype.jump = function (nav_tag) {
	var tag = $(this).attr('data-tag');
	var nav_tag = $(nav_tag);
	for (var i = 0; i < nav_tag.length; i++) {
		if (tag == $(nav_tag[i]).attr('data-nav-tag')) {
			$(nav_tag[i]).addClass('active');
			return false;
		}
	}
	
}