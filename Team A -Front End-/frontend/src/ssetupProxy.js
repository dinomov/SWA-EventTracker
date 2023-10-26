const { createProxyMiddleware } = require("http-proxy-middleware");
module.exports = function(app) {
    console.log("testing proxy");
  app.use(
    '/api/login',
    createProxyMiddleware({
      target: 'http://localhost:8088',
      changeOrigin: true,
    })
  );
};