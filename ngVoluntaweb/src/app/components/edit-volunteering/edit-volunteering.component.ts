import { Component, OnInit, OnDestroy } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { Volunteering } from 'src/app/models/volunteering';
import { Category } from 'src/app/models/category';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { CategoryService } from 'src/app/services/category.service';
import { global } from '../../services/global';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgoService } from 'src/app/services/ngo.service';
import { Form } from '@angular/forms';
import { Title } from '@angular/platform-browser';


@Component({
  selector: 'app-edit-volunteering',
  templateUrl: './edit-volunteering.component.html',
  styleUrls: ['./edit-volunteering.component.css'],
  providers: [VolunteeringService, NgoService, CategoryService]
})
export class EditVolunteeringComponent implements OnInit, OnDestroy {

  public volunteering: Volunteering;
  private ngoLogged: NGO;
  public categories;
  public category;
  public status: string;



  public afuConfig: any;
  public url: string;
  public token: string;


  constructor(private _volunteeringService: VolunteeringService, private _ngoService: NgoService, private _categoryService: CategoryService, private _route: ActivatedRoute, private _router: Router, private _titleService: Title) {
    this._titleService.setTitle("Editar voluntariado - VoluntaWeb");

    this.volunteering = new Volunteering(null, null, null, "", null, null, null, "", "", "", null, "");


    this.getCategories();






    this.ngoLogged = this._volunteeringService.getNgoLogged();

    this.url = global.url;

    this.token = localStorage.getItem('authorization');

    this._route.params.subscribe(
      (params) => {
        var volId = params['id'];
        this.afuConfig = {
          uploadAPI: {
            url: this.url + 'volunteerings/image/' + volId,
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
        this.getVolunteering(volId);
      }
    );


  }

  getVolunteering(volId) {
    this._volunteeringService.getVolunteeringById(volId).subscribe(
      (response: any) => {
        if (response && response.ong.id == this.ngoLogged.id) {
          this.volunteering = response;
          this.category = this.volunteering.category.id;


        }
        else {
          this._router.navigate(['/']);
        }
      },
      error => {
        console.log(<any>error);
      }
    );

  }

  ngOnInit() {


  }

  getCategories(): void {
    this._categoryService.getCategories().subscribe(
      (response: any) => {
        if (response) {
          this.categories = response;
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


  public onSubmit() {
    this.volunteering.category = this.categories[this.category - 1];

    this._volunteeringService.updateVolunteering(this.volunteering.id, this.volunteering).subscribe(
      (response: any) => {
        if (response) {
          this.volunteering = response;
          this.status = "success";
        }
        else {
          this.status = 'error';
        }
      },
      error => {
        console.log(<any>error);
        this.status = 'error';
      }
    );

  }

  avatarUpload(data) {

    let data_obj = JSON.parse(data.response);
    this.volunteering.image = data_obj.image;
  }

  ngOnDestroy() {
    this._ngoService.getNgo(this.ngoLogged.id).subscribe(
      (response: any) => {
        if (response) {
          this.ngoLogged = response;

          localStorage.setItem('identity', JSON.stringify(this.ngoLogged));

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

}
