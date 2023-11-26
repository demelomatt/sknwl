import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MessageService } from 'primeng/api';
import { ComponentProperties } from '../component-properties';
import { Language } from '../core/language';
import { Source } from '../core/source';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [MessageService]

})
export class HomeComponent{

  modeOptions: any[] = [{label: 'Plano de estudo', value: 'guide'}, {label: 'Conteúdo', value: 'content'}];
  modeValue: string = 'content';

  sortValues: string[] = ['Mais recente', 'Relevância', 'Nº Avaliações', 'Maior nota'];
  selectedSort!: string;
  searchValue?: string;
  formatTypes?: string[];
  languages?: Language[];
  sources?: Source[];
  fields?: {id: number, name: string}[];
  duration?: Map<string, number>;
  costTypes?: ComponentProperties[];

  contentFormVisible: boolean = false;

  constructor(private messageService: MessageService) {}

  onSearchValueChanged(value: string) {
    this.searchValue = value
  }

  onTypesChange(types: string[]) {
    this.formatTypes = types;
  }

  onLanguagesChange(languages: Language[]) {
    this.languages = languages;
  }

  onSourcesChange(sources: Source[]) {
    this.sources = sources;
  }

  onFieldsChange(fields: {id: number, name: string}[]) {
    this.fields = fields;
  }

  onDurationChange(duration: Map<string, number>) {
    this.duration = duration;
  }

  onCostChange(cost: ComponentProperties[]) {
    this.costTypes = cost;
    }

  showContentForm() {
    this.contentFormVisible = true;
  }

  hideContentForm() {
    this.contentFormVisible = false;
  }

  showMessageToast(message: {severity:string, summary:string, detail: string}) {
    this.messageService.add(message)
  }
}
