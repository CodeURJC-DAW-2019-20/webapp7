import { Component, OnInit } from '@angular/core';
import { NGO } from 'src/app/models/ngo';
import { NgoService } from 'src/app/services/ngo.service';
import { FormsModule } from '@angular/forms';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-ngo-settings',
  templateUrl: './ngo-settings.component.html',
  styleUrls: ['./ngo-settings.component.css'],
  providers: [NgoService]
})

export class NgoSettingsComponent implements OnInit {

  public ngo: NGO; 
  public status;

  constructor(private _ngoService:NgoService) { 

  

  }

  ngOnInit() {
    this.getUser();
  }


  getUser(){
    this._ngoService.getNGO("15").subscribe(
      (response:any) =>{
        if(response.ngo){
          this.ngo = response.ngo;
        }
        else{
          this.status = 'error';
        }
      },
      error =>{
        this.status = 'error';
        console.log(<any>error);
      }
    );
  
    console.log(this.ngo);
  }

  onSubmit(formNgo){
    //this._ngoService.updateNgo(this.ngo);
  }

}
