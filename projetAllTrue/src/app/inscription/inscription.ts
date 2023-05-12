export class Inscription {
    constructor(
        public prenom: string,
        public nom: string,
        public sexe : string,
        public adresse: string,
        public role :String ,
        public email: string,
        public password : string,
        public numTel : String,
        public dateInscription:Date,
    ){}
}
