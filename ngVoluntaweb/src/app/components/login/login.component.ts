import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { NGO } from '../../models/ngo';
import { Router, ActivatedRoute, Params } from '@angular/router'
import { Logeable } from 'src/app/models/logeable';
import { EntityService } from '../../services/entity.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [EntityService]
})
export class LoginComponent implements OnInit {

  public page_title: string;
  public user: User;
  public ngo: NGO;
  public status: string;
  public logeable: Logeable;
  public identity;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _entityService: EntityService
    ) {
      this.page_title = 'Iniciar sesión';
      this.logeable = new Logeable("","","user");
     }

  ngOnInit() {
  }

  onSubmit(form){
    this._entityService.login(this.logeable).subscribe(
      response =>{
        if(response.id){
          this.identity = response;
          let flatData = this.logeable.email+":"+this.logeable.password;
          localStorage.setItem('authorization', btoa(flatData));
          localStorage.setItem('identity', JSON.stringify(this.identity));
          localStorage.setItem('entity_type', this.logeable.type);
          this.status = "success";
          this._router.navigate(['/']);
        } else {
          this.status = "error";
        }
      },
      error =>{
        this.status = "error";
        console.log(<any>error)
      }
    );
  }

}
