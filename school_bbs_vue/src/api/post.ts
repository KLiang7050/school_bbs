import request from "@/utils/request";

export function getPostList(datas) {
  return request({
    url: "/post/main/list",
    method: "get",
    params: datas
  });
}

export function getReplyList(datas) {
  return request({
    url: `/post/reply/list`,
    method: "get",
    params: datas
  });
}

export function getMyReply(datas) {
  return request({
    url: `/post/reply/myReply`,
    method: "get",
    params: datas
  });
}


export function getTypeList() {
  return request({
    url: "/post/type/list",
    method: "get",
  });
}

export function addMain(datas) {
  return request({
    url: "/post/main/addMain",
    method: "post",
    data: datas
  });
}

export function addReply(replyDatas, mainId, parentUserId) {
  return request({
    url: "/post/reply/addReply",
    method: "post",
    data: replyDatas,
    params: {
      mainId,
      parentUserId
    }
  });
}

export function addReply2(replyDatas, content) {
  return request({
    url: "/post/reply/addReply2",
    method: "post",
    data: replyDatas,
    params: {
      content
    }
  });
}

export function getPostInfo(id) {
  return request({
    url: `/post/main/info/${id}`,
    method: "get",
  });
}

export function getMoreReply(params) {
  return request({
    url: `/post/reply/morelist`,
    method: "get",
    params: params
  });
}

export function getHots() {
  return request({
    url: `/post/main/hots`,
    method: "get"
  });
}

export function getMyPostList(data) {
  return request({
    url: `/post/main/getMyPostList`,
    method: "get",
    params: data
  });
}

export function updatePost(datas){
  return request({
    url: `/post/main/updatePost`,
    method: "post",
    data: datas,
    headers:{
      'Content-Type':'application/json'
    }
  });
}

export function delPost(id){
  return request({
    url: `/post/main/delPost/${id}`,
    method: "post",
  });
}