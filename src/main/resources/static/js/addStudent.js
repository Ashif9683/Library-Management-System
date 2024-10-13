$(document).ready(function() {
	console.log("loaded add student .........");

	$('#myStudentForm').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'registerStudent',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				if (data.trim() === 'Student Added Successfully') {
					swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'success', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
				} else{
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
		 document.getElementById('myStudentForm').reset();
	});

	 
});


window.onload = function() {
  fetchallBranch();
 
};

function fetchallBranch() {

  $.ajax({
			url: 'fetchallBranch',
			type: 'POST',
			
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="selectBranch"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
            $.each(data, function(index, selectBranch) {
                selectElement.append('<option value="' + selectBranch + '">' + selectBranch + '</option>');
            });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
			
		});
}