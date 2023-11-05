import { Component, OnInit } from '@angular/core';
import { Content } from '../content';
import { ContentService } from '../content.service';
import { Page } from 'src/app/core/page';
import { PaginatorState } from 'primeng/paginator';

@Component({
  selector: 'app-card-content',
  templateUrl: './card-content.component.html',
  styleUrls: ['./card-content.component.scss']
})
export class CardContentComponent implements OnInit{
  page: Page<Content> = {} as Page<Content>; 
  pageNumber: number = 0;  
  defaultPageSizes: number[] = [12, 8, 4];
  pageSize: number = 12;
  totalElements: number = 0;

  constructor(private service: ContentService) {}

  ngOnInit(): void {
      this.getContents();
  }

  onPageChange(event: PaginatorState) {

    if (event.page !== this.pageNumber || event.rows !== this.pageSize)
    //if (event && event.page !== undefined && event.page > 0 && event.rows !== undefined) 
    {
      this.pageNumber = event.page ?? this.pageNumber;
      this.pageSize = event.rows ?? this.pageSize;
      this.getContents(); 
    }
}

  getContents() {
    this.service.getContents(this.pageNumber, this.pageSize).subscribe((response) => {
      this.page = response;
      this.totalElements = response.totalElements;
    })
  }
}
