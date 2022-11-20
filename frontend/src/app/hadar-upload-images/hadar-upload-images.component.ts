import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-hadar-upload-images',
  templateUrl: './hadar-upload-images.component.html',
  styleUrls: ['./hadar-upload-images.component.css']
})
export class HadarUploadImagesComponent implements OnInit {

  @Output()
  allFilesUploaded = new EventEmitter<boolean>();

  constructor() {

  }

  ngOnInit(): void {
  }

  fileUploaded(fileUploaded: boolean) {
    // todo all files uploaded
    this.allFilesUploaded.emit(fileUploaded);
  }

}
