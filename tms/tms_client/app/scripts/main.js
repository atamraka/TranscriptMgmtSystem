/**
 * Created by muralidhar on 9/17/2015.
 */
'use strict';

angular.module('jsApp')
    .controller('MainCtrl',  function ($scope) {

         /*For Uploading the file*/
        $scope.data = 'none';
        $scope.add = function(){
            var f = document.getElementById('file').files[0],
                r = new FileReader();
            r.onloadend = function(e){
                $scope.data = e.target.result;
            }
            r.readAsBinaryString(f);
        };

        /*for uploading the audio file*/
        $scope.upload= function(audio){
         var file=document.getElementById('audio1');
           var filepath= file.value;
            alert(filepath);


        }

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

    });
