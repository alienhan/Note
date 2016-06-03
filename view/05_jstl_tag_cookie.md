1. jstl
2. tag
3. cookie

..............................................................................................
1. jstl

<c:if> if and else
<c:if var="flag" test=""></c:if>
<c:if test="${!flag}"></c:if>

----------------------------------------------------------------------------------------------
2. tag



----------------------------------------------------------------------------------------------
3. cookie

js 操作cookie、
添加：
document.cookie = "agentMerNo=" + $("#agentMerNo").val();

获取：
$("#agentMerNo option[value='" + getCookie("agentMerNo") + "']").attr(
				"selected", "selected");
function getCookieVal(offset) { //取得项名称为offset的cookie值 
	var endstr = document.cookie.indexOf(";", offset);//获取字符串长度

	if (endstr == -1)
		endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

function getCookie(name) { //取得名称为name的cookie值 
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal(j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0)
			break;
	}
	return "";
}

----------------------------------------------------------------------------------------------