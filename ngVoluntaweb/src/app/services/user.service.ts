import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';
import { User } from '../models/user';

@Injectable()
export class UserService{
    public url: string;
    public user: User;
    constructor(
        public _http: HttpClient
    ){
        this.url = global.url;
    }

    register(user):Observable<any>{

        let headers = new HttpHeaders().set('Content-Type', "application/json");

        return this._http.post(this.url+"users/", user, {headers: headers});

    }

    update(user):Observable<any>{
        let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
        return this._http.put(this.url+'users/', user, {headers: headers});
    }

    getLoggedUserToken(){
        return localStorage.getItem("authorization");
    }
    delete(id):Observable<any>{
        let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
        return this._http.delete(this.url+'users/'+id, {headers: headers});
    }

    getUser(id):Observable<any>{
        let headers = new HttpHeaders().set('Content-Type', "application/json");

        return this._http.get(this.url+"users/"+id, {headers: headers});
    }

    getUsers(page: number) :Observable<any>{
        let headers = new HttpHeaders().set('Content-Type', 'application/json');

        return this._http.get(this.url+'users/?page='+page, {headers:headers});
    }
  

}