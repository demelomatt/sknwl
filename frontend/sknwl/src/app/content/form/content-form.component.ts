import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContentType } from '../content-type';
import { LanguageService } from 'src/app/core/language.service';
import { Language } from 'src/app/core/language';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';
import { StudyField } from '../study-field';
import { ContentService } from '../content.service';
import { Content } from '../content';

@Component({
  selector: 'app-content-form',
  templateUrl: './content-form.component.html',
  styleUrls: ['./content-form.component.scss']
})
export class ContentFormComponent implements OnInit{
  contentForm: FormGroup;
  authors: string[] | undefined;

  formatOptions: string[] = Object.values(ContentType);
  filteredLanguages: Language[] = [];

  private languageSearchTerms = new Subject<string>();
  private fieldSearchTerms = new Subject<string>();

  studyFields: StudyField[] = [];

  constructor(private fb: FormBuilder, private languageService: LanguageService, private contentService: ContentService) {
    this.contentForm = this.fb.group({
      title: [''],
      description: [''],
      contentType: [''],
      url: [''],
      authors: [[]],
      subjects: [[]],
      languages: [[]],
      studyFields: [[]],
      durationMinutes: [0],
      price: [0]
    });
  }

  ngOnInit() {
    this.languageSearchTerms
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(query => this.languageService.getLanguages(query))
      )
      .subscribe(languages => { 
        this.filteredLanguages = languages;
      });

      this.fieldSearchTerms
      .pipe(
        debounceTime(300),
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

  filterField(event: { query: any; }) {
    let query = event.query;
    this.fieldSearchTerms.next(query);
  };

  onSubmit() {
    if (this.contentForm.valid) {
    } else {
      console.log("Preencha todos os campos");
    }

  }
}
