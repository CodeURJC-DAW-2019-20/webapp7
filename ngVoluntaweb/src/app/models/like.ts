import { User } from './user';
import { Volunteering } from './volunteering';

export class Like{
    constructor(
        public id: number,
        public user: User,
        public volunteering: Volunteering
    ){}
}