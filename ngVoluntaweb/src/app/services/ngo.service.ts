import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class NgoService {

  //public ngo: NGO;
  public url: string = "https://localhost:8443/api/ongs/";

  constructor(private _http: HttpClient) {
    /*this.ngo = new NGO(1, "PruebaName", "PruebaResponsibleName", "PruebaResponsibleSurname", "PruebaDescription", "PruebaAddress", "PruebaEmail",
      "PruebaCodigoPostal", "PruebaImagen", null, "PruebaTelefono", "PruebaPassword");*/
  }

  /*public getNGO(ngoId: string): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', 'aplication/json');

    return this._http.get(this.url + ngoId, { headers: headers });
  }*/

  updateNgo(ngoId:string, ngo: any): Observable<any> {

    let token:string = localStorage.getItem('authorization');

    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " + token);

    return this._http.put(this.url, ngo,{headers: headers});
  }

}
