export default class User {
    constructor(usernameOrEmail,vorname, nachname, geschlecht, geburtsdatum, heimatort, email, password) {
        this.usernameOrEmail = usernameOrEmail;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburtsdatum = geburtsdatum;
        this.heimatort = heimatort;
        this.email = email;
        this.password = password;
    }
}