import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {hourRange, requiredFileType} from "./upload-image-validators";
import {UploadImageGatewayService} from "../service/upload-image-gateway.service";

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  uploadImageForm: FormGroup;

  constructor(
    private uploadImageGateway: UploadImageGatewayService,
    private formBuilder: FormBuilder) {

    this.uploadImageForm = this.formBuilder.group({
      image: new FormControl(null, [Validators.required, requiredFileType('png')]),
      hour: new FormControl(null, [Validators.required, hourRange(16, 4)])
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.uploadImageForm.get('image')?.value);
    formData.append('hour', this.uploadImageForm.get('hour')?.value);

    this.uploadImageGateway.uploadFileWithHour(formData).subscribe(() => {
      console.log("Success!");
    });
  }

}
