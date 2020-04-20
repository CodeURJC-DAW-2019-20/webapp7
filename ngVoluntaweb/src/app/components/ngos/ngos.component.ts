import { Component, OnInit } from '@angular/core';
import { NGO } from '../../models/ngo';
import { NgoService } from '../../services/ngo.service';
import { global } from '../../services/global';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-ngos',
  templateUrl: './ngos.component.html',
  styleUrls: ['./ngos.component.css'],
  providers: [NgoService]
})
export class NgosComponent implements OnInit {
  public loading: boolean = true;
  public more: boolean = false;
  public data = [];
  public additional = [];
  public page: number = 2;
  public url: string = global.url;

  constructor(private _ngoService: NgoService, private _titleService: Title) {
    this._titleService.setTitle("ONGs - VoluntaWeb");
  }

  ngOnInit() {
    this._ngoService.getNgos(0).subscribe(
      result => {
        this.data = result;
        this.more = true;
        this.loading = false;
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
        this.loading = false;
      }
    );
  }

  moreNgos() {
    this.loading = true;
    this.data = this.data.concat(this.additional);
    this._ngoService.getNgos(this.page).subscribe(
      result => {
        this.additional = result;
        this.more = true;
        this.page++;
        this.loading = false;
        if (this.additional.length == 0) {
          this.more = false;
        }
      },
      error => {
        this.additional = [];
        this.more = false;
        this.loading = false;
      }
    );
  }
}
