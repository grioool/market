import {Component, OnInit} from '@angular/core';
import {NavigationPath} from "../../classes/navigation-path";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {AuthService} from "../../services/auth.service";

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

    public navigationPath: typeof NavigationPath = NavigationPath;

    constructor(private router: Router,
                private location: Location,
                public service: AuthService) {
    }

    ngOnInit(): void {
    }

    public routeTo(path: string) {
        this.router.navigate([path]).then();
    }

    public back(): void {
        this.location.back();
    }

}
