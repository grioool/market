import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Location} from "@angular/common";
import {User} from "../../classes/user";
import {isPresent} from "../../../util";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {NavigationPath} from "../../classes/navigation-path";

@Component({
  selector: 'app-information',
  templateUrl: './information.component.html',
  styleUrls: ['./information.component.css']
})
export class InformationComponent implements OnInit {
    title = 'frontend';

    @ViewChild('readOnlyTemplate', {static: false}) readOnlyTemplate: TemplateRef<any> | undefined;
    @ViewChild('editTemplate', {static: false}) editTemplate: TemplateRef<any> | undefined;

    public editedUser: User = null;

    public user: User;

    public isNewRecord: boolean = false;

  constructor(private userService: UserService,
              private location: Location,
              private router: Router) {

      this.userService.getUserInformation()
          .subscribe((user: User) => this.user = user);
  }

    ngOnInit() {
        this.editUser(this.user);
    }

    public loadUsers() {
    }

    public back(): void {
        this.location.back();
    }

    hide: boolean = true;

    public hideText(){
        this.hide = !this.hide;
    }

    public editUser(user: User) {
        this.editedUser = new User(user.id, user.email, user.password, user.username, user.nameCompany, user.wildBerriesKeys, user.isBlocked, user.isSubscribed);
    }

    public updateUser(user: User) {
        this.userService.updateUser(user).subscribe();
        this.router.navigate([NavigationPath.PROFILE]).then();
    }

    public loadTemplate(user: User) {
        if (this.editedUser && this.editedUser.id === user.id) {
            return this.editTemplate;
        } else {
            return this.readOnlyTemplate;
        }
    }

    public OnChange() {
        this.router.navigate([NavigationPath.UPASSWORD]).then();
    }

    public isReadOnly(user: User): boolean {
        return !this.isEditable(user);
    }

    public isEditable(user: User): boolean {
        return isPresent(this.editedUser) && this.editedUser.id === user.id;
    }
}
