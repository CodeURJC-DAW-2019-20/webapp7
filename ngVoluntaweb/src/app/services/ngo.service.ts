import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global.service';

@Injectable()
export class NgoService {
  
  public url: string;

  constructor(private _http: HttpClient) {
    this.url = global.url2 + "ongs/";
  }

  updateNgo(ngoId:string, ngo: any): Observable<any> {

    let token:string = localStorage.getItem('authorization');

    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " + token);

    return this._http.put(this.url, ngo,{headers: headers});
  }

}
