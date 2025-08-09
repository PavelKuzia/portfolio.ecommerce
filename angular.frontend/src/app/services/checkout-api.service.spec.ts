import { TestBed } from '@angular/core/testing';

import { CheckoutAPIService } from './checkout-api.service';

describe('CheckoutAPIService', () => {
  let service: CheckoutAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckoutAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
