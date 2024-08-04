import type { User } from '@/type/userType';

export interface PostReply {
    id: number;
    userId: number;
    parentId: number;
    parentUserId: number;
    parentUserName: string;
    rootId: number;
    mainId: number;
    contentId: number;
    replyCount: number;
    likes: number;
    floor: number;
    createTime: Date;
    updateTime: Date;
    delFlag: string;
    isEnable: string;
    content: string;
    user: User | null;
    replyList: PostReply[];
}