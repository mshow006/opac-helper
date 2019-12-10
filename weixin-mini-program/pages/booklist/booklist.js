// pages/customs/customs.js
var app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    booklist: [], // 搜索结果
    currentPage: 0,
    pageSize: 0,
    resultSum: "",
    title: "",
    isNull: false,

    strSearchType: "",
    inputVal: "",
    sort: "",
    orderBy: "",
    onlyEnable: "",
    withEBook: "",

    hasMore: ""
  },

  onLoad: function(option) {
    var that = this
    that.setData({
      strSearchType: option.strSearchType,
      inputVal: option.inputVal,
      hasMore: option.hasMore
    })
    if (option.hasMore) {
      that.setData({
        sort: option.sort,
        orderBy: option.orderBy,
        onlyEnable: option.onlyEnable,
        withEBook: option.withEBook
      })
    }
    var params = {
      "strSearchType": that.data.strSearchType,
      "inputVal": that.data.inputVal,
      "sort": that.data.sort,
      "orderBy": that.data.orderBy,
      "onlyEnable": that.data.onlyEnable,
      "withEBook": that.data.withEBook
    }
    console.log('请求url', app.globalData.url + '/simpleSearch?' + params.strSearchType + '=' + params.inputVal +
      '&sort=' + params.sort + '&orderBy=' + params.orderBy +
      '&onlylendable=' + params.onlyEnable + '&with_ebook=' + params.withEBook)
    wx.request({
      url: app.globalData.url + '/simpleSearch',
      data: JSON.stringify(params),
      header: {
        'content-type': 'application/json' // 默认值
      },
      method: 'POST',
      success: function(res) {
        console.log(res.data, '搜索结果')
        if (res.data == 0) {
          that.setData({
            isNull: true,
            resultSum: "图书检索结果: 0"
          })
        } else {
          that.setData({
            booklist: res.data.books,
            currentPage: res.data.currentPage,
            pageSize: res.data.pageSize,
            resultSum: res.data.resultSum,
            title: res.data.title,
            strSearchType: option.strSearchType,
            inputVal: option.inputVal
          })
        }
      }
    })
  },
  GetDetail: function(e) {
    wx.navigateTo({
      url: '../../pages/detail/detail?uid=' + e.currentTarget.id,
    })
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    var that = this
    wx.showToast({
      title: '数据加载中',
      icon: 'loading',
      duration: 1000,
    });
    var params = {
      "strSearchType": that.data.strSearchType,
      "inputVal": that.data.inputVal,
      "sort": that.data.sort,
      "orderBy": that.data.orderBy,
      "onlyEnable": that.data.onlyEnable,
      "withEBook": that.data.withEBook,
      "page": that.data.currentPage + 1,
    }
    console.log('请求url', app.globalData.url + '/simpleSearch?' + params.strSearchType + '=' + params.inputVal +
      '&sort=' + params.sort + '&orderBy=' + params.orderBy +
      '&onlylendable=' + params.onlyEnable + '&with_ebook=' + params.withEBook + '&page=' + params.page)
    if (that.data.currentPage < that.data.pageSize) {
      wx.request({
        url: app.globalData.url + '/simpleSearch?',
        data: JSON.stringify(params),
        header: {
          'content-type': 'application/json' // 默认值
        },
        method: 'POST',
        success: function(res) {
          console.log(res.data, '搜索结果')
          that.setData({
              booklist: that.data.booklist.concat(res.data.books),
              currentPage: res.data.currentPage,
              pageSize: res.data.pageSize,
              resultSum: res.data.resultSum,
              title: res.data.title
            }),
            console.log(that.data.booklist, '图书信息')
        }
      })
    } else {
      wx.showToast({
        title: '到底啦，朋友',
        icon: "none"
      })
    }
  },
})