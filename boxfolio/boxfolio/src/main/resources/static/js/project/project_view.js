var language_color=["blue", "red", "black","yellow", "orange"];

function change(p_data, p_per, r_data){
    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
          labels:
               p_data,
          datasets: [{
            label: "language",
            backgroundColor: language_color,
            data: p_per
          }]
        },
        options: {
            responsive: false,
            title: {
                display: true,
                text: 'programming language %'
            }
        }
    });

    new Chart(document.getElementById("radar-chart"), {
        type: 'radar',
        data: {
            labels: ["A", "B", "C"],
            datasets: [
                {
                    label: "level",
                    fill: true,
                    backgroundColor: "rgba(255,99,132,0.2)",
                    borderColor: "rgba(255,99,132,1)",
                    pointBorderColor: "#fff",
                    pointBackgroundColor: "rgba(255,99,132,1)",
                    pointBorderColor: "#fff",
                    data: r_data
                }
            ]
        },
        options: {
            responsive: false,
            scale: {
                ticks: {
                    max: 100,
                    min: 0,
                    stepSize: 20
                }
            },
            title: {
                display: true,
                text: 'level'
            }
        }
    });
}