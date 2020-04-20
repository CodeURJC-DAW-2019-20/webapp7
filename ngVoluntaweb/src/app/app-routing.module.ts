import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { NgosComponent } from './components/ngos/ngos.component';
import { IndexComponent } from './components/index/index.component';

import { NgoDetailsComponent } from './components/ngo-details/ngo-details.component';
import { SearchComponent } from './components/search/search.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterNgoComponent } from './components/register-ngo/register-ngo.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { JoinedVolunteeringsComponent } from './components/joined-volunteerings/joined-volunteerings.component'
import { ContactComponent } from './components/contact/contact.component';
import { VolunteeringPageComponent } from './components/volunteering-page/volunteering-page.component';
import { EditVolunteeringComponent } from './components/edit-volunteering/edit-volunteering.component';
import { NgoSettingsComponent } from './components/ngo-settings/ngo-settings.component';
import { CreateVolunteeringComponent } from './components/create-volunteering/create-volunteering.component';
import { AdminNgosComponent } from './components/admin-ngos/admin-ngos.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import { AdminVolunteeringsComponent } from './components/admin-volunteerings/admin-volunteerings.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { AdminCommentsComponent } from './components/admin-comments/admin-comments.component';
import { NgoVolunteeringsComponent } from './components/ngo-volunteerings/ngo-volunteerings.component';
import { ErrorComponent } from './components/error/error.component';

import { UserGuard } from './services/user.guard';
import { NGOGuard } from './services/ngo.guard';
import { AnonymousGuard } from './services/anonymous.guard';
import { AdminGuard } from './services/admin.guard';


const routes: Routes = [

  { path: '', component: IndexComponent },
  { path: 'ngos', component: NgosComponent },
  { path: "login", canActivate: [AnonymousGuard], component: LoginComponent },
  { path: "register", canActivate: [AnonymousGuard], component: RegisterComponent },
  { path: "register/user", canActivate: [AnonymousGuard], component: RegisterUserComponent },
  { path: "register/ong", canActivate: [AnonymousGuard], component: RegisterNgoComponent },
  { path: "user/settings", canActivate: [UserGuard], component: UserSettingsComponent },
  { path: "user/myvolunteerings", canActivate: [UserGuard], component: JoinedVolunteeringsComponent },
  { path: "contact", component: ContactComponent },
  { path: "volunteering/new", canActivate: [NGOGuard], component: CreateVolunteeringComponent },
  { path: "volunteering/:id", component: VolunteeringPageComponent },
  { path: "volunteering/edit/:id", canActivate: [NGOGuard], component: EditVolunteeringComponent },
  { path: "ngo/settings", canActivate: [NGOGuard], component: NgoSettingsComponent },
  { path: "admin/ngos", canActivate: [AdminGuard], component: AdminNgosComponent },
  { path: "admin/users", canActivate: [AdminGuard], component: AdminUsersComponent },
  { path: "admin/volunteerings", canActivate: [AdminGuard], component: AdminVolunteeringsComponent },
  { path: 'admin/comments', canActivate: [AdminGuard], component: AdminCommentsComponent },
  { path: "ngo/myvolunteerings", canActivate: [NGOGuard], component: NgoVolunteeringsComponent },
  { path: 'ngo/:id', component: NgoDetailsComponent },
  { path: 'search', component: SearchComponent },
  { path: 'search/:wordIndex', component: SearchComponent },
  { path: 'about-us', component: AboutUsComponent },
  { path: '**', component: ErrorComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
