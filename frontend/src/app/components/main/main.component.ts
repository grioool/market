import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {NavigationPath} from "../../classes/navigation-path";

@Component({
    selector: 'app-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css']
})
export class MainComponent {

    public navigationPath: typeof NavigationPath = NavigationPath;

    constructor(private router: Router) {
    }

    public routeTo(path: string) {
        this.router.navigate([path]).then();
    }

}
