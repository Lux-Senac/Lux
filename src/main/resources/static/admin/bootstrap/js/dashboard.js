const ctx = document.getElementById("chart").getContext('2d');
      const myChart = new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ["Tesla Model S", "Tesla Model X", "Porsche Taycan Turbo S", "Bmw I8",
          "BYD Yuan", "BYD Tan"],
          datasets: [{
            label: 'Vendas',
            backgroundColor: 'rgba(161, 198, 247, 1)',
            borderColor: 'rgb(47, 128, 237)',
            data: [10, 5, 3, 2, 20, 30],
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true,
              }
            }]
          }
        },
      });