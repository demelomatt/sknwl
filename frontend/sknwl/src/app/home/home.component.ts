import { Component, OnInit, ViewChild } from '@angular/core';
import { ContentType } from '../content/content-type';
import { CardContentComponent } from '../content/card/card-content.component';
import { ContentSortType } from '../content/content-sort-type';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent{
  modeOptions: any[] = [{label: 'Plano de estudo', value: 'guide'}, {label: 'Conteúdo', value: 'content'}];
  modeValue: string = 'content';

  sortValues: string[] = ['Mais recente', 'Relevância', 'Nº Avaliações', 'Maior nota'];
  selectedSort!: string;
  searchValue?: string;
  formatTypes?: string[];

  contentFormVisible: boolean = false;

  onSearchValueChanged(value: string) {
    this.searchValue = value
  }

  onTypesChange(types: string[]) {
    this.formatTypes = types;
  }

  showContentForm() {
    this.contentFormVisible = true;
  }

  hideContentForm() {
    this.contentFormVisible = false;
  }
}
