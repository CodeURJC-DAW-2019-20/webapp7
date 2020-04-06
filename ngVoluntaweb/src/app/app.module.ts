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


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent,
    NgoSettingsComponent,
    CreateVolunteeringComponent,
    EditVolunteeringComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
