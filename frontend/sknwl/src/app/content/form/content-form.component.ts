import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContentType } from '../content-type';
import { LanguageService } from 'src/app/core/language.service';
import { Language } from 'src/app/core/language';
import { Subject, debounceTime, distinctUntilChanged, switchMap } from 'rxjs';

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
  private searchTerms = new Subject<string>();

  ngOnInit() {
    this.searchTerms
      .pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(query => this.languageService.getLanguages(query))
      )
      .subscribe(languages => { 
        this.filteredLanguages = languages;
      });
  }


  constructor(private fb: FormBuilder, private languageService: LanguageService) {
    this.contentForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      contentType: ['', Validators.required],
      url: ['', Validators.required],
      authors: [[]],
      subjects: [[]],
      languages: [[]],
      durationMinutes: [0, Validators.min(0)]
    });
  }

  filter(event: { query: any; }) {
    let query = event.query;
    this.searchTerms.next(query);
  };


  onSubmit() {
    if (this.contentForm.valid) {
      // Process the form data
      console.log(this.contentForm.value);
    }
  }
}
