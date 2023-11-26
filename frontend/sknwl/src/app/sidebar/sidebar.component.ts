import { Component, ElementRef, EventEmitter, OnInit, Output } from '@angular/core';
import { ComponentProperties } from '../component-properties';
import { ContentType } from '../content/content-type';
import { ContentService } from '../content/content.service';
import { CostType } from '../content/cost-type';
import { CoreService } from '../core/core.service';
import { Language } from '../core/language';
import { Source } from '../core/source';
import { HostListener } from '@angular/core';


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
    if (window.innerWidth < 600) {
      this.visible = false;
    }
  }

  showMenu() {
    this.visible = !this.visible;
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
