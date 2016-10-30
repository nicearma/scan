/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { ScanService } from '../services/scan.service';

describe('Service: ScanService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ScanService]
    });
  });

  it('should ...', inject([ScanService], (service: ScanService) => {
    expect(service).toBeTruthy();
  }));
});
