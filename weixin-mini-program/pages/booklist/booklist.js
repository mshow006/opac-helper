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
    searchField: "",
    inputValue: ""
  },

  onLoad: function (option) {
    var that = this
    console.log('option', option)
    console.log('请求url',app.globalData.url + '/simpleSearch?' + option.searchField + '=' + option.inputValue)
    wx.request({
      url: app.globalData.url + '/simpleSearch?' + option.searchField + '=' + option.inputValue,
      success: function (res) {
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
            searchField: option.searchField,
            inputValue: option.inputValue
          })
        }
      }
    })
  },
  GetDetail: function (e) {
    wx.navigateTo({
      url: '../../pages/detail/detail?uid=' + e.currentTarget.id,
    })
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var that = this
    wx.showToast({
      title: '数据加载中',
      icon: 'loading',
      duration: 1000,
    });
    if (that.data.currentPage < that.data.pageSize) {
      wx.request({
        url: app.globalData.url + "/simpleSearch?" + that.data.searchField + '=' + that.data.inputValue+ "&page=" + (that.data.currentPage + 1),
        success: function (res) {
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