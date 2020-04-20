import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Category } from '../../models/category';
import { SearchService } from '../../services/search.service';
import { VolunteeringService } from '../../services/volunteering.service'
import { NgoService } from '../../services/ngo.service';
import { Volunteering } from 'src/app/models/volunteering';
import { CategoryService } from '../../services/category.service';
import { Observable } from 'rxjs';
import { global } from '../../services/global';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers: [SearchService, CategoryService, VolunteeringService]
})
export class SearchComponent implements OnInit {
  private currentPage: number;
  private numberPages: number;
  private volunteerings: Array<Volunteering>;
  private all_volunteerings: Array<Volunteering>;
  private selected_volunteerings: Array<Volunteering>;
  public categories: Array<Category>;
  private categor: number;
  private word: string;
  private non_volunteerings: boolean;
  public url: string;
  public keywordIndex: string;
  public loading: boolean = true;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _searchService: SearchService,
    private _volunteeringService: VolunteeringService,
    private _categoryService: CategoryService,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Voluntariados - VoluntaWeb");
    this.AllCategories();
    this.getNumberPages();
    this.url = global.url;
    this.categor = 1;
  }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
      this.keywordIndex = params.wordIndex;
    })
    this.word = this.keywordIndex;
    if (this.numberPages == 0) {
      this.non_volunteerings = true;
    } else if (this.keywordIndex != undefined) {
      this.AllVolunteeringsByWord();
    }
    else if (!this.volunteerings) {
      this.AllVolunteerings();
    } else {
      this.all_volunteerings = this.volunteerings;
      this.selected_volunteerings = null;
    }
  }

  AllVolunteerings() {
    this._searchService.getSearch(0).subscribe(
      response => {
        this.selected_volunteerings = null;
        this.all_volunteerings = response;
        this.volunteerings = [...this.all_volunteerings];
        this.non_volunteerings = false;
        this.loading = false;
      },
      error => {
        console.log(<any>error);
        this.non_volunteerings = true;
      }
    )
  }

  getNumberPages() {
    this._volunteeringService.getVolunteerings().subscribe(
      response => {
        if (response.length == 0) {
          this.numberPages = 0;
        } else if ((response.length % 5) == 0) {
          this.numberPages = response.length / 5;
          this.numberPages -= 1;
        } else {
          this.numberPages = Math.floor(response.length / 5);
        }
        this.currentPage = 0;
      },
      error => {
        console.log(<any>error);
      }
    )
  }

  addFiveMoreVolunteerings() {
    if (this.currentPage < (this.numberPages + 1)) {
      this.currentPage += 1;
    }
    this._searchService.getSearch(this.currentPage).subscribe(
      response => {
        this.volunteerings.push(...response);
      },
      error => {
        console.log(<any>error);
      }

    )
  }

  AllVolunteeringsByWord() {
    this._searchService.getByWord(this.word).subscribe(
      response => {
        this.selected_volunteerings = response;
        this.all_volunteerings = null;
        this.non_volunteerings = false;
        this.loading = false;
      },
      error => {
        console.log(<any>error);
        this.non_volunteerings = true;
        this.loading = false;
        this.selected_volunteerings = null;
      }
    )
  }

  AllCategories() {
    this._categoryService.getCategories().subscribe(
      response => {
        this.categories = response;
      },
      error => {
        console.log(<any>error);
      }
    )
  }

  AllVolunteeringsByCategory() {
    this._searchService.getByCategory(this.categor).subscribe(
      response => {
        this.selected_volunteerings = response;
        this.all_volunteerings = null;
        this.non_volunteerings = false;
        this.loading = false;
      },
      error => {
        console.log(<any>error);
        this.non_volunteerings = true;
        this.selected_volunteerings = null;
      }
    )
  }

}
