import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContentType } from '../content-type';

@Component({
  selector: 'app-content-form',
  templateUrl: './content-form.component.html',
  styleUrls: ['./content-form.component.scss']
})
export class ContentFormComponent {
  contentForm: FormGroup;
  authors: any[] = [];

  formatOptions: string[] = Object.values(ContentType);
  values: string[] | undefined;

  constructor(private fb: FormBuilder) {
    this.contentForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      contentType: ['', Validators.required],
      url: ['', Validators.required],
      authors: [[]],
      subjects: [[]],
      durationMinutes: [0, Validators.min(0)]
    });
  }

  onSubmit() {
    if (this.contentForm.valid) {
      // Process the form data
      console.log(this.contentForm.value);
    }
  }

  onAuthorChange(value: any[]) {
    this.contentForm.get('authors')?.setValue(value);
  }

}
