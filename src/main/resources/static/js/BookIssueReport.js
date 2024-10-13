var app = angular.module('BookIssueReport', []);

app.controller('BookIssueReportController', function($scope, $http) {
    
    $scope.duebooked = '';
	$scope.bookissueRecord = [];
	$scope.paginatedRecords = [];
	$scope.recordsPerPage = 14;
	$scope.currentPage = 1;
	$scope.pages = [];
	$scope.visiblePages = [];
	$scope.pageCount = 0;
	const maxVisiblePages = 5;
	
	
    $scope.goBack = function() {
        window.history.back();
    };

  
	
	
	$scope.findbookissuere = function() {
		var duebooked = $scope.duebooked;
		console.log("duebooked =" + duebooked);
		if (duebooked && duebooked !== "") {
			$http({
				method: 'POST',
				url: 'findbookissueDue',
				data: duebooked
			}).then(function(response) {
				
				if (Array.isArray(response.data)) {
					
					$scope.bookissueRecord = response.data;
						$scope.filterRecords();
				}else {
					swal.fire({
						title: 'No Data found! ',
						text: 'Thanks!!',
						icon: 'warning', // You can change this to 'warning', 'error', etc.
						confirmButtonText: 'OK',
					})
				}
				
			}, function(error) {
				console.error("Error occurred:", error);
			});
		} else {
			swal.fire({
				title: 'Please Select Option!!',
				text: 'Thanks!!',
				icon: 'warning',
				confirmButtonText: 'OK',
			});
		}
	};
	$scope.filterRecords = function() {
		const searchText = ($scope.searchText || '').toLowerCase();
		$scope.filteredRecords = $scope.bookissueRecord.filter(record =>
			record.selectStudent.toLowerCase().includes(searchText)
			
		);
		$scope.pageCount = Math.ceil($scope.filteredRecords.length / $scope.recordsPerPage);
		$scope.pages = Array.from({ length: $scope.pageCount }, (_, i) => i + 1);
		$scope.updateVisiblePages();
		$scope.goToPage(1);
	};

	$scope.updateVisiblePages = function() {
		const startPage = Math.max($scope.currentPage - Math.floor(maxVisiblePages / 2), 1);
		const endPage = Math.min(startPage + maxVisiblePages - 1, $scope.pageCount);
		$scope.visiblePages = [];
		for (let i = startPage; i <= endPage; i++) {
			$scope.visiblePages.push(i);
		}
	};

	$scope.goToPage = function(page) {
		if (page < 1 || page > $scope.pageCount) return;
		$scope.currentPage = page;
		const start = (page - 1) * $scope.recordsPerPage;
		const end = start + $scope.recordsPerPage;
		$scope.paginatedRecords = $scope.filteredRecords.slice(start, end);
		$scope.updateVisiblePages();
	};

	$scope.goToPreviousPage = function() {
		if ($scope.currentPage > 1) {
			$scope.goToPage($scope.currentPage - 1);
		}
	};

	$scope.goToNextPage = function() {
		if ($scope.currentPage < $scope.pageCount) {
			$scope.goToPage($scope.currentPage + 1);
		}
	};
	

	/*$scope.generatePDF = function() {
		const { jsPDF } = window.jspdf;
		const doc = new jsPDF();

		const columns = ["Roll Number", "Student Name", "Book Name", "Accession Number", "Due Book", "Fine", "Status"];
		const rows = $scope.bookissueRecord.map(record => [
			record.rollnumberid,
			record.selectStudent,
			record.selectbook,
			record.accessionNumber,
			record.duebook,
			record.fine,
			record.status
		]);

		
		
		autoTable(doc, {
			head: [columns],
			body: rows,
		});

		
		doc.save('BookIssueReport.pdf');
	};*/

    $scope.generatePDF = function() {
    var duebooked = $scope.duebooked;
    console.log("duebooked =" + duebooked);
    if (duebooked && duebooked !== "") {
        $http({
            method: 'POST',
            url: '/openpdf/employees',
            data: duebooked,
            responseType: 'arraybuffer'
        }).then(function(response) {
            var blob = new Blob([response.data], { type: 'application/pdf' });
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = 'BookIssueReport.pdf';
            link.click();
        }, function(error) {
            console.error("Error occurred:", error);
        });
    } else {
        swal.fire({
            title: 'Please Select Option!!',
            text: 'Thanks!!',
            icon: 'warning',
            confirmButtonText: 'OK',
        });
    }
};

});
