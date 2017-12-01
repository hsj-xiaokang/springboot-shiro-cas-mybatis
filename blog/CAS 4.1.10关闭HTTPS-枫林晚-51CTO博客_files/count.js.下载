var _ourplusCount = "https://logs.51cto.com/rizhi2";
var _debug;
var _ourplusType;
var _ourplusShow;
var _ourplusShowStr='';
var _ourplusIframe;

var _ourplusCountPage = _ourplusCount + "/count/count.php";
if( _ourplusIframe == true )
{
	var _ourplusPageurl = escape(location.href);
	var _ourplusReferer = escape(document.referrer);
}
else
{
	var _ourplusPageurl = escape(top.location.href);
	var _ourplusReferer = escape(top.document.referrer);
}



var _ourplusLanguage = (navigator.systemLanguage?navigator.systemLanguage:navigator.language);
var _ourplusColor = screen.colorDepth;
var _ourplusScreenSize = screen.width + '*' + screen.height;
var _ourplusCharset = document.charset


var _ourplusFirstTime;
var _ourplusLastTime;
_ourplusFirstTime = _ourplusReadCookie( '_ourplusFirstTime' );
if( _ourplusFirstTime == '' )
{
	_ourplusFirstTime = getTime();
	_ourplusLastTime = _ourplusFirstTime;
	_ourplusWriteCookie( '_ourplusFirstTime', _ourplusFirstTime, 10000 );
}
else
{
	_ourplusLastTime = getTime();
}

if( _ourplusType == null )
{
	_ourplusType = 1;
}

_ourplusReturnCount = _ourplusReadCookie( '_ourplusReturnCount' );
_ourplusReturnCount = _ourplusReturnCount == '' ? 0 : _ourplusReturnCount;

_ourplusReturnTime = _ourplusReadCookie( '_ourplusReturnTime' );
if( _ourplusReturnTime == '' )
{
	_ourplusReturnTime = getTime();
	_ourplusWriteCookie( '_ourplusReturnTime', _ourplusReturnTime, 10000 );
}

temp = _ourplusReturnTime.split( '-' )
_ourplusReturnTimeDate = new Date(temp[0], temp[1]-1, temp[2], temp[3], temp[4], temp[5] );
_ourplusNowTimeDate = new Date();

if( _ourplusNowTimeDate - _ourplusReturnTimeDate >= 43200000 )
{
	_ourplusWriteCookie( '_ourplusReturnCount', ++_ourplusReturnCount, 10000 );
	_ourplusWriteCookie( '_ourplusReturnTime', getTime(), 10000 );
}
else
{
	_ourplusReturnCount = null;
}


if( _ourplusShow != null && _ourplusShow.length > 0 )
{
	var _ourplusShowStr = '';
	for( i = 0; i < _ourplusShow.length; i ++ )
	{
		_ourplusShowStr += "&show[]=" + _ourplusShow[i];
	}
}
else
{
	var _ourplusShowStr = "";
}



var _ourplusCountUrl = _ourplusCountPage + '?'
+ '&counturl=' + _ourplusCount
+ '&pageurl=' + _ourplusPageurl
+ '&referer=' + _ourplusReferer
+ '&language=' + _ourplusLanguage
+ '&color=' + _ourplusColor
+ '&screensize=' + _ourplusScreenSize
+ '&debug=' + _debug
+ '&firsttime=' + _ourplusFirstTime
+ '&lasttime=' + _ourplusLastTime
+ '&type=' + _ourplusType
+ '&charset=' + _ourplusCharset
+ _ourplusShowStr
+ '&timezone=' + (new Date()).getTimezoneOffset()/60;

if( _ourplusReturnCount != null )
{
	_ourplusCountUrl += '&return1=' + _ourplusReturnCount;
}

var auth = document.cookie;
var authtmp = auth.split(";");  
for (i=0;i<authtmp.length ;i++ )    
{    
    var authtmp2 = authtmp[i].split("=");
    var authtmp3 = authtmp2[0].replace(/(^\s*)|(\s*$)/g, "");
    if(authtmp3 == "pub_sauth1")
    {
    	var auth2 = authtmp2[1];
    	break;
    } 
    authtmp2 = null;
} 

if(auth2)
{
	var _ourplusCountUrl2 = _ourplusCount + "/count/countuser2.php"+ '?auth=' + auth2+ '&pageurl=' + _ourplusPageurl;
}

if( _debug )
{
	document.write(_ourplusCountUrl);
	document.write("<iframe src='" + _ourplusCountUrl + "' width=2 height=2></iframe>");
	if(auth2)
	{
		document.write("<iframe src='" + _ourplusCountUrl2 + "' width=2 height=2></iframe>");		
	}
}
else
{
	document.write("<script src='" + _ourplusCountUrl + "'></script>");
	if(auth2)
	{
		document.write("<script src='" + _ourplusCountUrl2 + "'></script>");		
	}
}



//Functions

function getTime() 
{ 
	now = new Date(); 
	year=now.getYear();
	Month=now.getMonth()+1;
	Day=now.getDate();
	Hour=now.getHours(); 
	Minute=now.getMinutes(); 
	Second=now.getSeconds(); 
	return year+"-"+Month+"-"+Day+"-"+Hour+"-"+Minute+"-"+Second;
} 


function _ourplusReadCookie(name)
{
  var cookieValue = "";
  var search = name + "=";
  if(document.cookie.length > 0)
  { 
    offset = document.cookie.indexOf(search);
    if (offset != -1)
    { 
      offset += search.length;
      end = document.cookie.indexOf(";", offset);
      if (end == -1) end = document.cookie.length;
      cookieValue = unescape(document.cookie.substring(offset, end))
    }
  }
  return cookieValue;
}

function _ourplusWriteCookie(name, value, hours)
{
  var expire = "";
  if(hours != null)
  {
    expire = new Date((new Date()).getTime() + hours * 3600000);
    expire = "; expires=" + expire.toGMTString();
  }
  document.cookie = name + "=" + escape(value) + expire + "domain=;" + "path=/;";
}

 //document.writeln("<script src=\"http://www.51cto.com/images/homepage/scroll.js\"></script>");

/*************************数据分析 begin**********************************/

/*
   MD5()
   output: MD5 hash (as Hex String)
*/

var faultylabs = {};

faultylabs.MD5 = function(data) {

    // for test/debug
    function fflog(msg) {
        try {
            console.log(msg);
        } catch(e) {}
    }

    // convert number to (unsigned) 32 bit hex, zero filled string
    function to_zerofilled_hex(n) {
        var t1 = (n >>> 24).toString(16);
        var t2 = (n & 0x00FFFFFF).toString(16);
        return "00".substr(0, 2 - t1.length) + t1 +
        "000000".substr(0, 6 - t2.length) + t2;
    }

    // convert array of chars to array of bytes (note: Unicode not supported)
    function chars_to_bytes(ac) {
        var retval = [];
        for (var i = 0; i < ac.length; i++) {
            retval = retval.concat(str_to_bytes(ac[i]));
        }
        return retval;
    }


    // convert a 64 bit unsigned number to array of bytes. Little endian
    function int64_to_bytes(num) {
        var retval = [];
        for (var i = 0; i < 8; i++) {
            retval.push(num & 0xFF);
            num = num >>> 8;
        }
        return retval;
    }

    //  32 bit left-rotation
    function rol(num, places) {
        return ((num << places) & 0xFFFFFFFF) | (num >>> (32 - places));
    }

    // The 4 MD5 functions
    function fF(b, c, d) {
        return (b & c) | (~b & d);
    }

    function fG(b, c, d) {
        return (d & b) | (~d & c);
    }

    function fH(b, c, d) {
        return b ^ c ^ d;
    }

    function fI(b, c, d) {
        return c ^ (b | ~d);
    }

    // pick 4 bytes at specified offset. Little-endian is assumed
    function bytes_to_int32(arr, off) {
        return (arr[off + 3] << 24) | (arr[off + 2] << 16) | (arr[off + 1] << 8) | (arr[off]);
    }

	/*
	Conver string to array of bytes in UTF-8 encoding
	See: 
	http://www.dangrossman.info/2007/05/25/handling-utf-8-in-javascript-php-and-non-utf8-databases/
	http://stackoverflow.com/questions/1240408/reading-bytes-from-a-javascript-string
	How about a String.getBytes(<ENCODING>) for Javascript!? Isn't it time to add it?
	*/
	function str_to_bytes(str) {
		// alert("got " + str.length + " chars")
	    var retval = [ ];
	    for (var i = 0; i < str.length; i++)
	        if (str.charCodeAt(i) <= 0x7F) {
	            retval.push(str.charCodeAt(i));
	        } else {
	            var tmp = encodeURIComponent(str.charAt(i)).substr(1).split('%');
	            for (var j = 0; j < tmp.length; j++) {
	                retval.push(parseInt(tmp[j], 0x10));
				}
	        }
	    return retval;
	};


	

    // convert the 4 32-bit buffers to a 128 bit hex string. (Little-endian is assumed)
    function int128le_to_hex(a, b, c, d) {
        var ra = "";
        var t = 0;
        var ta = 0;
        for (var i = 3; i >= 0; i--) {
            ta = arguments[i];
            t = (ta & 0xFF);
            ta = ta >>> 8;
            t = t << 8;
            t = t | (ta & 0xFF);
            ta = ta >>> 8;
            t = t << 8;
            t = t | (ta & 0xFF);
            ta = ta >>> 8;
            t = t << 8;
            t = t | ta;
            ra = ra + to_zerofilled_hex(t);
        }
        return ra;
    }

    // check input data type and perform conversions if needed
    var databytes = null;
    // String
    if (typeof data == 'string') {
        // convert string to array bytes
        databytes = str_to_bytes(data);
    } else if (data.constructor == Array) {
        if (data.length === 0) {
            // if it's empty, just assume array of bytes
            databytes = data;
        } else if (typeof data[0] == 'string') {
            databytes = chars_to_bytes(data);
        } else if (typeof data[0] == 'number') {
            databytes = data;
        } else {
            fflog("input data type mismatch");
            return null;
        }
    } else {
        fflog("input data type mismatch");
        return null;
    }

    // save original length
    var org_len = databytes.length;

    // first append the "1" + 7x "0"
    databytes.push(0x80);

    // determine required amount of padding
    var tail = databytes.length % 64;
    // no room for msg length?
    if (tail > 56) {
        // pad to next 512 bit block
        for (var i = 0; i < (64 - tail); i++) {
            databytes.push(0x0);
        }
        tail = databytes.length % 64;
    }
    for (i = 0; i < (56 - tail); i++) {
        databytes.push(0x0);
    }
    // message length in bits mod 512 should now be 448
    // append 64 bit, little-endian original msg length (in *bits*!)
    databytes = databytes.concat(int64_to_bytes(org_len * 8));

    // initialize 4x32 bit state
    var h0 = 0x67452301;
    var h1 = 0xEFCDAB89;
    var h2 = 0x98BADCFE;
    var h3 = 0x10325476;

    // temp buffers
    var a = 0,
    b = 0,
    c = 0,
    d = 0;


	function _add(n1, n2) {
		return 0x0FFFFFFFF & (n1 + n2)
	}
	
    // function update partial state for each run
    var updateRun = function(nf, sin32, dw32, b32) {
        var temp = d;
        d = c;
        c = b;
        //b = b + rol(a + (nf + (sin32 + dw32)), b32);
		b = _add(b, 
			rol( 
				_add(a, 
					_add(nf, _add(sin32, dw32))
				), b32
			)
		);
        a = temp;
    };


    // Digest message
    for (i = 0; i < databytes.length / 64; i++) {
        // initialize run
        a = h0;
        b = h1;
        c = h2;
        d = h3;

        var ptr = i * 64;

        // do 64 runs
        updateRun(fF(b, c, d), 0xd76aa478, bytes_to_int32(databytes, ptr), 7);
        updateRun(fF(b, c, d), 0xe8c7b756, bytes_to_int32(databytes, ptr + 4), 12);
        updateRun(fF(b, c, d), 0x242070db, bytes_to_int32(databytes, ptr + 8), 17);
        updateRun(fF(b, c, d), 0xc1bdceee, bytes_to_int32(databytes, ptr + 12), 22);
        updateRun(fF(b, c, d), 0xf57c0faf, bytes_to_int32(databytes, ptr + 16), 7);
        updateRun(fF(b, c, d), 0x4787c62a, bytes_to_int32(databytes, ptr + 20), 12);
        updateRun(fF(b, c, d), 0xa8304613, bytes_to_int32(databytes, ptr + 24), 17);
        updateRun(fF(b, c, d), 0xfd469501, bytes_to_int32(databytes, ptr + 28), 22);
        updateRun(fF(b, c, d), 0x698098d8, bytes_to_int32(databytes, ptr + 32), 7);
        updateRun(fF(b, c, d), 0x8b44f7af, bytes_to_int32(databytes, ptr + 36), 12);
        updateRun(fF(b, c, d), 0xffff5bb1, bytes_to_int32(databytes, ptr + 40), 17);
        updateRun(fF(b, c, d), 0x895cd7be, bytes_to_int32(databytes, ptr + 44), 22);
        updateRun(fF(b, c, d), 0x6b901122, bytes_to_int32(databytes, ptr + 48), 7);
        updateRun(fF(b, c, d), 0xfd987193, bytes_to_int32(databytes, ptr + 52), 12);
        updateRun(fF(b, c, d), 0xa679438e, bytes_to_int32(databytes, ptr + 56), 17);
        updateRun(fF(b, c, d), 0x49b40821, bytes_to_int32(databytes, ptr + 60), 22);
        updateRun(fG(b, c, d), 0xf61e2562, bytes_to_int32(databytes, ptr + 4), 5);
        updateRun(fG(b, c, d), 0xc040b340, bytes_to_int32(databytes, ptr + 24), 9);
        updateRun(fG(b, c, d), 0x265e5a51, bytes_to_int32(databytes, ptr + 44), 14);
        updateRun(fG(b, c, d), 0xe9b6c7aa, bytes_to_int32(databytes, ptr), 20);
        updateRun(fG(b, c, d), 0xd62f105d, bytes_to_int32(databytes, ptr + 20), 5);
        updateRun(fG(b, c, d), 0x2441453, bytes_to_int32(databytes, ptr + 40), 9);
        updateRun(fG(b, c, d), 0xd8a1e681, bytes_to_int32(databytes, ptr + 60), 14);
        updateRun(fG(b, c, d), 0xe7d3fbc8, bytes_to_int32(databytes, ptr + 16), 20);
        updateRun(fG(b, c, d), 0x21e1cde6, bytes_to_int32(databytes, ptr + 36), 5);
        updateRun(fG(b, c, d), 0xc33707d6, bytes_to_int32(databytes, ptr + 56), 9);
        updateRun(fG(b, c, d), 0xf4d50d87, bytes_to_int32(databytes, ptr + 12), 14);
        updateRun(fG(b, c, d), 0x455a14ed, bytes_to_int32(databytes, ptr + 32), 20);
        updateRun(fG(b, c, d), 0xa9e3e905, bytes_to_int32(databytes, ptr + 52), 5);
        updateRun(fG(b, c, d), 0xfcefa3f8, bytes_to_int32(databytes, ptr + 8), 9);
        updateRun(fG(b, c, d), 0x676f02d9, bytes_to_int32(databytes, ptr + 28), 14);
        updateRun(fG(b, c, d), 0x8d2a4c8a, bytes_to_int32(databytes, ptr + 48), 20);
        updateRun(fH(b, c, d), 0xfffa3942, bytes_to_int32(databytes, ptr + 20), 4);
        updateRun(fH(b, c, d), 0x8771f681, bytes_to_int32(databytes, ptr + 32), 11);
        updateRun(fH(b, c, d), 0x6d9d6122, bytes_to_int32(databytes, ptr + 44), 16);
        updateRun(fH(b, c, d), 0xfde5380c, bytes_to_int32(databytes, ptr + 56), 23);
        updateRun(fH(b, c, d), 0xa4beea44, bytes_to_int32(databytes, ptr + 4), 4);
        updateRun(fH(b, c, d), 0x4bdecfa9, bytes_to_int32(databytes, ptr + 16), 11);
        updateRun(fH(b, c, d), 0xf6bb4b60, bytes_to_int32(databytes, ptr + 28), 16);
        updateRun(fH(b, c, d), 0xbebfbc70, bytes_to_int32(databytes, ptr + 40), 23);
        updateRun(fH(b, c, d), 0x289b7ec6, bytes_to_int32(databytes, ptr + 52), 4);
        updateRun(fH(b, c, d), 0xeaa127fa, bytes_to_int32(databytes, ptr), 11);
        updateRun(fH(b, c, d), 0xd4ef3085, bytes_to_int32(databytes, ptr + 12), 16);
        updateRun(fH(b, c, d), 0x4881d05, bytes_to_int32(databytes, ptr + 24), 23);
        updateRun(fH(b, c, d), 0xd9d4d039, bytes_to_int32(databytes, ptr + 36), 4);
        updateRun(fH(b, c, d), 0xe6db99e5, bytes_to_int32(databytes, ptr + 48), 11);
        updateRun(fH(b, c, d), 0x1fa27cf8, bytes_to_int32(databytes, ptr + 60), 16);
        updateRun(fH(b, c, d), 0xc4ac5665, bytes_to_int32(databytes, ptr + 8), 23);
        updateRun(fI(b, c, d), 0xf4292244, bytes_to_int32(databytes, ptr), 6);
        updateRun(fI(b, c, d), 0x432aff97, bytes_to_int32(databytes, ptr + 28), 10);
        updateRun(fI(b, c, d), 0xab9423a7, bytes_to_int32(databytes, ptr + 56), 15);
        updateRun(fI(b, c, d), 0xfc93a039, bytes_to_int32(databytes, ptr + 20), 21);
        updateRun(fI(b, c, d), 0x655b59c3, bytes_to_int32(databytes, ptr + 48), 6);
        updateRun(fI(b, c, d), 0x8f0ccc92, bytes_to_int32(databytes, ptr + 12), 10);
        updateRun(fI(b, c, d), 0xffeff47d, bytes_to_int32(databytes, ptr + 40), 15);
        updateRun(fI(b, c, d), 0x85845dd1, bytes_to_int32(databytes, ptr + 4), 21);
        updateRun(fI(b, c, d), 0x6fa87e4f, bytes_to_int32(databytes, ptr + 32), 6);
        updateRun(fI(b, c, d), 0xfe2ce6e0, bytes_to_int32(databytes, ptr + 60), 10);
        updateRun(fI(b, c, d), 0xa3014314, bytes_to_int32(databytes, ptr + 24), 15);
        updateRun(fI(b, c, d), 0x4e0811a1, bytes_to_int32(databytes, ptr + 52), 21);
        updateRun(fI(b, c, d), 0xf7537e82, bytes_to_int32(databytes, ptr + 16), 6);
        updateRun(fI(b, c, d), 0xbd3af235, bytes_to_int32(databytes, ptr + 44), 10);
        updateRun(fI(b, c, d), 0x2ad7d2bb, bytes_to_int32(databytes, ptr + 8), 15);
        updateRun(fI(b, c, d), 0xeb86d391, bytes_to_int32(databytes, ptr + 36), 21);

        // update buffers
        h0 = _add(h0, a);
        h1 = _add(h1, b);
        h2 = _add(h2, c);
        h3 = _add(h3, d);
    }
    // Done! Convert buffers to 128 bit (LE)
    return int128le_to_hex(h3, h2, h1, h0).toUpperCase();
};

/*******其它站存储cookie  begin******/
var eduUrl = 'http://edu.51cto.com'; //edu域名
var eduCurUrl = window.location.href; //当前页面

setCookieOther = function(name, value, iDay){ //设置cookie
	var oDate = new Date();
	oDate.setTime(oDate.getTime() + iDay*24*60*60*1000); 
	var dateStr = ( iDay == 0 ) ? '' : ';expires='+oDate.toGMTString();
	document.cookie = name+'='+value+dateStr+";domain=51cto.com"+';path=/';
}
	
randomCharOther = function(n){  //取出随机不重复的cookie
	var  x="qwertyuioplkjhgfdsazxcvbnmABCDEFGHIGKLMNOPQRSTUVWXYZ";
	var  tmp="";
	var timestamp = new Date().getTime(); //毫秒数
	var appname = navigator.appName; //浏览器类型
	var appVersion = navigator.appVersion; //浏览器版本
	var userAgent = navigator.userAgent.replace(/;/g,':');  //用户代理
	var random = Math.floor(Math.random()*1000000);

	for(var  i=0;i<n;i++)  {
		tmp  += x.charAt(Math.floor(Math.random()*x.length));
	}
	return  faultylabs.MD5(timestamp+userAgent+random)+tmp;  //MD5加密
}

isCookieOther = function(name){
	var arr = document.cookie.match(new RegExp("(^| )" + name
				 + "=([^;]*)(;|$)"));				 
	if(arr == null){
		setCookieOther(name,randomCharOther(4),3650);
	}
}

//学院外其他站存储cookie
if(eduCurUrl.indexOf(eduUrl) == '-1'){
	isCookieOther('www51cto') ; 
}

/*******其它站存储cookie  end******/


/*************************数据分析 begin**********************************/



/*************************识别广告位置 begin**********************************/

(function($) { 
  $(function() {
    // 使用 $ 作为 jQuery 别名的代码
  									
var urls = new Array('www.51cto.com/', 'home.51cto.com/index.php?s=/Index/index/reback/http%253A%252F%252Flovelace.blog.51cto.com%252F1028430%252F1600594/','bbs.51cto.com/','bbs.51cto.com/thread-1133589-1.html','bbs.51cto.com/forumdisplay.php?fid=57','down.51cto.com/','down.51cto.com/zt','down.51cto.com/zt/7020','down.51cto.com/data/1970237','down.51cto.com/search.php?q=spark','down.51cto.com/credits','home.51cto.com/index.php?s=/space/8976740','home.51cto.com/index.php?s=/Home/index','home.51cto.com/index.php?s=/Notify/inbox','home.51cto.com/apps/download/index.php?s=/Index/index/','home.51cto.com/apps/book/index.php?s=/Index/good','lovelace.blog.51cto.com/1028430/1600594','blog.51cto.com/blogcommend','blog.51cto.com/','aresy.blog.51cto.com/5100031/1600956','shayi1983.blog.51cto.com/4681835/1598662','blog.51cto.com/expert','down.51cto.com/upload','book.51cto.com/','developer.51cto.com/','www.cioage.com/','mobile.51cto.com/','mobile.51cto.com/ahot-463852.htm','developer.51cto.com/embed/','cloud.51cto.com/','virtual.51cto.com/','os.51cto.com/','server.51cto.com/','database.51cto.com/','developer.51cto.com/web/','developer.51cto.com/java/','netsecurity.51cto.com/','network.51cto.com/','zyedu.51cto.com/');
//锚点数组
var hashs = new Array();
hashs[0] = new Array('#51ctotestforvalueadvertisement38', '#51ctotestforvalueadvertisement72','#51ctotestforvalueadvertisement73','#51ctotestforvalueadvertisement74','#51ctotestforvalueadvertisement163','#51ctotestforvalueadvertisement164','#51ctotestforvalueadvertisement154','#51ctotestforvalueadvertisement155');
hashs[1] = new Array('#51ctotestforvalueadvertisement77');
hashs[2] = new Array('#51ctotestforvalueadvertisement49','#51ctotestforvalueadvertisement75','#51ctotestforvalueadvertisement78','#51ctotestforvalueadvertisement114','#51ctotestforvalueadvertisement147','#51ctotestforvalueadvertisement169','#51ctotestforvalueadvertisement156');
hashs[3] = new Array('#51ctotestforvalueadvertisement52','#51ctotestforvalueadvertisement102','#51ctotestforvalueadvertisement103','#51ctotestforvalueadvertisement104');
hashs[4] = new Array('#51ctotestforvalueadvertisement53','#51ctotestforvalueadvertisement54');
hashs[5] = new Array('#51ctotestforvalueadvertisement55','#51ctotestforvalueadvertisement56','#51ctotestforvalueadvertisement62','#51ctotestforvalueadvertisement79','#51ctotestforvalueadvertisement81','#51ctotestforvalueadvertisement82','#51ctotestforvalueadvertisement113','#51ctotestforvalueadvertisement168','#51ctotestforvalueadvertisement157');
hashs[6] = new Array('#51ctotestforvalueadvertisement57','#51ctotestforvalueadvertisement58','#51ctotestforvalueadvertisement86');
hashs[7] = new Array('#51ctotestforvalueadvertisement61','#51ctotestforvalueadvertisement87');
hashs[8] = new Array('#51ctotestforvalueadvertisement84');
hashs[9] = new Array('#51ctotestforvalueadvertisement159');
hashs[10] = new Array('#51ctotestforvalueadvertisement88');
hashs[11] = new Array('#51ctotestforvalueadvertisement65','#51ctotestforvalueadvertisement96','#51ctotestforvalueadvertisement99');
hashs[12] = new Array('#51ctotestforvalueadvertisement98','#51ctotestforvalueadvertisement100','#51ctotestforvalueadvertisement101');
hashs[13] = new Array('#51ctotestforvalueadvertisement105');
hashs[14] = new Array('#51ctotestforvalueadvertisement106');
hashs[15] = new Array('#51ctotestforvalueadvertisement107');
//hashs[16] = new Array('#51ctotestforvalueadvertisement68');
hashs[17] = new Array('#51ctotestforvalueadvertisement95');
hashs[18] = new Array('#51ctotestforvalueadvertisement67','#51ctotestforvalueadvertisement90','#51ctotestforvalueadvertisement112','#51ctotestforvalueadvertisement170','#51ctotestforvalueadvertisement171');
hashs[19] = new Array('#51ctotestforvalueadvertisement91');
hashs[20] = new Array('#51ctotestforvalueadvertisement92','#51ctotestforvalueadvertisement93');
hashs[21] = new Array('#51ctotestforvalueadvertisement94');
hashs[22] = new Array('#51ctotestforvalueadvertisement158');
hashs[23] = new Array('#51ctotestforvalueadvertisement149');
hashs[24] = new Array('#51ctotestforvalueadvertisement89','#51ctotestforvalueadvertisement133','#51ctotestforvalueadvertisement135','#51ctotestforvalueadvertisement136');
hashs[25] = new Array('#51ctotestforvalueadvertisement110');
hashs[26] = new Array('#51ctotestforvalueadvertisement116','#51ctotestforvalueadvertisement152');
hashs[27] = new Array('#51ctotestforvalueadvertisement166');
hashs[28] = new Array('#51ctotestforvalueadvertisement118');
hashs[29] = new Array('#51ctotestforvalueadvertisement119','#51ctotestforvalueadvertisement120','#51ctotestforvalueadvertisement121');
hashs[30] = new Array('#51ctotestforvalueadvertisement122');
hashs[31] = new Array('#51ctotestforvalueadvertisement124','#51ctotestforvalueadvertisement125','#51ctotestforvalueadvertisement126','#51ctotestforvalueadvertisement127','#51ctotestforvalueadvertisement128');
hashs[32] = new Array('#51ctotestforvalueadvertisement129','#51ctotestforvalueadvertisement130');
hashs[33] = new Array('#51ctotestforvalueadvertisement131','#51ctotestforvalueadvertisement132');
hashs[34] = new Array('#51ctotestforvalueadvertisement137','#51ctotestforvalueadvertisement148','#51ctotestforvalueadvertisement138');
hashs[35] = new Array('#51ctotestforvalueadvertisement139','#51ctotestforvalueadvertisement140','#51ctotestforvalueadvertisement141','#51ctotestforvalueadvertisement142');
hashs[36] = new Array('#51ctotestforvalueadvertisement143','#51ctotestforvalueadvertisement144');
hashs[37] = new Array('#51ctotestforvalueadvertisement145','#51ctotestforvalueadvertisement150','#51ctotestforvalueadvertisement153');

hashs[38] = new Array('#51ctotestforvalueadvertisement10000'); //测试用的


//调转到的位置数组
var jumpToDoms = new Array();
jumpToDoms[0] = new Array($(".f2 ul").children(":last"),$('.txtScroll-left'),$('.r2-r'),$('.act-list'),$('.adbox') , $('#ad_7'),$('.tab'),$('.subnav dl') );
jumpToDoms[1] = new Array( $('.loginImg'));
jumpToDoms[2] = new Array( $('.focus_btm') , $('.searchtag') ,$('.h294').next() ,$('.links') , $('.navbox') , $('#banner_top') , $('.title02 p') ); 
jumpToDoms[3] = new Array( $('.relate_subject').prev() , $('.vbox') ,$('.online').next() ,$('.postinfo06a') ); //论坛帖子页广告图
jumpToDoms[4] = new Array( $('.flabox'),$('#ad_plate img'));
jumpToDoms[5] = new Array( $('.flash') , $('.service_list'), $('#ld') ,$('.nav') , $('#midd .tit') , $('.down') , $('#link') , $('#ad_down_2') , $('.head_soso_tag') );
jumpToDoms[6] = new Array( $('.banner_head') , $('.ad225') , $('.border02'));
jumpToDoms[7] = new Array( $('.ad225') , $('.vbox') );
jumpToDoms[8] = new Array( $('#right') );
jumpToDoms[9] = new Array( $('.soso_leftbar').children(":last").find('img') );
jumpToDoms[10] = new Array( $('.yBar') );
jumpToDoms[11] = new Array( $('.ad_top') , $('#user_app_hide') , $('.Feed')  );
jumpToDoms[12] = new Array( $('#coursetab_0') , $('#gonggao') , $('.nav_sub') );
jumpToDoms[13] = new Array( $('.tab-menu').children().next()  );
jumpToDoms[14] = new Array( $('.tab-menu').children().next()  );
jumpToDoms[15] = new Array( $('.tab-menu').children().next().find('span')  );
//jumpToDoms[16] = new Array( $('#message_content')  );
jumpToDoms[17] = new Array( $('.addlink')  );
jumpToDoms[18] = new Array( $('.heiban') , $('.oneright #sddm').next() ,$('.fri') , $('#ad_blog_1') , $("#ad_blog_6") );
jumpToDoms[19] = new Array( $('.edu-col-b')  );
jumpToDoms[20] = new Array( $('.friendLink') , $('.relatedArt') );
jumpToDoms[21] = new Array( $('.fl').next().next()  );
jumpToDoms[22] = new Array( $('.ad221')  );
jumpToDoms[23] = new Array( $('#search')  );
jumpToDoms[24] = new Array( $('.main-right').children().next().next().next() ,$('.channel_hot') , $('.hot-tags') , $('.my-links') );
jumpToDoms[25] = new Array( $('.cio_920_top3') );
jumpToDoms[26] = new Array( $('.border02') , $('#navmenu') );
jumpToDoms[27] = new Array( $('.focus').find('iframe') );
jumpToDoms[28] = new Array( $('.hot') );
jumpToDoms[29] = new Array( $('.nav') , $('#right').children().next().next().next() , $('#link') );
jumpToDoms[30] = new Array( $('.left1') );
jumpToDoms[31] = new Array( $('.inmenu_foot'), $('.channels') , $('#edu_p') , $('#link') , $('#S_tequan') );
jumpToDoms[32] = new Array( $('.nav_info'), $('.linkslist') );
jumpToDoms[33] = new Array( $('.so_l') , $('#link') );
jumpToDoms[34] = new Array( $('.navTag') , $('.hottag') , $('.seatag') );
jumpToDoms[35] = new Array( $('.inmenu_foot') , $('.tag') , $('.to') , $('#right .con') );
jumpToDoms[36] = new Array( $('.Interactive') , $('#link').next().next() );
jumpToDoms[37] = new Array( $('#S_tequan').next() , $('.bbs') ,$('.zhuan_qu') );

jumpToDoms[38] = new Array( $('.Footer') ); //测试用的


for (var i = 0; i < urls.length; i++) {
  var hosts = location.host + location.pathname + location.search;

  if (hosts == urls[i]) {
    
    for (var o = 0; o < hashs[i].length; o++) {
      
      if (hashs[i][o] == location.hash) {
       
        $(window).scrollTop(jumpToDoms[i][o].offset().top);
        var xxx = $('<div></div>');
        xxx.css({
          'border': '2px solid blue',
          'position': 'absolute',
          'top': jumpToDoms[i][o].offset().top,
          'left': jumpToDoms[i][o].offset().left,
          'width': jumpToDoms[i][o].width(),
          'height': jumpToDoms[i][o].height(),
          'z-index': '100000'
        });
        $('body').append(xxx);
        var t = 800;
		$(xxx).fadeOut(t, function () {
          $(this).fadeIn(t, function () {
            $(this).fadeOut(t, function () {
              $(this).fadeIn(t, function () {
                $(this).fadeOut(t, function () {
                  $(this).fadeIn(t,function () {
					$(this).fadeOut(t,function () {
					  $(this).fadeIn(t, function () {
						$(this).fadeOut(t, function () {
						  $(this).fadeIn(t, function () {
							$(this).fadeOut(t, function () {
							  $(this).fadeIn(t,function () {
								$(this).fadeOut(t,function () {
								  $(this).fadeIn(t, function () {
									$(this).fadeOut(t, function () {
									  $(this).fadeIn(t,function () {
										$(this).fadeOut(t,function () {
											$(this).remove();
										})
									  })
									});
								  });
								})
							  })
							});
						  });
						});
					  });
					})
				  })
                });
              });
            });
          });
        });  
		
      }
	  
    }
  }
}

});
})(jQuery);	

/*************************识别广告位置 end**********************************/