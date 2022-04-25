import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {NavigationPath} from "../classes/navigation-path";
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root',
})
export class PasswordService {

    constructor(private http: HttpClient,
                private router: Router) {
    }

    private url = environment.apiHost;

    public resetPassword(email: string, password: string) {
        const params: HttpParams = new HttpParams()
            .set('email', email)
            .set('password', password);
        return this.http.post<String>(this.url + '/password/forgetting', {},{params})
        .subscribe((resp: String) => {
            this.router.navigate([NavigationPath.LOGIN]).then();
        })
    }

}
