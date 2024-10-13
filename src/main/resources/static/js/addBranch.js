console.log("this is category ");


function openDeleteBranch(primaryKey) {
        // Show the modal
		document.getElementById('deleteBranchModal').style.display = 'block';      
        var id = document.getElementById('primaryKeyValue').innerText = primaryKey;   
    }
 function closeDeleteBranchModal() {
        // Close the modal
        document.getElementById('deleteBranchModal').style.display = 'none';
    }
    
    
function DeleteaddBranchModal() {
        // Close the modal
           var primaryKeyValuecate = document.getElementById('primaryKeyValue').innerText;
           console.log("Primary Key book pk mdelete " + primaryKeyValuecate);
		 $.ajax({
            url: '/deleteBranchPrimary',
            method: 'POST', 
        	data: primaryKeyValuecate,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deleteBranchModal').style.display = 'none';
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
    
function addBranchModel() {
        // Show the modal
document.getElementById('addBranchid').style.display = 'block'; 
}
    
    
function closeBranchModel() {
        // close the modal
		document.getElementById('addBranchid').style.display = 'none';             
}

$(document).ready(function() {
		console.log("loaded..update.......");
	
		$('#addBranchform').on('submit', function(event) {
			event.preventDefault();
	
			let form = new FormData(this);
		console.log(form)
			$.ajax({
				url: '/addedBranch',
				type: 'POST',
				data: form,
				success: function(data, textStatus, jqXHR) {
					console.log(data);
	
					if (data.trim() === 'done') {
						swal.fire({
				title: 'Added Branch ',
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
    
    