import { Component, OnInit } from '@angular/core';
import { NgoService } from '../../services/ngo.service';
import { global } from '../../services/global';

@Component({
  selector: 'app-admin-ngos',
  templateUrl: './admin-ngos.component.html',
  styleUrls: ['./admin-ngos.component.css'],
  providers:[NgoService]
})
export class AdminNgosComponent implements OnInit {
  public more: boolean=false;
  public data = [];
  public additional = [];
  public page: number = 0;
  public url: string=global.url;

  constructor(private _ngoService: NgoService) {
  }

  ngOnInit() {
    this._ngoService.getNgos(0).subscribe(
      result => {
        this.data = result;
      },
      error => {
        console.log("No hay nada");
      }
    );
    this._ngoService.getNgos(1).subscribe(
      result => {
        this.additional = result;
        this.more = true;
      },
      error => {
        this.more = false;
      }
    );
  }

  deleteNgo(id: number) {
    console.log("Eliminado");
    console.log(id);
  }

  moreNgos(){
    this.data= this.data.concat(this.additional);
    console.log(this.additional);
    this.page++;
    this._ngoService.getNgos(this.page).subscribe(
      result=>{
        this.additional=result;
        this.more=true;
        console.log(this.data);
      },
      error=>{
        this.more=false;
        console.log("error");
      }
    );
    
  }
  
}
