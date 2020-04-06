import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { NgoService } from 'src/app/services/ngo.service';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-ngo-settings',
  templateUrl: './ngo-settings.component.html',
  styleUrls: ['./ngo-settings.component.css'],
  providers: [NgoService]
})

export class NgoSettingsComponent implements OnInit {

  public ngo: NGO; 
  public status;
  public identity:string;


  constructor(private _ngoService:NgoService) { 

    this.ngo = new NGO(null,"","","","","","","","",null,"","");
  }

  ngOnInit() {

    this.getNgo();

    console.log(this.ngo);
  }


  getNgo(){

    //This is fake for testing that this works
    this.ngo = new NGO(15,"Fundación de Ayuda a los Animales xdxdxdxdxd","Diez","Diez","Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general","Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada","informa@aventura.org","28691","true",null,"33333333","test");

    this.identity = JSON.stringify(this.ngo);
    localStorage.setItem('identity',this.identity);
    localStorage.setItem('authorization',"aW5mb3JtYUBhdmVudHVyYS5vcmc6dGVzdA==");
    //This is fake for testing that this works


    this.identity = localStorage.getItem('identity');

    this.ngo = JSON.parse(this.identity);
  }

  onSubmit(formNgo){
    this._ngoService.updateNgo(this.ngo.id.toString(),this.ngo).subscribe(
      (response:any) => {
          if(response.ngo){
            this.ngo = response.ngo;
          }
          else{
            this.status = 'error';
          }
      },
      error =>{
        console.log(<any>error);
      }
    );

    localStorage.setItem('identity',JSON.stringify(this.ngo));

  }

}
