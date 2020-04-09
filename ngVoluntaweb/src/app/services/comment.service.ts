import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { global } from './global';

import { Comment } from '../models/comment';


@Injectable()
export class CommentService{
    public url: String;
    public comment: Comment;


    constructor(
        private _http: HttpClient
    ) {
        this.url = global.url;
    }

    create(comment):Observable<any>{
        let headers = new HttpHeaders().set("Content-Type", "application/json");
        return this._http.post(this.url+'comments/', comment, {headers: headers});
    }

    get():Observable<any>{
        let headers = new HttpHeaders().set("Content-Type", "application/json");
        return this._http.get(this.url+'comments/', {headers: headers});
    }

    delete(commentId):Observable<any>{
        /*
        Hay que añadir el auth de paripe
        */

        let headers = new HttpHeaders().set("Content-Type", "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
        return this._http.delete(this.url+'comments/'+commentId, {headers: headers});
    }

    getOne(commentId):Observable<any>{
        let headers = new HttpHeaders().set("Content-Type", "application/json");
        return this._http.get(this.url+'comments/'+commentId, {headers: headers});
    }

    getLoggedUserToken(){
        return localStorage.getItem("authorization");
      }
    
}