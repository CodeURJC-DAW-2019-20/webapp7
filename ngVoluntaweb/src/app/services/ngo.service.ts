import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
    providedIn: 'root'
})
export class NgoService{
    public url: string;

    constructor(
        public _http: HttpClient
    ){
        this.url = 'https://localhost:8443/api/';
    }

    getNgos(page: number) :Observable<any>{
        return this._http.get(this.url+'ongs/?page='+page);
    }
}
