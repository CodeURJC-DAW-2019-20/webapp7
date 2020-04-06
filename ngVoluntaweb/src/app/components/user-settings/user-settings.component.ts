import { Component, OnInit, DoCheck } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { EntityService } from 'src/app/services/entity.service';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-user-settings',
  templateUrl: './user-settings.component.html',
  styleUrls: ['./user-settings.component.css'],
  providers: [UserService, EntityService]
})
export class UserSettingsComponent implements OnInit {

  public status: String;
  public user: User;
  public identity;


  constructor(
    private _userService: UserService,
    private _entityService: EntityService,
    private _router: Router,
    private _route: ActivatedRoute
  ) {
    this.identity = this._entityService.getIdentity();
    this.user = this.identity;
   }

  ngOnInit() {
    this.identity = this._entityService.getIdentity();
  }

  ngDoCheck(){
    this.identity = this._entityService.getIdentity();
  }

  onSubmit(form){
    this._userService.update(this.user).subscribe(response=>{
      if(response.id != null){
        this.status = "success";
        localStorage.setItem("identity", JSON.stringify(response));
      }else {
        this.status = "error";
      }
    },
    error=>{
      console.log(<any>error);
      this.status = "error";
    })
    
  }

  deleteUser(){
    let userId =this._entityService.getIdentity().id;
    this._userService.delete(userId).subscribe(
      response=>{
        if (response.id){
          this.status = "success";
          localStorage.removeItem("authorization");
          localStorage.removeItem("identity");
          localStorage.removeItem("entity_type");
        }

        this._router.navigate(['/']);
        
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

}
