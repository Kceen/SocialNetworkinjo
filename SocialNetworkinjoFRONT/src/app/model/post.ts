import { Comment } from './comment';

export class Post {
    constructor(
        public idPost: number,
        public content: string,
        public createdOn: Date,
        public numberOfComments: number,
        public grade: number,
        public numberOfGrades: number,
        public gradesSum: number,
        public creatorUsername: string,
        public comments: Comment[]
    ) {}
}