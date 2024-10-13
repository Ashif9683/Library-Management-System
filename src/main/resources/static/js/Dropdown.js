function create_custom_dropdowns() {
	    $('select').each(function (i, select) {
	        if (!$(this).next().hasClass('dropdown-select')) {
	            $(this).after('<div class="dropdown-select wide ' + ($(this).attr('class') || '') + '" tabindex="0"><span class="current"></span><div class="list"><ul></ul></div></div>');
	            var dropdown = $(this).next();
	            var options = $(select).find('option');
	            var selected = $(this).find('option:selected');
	            dropdown.find('.current').html(selected.data('display-text') || selected.text());
	            options.each(function (j, o) {
	                var display = $(o).data('display-text') || '';
	                dropdown.find('ul').append('<li class="option ' + ($(o).is(':selected') ? 'selected' : '') + '" data-value="' + $(o).val() + '" data-display-text="' + display + '">' + $(o).text() + '</li>');
	            });
	        }
	    });
	
	    $('.dropdown-select ul').before('<div class="dd-search"><input id="txtSearchValue" autocomplete="off" onkeyup="filter()" class="dd-searchbox" type="text"></div>');
	}
	
	// Event listeners
	
	// Open/close
	$(document).on('click', '.dropdown-select', function (event) {
	    if($(event.target).hasClass('dd-searchbox')){
	        return;
	    }
	    $('.dropdown-select').not($(this)).removeClass('open');
	    $(this).toggleClass('open');
	    if ($(this).hasClass('open')) {
	        $(this).find('.option').attr('tabindex', 0);
	        $(this).find('.selected').focus();
	    } else {
	        $(this).find('.option').removeAttr('tabindex');
	        $(this).focus();
	    }
	});
	
	// Close when clicking outside
	$(document).on('click', function (event) {
	    if ($(event.target).closest('.dropdown-select').length === 0) {
	        $('.dropdown-select').removeClass('open');
	        $('.dropdown-select .option').removeAttr('tabindex');
	    }
	    event.stopPropagation();
	});
	
	function filter(){
	    var valThis = $('#txtSearchValue').val();
	    $('.dropdown-select ul > li').each(function(){
	        var text = $(this).text();
	        (text.toLowerCase().indexOf(valThis.toLowerCase()) > -1) ? $(this).show() : $(this).hide();         
	    });
	};
	
	// Option click
	$(document).on('click', '.dropdown-select .option', function (event) {
	    $(this).closest('.list').find('.selected').removeClass('selected');
	    $(this).addClass('selected');
	    var text = $(this).data('display-text') || $(this).text();
	    $(this).closest('.dropdown-select').find('.current').text(text);
	    $(this).closest('.dropdown-select').prev('select').val($(this).data('value')).trigger('change');
	});
	
	// Keyboard events
	$(document).on('keydown', '.dropdown-select', function (event) {
	    var focused_option = $($(this).find('.list .option:focus')[0] || $(this).find('.list .option.selected')[0]);
	    // Space or Enter
	    //if (event.keyCode == 32 || event.keyCode == 13) {
	    if (event.keyCode == 13) {
	        if ($(this).hasClass('open')) {
	            focused_option.trigger('click');
	        } else {
	            $(this).trigger('click');
	        }
	        return false;
	        // Down
	    } else if (event.keyCode == 40) {
	        if (!$(this).hasClass('open')) {
	            $(this).trigger('click');
	        } else {
	            focused_option.next().focus();
	        }
	        return false;
	        // Up
	    } else if (event.keyCode == 38) {
	        if (!$(this).hasClass('open')) {
	            $(this).trigger('click');
	        } else {
	            var focused_option = $($(this).find('.list .option:focus')[0] || $(this).find('.list .option.selected')[0]);
	            focused_option.prev().focus();
	        }
	        return false;
	        // Esc
	    } else if (event.keyCode == 27) {
	        if ($(this).hasClass('open')) {
	            $(this).trigger('click');
	        }
	        return false;
	    }
	});
	
	function fetchallAsscessionRecord(){
	    $.ajax({
	        url: 'fetchallAsscessionRecord',
	        type: 'POST',
	        success: function(data, textStatus, jqXHR) {
	            console.log("data --  + " +  data);
	
	            var selectElement = $('select[name="AsscessionRecords"]');
	            selectElement.empty();
	            selectElement.append('<option value="">Select Asscession Number </option>');
	            $.each(data, function(index, AsscessionRecord) {
	                selectElement.append('<option value="' + AsscessionRecord + '">' + AsscessionRecord + '</option>');
	            });
	
	            // Call create_custom_dropdowns after data is populated
	            create_custom_dropdowns();
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR);
	        },
	        processData: false,
	        contentType: false
	    });
	}
	
	function getAssessionNumber() {
		var AsscessionRecords = document.getElementById('AsscessionRecords').value; 			
	 	$.ajax({
	        url: 'Assessionrecordone',
	        type: 'POST',
	        data: AsscessionRecords,
	        success: function(data, textStatus, jqXHR) {
	            console.log("data --  + " +  data);
	                renderTableAss(data);
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR);
	        },
	        processData: false,
	        contentType: false
	    });
	 	
	}
	
	function renderTableAss(records) {
	    const tbody = document.getElementById('bookRecords');
	    tbody.innerHTML = '';
	    const userRole = document.body.getAttribute('data-user-role');
	        const row = document.createElement('tr');
	        row.innerHTML = `
	            <td>
	                <a href="BookPerRecord.html?primaryKey=${records.primaryKey}">${records.booktitle}</a>
	            </td>
	            <td>${records.authorName}</td>
	            <td>${records.bookCategory}</td>
	            <td>${records.bookquantity}</td>
	            <td>
	                ${userRole === 'ADMIN' ? `
	                    <button onclick="openEditBookModel(${records.primaryKey})" class="btn btn-primary btn-sm">Edit</button>
	                    <button onclick="openDeleteBookModel(${records.primaryKey})" class="btn btn-danger btn-sm">Delete</button>
	                ` : ''}
	                <button onclick="openbookAccessionDetail(${records.primaryKey})" class="btn btn-secondary btn-sm">Accession Detail</button>
	            </td>
	        `;
	        tbody.appendChild(row);
	        }
	
	 function refreshPage() {
            location.reload();
        }
	
	$(document).ready(function () {
	    fetchallAsscessionRecord();
	});