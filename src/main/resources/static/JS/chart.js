let decodedChartData = decodeHtml(chartData);
let JSONChartData = JSON.parse(decodedChartData);

let arrayLength = JSONChartData.length;
let valueArray = [];
let labelArray = [];

for (let i = 0; i < arrayLength; i++){
    valueArray.push(JSONChartData[i].value)
    labelArray.push(JSONChartData[i].label)
}

new Chart(document.getElementById("pieChart"), {
    type: 'pie',
    data: {
        labels: labelArray,
        datasets: [{
            label: 'My first dataset',
            backgroundColor: ["#3e95cd", "#8e5ea2", "#3cba9f"],
            data: valueArray
        }]
    },
    options: {
        title: {
            display: true,
            text: "Project Statuses"
        }
    }
});

// [{"value" : 1, "label" : "COMPLETED"}, {"value" : 1, "label" : "INPROGRESS"}, {"value" : 1, "label" : "NOTSTARTED"}]
function decodeHtml (html) {
    let txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}