function i_d(data){
	
	var dd = JSON.parse(data); 
		  
	  if(dd.status == 0){
		  alert(dd.msg);
		  return false;
	  }
	  var status = dd.result;
	  if(status == 1){
		  alert("성공");
		  location.href = "/test/resources/views/boardList.html";
	  }else {
		  alert("실패!");
	  }
} 

//w3schol에서 가져옴 
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (1*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function delCookie(cname) {
    var d = new Date();
    d.setTime(d.getTime() + (0*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=;" + expires + ";path=/";
}

function i_d2(data){
	
	var dd = JSON.parse(data); 
		  
	  if(dd.status == 0){
		  alert(dd.msg);
		  return false;
	  }
	  var status = dd.result;
	  if(status == 1){
		  alert("성공");
		  location.href = "/test/resources/board/list.html";
	  }else {
		  alert("실패!");
	  }
} 
