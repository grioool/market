export const environment = {
    production: true,
    apiHost: "http://localhost:8080",
    passwordRegexp: "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,10}",
    usernameRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u0451]{2,20}",
    nameCompanyRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u0451]{2,20}",
    wbKeyRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u04510-9]{2,60}",
};
