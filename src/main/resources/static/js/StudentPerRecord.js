
	      // Function to get query parameters from URL
	      function getQueryParam(param) {
	          const urlParams = new URLSearchParams(window.location.search);
	          return urlParams.get(param);
	      }

	      // Function to display the roll number on the page
	      function displayRollNumber() {
	          const rollnumber = getQueryParam('rollnumber');
	          console.log("rollnumber " + rollnumber);         
	              $.ajax({
			            url: '/rollnumberfindObject',
			            method: 'POST', 
			        	data: rollnumber,
			        	processData: false,
			       		success: function(response,textStatus, jqXHR) {
			            	
			            	console.log('Record deleted successfully' + response.rollnumber);   
			            	   displayData(response);
			            	 
        			},
	        	  error: function(xhr, status, error) {
	            // Handle error response
	          	
        		}
        });	          
	   }
			
		function displayData(data) {
	  document.getElementById('rollnumber').textContent = data.rollnumber;
	  document.getElementById('fullName').textContent = data.fullName;
	  document.getElementById('fatherName').textContent = data.fatherName;
	  document.getElementById('email').textContent = data.email;
	  document.getElementById('phone').textContent = data.phone;
	  document.getElementById('selectSemester').textContent = data.selectSemester;
	  document.getElementById('selectYear').textContent = data.selectYear;
	  document.getElementById('selectBranch').textContent = data.selectBranch;
	  document.getElementById('bookBank').textContent = data.bookBank;
	  document.getElementById('bookAnnually').textContent = data.bookAnnually;
	  document.getElementById('noDuess').textContent = data.noDuess;
	  document.getElementById('createdAt').textContent = data.createdAt;
	  document.getElementById('modifiedOn').textContent = data.modifiedOn;

	 
	 
}
		
			
	      // Function to navigate back to the previous page
	      function goBack() {
	          window.history.back();
	      }

	      // Execute the function after the page has loaded
	      window.onload = displayRollNumber;
	