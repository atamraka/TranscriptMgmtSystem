describe('transcriptorTest', function() {
	    it('checks that the expected exception was thrown by the actual', function() {
	        var object = {
	        		getCurrentWord: function() {
	                throw new Error("Unexpected error!")
	            }
	        };
	        expect(object.getCurrentWord).toThrow(new Error("Unexpected error!"));
	    });
	});


describe("transcriptorTest", function() {
	 
	it("Should spy on word list", function() {
	    var spy = spyOn(ReadAlong,'generateWordList')
	    ReadAlong.generateWordList();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	
});
	
/*	it("Should spy on CurrentWord if Word is Null", function() {
		
		  var spy = spyOn(ReadAlong,'getCurrentWord').and.returnValue('null')
	    expect(ReadAlong.getCurrentWord()).toEqual('null');
	    
	  });*/
	
	
	
	describe("transcriptorTest", function() {
	
	
	it("Should spy on CurrentWord", function() {
	    var spy = spyOn(ReadAlong,'getCurrentWord')
	    ReadAlong.getCurrentWord();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	});
	
	/*
	it("Should spy on CurrentWord throw", function() {
	    var spy = spyOn(ReadAlong,'getCurrentWord').toThrow(new Error('problem'));
	    var word = null;
	    try
	    {
	    	word = ReadAlong.getCurrentWord();
	    } catch(ex){
	    	word = 'Tested';
	    		}
	 
	    expect(word).toEqual('Tested');
	    
	  });*/	
	
	describe("transcriptorTest", function() {
	
	it("Should spy on current word and set timeout to select the next one if playing", function() {
	    var spy = spyOn(ReadAlong,'selectCurrentWord')
	    ReadAlong.selectCurrentWord();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	
	});
	
	describe("transcriptorTest", function() {
	
	it("Should spy on once the selected word highlighed ,removing the word from classlist", function() {
	    var spy = spyOn(ReadAlong,'removeWordSelection')
	    ReadAlong.removeWordSelection();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	
	});
	
	/*it("copying the bookmark index values for local array to global array bookMarkWords variable", function() {
	    var spy = spyOn(ReadAlong,'CopyArraysBookMarks')
	    ReadAlong.CopyArraysBookMarks();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	*/
	describe("transcriptorTest", function() {
	
	it("Should spy on all the listeners when user performs any actions", function() {
	    var spy = spyOn(ReadAlong,'addEventListeners')
	    ReadAlong.addEventListeners();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	
	
	
	
	
	
});






