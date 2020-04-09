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
import { AboutUsComponent } from './components/about-us/about-us.component';
import { AdminCommentsComponent } from './components/admin-comments/admin-comments.component';
import { NgoVolunteeringsComponent } from './components/ngo-volunteerings/ngo-volunteerings.component';


const routes: Routes = [

  {path: '', component: IndexComponent},
  {path: 'ngos',component: NgosComponent},
  {path: "login", component: LoginComponent},
  {path: "register", component: RegisterComponent},
  {path: "register/user", component: RegisterUserComponent},
  {path: "register/ong", component: RegisterNgoComponent},
  {path: "user/settings", component: UserSettingsComponent},
  {path: "user/myvolunteerings", component: JoinedVolunteeringsComponent},
  {path: "contact", component: ContactComponent},
  {path: "volunteering/new", component: CreateVolunteeringComponent},
  {path: "volunteering/:id", component: VolunteeringPageComponent},
  {path: "volunteering/edit/:id", component: EditVolunteeringComponent},
  {path: "ngo/settings", component: NgoSettingsComponent},
  {path: "ngo/myvolunteerings",component:NgoVolunteeringsComponent},
  {path: 'ngo/:id', component: NgoDetailsComponent},
  {path: 'search', component: SearchComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: 'admin/comments', component: AdminCommentsComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
