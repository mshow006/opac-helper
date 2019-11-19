Page({
  data: {
    array: ['题名', '责任者', '主题词', 'ISBN/ISSN', '订购号', '分类号', '索书号', '出版社', '丛书名', '题名拼音', '责任者拼音'],
    objectArray: [
      {
        id: 'title',
        name: '题名'
      },
      {
        id: 'author',
        name: '责任者'
      },
      {
        id: 'keyword',
        name: '主题词'
      },
      {
        id: 'isbn',
        name: 'ISBN/ISSN'
      },
      {
        id: 'asordno',
        name: '订购号'
      },
      {
        id: 'coden',
        name: '分类号'
      },
      {
        id: 'callno',
        name: '索书号'
      },
      {
        id: 'publisher',
        name: '出版社'
      },
      {
        id: 'series',
        name: '丛书名'
      },
      {
        id: 'tpinyin',
        name: '提名拼音'
      },
      {
        id: 'apinyin',
        name: '责任者拼音'
      },
    ],
    index: 0,
    inputShowed: false,
    inputVal: ""
  },
  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  showInput: function () {
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    this.setData({
      inputVal: ""
    });
  },
  // 在输入框不为空时搜索
  inputTyping: function (e) {
    this.setData({
      inputVal: e.detail.value
    });
    console.log('picker:' + this.data.objectArray[this.data.index].id + this.data.objectArray[this.data.index].name)
    console.log("inputVal:" + this.data.inputVal)
    if (this.data.inputVal) {
      wx.navigateTo({
        url: '../../pages/booklist/booklist?searchField=' + this.data.objectArray[this.data.index].id + '&inputValue=' + this.data.inputVal,
      })
    }
  },
  // 监听转发
  onShareAppMessage: function (res) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '馆藏书目简单检索',
      path: '/pages/index/index',
      imageUrl: '/images/imageUrl.png'
    }
  }
})