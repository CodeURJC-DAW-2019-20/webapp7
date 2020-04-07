import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CategoryService } from '../../services/category.service';
import { Category } from 'src/app/models/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  providers: [CategoryService, UserService]
})
export class IndexComponent implements OnInit {
  public categories: Category[];
  public stats= [];
  public key: string;
  public chartType: string = 'line';

  public chartDatasets: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40, 8, 9, 10, 11, 12], label: 'Usuarios registros' }
  ];

  public chartLabels: Array<any> = ['Enero', 'Febero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];

  public chartColors: Array<any> = [
    {
      backgroundColor: 'rgba(0, 137, 132, .2)',
      borderColor: 'rgba(0, 10, 130, .7)',
      borderWidth: 2,
    }
  ];

  public chartOptions: any = {
    responsive: true
  };

  public chartClicked(e: any): void { }
  public chartHovered(e: any): void { }

  constructor(private _categoryService: CategoryService,private _userService: UserService, private router: Router) { }

  ngOnInit() {
    this._userService.getStats().subscribe(
      result =>{
        console.log(result);
        this.chartDatasets = [
          {data: result, label: 'Usuarios registros'}
        ];
      },
      error =>{
        console.log(error);
      }
    )
    this.categories= this._categoryService.getCategories();

  }

  onSubmit(){
    if(this.key.length>1){
      this.router.navigate(['/search/?keyword='+this.key]);
    }
    else{
      this.router.navigate(['/search']);
    }
  }

}
