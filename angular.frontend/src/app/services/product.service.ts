import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { map } from 'rxjs';
import { ProductCategory } from '../common/product-category';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  public baseUrl = `${environment.API_URL}/api`;

  constructor(private HttpClient: HttpClient) {}

  getProductCategoryList(): Observable<ProductCategory[]> {
    const searchUrl: string = `${this.baseUrl}/product_category`;
    return this.HttpClient.get<GetResponseProductCategory>(searchUrl).pipe(
      map((response) => {
        return response._embedded.productCategory;
      })
    );
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.HttpClient.get<GetResponseProduct>(searchUrl).pipe(
      map((response) => response._embedded.products)
    );
  }

  getProductList(categoryId: number): Observable<Product[]> {
    const searchUrl: string = `${this.baseUrl}/products/search/findByCategoryId?id=${categoryId}`;
    return this.getProducts(searchUrl);
  }

  getProductListPaginate(
    page: number,
    size: number,
    categoryId: number
  ): Observable<GetResponseProductsPaginate> {
    const searchUrl: string =
      `${this.baseUrl}/products/search/findByCategoryId?id=${categoryId}` +
      `&page=${page}&size=${size}`;
    return this.HttpClient.get<GetResponseProductsPaginate>(searchUrl);
  }

  getProductDetails(id: number): Observable<Product> {
    const searchUrl: string = `${this.baseUrl}/products/${id}`;
    return this.HttpClient.get<Product>(searchUrl);
  }

  searchByKeyword(keyword: string): Observable<Product[]> {
    const searchUrl: string = `${this.baseUrl}/products/search/findByNameIgnoreCaseContaining?name=${keyword}`;
    return this.getProducts(searchUrl);
  }
}

interface GetResponseProduct {
  _embedded: {
    products: Product[];
  };
}

interface GetResponseProductsPaginate {
  _embedded: {
    products: Product[];
  };
  page: {
    size: number;
    totalElements: number;
    totalPages: number;
    number: number;
  };
}

interface GetResponseProductCategory {
  _embedded: {
    productCategory: ProductCategory[];
  };
}
