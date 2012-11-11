<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>KindEditor JSP</title>
	<script type="text/javascript" charset="utf-8" src="lib/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript">
		KE.show({
			id : 'content1',
			imageUploadJson : 'upload_json.jsp',
			items : [
				'source', '|', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', '|', 'link', 'unlink', '-',
				'title', 'fontname', 'fontsize', '|', 'textcolor', 'bgcolor', 'bold',
				'italic', 'underline', 'strikethrough', 'removeformat', '|', 'image',
				'flash', 'media', 'advtable', 'hr', 'emoticons', '|', 'selectall'
			],
			resizeMode : 0,
			width : '700px',
			height : '450px'
			

		});
	</script>
</head>
<body>
	<%=htmlData%>
	<form name="example" method="post" action="demo.jsp">
		<textarea id="content1" name="content1" cols="100" rows="8" style="width:700px;height:200px;visibility:hidden;"><%=htmlspecialchars(htmlData)%></textarea>
		<br />
		<input type="submit" name="button" value="提交内容" /> (提交快捷键: Ctrl + Enter)
	</form>
</body>
</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>