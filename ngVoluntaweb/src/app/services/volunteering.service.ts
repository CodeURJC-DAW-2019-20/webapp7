
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

  public getNgoLogged():NGO{

    //This is fake for testing that this works
    /*this.ngo = new NGO(55,"Save the children","Andrés","Conde","Todos los niños tienen derecho a un futuro. En España y en todo el mundo, trabajamos cada día para asegurar que todos los niños sobreviven, aprenden y están protegidos frente a la violencia. Cuando se produce una emergencia, y los niños son más vulnerables, somos siempre los primeros en llegar y los últimos en marcharnos. Atendemos las necesidades de los niños y nos aseguramos de que sus voces son escuchadas. Conseguimos cambios duraderos en la vida de millones de niños, incluso en aquellos a los que cuesta más llegar.  Hacemos todo lo que sea necesario para lograr que todos los niños, cada día y cuando ocurre una emergencia, puedan cambiar sus vidas y el futuro que estamos construyendo juntos.","Calle del Dr. Esquerdo, 138, Madrid","irecepcion.central@savethechildren.org","28007","true",null,"33333333","test");

    this.identity = JSON.stringify(this.ngo);
    localStorage.setItem('identity',this.identity);
    localStorage.setItem('authorization',"cmVjZXBjaW9uLmNlbnRyYWxAc2F2ZXRoZWNoaWxkcmVuLm9yZzp0ZXN0");
    //This is fake for testing that this works*/

    this.identity = localStorage.getItem('identity');

    this.ngo = JSON.parse(this.identity);

    return this.ngo;

  }


  /*public getEditVolunteering():any{
      //This is fake for testing
      localStorage.setItem('editVolunteering',"128");
      //This is fake for testing

      return localStorage.getItem('editVolunteering');
  }*/


  public create(volunteering:any):Observable<any>{


    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " + this.getLoggedUserToken());

    return this._http.post(this.url+'volunteerings/',volunteering,{headers:headers});

}
  public getByJoined(userId):Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url + 'volunteerings/join/'+userId,{headers: headers});

  }

  public getVolunteeringById(volunteeringId:number):Observable<any>{

    let headers = new HttpHeaders().set('Content-Type', 'application/json');    
    return this._http.get(this.url+'volunteerings/' + volunteeringId,{headers: headers});

  }


  public updateVolunteering(volunteringId:number, volunteering:any){

    //Esto hay que quitarlo
    /*localStorage.setItem('authorization',"cmVjZXBjaW9uLmNlbnRyYWxAc2F2ZXRoZWNoaWxkcmVuLm9yZzp0ZXN0");*/
    //Esto hay que quitarlo


    let headers = new HttpHeaders().set('Content-Type', 'application/json').set("Authorization","Basic " + this.getLoggedUserToken());

    return this._http.put(this.url+'volunteerings/' + volunteringId, volunteering,{headers: headers});

  }


  join(volId):Observable<any>{

    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
    console.log(headers);
    return this._http.post(this.url+'volunteerings/join/'+volId,null, {headers: headers});

  }

  getLoggedUserToken(){
    return localStorage.getItem("authorization");
  }

  getVolunteerings(page: number) :Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this._http.get(this.url+'volunteerings/?page='+page, {headers:headers});
  }

  delete(id):Observable<any>{
    let headers = new HttpHeaders().set('Content-Type', "application/json").set('Authorization', 'Basic '+this.getLoggedUserToken());
    return this._http.delete(this.url+'volunteerings/'+id, {headers: headers});
  }

}

