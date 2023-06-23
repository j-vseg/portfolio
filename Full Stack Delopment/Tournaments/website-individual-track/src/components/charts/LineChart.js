import React from "react";
import { Line } from "react-chartjs-2";
import { Chart, CategoryScale, LinearScale, LineController, PointElement, LineElement} from 'chart.js';
import { useEffect } from "react";

function LineChart({ chartData }) {
    Chart.register(CategoryScale, LinearScale, LineController, PointElement, LineElement);
    console.log(chartData);

        useEffect(() => {
            if (document.getElementById('order-chart') !== undefined) {
                var myChart = new Chart(
                    document.getElementById('order-chart'),
                    {
                    type: 'line',
                    data: {
                        labels: chartData.map(row => row.year),
                        datasets: [
                        {
                            label: 'Yearly overview',
                            data: chartData.map(row => row.count),
                            borderColor: 'rgb(17, 16, 29)',
                            fill: false,
                            tension: 0.1
                        }
                        ]
                    }
                    }
                );
            }

            return () => {
                myChart.destroy()
            }
        })

    return (
        <div className="chart-container">

        <select id="selectFilter" className="selectpicker form-control" data-live-search="true">
            <option value="yearly">Yearly</option>
            <option value="weekly">Weekly</option>
            <option value="daily">Daily</option>
        </select> 

        <canvas id="order-chart"></canvas>

        {/*<Line
            data={chartData}
            options={{
            plugins: {
                title: {
                display: true,
                text: "Users Gained between 2016-2020"
                },
                legend: {
                display: false
                }
            }
            }}
        />*/}
        </div>
    );
}
export default LineChart;