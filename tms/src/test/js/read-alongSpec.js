describe('toThrow', function() {
	    it('checks that the expected exception was thrown by the actual', function() {
	        var object = {
	        		getCurrentWord: function() {
	                throw new Error("Unexpected error!")
	            }
	        };
	        expect(object.getCurrentWord).toThrow(new Error("Unexpected error!"));
	    });
	});
	
	describe("Spies", function() {
	 
	it("Should spy on word list", function() {
	    var spy = spyOn(ReadAlong,'generateWordList')
	    ReadAlong.generateWordList();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	
	it("Should spy on CurrentWord if Word is Null", function() {
		
	    var spy = spyOn(ReadAlong,'getCurrentWord').and.returnValue('null')
	    
	    expect(ReadAlong.getCurrentWord()).toEqual('null');
	    
	  });
	
	
	
	it("Should spy on CurrentWord", function() {
	    var spy = spyOn(ReadAlong,'getCurrentWord')
	    ReadAlong.getCurrentWord();
	    expect(spy).toHaveBeenCalled();
	    
	  });
	  
	  
	  
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
	    
	  });
	
	
	
	it("Should spy on current word and set timeout to select the next one if playing", function() {
	    var spy = spyOn(ReadAlong,'selectCurrentWord')
	    ReadAlong.selectCurrentWord();
	    expect(spy).toHaveBeenCalled();
	    
	  });