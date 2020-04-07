import { Injectable } from '@angular/core';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private  categories:Array<Category>;

  constructor() { 
    let aux1:Category = new Category(1,"Ayuda Humanitaria");
    let aux2:Category = new Category(2,"Cooperación al desarrollo");
    let aux3:Category = new Category(3,"Cultura y arte social");
    let aux4:Category = new Category(4,"Derechos humanos");
    let aux5:Category = new Category(5,"Inmigración,refugio y asilo");
    let aux6:Category = new Category(6,"Medio ambiente");
    let aux7:Category = new Category(7,"Personas en riesgo de exclusión");
    let aux8:Category = new Category(8,"Personas sin hogar");
    let aux9:Category = new Category(9,"Protección de animales");
    let aux10:Category = new Category(10,"Salud");

    this.categories = new Array();

    this.categories.push(aux1);
    this.categories.push(aux2);
    this.categories.push(aux3);
    this.categories.push(aux4);
    this.categories.push(aux5);
    this.categories.push(aux6);
    this.categories.push(aux7);
    this.categories.push(aux8);
    this.categories.push(aux9);
    this.categories.push(aux10);
  }

  public getCategoryById(categoryId: number):Category{
    return this.categories[categoryId];
  }

  public getCategories():Array<Category>{
    return this.categories;
  }
}
