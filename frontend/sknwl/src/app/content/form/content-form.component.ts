import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { CoreService } from 'src/app/core/core.service';
import { Currency } from 'src/app/core/currency';
import { Language } from 'src/app/core/language';
import { Content } from '../content';
import { ContentType } from '../content-type';
import { ContentService } from '../content.service';
import { Cover } from '../cover';
import { StudyField } from '../study-field';

@Component({
  selector: 'app-content-form',
  templateUrl: './content-form.component.html',
  styleUrls: ['./content-form.component.scss']
})
export class ContentFormComponent implements OnInit{
  @Output() submitEvent = new EventEmitter<void>();
  @Output() messageEvent = new EventEmitter<{ severity: string, summary: string, detail: string }>();

  contentForm: FormGroup;
  authors: string[] | undefined;

  formatOptions: string[] = Object.values(ContentType);
  filteredLanguages: Language[] = [];
  filteredCurrencies: Currency[] = [];
  selectedCurrency: Currency = {} as Currency;
  selectedField: StudyField = {} as StudyField;

  private languageSearchTerms = new Subject<string>();
  private currencySearchTerms = new Subject<string>();
  private fieldSearchTerms = new Subject<string>();

  studyFields: StudyField[] = [];

  constructor(private fb: FormBuilder, private coreService: CoreService, private contentService: ContentService) {
    this.contentForm = this.fb.group({
      title: ['', [Validators.required]],
      description: [''],
      contentType: [ContentType.COURSE, [Validators.required]],
      url: ['', [Validators.required]],
      authors: [[]],
      subjects: [[]],
      language: [{}, [Validators.required]],
      currency: [{}], 
      studyField: [{} as StudyField, [Validators.required]],
      durationMinutes: [0],
      price: [0.0]
    });
  }

  ngOnInit() {
    this.languageSearchTerms
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        switchMap(query => this.coreService.getLanguages(query))
      )
      .subscribe(languages => { 
        this.filteredLanguages = languages;
      });

    this.currencySearchTerms
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        switchMap(query => this.coreService.getCurrencies(query))
      )
      .subscribe(currencies => { 
        this.filteredCurrencies = currencies;
      });

      this.fieldSearchTerms
      .pipe(
        debounceTime(500),
        distinctUntilChanged(),
        switchMap(query => this.contentService.getStudyFields(query))
      )
      .subscribe(fields => { 
        this.studyFields = fields;
      });
  }

  filterLanguage(event: { query: any; }) {
    let query = event.query;
    this.languageSearchTerms.next(query);
  };

  filterCurrency(event: { query: any; }) {
    let query = event.query;
    this.currencySearchTerms.next(query);
  };

  filterField(event: { query: any; }) {
    let query = event.query;
    this.fieldSearchTerms.next(query);
  };

  onSubmit() {
    if (this.contentForm.valid) {
      console.log(this.contentForm)
      const contentTypeValue = (this.contentForm.value.contentType as string).toLowerCase();
      const contentTypeKey = Object.keys(ContentType).find(key => ContentType[key as keyof typeof ContentType].toLowerCase() === contentTypeValue) as keyof typeof ContentType;
      const content: Content = {
        title: this.contentForm.value.title,
        description: this.contentForm.value.description,
        contentType: contentTypeKey as ContentType,
        url: this.contentForm.value.url,
        authors: this.contentForm.value.authors,
        subjects: this.contentForm.value.subjects,
        language: this.contentForm.value.language,
        studyField: Object.hasOwn(this.selectedField , 'id') ? this.selectedField : {name: this.contentForm.value.studyField},
        durationMinutes: this.contentForm.value.durationMinutes,
        price: {amount: this.contentForm.value.price, currency: {id: 18, code: 'BRL'}},
        publisher: {
          id: 1,
          name: 'System User'
        },
        coverImage: {} as Cover
      };
      this.contentService.publishContent(content).subscribe(
        (response) => {
          const message = { severity: 'success', summary: 'Sucesso', detail: 'Conteúdo criado com sucesso.' };
          this.messageEvent.emit(message);
        },
        (error) => {
          const message = { severity: 'error', summary: 'Erro', detail: 'Erro ao criar conteúdo: ' + error.message};
          this.messageEvent.emit(message);
        }
      );
      this.submitEvent.emit();
    } else {
      console.log("Preencha todos os campos");
    }

  }
}
