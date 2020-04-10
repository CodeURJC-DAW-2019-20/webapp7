import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-about-us',
  templateUrl: './about-us.component.html',
  styleUrls: ['./about-us.component.css']
})
export class AboutUsComponent implements OnInit {

  constructor(private _titleService: Title) {
    this._titleService.setTitle("Sobre nosotros - VoluntaWeb");
   }

  ngOnInit() {
  }

}
