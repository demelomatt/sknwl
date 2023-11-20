import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Language } from './language';
import { Observable } from 'rxjs';
import { Currency } from './currency';


@Injectable({
  providedIn: 'root'
})
export class CoreService {
  private url = environment.core;

  constructor(private http: HttpClient) { }

  getLanguages(search?: string): Observable<Language[]>  {
    const apiUrl = this.url + "/languages";
    let params = new HttpParams();

    if (search !== undefined && search !== null) {
      params = params.set('search', search);
    }

    return this.http.get<Language[]>(apiUrl, { params });
  }

  getCurrencies(code?: string): Observable<Currency[]>  {
    const apiUrl = this.url + "/currencies";
    let params = new HttpParams();

    if (code !== undefined && code !== null) {
      params = params.set('code', code);
    }

    return this.http.get<Currency[]>(apiUrl, { params });
  }
}
