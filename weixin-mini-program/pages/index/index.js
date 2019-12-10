Page({
  data: {
    typeNameItems: [
      '题名', '责任者', '主题词', 'ISBN/ISSN', '订购号', '分类号', '索书号', '出版社', '丛书名', '题名拼音', '责任者拼音'
    ],
    typeIdItems: [
      'title', 'author', 'keyword', 'isbn', 'ISBN/ISSN', 'asordno', 'coden', 'callno', 'publisher', 'series', 'tpinyin', 'apinyin'
    ],

    orderTypeNameItems: [
      '入藏日期', '题名', '责任者', '索书号', '出版社', '出版日期'
    ],
    orderTypeIdItems: [
      'CATA_DATE', 'M_TITLE', 'M_AUTHOR', 'M_CALL_NO', 'M_PUBLISHER', 'M_PUB_YEAR'
    ],

    orderNameItems: ['降序排列', '升序排列'],
    orderIdItems: ['desc', 'asc'],

    typeIndex: 0,
    orderTypeIndex: 0,
    orderIndex: 0,

    inputVal: "",

    onlyEnable: false,
    withEBook: false,

    hasMore: false
  },

  /**
   * 搜索相关
   */
  /**
   * 监听inputVal值的变化
   */
  inputTyping: function(e) {
    this.setData({
      inputVal: e.detail.value
    });
  },
  /**
   * 按钮提交
   */
  btnConfirm: function(e) {
    this.inputConfirm()
  },
  inputConfirm: function() {
    var strSearchType = 'strSearchType=' + this.data.typeIdItems[this.data.typeIndex]
    var inputVal = '&inputVal=' + this.data.inputVal
    var sort = '&sort='
    var orderBy = '&orderBy='
    var onlyEnable = '&onlyEnable='
    var withEBook = '&withEBook='
    if (this.data.hasMore) {
      sort = '&sort=' + this.data.orderTypeIdItems[this.data.orderTypeIndex]
      orderBy = '&orderBy=' + this.data.orderIdItems[this.data.orderIndex]
      onlyEnable = '&onlyEnable=' + (this.data.onlyEnable ? 'yes' : 'no')
      withEBook = '&withEBook=' + (this.data.withEBook ? 'on' : 'off')
    }
    var hasMore = '&hasMore=' + this.data.hasMore
    console.log('strSearchType', strSearchType)
    console.log('inputVal', inputVal)
    console.log('sort', sort)
    console.log('orderBy', orderBy)
    console.log('onlyEnable', onlyEnable)
    console.log('hasMore', hasMore)
    if (this.data.inputVal) {
      wx.navigateTo({
        url: '../../pages/booklist/booklist?' + strSearchType + inputVal + sort + orderBy + onlyEnable + withEBook + hasMore
      })
    }
  },
  /**
   * 更多选项
   */
  btnMoreOption: function(e) {
    this.setData({
      hasMore: !this.data.hasMore
    })
    if (!this.data.hasMore) {
      this.setData({
        typeIndex: 0,
        orderTypeIndex: 0,
        orderIndex: 0,
        onlyEnable: false,
        withEBook: false,
      })
    }
  },
  /**
   * 检索类别
   */
  bindTypePickerChange: function(e) {
    console.log('type picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      typeIndex: e.detail.value
    })
  },
  /**
   * 排序方式
   */
  bindOrderTypePickerChange: function(e) {
    console.log('order type picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      orderTypeIndex: e.detail.value
    })
  },
  bindOrderPickerChange: function(e) {
    console.log('order picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      orderIndex: e.detail.value
    })
  },
  /**
   * 只显示可借书刊
   */
  bindOnlySwitchChange: function(e) {
    this.setData({
      onlyEnable: !this.data.onlyEnable
    })
  },
  /**
   * 显示电子书刊
   */
  bindEBookSwitchChange: function(e) {
    this.setData({
      withEBook: !this.data.withEBook
    })
  },
  // 监听转发
  onShareAppMessage: function(res) {
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