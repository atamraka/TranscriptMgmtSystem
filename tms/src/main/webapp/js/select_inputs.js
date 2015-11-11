/**
 * Created by Muralidhar on 11/01/2015.
 *
 *Seperation javascript code from home page and implementing upload and main function loading
 *
 *
 */

function onAudioChange() {
	/*============ LOG INFORMATION ============
	Anjila*/
	var log = log4javascript.getDefaultLogger();
	log.info("onAudioChange function in select_inputs.js");
	/*======================*/
	var selectBox = document.getElementById("selectAudioBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	console.log(selectedValue);
	var path = "audiofiles/"
	var audio = document.getElementById("passage-audio");
	audio.src = path + selectedValue;
	console.log(audio.src);
}

function onTextChange() {
	/*============ LOG INFORMATION ============
	Anjila*/
	var log = log4javascript.getDefaultLogger();
	log.info("onTextChange function  in select_inputs.js");
	/*======================*/
	/*removed Enable option button disabled attribute false
	 suryanarayana */
	var selectBox = document.getElementById("selecttextbox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	console.log(selectedValue);
	$("#passage-text").load('textfiles/' + selectedValue);
}

function loading() {
	/*============ LOG INFORMATION ============
	Anjila*/
	var log = log4javascript.getDefaultLogger();
	log.info("on loading main function in select_inputs.js");
	/*======================*/
	var check = document.getElementById("MainPage").value;
	console.log(check);
	document.getElementById("Bookmark").disabled = false;
	document.getElementById("EndBookmark").disabled = false;
	document.getElementById("playWithBookmark").disabled = false;
	document.getElementById("passage-audio").hidden = false;
	load_main(check);
}
