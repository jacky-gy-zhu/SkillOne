const path = require('path');

module.exports = {
    entry: {
        index: './src/index.js',
    },
    output: {
        filename: 'js/bundle.js',
        path: path.resolve(__dirname, '../dist')
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                use: 'babel-loader',
                exclude: /node_modules/,
                options: {
                    "presets": [
                        [
                            "@babel/preset-env",
                            {
                                "targets": {
                                    "edge": 17,
                                    "firefox": 60,
                                    "chrome": 67,
                                    "safari": 11.1
                                },
                                "useBuiltIns": "usage"
                            }
                        ],
                        "@babel/preset-react"
                    ]
                }
            }
        ]
    },
    externals: [
        "redux",
        "react-redux"
    ]
}