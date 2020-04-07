import { Component, OnInit, DoCheck } from '@angular/core';
import { EntityService } from '../../services/entity.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { global } from '../../services/global';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  public identity;
  public entity_type;
  public url;
  public test

  constructor(
    private _entityService: EntityService,
    private _router: Router,
    private _route: ActivatedRoute
    ) {
      this.url = global.url;
     }

  ngOnInit() {
    this.identity = this._entityService.getIdentity();
    this.entity_type = this._entityService.getEntityType();
  }

  ngDoCheck(){
    this.identity = this._entityService.getIdentity();
    this.entity_type = this._entityService.getEntityType();
  }


  logout(){
    this._entityService.logout().subscribe(
      response =>{
        if (response==true){
          this.identity = null;
          this.entity_type = null;
          localStorage.removeItem("identity");
          localStorage.removeItem("entity_type");
          localStorage.removeItem("authorization");
          this._router.navigate(['/']);
        } 
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

}
