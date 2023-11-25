import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ContentType } from '../content/content-type';
import { Language } from '../core/language';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { CoreService } from '../core/core.service';

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
  private languageSearchTerms = new Subject<string>();


  @Output() formatsChanged = new EventEmitter<string[]>();
  @Output() languagesChanged = new EventEmitter<Language[]>();

  constructor(private coreService: CoreService) {}

  ngOnInit() {
  }

  onTypesChange() {
    this.formatsChanged.emit(this.selectedFormats);
  }

  onLanguagesChange() {
    this.languagesChanged.emit(this.selectedLanguages);
  }

  filterLanguage(event: { query: any; }) {
    let query = event.query;

    this.coreService.getLanguages(query).subscribe((response) => {
      this.filteredLanguages = response;
    })
  };
}
