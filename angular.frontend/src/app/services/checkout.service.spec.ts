import { TestBed } from '@angular/core/testing';

import { CheckoutDataServiceService } from './checkout.service';

describe('CheckoutDataServiceService', () => {
  let service: CheckoutDataServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckoutDataServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
