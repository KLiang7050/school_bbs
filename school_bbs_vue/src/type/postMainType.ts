import type { User } from '@/type/userType';

export class PostMain {
    id: number;
    userId: number;
    contentId: number;
    replyCount: number;
    likes: number;
    favorites: number;
    title: string;
    typeId: number;
    type: string;
    isEnable: string;
    delFlag: string;
    createTime: string;
    updateTime: string;
    content: string;
    mediaUrl: string;
    urlList: string[];
    user: User;
}
