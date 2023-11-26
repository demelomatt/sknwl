import { ContentType } from "../content/content-type";

export interface Source { 
    id: number,
    name: string,
    webSiteUri: string,
    contentTypes: ContentType[];
}
