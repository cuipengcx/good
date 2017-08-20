/**
 * 对百度WebUploader的封装
 * 因为这里考虑到每个需求可能不一样，比如文件大小限制，文件格式限制，宽高等，还是没法做成共通的插件
 * 所以这些基本参数需要调用者自己定义，这里对回调进行了封装，调用者无需关心处理回调
 */
(function() {

	/**
	 * 图片
	 * @param options
	 */
	var $WebUploadPicture = function(options) {
		this.auto = options.auto || true;  //开启自动上传
		this.uploadBtnId = options.uploadBtnId || '#filePicker'; //选择图片按钮，id|class自己定义
		this.uploadPreId = options.picturePreId || 'fileListPre'; //图片预览ID
		this.uploadUrl = options.serverUrl || '/upload/images';    //上传URL
		this.swf = '/h-ui/lib/webuploader/0.1.5/Uploader.swf';
		this.pictureUrl = options.hiddenPictureUrl || 'pictureUrl';   //隐藏域name,用于往后台传值
		this.pictureName = options.hiddenPictureName || 'pictureName';   //隐藏域原文件名,用于往后台传值
		this.picWidth = options.width;  //图片宽度
		this.picHeight = options.height; //图片高度
		this.fileSizeLimit = options.fileSizeLimit || 100 * 1024 * 1024;   //验证文件总大小是否超出限制, 超出则不允许加入队列
		this.fileSingleSizeLimit = options.fileSingleSizeLimit || 2 * 1024 * 1024;   // 验证单个文件大小是否超出限制, 超出则不允许加入队列
		this.fileNumLimit = options.fileNumLimit || undefined;   // 验证文件总数量, 超出则不允许加入队列
		this.accept = options.accept;  //指定接受哪些类型的文件
		this.append = options.append || false;  //是否开启追加多个图片
		this.uploadBarId = null;      //图片上传的进度条的id
	};

	$WebUploadPicture.prototype = {
		/**
		 * 初始化webUploader
		 */
		init : function() {
			var uploader = this.create();
			this.bindEvent(uploader);
			return uploader;
		},

		/**
		 * 创建webuploader对象
		 */
		create : function() {
			return WebUploader.create({
				auto : this.auto,
				pick : {
					id : this.uploadBtnId,
					multiple : false // 只上传一个
				},
				accept : this.accept,
				swf : this.swf,
				disableGlobalDnd : true,
				duplicate : true,
				server : this.uploadUrl,
				fileSizeLimit : this.fileSizeLimit,
				fileSingleSizeLimit : this.fileSingleSizeLimit,
				fileNumLimit : this.fileNumLimit
			});
		},

		/**
		 * 绑定事件
		 */
		bindEvent : function(bindObj) {
			var me =  this;

			//当文件被加入队列以后触发
			bindObj.on('fileQueued', function(file) {
				var $li = $(
					'<div id="' + file.id + '" class="file-item thumbnail">' +
					'<img width="'+me.picWidth+'" height="'+me.picHeight+'">' +
					'<input type="hidden" name="'+me.pictureUrl+'" id="' + file.id + 'PicUrl" />'+
					'<input type="hidden" name="'+me.pictureName+'" id="' + file.id + 'PicName" />'+
					'<div class="info">' + file.name + '</div>' +
					'</div>'
				);
				// var $li = $('<div><img width="100px" height="100px"></div>');
				var $img = $li.find('img');
				if(me.append){
					$("#" + me.uploadPreId).append($li);
				}else {
					$("#" + me.uploadPreId).html($li);
				}

				// 生成缩略图
				bindObj.makeThumb(file, function(error, src) {
					if (error) {
						$img.replaceWith('<span>不能预览</span>');
						return;
					}
					$img.attr('src', src);
				}, 1, 1);
			});

			// 文件上传过程中创建进度条实时显示。
			bindObj.on('uploadProgress', function(file, percentage) {
				// $("#"+me.uploadBarId).css("width",percentage * 100 + "%");
				var $li = $( '#'+file.id ),
					$percent = $li.find('.progress span');

				// 避免重复创建
				if ( !$percent.length ) {
					$percent = $('<p class="progress"><span></span></p>')
						.appendTo( $li )
						.find('span');
				}

				$percent.css( 'width', percentage * 100 + '%' );
			});

			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			bindObj.on('uploadSuccess', function(file,response) {
				succeedMessage("上传成功");
				//隐藏域ID,用于往后台传值
				console.log(response._raw);
				$("#" + file.id + "PicUrl").val(response._raw);
				//隐藏域原文件名,用于往后台传值
				$("#" + file.id + "PicName").val(file.name);
				$('#' + file.id).addClass('upload-state-done');
			});

			// 完成上传完了，成功或者失败，先删除进度条。
			bindObj.on('uploadComplete', function(file) {
				$( '#'+file.id ).find('.progress').remove();
			});


			// 文件上传失败，显示上传出错。
			bindObj.on('uploadError', function(file) {
				errorMessage("上传失败");
				var $li = $( '#'+file.id ),
					$error = $li.find('div.error');

				// 避免重复创建
				if ( !$error.length ) {
					$error = $('<div class="error"></div>').appendTo( $li );
				}

				$error.text('上传失败');

				//移除上传失败的隐藏域input值
				$('#' + file.id + 'PicUrl').remove();
				$('#' + file.id + 'PicName').remove();
			});

			// 其他错误
			bindObj.on('error', function(type) {
				if ("Q_EXCEED_SIZE_LIMIT" == type) {
					errorMessage("文件大小超出了限制");
				} else if ("Q_TYPE_DENIED" == type) {
					errorMessage("文件类型不满足");
				} else if ("Q_EXCEED_NUM_LIMIT" == type) {
					errorMessage("上传数量超过限制");
				} else if ("F_DUPLICATE" == type) {
					errorMessage("图片选择重复");
				} else if("F_EXCEED_SIZE" == type){
					errorMessage("文件大小超出了限制");
				} else {
					errorMessage("上传过程中出错");
				}
			});

		},

		/**
		 * 设置图片上传的进度条的id
		 */
		setUploadBarId: function (id) {
			this.uploadBarId = id;
		}
	};

	/**
	 * 文件
	 * @param options
	 */
	var $WebUploadDoc = function(options) {
		this.auto = options.auto || true;  //开启自动上传
		this.uploadBtnId = options.uploadBtnId || '#filePicker'; //选择上传按钮，id|class自己定义
		this.uploadPreId = options.docPreId || 'fileListPre'; //预览ID
		this.uploadUrl = options.serverUrl || '/upload/docs';    //上传URL
		this.swf = '/h-ui/lib/webuploader/0.1.5/Uploader.swf';
		this.docUrl = options.hiddenDocUrl || 'docUrl';   //隐藏域name,用于往后台传值
		this.docName = options.hiddenDocName || 'docName';   //隐藏域原文件名,用于往后台传值
		this.fileSizeLimit = options.fileSizeLimit || 100 * 1024 * 1024;   //验证文件总大小是否超出限制, 超出则不允许加入队列
		this.fileSingleSizeLimit = options.fileSingleSizeLimit || 2 * 1024 * 1024;   // 验证单个文件大小是否超出限制, 超出则不允许加入队列
		this.fileNumLimit = options.fileNumLimit || undefined;   // 验证文件总数量, 超出则不允许加入队列
		this.accept = options.accept;  //指定接受哪些类型的文件
		this.append = options.append || false;  //是否开启追加多个
		this.uploadBarId = null;      //上传的进度条的id
	};


	$WebUploadDoc.prototype = {
		/**
		 * 初始化webUploader
		 */
		init : function() {
			var uploader = this.create();
			this.bindEvent(uploader);
			return uploader;
		},

		/**
		 * 创建webuploader对象
		 */
		create : function() {
			return WebUploader.create({
				auto : this.auto,
				pick : {
					id : this.uploadBtnId,
					multiple : false // 只上传一个
				},
				accept : this.accept,
				swf : this.swf,
				disableGlobalDnd : true,
				duplicate : true,
				server : this.uploadUrl,
				fileSizeLimit : this.fileSizeLimit,
				fileSingleSizeLimit : this.fileSingleSizeLimit,
				fileNumLimit : this.fileNumLimit
			});
		},

		/**
		 * 绑定事件
		 */
		bindEvent : function(bindObj) {
			var me =  this;

			//当文件被加入队列以后触发
			bindObj.on('fileQueued', function(file) {
				var $li = $(
					'<div id="' + file.id + '" class="item">' +
					'<h4 class="info">' + file.name + '</h4>' +
					'<input type="hidden" name="'+me.docUrl+'" id="' + file.id + 'DocUrl" />'+
					'<input type="hidden" name="'+me.docName+'" id="' + file.id + 'DocName" />'+
					'<a href="javascript:;" onclick="delDoc(this);">删除</a>'+
					'<p class="state">等待上传...</p>' +
					'</div>'
				);
				if(me.append){
					$("#" + me.uploadPreId).append($li);
				}else {
					$("#" + me.uploadPreId).html($li);
				}
			});

			// 文件上传过程中创建进度条实时显示。
			bindObj.on('uploadProgress', function(file, percentage) {
				// $("#"+me.uploadBarId).css("width",percentage * 100 + "%");
				var $li = $( '#'+file.id ),
					$percent = $li.find('.progress .progress-bar');

				// 避免重复创建
				if ( !$percent.length ) {
					$percent = $('<div class="progress progress-striped active">' +
						'<div class="progress-bar" role="progressbar" style="width: 0%">' +
						'</div>' +
						'</div>').appendTo( $li ).find('.progress-bar');
				}

				$li.find('p.state').text('上传中');

				$percent.css( 'width', percentage * 100 + '%' );
			});

			// 文件上传成功，给item添加成功class, 用样式标记上传成功。
			bindObj.on('uploadSuccess', function(file,response) {
				succeedMessage("上传成功");
				//隐藏域ID,用于往后台传值
				$("#" + file.id + "DocUrl").val(response._raw);
				//隐藏域原文件名,用于往后台传值
				$("#" + file.id + "DocName").val(file.name);

				$( '#'+file.id ).find('p.state').text('已上传');
			});

			// 完成上传完了，成功或者失败，先删除进度条。
			bindObj.on('uploadComplete', function(file) {
				// $( '#'+file.id ).find('.progress').fadeOut();
				$( '#'+file.id ).find('.progress').remove();
			});


			// 文件上传失败，显示上传出错。
			bindObj.on('uploadError', function(file) {
				errorMessage("上传失败");
				$( '#'+file.id ).find('p.state').text('上传出错');
				//移除上传失败的隐藏域input值
				$('#' + file.id + 'DocUrl').remove();
				$('#' + file.id + 'DocName').remove();
			});

			// 其他错误
			bindObj.on('error', function(type) {
				if ("Q_EXCEED_SIZE_LIMIT" == type) {
					errorMessage("文件大小超出了限制");
				} else if ("Q_TYPE_DENIED" == type) {
					errorMessage("文件类型不满足");
				} else if ("Q_EXCEED_NUM_LIMIT" == type) {
					errorMessage("上传数量超过限制");
				} else if ("F_DUPLICATE" == type) {
					errorMessage("文件选择重复");
				} else if("F_EXCEED_SIZE" == type){
					errorMessage("文件大小超出了限制");
				} else{
					errorMessage("上传过程中出错");
				}
			});
		},

		/**
		 * 设置图片上传的进度条的id
		 */
		setUploadBarId: function (id) {
			this.uploadBarId = id;
		}
	};

	window.$WebUploadPicture = $WebUploadPicture;
	window.$WebUploadDoc = $WebUploadDoc;

}());


function delDoc(obj) {
	var $this = $(obj);
	$this.parent("div").remove();
}