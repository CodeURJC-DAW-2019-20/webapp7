import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { NgoSettingsService } from 'src/app/service/ngo-settings.service';

@Component({
  selector: 'app-ong-settings',
  templateUrl: './ngo-settings.component.html',
  styleUrls: ['./ngo-settings.component.css']
})

export class NgoSettingsComponent implements OnInit {

  public ngo: NGO;
  public _ngoSettingsService: NgoSettingsService; 

  constructor() { 

    this._ngoSettingsService = new NgoSettingsService();

    this.ngo = this._ngoSettingsService.getNGO();

  }

  ngOnInit() {

  }

  onSubmit(formNgo){

    this._ngoSettingsService.updateNgo(this.ngo);

  }

}
