import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HadarStatisticsComponent } from './hadar-statistics.component';

describe('HadarStatisticsComponent', () => {
  let component: HadarStatisticsComponent;
  let fixture: ComponentFixture<HadarStatisticsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HadarStatisticsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HadarStatisticsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
