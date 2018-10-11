// pages/record/record.js
Page({

  /**
   * 页面的初始数据
   */
  data: {



  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {
    var mythis = this;
    wx.request({
      url: 'https://m.lvwei100.com/bill/queryHistory',
      method: 'POST',
      data: {
        "pageSize": 100000
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        console.log(res.data)
        var values = res.data.data;
        console.log(values)
        if (res.data.code == '0') {
          mythis.setData({
            records: values.dataList,
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
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {
    console.log("下拉")
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    console.log("上拉")
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  /**
   * 删除记录
   */
  deleteRecord: function(e) {

    var mythis = this;
    console.log("删除发票记录");
    var id = e.currentTarget.dataset.id;
    console.log(id);
    wx.showModal({
      title: '提示',
      content: '确定要删除吗？',
      success: function(sm) {
        if (sm.confirm) {
          // 用户点击了确定 可以调用删除方法了
          wx.request({
            url: 'https://m.lvwei100.com/bill/delete-record/' + id,
            method: 'GET',
            data: {},
            header: {
              'content-type': 'application/json' // 默认值
            },
            success(res) {
              console.log(res)
              if (res.data.code == '0') {
                console.log("删除成功")
                wx.showToast({
                  title: '删除成功',
                  icon: 'success',
                  duration: 2000,
                  mask: true
                })
                wx.request({
                  url: 'https://m.lvwei100.com/bill/queryHistory',
                  method: 'POST',
                  data: {
                    "pageSize": 100000
                  },
                  header: {
                    'content-type': 'application/json' // 默认值
                  },
                  success(res) {
                    console.log(res.data)
                    var values = res.data.data;
                    console.log(values)
                    console.log(mythis)
                    if (res.data.code == '0') {
                      mythis.setData({
                        records: values.dataList,
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
                  title: res.data.message,
                  icon: 'fail',
                  duration: 2000,
                  mask: true
                })

              }

            }
          })



        } else if (sm.cancel) {
          console.log('用户点击取消')
        }
      }
    })

  }

})