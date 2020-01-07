const path = require('path')
const name = 'xmmmmovo'

module.exports = {
  devServer: {
    port: 8089
  },
  chainWebpack(config) {
    // Provide the app's title in webpack's name field, so that
    // it can be accessed in index.html to inject the correct title.
    config.set('name', name)
  }
}
