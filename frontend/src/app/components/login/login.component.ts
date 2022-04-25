import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {NavigationPath} from "../../classes/navigation-path";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  public loginForm: FormGroup;

  constructor(private http: HttpClient,
              private authService: AuthService,
              private router: Router,
              private fb: FormBuilder) {
    this.createForm();
  }

  private createForm() {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    })
  }

  public get username() {
    return this.loginForm.get('username');
  }

  public get password() {
    return this.loginForm.get('password');
  }

  public OnLogin() {
      this.loginForm.markAllAsTouched();
      if (this.loginForm.valid)
          this.authService.login(this.loginForm.get("username").value, this.loginForm.get("password").value);
  }

  public OnRegister() {
    this.router.navigate([NavigationPath.REGISTRATION]).then();
  }

  public OnResetPassword() {
    this.router.navigate([NavigationPath.PASSWORD]).then();
  }

}

