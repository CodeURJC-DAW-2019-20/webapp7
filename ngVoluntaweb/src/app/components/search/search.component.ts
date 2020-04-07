import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { Category } from '../../models/category';
import { SearchService } from '../../services/search.service'
import { NgoService } from '../../services/ngo.service';
import { Volunteering } from 'src/app/models/volunteering';
import { CategoryService } from '../../services/category.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers: [SearchService, CategoryService]
})
export class SearchComponent implements OnInit {
  private page:number=0;
  private all_volunteerings:Array<Volunteering>;
  private selected_volunteerings:Array<Volunteering>;
  public categories: Array<Category>;
  private categor:Category;
  private word:string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _searchService: SearchService,
    private _categoryService: CategoryService
    ) {
      this.categories = this._categoryService.getCategories();
    }

  ngOnInit() {
    this._searchService.getSearch(this.page).subscribe(
      response => {
        this.selected_volunteerings = null;
        this.all_volunteerings = response;
      },
      error => {
        console.log(<any>error);
      }
    )
  }


  AllVolunteeringsByWord(){
    this._searchService.getByWord(this.word).subscribe(
      response => {
        this.selected_volunteerings = response;
        this.all_volunteerings = null;
      },
      error => {
        console.log(<any>error);
      }
    )
  }

  /*AllCategories(){
    this._categoryService.getCategories().subscribe(
      response => {
        this.categories = response;
        console.log(this.categories);
      },
      error => {
        console.log(<any>error);
      }
    )
  }*/

}
