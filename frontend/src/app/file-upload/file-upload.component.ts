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
      imageSource: new FormControl(null, [Validators.required]),
      hour: new FormControl(null, [Validators.required, hourRange(16, 4)])
    });
  }

  ngOnInit(): void {
  }

  onImageChange(files: File[]) {
    const image = files[0];
    this.uploadImageForm.patchValue({
      imageSource: image
    });
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.uploadImageForm.get('imageSource')?.value);
    formData.append('hour', this.convertTimeToHours(this.uploadImageForm.get('hour')?.value));

    this.uploadImageGateway.uploadFileWithHour(formData).subscribe(() => {
      console.log("Success!");
    });
  }

  private convertTimeToHours(time: any): string {
    return time.toString().split(":")[0];
  }

}
