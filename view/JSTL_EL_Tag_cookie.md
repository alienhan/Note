## 前后台交互语言    
----------------------------------------------------------------------------------------------          
#JSTL          

<c:if> if and else          
<c:if var="flag" test=""></c:if>          
<c:if test="${!flag}"></c:if>          
          
----------------------------------------------------------------------------------------------          
#EL（Expression Language） 目的：为了使JSP写起来更加简单。          
          
1）获取变量值          
	${username}   <-后台返回的参数名称          
	取出某一范围中名称为username的变量。          
	因为没有指定哪一个范围的username，          
	所以它会依序从Page、Request、Session、Application范围查找。          
	假如途中找到username，就直接回传，          
	不再继续找下去，但是假如全部的范围都没有找到时，就回传null。          
          
2）[]与.运算符          
     EL 提供.和[]两种运算符来存取数据。          
     当要存取的属性名称中包含一些特殊字符，          
     如.或?等并非字母或数字的符号，就一定要使用 []。          
   例如：          
     ${user.My-Name}应当改为${user["My-Name"] }          
     如果要动态取值时，就可以用[]来做，而.无法做到动态取值。例如：          
     ${sessionScope.user[data]}中data 是一个变量          
          
          
3）等价关系：          
	< %=request. getParameter(“username”)% > 等价于 ${ param. username }          
	<%=request.getAttribute(“userlist”) %> 等价于$ { requestScope.userlist }          
	<%=user.getAddr( ) %> 等价于 ${user.addr}          
          
          
          
          
----------------------------------------------------------------------------------------------          
#Tag          
    
* jsp引用    
<%@ taglib uri="http://www.justtide.com/mytags" prefix="my"%>    
    
* mytag.tld    
	位置： WEB-INF    
				tld    
					mytag.tld    
	code:    
		
		<?xml version="1.0" encoding="UTF-8"?>    
		<taglib xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0"    
		xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd">    
		<description><![CDATA[自定义标签]]]]></description>    
		<display-name>my Tags</display-name>    
		<tlib-version>1.0</tlib-version>    
		<short-name>s</short-name>    
		<uri>http://www.justtide.com/mytags</uri>    
		<tag>    
			<description><![CDATA[jsp merchantType Tag]]></description>    
			<name>merchantType</name>    
			<tag-class>com.mendian.tag.MerchantTypeTag</tag-class>    
			<body-content>empty</body-content>    
			<attribute>    
				<description><![CDATA[prefix ]]></description>    
				<name>prefix</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[key ]]></description>    
				<name>key</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
		</tag>    
		<!-- problem report type list select -->    
		<tag>    
			<description><![CDATA[jsp problemReportTypeList Tag]]></description>    
			<name>problemReportTypeList</name>    
			<tag-class>com.mendian.tag.ProblemReportTypeListTag</tag-class>    
			<body-content>empty</body-content>    
			<attribute>    
				<description><![CDATA[html attr]]></description>    
				<name>id</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[html attr]]></description>    
				<name>name</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[html attr]]></description>    
				<name>style</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[html attr]]></description>    
				<name>cssClass</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[默认选中值]]></description>    
				<name>defaultValue</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[是否包含空选项]]></description>    
				<name>hasEmpty</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[是否包含空选项]]></description>    
				<name>readOnly</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
		</tag>    
	</taglib>    
	
* java    
	package com.mendian.tag;    
	import java.io.IOException;    
	import javax.servlet.jsp.JspException;    
	import javax.servlet.jsp.JspWriter;      
	import javax.servlet.jsp.tagext.TagSupport;      
	import com.mendian.common.util.StringUtil;    
	import com.mendian.config.Constants;    
	import com.mendian.config.ServerUtil;    
	import com.mendian.entity.DictCommon;    
	public class ProblemReportTypeListTag extends TagSupport {    
		private String id;    
		private String name;    
		private String style;    
		private String cssClass;    
		private String readOnly;    
		private String disabled;    
		private String defaultValue;    
		private boolean hasEmpty;    
    
		public String doSomeThing() {    
			StringBuffer sb = new StringBuffer("<select ");    
			if (StringUtil.isNotEmpty(id)) {    
				sb.append("id=\"").append(id).append("\"");    
			}    
			if (StringUtil.isNotEmpty(name)) {    
				sb.append("name=\"").append(name).append("\"");    
			}    
			if (StringUtil.isNotEmpty(style)) {    
				sb.append("style=\"").append(style).append("\"");    
			}    
			if (StringUtil.isNotEmpty(cssClass)) {    
				sb.append("class=\"").append(cssClass).append("\"");    
			}    
			if (StringUtil.isNotEmpty(readOnly)) {    
				sb.append("readOnly=\"").append(readOnly).append("\"");    
			}    
			if (StringUtil.isNotEmpty(disabled)) {    
				sb.append("disabled=\"").append(disabled).append("\"");    
			}    
			sb.append(">");    
			if (hasEmpty) {    
				sb.append("<option></option>");    
			}    
			for (DictCommon dc : ServerUtil.findDictCommonList(Constants.DIC_PROBLEM_REPORT)) {    
				sb.append("<option ");    
				if (StringUtil.isNotEmpty(defaultValue) && String.valueOf(dc.getDKey()).equals(defaultValue)) {    
					sb.append("selected=\"selected\" ");    
				}    
				sb.append("value=\"").append(dc.getDKey()).append("\">").append(dc.getDValue()).append("</option>");    
			}    
			sb.append("</select>");    
			return sb.toString();    
		}    
    
		public int doEndTag() throws JspException {    
			String result = doSomeThing();    
			if (result != null) {    
				JspWriter out = this.pageContext.getOut();    
				try {    
					out.print(result);    
				} catch (IOException e) {    
					e.printStackTrace();    
				}    
			}    
			return 6;    
		}    
    
		public String getId() {    
			return id;    
		}    
    
		public void setId(String id) {    
			this.id = id;    
		}    
    
		public String getName() {    
			return name;    
		}    
    
		public void setName(String name) {    
			this.name = name;    
		}    
    
		public String getStyle() {    
			return style;    
		}    
    
		public void setStyle(String style) {    
			this.style = style;    
		}    
    
		public String getCssClass() {    
			return cssClass;    
		}    
    
		public void setCssClass(String cssClass) {    
			this.cssClass = cssClass;    
		}    
    
		public String getDefaultValue() {    
			return defaultValue;    
		}    
    
		public void setDefaultValue(String defaultValue) {    
			this.defaultValue = defaultValue;    
		}    
    
		public boolean isHasEmpty() {    
			return hasEmpty;    
		}    
    
		public void setHasEmpty(boolean hasEmpty) {    
			this.hasEmpty = hasEmpty;    
		}    
    
		public String getReadOnly() {    
			return readOnly;    
		}    
    
		public void setReadOnly(String readOnly) {    
			this.readOnly = readOnly;    
		}    
    
		public String getDisabled() {    
			return disabled;    
		}    
    
		public void setDisabled(String disabled) {    
			this.disabled = disabled;    
		}    
	}    
----------------------------------------------------------------------------------------------          
#cookie          
          
* js 操作cookie:          
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