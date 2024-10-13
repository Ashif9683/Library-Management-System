var app = angular.module('myAppbook', ['ui.bootstrap']);

app.controller('MainController', ['$scope', '$http', '$uibModal', function($scope, $http, $uibModal) {
	$scope.options = [];
	$scope.selectedOptions = [];
	$scope.responseMessage = '';
	$scope.student = {};
	$scope.book = {};
	$scope.issues = [];
	$scope.returns = [];
	 $scope.students = [];  // Array to hold student data
	$scope.inputs = [{ value: '' }];

	$scope.addField = function() {
		$scope.inputs.push({ value: '' });
	};

	$scope.removeField = function(index) {
		$scope.inputs.splice(index, 1);
	};
	$scope.searchTerm ='';
	 $scope.selectedItem = "";
    $scope.dropdownOpen = false;
  $scope.openDropdown = function() {
        $scope.dropdownOpen = true;
    };

    $scope.closeDropdown = function() {
        setTimeout(function() {
            $scope.dropdownOpen = false;
            $scope.$apply();
        }, 200);
    };

    $scope.toggleDropdown = function() {
        $scope.dropdownOpen = !$scope.dropdownOpen;
    };

    $scope.selectItem = function(item) {
        $scope.selectedItem = item;
        $scope.searchTerm = item; // Display selected item in search field
        $scope.dropdownOpen = false;
    };
	    $scope.loadStudents = function() {
			$http({
				method: 'GET', // or 'GET' depending on your requirement
				url: 'fetchfullName'
			}).then(function(response) {
				console.log("resopnese" + response.data);
				 $scope.students = response.data;
        }, function(error) {
            console.error("Error occurred while fetching students:", error);
        });
    };
	  $scope.onStudentSelected = function(selectedStudent) {
		                        console.log('Selected Student:', selectedStudent);

        $scope.studentName = selectedStudent.fullName;

    };
    
    	 $scope.loadStudents();

    
	$scope.bookrecord = function(accessionNumber) {
		console.log("accession ----  " + accessionNumber);
		if (accessionNumber && accessionNumber !== "") {
			console.log("accession   if  ---" + accessionNumber);
			$http({
				method: 'POST', // or 'GET' depending on your requirement
				url: 'accessionFetchBook',
				data: accessionNumber
			}).then(function(response) {
				if (response.data && response.data !== "") {
					$scope.book = response.data;
					document.getElementById('databookDisplay').style.display = 'block';
				} else {
					document.getElementById('databookDisplay').style.display = 'none';
					swal.fire({
						title: 'No Book found! ',
						text: 'Thanks!!',
						icon: 'warning', // You can change this to 'warning', 'error', etc.
						confirmButtonText: 'OK',
					})
				}
			}, function(error) {
				// Error callback
				console.error("Error occurred:", error);
			});

		} else {
			swal.fire({
				title: 'Please Enter Asscession Number !! ',
				text: 'Thanks!!',
				icon: 'warning', // You can change this to 'warning', 'error', etc.
				confirmButtonText: 'OK',
			})
		}
	};



	$scope.submitRollNumber = function() {
		var rollNumber = $scope.rollNumber;
		var studentName = $scope.selectedItem;
		var mobileNu = $scope.mobileNu;

		if (rollNumber && rollNumber !== "" || (studentName && studentName !== "" && mobileNu && mobileNu !== "")) {
			$http({
				method: 'POST',
				url: 'issue',
				headers: {
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({ rollNumber: rollNumber, studentName: studentName, mobileNu: mobileNu })
			}).then(function(response) {
				if (response.data && response.data !== "") {
					$scope.student = response.data;

					diaplaydataissuesintable(response.data.rollnumber);
					diaplaydatareturnintable(response.data.rollnumber);
					/* finecharges(response.data.rollnumber);*/
					document.getElementById('dataDisplay').style.display = 'block';
				} else {
					document.getElementById('dataDisplay').style.display = 'none';
					swal.fire({
						title: 'No data found! ',
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
				title: 'Please Enter At Least One Field !! ',
				text: 'Thanks!!',
				icon: 'warning', // You can change this to 'warning', 'error', etc.
				confirmButtonText: 'OK',
			})
		}
	};

	$scope.saveData = function() {

		var accessionNumber = $scope.accessionValue;
		var SelectBookBank = $scope.SelectBookBankValue;
		
		$http({
			method: 'POST',
			url: '/optionbookselecet',
			data: JSON.stringify({ student: $scope.student, accessionNumber: accessionNumber, SelectBookBank: SelectBookBank })
		}).then(function(response) {
			if (response.data.message == 'Book Issued Successfully') {
				swal.fire({
					title: response.data.message,
					text: 'Thanks!!',
					icon: 'success', // You can change this to 'warning', 'error', etc.
					confirmButtonText: 'OK',
				});
			} else {
				swal.fire({
					title: response.data.message,
					text: 'Thanks!!',
					icon: 'warning', // You can change this to 'warning', 'error', etc.
					confirmButtonText: 'OK',
				});

			}
		}, function(error) {
			console.log('Error occurred:', error.data);
			swal.fire({
				title: error.data,
				text: 'Thanks!!',
				icon: 'error', // You can change this to 'warning', 'error', etc.
				confirmButtonText: 'OK',
			}).then((result) => {
				if (result.isConfirmed) {
					// Refresh the page
					window.location.reload();
				}
			});
		});
	};

	function displaySemesterData(data) {
		$http({
			method: 'POST',
			url: '/fetchsemesterbook',
			data: data
		}).then(function(response) {
			$scope.options = response.data;
		}, function(error) {
			console.error("Error occurred:", error);
		});
	}

	function diaplaydataissuesintable(data) {
		$http({
			method: 'POST',
			url: '/displayintableissue',
			data: data
		}).then(function(response) {
			$scope.issues = response.data;
		}, function(error) {
			console.error("Error occurred:", error);
		});
	}

	function diaplaydatareturnintable(data) {
		$http({
			method: 'POST',
			url: '/displayintableReturn',
			data: data
		}).then(function(response) {
			$scope.returns = response.data;
		}, function(error) {
			console.error("Error occurred:", error);
		});
	}

	/*function finecharges(data){
		$http({
		  method: 'POST', 
		  url: '/displayfinechsxscvfb',
		  data: data 
		}).then(function(response) {
		 $scope.returns = response.data;
		}, function(error) {
		  console.error("Error occurred:", error);
		});
	}
	*/


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





