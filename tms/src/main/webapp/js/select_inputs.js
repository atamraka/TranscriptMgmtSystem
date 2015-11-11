/**
 * Created by Muralidhar on 11/01/2015.
 * 
 *Seperation javascript code from home page and implementing upload and main function loading
 * 
 * 
 */

function onAudioChange() {
	/*var log = log4javascript.getDefaultLogger();
	log.info("onAudioChange function");*/
	var selectBox = document.getElementById("selectAudioBox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	console.log(selectedValue);
	var path = "audiofiles/"
	var audio = document.getElementById("passage-audio");
	audio.src = path + selectedValue;
	console.log(audio.src);
}

function onTextChange() {
	/*var log = log4javascript.getDefaultLogger();
	log.info("onTextChange function");*/
	var selectBox = document.getElementById("selecttextbox");
	var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	console.log(selectedValue);
	$("#passage-text").load('textfiles/' + selectedValue);
}

function loading() {
	/*var log = log4javascript.getDefaultLogger();
	log.info("onloading main function");*/
	var check = document.getElementById("MainPage").value;
	console.log(check);
	document.getElementById("Bookmark").disabled = false;
	document.getElementById("EndBookmark").disabled = false;
	document.getElementById("playWithBookmark").disabled = false;
	document.getElementById("passage-audio").hidden = false;
	load_main(check);
}
