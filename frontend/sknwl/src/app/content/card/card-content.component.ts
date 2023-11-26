import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Content } from '../content';
import { ContentService } from '../content.service';
import { Page } from 'src/app/core/page';
import { ContentParams } from '../content-params';
import { PaginatorState } from 'primeng/paginator';
import { ContentSortType } from '../content-sort-type';
import { ContentType } from '../content-type';
import { Language } from 'src/app/core/language';
import { CostType } from '../cost-type';
import { ComponentProperties } from 'src/app/component-properties';
import { Source } from 'src/app/core/source';

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

  private isFirstChange = true;
  @Input() searchValue?: string;
  @Input() sort?: string;
  @Input() types?: string[];
  @Input() languages?: Language[];
  @Input() sources?: Source[];
  @Input() duration?: Map<string, number>;
  @Input() costTypes?: ComponentProperties[];
  @Input() fields?: {id: number, name: string}[];

  constructor(private service: ContentService) {}

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.isFirstChange) {
      this.isFirstChange = false;
      return;
    }
    this.getContents();
    // Check if 'myProperty' has changed
    /*
    if (changes['searchValue']) {
      // Call your method here
      this.getContents();
    }
    */
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
    const contentParams: Partial<ContentParams> = {
      pageNumber: this.pageNumber,
      pageSize: this.pageSize,
      keyphrase: this.searchValue,
      sort: this.getEnumKeyByValue(this.sort) as ContentSortType,
      contentTypes: this.types?.map((value: string) => {
        return Object.keys(ContentType).find(key => ContentType[key as keyof typeof ContentType] === value) as keyof typeof ContentType
    }) as unknown as ContentType[],
      languageIds: this.languages?.map(lang => lang.id),
      sourceIds: this.sources?.map(source => source.id),
      fields: this.fields?.map(field => field.name),
      minDuration: this.duration?.get('min'),
      maxDuration: this.duration?.get('max'),
      costTypes: this.costTypes?.map((type: ComponentProperties) => {
        return Object.keys(CostType).find(key => CostType[key as keyof typeof CostType] === type.value) as keyof typeof CostType;
      }) as unknown as CostType[],
    };

    console.log(this.costTypes);

    this.service.getContents(contentParams as ContentParams).subscribe((response) => {
      this.page = response;
      this.totalElements = response.totalElements;
    })
  }

  openExternalLink(url: string) {
    window.open(url, '_blank');
  }

  private getEnumKeyByValue(value: any): keyof typeof ContentSortType {
    const keys = Object.keys(ContentSortType) as (keyof typeof ContentSortType)[];
    for (const key of keys) {
      if (ContentSortType[key] === value) {
        return key;
      }
    }
    return "LATEST";
  }

  private getContentTypeKey(value: ContentType): ContentType {
    if (Object.values(ContentType).includes(value)) {
      return value;
    }
    return {} as ContentType;
  }
}
