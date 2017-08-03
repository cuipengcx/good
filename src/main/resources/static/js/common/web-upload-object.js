/**
 * 对百度WebUploader的封装
 * 因为这里考虑到每个需求可能不一样，比如文件大小限制，文件格式限制，宽高等，还是没法做成共通的插件
 * 所以这些基本参数需要调用者自己定义，这里对回调进行了封装，调用者无需关心处理回调
 */
(function() {
	
	var $WebUpload = function(options) {
		this.auto = true;  //开启自动上传
		this.uploadBtnId = options.uploadBtnId; //选择图片按钮，id|class自己定义
		this.uploadPreId = options.picturePreId; //图片预览ID
		this.uploadUrl = options.serverUrl;    //上传URL
		this.swf = '/h-ui/lib/webuploader/0.1.5/Uploader.swf';
		this.pictureId = options.hiddenPictureId;   //隐藏域ID,用于往后台传值
		this.picWidth = options.width;  //图片宽度
		this.picHeight = options.height; //图片高度
		this.fileSizeLimit = options.fileSizeLimit;   //验证文件总大小是否超出限制, 超出则不允许加入队列
		this.fileSingleSizeLimit = options.fileSingleSizeLimit;   // 验证单个文件大小是否超出限制, 超出则不允许加入队列
		this.fileNumLimit = options.fileNumLimit;   // 验证文件总数量, 超出则不允许加入队列
		this.accept = options.accept;  //指定接受哪些类型的文件
        this.uploadBarId = null;      //图片上传的进度条的id
	};

	$WebUpload.prototype = {
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
					id : this.uploadBtnId
					// multiple : false // 只上传一个
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
						'<img>' +
						'<div class="info">' + file.name + '</div>' +
						'</div>'
					);
				// var $li = $('<div><img width="100px" height="100px"></div>');
				var $img = $li.find('img');

				$("#" + me.uploadPreId).html($li);

				// 生成缩略图
				bindObj.makeThumb(file, function(error, src) {
					if (error) {
						$img.replaceWith('<span>不能预览</span>');
						return;
					}
					$img.attr('src', src);
				}, me.picWidth, me.picHeight);
			});

			// 文件上传过程中创建进度条实时显示。
			bindObj.on('uploadProgress', function(file, percentage) {
				console.log(percentage);
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
				$("#" + me.pictureId).val(response);
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

	window.$WebUpload = $WebUpload;

}());