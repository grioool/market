import {Component} from '@angular/core';
import {PasswordService} from "../../services/password.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-reset-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent {

    public passwordForm: FormGroup;

    constructor(private passwordService: PasswordService,
                private fb: FormBuilder) {
        this.createForm();
    }

    private createForm() {
        this.passwordForm = this.fb.group({
            email: ['', [
                Validators.required,
                Validators.email
            ]],
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
            this.passwordService.resetPassword(this.passwordForm.get('email').value, this.passwordForm.get('password').value);
    }

    public get passwordRep() {
        return this.passwordForm.get('passwordRep');
    }

    public get password() {
        return this.passwordForm.get('password');
    }

    public get email() {
        return this.passwordForm.get('email');
    }
}
