import {Component} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {getFirstUrlToken, NavigationPath} from "../../classes/navigation-path";
import {Locale} from "../../classes/locale";
import {LocaleContextHolder} from "../../classes/locale-context-holder";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class HeaderComponent {

    public navigationPath: typeof NavigationPath = NavigationPath;

    public currentLang: Locale;

    constructor(
        private authService: AuthService,
        private router: Router
    ) {
        this.currentLang = LocaleContextHolder.getLocale();
        LocaleContextHolder.locale()
            .subscribe((locale: Locale) => this.currentLang = locale);
    }

    public OnLogout() {
        this.authService.logout();
    }

    public isMenuActive(): boolean {
        return ![NavigationPath.LOGIN, NavigationPath.REGISTRATION, NavigationPath.PASSWORD, NavigationPath.UPASSWORD].includes(getFirstUrlToken(this.router.url) as NavigationPath)
    }

    public routeTo(path: string) {
        this.router.navigate([path]).then();
    }

    ru: boolean = true;

    public changeLang() {
        LocaleContextHolder.nextLocale();
    }
}
