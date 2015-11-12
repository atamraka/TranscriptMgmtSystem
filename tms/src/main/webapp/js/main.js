/**
 * Created by Anjila on 09/19/2015.
 *
 * this page will load and send a arguments with text,audio,word focus to other script file
 * and also initialize the words with hightlight color
 *
 *  changed eventlistner with function load_main
 *  modified by suryanarayana on 10/10/15
 */
function load_main(value) {
    if(value) {
        var log = log4javascript.getDefaultLogger();
        log.info("loading from selectinput class");
        try {
            var args = {
                text_element: document.getElementById('passage-text'),
                audio_element: document.getElementById('passage-audio'),
                autofocus_current_word: document.getElementById('autofocus-current-word').checked,


            };

            ReadAlong.init(args);
            document.getElementById('autofocus-current-word').addEventListener('change', function (e) {
                ReadAlong.autofocus_current_word=this.checked;
            });


        }
        catch (err) {
            /* Log.error(err);*/
        }
        document.body.classList.add('initialized');

    }

};