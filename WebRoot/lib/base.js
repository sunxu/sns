var $ = function (id) {
	return "string" == typeof id ? document.getElementById(id) : id;
}

//删除两端的空格
function trim(str){
	return str.replace(/(^\s*)|(\s*$)|(　*)/g , "");
}

//计算字符字节数
function check_code_size(string_data){
	var chars = 0;	//字节数变量
	for (var i=0; i < string_data.length; i++) {
		var charinit = string_data.charCodeAt(i);
		if((charinit >= 0x0001 && charinit <= 0x007e) || (0xff60<=charinit && charinit<=0xff9f)) {
			chars++;	//单字节加1
		}else{
			chars+=2;	//双字节加2
		}
	}
	return chars;
}

//页头搜索框
jQuery(document).ready(function() {
	jQuery('#top_search_input').keyup(function(event) {
		if (event.keyCode == '13') {
			top_search_submit();
		}
	});
});
	
function inputTxt(obj,act){
	var str="输入姓名...";
	if(obj.value==''&&act=='set')
	{
		obj.value=str;
		obj.style.color="#cccccc"
	}
	if(obj.value==str&&act=='clean')
	{
		obj.value='';
		obj.style.color="#666666"
	}
}

function top_search_submit(){
	var text = trim($("top_search_input").value);
	if(text != "" && text != "输入姓名...")
		document.getElementById("top_search").submit();
}

//导航应用菜单
function apMenuShow(isShow)
{
	var apMenu = document.getElementById('ap_menu');
	if(apMenu)
	{
		if(isShow)
		{
			apMenu.style.display = '';
		}
		else
		{
			apMenu.style.display = 'none';
		}
	}
}

//导航设置菜单
function setMenuShow(isShow)
{
	var setMenu = document.getElementById('set_menu');
	var setMenuBridge = document.getElementById('set_menu_bridge');
	if(setMenu)
	{
		if(isShow)
		{
			setMenuBridge.style.display = '';
			setMenu.style.display = '';
		}
		else
		{
			setMenu.style.display = 'none';
			setMenuBridge.style.display = 'none';
		}
	}
}

//添加收藏夹
function addBookMark()
{
    var nome_sito = "聚易社区";
    var url_sito = "http://localhost/iwebsns/";
    if ((navigator.appName == "Microsoft Internet Explorer") && (parseInt
        (navigator.appVersion) >= 4))
        window.external.AddFavorite(url_sito, nome_sito);
    else if (navigator.appName == "Netscape")
        window.sidebar.addPanel(nome_sito, url_sito, '');
    else
        parent.Dialog.alert("您的浏览器不允许脚本访问收藏夹，请手动添加！");
}

//  设置首页
function setMyHomepage(url){    
		 if(!!(window.attachEvent && !window.opera)){
				document.body.style.behavior = 'url(#default#homepage)';
				document.body.setHomePage(url);
		}else{
			if(window.clipboardData && clipboardData.setData){
		        clipboardData.setData('text', url);
		    }else{
		         parent.Dialog.alert('您的浏览器不允许脚本访问剪切板，请手动设置！');
		    }
		}
}

var rand_value=Math.random();

//最大字符量限制
function isMaxLen(o){
	var nMaxLen=o.getAttribute? parseInt(o.getAttribute("maxlength")):"";
	if(o.getAttribute && strLen(o.value) > nMaxLen){
		o.value=getStrbylen(o.value,nMaxLen)
	}
}

//截取字符串
function getStrbylen(str, len) {
	var num = 0;
	var strlen = 0;
	var newstr = "";
	var obj_value_arr = str.split("");
	for(var i = 0; i < obj_value_arr.length; i ++) {
		if(i < len && num + byteLength(obj_value_arr[i]) <= len) {
			num += byteLength(obj_value_arr[i]);
			strlen = i + 1;
		}
	}
	if(str.length > strlen) {
		newstr = str.substr(0, strlen);
	} else {
		newstr = str;
	}
	return newstr;
}

//获取字符串长度
function strLen(str) {
	var charset = document.charset; 
	var len = 0;
	for(var i = 0; i < str.length; i++) {
		len += str.charCodeAt(i) < 0 || str.charCodeAt(i) > 255 ? (charset == "utf-8" ? 3 : 2) : 1;
	}
	return len;
}

function byteLength (sStr) {
	aMatch = sStr.match(/[^\x00-\x80]/g);
	return (sStr.length + (! aMatch ? 0 : aMatch.length));
}

//删除换行符
function deleteBr(str){
	return str.replace(/\r/ig,"").replace(/\n/ig,"");
}

//回复给somebody
function reply_article_to(args){
	var args = args.split("_");
	var textarea = jQuery("#"+args[0]+"_"+args[1]).find("textarea")
	textarea.attr("touid",args[3]);
	textarea.empty();
	textarea.val("回复"+args[2]+"：");
}

//检查评论内容
function check_article_comment(args,event){
	var textarea = jQuery("#"+args).find("textarea");
	textarea.val(deleteBr(textarea.val()));
	var nMaxLen=parseInt(textarea.attr("maxlength"));
	
	if(strLen(textarea.val()) > nMaxLen){
		textarea.val(getStrbylen(textarea.val(),nMaxLen));
	}
	if (event.keyCode == '13') {
		submit_article_comment(args);
	}
}

//提交评论
function submit_article_comment(args){
	var arg = args.split("_");
	var textarea = jQuery("#"+args).find("textarea");
	var button = jQuery("#"+args).find("button");
	var message = jQuery("#"+args).find("#message");
	var reply_text=trim(textarea.val());
	button.disabled = true;
	
	if(reply_text==''){
		parent.Dialog.alert("请填写回复内容!");
	}else{
		message.val('数据提交中...');
		jQuery.post(
			"comment_save.do",
			{ 'comment.touid' : textarea.attr("touid"), 
			  'comment.type' : arg[0], 
			  'comment.articleid' : arg[1], 
			  'comment.message' : reply_text,
			  lastid :  textarea.attr("lastid"),
			  rv: rand_value},
			function(json){
				if(json.status){
					$("button").disabled = false;
					message.val('');
					jQuery("#"+args+"_comment").append(commentToHtml(json.commentlist));	
					textarea.val('');
					textarea.attr("lastid",json.commentlist[0].id);
					jQuery(".article_comment_to_who").click(function(){
						reply_article_to(this.getAttribute("args"));
					});
				}else{
					message.innerHTML='回复提交失败！';
					$("button").disabled = false;
					message.val("回复提交失败!");
				}
			},
			"json");	
	}
}

//处理加载的评论
function commentToHtml(commentlist){
	var div;
	var append = '';
	jQuery.each(commentlist, function(index,comment){
		div = '<DIV class=reaction><A class=figure href="space.do?id='
			  +comment.fromuid+'" target=_blank><IMG src="'
			  +comment.avatarURLPath+'small.jpg"></A><A href="space.do?id='
			  +comment.fromuid+'" target=_blank>'
			  +comment.realname+'</A><LABEL>'
			  +comment.date+'&nbsp;&nbsp;&nbsp;&nbsp;';
		if(comment.fromuid != uid)
		div +='<A class="article_comment_to_who" args="'
			  +comment.type+'_'+comment.articleid+'_'+comment.realname+'_'+comment.fromuid
			  +'" href="javascript:void(0)">回复</A>';
		div +='</LABEL><P>'
			  +comment.message+'</P></DIV>';
		append = div + append;
		if(index == 9)
			return false
	});
	return append;
}

//加载评论
function load_comment(type,id){
	var div = jQuery("#"+type+"_"+id+"_comment");
	var textarea = div.next().find("textarea");

	jQuery.post(
	"comment.do",
	{ 'comment.type' : type, 
	  'comment.articleid' : id, 
	  'comment.beginId' : div.attr("beginid"),
	  rv: rand_value},
	function(json){
		if(json.status){
			div.prepend(commentToHtml(json.commentlist));	
			jQuery(".article_comment_to_who").click(function(){
				reply_article_to(this.getAttribute("args"));
			});
			textarea.attr("lastid",json.commentlist[0].id);
			div.attr("beginid",json.beginid);
			div.attr("hasmore",json.hasmore);
			if(div.attr("hasmore") == true){
				jQuery(".stat").show();
			}			
			
		}else if(div.attr("beginid") != 0){
			parent.Dialog.alert("评论加载失败！");
		}
	},
	"json");
}

function load_more_comment(type,id){
	var div = jQuery("#"+type+"_"+id+"_comment");
	
	if(div.attr("hasmore") == true){
		jQuery(".stat").show();
		jQuery(".stat").find("#stat_message").val("加载中...");
	}

	jQuery.post(
	"comment.do",
	{ 'comment.type' : type, 
	  'comment.articleid' : id, 
	  'comment.beginId' : div.attr("beginid"),
	  rv: rand_value},
	function(json){
		if(json.status){
			div.prepend(commentToHtml(json.commentlist));	
			jQuery(".article_comment_to_who").click(function(){
				reply_article_to(this.getAttribute("args"));
			});
			div.attr("beginid",json.beginid);
			div.attr("hasmore",json.hasmore);
			if(div.attr("hasmore") == true){
				jQuery(".stat").show();
				jQuery(".stat").find("#stat_message").val("");
			}else{
				jQuery(".stat").hide();
				jQuery(".stat").find("#stat_message").val("");
			}
		}else{
			parent.Dialog.alert("评论加载失败！");
			jQuery(".stat").find("#stat_message").val("");
		}
	},
	"json");
}

//好友列表样式变换
function changeStyle(obj){
	obj.className = 'hover';
}
function recoverStyle(obj){
	obj.className = '';
}

//添加好友请求
function friend_request_add(uid){
	jQuery.post(
	"friend_ajax.do",
	{type : 'request', friendid : uid, rv: rand_value},
	function(json){
		if(json.status){
			parent.Dialog.alert(json.message);
			document.getElementById("operate_"+uid).innerHTML=json.message;
		}else{
			parent.Dialog.alert(json.message);
			document.getElementById("operate_"+uid).innerHTML="";
		}
	},
	"json");
}

//Ajax动态展示框
function ajax_show_message(message){
	parent.Dialog.alert(message);
	jQuery("#_Draghandle_0 div:nth-child(2)").remove();
	jQuery("#_ButtonRow_0").hide();
	
}

function ajax_change_message(message){
	jQuery("#Message_undefined").text(message);
	
}

function ajax_message_close(){
	jQuery("#_ButtonCancel_0").click();
	
}