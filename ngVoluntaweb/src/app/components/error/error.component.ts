import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {

  constructor(private _titleService: Title) {
    this._titleService.setTitle("No se ha encontrado la página - VoluntaWeb");
  }

  ngOnInit() {
  }

}
