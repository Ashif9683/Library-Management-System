function uploadFile() {
            var fileInput = document.getElementById('fileInput');
            var file = fileInput.files[0];
            var formData = new FormData();
            formData.append('file', file);
            $(".loader-div").show(); // show loader
		$.ajax({
			url: 'uploadStudentExcel',
			type: 'POST',
			data: formData,
			success: function(data, textStatus, jqXHR) {
				
				 $(".loader-div").hide(); // hide loader
				if (data.trim() === 'Uploaded student data saved successfully') {
					swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'success', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
				}else{
					 $(".loader-div").hide(); // hide loader
				swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'warning', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				 $(".loader-div").hide(); // hide loader
				console.log(jqXHR);
			},
			processData: false,
			contentType: false
		});
}

function uploadbookFile() {
	 
            var fileInput = document.getElementById('filebookInput');
            var file = fileInput.files[0];
            var formData = new FormData();
            formData.append('file', file);
		$(".loader-div").show(); // show loader
		$.ajax({
			url: 'uploadBookExcel',
			type: 'POST',
			data: formData,
			success: function(data, textStatus, jqXHR) {
				console.log(data);
				 $(".loader-div").hide(); // hide loader
				if (data.trim() === 'Uploaded Book data saved successfully') {
					swal.fire({
            title: data,
            text: 'Thanks!!',
            icon: 'success', // You can change this to 'warning', 'error', etc.
            confirmButtonText: 'OK',
        });
				}else{
					 $(".loader-div").hide(); // hide loader
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
			},
			processData: false,
			contentType: false
			
		});
		
        }
        
function createBookExcel() {
    const headers = ['Library Name (Text)','Book Accession Code(Text)', 'ISBN No (Text)', 'Library Item Type (Text)','Book Quantity (Numeric)','Book Title (Text)','Book Added In (Text)','Book Category (Text)','Item Status (Text)','Subject Title (Text)','Language (Text)',
    				'Author Name (Text)','Author Name 2 (Text)','Author Name 3 (Text)','Author Name 4 (Text)', 'Author Name 5 (Text)','Call No(Text)', 'Author Mark (Text)','Volume (Text)','Edition (Text)','Series No (Text)','Series Title (Text)','Publisher (Text)','Publisher Place (Text)',
    				'Vendor Name (Text)','Vendor Place (Text)','Year Of Pub. (Text)','Bill No (Text)','Bill Date (Date)','Order No (Text)','Order Date (Date)','Cost of Item (Text)','Current Status (Text)','Currency Name (Text)',
    				'Discount Amount (Text)','Rack No (Text)','Shelf No (Text)','Pages (Text)','Pre Pages','Size (Text)','Type Of Binding (Text)','Keywords (Text)','Publication Date (Date)','Location (Text)','Editor (Text)','Compiler (Text)',
    				'Translator (Text)','Abstract (Text)','Discount In Percentage(Text)', 'Sub Title (Text)','Entry Date (Date)','Verified[Yes/No] (Text)'];
    
    // Assuming you have headers and data in arrays


// Create a new workbook
const workbook = XLSX.utils.book_new();

// Add worksheet
const worksheet = XLSX.utils.aoa_to_sheet([headers]);

// Format header cells
const headerRange = XLSX.utils.decode_range(worksheet['!ref']);
for (let col = headerRange.s.c; col <= headerRange.e.c; ++col) {
  const cellRef = XLSX.utils.encode_cell({ r: headerRange.s.r, c: col });
  const cell = worksheet[cellRef];
  if (headers[col] === "Bill Date" && headers[col] === "Order Date" && headers[col] === "Publication Date" ) {
    cell.z = "dd/mm/yyyy"; // Date format
  } else {
    cell.t = "s"; // Text format
  }
}

// Add worksheet to the workbook
XLSX.utils.book_append_sheet(workbook, worksheet, "Sheet1");

// Generate Excel file
const excelBuffer = XLSX.write(workbook, { bookType: "xlsx", type: "array" });

// Convert buffer to Blob
const blob = new Blob([excelBuffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

// Create download link
const downloadLink = document.createElement("a");
downloadLink.href = window.URL.createObjectURL(blob);
downloadLink.download = "sample.xlsx";

// Append link to the body and trigger click event
document.body.appendChild(downloadLink);
downloadLink.click();

// Clean up
document.body.removeChild(downloadLink);

  }

  
 function createStudentExcel() {
    const headers = ['Roll Number(Text)', 'Full Name (Text)','Father Name(Text)','Contact(Text)','Email(Text)','Branch(Text)','Year(Text)', 'Semester(Text)' ,
    					'Book Bank (Test)', 'Book Annually(Text)'];
    
    // Create a new workbook
    const wb = XLSX.utils.book_new();
    
    // Create a new worksheet
    const ws = XLSX.utils.aoa_to_sheet([headers]);
    
    // Add the worksheet to the workbook
    XLSX.utils.book_append_sheet(wb, ws, "Sheet1");
    
    // Generate the Excel file
    const wbout = XLSX.write(wb, { bookType: 'xlsx', type: 'binary' });
    
    // Save the Excel file
    function s2ab(s) {
      const buf = new ArrayBuffer(s.length);
      const view = new Uint8Array(buf);
      for (let i = 0; i < s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
      return buf;
    }
    const blob = new Blob([s2ab(wbout)], { type: 'application/octet-stream' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'StudentSample.xlsx';
    document.body.appendChild(a);
    a.click();
    setTimeout(() => {
      document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
    }, 0);
  }