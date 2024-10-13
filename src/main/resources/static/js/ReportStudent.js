

var app = angular.module('ReportStudent', []);

app.controller('ReportStudentController', function($scope, $http) {
    $scope.branches = [];
    $scope.selectedBranch = '';
	$scope.studentRecord = [];
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

    $scope.fetchallBranch = function() {
        $http.post('fetchallBranch', {})
            .then(function(response) {
                $scope.branches = response.data;
            }, function(error) {
                console.error(error);
            });
    };
	
	
	$scope.findrecordbranch = function() {
		var selectedBranch = $scope.selectedBranch;
		console.log("selectedBranch =" + selectedBranch);
		if (selectedBranch && selectedBranch !== "") {
			$http({
				method: 'POST',
				url: 'findBranchStudent',
				data: selectedBranch
			}).then(function(response) {
				
				if (Array.isArray(response.data)) {
					
					$scope.studentRecord = response.data;
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
				title: 'Select  Department Student!!',
				text: 'Thanks!!',
				icon: 'warning',
				confirmButtonText: 'OK',
			});
		}
	};
	$scope.filterRecords = function() {
		const searchText = ($scope.searchText || '').toLowerCase();
		$scope.filteredRecords = $scope.studentRecord.filter(record =>
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
	
$scope.downexcelreport = function() {
    var ws_data = [];

    // Dynamic headline based on selected branch
    var selectedBranch = $scope.selectedBranch;
    var headline = 'Nitra Library Management Student Report';
    if (selectedBranch) {
        headline += ' - ' + selectedBranch; // Append selected branch if available
    }
    ws_data.push([headline]);

    // Adding headers
    const headers = ['Serial No.', 'Roll Number', 'Student Name', 'Father Name', 'Email', 'Student Branch', 'Student Year', 'Student Semester', 'Contact No'];
    ws_data.push(headers);

    // Adding data
    $scope.studentRecord.forEach(function(record , index) {
        ws_data.push([
            index + 1,
            record.rollnumber,
            record.fullName,
            record.fatherName,
            record.email,
            record.selectBranch,
            record.selectYear,
            record.selectSemester,
            record.phone
        ]);
    });

    // Creating a new workbook
    var ws = XLSX.utils.aoa_to_sheet(ws_data);
    var wb = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, "Student Report");

    // Styling the headline (first row)
    ws['A1'].s = {
        font: {
            name: 'Arial',
            sz: 14,
            bold: true,
            color: { rgb: "FFFFFF" }
        },
        alignment: {
            horizontal: 'center', // Center alignment
            vertical: 'center'
        },
        fill: {
            fgColor: { rgb: "4F81BD" }
        }
    };

    // Styling the headers (second row)
    headers.forEach((header, index) => {
        const cellAddress = XLSX.utils.encode_cell({ r: 1, c: index });
        ws[cellAddress].s = {
            font: {
                name: 'Arial',
                sz: 12,
                bold: true,
                color: { rgb: "000000" }
            },
            alignment: {
                horizontal: 'center',
                vertical: 'center'
            },
            fill: {
                fgColor: { rgb: "C6EFCE" }
            }
        };
    });

    // Styling specific cell containing the dynamic headline
    const headlineCellAddress = XLSX.utils.encode_cell({ r: 0, c: 0 }); // Assuming headline is in the first cell (A1)
    ws[headlineCellAddress].s = {
        font: {
            name: 'Arial',
            sz: 14,
            bold: true,
            color: { rgb: "000000" } // Black color
        },
        alignment: {
            horizontal: 'center', // Center alignment
            vertical: 'center'
        },
        fill: {
            fgColor: { rgb: "FFFF00" } // Yellow fill color
        }
    };

    // Save the workbook
    XLSX.writeFile(wb, 'Student_Report.xlsx');
};
	
	$scope.generatePDF = function() {
    const { jsPDF } = window.jspdf;

    const doc = new jsPDF();

    const header = [['ID', 'Name', 'Email']];
    const data = [
        [1, 'John Doe', 'john@example.com'],
        [2, 'Jane Smith', 'jane@example.com'],
        [3, 'William Johnson', 'william@example.com'],
        [4, 'Emma Wilson', 'emma@example.com']
    ];

    doc.autoTable({
        head: header,
        body: data,
        startY: 10,
        theme: 'striped',
        headStyles: { fillColor: [0, 102, 204], textColor: [255, 255, 255] },
        bodyStyles: { fillColor: [240, 240, 240] },
        alternateRowStyles: { fillColor: [255, 255, 255] },
        styles: { fontSize: 12, cellPadding: 4 },
        margin: { top: 20 }
    });

    doc.save('styled_table_generated.pdf');
};

    $scope.fetchallBranch();
});
