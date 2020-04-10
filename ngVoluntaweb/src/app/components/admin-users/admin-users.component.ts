import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { global } from '../../services/global';
import { User } from 'src/app/models/user';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css'],
  providers: [UserService]
})
export class AdminUsersComponent implements OnInit {
  public loading: boolean = true;
  public more: boolean = false;
  public data = [];
  public additional = [];
  public page: number = 2;
  public url: string = global.url;

  constructor(private _userService: UserService, private _titleService: Title) { 
    this._titleService.setTitle("Administrar usuarios - VoluntaWeb");
  }

  ngOnInit() {
    this._userService.getUsers(0).subscribe(
      result => {
        this.data = result;
        this.loading = false;
      },
      error => {
        console.log("No hay nada");
      }
    );
    this._userService.getUsers(1).subscribe(
      result => {
        this.additional = result;
        this.more = true;
      },
      error => {
        this.more = false;
      }
    );
  }

  deleteUser(id: number) {
    this._userService.delete(id).subscribe(
      (response:User) =>{
        if(response){
          window.location.reload();
        }
      },
      error =>{
        console.log(<any> error);
      }
    );
  }

  moreUsers() {
    this.loading = true;
    this.data = this.data.concat(this.additional);
    if (this.more) {
      this._userService.getUsers(this.page).subscribe(
        result => {
          this.additional = result;
          this.more = true;
          this.page++;
          this.loading = false;
          if(this.additional.length==0){
            this.more=false;
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
