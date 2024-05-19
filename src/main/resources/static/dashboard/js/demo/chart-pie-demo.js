fetch('/admin/carros/carTypeCounts')
    .then(response => response.json())
    .then(data => {
        // Extract labels (car types) and data (counts)
        const labels = Object.keys(data);
        const counts = Object.values(data);

        const pieCtx = document.getElementById('myPieChart').getContext('2d');
        new Chart(pieCtx, {
            type: 'doughnut',
            data: {
                labels: labels,
                datasets: [{
                    data: counts,
                    backgroundColor: ['#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF', '#FF9F40'] // Customize colors
                }]
            }
        });

    });