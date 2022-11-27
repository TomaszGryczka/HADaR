import {AfterViewInit, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import Chart from 'chart.js/auto';
import {FoodDrinkConfig} from "../app.component";

@Component({
  selector: 'app-food-drink-chart',
  templateUrl: './food-drink-chart.component.html',
  styleUrls: ['./food-drink-chart.component.css']
})
export class FoodDrinkChartComponent implements AfterViewInit, OnChanges {

   @Input()
   foodChartData?: FoodDrinkConfig;
   @Input()
   drinkChartData?: FoodDrinkConfig;

    foodChart:any;
    drinkChart:any;

    foodChartId?:string;
    drinkChartId?:string;


    constructor() {
    }

    ngAfterViewInit() {
      setTimeout(() => {
        this.createFoodChart();
        this.createDrinkChart();
      }, 0);
    }

     ngOnChanges(changes: SimpleChanges) {

      if (this.foodChartData) {
         if (this.foodChart) {
             this.foodChart.destroy();
         }
         this.createFoodChart();
      }

      if (this.drinkChartData) {
         if (this.drinkChart) {
             this.drinkChart.destroy();
         }
         this.createDrinkChart();
      }

    }

    createFoodChart() {
        if (!!this.foodChartData) {
          this.foodChartId = "chart-" + this.foodChartData.categoryName.toLowerCase();
          console.log(document.getElementById(this.foodChartId))
          console.log(this.foodChartData);

          if (document.getElementById(this.foodChartId)) {
            this.foodChart = new Chart(this.foodChartId, {
              type: 'line',
              data: {
                labels: ['18', '19', '20', '21', '22', '23', '0', '1', '2', '3', '4'],
                datasets: [
                  {
                    label: this.foodChartData.categoryName,
                    data: [
                      this.foodChartData.finalPeople["18"],
                      this.foodChartData.finalPeople["19"],
                      this.foodChartData.finalPeople["20"],
                      this.foodChartData.finalPeople["21"],
                      this.foodChartData.finalPeople["22"],
                      this.foodChartData.finalPeople["23"],
                      this.foodChartData.finalPeople["0"],
                      this.foodChartData.finalPeople["1"],
                      this.foodChartData.finalPeople["2"],
                      this.foodChartData.finalPeople["3"],
                      this.foodChartData.finalPeople["4"],
                    ],
                    backgroundColor: this.foodChartData.colour
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
                      text: 'Avg. food portions per avg. people per hour'
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



      createDrinkChart() {
              if (!!this.drinkChartData) {
                this.drinkChartId = "chart-" + this.drinkChartData.categoryName.toLowerCase();
                console.log(document.getElementById(this.drinkChartId))
                console.log(this.drinkChartData);

                if (document.getElementById(this.drinkChartId)) {
                  this.drinkChart = new Chart(this.drinkChartId, {
                    type: 'line',
                    data: {
                      labels: ['18', '19', '20', '21', '22', '23', '0', '1', '2', '3', '4'],
                      datasets: [
                        {
                          label: this.drinkChartData.categoryName,
                          data: [
                            this.drinkChartData.finalPeople["18"],
                            this.drinkChartData.finalPeople["19"],
                            this.drinkChartData.finalPeople["20"],
                            this.drinkChartData.finalPeople["21"],
                            this.drinkChartData.finalPeople["22"],
                            this.drinkChartData.finalPeople["23"],
                            this.drinkChartData.finalPeople["0"],
                            this.drinkChartData.finalPeople["1"],
                            this.drinkChartData.finalPeople["2"],
                            this.drinkChartData.finalPeople["3"],
                            this.drinkChartData.finalPeople["4"],
                          ],
                          backgroundColor: this.drinkChartData.colour
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
                            text: 'Avg. drinks per avg. people per hour'
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
