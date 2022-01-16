// "    hello     "
function ltrim(msg){
	//将msg以空格打头的，全部替换成""
	return msg.replace(/^\s+/,"");
}

function rtrim(msg){
	//将msg以空格结尾的，全部替换成""
	return msg.replace(/\s+$/,"");
}

function trim(msg){
	return rtrim(ltrim(msg));
	
	
}