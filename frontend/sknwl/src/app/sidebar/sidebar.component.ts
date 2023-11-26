import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ContentType } from '../content/content-type';
import { Language } from '../core/language';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { CoreService } from '../core/core.service';
import { CostType } from '../content/cost-type';
import { ComponentProperties } from '../component-properties';

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

  filteredLanguages: Language[] = [];
  selectedLanguages: Language[] = [];

  minDuration: number = 10;
  maxDuration: number = 600;

  costOptions: ComponentProperties[] = [
    { key: 'FREE', value: CostType.FREE, id: 'free'},
    { key: 'PAID', value: CostType.PAID, id: 'paid'}
  ];
  selectedCostOptions: ComponentProperties[] = this.costOptions;

  @Output() formatsChanged = new EventEmitter<string[]>();
  @Output() languagesChanged = new EventEmitter<Language[]>();
  @Output() durationChanged = new EventEmitter<Map<string, number>>();
  @Output() costChanged = new EventEmitter<any[]>();

  constructor(private coreService: CoreService) {}

  ngOnInit() {
  }

  onTypesChange() {
    this.formatsChanged.emit(this.selectedFormats);
  }

  onLanguagesChange() {
    this.languagesChanged.emit(this.selectedLanguages);
  }

  onDurationChange() {
    let durationMap = new Map<string, number>([
      ["min", this.minDuration],
      ["max", this.maxDuration]
  ]);

    this.durationChanged.emit(durationMap);
  }

  onCostChange() {
    this.costChanged.emit(this.selectedCostOptions);
  }

  filterLanguage(event: { query: any; }) {
    let query = event.query;

    this.coreService.getLanguages(query).subscribe((response) => {
      this.filteredLanguages = response;
    })
  };
}
