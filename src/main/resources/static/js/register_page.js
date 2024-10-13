$(document).ready(function() {
	console.log("loaded.........");

	$('#myRegisterForm').on('submit', function(event) {
		event.preventDefault();

		let form = new FormData(this);
	console.log(form)
		$.ajax({
			url: 'registerPage',
			type: 'POST',
			data: form,
			success: function(data, textStatus, jqXHR) {
				console.log(data);

				if (data.trim() === 'Registered Successfully') {
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
		 document.getElementById('myRegisterForm').reset();
	});

	 
});