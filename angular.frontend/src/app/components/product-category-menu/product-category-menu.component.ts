import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { ProductService } from '../../services/product.service';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-product-category-menu',
  imports: [RouterLink, RouterLinkActive, NgFor],
  templateUrl: './product-category-menu.component.html',
  styleUrl: './product-category-menu.component.css'
})

export class ProductCategoryMenuComponent implements OnInit {
  productCategories: ProductCategory[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.listProductCategories();
  }
  
  listProductCategories() {
    this.productService.getProductCategoryList().subscribe(data => {
      this.productCategories = data;
    })
  }
}
