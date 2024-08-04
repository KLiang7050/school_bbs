import type { User } from '@/type/userType';

export interface PostInfo {
    id: number;
    userId: number;
    parentId: number;
    rootId: number;
    mainId: number;
    typeId: number;
    type: string;
    title: string;
    allCount: number;
    rootCount: number;
    likes: number;
    favorites: number;
    createTime: Date;
    updateTime: Date;
    content: string;
    user: User | null;
}