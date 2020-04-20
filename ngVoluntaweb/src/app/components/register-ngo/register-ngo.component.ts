import { Component, OnInit } from '@angular/core';
import { NgoService } from '../../services/ngo.service';
import { NGO } from 'src/app/models/ngo';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-register-ngo',
  templateUrl: './register-ngo.component.html',
  styleUrls: ['./register-ngo.component.css'],
  providers: [NgoService]
})
export class RegisterNgoComponent implements OnInit {

  public ngo: NGO;
  public status: String;

  constructor(
    private _ngoService: NgoService,
    private _titleService: Title
  ) {
    this._titleService.setTitle("Registrarse como ONG - VoluntaWeb");
    this.ngo = new NGO(null, "", "", "", "", "", "", "", "", null, null, "");
  }

  ngOnInit() {
  }


  onSubmit(form) {
    this._ngoService.register(this.ngo).subscribe(response => {
      if (response.id != null) {
        this.status = "success";
      } else {
        this.status = "error";
      }
    },
      error => {
        console.log(<any>error);
        this.status = "error";
      })

  }

}
