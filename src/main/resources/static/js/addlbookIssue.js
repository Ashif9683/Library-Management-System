
$(document).ready(function() {
	console.log("loaded add student .........");

	$('#savebookissue').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'bookissue',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				if (data.trim() === 'Book Issued Successfully') {
					swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'success', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
				}else{
				swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'warning', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
			
				}
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
		 document.getElementById('savebookissue').reset();
	});
});

window.onload = function() {
  fetchbooknames();
  fctchRollnumber();
};
function fetchbooknames() {

  $.ajax({
			url: 'fetchallBook',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="selectbook"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
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

function fetchsStudentsNames() {

  $.ajax({
			url: 'fetchallStudentName',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			 var selectElement = $('select[name="selectStudent"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
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

function fctchRollnumber() {

  $.ajax({
			url: 'fetchallStudentrollnumber',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			 var selectElement = $('select[name="rollnumberid"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
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



document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('rollnumberid').addEventListener('change', function() {
        var rollnumber = this.value;
        if (rollnumber !== '') {
            localStorage.setItem('selectedRollNumber', rollnumber);
            fetchStudentName(rollnumber);
        } else {
            localStorage.removeItem('selectedRollNumber');
            document.getElementById('selectStudent').value = ''; // Clear student name field if roll number is empty
        }
    });
});

function fetchStudentName(rollnumber) {
    $.ajax({
			url: '/fetchallStudentNamewithrollnumber',
			type: 'POST',
    		data:  rollnumber,
			success: function(data, textStatus, jqXHR) {
				 document.getElementById('selectStudent').value = data;
			},
			error: function(jqXHR, textStatus, errorThrown) {
			},
			processData: false
		});
}
