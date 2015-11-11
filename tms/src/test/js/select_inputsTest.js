/**
 * 
 */

describe("select_inputsTest", function () {
	
	var selectBox="AudioBox";
	var selectedValue="sample1.mp3";
	var audiosrc="audiofiles/sample1.mp3";
	
	it("testing the selected audio files values are right or worng", function(){
		expect(selectBox).toMatch("Box");
		expect(selectedValue).toMatch("mp3");
		expect(audiosrc).toMatch("files");
		
	});
	
	/*it("testing the if selected wrong values", function(){
		
		expect(selectBox).toMatch("Box");
		expect(selectedValue).toMatch("html");
		expect(audiosrc).toMatch("text");
		
	});*/
	
	
});


describe("select_inputsTest", function () {
		it("testing the  text selected files values are right or worng", function(){
			var selectBox="textBox";
			var selectedValue="sample1.html";
			var passagesrc="textfiles/sample1.mp3";
			expect(selectBox).toMatch("Box");
			expect(selectedValue).toMatch("html");
			expect(passagesrc).toMatch("files");
			
		});
	});

describe("select_inputsTest", function () {
	
	it("Testing for loading main function for values ", function(){
		var check="true";
		expect(check).toBe("true");
			
	});
});




   