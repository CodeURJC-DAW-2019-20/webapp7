import { Component, OnInit } from '@angular/core';
import { NgoService } from '../../services/ngo.service';
import { global } from '../../services/global';
import { NGO } from 'src/app/models/ngo';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-ngos',
  templateUrl: './admin-ngos.component.html',
  styleUrls: ['./admin-ngos.component.css'],
  providers:[NgoService]
})
export class AdminNgosComponent implements OnInit {
  public loading: boolean=true;
  public more: boolean=false;
  public data = [];
  public additional = [];
  public page: number = 2;
  public url: string=global.url;

  constructor(private _ngoService: NgoService, private _titleService: Title) {
    this._titleService.setTitle("Administrar ONGs - VoluntaWeb");
  }

  ngOnInit() {
    this._ngoService.getNgos(0).subscribe(
      result => {
        this.data = result;
        this.more = true;
        this.loading=false;
      },
      error => {
        console.log("No hay nada");
      }
    );
    this._ngoService.getNgos(1).subscribe(
      result => {
        this.additional = result;
      },
      error => {
        this.more = false;
        this.loading=false;
      }
    );
  }

  deleteNgo(id: number, $event: MouseEvent) {
    ($event.target as HTMLButtonElement).disabled = true;
    ($event.target as HTMLButtonElement).value= "Procesando";
    this._ngoService.delete(id).subscribe(
      (response:NGO) =>{
        if(response){
          ($event.target as HTMLButtonElement).value= "Eliminada";
        }
      },
      error =>{
        ($event.target as HTMLButtonElement).value= "Error";
        console.log(<any> error);
      }
    );
  }

  moreNgos(){
    this.loading=true;
    this.data= this.data.concat(this.additional);
    this._ngoService.getNgos(this.page).subscribe(
      result=>{
        this.additional=result;
        this.more=true;
        this.page++;
        this.loading=false;
        if(this.additional.length==0){
          this.more=false;
        }
      },
      error=>{
        this.additional=[];
        this.more=false;
        this.loading=false;
      }
    );
  }
  
}
