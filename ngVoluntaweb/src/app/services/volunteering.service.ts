
import { Injectable } from '@angular/core';
import { NGO } from '../models/ngo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

@Injectable({
  providedIn: 'root'
})
export class VolunteeringService {

  private ngo:NGO;
  private identity:string;
  private url:string;

  constructor(private _http: HttpClient) { 
    this.url = global.url;
  }

  public getByJoined(userId):Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'volunteerings/join/'+userId,{headers: headers});
  }
}