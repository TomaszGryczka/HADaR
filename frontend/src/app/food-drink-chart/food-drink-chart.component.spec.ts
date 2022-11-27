import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodDrinkChartComponent } from './food-drink-chart.component';

describe('FoodDrinkChartComponent', () => {
  let component: FoodDrinkChartComponent;
  let fixture: ComponentFixture<FoodDrinkChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodDrinkChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodDrinkChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
