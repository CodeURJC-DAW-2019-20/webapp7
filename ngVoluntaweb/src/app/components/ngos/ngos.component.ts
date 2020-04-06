import { Component, OnInit } from '@angular/core';
import { NGO } from '../../models/ngo';
import { NgoService } from '../../services/ngo.service';
import { global } from '../../services/global';

@Component({
  selector: 'app-ngos',
  templateUrl: './ngos.component.html',
  styleUrls: ['./ngos.component.css'],
  providers: [NgoService]
})
export class NgosComponent implements OnInit {

  public previous: boolean;
  public next: boolean;
  public page: number=0;
  public data= [];
  public url: string;
  public loading: boolean;

  constructor(private _ngoService: NgoService) {
    this.previous= false;
    this.next= true;
    this.url= global.url;
    this.loading= true;
  }

  ngOnInit() {
    this._ngoService.getNgos(this.page).subscribe(
      result =>{
        this.loading= false;
        this.data=result;
      },
      error => {
        this.data=[];
      }
    );
  }

  previousPage(){
    this.loading= true;
    this.next = true;
    this.page--;
    this.previous= this.page>0;
    this._ngoService.getNgos(this.page).subscribe(
      result =>{
        this.data=result;
      },
      error => {
        
      }
    );
    this.loading= false;
  }

  nextPage(){
    this.loading= true;
    this.previous = true;
    this.page++;
    this._ngoService.getNgos(this.page).subscribe(
      result =>{
        this.data=result;
      },
      error => {
        this.next= false;
      }
    );
    this.loading= false;
    this._ngoService.getNgos(this.page+1).subscribe(
      result =>{
        
      },
      error => {
        this.next= false;
      }
    );
  }
}
