google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
	  
	  function usersMonth(){
	  fetch('/api/users-per-month').then(response=>{
		  var json = response.json();
	  });
	  return json;
	  }
	  json = (int) usersMonth();

      function drawChart(json) {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  json,      json],
          ['2005',  json,      json],
          ['2006',  660,       1120],
          ['2007',  1030,      540]
        ]);

        var options = {
          title: 'Company Performance',
          curveType: 'function',
          legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
      }