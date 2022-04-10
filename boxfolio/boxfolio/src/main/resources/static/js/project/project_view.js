var language_list=["c++", "c", "python"];
var language_per=[30,50,20];
var language_color=["#3e95cd", "#8e5ea2","#3cba9f"];

function change(){
    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
          labels:
               language_list,
          datasets: [{
            label: "language",
            backgroundColor: language_color,
            data: language_per
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
}


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
          data: [25,50,75]
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