/**
 * “用户管理”相关接口
 */
import request from '@/utils/request'

export default {

  list(queryParam,pageParam) {
    return request({
      url: '/goods',
      method: 'get',
      data: {
        ...queryParam,
        current: pageParam.current,
        size: pageParam.size
      }
    })
  },

  update(data) {
    return request({
      url: '/goods',
      method: 'put',
      data
    })
  },


  add(data) {
    return request({
      url: '/goods',
      method: 'post',
      data
    })
  },

  uploadCover(data) {
    return request({
      url: '/goods/cover',
      method: 'post',
      data
    })
  },

  delete(data) {
    return request({
      url: '/goods',
      method: 'delete',
      data
    })
  },


}

