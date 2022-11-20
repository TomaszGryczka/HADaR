import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import Chart from 'chart.js/auto';
import {ChartConfig} from "../app.component";

@Component({
  selector: 'app-category-chart',
  templateUrl: './category-chart.component.html',
  styleUrls: ['./category-chart.component.css']
})
export class CategoryChartComponent implements AfterViewInit, OnChanges {

  @Input()
  chartData?: ChartConfig;

  chart: any;

  chartId?: string;

  constructor() {
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.createChart();
    }, 0);
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.chartData) {
      if(this.chart) {
        this.chart.destroy();
      }
      this.createChart();
    }
  }

  createChart() {
    if (!!this.chartData) {
      this.chartId = "category-chart-" + this.chartData.categoryName.toLowerCase();
      console.log(document.getElementById(this.chartId))
      if(document.getElementById(this.chartId)) {
        this.chart = new Chart(this.chartId, {
          type: 'bar',
          data: {
            labels: ['18', '19', '20', '21', '22', '23', '0', '1', '2', '3', '4'],
            datasets: [
              {
                label: this.chartData.categoryName,
                data: [
                  this.chartData.averageActionsPerHour["18"],
                  this.chartData.averageActionsPerHour["19"],
                  this.chartData.averageActionsPerHour["20"],
                  this.chartData.averageActionsPerHour["21"],
                  this.chartData.averageActionsPerHour["22"],
                  this.chartData.averageActionsPerHour["23"],
                  this.chartData.averageActionsPerHour["0"],
                  this.chartData.averageActionsPerHour["1"],
                  this.chartData.averageActionsPerHour["2"],
                  this.chartData.averageActionsPerHour["3"],
                  this.chartData.averageActionsPerHour["4"],
                  ],
                backgroundColor: 'blue'
              }
            ]
          },
          options: {
            aspectRatio: 2.5
          }
        });
      }
    }
  }

  // createChart() {
  //   if (!!this.chartData) {
  //     this.chart = new Chart("category-chart", {
  //       type: 'bar',
  //       data: {
  //         labels: ['2022-05-10', '2022-05-11', '2022-05-12', '2022-05-13',
  //           '2022-05-14', '2022-05-15', '2022-05-16', '2022-05-17',],
  //         datasets: [
  //           {
  //             label: "Sales",
  //             data: ['467', '576', '572', '79', '92',
  //               '574', '573', '576'],
  //             backgroundColor: 'blue'
  //           }
  //         ]
  //       },
  //       options: {
  //         aspectRatio: 2.5
  //       }
  //     });
  //   }
  // }
}


