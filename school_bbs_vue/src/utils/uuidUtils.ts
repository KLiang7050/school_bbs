// 单例模式
import { v4 as uuidv4 } from 'uuid';
// 要生成一个随机的字符串，且每次执行不能发生变化
// 游客身份还要持久存储
export function getUUID(){
  return uuidv4();
}