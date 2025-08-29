import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ProductCategoryMenuComponent } from './components/product-category-menu/product-category-menu.component';
import { SearchComponent } from './components/search/search.component';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CartStatusComponent } from './components/cart-status/cart-status.component';
import { LoginStatusComponent } from './components/login-status/login-status.component';
import { environment } from '../environments/environment';
import { StorageService } from './services/storage.service';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    ProductCategoryMenuComponent,
    SearchComponent,
    CartStatusComponent,
    NgbPaginationModule,
    RouterLink,
    LoginStatusComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  constructor(private storageService: StorageService) {
    this.storageService.cleanStorage();
  }

  title = 'angular-ecommerce';
  ngOnInit(): void {
    console.log('🎉 Application has started successfully!');
    environment.production
      ? console.log('Running in production')
      : console.log('Running in development');
    console.log(`Backend API is ${environment.API_URL}`);
  }
}
