import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { Category } from '../../models/category';
import { SearchService } from '../../services/search.service';
import { VolunteeringService } from '../../services/volunteering.service'
import { NgoService } from '../../services/ngo.service';
import { Volunteering } from 'src/app/models/volunteering';
import { CategoryService } from '../../services/category.service';
import { Observable } from 'rxjs';
import { global } from '../../services/global';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers: [SearchService, CategoryService, VolunteeringService]
})
export class SearchComponent implements OnInit {
  private currentPage:number;
  private numberPages:number;
  private volunteerings:Array<Volunteering>;
  private all_volunteerings:Array<Volunteering>;
  private selected_volunteerings:Array<Volunteering>;
  public categories: Array<Category>;
  private categor:number;
  private word:string;
  private non_volunteerings:boolean;
  public url: string;
  public keywordIndex:string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _searchService: SearchService,
    private _volunteeringService: VolunteeringService,
    private _categoryService: CategoryService
    ) {
      this.AllCategories();
      this.getNumberPages();
      this.url = global.url;
      this.categor = 1;
      console.log(this.all_volunteerings);
      console.log(this.selected_volunteerings);
      console.log(this.volunteerings);
    }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      this.keywordIndex = params.wordIndex;
    })
    this.word = this.keywordIndex;
    if (this.numberPages == 0){
      this.non_volunteerings = true;
      console.log("hola num 1");
    } else if(this.keywordIndex){
      this.AllVolunteeringsByWord();
      console.log("hola num 2");
      console.log(this.all_volunteerings);
      console.log(this.selected_volunteerings);
      console.log(this.volunteerings);
      console.log(this.non_volunteerings);
    } 
    else if (!this.volunteerings){
      this.AllVolunteerings();
      console.log("hola num 3");
    }else{
      this.all_volunteerings = this.volunteerings;
      this.selected_volunteerings = null;
      console.log("hola num 4");
    }
  }

  AllVolunteerings(){
    this._searchService.getSearch(0).subscribe(
      response => {
        this.selected_volunteerings = null;
        this.all_volunteerings = response;
        this.volunteerings = [...this.all_volunteerings];
        this.non_volunteerings = false;
        console.log("hola soy todos los voluntariados");
      },
      error => {
        console.log(<any>error);
        this.non_volunteerings = true;
      }
    )
  }

  getNumberPages(){
    this._volunteeringService.getVolunteerings().subscribe(
      response =>{
        if(response.length == 0){
          this.numberPages = 0;
        } else if ((response.length % 5) == 0){
          this.numberPages = response.length / 5;
          this.numberPages -= 1;
        } else{
          this.numberPages = Math.floor(response.length / 5);
        }
        this.currentPage = 0;
        console.log("hola soy el numero de paginas");
      },
      error => {
        console.log(<any>error);
      }
    )
  }

  addFiveMoreVolunteerings(){
    if(this.currentPage < (this.numberPages + 1)){
      this.currentPage += 1;
    }
    this._searchService.getSearch(this.currentPage).subscribe(
      response => {
        this.volunteerings.push(...response);
        console.log("hola soy añadir voluntariados");
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
        this.non_volunteerings = false;
        console.log("hola soy buscar por palabra");
      },
      error => {
        console.log(<any>error);
        console.log("hola palabra no encontrada");
        this.non_volunteerings = true;
        console.log(this.non_volunteerings);
      }
    )
  }

  AllCategories(){
    this._categoryService.getCategories().subscribe(
      response => {
        this.categories = response;
        console.log("hola soy todas las categorias");
      },
      error => {
        console.log(<any>error);
      }
    )
  }

  AllVolunteeringsByCategory(){
    this._searchService.getByCategory(this.categor).subscribe(
      response => {
        this.selected_volunteerings = response;
        this.all_volunteerings = null;
        this.non_volunteerings = false;
        console.log("hola soy buscar por categoria");
      },
      error => {
        console.log(<any>error);
        this.non_volunteerings = true;
      }
    )
  }

}
