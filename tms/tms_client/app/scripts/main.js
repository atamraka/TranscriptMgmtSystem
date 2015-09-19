/**
 * Created by muralidhar on 9/17/2015.
 */
'use strict';

angular.module('jsApp')
    .controller('MainCtrl', function ($scope,$http) {

        $scope.UserOptions=[{options:'audio1.mp3'},{options:'audio2.mp3'},{options:'audio3.mp3'},{options:'audio4.mp3'}];

        /*For Uploading the file*/
        $scope.data = 'none';
        $scope.uploadTextFile = function(){
            var f = document.getElementById('file').files[0],
                r = new FileReader();
            r.onloadend = function(e){
                $scope.data = e.target.result;
            }
            r.readAsBinaryString(f);
        };


    /*    options functionality for audio drop down*/
        $scope.ChooseOption = function(item) {

            var SelectViewoption = item.options;
            var loc= "audios/";
             $scope.filepath =loc+SelectViewoption;

        };


        /* For selecting the file in text area*/
        $scope.showSelectedText = function() {
            $scope.selectedText =  $scope.getSelectionText();
        };
        $scope.getSelectionText = function() {
            var text = "";
            if (window.getSelection) {
             text = window.getSelection().toString();

             } else if (document.selection && document.selection.type != "Control") {
             text = document.selection.createRange().text;

             }
           /* var txtarea =document.getElementById("fileTextArea");
            var start = txtarea.selectionStart;
            // obtain the index of the last selected character
            var finish = txtarea.selectionEnd;
            // obtain the selected text
            var sel = txtarea.value.substring(start, finish);
            text=sel;*/

            return text;
        };

        /*for playing audio other than default function of player*/
        $scope.playAudio=function() {
            var audio = document.getElementById("audioRef");
            audio.play();
        };

       /* for pausing the audio without default functionality*/
       $scope.pauseAudio=function(){
           var audio = document.getElementById("audioRef");
           audio.pause();
           alert(audio.currentTime);
       }

        /* function for saving the users bookmark*/
        $scope.saveBookMark=function(){

        }

        /*function for playing with saved bookmarks*/
        $scope.playBackWithBookmark=function(){


        }



    });
