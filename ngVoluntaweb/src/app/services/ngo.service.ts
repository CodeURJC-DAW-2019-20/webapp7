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

    updateNgo(ngo: any): Observable<any> {
      let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " +  this.getLoggedNgoToken());
  
      return this._http.put(this.url+'ongs/', ngo,{headers: headers});
    }


    getLoggedNgoToken(){
      return localStorage.getItem("authorization");
    }

    delete(id):Observable<any>{
      let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedNgoToken());
      return this._http.delete(this.url+'ongs/'+id, {headers: headers});
    }

    getNgo(ngoId:number):Observable<any>{
      let headers = new HttpHeaders().set('Content-Type', 'application/json');

      return this._http.get(this.url + 'ongs/' + ngoId,{headers:headers});
    }

    getNgos(page: number) :Observable<any>{
      let headers = new HttpHeaders().set('Content-Type', 'application/json');
      return this._http.get(this.url+'ongs/?page='+page, {headers:headers});
    }

}


