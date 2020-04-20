import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { CategoryService } from '../../services/category.service';
import { Category } from 'src/app/models/category';
import { Router } from '@angular/router';
import * as CanvasJS from './canvasjs.min';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  providers: [CategoryService, UserService]
})
export class IndexComponent implements OnInit {
  public categories: Category[];
  public data: number[] = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
  public key: string;

  constructor(private _categoryService: CategoryService, private _userService: UserService, private router: Router, private _titleService: Title) {
    this._titleService.setTitle("Inicio - VoluntaWeb");
  }

  ngOnInit() {
    this._userService.getStats().subscribe(
      result => {
        for (let i = 0; i < 12; i++) {
          this.data[i] = result[i];
        }
        this._categoryService.getCategories().subscribe(
          response => {
            this.categories = response;
          },
          error => {
            console.log(<any>error);
          }
        );
        let chart = new CanvasJS.Chart("chartContainer", {
          animationEnabled: true,
          exportEnabled: true,
          title: {
            text: "Usuarios registrados por meses"
          },
          data: [{
            type: "line",
            dataPoints: [
              { y: this.data[0], label: "Enero" },
              { y: this.data[1], label: "Febrero" },
              { y: this.data[2], label: "Marzo" },
              { y: this.data[3], label: "Abril" },
              { y: this.data[4], label: "Mayo" },
              { y: this.data[5], label: "Junio" },
              { y: this.data[6], label: "Julio" },
              { y: this.data[7], label: "Agosto" },
              { y: this.data[8], label: "Septiembre" },
              { y: this.data[9], label: "Octubre" },
              { y: this.data[10], label: "Noviembre" },
              { y: this.data[11], label: "Diciembre" }
            ]
          }]
        });
        chart.render();
      },
      error => {
        console.log("error");
      }
    )

  }


  prevent(event) {
    if (event.code == "Enter" || event.code == "NumpadEnter") {
      event.preventDefault();
    }
  }

  onSubmit() {
    if (this.key.length > 1) {
      this.router.navigate(['search/?keyword=' + this.key]);
    }
    else {
      this.router.navigate(['search']);
    }
  }

}
