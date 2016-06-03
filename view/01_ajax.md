#ajax            
            
----------------------------------------------------------------------------------------------------------            
说明：            
	如果需要像 HTML 表单那样 POST 数据，            
	请使用 setRequestHeader() 来添加 HTTP 头。            
	然后在 send() 方法中规定您希望发送的数据：            
	xmlhttp.open("POST","ajax_test.asp",true);            
	xmlhttp.setRequestHeader            
	("Content-type","application/x-www-form-urlencoded");            
	xmlhttp.send("fname=Bill&lname=Gates");            
            
-----------------------------------------------------------------------------------------------------------