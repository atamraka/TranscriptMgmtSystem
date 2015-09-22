/**
 * Created by muralidhar on 9/17/2015.
 * surya @ updated upload textfile from bnary to text
 * implemented the watch for getting index value
 */
'use strict';

angular.module('jsApp')
    .controller('MainCtrl', function ($scope,$http) {

        $scope.UserOptions = [{options: 'audio1.mp3'}, {options: 'audio2.mp3'}, {options: 'audio3.mp3'}, {options: 'audio4.mp3'}];

        /*For Uploading the file*/
        $scope.data = 'none';
        $scope.uploadTextFile = function () {
            var r = new FileReader();
            r.onloadend = function (e) {
                $scope.$apply(function () {
                    $scope.data = r.result;
                });

            };
            var FileInput = document.getElementById('file');
            var data = FileInput.files[0];


            r.readAsText(data);

        };
        /*$scope.$watch("data", function() {
            var words, wordNumber, data, length;
            words = $scope.data.split(" ");
           /!* console.log(words);*!/
            console.log(words.length);
        });*/


        /*    options functionality for audio drop down*/
        $scope.ChooseOption = function (item) {

            var SelectViewoption = item.options;
            var loc = "audios/";
            $scope.filepath = loc + SelectViewoption;

        };


        /* For selecting the text in text area*/
        $scope.$watch("data", function() {
            var words, wordNumber ;
            words = $scope.data.split(" ");
            /* console.log(words);*/
            console.log(words.length);

            $scope.showSelectedText = function () {
                $scope.selectedText = $scope.getSelectionText();
            };
            $scope.getSelectionText = function () {

                var text = "", range = document.createRange();

                if (window.getSelection) {
                    text = window.getSelection().toString();
                    for (var i = 0; i <= words.length; i++) {
                        if (words[i] == text) {
                            text=i;
                        }
                    }
                    /*range.selectNodeContents(this);
                     window.getSelection().addRange(range);*/

                } else if (document.selection && document.selection.type != "Control") {
                    range = document.selection.createRange().text;
                    alert("range");
                }


                return text;
            };
            $scope.playBackWithBookmark = function () {
                /* var P = '{"time":"7","word":"Sylvia"}';*/
                /* $scope.data = JSON.parse(P);*/
                /* var audio = document.getElementById("audioRef");
                 audio.play();*/

                $scope.$watch('data', function() {
                    $scope.data=function(){

                    }
                });


            };
        });

        /*for playing audio other than default function of player*/
        $scope.playAudio = function () {
            var audio = document.getElementById("audioRef");
            audio.play();
        };

        /* for pausing the audio without default functionality*/
        $scope.pauseAudio = function () {
            var audio = document.getElementById("audioRef");
            audio.pause();
            alert(audio.currentTime);
        };

        /* function for saving the users bookmark*/
        $scope.saveBookMark = function () {

        };


    });





