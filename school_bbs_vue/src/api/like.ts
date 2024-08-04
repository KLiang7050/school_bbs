import request from "@/utils/request";

export function like(postId, type, mainId,likeUserId) {
  return request({
    url: `/like/like/like/${postId}/${type}/${mainId}/${likeUserId}`,
    method: "post",
  });
}

export function list(mainId) {
  return request({
    url: `/like/like/list/${mainId}`,
    method: "get",
  });
}

export function favorite(postId,favoriteUserId) {
  return request({
    url: `/like/favorites/favorite/${postId}/${favoriteUserId}`,
    method: "post",
  });
}

export function favoriteList() {
  return request({
    url: `/like/favorites/list`,
    method: "get"
  });
}

export function favoriteInfoList(datas) {
  return request({
    url: `/like/favorites/infolist`,
    method: "get",
    params: datas
  });
}