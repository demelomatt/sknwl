import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../core/page';
import { Content } from './content';
import { ContentParams } from './content-params';
import { StudyField } from './study-field';

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private url = environment.content;

  constructor(private http: HttpClient) { }

  private convertToHttpParams(contentParams: ContentParams): HttpParams {
    let params = new HttpParams();

    // Iterate through the fields of the interface and add them to the params if they exist in the data object
    Object.entries(contentParams).forEach(([key, value]) => {
      if (value !== undefined && value !== null) {
        if (Array.isArray(value)) {
          // If the value is an array, serialize it as a comma-separated string
          value.forEach(item => {
            params = params.append(key, item.toString());
          });
        } else {
          params = params.set(key, value.toString());
        }
      }
    });

    return params;
  }

  getContents(contentParams: ContentParams): Observable<Page<Content>> {
    const params = this.convertToHttpParams(contentParams);
    return this.http.get<Page<Content>>(this.url, { params });
  }

  getStudyFields(field: string) {
    const apiUrl = environment.field + "/name/" + field;
    return this.http.get<StudyField[]>(apiUrl);
  }

  registerContent(content: Content) {
    return this.http.post<Content>(this.url, content);
  }
}
