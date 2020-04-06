import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { Volunteering } from 'src/app/models/volunteering';
import { Category } from 'src/app/models/category';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { CategoryService } from 'src/app/services/category.service';

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
  

  constructor(private _volunteeringService: VolunteeringService, private _categoryService: CategoryService) { 
    
    this.volunteering = new Volunteering(null,null,null,"",null,null,null,"","","",null,"");

    this.categories = this._categoryService.getCategories();

    this.category = this.categories[0];

  }

  ngOnInit() {

    //this.ngoLogged = this._volunteeringService.getNgoLogged();
    this.editedVolunteering = this._volunteeringService.getEditVolunteering();

    this._volunteeringService.getVolunteeringById(this.editedVolunteering).subscribe(
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

  }
  

  public onSubmit(){



  }

}
