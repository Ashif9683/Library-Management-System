var app = angular.module('studentbookissue', ['ui.bootstrap']);  

app.controller('StudentController',['$scope', '$http' ,'$uibModal', function($scope, $http , $uibModal) {
   
	$scope.student = {};
	$scope.issues = [];
	$scope.returns = [];
	
	$scope.inputs = [{ value: '' }];
	
	$scope.StudentcheckRollNumber = function() {
    var rollNumber = $scope.rollNumber;
    if (rollNumber && rollNumber !== "") {
    console.log("rollNumber ---"+ rollNumber);
    $http({
      method: 'POST', // or 'GET' depending on your requirement
      url: 'StudentcheckRollNumber',
      data: rollNumber 
    }).then(function(response) {
     if (response.data && response.data !== "") {
               $scope.student = response.data;
            	 
            	 diaplaydataStudentissuesintable(response.data.rollnumber);
            	 diaplaydataStudentreturnintable(response.data.rollnumber);
           
                document.getElementById('StudentdataDisplay').style.display = 'block';
            } else {
                document.getElementById('StudentdataDisplay').style.display = 'none';
               swal.fire({    
            title: 'You Have Not to Access the Other Student Data ! ',
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
      // Error callback
      console.error("Error occurred:", error);
    });
    
    } else{
		swal.fire({    
            title: 'Select Roll Number ',
            text: 'Thanks!!',
            icon: 'warning', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        })
	}
  };
	
	
function diaplaydataStudentissuesintable(data) {
	$http({
      method: 'POST', 
      url: '/diaplaydataStudentissuesintable',
      data: data 
    }).then(function(response) {
	 $scope.issues = response.data;
    }, function(error) {
      console.error("Error occurred:", error);
    });
}

function diaplaydataStudentreturnintable(data) {
	$http({
      method: 'POST', 
      url: '/diaplaydataStudentreturnintable',
      data: data 
    }).then(function(response) {
	 $scope.returns = response.data;
    }, function(error) {
      console.error("Error occurred:", error);
    });
} 
}]);