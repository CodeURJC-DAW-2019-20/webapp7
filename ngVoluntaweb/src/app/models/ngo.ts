import { Volunteering } from './volunteering';

export class NGO{
    constructor(
        public id:number,
        public name:string,
        public responsiblename:string,
        public responsiblesurname:string,
        public description:string,
        public address:string,
        public email:string,
        public postal:string,
        public image:string,
        public volunteerings: Set<Volunteering>,
        public telephone: string,
        public password: string
    ){

    }
}