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

    getNgo(id): Observable<any>{
        let headers = new HttpHeaders().set('Content-Type', "application/json");
        return this._http.get(this.url+'ongs/' + id, {headers:headers});
    }

    register(ngo):Observable<any>{

        let headers = new HttpHeaders().set('Content-Type', "application/json");

        return this._http.post(this.url+"ongs/", ngo, {headers: headers});

    }

    updateNgo(ngo: any): Observable<any> {
      let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " +  this.getLoggedNgoToken());
  
      return this._http.put(this.url+'ongs/', ngo,{headers: headers});
    }

    getLoggedNgoToken(){
      return localStorage.getItem("authorization");
    }
  
  getNgos(page: number) :Observable<any>{
        return this._http.get(this.url+'ongs/?page='+page);
    }
}

