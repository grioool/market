export class User {
    constructor(
        public id: number,
        public email: string,
        public password: string,
        public username: string,
        public nameCompany: string,
        public wildBerriesKeys: string,
        public isBlocked: boolean,
        public isSubscribed: boolean
    ) {}
}
