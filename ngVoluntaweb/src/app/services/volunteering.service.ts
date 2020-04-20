import { Injectable } from '@angular/core';
import { NGO } from '../models/ngo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';


@Injectable()
export class VolunteeringService {

  private ngo: NGO;
  private identity: string;
  private url: string;

  constructor(private _http: HttpClient) {
    this.url = global.url;
  }

  public getNgoLogged(): NGO {


    this.identity = localStorage.getItem('identity');

    this.ngo = JSON.parse(this.identity);

    return this.ngo;

  }

  getVolunteerings(): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', "application/json");
    return this._http.get(this.url + 'volunteerings/all/', { headers: headers });
  }




  public create(volunteering: any): Observable<any> {


    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization", "Basic " + this.getLoggedUserToken());

    return this._http.post(this.url + 'volunteerings/', volunteering, { headers: headers });

  }
  public isJoined(volId): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization", "Basic " + this.getLoggedUserToken());
    return this._http.get(this.url + 'users/joined/' + volId, { headers: headers });

  }

  public isLiked(volId): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization", "Basic " + this.getLoggedUserToken());
    return this._http.get(this.url + 'users/liked/' + volId, { headers: headers });

  }

  public getByJoined(userId): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'volunteerings/join/' + userId, { headers: headers });

  }

  public getVolunteeringById(volunteeringId: number): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'volunteerings/' + volunteeringId, { headers: headers });

  }


  public updateVolunteering(volunteringId: number, volunteering: any) {



    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization", "Basic " + this.getLoggedUserToken());

    return this._http.put(this.url + 'volunteerings/' + volunteringId, volunteering, { headers: headers });

  }


  join(volId): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic ' + this.getLoggedUserToken());
    return this._http.post(this.url + 'volunteerings/join/' + volId, null, { headers: headers });

  }

  like(volId): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic ' + this.getLoggedUserToken());
    return this._http.post(this.url + 'volunteerings/like/' + volId, null, { headers: headers });

  }

  getLoggedUserToken() {
    return localStorage.getItem("authorization");
  }


  getVolunteeringsByPage(page: number): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'volunteerings/?page=' + page, { headers: headers });
  }

  deleteoneVolunteering(volid: number): Observable<any> {
    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic ' + this.getLoggedUserToken());
    return this._http.delete(this.url + 'volunteerings/' + volid, { headers: headers });

  }

}

