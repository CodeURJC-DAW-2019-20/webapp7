import { Component, OnInit } from '@angular/core';
import { Volunteering } from 'src/app/models/volunteering';
import { NGO } from 'src/app/models/ngo';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Category } from 'src/app/models/category';
import { CategoryService } from 'src/app/services/category.service';

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

  public category:number;
  



  constructor(private _volunteeringService: VolunteeringService, private _categoryService: CategoryService) {

    this.volunteering = new Volunteering(null,null,null,"aaa",null,null,null,"aaa","aaa","aaa",null,"aaa");

    //this.categoryId = 1;

    this.categories = this._categoryService.getCategories();

   }



  ngOnInit() {

    this.ngoLogged = this._volunteeringService.getNgoLogged();

  }

  onSubmit(){



  }

}
