import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { EntityService } from './entity.service';

@Injectable()
export class AnonymousGuard implements CanActivate {
    constructor(
        private _router: Router,
        private _entityService: EntityService
    ){

    }

    canActivate(){
        let identity = this._entityService.getIdentity();

        if(identity && identity.name){
            this._router.navigate(['/']);
            return false;
        } else {
            return true;
        }
    }
}