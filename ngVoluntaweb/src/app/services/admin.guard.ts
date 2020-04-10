import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { EntityService } from './entity.service';

@Injectable()
export class AdminGuard implements CanActivate {
    constructor(
        private _router: Router,
        private _entityService: EntityService
    ){

    }

    canActivate(){
        let identity = this._entityService.getIdentity();
        let entity_type = this._entityService.getEntityType();

        if(identity && identity.name && entity_type == "user" && identity.roles.includes("ROLE_ADMIN")){
            return true;
        } else {
            this._router.navigate(['/']);
            return false;
        }
    }
}