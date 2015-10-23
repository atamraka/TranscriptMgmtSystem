/**
 * Created by Anjila on 09/19/2015.
 * 
 * this page will load and send a arguments with text,audio,word focus to other script file
 * and also initialize the words with hightlight color
 * 
 * 
 */
window.addEventListener('load', function (e) {
    try {
        var args = {
            text_element: document.getElementById('passage-text'),
            audio_element: document.getElementById('passage-audio'),
            autofocus_current_word: document.getElementById('autofocus-current-word').checked,
        };
        ReadAlong.init(args);
        document.getElementById('autofocus-current-word').addEventListener('change', function (e) {
            ReadAlong.autofocus_current_word = this.checked;
        });
        
    }
    catch (err) {
        console.error(err);
    }
    document.body.classList.add('initialized');
    

},false);