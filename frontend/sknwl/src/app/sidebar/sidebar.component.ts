import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ContentType } from '../content/content-type';

interface Sort {
  type: string;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit{
  sortValues: Sort[] | undefined;
  selectedSort: Sort | undefined;

  formatOptions: string[] = Object.values(ContentType);
  selectedFormats!: string[];

  @Output() formatsChanged = new EventEmitter<string[]>();

  ngOnInit() {
  }

  onTypesChange() {
    this.formatsChanged.emit(this.selectedFormats);
  }
}
