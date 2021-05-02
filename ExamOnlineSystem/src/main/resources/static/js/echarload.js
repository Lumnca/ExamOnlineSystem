//---------------------------------------------------------------
var chartDom = document.getElementById('cy');
var myChart = echarts.init(chartDom);
myChart.showLoading();
var option = {
    title: {
        text: '分数组成',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '成绩组成',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
var option2 = {
    title: {
        text: '分数组成',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '成绩组成',
            type: 'pie',
            radius: '50%',
            data: [],
            emphasis: {
                itemStyle: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
var chartDom2 = document.getElementById('fb');
var myChart2 = echarts.init(chartDom2);
myChart2.showLoading();
var chartDom3 = document.getElementById('st');
var myChart3 = echarts.init(chartDom3);
var option3;
request('GET', "/submits/" + id, null, function (res) {
    app.exampeopleinfor = res.data;
    let notPass = 0;
    let Pass = 0;
    res.data.forEach((e) => {
        if (e.score < 60) {
            notPass++;
        }
        else if (e.score >= 60) {
            Pass++;
        }
    });

    option.series[0].data.push({ value: notPass, name: '不及格' + `(${Math.floor(notPass / res.data.length * 100)}%)` });
    option.series[0].data.push({ value: Pass, name: '及格' + `(${Math.floor(Pass / res.data.length * 100)}%)` });
    myChart.hideLoading();
    myChart.setOption(option);

    let s1 = 0;
    let s2 = 0;
    let s3 = 0;
    let s4 = 0;
    res.data.forEach((e) => {
        if (e.score < 60) {
            s1++;
        }
        else if (e.score >= 60 && e.score < 80) {
            s2++;
        }
        else if (e.score >= 80 && e.score < 90) {
            s3++;
        }
        else {
            s4++;
        }
    });
    option2.series[0].data.push({ value: s1, name: '不及格(<60)' + `(${Math.floor(s1 / res.data.length * 100)}%)` });
    option2.series[0].data.push({ value: s2, name: '良好(60~80)' + `(${Math.floor(s2 / res.data.length * 100)}%)` });
    option2.series[0].data.push({ value: s3, name: '中等(80~90)' + `(${Math.floor(s3 / res.data.length * 100)}%)` });
    option2.series[0].data.push({ value: s4, name: '优秀(90~100)' + `(${Math.floor(s4 / res.data.length * 100)}%)` });
    myChart2.hideLoading();
    myChart2.setOption(option2);




    var subArray = [];
    var dataArray = [];
    var tit = [];
    var datas = []
    res.data.forEach((e, i) => {
        dataArray.push(e.results);
    })
    dataArray.forEach((e, k) => {
        e.forEach((r, i) => {
            subArray[i] ? subArray[i] = subArray[i] : subArray[i] = 0;
            let df = r.score / dataArray.length;
            subArray[i] += df;
            subArray[i] = parseFloat(subArray[i].toFixed(2));
            tit[i] ? tit[i] = tit[i] : tit[i] = `第${i + 1}题`;

            if (!datas[i]) {
                let d = {
                    no : r.no,
                    title : r.title,
                    tNum : 0,
                    fNum : 0,
                    avg : 0,
                    avgs:0,
                }
                datas[i] = d;  
            }
            r.score ? datas[i].tNum++ : datas[i].fNum++;
            datas[i].avg = subArray[i];
            datas[i].avgs = (datas[i].tNum / dataArray.length * 100).toFixed(2) + "%";
        })
    });

    app.datas = datas;

    option3 = {
        title: {
            text: '题目得分',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        xAxis: {
            type: 'category',
            data: tit
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: '平均分',
            data: subArray,
            type: 'bar',
            showBackground: true,
            backgroundStyle: {
                color: 'rgba(180, 180, 180, 0.2)'
            }
        }]
    };

    myChart3.setOption(option3);


}, null);






