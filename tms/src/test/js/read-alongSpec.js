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