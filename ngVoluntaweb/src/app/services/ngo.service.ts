import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';
import { NGO } from '../models/ngo';

@Injectable()
export class NgoService{
    public url: string;
    public ngo: NGO;
    constructor(
        public _http: HttpClient
    ){
        this.url = global.url;
    }

    register(ngo):Observable<any>{

        let headers = new HttpHeaders().set('Content-Type', "application/json");

        return this._http.post(this.url+"ongs/", ngo, {headers: headers});

    }


}