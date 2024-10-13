var app = angular.module('MigrateStudent', ['ui.bootstrap']);

app.controller('MigrateController', ['$scope', '$http', function($scope, $http) {
	$scope.studentSemesterRecords = [];
	$scope.paginatedRecords = [];
	$scope.selectedStudents = [];
	$scope.recordsPerPage = 14;
	$scope.currentPage = 1;
	$scope.pages = [];
	$scope.visiblePages = [];
	$scope.pageCount = 0;
	const maxVisiblePages = 5;

	$scope.toggleSelection = function(student) {
		var idx = $scope.selectedStudents.indexOf(student);
		if (idx > -1) {
			$scope.selectedStudents.splice(idx, 1);
		} else {
			$scope.selectedStudents.push(student);
		}
	};

	$scope.selectAll = function() {
		if ($scope.selectedStudents.length === $scope.studentSemesterRecords.length) {
			$scope.selectedStudents = [];
		} else {
			$scope.selectedStudents = $scope.studentSemesterRecords.slice(0);
		}
	};

	$scope.isSelected = function(student) {
		return $scope.selectedStudents.includes(student);
	};

	$scope.submitselectStudent = function() {
		if ($scope.selectedStudents.length > 0) {
			$http.post('/migrateSemester', $scope.selectedStudents)
				.then(function(response) {
					swal.fire({
						title: response.data.message,
						text: 'Thanks!!',
						icon: 'success',
						confirmButtonText: 'OK',
					}).then((result) => {
						if (result.isConfirmed) {
							window.location.reload();
						}
					});
				}, function(error) {
					console.error('Error:', error);
				});
		} else {
			swal.fire({
				title: 'No Student Selected',
				text: 'Thanks!!',
				icon: 'warning',
				confirmButtonText: 'OK',
			});
		}
	};

	$scope.submitMigreateSemester = function() {
		var migrateSeme = $scope.migrateSeme;
		if (migrateSeme && migrateSeme !== "") {
			$http({
				method: 'POST',
				url: 'migrateSemeesterStudent',
				data: migrateSeme
			}).then(function(response) {
				if (Array.isArray(response.data)) {
					$scope.studentSemesterRecords = response.data;
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
				title: 'Select Semester Migrate Student',
				text: 'Thanks!!',
				icon: 'warning',
				confirmButtonText: 'OK',
			});
		}
	};

	$scope.filterRecords = function() {
		const searchText = ($scope.searchText || '').toLowerCase();
		$scope.filteredRecords = $scope.studentSemesterRecords.filter(record =>
			record.fullName.toLowerCase().includes(searchText) ||
			record.rollnumber.toLowerCase().includes(searchText) ||
			record.fatherName.toLowerCase().includes(searchText)
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

	$scope.$watch('searchText', function() {
		$scope.filterRecords();
	});
}]);
