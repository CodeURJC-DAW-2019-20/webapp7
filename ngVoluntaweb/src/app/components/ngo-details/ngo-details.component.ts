import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params} from '@angular/router';
import { NGO } from '../../models/ngo';
import { NgoService } from '../../services/ngo.service'

@Component({
  selector: 'app-ngo-details',
  templateUrl: './ngo-details.component.html',
  styleUrls: ['./ngo-details.component.css'],
  providers: [NgoService]
})
export class NgoDetailsComponent implements OnInit {
  public id_ngo:number;
  public current_ngo: NGO;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _ngoService: NgoService
  ) {}

  ngOnInit() {
    this._route.params.subscribe((params: Params) => {
        this.id_ngo = params.id;
        console.log(this.id_ngo);
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
