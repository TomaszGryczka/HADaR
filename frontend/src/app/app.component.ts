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
            averageActionsPerHour: resp.calling.averageActionsPerHour
          },
          {
            categoryName: "Dancing",
            averageActionsPerHour: resp.dancing.averageActionsPerHour
          },
          {
            categoryName: "Drinking",
            averageActionsPerHour: resp.drinking.averageActionsPerHour
          },
          {
            categoryName: "Eating",
            averageActionsPerHour: resp.eating.averageActionsPerHour
          },
          {
            categoryName: "Fighting",
            averageActionsPerHour: resp.fighting.averageActionsPerHour
          },
          {
            categoryName: "Hugging",
            averageActionsPerHour: resp.hugging.averageActionsPerHour
          }
        ];
      });

    }
  }
}

export interface ChartConfig {
  categoryName: string,
  averageActionsPerHour: AverageActionsPerHour;
}
