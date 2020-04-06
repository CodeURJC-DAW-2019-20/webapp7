import { Component, OnInit } from '@angular/core';
import { Volunteering } from 'src/app/models/volunteering';
import { NGO } from 'src/app/models/ngo';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';
import { Form } from '@angular/forms';

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
  



  constructor(private _volunteeringService: VolunteeringService, private _categoryService: CategoryService) {

    this.volunteering = new Volunteering(null,null,null,"",null,null,null,"","","",null,"");

    this.categories = this._categoryService.getCategories();

    this.category = this.categories[0];

   }



  ngOnInit() {

    this.ngoLogged = this._volunteeringService.getNgoLogged();

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

    this.ngoLogged.volunteerings.add(this.volunteering);

    localStorage.setItem('identity',JSON.stringify(this.ngoLogged));

  }

}
