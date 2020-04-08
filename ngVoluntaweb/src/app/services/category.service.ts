import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {global} from '../services/global';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private  categories:Array<Category>;
  private url:string;

  constructor(private _http:HttpClient) { 
    this.url = global.url;
  }
  
  getCategories():Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this._http.get(this.url + "categories/", {headers:headers});
  }

}
