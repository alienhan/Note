error
������������
����ԭ��
������
------------------------------------------------------------------------------------------------
1.hibernate
Caused by: java.lang.ClassNotFoundException:
javax.persistence.EntityListene
�õ���Hibernate3.6.0.final,
�����������Ŀ���ԭ����û�е���
hibernate-jpa-2.0-api-1.0.0.Final.jar�����

------------------------------------------------------------------------------------------------
2.eclipse
.����֪���Ĵ���
Servlet.service() for servlet [jsp] in context with path
[/dew] threw exception [javax.servlet.Servl
�����project ->clean ->��յ�ǰ�������е���Ŀ���档����tomcat

------------------------------------------------------------------------------------------------
3. java API
java.lang.NumberFormatException: For input string: ""
����ǿ��ת��һ��StringΪIntegerʱ,
�����StringΪ""ʱ���쳣,�����String����תΪInteger���쳣

------------------------------------------------------------------------------------------------
4. struts��springMVC  ������������

url��ֵ��Action��������������
	jspҳ�� URL�����Ĳ�����Action����������룬
�ù������Ϳ�������������ˣ�������������Сʱ������С���⣡---------

ԭ��
Ĭ������£�tomcatʹ�õĵı��뷽ʽ��iso-8859-1

������������¼��֣�
��һ�֣���Action���� new String(str.getBytes("ISO-8859-1"), "UTF-8")������ת�룬��Ϊ���ݵ�ʱ������Ĭ�˵���ISO-8859-1
					 new String(str.getBytes("GBK"), "ISO-8859-1")

�ڶ��֣���ͨ������TOMCAT����������⣬�������������£���tomcat��server.xml��ҵ�<Connector port="8080" 
              maxThreads="150" minSpareThreads="25" maxSpareThreads="75" 
              enableLookups="false" redirectPort="8443" acceptCount="100" 
              debug="0" connectionTimeout="20000" 
              disableUploadTimeout="true" useBodyEncodingForURI="true" URIEncoding="gbk">
�������޸� disableUploadTimeout="true" useBodyEncodingForURI="true" URIEncoding="gbk" �䷽���Ƿ�ֹ��url�г�������
Ȼ����ÿ��Jspҳ��������´���
<%@ page pageEncoding=��gb2312��%>
<%@ page contentType=��text/html;charset=gb2312��%>
<%request.setCharacterEncoding(��gb2312��);%>

�����֣�ֱ����ACTION���������·��������request.setCharacterEncoding("gb2312");
        response.setCharacterEncoding("gb2312");

action,controller ����ҳ���ֵ���룺
	ǰ̨�������Ĳ���
	str = new String(str.getBytes("ISO-8859-1"),"utf-8");
	����ǰ̨�Ĳ���--�����ļ�ʱ
	String str2 = new String(str.getBytes("GBK"), "ISO-8859-1");

getBytes������
	String��getBytes()�����ǵõ�һ������ϵͳĬ�ϵı����ʽ���ֽ����顣
�����ʾ�ڲ�ͨOS�£����صĶ�����һ���� 
	String.getBytes(String decode)���������ָ����decode���뷵��ĳ�ַ����ڸñ����µ�byte�����ʾ����
byte[] b_gbk = "��".getBytes("GBK"); 
byte[] b_utf8 = "��".getBytes("UTF-8");
byte[] b_iso88591 = "��".getBytes("ISO8859-1");
���ֱ𷵻ء��С����������GBK��UTF-8��ISO8859-1�����µ�byte�����ʾ��
��ʱb_gbk�ĳ���Ϊ2��b_utf8�ĳ���Ϊ3��b_iso88591�ĳ���Ϊ1�� 

	����getBytes��Եģ�����ͨ��new String(byte[], decode)�ķ�ʽ����ԭ������С���ʱ��
���new String(byte[], decode)ʵ����ʹ��decodeָ���ı�������byte[]�������ַ�����
String s_gbk = new String(b_gbk,"GBK");
String s_utf8 = new String(b_utf8,"UTF-8");
String s_iso88591 = new String(b_iso88591,"ISO8859-1");

ͨ����ӡs_gbk��s_utf8��s_iso88591���ᷢ�֣�s_gbk��s_utf8���ǡ��С�����ֻ��s_iso88591��һ������ʶ���ַ���
Ϊʲôʹ��ISO8859-1���������֮���޷���ԭ���С����أ���ʵԭ��ܼ򵥣�
��ΪISO8859-1����ı�����У�������û�а��������ַ���
��ȻҲ���޷�ͨ��"��".getBytes("ISO8859-1");���õ���ȷ�ġ��С�����ISO8859-1�еı���ֵ�ˣ�
������ͨ��new String()����ԭ���޴�̸���ˡ�
��ˣ�ͨ��String.getBytes(String decode)�������õ�byte[]ʱ��
һ��Ҫȷ��decode�ı������ȷʵ����String��ʾ����ֵ�������õ���byte[]���������ȷ����ԭ��

------------------------ΪʲôҪת�����룺
��ʱ��Ϊ���������ַ���ӦĳЩ����Ҫ����http headerͷҪ�������ݱ���Ϊiso8859-1���룩��
���ܻ�ͨ���������ַ������ֽڷ�ʽ��������������
String s_iso88591 = new String("��".getBytes("UTF-8"),"ISO8859-1")��
�����õ���s_iso8859-1�ַ���ʵ���������� ISO8859-1�е��ַ����ڽ���Щ�ַ����ݵ�Ŀ�ĵغ�
Ŀ�ĵس�����ͨ���෴�ķ�ʽ
String s_utf8 = new String(s_iso88591.getBytes("ISO8859-1"),"UTF-8")
���õ���ȷ�����ĺ��֡��С��������ͼȱ�֤������Э��涨��Ҳ֧�����ġ� 

servlet�淶��
(1) HttpServletRequest.setCharacterEncoding()���� ����ֻ����������post�ύ��request body�ı������������get�����ύ��queryString�ı��롣�÷�������Ӧ�÷�����Ӧ�ò���ʲô�������post�����������ݡ��ܶ����²�û��˵����һ�㡣
(2) HttpServletRequest.getPathInfo()���صĽ������Servlet����������(decode)���ġ�
(3) HttpServletRequest.getRequestURI()���ص��ַ���û�б�Servlet������decoded����
(4) POST�ύ����������Ϊrequest body��һ���֡�
(5) ��ҳ��Httpͷ��ContentType("text/html; charset=GBK")�����ã�
    (a) �����������ҳ��������ʲô���룻
    (b) ���ύʱ��ͨ������������ContentTypeָ����charset�Ա��е����ݱ��룬Ȼ���͸��������ġ�
������Ҫע����ǣ�������˵��ContentType��ָhttpͷ��ContentType������������ҳ��meta�е�ContentType��

-------------------------------------------------------------------------------------------------------------
5 ctrl + c ??tomcat

-------------------------------------------------------------------------------------------------------------
6.required="true"
  stuts ��ǩ������ʹ��

-------------------------------------------------------------------------------------------------------------
7.<A href="javascript:void(0)">���</a>������Ӻ󲻻�ص���ҳ����

-------------------------------------------------------------------------------------------------------------
No row with the given identifier exists
�����ű�,table1��table2.
�����������ԭ�����table1
�����˹���<one-to-one>����<many-to-one unique="true">
(����Ķ��һӳ��,ʵ�ʾ���һ��һ)
������table2.��hibernate���ҵ�ʱ��,
table2�������û����table1��ƥ���,
�����ͻᱨNo row with the given identifier exists�����.
(һ�仰,�������ݵ�����!)

-------------------------------------------------------------------------------------------------------------
9.SQL Server 2005�����ݼ��е����ݿⱸ�������е����ݿⲻͬ���������
http://www.jb51.net/article/19233.htm

-------------------------------------------------------------------------------------------------------------
10.
java.lang.NumberFormatException: For input string: ""
����ת��������
-------------------------------------------------------------------------------------------------------------

11. xgb ����
ClientAbortException: java.net.SocketException:
Connection reset by peer: socket write error
ԭ�����£�
1���������һ���ǿͻ��������ӻ�û����ȫ������ʱ���ȡ�����ӣ�
�����û��������������ġ�ֹͣ����ť��һ����˵û��ʲô���⡣
�������Ƶ�����֣��ͱ�ʾ�ܶ�ͻ������ӵ�Apache����������Ӧʱ��̫���ˣ�
�����������������߷������������� ��������������Ӵ���һЩ���⣬
������ݴ����ʱ�򣬿�������ʱ��ȴ���̫�ã�
����server�����õ����Ӽ���ʱ������һ����
��ô�Ϳ��ܳ�����������ģ� 
-------------------------------------------------------------------------------------------------------------
12��xgb ����
ClientAbortException: java.net.SocketException:
Connection reset by peer: socket write error
��ԭ�������ڴ���http����ʱ�������������ʱ��
�û��ر���IE�������һ��"ClientAbortException"��
����I/O�����г��ֵ�һ���쳣��Ӧ�÷�����Ӧ�ûᲶ׽��
3��Ӧ�����̱߳�ռ�õ�̫���� �޷������� 
������ܻ��ǣ�.���ݿ�����Ӵﵽ����
��.Ӧ�÷����� ����̲߳����� �ﵽ���ޣ�.�ڴ�ľ�

-------------------------------------------------------------------------------------------------------------

13	string
���Ͳ�ѯʱ����
   stringǿ��ת��int ʱ��null," " ���ܱ�ת��
-------------------------------------------------------------------------------------------------------------
14.	����
socket write error
����쳣��ԭ���ǣ����ҳ����һЩ��̬���ɵĶ�����������Խϴ�
��ķ�������ͻ��˷������ݣ�����շ�һ�룬�ͻ��˹ر������ӣ���������������

һ����������֤�������ĵط��� ��֮����쳣�Ǻ���ģ������Ƿ�����tomcat�ҵ����Ҳ���Ϊ�����ԭ��

��������ⲿ�ֳ����д���

-------------------------------------------------------------------------------------------------------------
15.	����
Java heap space
tomcat���治��

-------------------------------------------------------------------------------------------------------------
16.	serlet
String filePath = request.getSession().getServletContext().getRealPath("/");
request.getSession().getServletContext() ��ȡ����Servlet��������
�൱��tomcat�����ˡ�getRealPath("/") ��ȡʵ��·������/��ָ����Ŀ��Ŀ¼
�����Դ��뷵�ص�����Ŀ�������е�ʵ�ʷ������еĸ�·����
��I:\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\
wtpwebapps\UMPWeb_20131230\

-------------------------------------------------------------------------------------------------------------
17.	eclipse ����Ĭ�ϱ���ΪUtf-8
��Ҫ���õļ����ط�Ϊ��

 Window->Preferences->General ->Content Type->Text->JSP ����������ΪUTF-8

 Window->Preferences->General->Workspace ���Text file encoding ѡ��UTF-8
 Window->Preferences->Web->JSP Files���ѡ�� ISO 10646/Unicode(UTF-8)

-------------------------------------------------------------------------------------------------------------
18. eclipse
	��Ŀ����!
    ��ǰ���õİ�����Ϊ·���ĵ�����
    �Ҳ�����
    buildpath�Ϳ���

-------------------------------------------------------------------------------------------------------------
19	Tomcat
Tomcat����Document base ����does not exist or is not a readable directory
ɾ��eclipse�е�tomcat�ͺ���

-------------------------------------------------------------------------------------------------------------
20	log4j
log4j:ERROR LogMananger.repositorySelector was null likely due to error in class reloading, usin (2011-05-10 19:54:32)
��ǩ�� ��̸	���ࣺ javascript_SSH
Today I got this error message from Tomcat 6.0.24:
log4j:ERROR LogMananger.repositorySelector was null likely due to error
in class reloading, using NOPLoggerRepository.

The reason for the error is a new listener in Tomcat 6.0.24. You can fix this error by adding this line:
org.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false
to the "conf/catalina.properties" file in your tomcat directory.

-------------------------------------------------------------------------------------------------------------
21 JSP
org.apache.jasper.JasperException: Unable to compile class for JSP: 

An error occurred at line: 23 in the generated java file
The method getJspApplicationContext(ServletContext) is undefined for the type JspFactory

��Ŀ������jsp-api.jar servlet-api.jar��tomcat6��lib��ͬ���İ���
���汾��tomcat6�İ汾Ҫ�ͣ������е�ʱ����Ϊ�����ȼ�����Ŀ�еİ���
���� �����˴��󣬰���Ŀ�к�tomcat��ͬ�İ�ɾ��(����ɾ��������滻������������Ĵ���)��
����Ϳ��Խ���ˡ�

-------------------------------------------------------------------------------------------------------------
22. �淶--wonders
xhzs
���������淶��
	1.java�շ�������������ĸ��Сд��ͷ��ÿ����������ĸ��д����һ�����ʳ��⣩
	2.������ʾһ����Ϊ��������һ�ֶ����������һ�����ʻ��߶��ʴ�����ߵ�һ������Ϊһ�����ʡ�
		ҳ����ת��������to+XXX
		ҳ���ѯ��������query+XXX��find+XXX��get+XXX��load+XXX��search+XXX
		ҳ�������������update+XXX, modify+XXX
		ҳ�汣����������save+XXX
		ҳ��ɾ����������remove+XXX��delete+XXX

-------------------------------------------------------------------------------------------------------------
23 spring fileupload �ϴ� �ļ��ϴ� ���ļ��ϴ�
xhzs
user jar��
	ʹ��commons-fileupload.jar�ϴ�
code��
	html��
	<form id="formInfo" method="post" action="" enctype="multipart/form-data">
		<input id="realFile" type="file" name="realFile" class="inp_w45" />
	</form>

	java��
	boolean isFileUpload = ServletFileUpload.isMultipartContent(request);
	if (isFileUpload) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("realFile");//readFile��ǰ̨input��nameֵ
		if (file.getSize() == 0) { // �ϴ��ļ���СΪ0,�������ϴ��ļ�������ѯ����
		} else if (file.getSize() != 0) { // �ϴ��ļ���С��Ϊ0,�������ϴ��ļ�������ѯ����
			InputStream in = file.getInputStream();
			String fileName = file.getOriginalFilename();//Originalԭ����
			//�б��ϴ��ļ��ǲ���excel�ļ�
			if (fileName.endsWith("xls")) {
				fileType1 = "xls";
			} else if (fileName.endsWith("xlsx")) {
				fileType1 = "xlsx";
			}
		}
	}
----------------------------------------------------------------------------------------------------------------
24 excel  ��ȡexcel д��excel ����excel
xhzs
code��
/**
 * ��ȡexcel�ļ�����
 * 
 * @param is�����ֽ���
 * @param isXlsOrXlsx�ļ�����
 * @return
 * @throws IOException
 */
private void readExcel(InputStream is,
			String isXlsOrXlsx) throws IOException {
	/** ���ݰ汾ѡ�񴴽�Workbook�ķ�ʽ */
	Workbook wb = null;

	if ("xls".equals(isXlsOrXlsx)) {
		wb = new HSSFWorkbook(is);
	} else {
		wb = new XSSFWorkbook(is);
	}
	for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
		Sheet sheet = wb.getSheetAt(numSheet);
		if (sheet == null) {
			continue;
		}
		/** �õ�Excel������ */
		int totalRows = sheet.getPhysicalNumberOfRows();
		// ѭ����Row
		for (int rowNum = 0; rowNum < totalRows; rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row != null) {
				if (row.getCell(0) != null) {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					String value = row.getCell(0).getStringCellValue(); // ��ȡ��һ�е�Ԫ���ֵ
					if (StringUtils.isNotEmpty(value)) {
						//д�뵽pojo
					}
				}
			}
			//list��Ӷ��pojo
		}
	}
}
--------------------------------------------------------------------------------------------------------
25 hibernate annotation��������    hibernate����  hibernate˳��

@OneToMany(cascade = { CascadeType.PERSIST ,CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "meetingInfo")
@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OrderBy("uploadTime asc")//���ݹ�������ϴ�ʱ������
public Set<MeetingAttach> getMeetingAttachs() {
	return meetingAttachs;
}
--------------------------------------------------------------------------------------------------------
26 jquery valicate jquery�ύ��֤ jquery��֤ ����֤���jquery validation  xhzs

<!--���� jquery validate ���js -->
<script src="${assets_path}/assets/js/jquery.validate.min.js"></script>
<script src="${assets_path}/assets/js/messages_zh.min.js"></script>

Ĭ��У�����
���	����	        ����
1	required:true	    ����������ֶΡ�
2	remote:"check.php"	ʹ�� ajax �������� check.php ��֤����ֵ��
3	email:true	        ����������ȷ��ʽ�ĵ����ʼ���
4	url:true			����������ȷ��ʽ����ַ��
5	date:true			����������ȷ��ʽ�����ڡ�����У�� ie6 �������á�
6	dateISO:true		����������ȷ��ʽ�����ڣ�ISO�������磺2009-06-23��1998/01/22��ֻ��֤��ʽ������֤��Ч�ԡ�
7	number:true			��������Ϸ������֣�������С������
8	digits:true			��������������
9	creditcard:			��������Ϸ������ÿ��š�
10	equalTo:"#field"	����ֵ����� #field ��ͬ��
11	accept:				����ӵ�кϷ���׺�����ַ������ϴ��ļ��ĺ�׺����
12	maxlength:5			���볤������� 5 ���ַ�����������һ���ַ�����
13	minlength:10		���볤����С�� 10 ���ַ�����������һ���ַ�����
14	rangelength:[5,10]	���볤�ȱ������ 5 �� 10 ֮����ַ�����������һ���ַ�����
15	range:[5,10]		����ֵ������� 5 �� 10 ֮�䡣
16	max:5				����ֵ���ܴ��� 5��
17	min:10				����ֵ����С�� 10��

jquery.validate.min.js���Դ���messages���Ǵ��������ʾ 
messages: {
    required: "This field is required.",
    remote: "Please fix this field.",
    email: "Please enter a valid email address.",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    dateDE: "Bitte geben Sie ein g��ltiges Datum ein.",
    number: "Please enter a valid number.",
    numberDE: "Bitte geben Sie eine Nummer ein.",
    digits: "Please enter only digits",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: $.validator.format("Please enter no more than {0} characters."),
    minlength: $.validator.format("Please enter at least {0} characters."),
    rangelength: $.validator.format("Please enter a value between {0} and {1} characters long."),
    range: $.validator.format("Please enter a value between {0} and {1}."),
    max: $.validator.format("Please enter a value less than or equal to {0}."),
    min: $.validator.format("Please enter a value greater than or equal to {0}.")
},

code��
//У��
$('#addMeetingInfo').validate({
	rules : {
		'meetingName':{required:true,maxlength:100},
		'meetingType':{required:true}
	},messages : {
		'meetingName':{required:"������������ƣ�",maxlength:"�������Ʋ��ܳ���33�֣�"},
		'meetingType':{required:"��ѡ���������"}
	},
	submitHandler:function() {
		var success = true;
		if(success) {
			var isNecessary = "${dicAttachTypeList[0].isNecessary}";
			//��ȡ
			if(isNecessary == 1){
				
			}else{
				var form=document.getElementById("addMeetingInfo");
				var url=form.action;
				url=url+"?status="+status;
				form.action=url;
				document.getElementById("addMeetingInfo").action=url;
				form.submit();
			}
		}
	}
});

code2��
$(document).ready(function validateFrom (argument) {
     $("#form").validate({
          rules:{
               name:{		//input name ֵ
                    required:true,
                    minlength:2,
                    maxlength:10
               },
               IDNumber:{
                    required:true,
                    minlength:15,
                    maxlength:18
               }
          },
          messages:{
               name:{
                    required:"������д�û���",
                    minlength:"�û�����СΪ2λ",
                    maxlength:"�û������Ϊ10λ"
               },
               IDNumber:{
                    required:"���֤����Ϊ��",
                    minlength:15,
                    maxlength:18
               }
          }
     });
});

validate()���������
submitHandler(form) ͨ����֤�����еĺ��������Լ��ϱ��ύ�ķ���
invalidHandler ��Ч���ύ�����еĺ���
ignore ��ĳЩԪ�ز�������֤
rules ����������
messages ������ʾ��Ϣ
groups ��һ��Ԫ�ص���֤����һ��������ʾ����errorPlacement���ưѴ�����Ϣ��������
onsubmit �ύʱ�Ƿ������֤��Ĭ����true���ύ��ʱ�������֤
onfocusout �Ƿ��ڻ�ȡ����ʱ��֤
onkeyup �Ƿ����û�����ʱ��֤
onclick �Ƿ��������ʱ��֤��һ������checkbox
focusInvalid �ύ����δͨ����֤�ı��Ƿ��ý���
focusCleanup ��δͨ����֤��Ԫ�ػ�ȡ����ʱ���Ƿ��Ƴ�������ʾ
errorClass ָ��������ʾ��css�����������Զ��������ʾ��ʽ
validClass ָ����֤ͨ��ʹ�õ�css������
errorElement ʹ��ʲô��ǩ��Ǵ���
wrapper ʹ��ʲô��ǩ���ϱߵ�errorElement������
errorLabelContainer �Ѵ�����Ϣͳһ����һ����������
errorContainer ��ʾ����������֤��Ϣ�������Զ�ʵ���д�����Ϣ����ʱ���������Ա�Ϊ��ʾ���޴���ʱ����
showErrors(errorMap,errorList) ��ʾ�ܹ��ж��ٸ�δͨ����֤��Ԫ��
errorPlacement ���������Ϣ��������
success: Ҫ��֤��Ԫ��ͨ����֤��Ķ���
highlight(element,errorClass,validClass) ���Ը�δͨ����֤��Ԫ�ؼ�Ч��
unhighlight ȥ��δͨ����֤��Ԫ�ص�Ч����һ���highlightͬʱʹ��

ѡ������չ
:blank ѡ������ֵΪ�յ�Ԫ��
:filled ѡ������ֵ��Ϊ�յ�Ԫ��
:unchecked ѡ������û�б�ѡ�е�Ԫ��

�Զ�����֤����:
jQuery.validator.addMethod(name,method,[message])
name ��������
method: function(value,element,params) �����߼�
messages:��ʾ��Ϣ

.....................................
jquery valicate ������ʽ ƥ��  ����µ��Լ��ĺ���������Լ��ĺ���
	$(document).ready(function(){
		//��ϵ�绰(�ֻ�/�绰�Կ�)��֤   
		$.validator.addMethod("isPhone", function(value,element) {   
			var length = value.length;    
			var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;   
			var tel = /^\d{3,4}-?\d{7,9}$/;   
			return this.optional(element) || (tel.test(value) || mobile.test(value));
		}, "����ȷ��д������ϵ�绰");   
		$('#formStatus').validate({
			rules:{
				'contactTel':{isPhone:true}
			},
			messages:{
				'contactTel':{isPhone:"������һ����Ч����ϵ�绰"}
			},
			submitHandler:function(){
				_ajax_submit($('#formStatus'),return2Todo);
			}
		});
		
	})
	function return2Todo(responseText) {
		parent.parent.$('#dialogDiv').dialog('close');
	}


--------------------------------------------------------------------------------------------------------
27 jquery ajax ajax ajax�ύ 
	//ajax�ύ����
	var meetingYear = $("#meetingYear").val();
	var meetingType = $("#meetingType").val();
	//����ģʽ
	var data = {
		meetingYear:meetingYear,
		meetingType:meetingType
	};
	var url = _path + 'xhzs/policysupport/getMeetingNo';
	$.ajax({
		type : "post",
		url : url,
		data : data,
		dataType : "text",
		success : function(data) {
			var obj = eval("("+data+")");
			$("#meetingNo").val(obj.object);
		},
		error : function(data, textStatus) {
			var obj = eval("("+data+")");
			alert("������ݲ���ȷ");
		}
	}); 
--------------------------------------------------------------------------------------------------------
28 jquery ui dialog dialog�Ի���








--------------------------------------------------------------------------------------------------------
29 svn���� �����ύ���� ���벻�ܸ���
org.apache.subversion.javahl.ClientException: Attempted to lock an already-locked dir
svn: Working copy 'D:\workspace2\xhzs-sabic' locked.
svn: 'D:\workspace2\xhzs-sabic' is already locked.

�һ���Ŀ��-> Team -> Refresh/cleanUp


--------------------------------------------------------------------------------------------------------
30 sql ת�嵥����
code:
	insert into dic_load_config values('dic_unit_tag','��ҵ��ʾ�ֵ�','1','DIC_ITEM','CODE','NAME','WHERE DIC_ID=''73''','','')


--------------------------------------------------------------------------------------------------------
31 jsp:include ��ֵ   jsp:param   
��ֵ��
	<jsp:include flush="true" page="../unitdailymanager/openDetailUnitDeclare.jsp" >
		<jsp:param value="unit_tag" name="unit_tag"/>
	</jsp:include>
	����ֵ��
	<%
		String unit_tag=request.getParameter("unit_tag"); 
	%>

--------------------------------------------------------------------------------------------------------
32 java error map map.entry ����map
	  �޸ģ��������ı�
java.util.ConcurrentModificationException
��ʹ��iterator.hasNext()������������ʱ�������ʱ�����Ķ������ı䣬��������������ݣ����������ݱ�ɾ����

��ʹ�ûᱨ�����쳣��
java.util.ConcurrentModificationException
        at java.util.HashMap$HashIterator.nextEntry(HashMap.java:793)
        at java.util.HashMap$KeyIterator.next(HashMap.java:828)
//����map
	public static Map<String, String> DicToSingle(String deptId, String dic) {
		Map<String, String> map = DicLoadUtil.getInstance().getDic(dic);
		if (!map.isEmpty()) {
			if (map.containsKey(deptId)) {
				Iterator<Map.Entry<String, String>> it = map.entrySet()
						.iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = it.next();
					String key = entry.getKey();
					if (!StringUtils.equals(deptId, key)) {
						it.remove();
					}
				}
			}
		}
		return map;
	} 
java Map��Map.Entry���
Map�ṩ��һЩ���÷�������keySet()��entrySet()�ȷ�����
keySet()��������ֵ��Map��keyֵ�ļ��ϣ�
entrySet()�ķ���ֵҲ�Ƿ���һ��Set���ϣ��˼��ϵ�����ΪMap.Entry��
Map.Entry��Map������һ���ڲ��ӿڣ��˽ӿ�Ϊ���ͣ�����ΪEntry<K,V>��
����ʾMap�е�һ��ʵ�壨һ��key-value�ԣ����ӿ�����getKey(),getValue������

	Map<String, String> map = ...
	for (Map.Entry<String, String> entry : map.entrySet())
	{
		System.out.println(entry.getKey() + "/" + entry.getValue());
	}

--------------------------------------------------------------------------------------------------------
33 jqery ���� <form:select> <input type="select"> Ĭ��ֵ  selectĬ��ֵ ����select selected
	$(function(){
		 $("#unitTag option[value='01']").attr("selected", true); 
	});


--------------------------------------------------------------------------------------------------------
34 form��
	�����ύ������̨  ����form���ص�

--------------------------------------------------------------------------------------------------------
35 tomcat myeclipse 
java.lang.UnsupportedOperationException: Manual close is not allowed over a Spring managed SqlSession

org.springframework.beans.factory.BeanCreationException: 
Error creating bean with name 'cashierAction' defined in file 
[D:\java\apache-tomcat-6.0.44-windows-x86\apache-tomcat-6.0.44\webapps\mendian-tms\WEB-INF\classes\com\mendian\action\appStore\CashierAction.class]:
Instantiation of bean failed; nested exception is org.springframework.beans.BeanInstantiationException: 
Could not instantiate bean class [com.mendian.action.appStore.CashierAction]: Constructor threw exception; nested exception is java.lang.Error: 
Unresolved compilation problems: 

do:
myeclise servers ���µ�����Ŀ
һ�㶼��δ���أ�δ���������


------------------------------------------------------------------------------------------------------------------
36 Caused by: java.lang.ClassNotFoundException:javax.persistence.EntityListene
ʹ��Hibernate3.6.0.final��������
ԭ����û�е���
hibernate-jpa-2.0-api-1.0.0.Final.jar�����


-----------------------------------------------------------------------------------------------------------------
37 org.springframework.beans.factory.BeanCreationException: 
   Error creating bean with name 'entityManagerFactory' defined in class path resource [beans.xml]

����ȱ���˰������������Ƕ���jpa�������п�������Ŀ��������һ��jpa����hibernate���Դ���һ�����Ұ汾��һ����
ɾ��hibernate-jpa-2.0-api-1.0.0.Final.jar
hibernate3.6final���Ѿ���Annotation��⼯�ɵ���hibernate3.6����������ʹ��Annotationʱ��
����hibernate3.6final��������Ҫ����hibernate-annotations-3.4.0.GA.zip�ˡ�

------------------------------------------------------------------------------------------------------------------

