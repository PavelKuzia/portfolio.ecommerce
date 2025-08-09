import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CurrencyPipe } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-cart-status',
  imports: [CurrencyPipe, RouterLink, RouterLinkActive],
  templateUrl: './cart-status.component.html',
  styleUrl: './cart-status.component.css'
})
export class CartStatusComponent implements OnInit {
  public totalSum: number = 0;
  public totalAmount: number = 0;

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.cartService.totalPrice.subscribe((val) => {
      this.totalSum = val;
    })
    this.cartService.totalQuality.subscribe((val) => {
      this.totalAmount = val;
    })
  }
}
