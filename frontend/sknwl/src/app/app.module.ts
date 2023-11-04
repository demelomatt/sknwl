import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FormsModule } from '@angular/forms';
import { AvatarModule } from 'primeng/avatar';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { PaginatorModule } from 'primeng/paginator';
import { TagModule } from 'primeng/tag';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardContentComponent } from './content/card/card-content.component';
import { ContentService } from './content/content.service';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HttpClientModule } from '@angular/common/http';
import { MainComponent } from './main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    CardContentComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    AppRoutingModule,
    InputTextModule,
    AvatarModule,
    DropdownModule,
    CardModule,
    TagModule,
    ButtonModule,
    PaginatorModule,
    HttpClientModule
  ],
  providers: [ContentService],
  bootstrap: [AppComponent]
})
export class AppModule { }
