// pages/tongji/tongji.js
var wxCharts = require('../../utils/wxcharts.js');
var app = getApp();
var pieChart = null;
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
  onReady: function(e) {
    console.log(this)
    //初始化就是本月
    var id = 4;
    var tongjiTitle = this.conversionTitle(id);
    console.log(tongjiTitle)
    var mythis = this;
    var windowWidth = 320;
    try {
      var res = wx.getSystemInfoSync();
      windowWidth = res.windowWidth;
    } catch (e) {
      console.error('getSystemInfoSync failed!');
    }
    wx.request({
      url: 'https://m.lvwei100.com/bill/statistics-input-type',
      method: 'POST',
      data: {
        statisticsInputType: id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        console.log(res)
        if (res.data.code == '0') {
          console.log(res.data.data)
          var key1 = 1;
          var key2 = 2;
          var value1 = 0;
          var value2 = 0;
          var mapObject = res.data.data;
          for (var key in mapObject) {
            if (key == 1) {
              value1 = mapObject[key];
            } else {
              value2 = mapObject[key];
            }
          }
          console.log(key1, key2, value1, value2)


          pieChart = new wxCharts({
            animation: true,
            canvasId: 'pieCanvas',
            type: 'pie',
            series: [{
              name: '自动扫描',
              data: value1,
            }, {
              name: '手动录入',
              data: value2,
            }],
            width: windowWidth,
            height: 400,
            dataLabel: true,
          });
          mythis.setData({
            autoAccount: value1,
            muAcount: value2,
            tongjiTitle: tongjiTitle
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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  tongji: function(e) {
    var id = e.currentTarget.dataset.id;
    console.log(id);
    var tongjiTitle = this.conversionTitle(id);
    console.log(tongjiTitle)
    var mythis = this;
    console.log(this)
    console.log('-------------')
    console.log(e)
    var windowWidth = 320;
    try {
      var res = wx.getSystemInfoSync();
      windowWidth = res.windowWidth;
    } catch (e) {
      console.error('getSystemInfoSync failed!');
    }
    wx.request({
      url: 'https://m.lvwei100.com/bill/statistics-input-type',
      method: 'POST',
      data: {
        statisticsInputType: id
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        console.log(res)
        if (res.data.code == '0') {
          console.log(res.data.data)
          var key1 = 1;
          var key2 = 2;
          var value1 = 0;
          var value2 = 0;
          var mapObject = res.data.data;
          for (var key in mapObject) {
            if (key == 1) {
              value1 = mapObject[key];
            } else {
              value2 = mapObject[key];
            }
          }
          console.log(key1, key2, value1, value2)


          pieChart = new wxCharts({
            animation: true,
            canvasId: 'pieCanvas',
            type: 'pie',
            series: [{
                name: '自动扫描',
                data: value1,
              }, {
                name: '手动录入',
                data: value2,
              }

            ],
            width: windowWidth,
            height: 300,
            dataLabel: true,
          });

          mythis.setData({
            autoAccount: value1,
            muAcount: value2,
            tongjiTitle: tongjiTitle
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
  conversionTitle:function(n){
    switch(n){
      case '1':
        return "今天"
        break;

      case '2':
        return "昨天"
        break;

      case '3':
        return "本周"
        break;

      case '4':
        return "本月"
        break;   

      case '5':
        return "本年"
        break;

      default:
        return "本月"
        break;   
    }
  }
})