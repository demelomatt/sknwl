export interface Cover {
    id: number,
    identifier: string,
    author: {
        id: number,
        identifier: string,
        name: string,
        userName: string,
        profileUrl: string
    },
    urls: [{
        id: number,
        quality: string,
        url: string
    }]
}