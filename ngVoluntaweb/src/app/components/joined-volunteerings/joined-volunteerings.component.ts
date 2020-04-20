import { Component, OnInit } from '@angular/core';
import { EntityService } from '../../services/entity.service';
import { VolunteeringService } from 'src/app/services/volunteering.service';
import { Volunteering } from 'src/app/models/volunteering';
import { Title } from '@angular/platform-browser';
import { global } from '../../services/global';

@Component({
  selector: 'app-joined-volunteerings',
  templateUrl: './joined-volunteerings.component.html',
  styleUrls: ['./joined-volunteerings.component.css'],
  providers: [EntityService, VolunteeringService]
})
export class JoinedVolunteeringsComponent implements OnInit {
  public identity;
  public joined: Set<Volunteering>;
  public url: string;

  constructor(
    private _entityService: EntityService,
    private _volunteeringService: VolunteeringService,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Mis voluntariados - VoluntaWeb");
    this.identity = this._entityService.getIdentity();
    this.url = global.url;
  }

  ngOnInit() {
    this._volunteeringService.getByJoined(this.identity.id).subscribe(
      response => {
        this.joined = response;
      },
      error => {
        console.log(<any>error);
      }
    );
  }


  getMyVolunteerings() {

  }

}
