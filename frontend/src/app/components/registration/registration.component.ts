import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {NavigationPath} from "../../classes/navigation-path";
import {MessageService} from "primeng/api";
import {environment} from "../../../environments/environment";

@Component({
    selector: 'app-registration',
    templateUrl: './registration.component.html',
    styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {

    public registrationForm: FormGroup;

    private passwordRegexp: string = environment.passwordRegexp;

    private usernameRegexp: string = environment.usernameRegexp;

    private nameCompanyRegexp: string = environment.nameCompanyRegexp;

    private wbKeyRegexp: string = environment.wbKeyRegexp;

    constructor(private http: HttpClient,
                private authService: AuthService,
                private messageService: MessageService,
                private router: Router,
                private fb: FormBuilder) {
        this.createForm();
        this.messageService.add({severity:"error", summary: "dfds;lfds"})
    }

    private createForm() {
        this.registrationForm = this.fb.group({
            username: ['', [
                Validators.required,
                Validators.pattern(this.usernameRegexp)
            ]],
            email: ['', [
                Validators.required,
                Validators.email,
            ]],
            nameCompany: ['', [
                Validators.required,
                Validators.pattern(this.nameCompanyRegexp)
            ]],
            wbKey: ['', [
                Validators.required,
                Validators.pattern(this.wbKeyRegexp)
            ]],
            passwordRep: ['', [
                Validators.required
            ]],
            password: [
                '',
                [
                    Validators.required,
                    Validators.pattern(this.passwordRegexp)
                ],
            ],
        })
    }

    public OnLogin() {
        this.router.navigate([NavigationPath.LOGIN]).then();
    }

    public OnRegister() {
        this.registrationForm.markAllAsTouched();
        if (this.registrationForm.valid)
            this.authService.
            register(
                this.registrationForm.get("username").value,
                this.registrationForm.get("email").value,
                this.registrationForm.get("password").value,
                this.registrationForm.get("nameCompany").value,
                this.registrationForm.get("wbKey").value
            );

    }

    public get username() {
        return this.registrationForm.get('username');
    }

    public get email() {
        return this.registrationForm.get('email');
    }

    public get nameCompany() {
        return this.registrationForm.get('nameCompany');
    }

    public get wbKey() {
        return this.registrationForm.get('wbKey');
    }

    public get passwordRep() {
        return this.registrationForm.get('passwordRep');
    }

    public get password() {
        return this.registrationForm.get('password');
    }

    ngOnInit(): void {
    }

}
