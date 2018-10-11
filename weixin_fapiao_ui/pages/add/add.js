// pages/add/add.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  /**
   * 历史发票记录跳转
   */
  record:function(){
    console.log("fsdfsd")
    wx.navigateTo({
      url: '../record/record'
    })
  },

  /**
   * 手动录入
   */
  maInput: function (e) {
    const that = this;
    var val = e.detail.value;
    console.log(val)
    var mythis = this;
    if (val.bill_code == ""){
      wx.showToast({
        title: '请输入发票代码',
        icon: 'none',
        duration: 2000,
        mask: true
      })
      return false;
    }
    if (val.bill_num == ""){
      wx.showToast({
        title: '请输入发票号码',
        icon: 'none',
        duration: 2000,
        mask: true
      })
     return false;
    }
    wx.request({
      url: 'https://m.lvwei100.com/bill/insert',
      method: 'POST',
      data: {
        billCode: val.bill_code,
        billNum: val.bill_num,
        amount: val.bill_amount,
        billDate: val.bill_date,
        inputType: '2'
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        console.log(res.data)
        if (res.data.code == '0') {
          wx.showToast({
            title: '发票保存成功',
            icon: 'succes',
            duration: 2000,
            mask: true
          })
          console.log(mythis);
          mythis.setData({
            bill_code: '',
            bill_num: '',
            bill_amount: '',
            bill_date: ''
          })

        } else {
          wx.showToast({
            title: res.data.message,
            icon: 'fail',
            duration: 2000,
            mask: true
          })
        }
      }
    })

  },

  /**
   * 自动扫描
   */
  autoScanInput: function () {

    wx.scanCode({
      success: (res) => {
        console.log(res)
        if (res.errMsg == 'scanCode:ok') {
          console.log(res.result)
          var resultArray = res.result.split(",");
          var bill_code = resultArray[2];
          var bill_num = resultArray[3];
          var bill_amount = resultArray[4];
          var bill_date = resultArray[5];

          wx.request({
            url: 'https://m.lvwei100.com/bill/insert',
            method: 'POST',
            data: {
              billCode: bill_code,
              billNum: bill_num,
              amount: bill_amount,
              billDate: bill_date,
              inputType: '1'
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success(res) {
              console.log(res.data)
              if (res.data.code == '0') {
                wx.showToast({
                  title: '发票保存成功',
                  icon: 'succes',
                  duration: 2000,
                  mask: true
                })
              } else {
                wx.showToast({
                  title: res.data.message,
                  icon: 'fail',
                  duration: 2000,
                  mask: true
                })
              }
            }
          })

        } else {
          wx.showToast({
            title: '自动扫码失败',
            icon: 'fail',
            duration: 2000,
            mask: true
          })
        }
      }
    })
  }

})