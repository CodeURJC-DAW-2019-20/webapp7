import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { Volunteering } from 'src/app/models/volunteering';
import { Category } from 'src/app/models/category';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { CategoryService } from 'src/app/services/category.service';
import { global } from '../../services/global.service';


@Component({
  selector: 'app-edit-volunteering',
  templateUrl: './edit-volunteering.component.html',
  styleUrls: ['./edit-volunteering.component.css']
})
export class EditVolunteeringComponent implements OnInit {

  private volunteering: Volunteering;
  private ngoLogged:NGO;
  public categories:Array<Category>;
  public category:Category;
  private status: string;
  private editedVolunteering:string;
  

  public afuConfig:any;
  public url:string;
  public token:string;
  

  constructor(private _volunteeringService: VolunteeringService, private _categoryService: CategoryService) { 
    
    this.volunteering = new Volunteering(null,null,null,"",null,null,null,"","","",null,"");

    this.categories = this._categoryService.getCategories();

    this.category = this.categories[0];


    this.ngoLogged = this._volunteeringService.getNgoLogged();

    this.editedVolunteering = this._volunteeringService.getEditVolunteering();

    this._volunteeringService.getVolunteeringById(Number.parseInt(this.editedVolunteering)).subscribe(
      (response:any)=>{
        if(response){
          this.volunteering = response;
        }
        else{
          this.status = 'error';
        }
      },
      error =>{
        console.log(<any>error);
      }
    );

    //Esto esta para testear
    localStorage.setItem('authorization',"cmVjZXBjaW9uLmNlbnRyYWxAc2F2ZXRoZWNoaWxkcmVuLm9yZzp0ZXN0");
    //Esto esta para testear

    this.token = localStorage.getItem('authorization');

    this.url = global.url;
  
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

    if(this.ngoLogged.volunteerings != null){     //Cuando se una todo esto no va 
      this.ngoLogged.volunteerings.delete(this.volunteering);
    }
    
  }
  

  public onSubmit(){

    this._volunteeringService.updateVolunteering(this.volunteering.id,this.volunteering).subscribe(
      (response:any) =>{
        if(response){
          this.volunteering = response;
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
    let data_obj = JSON.parse(data.response);
    this.volunteering.image = "true";
  }

}
