$(document).ready(function() {
	

	$('#myAddBookForm').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'addBook',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);

				if (data.trim() === 'Book Added Successfully') {
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
		 document.getElementById('myAddBookForm').reset();
	});

	 
});

window.onload = function() {
  fetchcategoryname();
  fetchAccessionCode();
};

function fetchcategoryname() {
  $.ajax({
			url: 'fetchallcategory',
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="bookCategory"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
            $.each(data, function(index, bookCategory) {
                selectElement.append('<option value="' + bookCategory + '">' + bookCategory + '</option>');
            });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
		});
}

function fetchAccessionCode() {
  $.ajax({
			url: 'fetchallAccessionCode',
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				console.log(data);
			  var selectElement = $('select[name="bookAccessionCode"]');
            selectElement.empty();
            selectElement.append('<option value="">Please select</option>');
            $.each(data, function(index, bookAccessionCode) {
                selectElement.append('<option value="' + bookAccessionCode + '">' + bookAccessionCode + '</option>');
            });
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
		});
}



