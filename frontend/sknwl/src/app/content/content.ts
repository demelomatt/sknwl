import { Member } from "../core/member";
import { ContentType } from "./content-type";
import { Cover } from "./cover";

export interface Content {
    id: number,
    title: string,
    description: string,
    contentType: ContentType
    url: string
    source: {
        id: number,
        name: string,
        webSiteUri: string,
        contentTypes: ContentType[]
    },
    authors: string[],
    subjects: string[],
    studyField: {
        id?: number,
        name: string
    },
    language: {
        id: number,
        code: string,
        name: string
    },
    durationMinutes: number,
    publisher: Member,
    publishedDateTime: Date,
    reviewers: number,
    rating: number,
    price: number,
    coverImage: Cover
}
