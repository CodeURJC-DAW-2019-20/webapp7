import { Component, OnInit, DoCheck } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { EntityService } from 'src/app/services/entity.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { global } from '../../services/global';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css'],
  providers: [UserService, EntityService]
})
export class UserSettingsComponent implements OnInit {

  public status: String;
  public user: User;
  public identity;
  public afuConfig;
  public token;
  public url;


  constructor(
    private _userService: UserService,
    private _entityService: EntityService,
    private _router: Router,
    private _route: ActivatedRoute,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Mis ajustes - VoluntaWeb");
    this.identity = this._entityService.getIdentity();
    this.user = this.identity;

    this.token = localStorage.getItem('authorization');

    this.url = global.url;

    this.afuConfig = {
      uploadAPI: {
        url: this.url + 'users/image',
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
    this.identity = this._entityService.getIdentity();
  }

  ngDoCheck() {
    this.identity = this._entityService.getIdentity();
  }

  onSubmit(form) {
    this._userService.update(this.user).subscribe(response => {
      if (response.id != null) {
        this.status = "success";
        this.identity = response;
        localStorage.setItem("identity", JSON.stringify(response));
      } else {
        this.status = "error";
      }
    },
      error => {
        console.log(<any>error);
        this.status = "error";
      });



  }

  deleteUser() {
    let userId = this._entityService.getIdentity().id;
    this._userService.delete(userId).subscribe(
      response => {
        if (response.id) {
          this.status = "success";
          localStorage.removeItem("authorization");
          localStorage.removeItem("identity");
          localStorage.removeItem("entity_type");
        }

        this._router.navigate(['/']);

      },
      error => {
        console.log(<any>error);
      }
    );
  }

  avatarUpload(data) {
    let data_obj = JSON.parse(data.response);
    this.user.image = data_obj.image;
  }


}
