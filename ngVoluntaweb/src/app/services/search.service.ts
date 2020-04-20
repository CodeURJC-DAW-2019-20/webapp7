import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

@Injectable()
export class SearchService {
    public url: string;
    constructor(
        public _http: HttpClient
    ) {
        this.url = global.url;
    }

    getSearch(page: number): Observable<any> {
        let headers = new HttpHeaders().set('Content-Type', "application/json");
        return this._http.get(this.url + 'search?page=' + page, { headers: headers });
    }

    getByWord(word: string): Observable<any> {
        let headers = new HttpHeaders().set('Content-Type', "application/json");
        return this._http.get(this.url + 'search/?keyword=' + word, { headers: headers });
    }

    getByCategory(category: number): Observable<any> {
        let headers = new HttpHeaders().set('Content-Type', "application/json");
        return this._http.get(this.url + 'search/?category=' + category, { headers: headers });
    }
}