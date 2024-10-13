var app = angular.module('ReportBook', []);

app.controller('ReportBookController', function($scope, $http) {
    $scope.categorys = [];
    $scope.AccessionCodeCategory = [];
    $scope.bookCategory = '';
	$scope.bookRecord = [];
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

    $scope.fetchallCategory = function() {
        $http.post('fetchallcategory', {})
            .then(function(response) {
                $scope.categorys = response.data;
            }, function(error) {
                console.error(error);
            });
    };  
    $scope.fetchAllAccessioncode = function() {
        $http.post('fetchallAccessionCode', {})
            .then(function(response) {
                $scope.AccessionCodeCategory = response.data;
            }, function(error) {
                console.error(error);
            });
    };
    
	$scope.findrecordcategory = function() {
		
		
		var accessionCategory = $scope.accessionCodeCategory;
		
		if (accessionCategory && accessionCategory !== "" ) {
			$(".loader-div").show(); // show loader
			$http({
				method: 'POST',
				url: 'findbookCategory',
				data: JSON.stringify({ accessionCategory: accessionCategory })
			}).then(function(response) {
				console.log("response  --   " + response.data);
				if (Array.isArray(response.data)) {
					console.log("response  --   " + response.data);
					$scope.bookRecord = response.data;
						$scope.filterRecords();
				}else {
					swal.fire({
						title: 'No Data found! ',
						text: 'Thanks!!',
						icon: 'warning', // You can change this to 'warning', 'error', etc.
						confirmButtonText: 'OK',
					})
				}
				$(".loader-div").hide(); // hide loader
			}, function(error) {
				console.error("Error occurred:", error);
			});
		} else {
			swal.fire({
				title: 'Select  Atleast One Option !!',
				text: 'Thanks!!',
				icon: 'warning',
				confirmButtonText: 'OK',
			});
		}
	};
	$scope.filterRecords = function() {
		const searchText = ($scope.searchText || '').toLowerCase();
		$scope.filteredRecords = $scope.bookRecord.filter(record =>
			record.booktitle.toLowerCase().includes(searchText)
		
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
    var bookCategory = $scope.bookCategory;
    var headline = 'Nitra Library Management Book Report';
    if (bookCategory) {
        headline += ' - ' + bookCategory; // Append selected branch if available
    }
    ws_data.push([headline]);

    // Adding headers
    // 'Book Accession Code','Book Accession Number',
    const headers = ['Serial No.','Library Name','Accession Number','ISBN No', 'Library Item Type','Book Quantity','Book Title ','Book Added In ','Book Category','Item Status','Subject Title','Language',
    				'Author Name','Author Name 2','Author Name 3','Author Name 4 ', 'Author Name 5','Call No', 'Author Mark','Volume','Edition','Series No','Series Title','Publisher Name','Publisher Place',
    				'Vendor Name','Vendor Place','Year Of Pub.','Bill No','Bill Date','Order No','Order Date','Cost of Item','Current Status','Currency Name',
    				'Discount Amount','Rack No','Shelf No','Pages','Pre Pages','Size','Type Of Binding','Keywords ','Publication Date','Location ','Editor','Compiler',
    				'Translator','Abstract ','Discount In Percentage', 'Sub Title','User Name','Employee Name','Entry Date','Verified','Created At'];
    ws_data.push(headers);

    // Adding data
    $scope.bookRecord.forEach(function(record) {
        ws_data.push([
            record.serailNo,
            record.libraryName,
            record.accessionNumber,  
            record.isbnNumber,
            record.libraryItemType,
            record.bookquantity,
            record.booktitle,
            record.bookAddedIn,
            record.bookCategory,
            record.itemStatus,
            record.subjectTitle,
            record.languageName,
            record.authorName,
            record.authorName2,
            record.authorName3,
            record.authorName4,
            record.authorName5,
            record.callNo,
            record.authorMark,
            record.volume,
            record.edition,
            record.seriesNo,
            record.seriesTitle,
            record.bookPublisherName,
            record.bookPublisherPlace,
            record.vendorName,
            record.vendorPlace,
            record.publicationYear,
            record.billNumber,
            record.billDate,
            record.orderNumber,
            record.orderDate,
            record.costOfItem,
            record.currentStatus,
            record.currencyName,
            record.discountAmount,
            record.rackNumber,
            record.shelfNumber,
            record.pagesNumber,
            record.prePages,
            record.size,
            record.typeOfBinding,
            record.keywords,
            record.publicationDate,
            record.location,
            record.editor,
            record.compiler,
            record.translator,
            record.abstracts,
            record.discountInPercantage,
            record.subTitle,
            record.userName,
            record.employeeName,
            record.entryDate,
            record.verified,
            record.createdAt
           
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


    $scope.fetchallCategory();
    $scope.fetchAllAccessioncode();
});
