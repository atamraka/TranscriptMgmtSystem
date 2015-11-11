/**
 * 
 */


describe("mainTest", function () {
		
	var loading;
	var check=null;
	 beforeEach(function(){
	        loading ={
	        		setValue:function(value){
	        			check=value;
	        		}
	        }
	 spyOn(loading,'setValue');
	  loading.setValue(true);
	  loading.setValue(false);
	 });  
	  
	
	it("Testing the values which passed from selectinput file ", function(){
	    expect(loading.setValue).toHaveBeenCalled();		
	});
	
	it("Tracking all the arguments of its call", function(){
	    expect(loading.setValue).toHaveBeenCalledWith(true);	
	    expect(loading.setValue).toHaveBeenCalledWith(false);	
	});
	
	it("it should not call the function if not selected", function(){
	   expect(check).toBeNull();
	});
	
});


/*describe("Testing whether arguments are passed from home to selectinput to main ", function () {
	
	
		text_element: document.getElementById('passage-text'),
	    audio_element: document.getElementById('passage-audio'),
	    autofocus_current_word: document.getElementById('autofocus-current-word').checked,
	
	var  text_element=" <body><span data-dur data-begin>  today </span>"

	var  audio_element=document.getElementById('passage-audio');
	it("Testing for loading main function for values ", function(){
		
		expect(text_element).toMatch("<span>");
			
	});
});*/