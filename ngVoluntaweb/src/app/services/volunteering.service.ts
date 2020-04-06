import { Injectable } from '@angular/core';
import { NGO } from '../models/ngo';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VolunteeringService {

  private ngo:NGO;
  private identity:string;
  private url:string = "https://localhost:8443/api/volunteerings/";

  constructor(private _http: HttpClient) { 

  }

  public getNgoLogged():any{

    //This is fake for testing that this works
    this.ngo = new NGO(15,"Fundación de Ayuda a los Animales xdxdxdxdxd","Diez","Diez","Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general","Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada","informa@aventura.org","28691","true",null,"33333333","test");

    this.identity = JSON.stringify(this.ngo);
    localStorage.setItem('identity',this.identity);
    localStorage.setItem('authorization',"aW5mb3JtYUBhdmVudHVyYS5vcmc6dGVzdA==");
    //This is fake for testing that this works

    this.identity = localStorage.get('identity')
    return JSON.parse(this.identity);

  }


  public getEditVolunteering():any{
      //This is fake for testing
      localStorage.setItem('editVolunteering',"382")
      //This is fake for testing

      return localStorage.getItem('editVolunteering');
  }


  public createVolunteering(volunteering:any):Observable<any>{

    let token:string = localStorage.getItem('authorization');

    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " + token);

    return this._http.post(this.url,volunteering,{headers:headers});

  }

  public getVolunteeringById(volunteeringId:string):Observable<any>{

    let headers = new HttpHeaders().set('Content-Type', 'application/json');

    console.log(this.url + volunteeringId);
    
    return this._http.get(this.url + volunteeringId,{headers: headers});

  }
}
