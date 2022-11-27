import {AfterViewInit, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import Chart from 'chart.js/auto';
import {PeopleChartConfig} from "../app.component";

@Component({
  selector: 'app-people-chart',
  templateUrl: './people-chart.component.html',
  styleUrls: ['./people-chart.component.css']
})
export class PeopleChartComponent implements AfterViewInit, OnChanges {

  @Input()
  peopleChartData?: PeopleChartConfig;

  peopleChart:any;

  chartPeopleId?:string;

  constructor() {
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.createPeopleChart();
    }, 0);
  }

   ngOnChanges(changes: SimpleChanges) {

    if (this.peopleChartData) {
       if (this.peopleChart) {
           this.peopleChart.destroy();
       }
       this.createPeopleChart();
    }


  }

  createPeopleChart() {
      if (!!this.peopleChartData) {
        this.chartPeopleId = "chart-" + this.peopleChartData.categoryName.toLowerCase();
        console.log(document.getElementById(this.chartPeopleId))
        console.log(this.peopleChartData);

        if (document.getElementById(this.chartPeopleId)) {
          this.peopleChart = new Chart(this.chartPeopleId, {
            type: 'line',
            data: {
              labels: ['18', '19', '20', '21', '22', '23', '0', '1', '2', '3', '4'],
              datasets: [
                {
                  label: this.peopleChartData.categoryName,
                  data: [
                    this.peopleChartData.finalPeople["18"],
                    this.peopleChartData.finalPeople["19"],
                    this.peopleChartData.finalPeople["20"],
                    this.peopleChartData.finalPeople["21"],
                    this.peopleChartData.finalPeople["22"],
                    this.peopleChartData.finalPeople["23"],
                    this.peopleChartData.finalPeople["0"],
                    this.peopleChartData.finalPeople["1"],
                    this.peopleChartData.finalPeople["2"],
                    this.peopleChartData.finalPeople["3"],
                    this.peopleChartData.finalPeople["4"],
                  ],
                  backgroundColor: this.peopleChartData.colour
                }
              ]
            },
            options: {
              aspectRatio: 2.5,
              scales: {
                y: {
                  min: 0,
                  title: {
                    display: true,
                    text: 'Avg. people per hour'
                  }
                },
                x: {
                  title: {
                    display: true,
                    text: 'Hour'
                  }
                }
              }
            }
          });
        }
      }
    }


}


