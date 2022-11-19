import {TestBed} from '@angular/core/testing';

import {UploadImageGatewayService} from './upload-image-gateway.service';

describe('UploadImageGatewayService', () => {
  let service: UploadImageGatewayService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UploadImageGatewayService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
