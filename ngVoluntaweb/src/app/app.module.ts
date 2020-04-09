import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HeaderComponent } from './components/header/header.component';
import { NgoSettingsComponent } from './components/ngo-settings/ngo-settings.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CreateVolunteeringComponent } from './components/create-volunteering/create-volunteering.component';
import { EditVolunteeringComponent } from './components/edit-volunteering/edit-volunteering.component';
import { AngularFileUploaderModule } from 'angular-file-uploader';

import { LoginComponent } from './components/login/login.component';

import { EntityService } from './services/entity.service';
import { RegisterComponent } from './components/register/register.component';
import { RegisterUserComponent } from './components/register-user/register-user.component';
import { RegisterNgoComponent } from './components/register-ngo/register-ngo.component';
import { UserSettingsComponent } from './components/user-settings/user-settings.component';
import { JoinedVolunteeringsComponent } from './components/joined-volunteerings/joined-volunteerings.component';
import { ContactComponent } from './components/contact/contact.component';
import { VolunteeringPageComponent } from './components/volunteering-page/volunteering-page.component';
import { AdminNgosComponent } from './components/admin-ngos/admin-ngos.component';
import { AdminUsersComponent } from './components/admin-users/admin-users.component';
import { AdminVolunteeringsComponent } from './components/admin-volunteerings/admin-volunteerings.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent,
    NgoSettingsComponent,
    CreateVolunteeringComponent,
    EditVolunteeringComponent,
    LoginComponent,
    RegisterComponent,
    RegisterUserComponent,
    RegisterNgoComponent,
    UserSettingsComponent,
    JoinedVolunteeringsComponent,
    ContactComponent,
    VolunteeringPageComponent,
    AdminNgosComponent,
    AdminUsersComponent,
    AdminVolunteeringsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AngularFileUploaderModule
  ],
  providers: [EntityService],
  bootstrap: [AppComponent]
})
export class AppModule { }
