
	      // Function to get query parameters from URL
	      function getQueryParam(param) {
	          const urlParams = new URLSearchParams(window.location.search);
	          return urlParams.get(param);
	      }

	      // Function to display the roll number on the page
	      function displayBooktitle() {
	          const primaryKey = getQueryParam('primaryKey');
	          console.log("primaryKey " + primaryKey);         
	              $.ajax({
			            url: '/fetchbookdetails',
			            method: 'POST', 
			        	data: primaryKey,
			        	processData: false,
			       		success: function(response,textStatus, jqXHR) {
			            	
			            	console.log('Record deleted successfully' + response.libraryName);   
			            	   displayData(response);
			            	 
        			},
	        	  error: function(xhr, status, error) {
	            // Handle error response
	          	
        		}
        });	          
	   }
			
		function displayData(data) {
			  document.getElementById('primaryKey').textContent = data.primaryKey;
			  document.getElementById('libraryName').textContent = data.libraryName;
			  document.getElementById('isbnNumber').textContent = data.isbnNumber;
			  document.getElementById('libraryItemType').textContent = data.libraryItemType;
			  document.getElementById('booktitle').textContent = data.booktitle;
			  document.getElementById('bookAddedIn').textContent = data.bookAddedIn;
			  document.getElementById('bookCategory').textContent = data.bookCategory;
			  document.getElementById('bookquantity').textContent = data.bookquantity;
			  document.getElementById('itemStatus').textContent = data.itemStatus;
			  document.getElementById('subjectTitle').textContent = data.subjectTitle;
			  document.getElementById('languageName').textContent = data.languageName;
			  document.getElementById('authorName').textContent = data.authorName;
			  document.getElementById('authorName2').textContent = data.authorName2;
			  document.getElementById('authorName3').textContent = data.authorName3;
			  document.getElementById('authorName4').textContent = data.authorName4;
			  document.getElementById('authorName5').textContent = data.authorName5;
			  document.getElementById('authorMark').textContent = data.authorMark;
			  document.getElementById('volume').textContent = data.volume;     
			  document.getElementById('callNo').textContent = data.callNo;
			  document.getElementById('edition').textContent = data.edition;
			  document.getElementById('seriesNo').textContent = data.seriesNo;
			  document.getElementById('seriesTitle').textContent = data.seriesTitle;
			  document.getElementById('discountInPercantage').textContent = data.discountInPercantage;
			  document.getElementById('bookPublisherName').textContent = data.bookPublisherName;
			  document.getElementById('bookPublisherPlace').textContent = data.bookPublisherPlace;
			  document.getElementById('vendorName').textContent = data.vendorName;
			  document.getElementById('vendorPlace').textContent = data.vendorPlace;
			  document.getElementById('publicationYear').textContent = data.publicationYear;
			  document.getElementById('billNumber').textContent = data.billNumber;
			  document.getElementById('billDate').textContent = data.billDate;
			  document.getElementById('orderNumber').textContent = data.orderNumber;
			  document.getElementById('orderDate').textContent = data.orderDate;
			  document.getElementById('costOfItem').textContent = data.costOfItem;
			  document.getElementById('currentStatus').textContent = data.currentStatus;
			  document.getElementById('currencyName').textContent = data.currencyName;
			  document.getElementById('discountAmount').textContent = data.discountAmount;
			  document.getElementById('rackNumber').textContent = data.rackNumber;
			  document.getElementById('shelfNumber').textContent = data.shelfNumber;
			  document.getElementById('pagesNumber').textContent = data.pagesNumber;
			  document.getElementById('prePages').textContent = data.prePages;
			  document.getElementById('size').textContent = data.size;
			  document.getElementById('typeOfBinding').textContent = data.typeOfBinding;
			  document.getElementById('keywords').textContent = data.keywords;
			  document.getElementById('publicationDate').textContent = data.publicationDate;
			  document.getElementById('location').textContent = data.location;
			  document.getElementById('editor').textContent = data.editor;
			  document.getElementById('compiler').textContent = data.compiler;
			  document.getElementById('translator').textContent = data.translator;
			  document.getElementById('abstracts').textContent = data.abstracts;
			  document.getElementById('subTitle').textContent = data.subTitle;
			  document.getElementById('userName').textContent = data.userName;
			  document.getElementById('employeeName').textContent = data.employeeName;
			  document.getElementById('entryDate').textContent = data.entryDate;
			  document.getElementById('verified').textContent = data.verified;
			  document.getElementById('createdAt').textContent = data.createdAt;
			  document.getElementById('userName').textContent = data.userName;
			  	
}
		
			
	      // Function to navigate back to the previous page
	      function goBack() {
	          window.history.back();
	      }

	      // Execute the function after the page has loaded
	      window.onload = displayBooktitle;
	