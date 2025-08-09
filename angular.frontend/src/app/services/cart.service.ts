import { inject, Injectable, PLATFORM_ID } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { BehaviorSubject, Subject } from 'rxjs';
import { isPlatformBrowser, isPlatformServer } from '@angular/common';
import { StorageService } from './storage.service';
import { LOCAL_STORAGE } from '../tokens';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private storageService: StorageService) {
    if (this.storageService.getItem('cartItems')) {
      let data = JSON.parse(this.storageService.getItem('cartItems')!);

      if (data != null) {
        this.cartItems = data;

        this.computeCartTotals();
      }
    }
  }

  cartItems: CartItem[] = [];

  totalPrice: BehaviorSubject<number> = new BehaviorSubject<number>(0);
  totalQuality: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  addToCart(cartItem: CartItem) {
    let exists: boolean = false;
    let existingCartItem: CartItem | undefined = this.cartItems.find(
      (item) => item.id === cartItem.id
    );

    exists = existingCartItem != undefined;

    if (exists) {
      existingCartItem!.quantity++;
    } else {
      this.cartItems.push(cartItem);
    }

    this.computeCartTotals();
  }

  decrementQuantity(item: CartItem) {
    item.quantity--;

    if (item.quantity === 0) {
      this.remove(item);
    } else this.computeCartTotals();
  }

  remove(item: CartItem) {
    const index = this.cartItems.findIndex((tmpItem) => tmpItem.id === item.id);
    this.cartItems.splice(index, 1);
    this.computeCartTotals();
  }

  computeCartTotals() {
    let totalCost: number = 0;
    let totalQuality: number = 0;

    for (let item of this.cartItems) {
      totalCost += item.quantity * item.unitPrice;
      totalQuality += item.quantity;
    }

    this.totalPrice.next(totalCost);
    this.totalQuality.next(totalQuality);

    this.persistCartItems();
  }

  persistCartItems() {
    this.storageService.setItem('cartItems', JSON.stringify(this.cartItems));
  }

  clearCartItems() {
    this.totalPrice.next(0);
    this.totalQuality.next(0);
  }
}
