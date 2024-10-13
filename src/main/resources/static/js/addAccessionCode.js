console.log("this is category ");


function openDeletemodelAccession(primaryKey) {
        // Show the modal
		document.getElementById('deleteAccessionModal').style.display = 'block';      
        var id = document.getElementById('primaryKeyValue').innerText = primaryKey;   
    }
 function closeDeleteAccessionoModal() {
        // Close the modal
        document.getElementById('deleteAccessionModal').style.display = 'none';
    }
    
    
function DeleteAccessionModal() {
        // Close the modal
           var primaryKeyValueAcc = document.getElementById('primaryKeyValue').innerText;
           console.log("Primary Key book pk mdelete " + primaryKeyValueAcc);
		 $.ajax({
            url: '/deleteAccessionPrimaryKey',
            method: 'POST', 
        	data: primaryKeyValueAcc,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deleteAccessionModal').style.display = 'none';
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
    
function addAccessionCo() {
        // Show the modal
document.getElementById('addaccessionID').style.display = 'block'; 
}
    
    
function closeAccessionModal() {
        // close the modal
		document.getElementById('addaccessionID').style.display = 'none';             
}

$(document).ready(function() {
		console.log("loaded..update.......");
	
		$('#addAccessionform').on('submit', function(event) {
			event.preventDefault();
	
			let form = new FormData(this);
		console.log(form)
			$.ajax({
				url: '/addedAccessioncategory',
				type: 'POST',
				data: form,
				success: function(data, textStatus, jqXHR) {
					console.log(data);
	
					if (data.trim() === 'done') {
						swal.fire({
				title: 'Added Category ',
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
    
    