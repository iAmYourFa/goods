// 查询列表接口
const getBrandPage = (params) => {
  return $axios({
    url: '/brand/page',
    method: 'get',
    params
  })
}

// 编辑页面反查详情接口
const queryBrandById = (id) => {
  return $axios({
    url: `/brand/${id}`,
    method: 'get'
  })
}

// 删除当前列的接口
const deleBrand = (ids) => {
  return $axios({
    url: '/brand',
    method: 'delete',
    params: { ids }
  })
}

// 修改接口
const editBrand = (params) => {
  return $axios({
    url: '/brand',
    method: 'put',
    data: { ...params }
  })
}

// 新增接口
const addBrand = (params) => {
  return $axios({
    url: '/brand',
    method: 'post',
    data: { ...params }
  })
}