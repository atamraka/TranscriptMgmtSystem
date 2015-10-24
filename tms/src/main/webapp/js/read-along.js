/**
 Created by muralidhar and surya on 9/24/2015.
 
 ****/
/***
 * @ Mahaveer on 10/24/2015
 *   added a basic template and retriving parameter from main.js file 
 */

var ReadAlong = {
    text_element: null,
    audio_element: null,
    autofocus_current_word: false,
    flag:null,

   words: [],
    bookMarkWords: [],
    init: function (args) {
        var name;


        for (name in args) {
            this[name] = args[name];
        }
        this.generateWordList();
        this.addEventListeners();
        this.selectCurrentWord();

    },
   
    /***
    @ vinay reddy on 10/24/2015
    
    Added all the listeners when user performs any actions
   
   */
  

  addEventListeners: function () {
      var that = this;
      var word_book=[];
      var temp;

          /**
       * Select next word (at that.audio_element.currentTime) when playing begins
       */
      that.audio_element.addEventListener('play', function () {
          that.selectCurrentWord();
          that.text_element.classList.add('speaking');
          console.log(that.flag);

      }, false);

  
        
    }
};