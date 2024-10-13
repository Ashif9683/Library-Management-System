var app = angular.module('RetrunbookWithoutAccession', ['ui.bootstrap']);

app.controller('RetrunBookController', ['$scope', '$http', '$uibModal', function($scope, $http, $uibModal) {
	
	$scope.withoutAccession = {};
	console.log(" page run ");
	
	
	$scope.withoutAsscessionFind = function() {
		var AccessionNumber = $scope.AccessionNUmber;
		console.log("AccessionNumber---  " + AccessionNumber);
		
		if (AccessionNumber && AccessionNumber !== "") {
			$http({
				method: 'POST', // or 'GET' depending on your requirement
				url: 'retrunBookWithoutAccession',
				headers: {
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({ AccessionNumber: AccessionNumber})
			}).then(function(response) {
				if (response.data && response.data !== "") {
					$scope.withoutAccession = response.data;

				
					document.getElementById('WithoutAccDisplay').style.display = 'block';
				} else {
					document.getElementById('WithoutAccDisplay').style.display = 'none';
					swal.fire({
						title: 'No Book Issue ! ',
						text: 'Thanks!!',
						icon: 'warning', // You can change this to 'warning', 'error', etc.
						confirmButtonText: 'OK',
					}).then((result) => {
						// Check if user clicked OK
						if (result.isConfirmed) {
							// Refresh the page
							window.location.reload();
						}
					});
				}

			}, function(error) {
				Swal.fire({
					title: 'Error!',
					text: 'There was an error processing your request.',
					icon: 'error',
					confirmButtonText: 'OK'
				});
			});
		} else {
			swal.fire({
				title: 'Please Enter Accession NUmber !! ',
				text: 'Thanks!!',
				icon: 'warning', // You can change this to 'warning', 'error', etc.
				confirmButtonText: 'OK',
			})
		}
	};
	
	$scope.returnbookOpen = function(primaryKey) {
		// Show the modal
		document.getElementById('openreturnmodal').style.display = 'block';
		var id = document.getElementById('primaryKeyValue').innerText = primaryKey;
		console.log("id  --" + id);
	}

}]);

function closeRetrunModal() {
	// Close the modal
	document.getElementById('openreturnmodal').style.display = 'none';
}

function returnBookIssueModal() {
	// Close the modal
	var primaryKeyValue = document.getElementById('primaryKeyValue').innerText;
	console.log("Primary Key: pk mdelete " + primaryKeyValue);
	$.ajax({
		url: '/returnbookbutton',
		method: 'POST',
		data: primaryKeyValue,
		processData: false,
		success: function(response, textStatus, jqXHR) {

			document.getElementById('openreturnmodal').style.display = 'none';
			if (response.trim() === 'Book Retruned Successfully') {
				swal.fire({
					title: response,
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