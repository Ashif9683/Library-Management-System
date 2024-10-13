  var student = {};
const recordsPerPage = 14;
let currentPage = 1;
let bookRecords = [];
 let filteredRecords = [];

window.onload = function() {
   fetchallbookRecord();
};

/*document.getElementById('searchbookInput').addEventListener('input', searchTable); */



function fetchallbookRecord(){
	 $(".loader-div").show(); // show loader
	$.ajax({
			url: 'fetchallBookRecord',
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				 bookRecords = data;
				 filteredRecords = data;
                 renderTable(filteredRecords);
                 renderPagination();	
			; 
			$(".loader-div").hide(); // hide loader
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
		});
}
function renderTable(records) {
            const tbody = document.getElementById('bookRecords');
            tbody.innerHTML = '';
            const start = (currentPage - 1) * recordsPerPage;
            const end = start + recordsPerPage;
            const paginatedRecords = records.slice(start, end);
			const userRole = document.body.getAttribute('data-user-role'); 
            paginatedRecords.forEach(borecord => {
                const row = document.createElement('tr');
                row.innerHTML = `
					<td>	
						<a href="BookPerRecord.html?primaryKey=${borecord.primaryKey}">${borecord.booktitle}</a>
					</td>
                    <td>${borecord.authorName}</td>
                    <td>${borecord.bookCategory}</td>
                    <td>${borecord.bookquantity}</td>
                     <td>
                        ${userRole === 'ADMIN' ? `
                            <button onclick="openEditBookModel(${borecord.primaryKey})" class="btn btn-primary btn-sm">Edit</button>
                            <button onclick="openDeleteBookModel(${borecord.primaryKey})" class="btn btn-danger btn-sm">Delete</button>
                        ` : ''}
                            <button onclick="openbookAccessionDetail(${borecord.primaryKey})" class="btn btn-secondary btn-sm">Accession Detail</button>
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
function searchbookTable() {
    const searchText = document.getElementById('searchbookInput').value.toLowerCase();
    const filteredRecords = bookRecords.filter(record =>
          record.booktitle.toLowerCase().includes(searchText) || record.authorName.toLowerCase().includes(searchText)  
          || record.bookCategory.toLowerCase().includes(searchText)
    );
    currentPage = 1; 
    renderTable(filteredRecords);
    renderPagination();
}
function openDeleteBookModel(primaryKey) {
		document.getElementById('deleteBookModal').style.display = 'block';      
        var id = document.getElementById('primaryKeyValuebook').innerText = primaryKey;   
    }
    
    function openEditBookModel(primaryKeys) {		
		$.ajax({
            url: '/fetchBook',
            method: 'POST', 
        	data: primaryKeys,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
			displayData(response);
			   		document.getElementById('editBookModal').style.display = 'block'; 
 
        	},
	        error: function(xhr, status, error) {
	            // Handle error response    
        }
        });     
           
    }

    
    function openbookAccessionDetail(primaryKeys) {
        // Show the modal
        
		$.ajax({
            url: '/fetchbookAccessiondetails',
            method: 'POST', 
        	data: primaryKeys,
        	processData: false,
       		success: function(data,textStatus, jqXHR) {
            	console.log(data);
				 const tbody = document.getElementById('recordbookdetail');
      				tbody.innerHTML = '';

      // Populate table with fetched records
      data.forEach(record => {
        const row = document.createElement('tr');
        row.innerHTML = `
        	 <td>${record.booknameid}</td>
          <td>${record.accessionNumber}</td>
          <td>${record.createdAt}</td>
        `;
        tbody.appendChild(row);
      });
			document.getElementById('opendetailbookModal').style.display = 'block'; 
        	},
	        error: function(xhr, status, error) {
	            // Handle error response    
        }
        });    
    }
    
    function displayData(data) {
	  document.getElementById("primaryKey").value = data.primaryKey;
	  document.getElementById("booktitle").value = data.booktitle;
	  document.getElementById("authorName").value = data.authorName;
	  document.getElementById("isbnNumber").value = data.isbnNumber;
	   document.getElementById("bookquantity").value = data.bookquantity;
	  document.getElementById("publicationYear").value = data.publicationYear;
	}
    
     function closeeditBookModal() {
        // close the modal
		document.getElementById('editBookModal').style.display = 'none';      
    }
 
    function closebookdetailModal() {
        // close the modal
		document.getElementById('opendetailbookModal').style.display = 'none';       
    }
    
    function closedetailModal() {
        // close the modal
		document.getElementById('opendetailModal').style.display = 'none';       
    }
    function closeDeleteBookModal() {
        // Close the modal
        document.getElementById('deleteBookModal').style.display = 'none';
    }
    
     function DeleteBookModal() {
        // Close the modal
           var primaryKeyValuebook = document.getElementById('primaryKeyValuebook').innerText;
           console.log("Primary Key book pk mdelete " + primaryKeyValuebook);
		 $.ajax({
            url: '/deletebookPrimary',
            method: 'POST', 
        	data: primaryKeyValuebook,
        	processData: false,
       		success: function(response,textStatus, jqXHR) {
            	
            	console.log('Record deleted successfully' + response);    
			    document.getElementById('deleteBookModal').style.display = 'none';
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

	$('#editBookModelPage').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'updateBookID',
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

function fetchcategoryname() {

  $.ajax({
			url: 'fetchallcategory',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="selectcategory"]');
            selectElement.empty();
            selectElement.append('<option value="">Select Category Name..</option>');
            $.each(data, function(index, selectcategory) {
                selectElement.append('<option value="' + selectcategory + '">' + selectcategory + '</option>');
            });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
			
		});
}
function fetchbooknames() {

  $.ajax({
			url: 'fetchallBook',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="selectbook"]');
            selectElement.empty();
            selectElement.append('<option value="">Select Book Name..</option>');
            $.each(data, function(index, bookName) {
                selectElement.append('<option value="' + bookName + '">' + bookName + '</option>');
            });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
			
		});
}

$(document).ready(function() {
	

	$('#myfilterForm').on('submit', function(event) {
		event.preventDefault();
	
		let form = new FormData(this);
	console.log(form)
	
		$.ajax({
			url: '/addfilterform',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				 const tbody = document.getElementById('recordsBody');
      				tbody.innerHTML = '';

      // Populate table with fetched records
      data.forEach(record => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>
          	<button onclick="openbookDetail(${record.primaryKey})" class="btn btn-secondary btn-sm" ><span th:text="${record.booktitle}"></span></button>
         </td>
          <td>${record.publicationYear}</td>
          <td>${record.authorName}</td>
          <td>${record.isbnNumber}</td>
          <td>${record.bookquantity}</td>
          <td>${record.seriesNo}</td>
           <td>${record.createdAt}</td>
          <td>
	        <button onclick="openEditBookModel(${record.primaryKey})" class="btn btn-primary btn-sm" >Edit</button>
	        <button onclick="openbookAccessionDetail(${record.primaryKey})" class="btn btn-secondary btn-sm">Detail</button>
	        <button onclick="openDeleteBookModel(${record.primaryKey})" class="btn btn-danger btn-sm">Delete</button>
      	</td>
        `;
        tbody.appendChild(row);
       
      });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				
				console.log(jqXHR);
				swal.fire({
            title: data,
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