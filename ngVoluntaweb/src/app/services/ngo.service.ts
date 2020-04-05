import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NGO } from '../models/ngo';
import { Observable } from 'rxjs';
import { NgoSettingsComponent } from '../components/ngo-settings/ngo-settings.component';

@Injectable(/*{
  providedIn: NgoSettingsComponent
}*/)
export class NgoService {

  public ngo: NGO;
  public url: string = "https://localhost:8443/api";

  constructor(private _http: HttpClient) {
    this.ngo = new NGO(1, "PruebaName", "PruebaResponsibleName", "PruebaResponsibleSurname", "PruebaDescription", "PruebaAddress", "PruebaEmail",
      "PruebaCodigoPostal", "PruebaImagen", null, "PruebaTelefono", "PruebaPassword");
  }

  public getNGO(ngoId: string): Observable<any> {

    let headers = new HttpHeaders().set('Content-Type', 'aplication/json');

    return this._http.get(this.url + "/ongs/" + ngoId, { headers: headers });
  }

  updateNgo(ngo: NGO): void {
    this.ngo = ngo;

    console.log(this.ngo);

    //Here we have to implement the update of the ngo with the API Rest


  }

}
