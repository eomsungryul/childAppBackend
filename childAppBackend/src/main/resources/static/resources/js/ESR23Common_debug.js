/**
 * @description ESR23Common
 * @author ESR23
 */
//----------------------------------------------------------------------------------------------------;
// DEFINE;
//----------------------------------------------------------------------------------------------------;

if( !window.hasOwnProperty )
{
	Object.prototype.hasOwnProperty = function( p )
	{
		if( this[ p ] ) return true;
		else return false;
	};
	window.hasOwnProperty = function( p )
	{
		if( this[ p ] ) return true;
		else return false;
	};
}

window.ESR23Common = window.ESR23Common ? window.ESR23Common : {};

//----------------------------------------------------------------------------------------------------;

//----------------------------------------------------------------------------------------------------;
// PROPERTY - CONST;
//----------------------------------------------------------------------------------------------------;

//----------------------------------------------------------------------------------------------------;
// EVENT ;
//----------------------------------------------------------------------------------------------------;


/**
 * 키보드 입력을 숫자만 가능하도록 제한하고 시간 포맷으로 설정
 * @param {Object} obj
 */
window.ESR23Common.evt_kPress_onlyTime = function( obj )
{
	var ESR23 = window.ESR23Common;
		ESR23.evt_kPress_onlyNum( obj );
		ESR23.setAutomaticFormat_Time( obj );
};

/**
 * 키보드 입력을 숫자만 가능하도록 제한하고 년도 포맷으로 설정
 * @param {Object} obj
 */
window.ESR23Common.evt_kPress_onlyYear = function( obj )
{
	var ESR23 = window.ESR23Common;
		ESR23.evt_kPress_onlyNum( obj );
		ESR23.setAutomaticFormat_Year( obj );
};

//----------------------------------------------------------------------------------------------------;
// FUNCTION ;
//----------------------------------------------------------------------------------------------------;

/**
 * ajax 수행 ( 비동기 )
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 * @param {String} dataType
 * @param {String} type
 */
window.ESR23Common.ajax = function( url, data, fnSuccess, fnFail, dataType, type )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		if( type ){d.type = type;}
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
		if( dataType ){d.dataType = dataType;}
		d.beforeSend = t.evt_beforeSend_ajax; // ajax 수행 전 로딩서클 show ;
	$.ajax( d )
		.done( fnSuccess ? fnSuccess : t.evtComplete )
		.fail( fnFail ? fnFail : t.evtFail )
		.always( t.evt_complete_ajax ); // ajax 수행 후 로딩서클 hide ;
};

/**
 * ajax 수행 ( 비동기 ) 로딩서클 없음
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 * @param {String} dataType
 * @param {String} type
 */
window.ESR23Common.ajaxNoneRoadingCircle = function( url, data, fnSuccess, fnFail, dataType, type )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		if( type ){d.type = type;}
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
		if( dataType ){d.dataType = dataType;}
	$.ajax( d )
		.done( fnSuccess ? fnSuccess : t.evtComplete )
		.fail( fnFail ? fnFail : t.evtFail )
};

/**
 * ajax 수행 ( 동기 )
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 * @param {String} dataType
 */
window.ESR23Common.ajaxSync = function( url, data, fnSuccess, fnFail, dataType )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		d.async = false;
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
		if( dataType ){d.dataType = dataType;}
		d.beforeSend = t.evt_beforeSend_ajax; // ajax 수행 전 로딩서클 show ;
	fnSuccess = fnSuccess ? fnSuccess : t.evtComplete;
	fnFail = fnFail ? fnFail : t.evtFail;
	$.ajax( d )
		.done( fnSuccess )
		.fail( fnFail )
		.always( t.evt_complete_ajax ); // ajax 수행 후 로딩서클 hide ;
};

/**
 * ajax 수행 ( 동기 )
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 */
window.ESR23Common.ajaxSync_text = function( url, data, fnSuccess, fnFail )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		d.async = false;
		d.cache = false;
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
		d.dataType = "text";
		d.mimeType = "text/plain";
		d.beforeSend = t.evt_beforeSend_ajax; // ajax 수행 전 로딩서클 show ;
	fnSuccess = fnSuccess ? fnSuccess : t.evtComplete;
	fnFail = fnFail ? fnFail : t.evtFail;
	$.ajax( d )
		.done( fnSuccess )
		.fail( fnFail )
		.always( t.evt_complete_ajax ); // ajax 수행 후 로딩서클 hide ;
};

/**
 * ajax 수행
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 * @param {Function} fnBefore
 * @param {Function} fnAfter
 */
window.ESR23Common.ajax_custom = function( url, data, fnSuccess, fnFail, fnBefore, fnAfter )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
		d.beforeSend = fnBefore ? fnBefore : t.evt_beforeSend_ajax; // ajax 수행 전 ;
	$.ajax( d )
		.done( fnSuccess ? fnSuccess : t.evtComplete )
		.fail( fnFail ? fnFail : t.evtFail )
		.always( fnAfter ? fnAfter : t.evt_complete_ajax ); // ajax 수행 후 ;
};

/**
 * ajax 수행 ( 로딩 시작 처리, 완료 처리 없음 )
 * @param {String} url
 * @param {Object} data
 * @param {Function} fnSuccess
 * @param {Function} fnFail
 */
window.ESR23Common.ajax_only = function( url, data, fnSuccess, fnFail )
{
	var t = window.ESR23Common;
	var d = {};
		//d.type = "POST";
		d.type = "GET";
		d.timeout = 18000; // 밀리세컨드 ;
		d.url = url;
		d.data = data;
	$.ajax( d )
		.done( fnSuccess ? fnSuccess : t.evtComplete )
		.fail( fnFail ? fnFail : t.evtFail );
};

/**
 * 파일 확장자 제한 ( 이미지 )
 */
window.ESR23Common.checkbox_checkall = function( name,target )
{
	
	if($("#"+name).prop("checked")){
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 true로 정의
		$("input:checkbox[name='"+target+"']").prop('checked', true);
        //클릭이 안되있으면
    }else{
        //input태그의 name이 chk인 태그들을 찾아서 checked옵션을 false로 정의
    	$("input:checkbox[name='"+target+"']").prop('checked', false);
    }

};

/**
 * 파일 확장자 제한 ( 이미지 )
 */
window.ESR23Common.checkFile_Image = function( fileName )
{
	var ext = fileName.substring( fileName.lastIndexOf( "." ) + 1 ).toLowerCase();
	var reg = /gif|jpg|jpeg|png/i;
	var result = false;
	if( reg.test( ext ) == false )
	{
		alert( "첨부파일은 gif, jpg, png로 된 이미지만 가능합니다." );
	}
	else
	{
		result = true;
	}
	return result;
};

/**
 * Browser check
 * @return {Array} [ browser, version ]
 */
window.ESR23Common.checkBrowser = function()
{
	var N = navigator.appName
	var ua = navigator.userAgent
	var tem = null;
	var M = ua.match(/(opera|chrome|safari|firefox|msie|trident)\/?\s*(\.?\d+(\.\d+)*)/i);
	if( M && ( tem = ua.match(/version\/([\.\d]+)/i) )!= null ) M[2]=tem[1];
	M= M? [M[1], M[2]]: [N, navigator.appVersion,'-?'];
	return M;
};

/**
 * LPAD
 * @param {String} s 주어진 텍스트
 * @param {String} c 채울 문자
 * @param {Number} n 문자열 길이
 */
window.ESR23Common.LPAD = function(s, c, n)
{
	s = s + "";
	if (! s || ! c || s.length >= n) return s;

	var max = ( n - s.length ) / c.length;
	var i=0;
	for ( ; i<max; i++) s = c + s;
	return s;
}

/**
 * RPAD
 * @param {String} s 주어진 텍스트
 * @param {String} c 채울 문자
 * @param {Number} n 문자열 길이
 */
window.ESR23Common.RPAD = function(s, c, n)
{
	s = s + "";
	if (! s || ! c || s.length >= n) return s;

	var max = ( n - s.length ) / c.length;
	var i=0;
	for ( ; i<max; i++) s += c;
	return s;
}

/**
 * javascript 파일 src를 date time을 추가하여 import
 * @param {Array} src JS 파일 url 배열
 * @param {Boolean} bId 파일명으로 id 적용여부
 * @param {Boolean} bDebug date time 추가 여부 ( debug 시 사용 )
 */
window.ESR23Common.import_JS = function( src, bId, bDebug )
{
	var i=0, iLen=src.length;
	var url = null;
	var d = (bDebug) ? "" : "?d=" + window.ESR23Common.getTimeStamp( "YYYYMMDDhhmmss" );
	var body = document.getElementsByTagName( "body" )[0];
	var js = null;
	if( bId )
	{
		for( ; i<iLen; ++i )
		{
			url = src[ i ];

			js = document.createElement( "script" );
			js.type = "text/javascript";
			js.id = url.substring( url.lastIndexOf( "/" ) + 1, url.length ).replace( ".js", "" );
			js.src = url + d;
			body.appendChild( js );
		}
	}
	else
	{
		for( ; i<iLen; ++i )
		{
			url = src[ i ];

			js = document.createElement( "script" );
			js.type = "text/javascript";
			js.src = url + d;
			body.appendChild( js );
		}
	}
};

window.ESR23Common.import_JS_Async = function( src, bId, bDebug )
{
	var ESR23 = window.ESR23Common;
	var browser = ESR23.checkBrowser();
	var f = null;
	if( browser[0] == "MSIE"  && parseFloat( browser[1] ) < 10 )
	{
		f = ESR23.import_JS_Async_Document;
	}
	else
	{
		f = ESR23.import_JS_Async_Element;
	}
	f( src, bId, bDebug );
};

window.ESR23Common.import_JS_Async_Document = function( src, bId, bDebug )
{
	var i=0, iLen=src.length;
	var url = null;
	var d = (bDebug) ? "" : "?d=" + window.ESR23Common.getTimeStamp( "YYYYMMDDhhmmss" );
	var js = null;

	for( ; i<iLen; ++i )
	{
		url = src[ i ];
		js = "<script type=\"text/javascript\""
			+ " id=\"" + url.substring( url.lastIndexOf( "/" ) + 1, url.length ).replace( ".js", "" ) + "\""
			+ " src=\"" + url + d + "\""
			+ "></script>";
		document.writeln( js );
	}
};

/**
 * javascript 파일 src를 date time을 추가하여 동기 import
 * @param {Array} src JS 파일 url 배열
 * @param {Boolean} bId 파일명으로 id 적용여부
 * @param {Boolean} bDebug date time 추가 여부 ( debug 시 사용 )
 */
window.ESR23Common.import_JS_Async_Element = function( src, bId, bDebug )
{
	var ESR23 = window.ESR23Common;
	var browser = ESR23.checkBrowser();
	var f = null;
	if( browser[0] == "MSIE"  && parseFloat( browser[1] ) < 10 )
	{
	}
	var i=0, iLen=src.length;
	var url = null;
	var d = (bDebug) ? "" : "?d=" + ESR23.getTimeStamp( "YYYYMMDDhhmmss" );
	var body = document.getElementsByTagName( "body" )[0];
	var js = null;
	if( bId )
	{
		for( ; i<iLen; ++i )
		{
			url = src[ i ];

			js = document.createElement( "script" );
			js.type = "text/javascript";
			js.id = url.substring( url.lastIndexOf( "/" ) + 1, url.length ).replace( ".js", "" );
			js.src = url + d;
			js.async = false; // script가 삽입된 순서대로 실행하게 함;
			js.defer = "defer";

			body.appendChild( js );
		}
	}
	else
	{
		for( ; i<iLen; ++i )
		{
			url = src[ i ];

			js = document.createElement( "script" );
			js.type = "text/javascript";
			js.src = url + d;
			js.async = false; // script가 삽입된 순서대로 실행하게 함;

			body.appendChild( js );
		}
	}
};

/**
 * 랜덤 좌표들을 1차원 배열로 반환
 * @param {Number} cnt 좌표갯수
 * @param {Array} minVal
 * @param {Array} maxVal
 * @return {Array} resultArray
 * @example window.ESR23Common.randomArrayCoord( 30, [ 0, 0 ], [90, 180] );
 */
window.ESR23Common.randomPoiCoord = function( cnt, minVal, maxVal ){
	var iLen = cnt;
	var resultArray = [];
	var i=0;
	for( ; i<iLen; i++ ){
		resultArray[ i ] = [window.ESR23Common.randomNumber_Coord( minVal[ 0 ], maxVal[ 0 ]),window.ESR23Common.randomNumber_Coord( minVal[ 1 ], maxVal[ 1 ])];
	}
	return resultArray;
};

/**
 * 랜덤 좌표들을 1차원 배열로 반환
 * @param {Number} cnt 좌표갯수
 * @param {Array} minVal
 * @param {Array} maxVal
 * @return {Array} resultArray
 * @example window.ESR23Common.randomArrayCoord( 30, [ 0, 0 ], [90, 180] );
 */
window.ESR23Common.randomArrayCoord = function( cnt, minVal, maxVal ){
	var iLen = cnt * 2;
	var resultArray = [];
	var i=0;
	for( ; i<iLen; i=i+2 ){
		resultArray[ i ] = window.ESR23Common.randomNumber_Coord( minVal[ 0 ], maxVal[ 0 ]);
		resultArray[ i+1 ] = window.ESR23Common.randomNumber_Coord( minVal[ 1 ], maxVal[ 1 ]);
	}
	return resultArray;
};

/**
 * 임의의 숫자 16진수 반환
 */
window.ESR23Common.randomColorRGB = function()
{
	var ESR23 = window.ESR23Common;
	var c = ESR23.randomNumber;
	var l = ESR23.LPAD;
	var r = l( Math.ceil( c( 0, 255 ) ).toString(16) , 0, 2 );
	var g = l( Math.ceil( c( 0, 255 ) ).toString(16) , 0, 2 );
	var b = l( Math.ceil( c( 0, 255 ) ).toString(16) , 0, 2 );
	return "0x" + r + g + b;
};

/**
 * 입력된 min, max값 안의 랜덤 숫자 생성하여 반환
 * @param {Number} minVal
 * @param {Number} maxVal
 * @return {Number} result
 */
window.ESR23Common.randomNumber = function( minVal, maxVal )
{
	var result = 0;
	result = Math.floor( (Math.random() * (maxVal - minVal + 1)) + minVal );
	return result;
};

/**
 * 입력된 min, max값 안의 랜덤 좌표 생성하여 반환
 * @param {Number} minVal
 * @param {Number} maxVal
 * @return {Number} result
 */
window.ESR23Common.randomNumber_Coord = function( minVal, maxVal )
{
	var result = 0;
	var digits = 1000000;
	minVal = minVal * digits;
	maxVal = maxVal * digits;
	result = Math.floor( (Math.random() * (maxVal - minVal + 1)) + minVal );
	return result / digits;
};

/**
 * 대문자 언더바로 구분되어 있는 문자열을 카멜 표기법으로 전환
 * @param {String} str
 */
window.ESR23Common.StringUnderToCamel = function( str )
{
	return str.toLowerCase().replace(/(\_[a-z])/g, function(arg){return arg.toUpperCase().replace('_','');});
};

/**
 * 카멜 표기법을 대문자 언더바로 구분되어 있는 문자열로 전환
 * @param {String} str
 */
window.ESR23Common.StringCamelToUnder = function( str )
{
	return str.replace(/([A-Z])/g, function(arg){return "_"+arg.toLowerCase();}).toUpperCase();
};

/**
 * textField byte counter
 * @param {String} fieldId
 * @param {String} countFieldId
 * @param {String} maxLimit bytes
 */
window.ESR23Common.textCounter = function( fieldId, countFieldId, maxLimit )
{
	var field = document.getElementById( fieldId );
	var countField = document.getElementById( countFieldId );
	var getByteLength = window.ESR23Common.getByteLength;
	var temp_str = field.value;
	var temp = maxLimit - getByteLength( temp_str );
	if( temp < 0 )
	{
		alert( maxLimit + "Bytes를 초과할 수 없습니다." );
		temp_str = temp_str.substring( 0, temp_str.length - ( temp * -1 /2 ) );
		field.value = temp_str;
		if( countField ) countField.value = temp;
		field.focus();
	}
};

/**
 * xml 형태의 text를 JSON object로 변환
 * @param {String} text
 */
window.ESR23Common.xmlTEXTToJSON = function( text )
{
	var XML = new Object();
	XML.xml2obj = function(xml)
	{
		var obj = {}, que = [], depth = 0;
		var parse_attr = function(oobj, str) {
			str.replace(/([^=\s]+)\s*=\s*"([^"]*)"/g, function(a0,a1,a2) {
				oobj[a1] = a2;
			});
		};
		xml = xml.replace(/<(\?|\!-)[^>]*>/g,'').replace(/>\s+</g, '><');
		xml = xml.replace(/<([^!][^ >]+)(\s[^>]*)?><\/\1>/g, '<$1$2 />').replace(/^\s+|\s+$/g, '').replace(/\s?\/>/g,' />');
		xml = xml.replace(/gml:/g,'').replace(/sw:/g,'');
		xml = xml.replace(/<\/?([^\!][^ >]*)(\s[^>]*)?>(<\/$1>|<\!\[CDATA\[(?:(.|\s)*?)\]\]>|[^<>]*)/g, function(a0,a1,a2,a3)
		{
			if (typeof a1 == 'undefined'){a1 = '';}
			if (typeof a2 == 'undefined'){a2 = '';}
			if (typeof a3 == 'undefined'){a3 = '';}
			if (a0.substr(1,1) == '/') {
				depth--;
			} else if (que.length == 0) {
				que[depth] = obj;
				parse_attr(obj, a2);
			} else {
				var k  = a1, o = {}, is_closed = false;
				is_closed = (a2.substr(-1,1) == '/');
				if (a3.length > 0 || is_closed) {
					o = a3;
					if (o.substr(0,9) == '<![CDATA[' && o.substr(-3,3) == ']]>'){o = o.substring(9, o.length-3);} else {o = o.replace(/</g, '<').replace(/>/g, '>').replace(/&/g, '&');}
				}
				if (typeof que[depth][k] == 'undefined') {
					que[depth][k] = o;
				} else {
					var v = que[depth][k];
					if (que[depth][k].constructor != Array){que[depth][k] = [v];}
					que[depth][k].push(o);
				}
				parse_attr(o, a2);
				if (!is_closed){que[++depth] = o;}
			}
			return '';
		});
		return obj;
	};
	var obj = XML.xml2obj( text );
	return obj;
};

/**
 * JSON Array 정렬
 * @param {Array} objArray
 * @param {String} propertyName
 */
window.ESR23Common.sortJsonArrayByProperty = function( objArray, propertyName )
{
	return objArray.sort( sortByFunc );
	function sortByFunc( a, b ){
		if (typeof a === "undefined") return -1;
		if (typeof b === "undefined") return 1;

		var x = a[propertyName];
		var y = b[propertyName];
		return ((x < y) ? -1 : ((x > y) ? 1 : 0));
	}
};

/**
 * JSON Array에서 propertyName, value값이 동일한 객체를 배열에 담아 반환
 * @param {Array} objArray
 * @param {String} propertyName
 * @param {Object} value
 */
window.ESR23Common.getJsonArrayByPropertyValue = function( objArray, propertyName, value )
{
	var result = [];
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( objArray[ i ][ propertyName ] == value ) result.push( objArray[ i ] );
		else continue;
	}
	return result;
};

/**
 * JSON Array에서 propertyName, value값이 포함된 객체를 배열에 담아 반환
 * @param {Array} objArray
 * @param {String} propertyName
 * @param {String} value
 */
window.ESR23Common.getJsonArrayByPropertyLikeValue = function( objArray, propertyName, value )
{
	var result = [];
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( ( objArray[ i ][ propertyName ] ).indexOf( value ) > -1 ) result.push( objArray[ i ] );
		else continue;
	}
	return result;
};

/**
 * JSON Array에서 propertyName 객체의 값을 배열에 담아 반환
 * @param {Array} objArray
 * @param {String} propertyName
 * @param {String} value
 * @return {Array}
 */
window.ESR23Common.getArrayJsonArrayByProperty = function( objArray, propertyName )
{
	var key = [];
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		key[ i ] = objArray[ i ][ propertyName ];
	}
	return key;
};

/**
 * 1차원 좌표 배열에서 extent를 반환
 * @param {Array} objArray
 * @return {Object}
 *
 * {
	center : [ x, y ]
	, extent : [ l, b, r t ]
	, geometry : []
	, properties : {}
}
 */
window.ESR23Common.getExtentFromCoordArray = function( objArray )
{
	var result = { left : null, bottom : null, right : null, bottom : null };
	var ax = [];
	var ay = [];
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( i % 2 == 0 ) ax.push( objArray[ i ] );
		else ay.push( objArray[ i ] );
	}

	var f = window.ESR23Common.getMinMaxFromArray;
	var o = null;
	o = f( ax );
	result.left = o.min;
	result.right = o.max;

	o = f( ay );
	result.bottom = o.min;
	result.top = o.max;

	return result;
};

/**
 * Array에서 value 값의 인덱스를 반환
 * @param {Array} objArray
 * @param {String} value
 * @return {Number}
 */
window.ESR23Common.getIndexFromArrayByValue = function( objArray, value )
{
	var result = null;
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( objArray[ i ] == value )
		{
			result = i;
			break;
		}
	}
	return result;
};

/**
 * Array에서 value 값과 가장 가까운 최대값 인덱스를 반환
 * @param {Array} objArray
 * @param {String} value
 * @return {Number}
 */
window.ESR23Common.getIndexFromArrayByValue_Max = function( objArray, value )
{
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( objArray[ i ] <= value ) break;
		else continue;
	}
	return i;
};

/**
 * Array에서 value 값과 가장 가까운 최소값 인덱스를 반환
 * @param {Array} objArray
 * @param {String} value
 * @return {Number}
 */
window.ESR23Common.getIndexFromArrayByValue_Min = function( objArray, value )
{
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( objArray[ i ] <= value ) break;
		else continue;
	}
	return i;
};

/**
 * Array에서 최소값, 최대값 value 반환
 * @param {Array} objArray
 * @return {Object, Object}
 */
window.ESR23Common.getMinMaxFromArray = function( objArray )
{
	var result = { min : null, max : null };
	objArray.sort();
	var i=0, iLen=objArray.length;
	for( ; i<iLen; ++i )
	{
		if( objArray[ i ] )
		{
			result.min = objArray[ i ];
			result.max = objArray[ iLen-1 ];
			break;
		}
	}
	/*/
	var result = { min : null, max : null };
		result.min = Math.min.apply( null, objArray );
		result.max = Math.max.apply( null, objArray );
	//*/
	return result;
};

/**
 * Point Array에서 value 값과 가장 가까운 최소값 인덱스를 반환
 * @param {Array} points :: [ x, y, x, y, ... ]
 * @return {Object}
 */
window.ESR23Common.getMiddleValueFromPointArray = function( points )
{
	var resultPoint = null;
	var i=0, iLen=points.length;
	var arrX = [];
	var arrY = [];
	for( ; i<iLen; ++i )
	{
		if( i % 2 == 1 ) arrY.push( points[ i ] );
		else arrX.push( points[ i ] );
	}
	arrX = arrX.sort();
	arrY = arrY.sort();
	if( arrX.length > 0 && arrY.length > 0 )
	{
		resultPoint = {
			x : arrX[ parseInt( arrX.length / 2 ) ]
			, y : arrY[ parseInt( arrY.length / 2 ) ]
		};
	}
	return resultPoint;
};

/**
 * JSON Array에서 propertyName 객체의 값 중 최소값과 최대값을 반환
 * @param {Array} objArray
 * @param {String} propertyName
 * @return {Object}
 */
window.ESR23Common.getMinMax_fromJSONArrayByProperty = function( objArray, propertyName )
{
	var a = window.ESR23Common.getArrayJsonArrayByProperty( objArray, propertyName );
		//a.sort();
		a.sort(function(a,b){ return a-b; }); // 작은 숫자부터 나오도록 함 ;

	var result = { min : null, max : null };
		result.min = a[ 0 ];
		result.max = a[ a.length - 1 ];
	return result;
};

/**
 * JSON Array 중복제거
 * @param {Array} objArray
 * @param {String} propertyName
 */
window.ESR23Common.getUniqueJsonArrayByProperty = function( objArray, propertyName )
{
	var key = window.ESR23Common.getArrayJsonArrayByProperty( objArray, propertyName );
		key = key.getUnique();
	var result = [];
	var j=0, jLen=key.length;
	var o = null;
	for( ; j<jLen; ++j )
	{
		i = 0;
		for( ; i<iLen; ++i )
		{
			o = objArray[ i ];
			if( o[ propertyName ] == key[ j ] )
			{
				result[ j ] = o;
				break;
			}
			else continue;
		}
	}
	return result;
};

/**
 * 날짜 비교
 * @param {Date} startDt
 * @param {Date} endDt
 * @param {String} unit mm
 * @return {Number} result
 */
window.ESR23Common.compareDate = function( startDt, endDt, unit )
{
	unit = unit ? unit : "min";
	var unitNum = 0;
	if( unit == "min" ) unitNum = 1000 * 60; // 분단위 ;
	else if( unit == "h" ) unitNum = 1000 * 60 * 60; // 시간단위 ;
	else if( unit == "sec" ) unitNum = 1000; // 초단위 ;
	else if( unit == "dd" ) unitNum = 1000 * 60 * 60 * 24; // 일단위 ;

	var result = ( endDt.getTime() - startDt.getTime() );
		result = result / unitNum;
	return result;
};

/**
 * 날짜 비교
 * @param {String} startDt
 * @param {String} endDt
 * @param {String} unit mm
 * @return {Boolean} result
 */
window.ESR23Common.compareDate_String = function( startDt, endDt )
{
	startDt = startDt.replaceAll( "-", "" );
	endDt = endDt.replaceAll( "-", "" );
	startDt = new Date( parseInt( startDt.substring( 4, 6 ) )  + "/" + parseInt( startDt.substring( 6, 8 ) ) + "/" + parseInt( startDt.substring( 0, 4 ) ) );
	endDt = new Date( parseInt( endDt.substring( 4, 6 ) ) + "/" + parseInt( endDt.substring( 6, 8 ) ) + "/" + parseInt( endDt.substring( 0, 4 ) ) );
	var compare = window.ESR23Common.compareDate( startDt, endDt, "dd" );
	if( compare > 0 ) return true;
	else return false;
};

window.ESR23Common.removeAnnotation = function( str )
{
	return str.replace( /\/\*([^*]|[\r\n]|(\*+([^\*\/]|[\r\n])))*\*\//g, "" ).replace( /\n|\t/g, "" ).replace( /\/\/(.*?)\;/g, "" );
};

/**
 * 날짜 정보를 입력 받아서 유효성 체크
 * @param inDate YYYYMMDD
 */
window.ESR23Common.valid_Date = function( inDate )
{
	var END_OF_MONTH = [0,31,28,31,30,31,30,31,31,30,31,30,31];

	if(isNaN(inDate)) return false;
	if(Number(inDate)/10000000 <= 1) return false;

	var strDate	= String(inDate);
	var nYear	= Number(strDate.substring(0,4));
	var nMonth	= Number(strDate.substring(4,6));
	var nDay	= Number(strDate.substring(6,8));

	//년 확인 ;
	if(nYear <= 0) return false;

	//월 확인 ;
	if(nMonth < 1 || nMonth > 12) return false;

	//윤달 확인 ;
	if(nYear % 4 == 0)
		if(nYear % 100 != 0 || nYear % 400 == 0)
			END_OF_MONTH[2] = 29;

	//일 확인 ;
	if(nDay < 1 || END_OF_MONTH[nMonth] < nDay) return false;

	return true;
};

//----------------------------------------------------------------------------------------------------;
// GETTER ;
//----------------------------------------------------------------------------------------------------;

/**
 * 해당 object의 절대좌표 ( 좌상단 ) 반환
 * @param {Object} obj
 * @return {Object}
 */
window.ESR23Common.getAbsoluteXY = function( obj )
{
	var position = { x : 0, y : 0 };
	if( obj )
	{
		position.x = obj.offsetLeft;
		position.y = obj.offsetTop;

		if( obj.offsetParent )
		{
			var parentpos = window.ESR23Common.getAbsoluteXY( obj.offsetParent );
			position.x += parentpos.x;
			position.y += parentpos.y;
		}
	}
	position.cx = obj.offsetWidth;
	position.cy = obj.offsetHeight;
	return position;
};

/**
 * canvas를 이용하여 해당 이미지 객체를 Base64 String으로 반환
 * @param {Object} imgObj
 * @return {String}
 */
window.ESR23Common.getBase64Image = function( imgObj )
{
	var canvas = document.createElement("canvas");
		canvas.width = imgObj.width;
		canvas.height = imgObj.height;
	var ctx = canvas.getContext("2d");
		ctx.drawImage( imgObj, 0, 0 );

	// Get the data-URL formatted image
	// Firefox supports PNG and JPEG. You could check img.src to
	// guess the original format, but be aware the using "image/jpg"
	// will re-encode the image.
	var dataURL = canvas.toDataURL("image/png");

	return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
};

/**
 * canvas를 이용하여 해당 url에 존재하는 이미지 객체를 Base64 String으로 반환
 * @param {String} url
 * @return {String}
 */
window.ESR23Common.getBase64FromImageUrl = function( url )
{
	var img = new Image();
		img.src = url;
		img.onload = function()
		{
			var canvas = document.createElement("canvas");
				canvas.width = this.width;
				canvas.height = this.height;
			var ctx = canvas.getContext("2d");
				ctx.drawImage(this, 0, 0);
			var dataURL = canvas.toDataURL("image/png");
			return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");

		};
};

/**
 * URL로부터 Context 반환
 * @return {String}
 */
window.ESR23Common.getContextPath = function()
{
	var offset = null;
	var ctxPath = null;
	if( location.host.length > 0 )
	{
		offset = location.href.indexOf(location.host)+location.host.length;
		//ctxPath = location.href.substring(offset,location.href.lastIndexOf( "/", offset+1 ) );
		ctxPath = location.href.substring(offset+1,location.href.lastIndexOf( "/" ) );
		ctxPath = "/"+ctxPath.split("/")[0];
	}
	else
		ctxPath = ".";
	return ctxPath;
};

/**
 * 문자열로부터 Date 객체를 반환
 * @param {String} str
 */
window.ESR23Common.getDateFromStr = function( str )
{
	str = str.replace( /[^0-9]/g, "" ); // 숫자가 아닌 문자는 전부 삭제 ;
	var y = 1997;
	var m = 0;
	var dd = 1;
	var hh = 0;
	var mm = 0;
	var ss = 0;
	var ms = 0;

	if( str.length >= 4 ) y = str.substr( 0, 4 );
	if( str.length >= 6 ) m = str.substr( 4, 2 );
	if( str.length >= 8 ) dd = str.substr( 6, 2 );
	if( str.length >= 10 ) hh = str.substr( 8, 2 );
	if( str.length >= 12 ) mm = str.substr( 10, 2 );
	if( str.length >= 14 ) ss = str.substr( 12, 2 );
	if( str.length >= 17 ) ms = str.substr( 14, 2 );

	return new Date( y, m, dd, hh, mm, ss, ms );
};

/**
 * get string byte length
 * @param {String} str
 * @param {Number} chrByte
 */
window.ESR23Common.getByteLength = function( str, chrByte )
{
	chrByte = chrByte ? chrByte : 2;
	var resultSize = 0;
	if( str == null ) return 0;
	var i=0, iLen=str.length;
	var c = null;
	for( ; i<iLen; ++i )
	{
		c = escape( str.charAt( i ) );
		if( c.length == 1 ) resultSize++;
		else if( c.indexOf( "%u" ) != -1 ) resultSize += chrByte;
		else if( c.indexOf( "%" ) != -1 ) resultSize += c.length / 3;
	} // end for ;
	return resultSize;
}

/**
 * get string byte length index
 * @param {String} str
 */
window.ESR23Common.getByteLengthIndex = function( str, size )
{
	var resultSize = 0;
	if( str == null ) return 0;
	var i=0, iLen=str.length;
	var c = null;
	for( ; i<iLen; ++i )
	{
		c = escape( str.charAt( i ) );
		if( c.length == 1 ) resultSize++;
		else if( c.indexOf( "%u" ) != -1 ) resultSize += 2;
		else if( c.indexOf( "%" ) != -1 ) resultSize += c.length / 3;

		if( resultSize == size ) break;
	} // end for ;
	return i;
}

/**
 * color RGB to 16진수 String
 * @function
 * @param {Number} r
 * @param {Number} g
 * @param {Number} b
 * @return {String}
 * @sample
 * <code>
 * window.ESR23Common.getColorHexFromRGB( 255, 0, 0 );
 * </code>
 */
window.ESR23Common.getColorHexFromRGB = function( r, g, b )
{
	var rgb = b | (g << 8) | (r << 16);
	return '#' + (0x1000000 + rgb).toString(16).slice(1);
};

/**
 * color 16진수 string to RGB Array
 * @function
 * @param {String} str
 * @return {Array}
 * @sample
 * <code>
 * window.ESR23Common.getColorRGBFromHex_Array( "ff0000" );
 * </code>
 */
window.ESR23Common.getColorRGBFromHex_Array = function( str )
{
	var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(str);
    return result ? [
			parseInt(result[1], 16)
			, parseInt(result[2], 16)
			, parseInt(result[3], 16)
    ] : null;
};

/**
 * color 16진수 string to RGB Object
 * @function
 * @param {String} str
 * @return {Object}
 * @sample
 * <code>
 * window.ESR23Common.getColorRGBFromHex_Object( "ff0000" );
 * </code>
 */
window.ESR23Common.getColorRGBFromHex_Object = function( str )
{
	var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(str);
    return result ? {
        r : parseInt(result[1], 16)
        , g : parseInt(result[2], 16)
        , b : parseInt(result[3], 16)
    } : null;
};

/**
 * 시간을 포맷 적용하여 반환 ( 시간 입력 안될 시 현재 시각 )
 * @param {String} format
 * <code>
 * 	format sample : 'YYYY-MM-DD hh:mm:ss'
 * </code>
 * @param {Date) d
 * @return {String} now
 */
window.ESR23Common.getTimeStamp = function( format, d )
{
	format = format ? format : "YYYY-MM-DD hh:mm:ss";
	var leadingZeros = function( value, digit )
	{
		var result = "";
		value = value.toString();

		if( value.length < digit )
		{
			var i=0, iLen=digit-value.length;
			for( ; i<iLen; ++i ) result += "0";
		}
		return result + value;
	};

	if( !d ) d = new Date();
	var f = leadingZeros;

	var y = f( d.getFullYear(), 4 ); // 년 ;
	var m = f( d.getMonth() + 1, 2 ); // 월 ;
	var dd = f( d.getDate(), 2 ); // 일 ;

	var hh = f( d.getHours(), 2 ); // 시 ;
	var mm = f( d.getMinutes(), 2 ); // 분 ;
	var ss = f( d.getSeconds(), 2 ); // 초 ;

	var now = format.replace( "YYYY", y );
		now = now.replace( "MM", m );
		now = now.replace( "DD", dd );
		now = now.replace( "hh", hh );
		now = now.replace( "mm", mm );
		now = now.replace( "ss", ss );

	return now;
};

/**
 * 입력된 전화번호를 형식적용하여 반환
 * @param {String} str
 * @return {String} result
 */
window.ESR23Common.getTel = function( str )
{
	str = str.replace( /[^0-9]/g, "" ); // 숫자만 추출 ;
	var reg = null;
	var out = null;
	var result = "";
	if( str.length < 4 )
	{
		reg = "";
		out = "";
	}
	else if( str.length < 7)
	{
		reg = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)/;
		out = "$1-$2";
	}
	else
	{
		reg = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/;
		out = "$1-$2-$3";
	}
	result = str.replace( reg, out );
	if( result.length > 14 ) result = result.substr(0, 13);
	return result;
};

/**
 * 입력된 시간을 입력된 시간 형식을 적용하여 반환
 * @param {String} time
 * @param {String} format
 * <code>
 * 	format sample : 'YYYY-MM-DD hh:mm:ss'
 * </code>
 * @return {String} result
 */
window.ESR23Common.getTimeFormat = function( time, format )
{
	time = time.replace( /[^0-9]/g, "" ); // 숫자만 추출 ;
	format = format ? format : "YYYY-MM-DD hh:mm:ss";
	var len = time.length;

	if( len >= 8 )
	{
		if( window.ESR23Common.valid_Date( time.substring( 0, 8 ) ) )
		{
			var result = format;
			if( result.indexOf( "YYYY" ) > -1 && len >= 4 )
			{
				result = result.replace( "YYYY", time.substring( 0, 4 ) );
			}
			if( result.indexOf( "MM" ) > -1 && len >= 6 )
			{
				result = result.replace( "MM", time.substring( 4, 6 ) );
			}
			if( result.indexOf( "DD" ) > -1 && len >= 8 )
			{
				result = result.replace( "DD", time.substring( 6, 8 ) );
			}
			if( result.indexOf( "hh" ) > -1 && len >= 10 )
			{
				result = result.replace( "hh", time.substring( 8, 10 ) );
			}
			if( result.indexOf( "mm" ) > -1 && len >= 12 )
			{
				result = result.replace( "mm", time.substring( 10, 12 ) );
			}
			if( result.indexOf( "ss" ) > -1 && len >= 14 )
			{
				result = result.replace( "ss", time.substring( 12, 14 ) );
			}
			return result;
		}
		else return "";
	}
	else return "";
};

/**
 * JSON Data로부터 GET 방식의 URL을 만들어 반환
 * @param {String} url
 * @param {Object} d
 * @return {String} url
 */
window.ESR23Common.getURLFromJSON = function( url, d )
{
	var prop = "";
	var o = null;
	for( prop in d )
	{
		o = d[ prop ];
		url += "&" + prop + "=" + o;
	}
	url = url.replace( "&", "?" );
	return url;
};

//----------------------------------------------------------------------------------------------------;
// SETTER ;
//----------------------------------------------------------------------------------------------------;

/**
 * 콤마 자동생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Comma = function( obj )
{
	var s = parseFloat( obj.value.replace( /\,/g, "" ) );
	var ns = s.toString();
	var dp = null;

	if( isNaN( ns ) ) return "";

	dp = ns.search( /\./ );
	if( dp < 0 ) dp = ns.length;
	dp -= 2;
	while( dp > 0 )
	{
		ns = ns.substr( 0, dp ) + "," + ns.substr( dp );
		dp -= 3;
	}
	obj.value = ns;
	return true;
};

/**
 * 년도 포맷 자동 생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Year = function( obj )
{
	obj = obj.target;
	var s = parseFloat( obj.value.replace( /\-/g, "" ) );
	var ns = s.toString();
	if( ns.length >= 4 ) obj.value = ns.substr(0,4);
	return true;
};

/**
 * 날짜 포맷 자동 생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Date_NonDash = function( obj )
{
	obj = obj.target;
	var str = obj.value + "";
		str = str.replace( /[^0-9]/g, "" ); // 숫자만 추출 ;
	var reg = null;
	var out = null;
	var result = "";
	if( str.length < 4 )
	{
		reg = "";
		out = "";
	}
	else if( str.length < 7)
	{
		reg = /([0-9]{4})([0-9]{2})/;
		out = "$1$2";
	}
	else
	{
		reg = /([0-9]{4})([0-9]{2})([0-9]{2})/;
		out = "$1$2$3";
	}
	result = str.replace( reg, out );
	if( result.length > 10 ) result = result.substr(0, 9);
	obj.value = result;
	return true;
};

/**
 * 날짜 포맷 자동 생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Date = function( obj )
{
	obj = obj.target;
	var str = obj.value + "";
		str = str.replace( /[^0-9]/g, "" ); // 숫자만 추출 ;
	var reg = null;
	var out = null;
	var result = "";
	if( str.length < 4 )
	{
		reg = "";
		out = "";
	}
	else if( str.length < 7)
	{
		reg = /([0-9]{4})([0-9]{2})/;
		out = "$1-$2";
	}
	else
	{
		reg = /([0-9]{4})([0-9]{2})([0-9]{2})/;
		out = "$1-$2-$3";
	}
	result = str.replace( reg, out );
	if( result.length > 10 ) result = result.substr(0, 9);
	obj.value = result;
	return true;
};

/**
 * 전화번호 포맷 자동 생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Tel = function( obj )
{
	obj = obj.target;
	var s = obj.value.replace(/[^0-9]/g, "");
	obj.value = window.ESR23Common.getTel( s );
	return true;
};

/**
 * 시간 포맷 자동 생성
 * @param {Object} obj
 */
window.ESR23Common.setAutomaticFormat_Time = function( obj )
{
	obj = obj.target;
	var s = parseInt( obj.value.replace( /\:/g, "" ) );
	var ns = s.toString();

	var nLength = 0;
	var str = "";

	if( isNaN( ns ) ) return "";
	nLength = ns.length;
	if( nLength > 0 )
	{
		var i = 0;
		for( ; i<nLength ; ++i )
		{
			if( ( i == 1) || ( i == 3 ) ) str += ns.substr(i,1) + ":";
			else if( i < 5 ) str += ns.substr(i,1);
		}
	}
	obj.value = str;
	return true;
};

/**
 * 숫자 문자열에 콤마 적용하여 반환
 * @param {String} strNum
 */
window.ESR23Common.setComma = function( strNum )
{
	var result = null;
	strNum = strNum + "";
	strNum = strNum.split(".");
	if(strNum.length == 2){
		result = strNum[0].setComma();
		result = result + "." + strNum[1];
	} else {
		result = strNum[0].setComma();
	}
	return result;
};

window.ESR23Common.calc_digits_division = function( a, b ){
	var result = 0;
	var digits = 1000000;
	a = a * digits;
	b = b * digits;
	result = a / b;
	return result / digits;
};
window.ESR23Common.calc_digits_minus = function( a, b ){
	var result = 0;
	var digits = 1000000;
	a = a * digits;
	b = b * digits;
	result = a - b;
	return result / digits;
};
window.ESR23Common.calc_digits_multi = function( a, b ){
	var result = 0;
	var digits = 1000000;
	a = a * digits;
	b = b * digits;
	result = a * b;
	return result / digits;
};
window.ESR23Common.calc_digits_plus = function( a, b ){
	var result = 0;
	var digits = 1000000;
	a = a * digits;
	b = b * digits;
	result = a + b;
	return result / digits;
};

//----------------------------------------------------------------------------------------------------;
//----------------------------------------------------------------------------------------------------;
//----------------------------------------------------------------------------------------------------;
// Extends ;
//----------------------------------------------------------------------------------------------------;
//----------------------------------------------------------------------------------------------------;

//----------------------------------------------------------------------------------------------------;
//Array ;
//----------------------------------------------------------------------------------------------------;

/**
* Array Class에 getUnique 함수 설정 :: 중복 제거
* @returns {Array}
*/
Array.prototype.getUnique = function()
{
	var u = {}, a = [];
	var i = 0, l = this.length;
	for( ; i < l; ++i )
	{
		if(u.hasOwnProperty(this[i])) continue;
		a.push(this[i]);
		u[this[i]] = 1;
	}
	return a;
};

/**
 * Array Class에 remove 함수 설정 :: 인덱스 값으로 잘라내기
 * @param {Number} from
 * @param {Number} to
 * @returns {Array}
 */
Array.prototype.remove = function( from, to ) {
	var rest = this.slice( ( to || from ) + 1 || this.length );
	this.length = from < 0 ? this.length + from : from;
	this.push.apply( this, rest );
	return this;
};

//----------------------------------------------------------------------------------------------------;
//String ;
//----------------------------------------------------------------------------------------------------;

/**
 * String Class에 setComma 함수 설정 :: 문자열에 콤마 적용
 */
String.prototype.setComma = function()
{
	var temp_str = String( this );
	for(var i = 0 , retValue = String() , stop = temp_str.length; i < stop ; i++)
		retValue = ((i%3) == 0) && i != 0 ? temp_str.charAt((stop - i) -1) + "," + retValue : temp_str.charAt((stop - i) -1) + retValue;
	return retValue;
};

/**
 * String Class에 replaceAll 함수 설정 :: 문자열 변환
 */
String.prototype.replaceAll = function( orgStr, repStr )
{
	return this.split( orgStr ).join( repStr );
};

String.prototype.regexIndexOf = function( pattern, startIndex )
{
	startIndex = startIndex || 0;
	var searchResult = this.substr( startIndex ).search( pattern );
	return ( -1 === searchResult ) ? -1 : searchResult + startIndex;
};

String.prototype.regexLastIndexOf = function( pattern, startIndex )
{
	startIndex = startIndex === undefined ? this.length : startIndex;
	var searchResult = this.substr( 0, startIndex ).reverse().regexIndexOf( pattern, 0 );
	return ( -1 === searchResult ) ? -1 : this.length - ++searchResult;
};

String.prototype.reverse = function()
{
	return this.split("").reverse().join("");
};

//----------------------------------------------------------------------------------------------------;
//----------------------------------------------------------------------------------------------------;