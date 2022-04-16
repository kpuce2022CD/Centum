new Chart(document.getElementById("code-chart"), {
    type: 'radar',
    data: {
        labels: ["응집도", "비결합도", "비복잡도", "비중복도", "표준화"],
        datasets: [
            {
                label: "현재 구현도",
                fill: true,
                backgroundColor: "rgba(200,222,255,0.2)",
                borderColor: "rgba(200,222,255,1)",
                pointBorderColor: "#fff",
                pointBackgroundColor: "rgba(179,181,198,1)",
                data: [65.77, 65.61, 46.69, 77.62, 98.82],
            },
            {
                label: "",
                fill: false,
                data: [0, 100],
                backgroundColor: "#fff0",
                borderColor: "#fff0",
                pointBorderColor: "#fff0",
                pointBackgroundColor: "#fff0"
            }
        ]
    },
    options: {
    //   title: {
    //     display: true,
    //     text: '코드 구현도'
    //   }
    }
});

const actions = [
  {
    name: 'Randomize',
    handler(chart) {
      chart.data.datasets.forEach(dataset => {
        dataset.data = Utils.numbers({count: chart.data.labels.length, min: 0, max: 100});
      });
      chart.update();
    }
  }
];