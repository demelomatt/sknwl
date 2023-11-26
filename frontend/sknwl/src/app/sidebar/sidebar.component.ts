import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ContentType } from '../content/content-type';
import { Language } from '../core/language';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { CoreService } from '../core/core.service';
import { CostType } from '../content/cost-type';
import { ComponentProperties } from '../component-properties';
import { ContentService } from '../content/content.service';
import { Source } from '../core/source';

interface Sort {
  type: string;
}

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit{

  visible: boolean = true;

  sortValues: Sort[] | undefined;
  selectedSort: Sort | undefined;

  formatOptions: string[] = Object.values(ContentType);
  selectedFormats!: string[];

  filteredLanguages: Language[] = [];
  selectedLanguages: Language[] = [];

  filteredSources: Source[] = [];
  selectedSources: Source[] = [];

  filteredFields: {id: number, name: string}[]= [];
  selectedFields: {id: number, name: string}[]= [];

  minDuration: number = 10;
  maxDuration: number = 600;

  costOptions: ComponentProperties[] = [
    { key: 'FREE', value: CostType.FREE, id: 'free'},
    { key: 'PAID', value: CostType.PAID, id: 'paid'}
  ];
  selectedCostOptions: ComponentProperties[] = this.costOptions;

  @Output() formatsChanged = new EventEmitter<string[]>();
  @Output() languagesChanged = new EventEmitter<Language[]>();
  @Output() sourcesChanged = new EventEmitter<Source[]>();
  @Output() durationChanged = new EventEmitter<Map<string, number>>();
  @Output() costChanged = new EventEmitter<any[]>();
  @Output() fieldsChanged = new EventEmitter<{id: number, name: string}[]>();


  constructor(private coreService: CoreService, private contentService: ContentService) {}

  ngOnInit() {
  }

  onTypesChange() {
    this.formatsChanged.emit(this.selectedFormats);
  }

  onLanguagesChange() {
    this.languagesChanged.emit(this.selectedLanguages);
  }

  onSourcesChange() {
    this.sourcesChanged.emit(this.selectedSources);
  }

  onFieldsChange() {
    this.fieldsChanged.emit(this.selectedFields);
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

  filterSource(event: { query: any; }) {
    let query = event.query;

    this.coreService.getSources(query).subscribe((response) => {
      this.filteredSources = response;
    })
  };

  filterField(event: { query: any; }) {
    let query = event.query;

    this.contentService.getStudyFields(query).subscribe((response) => {
      this.filteredFields = response;
    })
  };
}
