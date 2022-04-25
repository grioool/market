import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PurchaseList} from "./components/purchase-list/purchase-list.component";
import {UserList} from "./components/user-list/user-list.component";
import {SupplyList} from "./components/supply-list/supply-list.component";
import {ReportList} from "./components/report-list/report-list.component";
import {MainComponent} from "./components/main/main.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {LoginComponent} from "./components/login/login.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {InformationComponent} from "./components/information/information.component";
import {AuthGuardService} from "./services/authguard.service";
import {SaleListComponent} from "./components/sale-list/sale-list.component";
import {OrderListComponent} from "./components/order-list/order-list.component";
import {NavigationPath} from "./classes/navigation-path";
import {ForgotPasswordComponent} from "./components/forgot-password/forgot-password.component";
import {UserForgotPasswordComponent} from "./components/user-forgot-password/user-forgot-password.component";

const routes: Routes = [{
    path: '',
    canActivateChild: [AuthGuardService],
    children: [
        {path: NavigationPath.USERS, component: UserList},
        {path: NavigationPath.PURCHASES, component: PurchaseList},
        {path: NavigationPath.SUPPLIES, component: SupplyList},
        {path: NavigationPath.REPORTS, component: ReportList},
        {path: NavigationPath.MAIN, component: MainComponent},
        {path: NavigationPath.REGISTRATION, component: RegistrationComponent},
        {path: NavigationPath.LOGIN, component: LoginComponent},
        {path: NavigationPath.PROFILE, component: ProfileComponent},
        {path: NavigationPath.INFORMATION, component: InformationComponent},
        {path: NavigationPath.SALES, component: SaleListComponent},
        {path: NavigationPath.ORDERS, component: OrderListComponent},
        {path: NavigationPath.PASSWORD, component: ForgotPasswordComponent},
        {path: NavigationPath.UPASSWORD, component: UserForgotPasswordComponent},
        {path: "**", redirectTo: NavigationPath.MAIN}
    ]
}];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
