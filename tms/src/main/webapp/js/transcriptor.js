/**
 Created by muralidhar and surya on 9/24/2015.
 
 ****/
/***
 * @ Mahaveer on 10/24/2015
 *   added a basic template and retriving parameter from main.js file 
 */
/*============ LOG INFORMATION ============
 suryanarayana*/
var log  = log4javascript.getDefaultLogger();
var ReadAlong = {
    text_element: null,
    audio_element: null,
    autofocus_current_word: false,
    flag: null,

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
            //Log.info(this.words.length);
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
    selectCurrentWord: function () {
        var that = this;
        var current_word = this.getCurrentWord();
        var is_playing = !this.audio_element.paused;
        if (that.flag) {
            log.info(that.flag);
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

    /**
     *
     * @Surya & Muralidhar
     *
     * The timeupdate Media event does not fire repeatedly enough to be
     * able to rely on for updating the selected word (it hovers around
     * 250ms resolution), so we add a setTimeout with the exact duration
     * of the word.
     */
        if (is_playing) {
    // Remove word selection when the word ceases to be spoken
       var seconds_until_this_word_ends = current_word.end - this.audio_element.currentTime; // Note: 'word' not 'world'! ;-
       clearTimeout(this._current_end_select_timeout_id);
        this._current_end_select_timeout_id = setTimeout(
          function () {
            if (!that.audio_element.paused) { // we always want to have a word selected while paused
                current_word.element.classList.remove('speaking');
            }
          },
          Math.max(seconds_until_this_word_ends * 1000, 0)
        );

    // Automatically trigger selectCurrentWord when the next word begins
        var next_word = this.words[current_word.index + 1];
        if (next_word) {
           var seconds_until_next_word_begins = next_word.begin - this.audio_element.currentTime;

         var orig_seconds_until_next_word_begins = seconds_until_next_word_begins; // temp
         clearTimeout(this._current_next_select_timeout_id);
         this._current_next_select_timeout_id = setTimeout(
            function () {
                that.selectCurrentWord();
            },
            Math.max(seconds_until_next_word_begins * 1000, 0)
        );
      }
        }

    },


/***
     * @vishal thakur 10/26/2015
     * added a function once the selected word highlighed ,removing the word from classlist
     *
     */

    removeWordSelection: function () {
        // There should only be one element with .speaking, but selecting all for good measure
        var spoken_word_els = this.text_element.querySelectorAll('span[data-begin].speaking');
        Array.prototype.forEach.call(spoken_word_els, function (spoken_word_el) {
            spoken_word_el.classList.remove('speaking');
        });
    },
	
	/*** anjila /10/11/2015
	* copying the bookmark index values for local array to global array bookMarkWords variable
	* @param bookvalues
	*/
    
	CopyArraysBookMarks: function(bookvalues) {
        Array.prototype.push.apply(this.bookMarkWords, bookvalues);
        /*============ LOG INFORMATION ============
         suryanarayana*/
        log.info("copied array "+this.bookMarkWords);
    },


    /***
     @ vinay reddy on 10/24/2015

     Added all the listeners when user performs any actions

     */
    addEventListeners: function () {
        var that = this;
        var word_book = [];
        var temp;

        /**
         * Select next word (at that.audio_element.currentTime) when playing begins
         */
        that.audio_element.addEventListener('play', function () {
            that.selectCurrentWord();
            that.text_element.classList.add('speaking');
            /*============ LOG INFORMATION ============
             suryanarayana*/
            log.info(that.flag);

        }, false);


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
            if ((e.charCode || e.keyCode) === 13 /*Enter*/) {
                on_select_word_el.call(this, e);
            }
        }, false);
        /**
         * @ Ravi charan
         *
         *added  Spacebar toggles playback
         */
        document.addEventListener('keypress', function (e) {
            if ((e.charCode || e.keyCode) === 32 /*Space*/) {
                e.preventDefault();
                if (that.audio_element.paused) {
                    that.audio_element.play();
                }
                else {
                    that.audio_element.pause();
                }
            }
        }, false);


        /**
         * @ Raghu verma
         * First click handler sets currentTime to the word, and second click
         * here then causes it to play.
         * @todo Should it stop playing once the duration is over?
         */
        that.text_element.addEventListener('dblclick', function (e) {
            e.preventDefault();
            that.audio_element.play();
        }, false);
        /**
         *
         *  Vinay Reddy on 10/29/2015
         * Select a word when seeking
         */
        that.audio_element.addEventListener('seeked', function (e) {
            that.selectCurrentWord();
            var audio_element = this;
            if (!audio_element.paused) {
                var previousTime = audio_element.currentTime;
                setTimeout(function () {
                    if (!audio_element.paused && previousTime === audio_element.currentTime) {
                        audio_element.currentTime += 0.01; // Attempt to unstick
                    }
                }, 500);
            }
        }, false);

        /**
         * @Vinay Reddy on 10/30/2015
         * Select a word when seeking
         */

        /* removed rate change eventlistner
        * @ suryanarayana on 11/10/2015
         */
      
         /***
         @Muralidhar  on 10/15/2015
          added a Listener when user clicks on buttom playwithboomark
         
         */
       document.getElementById("playWithBookmark").addEventListener('click',function(){
           /*============ LOG INFORMATION ============
            suryanarayana*/
           log .info("in play with book mark function");
           log .info(that.flag);
            that.audio_element.play();
           that.flag=document.getElementById("playWithBookmark").value;
        });

        /***
         @suryanarayna  on 10/12/2015
         added below two function when user clicks on bookmark and EndBookMark buttons
         */

        document.getElementById("Bookmark").addEventListener('click', function () {
            var current_word = that.getCurrentWord();
            if (temp != false) {
                word_book.push(current_word.index);

            }
        });

        /** suryanarayana */

        document.getElementById("EndBookmark").addEventListener('click', function () {
            temp = false;
            that.CopyArraysBookMarks(word_book);
            that.audio_element.load();


        });
    }
};