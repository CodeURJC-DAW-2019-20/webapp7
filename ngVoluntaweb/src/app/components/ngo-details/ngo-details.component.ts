import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { NGO } from '../../models/ngo';
import { NgoService } from '../../services/ngo.service'
import { global } from '../../services/global';

@Component({
  selector: 'app-ngo-details',
  templateUrl: './ngo-details.component.html',
  styleUrls: ['./ngo-details.component.css'],
  providers: [NgoService]
})
export class NgoDetailsComponent implements OnInit {
  public id_ngo:number;
  public current_ngo: NGO;
  public url: string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _ngoService: NgoService
  ) {
    this.url = global.url;
  }

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
        this.id_ngo = params.id;
    })
    this._ngoService.getNgo(this.id_ngo).subscribe(
      response => {
        this.current_ngo = response;
      },
      error => {
        console.log(<any>error);
      }
    )
  }

}
