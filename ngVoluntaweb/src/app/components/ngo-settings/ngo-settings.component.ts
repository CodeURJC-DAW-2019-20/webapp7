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
  public identity: string;

  public afuConfig: any;
  public url: string;
  public token: string;


  constructor(private _ngoService: NgoService, private _entityService: EntityService, private _titleService: Title) {
    this._titleService.setTitle("Mis ajustes - VoluntaWeb");

    this.ngo = new NGO(null, "", "", "", "", "", "", "", "", null, "", "");

    this.url = global.url;

    this.token = localStorage.getItem('authorization');

    this.identity = this._entityService.getIdentity();

    this.afuConfig = {
      uploadAPI: {
        url: this.url + 'ongs/image',
        headers: {
          "Authorization": 'Basic ' + this.token
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


  getNgo() {

    this.identity = localStorage.getItem('identity');

    this.ngo = JSON.parse(this.identity);
  }

  onSubmit(formNgo) {
    this._ngoService.updateNgo(this.ngo).subscribe(
      (response: any) => {
        if (response.id) {
          this.ngo = response;
          localStorage.setItem('identity', JSON.stringify(this.ngo));
        }
        else {
          this.status = 'error';
        }
      },
      error => {
        this.status = 'error';
        console.log(<any>error);
      }
    );

  }


  avatarUpload(data) {
    let data_obj = JSON.parse(data.response);
    this.ngo.image = data_obj.image;
  }
}
