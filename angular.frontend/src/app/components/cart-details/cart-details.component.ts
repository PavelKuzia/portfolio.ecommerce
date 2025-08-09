import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../common/cart-item';
import { CurrencyPipe, NgFor, NgIf } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-cart-details',
  imports: [NgFor, CurrencyPipe, NgIf, RouterLink],
  templateUrl: './cart-details.component.html',
  styleUrl: './cart-details.component.css'
})

export class CartDetailsComponent implements OnInit {
  cartItems: CartItem[] = [];
  totalPrice: number = 0;
  totalQuantity: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.listCartDetails();
  }

  listCartDetails() {
    this.cartItems = this.cartService.cartItems;

    this.cartService.totalPrice.subscribe(val => {
      this.totalPrice = val;
    })

    this.cartService.totalQuality.subscribe(val => {
      this.totalQuantity = val;
    })

    this.cartService.computeCartTotals();
  }

  onAddhandler(cartItem: CartItem) {
    this.cartService.addToCart(cartItem);
  }

  onDecrementHandler(cartItem: CartItem) {
    this.cartService.decrementQuantity(cartItem);
  }

  onRemovehandler(cartItem: CartItem) {
    this.cartService.remove(cartItem);
  }
}
