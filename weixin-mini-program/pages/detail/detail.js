// pages/detail/detail.js
var app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        detail: null, // 图书信息
        collect: [], // 馆藏信息
        uid: ""
    },

    onLoad: function (option) {
        var that = this
        console.log(option.uid, "uid")
        wx.request({
            url: app.globalData.url + "/item?uid=" + option.uid,
            success: function (res) {
                console.log(res.data, '详情')
                if (res.data != 0) {
                    that.setData({
                        detail: res.data,
                        uid: option.uid
                    })
                } else {
                    that.setData({
                    })
                }
            }
        })
    },
    // 监听转发
    onShareAppMessage: function (res) {
      if (res.from === 'button') {
        // 来自页面内转发按钮
        console.log(res.target)
      }
      return {
        title: "题名：" + this.data.detail.title,
        path: '/pages/detail/detail?uid=' + this.data.uid,
        // imageUrl: '/images/imageUrl.png'
      }
    }

})