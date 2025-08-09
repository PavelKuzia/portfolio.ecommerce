import { Component, OnInit } from '@angular/core';
import { OrderHistory } from '../../common/order-history';
import { OrderHistoryService } from '../../services/order-history.service';
import { StorageService } from '../../services/storage.service';
import { DatePipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-order-history',
  imports: [NgIf, NgFor, DatePipe],
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.css',
})
export class OrderHistoryComponent implements OnInit {
  constructor(
    private orderHistoryService: OrderHistoryService,
    private storageService: StorageService
  ) {}

  orderHistoryList: OrderHistory[] = [];

  ngOnInit(): void {
    this.handleOrderHistory();
  }

  handleOrderHistory() {
    const email = this.storageService.getItem<string>('email')!;

    this.orderHistoryService
      .getOrderHistory(email)
      .subscribe((data) => (this.orderHistoryList = data._embedded.orders));
  }
}
