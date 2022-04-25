import {Component, OnInit} from '@angular/core';
import {PasswordService} from "../../services/password.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../classes/user";
import {UserService} from "../../services/user.service";
import {Location} from "@angular/common";
import {Router} from "@angular/router";

@Component({
    selector: 'app-user-forgot-password',
    templateUrl: './user-forgot-password.component.html',
    styleUrls: ['./user-forgot-password.component.css']
})
export class UserForgotPasswordComponent {

    public passwordForm: FormGroup;

    public user: User;

    constructor(private passwordService: PasswordService,
                private fb: FormBuilder,
                private userService: UserService) {
        this.userService.getUserInformation()
            .subscribe((user: User) => this.user = user);
        this.createForm();
    }

    private createForm() {
        this.passwordForm = this.fb.group({
            passwordRep: ['', [
                Validators.required
            ]],
            password: [
                '',
                [
                    Validators.required,
                    Validators.pattern(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,10}/)
                ],
            ],
        })
    }

    public resetPassword() {
        this.passwordForm.markAllAsTouched();
        if (this.passwordForm.valid || this.passwordForm.get('password').value !== this.passwordForm.get('passwordRep').value)
            this.passwordService.resetPassword(this.user.email, this.passwordForm.get('password').value);
    }

    public get passwordRep() {
        return this.passwordForm.get('passwordRep');
    }

    public get password() {
        return this.passwordForm.get('password');
    }

}

