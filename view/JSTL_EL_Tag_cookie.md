## ǰ��̨��������    
----------------------------------------------------------------------------------------------          
#JSTL          

<c:if> if and else          
<c:if var="flag" test=""></c:if>          
<c:if test="${!flag}"></c:if>          
          
----------------------------------------------------------------------------------------------          
#EL��Expression Language�� Ŀ�ģ�Ϊ��ʹJSPд�������Ӽ򵥡�          
          
1����ȡ����ֵ          
	${username}   <-��̨���صĲ�������          
	ȡ��ĳһ��Χ������Ϊusername�ı�����          
	��Ϊû��ָ����һ����Χ��username��          
	�������������Page��Request��Session��Application��Χ���ҡ�          
	����;���ҵ�username����ֱ�ӻش���          
	���ټ�������ȥ�����Ǽ���ȫ���ķ�Χ��û���ҵ�ʱ���ͻش�null��          
          
2��[]��.�����          
     EL �ṩ.��[]�������������ȡ���ݡ�          
     ��Ҫ��ȡ�����������а���һЩ�����ַ���          
     ��.��?�Ȳ�����ĸ�����ֵķ��ţ���һ��Ҫʹ�� []��          
   ���磺          
     ${user.My-Name}Ӧ����Ϊ${user["My-Name"] }          
     ���Ҫ��̬ȡֵʱ���Ϳ�����[]��������.�޷�������̬ȡֵ�����磺          
     ${sessionScope.user[data]}��data ��һ������          
          
          
3���ȼ۹�ϵ��          
	< %=request. getParameter(��username��)% > �ȼ��� ${ param. username }          
	<%=request.getAttribute(��userlist��) %> �ȼ���$ { requestScope.userlist }          
	<%=user.getAddr( ) %> �ȼ��� ${user.addr}          
          
          
          
          
----------------------------------------------------------------------------------------------          
#Tag          
    
* jsp����    
<%@ taglib uri="http://www.justtide.com/mytags" prefix="my"%>    
    
* mytag.tld    
	λ�ã� WEB-INF    
				tld    
					mytag.tld    
	code:    
		
		<?xml version="1.0" encoding="UTF-8"?>    
		<taglib xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0"    
		xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-jsptaglibrary_2_0.xsd">    
		<description><![CDATA[�Զ����ǩ]]]]></description>    
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
				<description><![CDATA[Ĭ��ѡ��ֵ]]></description>    
				<name>defaultValue</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[�Ƿ������ѡ��]]></description>    
				<name>hasEmpty</name>    
				<required>false</required>    
				<rtexprvalue>true</rtexprvalue>    
			</attribute>    
			<attribute>    
				<description><![CDATA[�Ƿ������ѡ��]]></description>    
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
          
* js ����cookie:          
��ӣ�          
	document.cookie = "agentMerNo=" + $("#agentMerNo").val();          
          
��ȡ��          
	      
	$("#agentMerNo option[value='" + getCookie("agentMerNo") + "']").attr(          
					"selected", "selected");          
	function getCookieVal(offset) { //ȡ��������Ϊoffset��cookieֵ           
		var endstr = document.cookie.indexOf(";", offset);//��ȡ�ַ�������          
		if (endstr == -1)          
			endstr = document.cookie.length;          
		return unescape(document.cookie.substring(offset, endstr));          
	}          
          
	function getCookie(name) { //ȡ������Ϊname��cookieֵ           
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