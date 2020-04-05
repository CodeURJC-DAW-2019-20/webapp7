import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';

@Component({
  selector: 'app-ong-settings',
  templateUrl: './ong-settings.component.html',
  styleUrls: ['./ong-settings.component.css']
})
export class OngSettingsComponent implements OnInit {

  public ngo:NGO;

  constructor() { 
    this.ngo = new NGO(null,'','','','','','','','',null,'','');
  }

  ngOnInit() {
  }

  onSubmit(){
    console.log(this.ngo);
    
  }

}
