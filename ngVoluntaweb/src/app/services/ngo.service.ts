import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global'

@Injectable()
export class NgoService{
    public url: string;
    constructor(
        public _http: HttpClient
    ){
        this.url = global.url;
    }

    getNgo(id): Observable<any>{
        return this._http.get(this.url+'ongs/' + id)
    }
}