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
     * @Mahaveer on 10/26/2015
     * 
     * From the audio's currentTime, find the word that is currently being played
     * @todo this would better be implemented as a binary search
     */
    getCurrentWord: function () {
        var i;
        var len;
        var is_current_word;
        var word = null;
        for (i = 0, len = this.words.length; i < len; i += 1) {
            //
            //console.log(this.words.length);
            is_current_word = (
                (
                    this.audio_element.currentTime >= this.words[i].begin
                    &&
                    this.audio_element.currentTime < this.words[i].end
                )
                ||
                (this.audio_element.currentTime < this.words[i].begin)
            );
            if (is_current_word) {
                word = this.words[i];
                break;
            }
        }

        if (!word) {
            throw Error('Unable to find current word and we should always be able to.');
        }
        return word;
    },

    _current_end_select_timeout_id: null,
    _current_next_select_timeout_id: null,

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
     * @vishal thakur 10/26/2015
     * added a function once the selected word highlighed ,removing the word from classlist
     * 
     */

    removeWordSelection: function() {
        // There should only be one element with .speaking, but selecting all for good measure
        var spoken_word_els = this.text_element.querySelectorAll('span[data-begin].speaking');
        Array.prototype.forEach.call(spoken_word_els, function (spoken_word_el) {
            spoken_word_el.classList.remove('speaking');
        });
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


/**
 * @vinay reddy on 10/26/2015
 * Abandon seeking the next word because we're paused
 */
that.audio_element.addEventListener('pause', function (e) {
    that.selectCurrentWord(); // We always want a word to be selected
    that.text_element.classList.remove('speaking');
}, false);


/**
         * Nitesh Agarwal on 10/27/2015
         * Seek by selecting a word (event delegation)
         */
        function on_select_word_el(e) {
            if (!e.target.dataset.begin) {
                return;
            }
            e.preventDefault();

            var i = e.target.dataset.index;
            that.audio_element.currentTime = that.words[i].begin + 0.01; //Note: times apparently cannot be exactly set and sometimes select too early
            that.selectCurrentWord();
        }
        that.text_element.addEventListener('click', on_select_word_el, false);
        that.text_element.addEventListener('keypress', function (e) {
            if ( (e.charCode || e.keyCode) === 13 /*Enter*/) {
                on_select_word_el.call(this, e);
            }
        }, fals);
