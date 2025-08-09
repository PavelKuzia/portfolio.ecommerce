import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ActivatedRoute, RouterLink, RouterLinkActive } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { CurrencyPipe, NgIf } from '@angular/common';
import { CartItem } from '../../common/cart-item';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-product-details',
  imports: [NgIf, CurrencyPipe, RouterLink, RouterLinkActive],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})

export class ProductDetailsComponent implements OnInit {
  product!: Product;

  constructor(private route: ActivatedRoute,
    private service: ProductService, private cartService: CartService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.handleProductDetails();
    })
  }

  handleProductDetails() {
    const id: number = +this.route.snapshot.paramMap.get('id')!;
    this.service.getProductDetails(id).subscribe(data => {
      this.product = data;
    })
  }

  onAddToCardHandler(item: Product) {
      let cartItem: CartItem = new CartItem(item);
      this.cartService.addToCart(cartItem);
    }
}
