import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterNgoComponent } from './components/register-ngo/register-ngo.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';


const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "register/user", component: RegisterUserComponent},
  {path: "register/ong", component: RegisterNgoComponent},
  {path: "user/settings", component: UserSettingsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
