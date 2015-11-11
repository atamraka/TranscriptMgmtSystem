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