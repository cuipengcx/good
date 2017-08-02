/****
 *Feature:
 * 简化配置多个上传实例
 * 封装配置操作
 * 提供上传组件的基本UI
 * 可以作为jQuery插件使用,导入jQuery即可，但不依赖jQuery
 * 提供暴露的class 供配置样式
 * 暂时提供两种组件样式供选择
 *
 * 兼容性：IE8+ 最新版本的Safari Chrome FireFox Opera等
 *
 *
 * Usage
 * 请在你的项目目录新建upload文件夹，并把webuploader所有文件
 * 保存到里面 如:
 * upload/webuploader.js upload/Uploader.swf ... 的形式
 *
 * options= {
*     serverPath:'../balight/photo/uploadcode',
*     number: 2,   //创建的上传按钮的个数
*     type: img,//img or file 上传图片 or  上传普通文件
*     divId:id,  // 你要在页面哪个div下创建这个上传组件
*     fileSizeLimit:,// int  所有文件的总上传大小不超过这个值
*
*     uploadText:'',
*     thumbnailSize:100, //100 or 200
*    }
 *
 *  Bug1:  处理大文件,进度条卡死。进度条没有反映真实的上传进度
 *  Bug2:   监听all事件？ 不对！
 *   Add :addButton 添加文件选择按钮
 *           addFile 添加文件到队列
 *           removeFile 移除
 *Author Jason
 *Date 2016/11/25
 *****/
/*这时一个闭包包裹的IIFE Immediately-Invoked Function Expression
 分析一下jQuery原理
 使用了querySelector ，在IE8中兼容，有什么问题
 jQuery优先使用querySelector，对于IE6，7走sizzle逻辑
 ***/
(function(jQuery,WebUploader){
    //param  setting  Global variable
    var $=jQuery;
    ///var rootx=this;  这个this是 window！
    //优化retina，在retina下这个值是2
    var ratio=window.devicePixelRatio||1;
    //缩略图大小

    var resize=false,
        swf='upload/Uploader.swf',
        uploadId='upload',
        classRoot='uploadRoot',
        listId='list',
        classUploadList='uploader-list',
        picker='pick',
        btnId='ctlBtn',
        classBtn='btn',
        extendsion='gif,jpg,jpeg,bmp,png',
        imgTitle='Images',
        msgHint={img:'选择图片',file:'选择文件',text:''},
        btnTxt={img:'',file:'确认上传',text:''};

    //Polyfill for classlist, which is a HTML5 new prop
    function classList(e){
        if(e.classList) return e.classList;  //如果e.classList存在返回
        else return new CSSClassList(e);//不存在就伪造一个
    }

    //CSSClassList是一个模拟DOMTokenList的 JS类
    function CSSClassList(e){
        this.e=e;
    }

    //如果e.className包含类名c则返回true 否则返回false
    CSSClassList.prototype.contains=function(c){
        //检测c是否是合法的类名
        if(c.length===0||c.indexOf(" ")!=-1){
            throw new Error('Invalid class name: '+c);
        }
        //常规检查
        var classes=this.e.className;
        if(!classes) return false;//e不包含类名
        if(classes===c) return true;//完全匹配的类名
        //否则把c自身看作一个单词，利用正则表达式搜索c
        // \b 在正则表达式里代表单词的边界
        // String.prototype.search
        //noinspection JSAnnotator
        return classes.search("\\b"+c+"\\b")|= -1;
    };

    //如果c不存在，把c添加到e.className
    CSSClassList.prototype.add=function(c){
        if(this.contains(c)) return; //如果存在，什么都不做
        var classes=this.e.className;
        if(classes&&classes[classes.length-1]!=" ")
            c=" "+c; //如果需要加一个空格
        this.e.className+=c; //将c添加到className中
    };

    //将在e.className 中出现的所有c都删除
    CSSClassList.prototype.remove=function(c){
        //检查c是否是合法的类名
        if(c.length===0||c.indexOf(" ")!=-1)
            throw new Error("Invalid class name: "+c);
        //将所有作为单词的c和多余尾随空格删除
        var pattern=new RegExp("\\b"+c+"\\b\\s*","g'");
        this.e.className=this.e.className.replace(pattern,"");
    };


    //判断传入是否为对象
    function isObject(obj){
        var type=typeof obj;
        return type==='function'||type==='object'&&!!obj;
    }

    //generate random string
    function randomString(len){
        len=len||32;
        var chars='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
        var maxPos=chars.length;
        var pwd='';
        for(var i=0;i<len;i++){
            //Math.random()  乘以最大长度然后取整，就能生成随机的一位字符
            //随机的其实是这个index 下标
            pwd+=chars.charAt(Math.floor(Math.random()*maxPos));
        }
        return pwd;
    }

    //文本节点，兼容性的处理. IE innerText   FF没有innerText
    function textContent(element,value){
        var content=element.textContent; //检测textContent是否有定义
        if(value==undefined){ //没有传递value 返回当前文本
            if(content!==undefined) return content;
            else return element.innerText;
        }
        else{
            if(content!==undefined) element.textContent=value;
            else element.innerText=value;
        }

    }

    // 跨浏览器兼容的工具函数
    function addEvent(element,type,handler) {
        if(element.addEventListener) {
            element.addEventListener(type, handler,false);
        }
        else if(element.attachEvent) {
            element.attachEvent("on" + type, handler);
        }
        else {
            element["on" + type] = handler;
        }
    }

    function render(options){
        var classObj={};
        var type=options.type;
        var id=options.divId;
        var item=document.getElementById(id);
        var hintx=(options.type==='img')?msgHint['img']:msgHint['file'];
        var buttonText=(options.type==='img')?btnTxt['img']:btnTxt['file'];
        //generating random ID of picker and uploader
        picker+=randomString(5);
        uploadId+=randomString(5);
        //class  或者 id名的 长度限制？
        var uploadDom='<div id='+uploadId+' class='+classRoot+'>'+'<h3>'+options.uploadText+'</h3>'+
            '<div class='+classUploadList+' id='+listId+'></div>'+'<div id='+picker+'>'+hintx+'</div>';

        if(options.type==='file'){
            uploadDom+= '<div id='+btnId+' class="btn-danger '+classBtn+'">'+buttonText+'</div>';
        }
        uploadDom+='</div>';
        item.innerHTML+=uploadDom;
    }

    //初始化，创建上传实例
    function initUploader(options){
        if(!isObject(options)){
            console.log("error! it is not an object!");
            return ;
        }
        else if(!WebUploader.Uploader.support()){
            var error="上传组件不支持你的浏览器！请使用Chrome!";
            alert(error);
            return ;
        }
        else{
            //render the html first!
            render(options); //render the html dom
            //common setting
            var settings={
                method:'POST',
                // swf文件路径
                swf:swf,
                // 文件接收服务端。
                server:options.serverPath,
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                fileSizeLimit:options.fileSizeLimit||102400,
                pick: '#'+picker, // picker id
                chunked:true,
                chunkRetry:3,
                chunkSize:102400
            };
            if(options.type==='img'){
                settings['auto'] = true;
                settings['accept']={
                    title:imgTitle,
                    extensions:extendsion,
                    mimeTypes:'image/*'
                };
            }else if(options.type==='file'){
                settings['resize']=false;
            }
            var uploader=WebUploader.create(settings);
        }
        return uploader;
    }

// fileQueued   uploadProgress  uploadSuccess uploadError uploadComplete all
    function createUpload(options){
        var uploader=initUploader(options);
        var list=document.getElementById(listId);
        var state='pending';//

        //添加文件选择按钮
        if(options.number>1){
            for(var k=0;k<options.number;k++){
                uploader.addButton({
                    id:'picker'+randomString(2)+i,
                    innerHTML:'选择文件'+i
                });
            }
        }

        // 当有文件添加进来的时候,一次处理一张图片，每张图片加进来都会触发一次这个事件
        uploader.on('fileQueued',function(file){
            if(options.type==='file'){
                list.innerHTML+= '<div id="' + file.id + '" class="item">' +
                    '<h4 class="info">' + file.name + '</h4>' +
                    '<p class="state">等待上传...</p>' +'</div>' ;
            }else if(options.type==='img'){
                var li='<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +'<div class="info">' + file.name + '</div>' +
                    '</div>';
                list.innerHTML+=li;
                //创建缩略图
                //document.querySelector
                var img=document.querySelector('#'+file.id+' img');
                uploader.makeThumb( file, function( error, src ) {
                    if ( error ) {
                        //$img.replaceWith('<span>不能预览</span>');
                        var parentNode=img.parentNode;//获得img的父节点
                        var newNode=document.createElement('span');
                        newNode.appendChild(document.createTextNode('不能预览'));
                        parentNode.replaceChild(newNode,img);//replaceChild(newChild,oldChild)
                        return;
                    }
                    img.setAttribute( 'src', src );//js设置属性的值
                    //thumbnailWidth=options.thumbnailSize*ratio,
                    //thumbnailHeight=options.thumbnailSize*ratio;
                }, options.thumbnailSize*ratio, options.thumbnailSize*ratio );
            }else{
                alert('Please input correct args, file or img!');
                return;
            }

        });

        // 文件上传过程中创建进度条实时显示。
        //如何取代find方法？ li.querySelector?
        uploader.on('uploadProgress',function(file,percentage){
            //var $li = $( '#'+file.id ),
            var li=document.querySelector('#'+file.id),
                uploadBtn=document.getElementById(btnId),//$btn=$('#'+btnId);
                //$percent = $li.find('.progress .progress-bar');
                percent=li.querySelector('.progress .progress-bar');
            //上传按钮的提示信息
            var btnInfo=(options.type==='file')?btnTxt['file']:btnTxt['img'];

            // 避免重复创建
            // appendTo : Insert every element in the set of matched elements
            //  to the end of the target.
            //  (选择器)append(内容) 插入内容，被参数指定的内容
            //  (内容)appendTo(选择器)
            //  jQuery链式调用是return这个对象
            if ( !percent.length ) {
                // $percent = $('<div class="progress progress-striped active">' +
                //   '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                //   '</div>' +'</div>').appendTo( $li ).find('.progress-bar');
                //   理解是链式调用返回的是最后一个this，因为第一个函数
                //   中执行完后返回this，this执行下一个函数，下一个函数执行完之后
                //   又返回 this
                li.innerHTML+='<div class="progress progress-striped active">' +
                    '<div class="progress-bar" role="progressbar" style="width: 0%">' +
                    '</div>' +'</div>';
                percent=li.querySelector('.progress-bar');
            }

            // $li.find('p.state').text('上传中');
            textContent(li.querySelector('p.state'),'上传中');
            if(options.type==='file'){
                if ( state === 'uploading' ) {
                    //$btn.text('暂停上传');
                    textContent(uploadBtn,'暂停上传');
                    //$percent.css( 'width', percentage * 100 + '%' );
                    percent.style.width=percentage*100+'%';
                } else {
                    //depends on type
                    textContent(uploadBtn,btnInfo); //$btn.text(btnInfo);
                }
            }else{
                //$percent.css( 'width', percentage * 100 + '%' );
                percent.style.width=percentage*100+'%';
            }
        });

        //uploadSuccess
        uploader.on('uploadSuccess',function(file){
            var filenode=document.getElementById(file.id);
            var stateNode=filenode.querySelectorAll('p.state');
            if(options.type==='file'){
                //$( '#'+file.id ).find('p.state').text('已上传');
                for(var i=0;i<stateNode.length;i++)
                    //stateNode[i].textContent='已上传';
                    textContent(stateNode[i],'已上传');
            }else if(options.type==='img'){
                var classlist=new classList(filenode);
                classlist.add('upload-state-done');
            }else{
                console.log('option.type error');
            }
        });

        //Error handler
        uploader.on( 'uploadError', function( file ) {
            console.log('uploadError');
            var filenode=document.getElementById(file.id);
            var stateNode=filenode.querySelectorAll('p.state');
            var errorNode=filenode.querySelector('div.error');//just need one
            if(options.type==='file'){
                //$( '#'+file.id ).find('p.state').text('上传出错');
                for(var i=0;i<stateNode.length;i++){
                    textContent(stateNode[i],'上传出错');
                }
            }else if(options.type==='img'){
                //console.log(errNode);
                if(errorNode===null){//if not found return null
                    errorNode=document.createElement('div');//innerHTML+='<div class="error"></div>';
                    errorNode.setAttribute('class','error'); //errNode.className="error"
                    filenode.appendChild(errorNode);
                    errorNode=filenode.querySelector('div.error');//just need one
                }
                textContent(errorNode,'上传失败!');
            }else{
                console.log('option.type error');
            }
        });

        uploader.on( 'uploadComplete', function( file ) {
            var filenode=document.getElementById(file.id);
            var progress=filenode.querySelector('.progress');
            var uploadBtn=document.getElementById(btnId);
            if(options.type==='file'){
                //$( '#'+file.id ).find('.progress').fadeOut();
                progress.parentNode.removeChild(progress);
            }else if(options.type==='img'){
                //$( '#'+file.id ).find('.progress').remove();
                progress.parentNode.removeChild(progress);
            }else{
                console.log('option.type error');
            }
            if(uploadBtn)
                textContent(uploadBtn,'上传完成');
        });

        /**监听开始上传、停止上传、上传完成三个事件*/
        uploader.on('startUpload',function(){
            state = 'uploading';
            console.log('uploading..');
        });

        uploader.on('stopUpload',function(){
            state = 'paused';
            console.log('pause....');
        });

        uploader.on('uploadFinished',function(){
            state = 'done';
            console.log('finished..');
        });

//var eventHandler=
        (function(){
            var uploadBtn=document.getElementById(btnId);
            if(uploadBtn){
                addEvent(uploadBtn,'click',function(){
                    if ( state === 'uploading' ) {
                        uploader.stop();
                    } else {
                        uploader.upload();
                    }
                });
            }
            /*
             addEvent(,'click',function(){
             uploader.upload();
             });*/
        })();
    }

    $.fn.createUpload=createUpload;

})(jQuery,WebUploader);