import type {User} from "@/type/userType"
import type {PostReply} from "@/type/postReplyType"

export interface MyReply{
    id: number;
    userId: number;
    parentId: number;
    parentUserId: number;
    parentUserName: string;
    rootId: number;
    mainId: number;
    replyCount: number;
    likes: number;
    floor: number;
    contentId: number;
    isEnable: boolean;
    delFlag: boolean;
    createTime: Date;
    updateTime: Date;
    content: string;
    user: User;
    parent: PostReply; 
}