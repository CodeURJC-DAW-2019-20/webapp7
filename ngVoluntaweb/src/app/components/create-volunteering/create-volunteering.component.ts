import { Component, OnInit } from '@angular/core';
import { Volunteering } from 'src/app/models/volunteering';
import { NGO } from 'src/app/models/ngo';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { Form } from '@angular/forms';
import { global } from '../../services/global.service';

@Component({
  selector: 'app-create-volunteering',
  templateUrl: './create-volunteering.component.html',
  styleUrls: ['./create-volunteering.component.css'],
  providers: [VolunteeringService]
})
export class CreateVolunteeringComponent implements OnInit {

  private volunteering: Volunteering;

  private ngoLogged:NGO;

  public categories:Array<Category>;

  public category:Category;

  private status: string;

  private afuConfig:any;

  private url:string;

  private token: string;
  



  constructor(private _volunteeringService: VolunteeringService, private _categoryService: CategoryService) {

    this.volunteering = new Volunteering(null,null,null,"",null,null,null,"","","",null,"");

    this.categories = this._categoryService.getCategories();

    this.category = this.categories[0];

    this.url = global.url;

    //Esto esta para testear
    localStorage.setItem('authorization',"cmVjZXBjaW9uLmNlbnRyYWxAc2F2ZXRoZWNoaWxkcmVuLm9yZzp0ZXN0");
    //Esto esta para testear

    this.token = localStorage.getItem('authorization');

    console.log(this.url + 'volunteerings/image/' + this.volunteering.id);

    this.volunteering.image = "false";


    this.afuConfig = {
      uploadAPI: {
        url: this.url + 'volunteerings/image/'+ this.volunteering.id,
        headers: {
          "Authorization": 'Basic '+ this.token
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

    this.ngoLogged = this._volunteeringService.getNgoLogged();
    console.log(this.ngoLogged);
  }

  onSubmit(){

    this.volunteering.category = this.category;
    this.volunteering.ong = this.ngoLogged;
    this.volunteering.id = null; //The API give the id

    this._volunteeringService.createVolunteering(this.volunteering).subscribe(
      (response:any) =>{
        if(response.volunteering){
          this.volunteering = response.volunteering;
        }
        else{
          this.status = 'error';
        }
      },
      error =>{
        console.log(<any>error);
      }
    );


    if(this.ngoLogged.volunteerings == null){
      this.ngoLogged.volunteerings = new Set<Volunteering>();
    }


    this.ngoLogged.volunteerings.add(this.volunteering);

    localStorage.setItem('identity',JSON.stringify(this.ngoLogged));

  }


  avatarUpload(data) {
    console.log(data.response);
    let data_obj = JSON.parse(data.response);
    this.volunteering.image = "true";
  }
}
