export class User{
    constructor(
        public id: number,
        public name: string,
        public surname: string,
        public email: string,
        public password: string,
        public city: string,
        public telephone: string,
        public image: string,
        public registeredAt: Date
    ){

    }
}