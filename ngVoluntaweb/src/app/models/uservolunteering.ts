import { User } from './user';
import { Volunteering } from './volunteering';

export class UserVolunteering {
    constructor(
        public id: number,
        public user: User,
        public volunteering: Volunteering,
        public Timestamp: Date
    ) {

    }
}