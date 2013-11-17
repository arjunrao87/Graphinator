$(document).ready(function(){
	/* Demo data only for jsfiddle */
	var ajaxUrl = '/assets/data.json';
	/* END demo data */

	$('.typeahead').on('click.typeaheadonce', function() {
	    var $this = $(this)
	    $this.off('click.typeaheadonce'); // Disable this listener so that it's called only once
	    $.ajax(ajaxUrl, {
	        type: 'get',
	        data: "Alabama","Alaska","Arizona","Arkansas","California"
	    }).done(function(data) {
	    	console.log("in here");
	        // initialize the typeahead plugin
	        $this.typeahead({
	            source: data
	        });
	        alert('activated!'); // Ok !
	    });
	});
});


