import { CurrencyPipe, NgFor, NgIf } from '@angular/common';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CartService } from '../../services/cart.service';
import { FormService } from '../../services/form.service';
import { Country } from '../../common/country';
import { State } from '../../common/state';
import { CheckoutDataService } from '../../services/checkout.service';
import { CustomValidator } from '../../validators/custom-validator';
import { CheckoutAPIService } from '../../services/checkout-api.service';
import { Router } from '@angular/router';
import { Order } from '../../common/order';
import { OrderItem } from '../../common/order-item';
import { Purchase } from '../../common/purchase';
import { error } from 'console';
import { CustomService } from '../../services/custom.service';
import { Address } from '../../common/address';
import { StorageService } from '../../services/storage.service';
import { stat } from 'fs';

@Component({
  selector: 'app-checkout',
  imports: [ReactiveFormsModule, CurrencyPipe, NgFor, NgIf],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css',
})
export class CheckoutComponent implements OnInit {
  checkoutFormGroup!: FormGroup;
  totalQuantity: number = 0;
  totalPrice: number = 0;

  formMonths: number[] = [];
  formYears: number[] = [];
  currentMonth: number = new Date().getMonth() + 1;
  currentYear: number = new Date().getFullYear();

  countries: Country[] = [];
  shipmentStates: State[] = [];
  billingStates: State[] = [];

  name: string | undefined;
  surname: string | undefined;
  emailUser: string | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private cartService: CartService,
    private formService: FormService,
    private checkoutDataService: CheckoutDataService,
    private checkoutApi: CheckoutAPIService,
    private router: Router,
    private customService: CustomService,
    private storageService: StorageService
  ) {}

  ngOnInit(): void {
    const username = this.storageService.getItem<string>('username') ?? '';
    this.emailUser = this.storageService.getItem<string>('email') ?? '';
    this.name = username ? username.split(' ')[0] : '';
    this.surname = username ? username.split(' ')[1] : '';

    this.reviewCartDetails();

    this.getAPIData();

    this.createForm();
  }

  reviewCartDetails() {
    this.cartService.totalQuality.subscribe(
      (val) => (this.totalQuantity = val)
    );

    this.cartService.totalPrice.subscribe((val) => (this.totalPrice = val));
  }

  getAPIData() {
    this.formService.getCreditCardMonths(this.currentMonth).subscribe((val) => {
      this.formMonths = val;
    });

    this.formService.getCreditCardYears(this.currentYear).subscribe((val) => {
      this.formYears = val;
    });

    this.checkoutDataService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });

    this.customService.getAddress().subscribe((res) => {
      if (res) {
        this.checkoutFormGroup.controls['shipmentAddress'].patchValue({
          state: res.state,
          street: res.street,
          city: res.city,
          country: res.country,
          zipCode: res.zipCode,
        });
      }
    });
  }

  createForm() {
    this.checkoutFormGroup = new FormGroup({
      customer: this.formBuilder.group({
        firstName: new FormControl(capitalize(this.name!) ?? '', [
          Validators.required,
          Validators.minLength(2),
          CustomValidator.notOnlyWhiteSpace,
        ]),
        lastName: new FormControl(capitalize(this.surname!) ?? '', [
          Validators.required,
          Validators.minLength(2),
        ]),
        email: new FormControl(this.emailUser ?? '', [
          Validators.required,
          Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$'),
        ]),
      }),
      shipmentAddress: this.formBuilder.group({
        street: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidator.notOnlyWhiteSpace,
        ]),
        city: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidator.notOnlyWhiteSpace,
        ]),
        state: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required]),
      }),
      creditCard: this.formBuilder.group({
        cardType: [''],
        nameOnCard: [''],
        cardNumber: [''],
        securityCode: [''],
        expirationMonth: [''],
        expirationYear: [''],
      }),

      billingAddress: this.formBuilder.group({
        street: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
        ]),
        city: new FormControl('', [
          Validators.required,
          Validators.minLength(2),
          CustomValidator.notOnlyWhiteSpace,
        ]),
        state: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required]),
      }),
    });
  }

  handleMonthsAndYear() {
    let yearChosen = parseInt(
      this.checkoutFormGroup.controls['creditCard'].value['expirationYear']
    );
    if (yearChosen !== this.currentYear) {
      this.formService.getCreditCardMonths(1).subscribe((val) => {
        this.formMonths = val;
      });
    } else {
      this.formService
        .getCreditCardMonths(this.currentMonth)
        .subscribe((val) => {
          this.formMonths = val;
        });
    }
  }

  onShipmentCountryChange() {
    let country =
      this.checkoutFormGroup.controls['shipmentAddress'].value['country'];
    if (this.countries.find((o) => o.name === country)) {
      let code = this.countries.find((val) => val.name === country)?.code;
      this.checkoutDataService.getAllStates(code!).subscribe((data) => {
        this.shipmentStates = data;
      });
    }
  }

  onBillingCountryChange() {
    let country =
      this.checkoutFormGroup.controls['billingAddress'].value['country'];
    if (this.countries.find((o) => o.name === country)) {
      let code = this.countries.find((val) => val.name === country)?.code;
      this.checkoutDataService.getAllStates(code!).subscribe((data) => {
        this.billingStates = data;
      });
    }
  }

  copyShippingAddressToBillingAddress(event: any) {
    if (event!.target!.checked) {
      let country =
        this.checkoutFormGroup.controls['shipmentAddress'].value['country'];
      if (this.countries.find((o) => o.name === country)) {
        let code = this.countries.find((val) => val.name === country)?.code;

        this.checkoutDataService.getAllStates(code!).subscribe({
          next: (data) => {
            this.billingStates = data;
            setTimeout(() => {
              this.copyShipmentToBillingAddress();
            }, 200);
          },
        });
      }
    } else this.checkoutFormGroup.controls['billingAddress'].reset();
  }

  copyShipmentToBillingAddress() {
    this.checkoutFormGroup.controls['billingAddress'].setValue(
      this.checkoutFormGroup.controls['shipmentAddress'].value
    );
  }

  onSubmitHandler() {
    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }

    let order = new Order(this.totalQuantity, this.totalPrice);

    const cartItems = this.cartService.cartItems;

    let orderItems = cartItems.map((item) => new OrderItem(item));

    let purchase = new Purchase();

    purchase.customer = this.checkoutFormGroup.controls['customer'].value;
    purchase.shippingAddress =
      this.checkoutFormGroup.controls['shipmentAddress'].value;
    const shippingState: State = JSON.parse(
      JSON.stringify(purchase.shippingAddress.state)
    );
    const shippingCountry: Country = JSON.parse(
      JSON.stringify(purchase.shippingAddress.country)
    );
    purchase.shippingAddress.state = shippingState.name;
    purchase.shippingAddress.country = shippingCountry.name;

    purchase.billingAddress =
      this.checkoutFormGroup.controls['billingAddress'].value;
    const billingState: State = JSON.parse(
      JSON.stringify(purchase.billingAddress.state)
    );
    const billingCountry: Country = JSON.parse(
      JSON.stringify(purchase.billingAddress.country)
    );
    purchase.billingAddress.state = billingState.name;
    purchase.billingAddress.country = billingCountry.name;

    purchase.order = order;
    purchase.orderItems = orderItems;

    console.log('Purchase is ', purchase);

    this.checkoutApi.placeOrder(purchase).subscribe({
      next: (response) => {
        console.log(`Tracking number is ${response.orderTrackingNumber}`);
        this.resetCart();
      },
      error: (err) => {
        alert(`There was an error: ${err.message}`);
      },
    });
  }

  resetCart() {
    this.cartService.cartItems = [];
    this.cartService.totalPrice.next(0);
    this.cartService.totalQuality.next(0);

    this.checkoutFormGroup.reset();

    this.router.navigateByUrl('/products');
  }

  get firstName() {
    return this.checkoutFormGroup.get('customer.firstName');
  }
  get lastName() {
    return this.checkoutFormGroup.get('customer.lastName');
  }
  get email() {
    return this.checkoutFormGroup.get('customer.email');
  }

  get shippingAddressStreet() {
    return this.checkoutFormGroup.get('shipmentAddress.street');
  }
  get shippingAddressCity() {
    return this.checkoutFormGroup.get('shipmentAddress.city');
  }
  get shippingAddressState() {
    return this.checkoutFormGroup.get('shipmentAddress.state');
  }
  get shippingAddressZipCode() {
    return this.checkoutFormGroup.get('shipmentAddress.zipCode');
  }
  get shippingAddressCountry() {
    return this.checkoutFormGroup.get('shipmentAddress.country');
  }

  get billingAddressStreet() {
    return this.checkoutFormGroup.get('billingAddress.street');
  }
  get billingAddressCity() {
    return this.checkoutFormGroup.get('billingAddress.city');
  }
  get billingAddressState() {
    return this.checkoutFormGroup.get('billingAddress.state');
  }
  get billingAddressZipCode() {
    return this.checkoutFormGroup.get('billingAddress.zipCode');
  }
  get billingAddressCountry() {
    return this.checkoutFormGroup.get('billingAddress.country');
  }
}

const capitalize = (s: string) => {
  return s && String(s[0]).toUpperCase() + String(s).slice(1);
};
