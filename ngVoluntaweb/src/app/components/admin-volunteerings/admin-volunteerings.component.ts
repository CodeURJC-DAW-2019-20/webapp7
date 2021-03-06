import { Component, OnInit } from '@angular/core';
import { VolunteeringService } from '../../services/volunteering.service';
import { global } from '../../services/global';
import { Volunteering } from 'src/app/models/volunteering';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-volunteerings',
  templateUrl: './admin-volunteerings.component.html',
  styleUrls: ['./admin-volunteerings.component.css'],
  providers: [VolunteeringService]
})
export class AdminVolunteeringsComponent implements OnInit {
  public loading: boolean = true;
  public more: boolean = false;
  public data = [];
  public additional = [];
  public page: number = 2;
  public url: string = global.url;

  constructor(private _volunteeringService: VolunteeringService, private _titleService: Title) {
    this._titleService.setTitle("Administrar voluntariados - VoluntaWeb");
  }

  ngOnInit() {
    this._volunteeringService.getVolunteeringsByPage(0).subscribe(
      result => {
        this.data = result;
        this.loading = false;
      },
      error => {
        console.log(<any>error);
      }
    );
    this._volunteeringService.getVolunteeringsByPage(1).subscribe(
      result => {
        this.additional = result;
        this.more = true;
      },
      error => {
        this.more = false;
      }
    );
  }

  deleteVolunteering(id: number, $event: MouseEvent) {
    ($event.target as HTMLButtonElement).disabled = true;
    ($event.target as HTMLButtonElement).value = "Procesando";
    this._volunteeringService.deleteoneVolunteering(id).subscribe(
      (response: Volunteering) => {
        if (response) {
          ($event.target as HTMLButtonElement).value = "Eliminado";
        }
      },
      error => {
        ($event.target as HTMLButtonElement).value = "Error";
        console.log(<any>error);
      }
    );
  }

  moreVolunteerings() {
    this.loading = true;
    this.data = this.data.concat(this.additional);
    if (this.more) {
      this._volunteeringService.getVolunteeringsByPage(this.page).subscribe(
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

}
