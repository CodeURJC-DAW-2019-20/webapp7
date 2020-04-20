import { UserVolunteering } from './uservolunteering';
import { Like } from './like';

export class User {
    constructor(
        public id: number,
        public registrations: Set<UserVolunteering>,
        public likes: Set<Like>,
        public name: string,
        public surname: string,
        public email: string,
        public password: string,
        public city: string,
        public telephone: string,
        public roles: Array<string>,
        public image: string,
        public registeredAt: Date
    ) {

    }
}