const recordsPerPage = 14;
let currentPage = 1;
let studentRecords = [];
 let filteredRecords = [];

window.onload = function() {
   fetchallStudentRecord();
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('input', searchTable);
    } else {
        console.error('Element with ID "searchInput" not found.');
    }
};

function fetchallStudentRecord(){
	$.ajax({
			url: 'fetchallStudentRecord',
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				 studentRecords = data;
				 filteredRecords = data;
                 renderTable(filteredRecords);
                 renderPagination();	
			; 
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
		});
}
function renderTable(records) {
            const tbody = document.getElementById('studentRecords');
            tbody.innerHTML = '';
            const start = (currentPage - 1) * recordsPerPage;
            const end = start + recordsPerPage;
            const paginatedRecords = records.slice(start, end);
			const userRole = document.body.getAttribute('data-user-role'); 
            paginatedRecords.forEach(sturecord => {
                const row = document.createElement('tr');
                row.innerHTML = `
		             <td>	
    					<a href="StudentPerRecord.html?rollnumber=${sturecord.rollnumber}">${sturecord.rollnumber}</a>
					</td>
                    <td>${sturecord.fullName}</td>
                    <td>${sturecord.fatherName}</td>
                    <td>${sturecord.email}</td>
                    <td>${sturecord.selectBranch}</td>
                     <td>
                        ${userRole === 'ADMIN' ? `
                            <button onclick="openEditModel(${sturecord.primaryKey})" class="btn btn-primary btn-sm">Edit</button>
                            <button onclick="OpenRemark(${sturecord.primaryKey})" class="btn btn-primary btn-sm">Remark</button>
                            <button onclick="openDelete(${sturecord.primaryKey})" class="btn btn-danger btn-sm">Delete</button>
                        ` : ''}
                    </td>
                `;
                tbody.appendChild(row);
            });
        }

function renderPagination() {
            const pageCount = Math.ceil(filteredRecords.length / recordsPerPage);
            const pagination = document.getElementById('pagination');
            pagination.innerHTML = '';
            const prevLi = document.createElement('li');
            prevLi.className = 'page-item';
            prevLi.innerHTML = `<a class="page-link" href="#">Previous</a>`;
            prevLi.onclick = () => {
                if (currentPage > 1) {
                    currentPage--;
                    renderTable(filteredRecords);
                    renderPagination();
                }
            };
            pagination.appendChild(prevLi);
           const maxPagesToShow = 5; // Number of pages to show at once
	    let startPage = Math.max(currentPage - Math.floor(maxPagesToShow / 2), 1);
	    let endPage = startPage + maxPagesToShow - 1;
	
	    if (endPage > pageCount) {
	        endPage = pageCount;
	        startPage = Math.max(endPage - maxPagesToShow + 1, 1);
	    }

    for (let i = startPage; i <= endPage; i++) {
        const li = document.createElement('li');
        li.className = 'page-item ' + (i === currentPage ? 'active' : '');
        li.innerHTML = `<a class="page-link" href="#">${i}</a>`;
        li.onclick = () => {
            currentPage = i;
            renderTable(filteredRecords);
            renderPagination();
        };
        pagination.appendChild(li);
    }
            const nextLi = document.createElement('li');
            nextLi.className = 'page-item';
            nextLi.innerHTML = `<a class="page-link" href="#">Next</a>`;
            nextLi.onclick = () => {
                if (currentPage < pageCount) {
                    currentPage++;
                    renderTable(filteredRecords);
                    renderPagination();
                }
            };
            pagination.appendChild(nextLi);
}
function searchTable() {
    const searchText = document.getElementById('searchInput').value.toLowerCase();
    const filteredRecords = studentRecords.filter(record =>
          record.fullName.toLowerCase().includes(searchText) || record.rollnumber.toLowerCase().includes(searchText)  
          || record.fatherName.toLowerCase().includes(searchText)
    );
    currentPage = 1; 
    renderTable(filteredRecords);
    renderPagination();
}

function openDelete(primaryKey) {
               
         var deleteModal = document.getElementById('deleteModal');
         if (deleteModal) {
                deleteModal.style.display = 'block';
         } else {
              console.error('Element with ID "deleteModal" not found.');
         }
         var primaryKeyValue = document.getElementById('primaryKeyValue');
         if (primaryKeyValue) {
                primaryKeyValue.innerText = primaryKey;
         } else {
                console.error('Element with ID "primaryKeyValue" not found.');
         }
}

function OpenRemark(primaryKey) {
               
         var OpenRemarkModal = document.getElementById('RemarkOpenModel');
         if (OpenRemarkModal) {
                OpenRemarkModal.style.display = 'block';
         } else {
              console.error('Element with ID "RemarkOpenModel" not found.');
         }
         var primaryKeyValue = document.getElementById('primaryKeyValue');
         console.log("primaryKeyValue --   147--    " + primaryKeyValue);
         if (primaryKeyValue) {
                primaryKeyValue.innerText = primaryKey;
                         console.log("primaryKeyValue.innerText = primaryKey;--    " + primaryKeyValue.innerText);

         } else {
                console.error('Element with ID "primaryKeyValue" not found.');
         }
}
function closeRemarkModal() {
        // Close the modal
 		document.getElementById('RemarkOpenModel').style.display = 'none';
}

function openEditModel(primaryKeys) {
    $.ajax({
            url: '/fetchStudent',
            method: 'POST', 
        	data: primaryKeys,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            displayData(response);
			document.getElementById('editModal').style.display = 'block'; 
        	},
	        error: function(xhr, status, error) {   
        }
   });          
}
 
 function addremark() {
        // Close the modal
           var primaryKeyValue = document.getElementById('primaryKeyValue').innerText;
           var noDuessValue = document.getElementById('noDuess').value;
		 $.ajax({
            url: '/addRemark',
            method: 'POST', 
        	data: JSON.stringify({ primaryKey: primaryKeyValue, noDuess: noDuessValue }),
        	contentType: 'application/json',
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record Add successfully' + response);    
			    document.getElementById('RemarkOpenModel').style.display = 'none';
				if (response.trim() === 'Added Remark') {
						swal.fire({
					            title: 'Added Remark',
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
 
    
function displayData(data) {
	  document.getElementById("primaryKey").value = data.primaryKey;
	  document.getElementById("rollnumber").value = data.rollnumber;
	  document.getElementById("fullName").value = data.fullName;
	  document.getElementById("email").value = data.email;
	  document.getElementById("fatherName").value = data.fatherName;
	  document.getElementById("phone").value = data.phone;
	  document.getElementById("selectSemester").value = data.selectSemester;
	  document.getElementById("selectYear").value = data.selectYear;
	  document.getElementById("bookBank").value = data.bookBank;
}
    
     function closeeditModal() {
        // close the modal
		document.getElementById('editModal').style.display = 'none';          
    }
    
function closeDeleteModal() {
        // Close the modal
 		document.getElementById('deleteModal').style.display = 'none';
}
    
     function DeleteModal() {
        // Close the modal
           var primaryKeyValue = document.getElementById('primaryKeyValue').innerText;
           console.log("Primary Key: pk mdelete " + primaryKeyValue);
		 $.ajax({
            url: '/deletePrimary',
            method: 'POST', 
        	data: primaryKeyValue,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deleteModal').style.display = 'none';
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
	$('#editform').on('submit', function(event) {
		event.preventDefault();
		let form = new FormData(this);
		$.ajax({
			url: 'updateStudentID',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
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