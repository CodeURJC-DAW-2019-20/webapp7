import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Volunteering } from 'src/app/models/volunteering';
import { EntityService } from 'src/app/services/entity.service';
import { global } from '../../services/global';
import { User } from '../../models/user';
import { UserVolunteering } from 'src/app/models/uservolunteering';
import { Title, DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-volunteering-page',
  templateUrl: './volunteering-page.component.html',
  styleUrls: ['./volunteering-page.component.css'],
  providers: [VolunteeringService, EntityService]
})
export class VolunteeringPageComponent implements OnInit {

  public volunteering: Volunteering;
  public status: String;
  public likesNumber: any;
  public identity;
  public entity_type;
  public isJoined: Boolean;
  public isLiked: Boolean;
  public url;
  public user: User;
  public registration: UserVolunteering;
  public alert: Boolean;
  public btnColor: String;
  public iconColor: String;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _volunteeringService: VolunteeringService,
    private _entityService: EntityService,
    private _titleService: Title,
    private _sanitizer: DomSanitizer

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

    



  }


  sanitizeURL(){
    return this._sanitizer.bypassSecurityTrustResourceUrl('https://www.google.com/maps/embed/v1/place?q='+this.volunteering.city+'&key=AIzaSyBRzPLTqGSEnouc5sMpfk6cYbVKVX42c5s');
  }

  isJoinedF(volId){
    this._volunteeringService.isJoined(this.volunteering.id).subscribe(
      (response)=>{
          this.isJoined = response;
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

  isLikedF(volId){
    this._volunteeringService.isLiked(this.volunteering.id).subscribe(
      (response)=>{
          this.isLiked = response;
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
          this._titleService.setTitle(this.volunteering.name+" - VoluntaWeb");
          this.likesNumber=Array.from(this.volunteering.likes).length;
          this.isJoinedF(this.volunteering.id);
          this.isLikedF(this.volunteering.id);
          if (this.isLiked){
            this.btnColor = "blue2";
            this.iconColor = "blue";
          } else {
            this.btnColor = "grey2";
            this.iconColor = "grey";
          }
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
          this.isJoined= response;
          if (this.isJoined){
            this.alert = true;
          } else {
            this.alert = false;
          }
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

  like(volId){
    this._volunteeringService.like(volId).subscribe(
      response=> {
          this.isLiked= response;
          if (this.isLiked){
            this.likesNumber = this.likesNumber + 1;
            this.btnColor = "blue2";
            this.iconColor = "blue";
          } else {
            this.likesNumber = this.likesNumber -1;
            this.btnColor = "grey2";
            this.iconColor = "grey";
          }
      },
      error=>{
        console.log(<any>error);
      }
    );
  }

}
