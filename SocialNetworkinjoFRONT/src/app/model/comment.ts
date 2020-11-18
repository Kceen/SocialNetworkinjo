export class Comment {
    constructor(
        public idPostComment: number,
        public content: string,
        public createdOn: Date,
        public creatorUsername: string
    ) {}
}