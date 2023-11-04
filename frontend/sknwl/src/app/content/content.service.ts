import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Page } from '../core/page';
import { Content } from './content';
import { ContentSortType } from './content-sort-type';
import { ContentType } from './content-type';

@Injectable({
  providedIn: 'root'
})
export class ContentService {
  private url = environment.content;

  constructor(private http: HttpClient) { }

  getContents(
    pageNumber: number, 
    pageSize: number, 
    sort?: ContentSortType, 
    keyphrase?: string, 
    minRatings?: number, 
    contentTypes?: ContentType[],
    sourceId?: number,
    languageId?: number,
    minDuration?: number,
    maxDuration?: number
    ): Observable<Page<Content>> {
      let params = new HttpParams();
      return this.http.get<Page<Content>>(this.url);
  }
}
