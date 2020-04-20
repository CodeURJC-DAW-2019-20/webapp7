import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { NGO } from '../models/ngo';
import { Logeable } from '../models/logeable';

import { global } from './global';


@Injectable()

export class EntityService {
    public url: String;
    public logeable;
    public identity;
    public entity_type;

    constructor(
        private _http: HttpClient
    ) {
        this.url = global.url;
    }

    login(logeable): Observable<any> {
        //Get login data from logeable entity and encode in base64
        let flatData = logeable.email + ":" + logeable.password;
        let encodedData = btoa(flatData);
        //Headers
        let headers = new HttpHeaders().set('Content-Type', "application/json").set("Authorization", "Basic " + encodedData);
        //REST request
        if (logeable.type == "ngo") {
            return this._http.get(this.url + "ongs/login", { headers: headers });
        } else {
            return this._http.get(this.url + "users/login", { headers: headers });
        }
    }

    getIdentity() {
        let identity = JSON.parse(localStorage.getItem('identity'));

        if (identity && identity != null && identity != undefined && identity != 'undefined') {
            this.identity = identity;
        } else {
            this.identity = null
        }

        return this.identity;
    }

    getEntityType() {
        let entity_type = localStorage.getItem('entity_type');

        if (entity_type && entity_type != null && entity_type != undefined && entity_type != 'undefined') {
            this.entity_type = entity_type;
        } else {
            this.entity_type = null
        }

        return this.entity_type;
    }

    logout(): Observable<any> {

        let token = localStorage.getItem('authorization');

        let headers = new HttpHeaders().set("Content-Type", "application/json").set("Authorization", "Basic " + token);

        if (this.getEntityType() == "user") {
            return this._http.get(this.url + "users/logout", { headers: headers });
        } else {
            return this._http.get(this.url + "ongs/logout", { headers: headers })
        }
    }
}