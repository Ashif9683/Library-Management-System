const serachFun = () =>{
	let filter = document.getElementById('search-input').value;
	let mytable = document.getElementById('data-table');
	let tr = mytable.getElementsByTagName('tr');
	for(var i=1; i<tr.length; i++) {
		let td = tr[i].getElementsByTagName('td')[0];
		if(td) {
			let textvalue = td.textContent || td.innerHTML;
			if(textvalue.indexOf(filter) > -1){
					tr[i].style.display = "";
			}else{
				tr[i].style.display = "none";
			}
		}
	}
}


 function openbookissueDelete(primaryKey) {
        // Show the modal
		document.getElementById('deletebookissueModal').style.display = 'block';      
        var id = document.getElementById('primaryKeyValue').innerText = primaryKey;   
    }
    
 function closeDeleteBookissueModal() {
        // Close the modal
        document.getElementById('deletebookissueModal').style.display = 'none';
    }
    
function DeleteBookIssueModal() {
        // Close the modal
           var primaryKeyValue = document.getElementById('primaryKeyValue').innerText;
           console.log("Primary Key: pk mdelete " + primaryKeyValue);
		 $.ajax({
            url: '/deletebookissuePrimary',
            method: 'POST', 
        	data: primaryKeyValue,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deletebookissueModal').style.display = 'none';
				if (response.trim() === 'Done') {
						swal.fire({
					            title: 'Delete record Successfully',
					            text: 'Thanks!!',
					            icon: 'success', // You can change this to 'warning', 'error', etc.
					            confirmButtonText: 'OK',
					        	}).then((result) => {
									        // Check if user clicked OK
									        if (result.isConfirmed) {
									            // Refresh the page
									            window.location.reload();
									        }
									});
            	}
        	},
	        error: function(xhr, status, error) {
	            // Handle error response
	          	swal.fire({
		            title: error,
		            text: 'Thanks!!',
		            icon: 'error', // You can change this to 'warning', 'error', etc.
		            confirmButtonText: 'OK'
			            // Display an error message or perform any other error handling actions
      	  		}); 
        }
        });
    }
    
    
    function openEditBookIssueModel(primaryKeys) {
        // Show the modal
        console.log("open edit model" +primaryKeys);
		
		$.ajax({
            url: '/fetchbookissue',
            method: 'POST', 
        	data: primaryKeys,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record fetch successfully---  ' + response);    
			//   document.getElementById('responseKeyValue').innerText = response;  
			displayData(response);
			   		document.getElementById('editBookissueModal').style.display = 'block'; 
 
        	},
	        error: function(xhr, status, error) {
	            // Handle error response    
        }
        });     
           
    }
    
    function displayData(data) {
	  document.getElementById("primaryKey").value = data.primaryKey;
	  document.getElementById("rollnumberid").value = data.rollnumberid;
	  document.getElementById("selectbook").value = data.selectbook;
	  document.getElementById("selectStudent").value = data.selectStudent;
	  document.getElementById("bookQuantityid").value = data.bookQuantityid;
	  document.getElementById("status").value = data.status;
	  document.getElementById("fine").value = data.fine;
	  document.getElementById("issueBookDate").value = data.issueBookDate;
	  document.getElementById("returnDate").value = data.returnDate;
	}
    
    function closeeditBookissueModal() {
        // close the modal
		document.getElementById('editBookissueModal').style.display = 'none';      
           
    }
    
	$(document).ready(function() {
		console.log("loaded..update.......");
	
		$('#editbookissueform').on('submit', function(event) {
			event.preventDefault();
	
			let form = new FormData(this);
		console.log(form)
			$.ajax({
				url: 'updatebookissueID',
				type: 'POST',
				data: form,
				success: function(data, textStatus, jqXHR) {
					console.log(data);
	
					if (data.trim() === 'done') {
						swal.fire({
				title: 'Book Issue Return ',
				text: 'Thanks!!',
				icon: 'success', // You can change this to 'warning', 'error', etc.
			   confirmButtonText: 'OK',
									}).then((result) => {
												// Check if user clicked OK
												if (result.isConfirmed) {
													// Refresh the page
													window.location.reload();
												}
										});
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(jqXHR);
					swal.fire({
				title: 'Something went worng!!',
				text: 'Thanks!!',
				icon: 'error', // You can change this to 'warning', 'error', etc.
				confirmButtonText: 'OK',
			});
				},
				processData: false,
				contentType: false
				
			});
			
		});
	
		 
	});