import { ContentSortType } from "./content-sort-type";
import { ContentType } from "./content-type";
import { CostType } from "./cost-type";

export interface ContentParams {
    pageNumber: number;
    pageSize: number;
    sort?: ContentSortType;
    keyphrase?: string;
    minRatings?: number;
    contentTypes?: ContentType[];
    sourceId?: number;
    languageIds?: number[];
    minDuration?: number;
    maxDuration?: number;
    costTypes?: CostType[];
  }