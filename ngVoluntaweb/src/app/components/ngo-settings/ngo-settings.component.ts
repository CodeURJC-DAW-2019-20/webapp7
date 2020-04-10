import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { NgoService } from 'src/app/services/ngo.service';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';
import { global } from '../../services/global';
import { EntityService } from 'src/app/services/entity.service';
import { Title } from '@angular/platform-browser';




@Component({
  selector: 'app-ngo-settings',
  templateUrl: './ngo-settings.component.html',
  styleUrls: ['./ngo-settings.component.css'],
  providers: [NgoService, EntityService]
})

export class NgoSettingsComponent implements OnInit {

  public ngo: NGO; 
  public status;
  public identity:string;

  public afuConfig:any;
  public url:string;
  public token:string;


  constructor(private _ngoService:NgoService, private _entityService: EntityService, private _titleService: Title) { 
    this._titleService.setTitle("Mis ajustes - VoluntaWeb");

    this.ngo = new NGO(null,"","","","","","","","",null,"","");

    this.url = global.url;

    this.token = localStorage.getItem('authorization');

    this.identity = this._entityService.getIdentity();

    this.afuConfig = {
      uploadAPI: {
        url: this.url + 'ongs/image',
        headers: {
          "Authorization": 'Basic '+this.token
        }
      },
      multiple: false,
      formatsAllowed: ".jpg",
      maxSize: '50',
      theme: "attachPin",
      hideProgressBar: false,
      hideResetBtn: true,
      hideSelectBtn: false,
      replaceTexts: {
        selectFileBtn: 'Seleccionar archivo...',
        resetBtn: 'Reset',
        uploadBtn: 'Subir',
        dragNDropBox: 'Drag N Drop',
        attachPinBtn: 'Seleccionar archivo...',
        afterUploadMsg_success: '¡Subida satisfactoria!',
        afterUploadMsg_error: '¡Subida fallida!'
      }
    };
   }
  

  ngOnInit() {

    this.getNgo();

  }


  getNgo(){

    /*//This is fake for testing that this works
    this.ngo = new NGO(15,"Fundación de Ayuda a los Animales xdxdxdxdxd","Diez","Diez","Tenemos el placer de darle la bienvenida a la Fundación de Ayuda a los Animales (F.A.A.).En donde el respeto a los animales, al amor y el cariño que sentimos hacia ellos es lo que mueve a nuestra Fundación a luchar día a día por todos aquellos animales que sufren el comportamiento inhumano con el que algunas personas premian su ayuda y fidelidad. Por eso desde la Fundación de Ayuda a los Animales luchamos para que todos los animales puedan tener una vida digna y para que animales abandonados puedan recibir el amor, la amistad y lealtad que merecen. Desde la FAA apoyamos a Organizaciones y Albergues dedicados a esta difícil lucha que es la Defensa de los Animales. Por otro lado, consideramos que colaborar en la lucha por un mundo más justo con los animales es colaborar por un mundo más solidario y menos violento en general","Calle Centro Comercial Mocha Chica, 0 S/N, Villanueva de la cañada","informa@aventura.org","28691","true",null,"33333333","test");

    this.identity = JSON.stringify(this.ngo);
    localStorage.setItem('identity',this.identity);
    localStorage.setItem('authorization',"aW5mb3JtYUBhdmVudHVyYS5vcmc6dGVzdA==");
    //This is fake for testing that this works*/


    this.identity = localStorage.getItem('identity');

    this.ngo = JSON.parse(this.identity);
  }

  onSubmit(formNgo){
    this._ngoService.updateNgo(this.ngo).subscribe(
      (response:any) => {
          if(response.id){
            this.ngo = response;
            localStorage.setItem('identity',JSON.stringify(this.ngo));
          }
          else{
            this.status = 'error';
          }
      },
      error =>{
        this.status = 'error';
        console.log(<any>error);
      }
    );

  }


  avatarUpload(data) {
    let data_obj = JSON.parse(data.response);
    console.log(data_obj);
    this.ngo.image = data_obj.image;
  }
}
