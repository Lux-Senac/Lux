fetch('/admin/vendas/monthlyEarnings')
    .then(response => response.json())
    .then(data =>
    {
        const monthOrder = ["janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"];

        const sortedData = {};
        monthOrder.forEach(month => {
            if (data.hasOwnProperty(month)) {
                sortedData[month] = data[month];
            } else {
                // If a month has no data, set earnings to 0 to avoid gaps in the chart
                sortedData[month] = 0;
            }
        });

        // Now, use the sorted data
        const labels = Object.keys(sortedData);
        const dataset = Object.values(sortedData);

        const ctx = document.getElementById('myAreaChart').getContext('2d');
        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Ganhos Mensais',
                    data: dataset,
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    });