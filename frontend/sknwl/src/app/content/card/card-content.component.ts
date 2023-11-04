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
  pageSize: number = 10;

  constructor(private service: ContentService) {}

  ngOnInit(): void {
      this.getContents(this.pageNumber, this.pageSize);
  }

  onPageChange(event: PaginatorState) {
    if (event && event.first !== undefined && event.rows !== undefined) {
      this.getContents(event.first, event.rows);
    }
}

  getContents(pageNumber: number, pageSize: number) {
    this.service.getContents(pageNumber, pageSize).subscribe((response) => {
      this.page = response;
    })
  }
}
