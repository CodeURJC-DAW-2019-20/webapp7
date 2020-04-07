import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { global } from './global.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private urlNgo:string;
  private urlVolunteerings

  constructor(private _http:HttpClient) {

    this.urlNgo = global.url + "ongs/image";
    this.urlVolunteerings = global.url + "volunteerings/image";

  }

  public uploadImage(fd:any):Observable<any>{

  //Paripe para testear
  localStorage.setItem('authorization',"cmVjZXBjaW9uMi5jZW50cmFsQHNhdmV0aGVjaGlsZHJlbi5vcmc6dGVzdA==");
  //Paripe para testear

    let token:string = localStorage.getItem('authorization');

    let headers = new HttpHeaders().set('Content-Type', 'multipart/form-data; boundary=<calculated when request is sent>').set("Authorization","Basic " + token);

    return this._http.post(this.urlNgo,fd,{headers:headers});
  }
}
