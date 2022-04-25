import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivateChild, Router, RouterStateSnapshot} from '@angular/router';
import {map, Observable} from "rxjs";
import {AuthService} from "./auth.service";
import {getFirstUrlToken, NavigationPath} from "../classes/navigation-path";

@Injectable({
    providedIn: 'root',
})

export class AuthGuardService implements CanActivateChild {

    constructor(private _router: Router, private authService: AuthService) {
    }

    public canActivateChild(route: ActivatedRouteSnapshot,
                       state: RouterStateSnapshot): Observable<boolean> {
        return this.authService.authenticated()
            .pipe(map(authenticated => {
                if (AuthGuardService.requireAuthentication(state.url)) {
                    if (authenticated) return true;
                    this._router.navigate([NavigationPath.LOGIN]).then();
                    return false;
                }
                if (authenticated) {
                    this._router.navigate([NavigationPath.MAIN]).then();
                    return false;
                }
                return true;
            }))
    }

    private static requireAuthentication(url: string): boolean {
        return ![NavigationPath.REGISTRATION, NavigationPath.LOGIN, NavigationPath.PASSWORD].includes(getFirstUrlToken(url) as NavigationPath);
    }
}
