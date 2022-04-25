// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
    apiHost: "http://localhost:8080",
    passwordRegexp: "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,10}",
    usernameRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u0451]{2,20}",
    nameCompanyRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u0451]{2,20}",
    wbKeyRegexp: "^[A-Za-z\u0410-\u042f\u0430-\u044f\u00cb\u04510-9]{2,60}",
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
