const serachUserFun = () =>{
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

function openDeleteUserModel(primaryKey) {
        // Show the modal
		document.getElementById('deleteUserModal').style.display = 'block';      
        var id = document.getElementById('primaryKeyValuebook').innerText = primaryKey;   
}

function closeDeleteUserModal() {
        // Close the modal
        document.getElementById('deleteUserModal').style.display = 'none';
}

 function closeeditUserModal() {
        // close the modal
		document.getElementById('editUserModal').style.display = 'none';      
}

 function openEditUserModel(primaryKeys) {
        // Show the modal
        console.log("open edit model" +primaryKeys);
		
		$.ajax({
            url: '/fetchUser',
            method: 'POST', 
        	data: primaryKeys,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record fetch successfully' + response);    
			//   document.getElementById('responseKeyValue').innerText = response;  
			displayData(response);
			   		document.getElementById('editUserModal').style.display = 'block'; 
 
        	},
	        error: function(xhr, status, error) {
	            // Handle error response    
        }
        });     
           
    }
    
    function displayData(data) {
	  document.getElementById("primarykey").value = data.primarykey;
	  document.getElementById("username").value = data.username;
	  document.getElementById("employeename").value = data.employeename;
	  document.getElementById("email").value = data.email;
	  document.getElementById("password").value = data.password;
	   document.getElementById("userrole").value = data.userrole;
	}

function DeleteUserModal() {
        // Close the modal
           var primaryKeyValuebook = document.getElementById('primaryKeyValuebook').innerText;
           console.log("Primary Key book pk mdelete " + primaryKeyValuebook);
		 $.ajax({
            url: '/deleteUserPrimary',
            method: 'POST', 
        	data: primaryKeyValuebook,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deleteUserModal').style.display = 'none';
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
    
$(document).ready(function() {
	console.log("loaded..update.......");

	$('#editUserModelPage').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'updateUserID',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);

				if (data.trim() === 'done') {
					swal.fire({
            title: 'Student Record Update ',
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