import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PeopleChartComponent } from './people-chart.component';

describe('PeopleChartComponent', () => {
  let component: PeopleChartComponent;
  let fixture: ComponentFixture<PeopleChartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PeopleChartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PeopleChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
