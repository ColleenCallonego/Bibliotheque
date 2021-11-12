module.exports = {
    devServer: {
        https: false,
        port: 8080,
        open: true, // opens browser window automatically
        proxy:{
            '/':{
                target:'http://localhost:8082/',
                changeOrigin:true
            }
        }
    }
}