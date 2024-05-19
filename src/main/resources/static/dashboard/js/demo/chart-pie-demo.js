fetch('/admin/carros/carTypeCounts')
    .then(response => response.json())
    .then(data =>
    {
        const labels = Object.keys(data);
        const counts = Object.values(data);

        const pieCtx = document.getElementById('myPieChart').getContext('2d');
        new Chart(pieCtx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: counts,
                    backgroundColor: ['#bb05ed', '#07de98', '#038898', "#07d9f2", "#e307ac", "#fbff00", "#b60628", "#b60628"],
                    hoverBackgroundColor:  ['#ae71bf', '#729e90', '#038898', "#589da6", "#e084c9", "#e1e37b", "#c25f72", "#b60628"],
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                }]
            },
            options: {
                maintainAspectRatio: false,
                tooltips: {
                    backgroundColor: "rgb(255,255,255)",
                    bodyFontColor: "#858796",
                    borderColor: '#dddfeb',
                    borderWidth: 1,
                    xPadding: 15,
                    yPadding: 15,
                    displayColors: false,
                    caretPadding: 10,
                },
                legend: {
                    display: false
                },
                cutoutPercentage: 80,
            },
        });
    });