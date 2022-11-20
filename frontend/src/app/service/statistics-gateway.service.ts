import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatisticsGatewayService {

  private readonly apiUrl = "http://localhost:8080/statistics";

  constructor(private http: HttpClient) {
  }

  getChartStatistics(): Observable<ChartData> {
    return this.http.get<ChartData>(this.apiUrl);
  }
}

export interface ChartData {
  calling: Calling;
  dancing: Dancing;
  drinking: Drinking;
  hugging: Hugging;
  eating: Eating;
  fighting: Fighting;
}

export interface Calling {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface Dancing {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface Drinking {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface Hugging {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface Eating {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface Fighting {
  averageActionsPerHour: AverageActionsPerHour;
}

export interface AverageActionsPerHour {
  18: number;
  19: number;
  20: number;
  21: number;
  22: number;
  23: number;
  0: number;
  1: number;
  2: number;
  3: number;
  4: number;
}
