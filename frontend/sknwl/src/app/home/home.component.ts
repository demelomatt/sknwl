import { Component, OnInit, ViewChild } from '@angular/core';
import { ContentType } from '../content/content-type';
import { CardContentComponent } from '../content/card/card-content.component';
import { ContentSortType } from '../content/content-sort-type';
import { Language } from '../core/language';
import { CostType } from '../content/cost-type';
import { ComponentProperties } from '../component-properties';

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
  languages?: Language[];
  duration?: Map<string, number>;
  costTypes?: ComponentProperties[];

  contentFormVisible: boolean = false;

  onSearchValueChanged(value: string) {
    this.searchValue = value
  }

  onTypesChange(types: string[]) {
    this.formatTypes = types;
  }

  onLanguagesChange(languages: Language[]) {
    this.languages = languages;
  }

  onDurationChange(duration: Map<string, number>) {
    this.duration = duration;
  }

  onCostChange(cost: ComponentProperties[]) {
    this.costTypes = cost;
    console.log(this.costTypes);
  }

  showContentForm() {
    this.contentFormVisible = true;
  }

  hideContentForm() {
    this.contentFormVisible = false;
  }
}
