import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HadarUploadImagesComponent } from './hadar-upload-images.component';

describe('HadarUploadImagesComponent', () => {
  let component: HadarUploadImagesComponent;
  let fixture: ComponentFixture<HadarUploadImagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HadarUploadImagesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HadarUploadImagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
