import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category-chart',
  templateUrl: './category-chart.component.html',
  styleUrls: ['./category-chart.component.css']
})
export class CategoryChartComponent implements OnInit {

  datasets = [
    {
      label: 'Traffic',
      data: [2112, 2343, 2545, 3423, 2365, 1985, 987],
    },
  ];

  labels = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday'];

  constructor() { }

  ngOnInit(): void {
  }

}
