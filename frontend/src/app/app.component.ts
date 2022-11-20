import {Component, OnInit} from '@angular/core';
import {AverageActionsPerHour, StatisticsGatewayService} from "./service/statistics-gateway.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  chartConfigs?: ChartConfig[];

  constructor(private statisticsGateway: StatisticsGatewayService) {
  }

  ngOnInit(): void {
    this.fetchChartData(true);
  }


  fetchChartData(allFilesUploaded: boolean) {
    if (allFilesUploaded) {
      this.statisticsGateway.getChartStatistics().subscribe(resp => {
        this.chartConfigs = [
          {
            categoryName: "Calling",
            averageActionsPerHour: resp.calling.averageActionsPerHour,
            colour: "red"
          },
          {
            categoryName: "Dancing",
            averageActionsPerHour: resp.dancing.averageActionsPerHour,
            colour: "blue"
          },
          {
            categoryName: "Drinking",
            averageActionsPerHour: resp.drinking.averageActionsPerHour,
            colour: "green"
          },
          {
            categoryName: "Eating",
            averageActionsPerHour: resp.eating.averageActionsPerHour,
            colour: "yellow"
          },
          {
            categoryName: "Fighting",
            averageActionsPerHour: resp.fighting.averageActionsPerHour,
            colour: "black"
          },
          {
            categoryName: "Hugging",
            averageActionsPerHour: resp.hugging.averageActionsPerHour,
            colour: "grey"
          }
        ];
      });

    }
  }
}

export interface ChartConfig {
  categoryName: string,
  averageActionsPerHour: AverageActionsPerHour,
  colour: string
}
