import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UploadImageGatewayService {

  private readonly apiUrl = "http://localhost:8080/file/upload";

  constructor(private http: HttpClient) {
  }

  uploadFileWithHour(formData: FormData): Observable<any> {
    return this.http.put<any>(this.apiUrl, formData);
  }
}
