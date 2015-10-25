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
    /** 
     * @Nitesh agarawal extended code on 10/24/2015
     * 
     * Build an index of all of the words that can be read along with their begin,
     * and end times, and the DOM element representing the word.
     */
    generateWordList: function () {
        var word_els = this.text_element.querySelectorAll('[data-begin]');
        this.words = Array.prototype.map.call(word_els, function (word_el, index) {
            var word = {
                'begin': parseFloat(word_el.dataset.begin),
                'dur': parseFloat(word_el.dataset.dur),
                'element': word_el
            };
            word_el.tabIndex = 0; // to make it focusable/interactive
            word.index = index;
            word.end = word.begin + word.dur;
            word_el.dataset.index = word.index;
            return word;
        });
    },



      /**
     * 
     * @Vivek Bhatnagar on 10/25/2015
     * Select the current word and set timeout to select the next one if playing
     */
    selectCurrentWord: function() {
     var that = this;
     var current_word = this.getCurrentWord();
     var is_playing = !this.audio_element.paused;
      if (that.flag) {
         console.log(that.flag);
         for (var i in that.bookMarkWords) {
             if (that.bookMarkWords[i] == current_word.index) {
                 current_word.element.classList.add('bookmark');
                 if (current_word.element.classList.contains('bookmark')) {
                     if (that.autofocus_current_word) {
                         current_word.element.focus();
                     }
                 }
             }

         }
      }
          if (!current_word.element.classList.contains('speaking')) {
         this.removeWordSelection();
         current_word.element.classList.add('speaking');
         if (this.autofocus_current_word) {
             current_word.element.focus();
         }
         }
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
    },
  };