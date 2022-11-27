import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatisticsGatewayService {

  private readonly apiUrl = "https://hadar-backend.azurewebsites.net/statistics";

  private readonly apiPeopleUrl = "https://hadar-backend.azurewebsites.net/people";

  constructor(private http: HttpClient) {
  }

  getChartStatistics(): Observable<ChartData> {
    return this.http.get<ChartData>(this.apiUrl);
  }

  getPeopleChartStatistics(): Observable<FinalPeoplePerHour> {
      return this.http.get<FinalPeoplePerHour>(this.apiPeopleUrl);
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

export interface PeopleChartData {
  finalPeople: FinalPeoplePerHour;
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

export interface FinalPeoplePerHour {
  "18"?: number;
  "19"?: number;
  "20"?: number;
  "21"?: number;
  "22"?: number;
  "23"?: number;
  "0"?: number;
  "1"?: number;
  "2"?: number;
  "3"?: number;
  "4"?: number;
}
