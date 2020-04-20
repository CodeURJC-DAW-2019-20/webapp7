import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private _titleService: Title) {
    this._titleService.setTitle("Registrarse - VoluntaWeb");
  }

  ngOnInit() {
  }

}
