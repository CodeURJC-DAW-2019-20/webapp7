import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Volunteering } from 'src/app/models/volunteering';
import { EntityService } from 'src/app/services/entity.service';
import { global } from '../../services/global';
import { User } from '../../models/user';
import { UserVolunteering } from 'src/app/models/uservolunteering';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-volunteering-page',
  templateUrl: './volunteering-page.component.html',
  styleUrls: ['./volunteering-page.component.css'],
  providers: [VolunteeringService, EntityService, UserService]
})
export class VolunteeringPageComponent implements OnInit {

  public volunteering: Volunteering;
  public status: String;
  public likesNumber: Number;
  public identity;
  public entity_type;
  public isJoined: Boolean;
  public joined: Volunteering[];
  public url;
  public user: User;
  public registration: UserVolunteering;
  public alert: Boolean;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _volunteeringService: VolunteeringService,
    private _entityService: EntityService,
    private _userService: UserService
  ) { 
    this.identity = this._entityService.getIdentity();
    this.entity_type = this._entityService.getEntityType();
    this.url = global.url;
  }

  ngOnInit() {
    this._route.params.subscribe(
      (params) =>{
        var volId = params['id'];
        this.getVolunteering(volId);
      }
    );

    this._volunteeringService.getByJoined(this.identity.id).subscribe(
      (response)=>{
        let volsJoined = response as Set<Volunteering>;
        this.joined = Array.from(volsJoined);
        if(this.joined.includes(this.volunteering)){
          this.isJoined = true;
        } else {
          this.isJoined = false;
        }
      },
      error=>{
        console.log(<any>error);
      }
    );



  }


  getVolunteering(volId){
    this._volunteeringService.getVolunteeringById(volId).subscribe(
      response=> {
        if (response.id != null){
          this.volunteering = response;
          this.likesNumber=Array.from(this.volunteering.likes).length;
        } else {
          this.status = "error";
          this._router.navigate(['/']);
        }
      },
      error=>{
        console.log(<any>error);
        this._router.navigate(['/']);
      }
    );
  }

  join(volId){
    this._volunteeringService.join(volId).subscribe(
      response=> {
        if (response.id){
          this.alert= true;
        }
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

}
