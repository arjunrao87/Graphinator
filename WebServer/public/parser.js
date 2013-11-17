$(document).ready($(function () {
	
	// Step 1 : Making the post requests
	makePostRequest( "START a=node(1) MATCH a-[?:PORTFOLIOS|CUSIP*]->()-[:RETURNS]->d  RETURN d LIMIT 1000", "returns" );
	
	// Step 2 : Plot the chart
	
	var returnsMetadata = {
			"chartTitle" : "Cusip Returns",
			
			"xAxisTitle" : ['Apr', 'May', 'Jun'],
			
			"yAxisTitle" : "Returns ( in Dollars )",
			
			"dataPoints" : [{
				name: 'P1',
				data: [7.0, 6.9, 9.5]
			}, {
				name: 'P2',
				data: [-0.2, 0.8, 5.7]
			}, {
				name: 'P3',
				data: [-0.9, 0.6, 3.5]
			}]					
	}
	
	plotChart( returnsMetadata );

	// Provide popup within the modal
	$(function (){ 
		$("#example").popover();  
	});  


}));

//-------------------------  METHOD DEFINITION -------------------------------------//

function makePostRequest( query, parameter ){
	var request = $.ajax({
		url: "http://localhost:7474/db/data/cypher",
		type: "POST",
		beforeSend: function (request)
        {
			request.setRequestHeader("'Access-Control-Allow-Origin'", "*");
			request.setRequestHeader("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
			request.setRequestHeader("Access-Control-Allow-Methods", "GET, PUT, POST");
        },
		data: {
			"query" : query,
			"params" : {}
		},
		crossDomain:true,
		success: function(data, textStatus, jqXHR){
			var obj = JSON.stringify(data);
			var itemA = data.data[1];
			for(i in itemA){
				var key = i;
				var val = itemA[i];
				for(j in val){
					var sub_key = j;
					var sub_val = val.j;
					console.log(sub_key + "--"+ sub_val);
				}
			}
			$("#displayPane").html(obj);
		},
		dataType: "json"
	});
}

function plotChart( metadata ){
	$('#main-result').highcharts({
		title: {
			text: metadata.chartTitle,
			x: -20 //center
		},
		subtitle: {
			text: 'Source: Aladdin',
			x: -20
		},
		xAxis: {
			categories: metadata.xAxisTitle
		},
		yAxis: {
			title: {
				text: metadata.yAxisTitle
			},
			plotLines: [{
				value: 0,
				width: 1,
				color: '#808080'
			}]
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		series: metadata.dataPoints
	});
}