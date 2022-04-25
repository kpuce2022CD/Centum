var language_color=["blue", "red", "black","yellow", "orange"];
var lev_data=[];
var lan_data=[[], []];

function set(){
    var level=document.getElementsByClassName("level");
    var language=document.getElementsByClassName("language");
    var percent=document.getElementsByClassName("percent");

    for (var i=0 ; i < level.length ; i++){
        lev_data[i]=level[i].value;
    }
    for (var i=0 ; i < language.length ; i++){
        lan_data[0][i]=language[i].value;
        lan_data[1][i]=percent[i].value;
    }
}

function change(){
    set();
    new Chart(document.getElementById("pie-chart"), {
        type: 'pie',
        data: {
          labels:
               lan_data[0],
          datasets: [{
            label: "language",
            backgroundColor: language_color,
            data: lan_data[1]
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
            labels: ["응집도", "비복잡도", "비결합도", "비중복도", "표준화"],
            datasets: [
                {
                    label: "코드 완성도",
                    fill: true,
                    backgroundColor: "rgba(255,99,132,0.2)",
                    borderColor: "rgba(255,99,132,1)",
                    pointBorderColor: "#fff",
                    pointBackgroundColor: "rgba(255,99,132,1)",
                    pointBorderColor: "#fff",
                    data: lev_data
                }
            ]
        },
        options: {
            responsive: false,
            scale: {
                ticks: {
                    max: 100,
                    min: 0,
                    stepSize: 10
                }
            },
            title: {
                display: true,
                text: 'level'
            }
        }
    });
}