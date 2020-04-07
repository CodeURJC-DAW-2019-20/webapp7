
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

  public getVolunteeringById(volunteeringId:number):Observable<any>{

    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    
    return this._http.get(this.url+'volunteerings/' + volunteeringId,{headers: headers});

  }

  join(volId):Observable<any>{

    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
    console.log(headers);
    return this._http.post(this.url+'volunteerings/join/'+volId,null, {headers: headers});

  }

  getLoggedUserToken(){
    return localStorage.getItem("authorization");
}
}