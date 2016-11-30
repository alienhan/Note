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
js submit file

#gameIcon 为<input type="file" name="gameIcon"/>
$("#gameIcon").change(function(){
        var file = this.files[0];
        console.log(file);
        var reader = new FileReader();
        reader.readAsDataURL(file); 
        reader.onload = function(e){ 
        	var form = new FormData();// ??只有form格式可以传数据
        	form.append("uploadFile", file);
        	var settings = {
        	  "async": true,
        	  "crossDomain": true,
        	  "url": ctx + "/fileAction/commonFileUpLoad.action",
        	  "method": "POST",
        	  "headers": {
        	    "cache-control": "no-cache",
        	  },
        	  "processData": false,
        	  "contentType": false,
        	  "mimeType": "multipart/form-data",
        	  "data": form
        	}

        	$.ajax(settings).done(function (response) {
        	  console.log(response);
        	});
        
        }; 
	});

---------------------------------------------------------------------------------------------------------
spring js 序列化传参 serialize