const {resolve} = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const MiniCssExtractPlugin = require('mini-css-extract-plugin')
const OptimizeCssAssetsWebpackPlugin = require('optimize-css-assets-webpack-plugin')
const webpack = require('webpack')
const AddAssetHtmlWebpackPlugin = require('add-asset-html-webpack-plugin')
const PUBLIC_PATH = '/'

process.env.NODE_ENV = "production"

const commonCssLoader = [
    // usYloader取代style-loader，作用：提取js中的css成单独文件
    MiniCssExtractPlugin.loader,
    'css-loader',
    {
        loader: 'postcss-loader',
        options: {
            postcssOptions: {
                plugins: [
                    [
                        'postcss-preset-env',
                        {
                            // Options
                        },
                    ],
                ],
            },
        },
    }
]

module.exports = {
    entry: './src/index.js',
    output: {
        filename: 'js/[name].[contenthash:10].js',
        path: resolve(__dirname, 'build'),
        publicPath: PUBLIC_PATH
    },
    module: {
        rules: [
            {
                oneOf: [
                    {
                        test: /\.css$/,
                        use: [...commonCssLoader]
                    },
                    {
                        test: /\.less$/,
                        use: [
                            ...commonCssLoader,
                            'less-loader'
                        ]
                    },
                    {
                        test: /\.html$/,
                        loader: 'html-loader'
                    },
                    {
                        test: /\.(js|jsx)$/,
                        exclude: /node_modules/,
                        loader: 'babel-loader',
                        options: {
                            "presets": [
                                "@babel/preset-env",
                                "@babel/preset-react",
                                {
                                    "plugins": ['@babel/plugin-proposal-class-properties']
                                }
                            ]
                        }
                    },
                    {
                        test: /\.css$/,
                        exclude: /node_modules/,
                        use: [
                            { loader: 'style-loader' },
                            {
                                loader: 'css-loader',
                                options: {
                                    modules: {
                                        localIdentName: "[name]__[local]___[hash:base64:5]",
                                    },
                                    sourceMap: true
                                }
                            },
                            {
                                loader: 'postcss-loader',
                                options: {
                                    postcssOptions: {
                                        plugins: [
                                            [ 'autoprefixer', {}, ],
                                        ],
                                    },
                                }
                            }
                        ]
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './public/index.html',
            minify: {
                collapseWhitespace: true,
                removeComments: true
            }
        })
    ],
    mode: 'production',
    resolve: {
        extensions: ['.js', '.jsx']
    }
}